package eu.akka.saem.alfresco.workflow.restitution;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.rpc.ServiceException;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.alfresco.model.ContentModel;
import org.alfresco.repo.security.authentication.AuthenticationUtil;
import org.alfresco.repo.security.authentication.AuthenticationUtil.RunAsWork;
import org.alfresco.repo.workflow.activiti.ActivitiScriptNode;
import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.cmr.dictionary.InvalidAspectException;
import org.alfresco.service.cmr.repository.ChildAssociationRef;
import org.alfresco.service.cmr.repository.InvalidNodeRefException;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.apache.axis.encoding.Base64;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import eu.akka.saem.alfresco.connector.asalae.Exceptions.AsalaeConnectorException;
import eu.akka.saem.alfresco.connector.asalae.ws.ServerServiceLocator;
import eu.akka.saem.alfresco.connector.asalae.ws.WebServicePortType;
import eu.akka.saem.alfresco.constants.SAEMArchiveStates;
import eu.akka.saem.alfresco.constants.SAEMBeanContants;
import eu.akka.saem.alfresco.constants.SAEMModelConstants;
import eu.akka.saem.alfresco.constants.SAEMPropertiesConstants;
import eu.akka.saem.alfresco.helper.PropertyReader;
import eu.akka.saem.alfresco.seda.loader.XMLLoader;
import eu.akka.saem.alfresco.seda.v02.ArchiveRestitutionRequestType;
import eu.akka.saem.alfresco.seda.v02.complexType.ArchiveType;
import eu.akka.saem.alfresco.seda.v02.complexType.ArchivesIDType;
import eu.akka.saem.alfresco.seda.v02.complexType.DateTimeType;
import eu.akka.saem.alfresco.seda.writer.SEDAWriter;
import eu.akka.saem.alfresco.workflow.SAEMWorkflowTask;

/**
 *   Tache d'envoi du bordereau de demande de restitution à Asalae
 * 
 * @Class         : AsalaeBordereauSender.java
 * @Package       : eu.akka.saem.alfresco.workflow.restitution
 * @Date          : $Date: 25 févr. 2014 $:
 * @author        : $Author: THOMAS.POGNANT $
 * @version       : $Revision:  $:
 * @Id            : $Id: AsalaeBordereauSender.java $
 *
 */
public class AsalaeBordereauSender extends SAEMWorkflowTask implements TaskListener {

	final ServiceRegistry serviceRegistry = getServiceRegistry();
	final NodeService nodeService = serviceRegistry.getNodeService();
	final PropertyReader propertyReader = (PropertyReader) serviceRegistry.getService(SAEMBeanContants.PROPERTY_READER);

	private static Logger LOGGER = Logger.getLogger(AsalaeBordereauSender.class);

	NodeRef folderRef2 = null;
	NodeRef createdFile = null;
	Boolean isArchiveDefinitive = false;

	@Override
	public void notify(DelegateTask exec) {
		
		ActivitiScriptNode scriptNode = (ActivitiScriptNode) (exec.getVariable("bpm_package"));
		final NodeRef bpmPackage = scriptNode.getNodeRef();

		List<ChildAssociationRef> children = AuthenticationUtil.runAsSystem(new RunAsWork<List<ChildAssociationRef>>() {

			@Override
			public List<ChildAssociationRef> doWork() throws Exception {
				return nodeService.getChildAssocs(bpmPackage);
			}
		});

		final NodeRef archive = children.get(0).getChildRef();
		// Determine si l'archive est en asalae intermediaire ou définitif
		if (nodeService.getProperty(archive, SAEMModelConstants.PROP_ARCHIVE_STATE_PROP).equals(
				SAEMArchiveStates.DEFINITIF)) {
			LOGGER.info("Archive définitive" + isArchiveDefinitive.toString());
			isArchiveDefinitive = true;
		}
		
		// Création de l'archivetype avec l'identifier
		ArchiveType at = new ArchiveType();
		String aaaIdentifier = (String) nodeService.getProperty(archive,
				SAEMModelConstants.PROP_SEDA_ARCHIVE_ARCHIVAL_AGENCY_ARCHIVE_IDENTIFIER);
		ArchivesIDType ait = new ArchivesIDType();
		ait.setValue(aaaIdentifier);
		at.setArchivalAgencyArchiveIdentifier(ait);

		// Creation de l'objet SEDA
		ArchiveRestitutionRequestType arrt = new ArchiveRestitutionRequestType();
		arrt.setRestitutionRequestIdentifier(new ArchivesIDType());
		arrt.addArchive(at);
		arrt.setComment(exec.getVariable("bpm_comment") != null ? (String) exec.getVariable("bpm_comment") : "  ");
		DateTimeType date = new DateTimeType();
		date.setDate(new Date());
		arrt.setDate(date);

		// ObjetSEDA vers string pour transfer vers asalae
		String bordereau = SEDAWriter.transformSEDAObjectToXML(arrt);

		try {
			
			// Connexion à Asalae
			ServerServiceLocator ssl = new ServerServiceLocator();
			
			LOGGER.info("Valeur isArchiveDefinitive: " + isArchiveDefinitive);
			
			WebServicePortType test = ssl.getWebServicePort(new URL(propertyReader
					.getProperty(isArchiveDefinitive ? SAEMPropertiesConstants.ASALAE_DEF_URL
							: SAEMPropertiesConstants.ASALAE_INT_URL)
					+ "/webservices/service"));

			if (test == null) {
				LOGGER.error("Impossible de se connecter à Asalae");
				throw new AsalaeConnectorException("Impossible de se connecter à Asalae");
			}

			// Completion du bordereau par webservice asalae
			String asalaeLogin = propertyReader
					.getProperty(isArchiveDefinitive ? SAEMPropertiesConstants.ASALAE_DEF_LOGIN
							: SAEMPropertiesConstants.ASALAE_INT_LOGIN);
			String asalaePassword = propertyReader
					.getProperty(isArchiveDefinitive ? SAEMPropertiesConstants.ASALAE_DEF_PASSWORD
							: SAEMPropertiesConstants.ASALAE_INT_PASSWORD);
			
			LOGGER.info("Asalae login: " + asalaeLogin);
			LOGGER.info("Asalae password: " + asalaePassword);
			
			String bordereauSEDA = new String(Base64.decode(new String(test.wsCompleterBordereau(
					Base64.encode(bordereau.getBytes("UTF8")).getBytes(), asalaeLogin, asalaePassword))), "UTF8");

			ArchiveRestitutionRequestType arrtresult = (ArchiveRestitutionRequestType) XMLLoader
					.SEDALoader(new ByteArrayInputStream(bordereauSEDA.getBytes()));

			// Mise en place de l'identifiant de restitution sur l'archive
			nodeService.setProperty(archive, SAEMModelConstants.PROP_SEDA_RESTITUTION_ACKNOWLEDGMENT, arrtresult
					.getRestitutionRequestIdentifier().getValue());

			exec.setVariable("bpm_workflowDescription",
					"Restitution - " + (String) nodeService.getProperty(archive, ContentModel.PROP_NAME));

			// Envoi du bordereau de restitution
			if (test.wsRestitution("bordereau.xml",
					Base64.encode(SEDAWriter.transformSEDAObjectToXML(arrtresult).getBytes("UTF8")).getBytes(), null, null,
					null, asalaeLogin, asalaePassword).equals("0")) {
				nodeService.removeAspect(archive, SAEMModelConstants.ASPECT_RESTITUABLE);
			} else {
				LOGGER.error("Processus de restitution de l'archive avorte");
			}

		} catch (ServiceException | InvalidAspectException | InvalidNodeRefException | ClassNotFoundException | InstantiationException | IllegalAccessException | SecurityException | NoSuchMethodException | ParserConfigurationException | SAXException | IOException e) {
			LOGGER.error("Erreur lors du traitement de l'envoi du bordereau de restitution", e);
		}
		
	}

}
