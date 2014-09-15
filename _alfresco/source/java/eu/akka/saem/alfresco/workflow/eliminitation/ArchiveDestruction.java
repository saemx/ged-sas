package eu.akka.saem.alfresco.workflow.eliminitation;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.alfresco.repo.security.authentication.AuthenticationUtil;
import org.alfresco.repo.security.authentication.AuthenticationUtil.RunAsWork;
import org.alfresco.repo.workflow.activiti.ActivitiScriptNode;
import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.cmr.repository.ChildAssociationRef;
import org.alfresco.service.cmr.repository.ContentService;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.apache.axis.encoding.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import eu.akka.saem.alfresco.connector.asalae.ws.ServerServiceLocator;
import eu.akka.saem.alfresco.connector.asalae.ws.WebServicePortType;
import eu.akka.saem.alfresco.constants.SAEMBeanContants;
import eu.akka.saem.alfresco.constants.SAEMModelConstants;
import eu.akka.saem.alfresco.constants.SAEMPropertiesConstants;
import eu.akka.saem.alfresco.helper.PropertyReader;
import eu.akka.saem.alfresco.seda.v02.ArchiveDestructionRequestType;
import eu.akka.saem.alfresco.seda.v02.ArchiveTransferType;
import eu.akka.saem.alfresco.seda.v02.complexType.ArchiveObjectType;
import eu.akka.saem.alfresco.seda.v02.complexType.ArchiveType;
import eu.akka.saem.alfresco.seda.v02.complexType.ArchivesIDType;
import eu.akka.saem.alfresco.seda.v02.complexType.OrganizationType;
import eu.akka.saem.alfresco.seda.writer.SEDAWriter;
import eu.akka.saem.alfresco.utils.ProfilSEDAUtils;
import eu.akka.saem.alfresco.workflow.SAEMWorkflowTask;

/**
 * 
 *   Tache de destruction de l'archive du workflow d'elimination
 * 
 * @Class         : ArchiveDestruction.java
 * @Package       : eu.akka.saem.alfresco.workflow.eliminitation
 * @Date          : $Date: 25 févr. 2014 $:
 * @author        : $Author: THOMAS.POGNANT $
 * @version       : $Revision:  $:
 * @Id            : $Id: ArchiveDestruction.java $
 *
 */
public class ArchiveDestruction extends SAEMWorkflowTask implements JavaDelegate {

	private static final Log LOG = LogFactory.getLog(ArchiveDestruction.class);

	final ServiceRegistry serviceRegistry = getServiceRegistry();
	final ContentService contentService = serviceRegistry.getContentService();
	final NodeService nodeService = serviceRegistry.getNodeService();
	final ProfilSEDAUtils profilSEDAUtils = (ProfilSEDAUtils) serviceRegistry.getService(SAEMBeanContants.PROFIL_SEDA_UTILS);
	final PropertyReader propertyReader = (PropertyReader) serviceRegistry.getService(SAEMBeanContants.PROPERTY_READER);

	@Override
	public void execute(DelegateExecution exec) throws Exception {
		try {

			// Récupération des identifiants de connexion à asalae
			String asalaeLogin = propertyReader.getProperty(SAEMPropertiesConstants.ASALAE_INT_LOGIN);
			String asalaePassword = propertyReader.getProperty(SAEMPropertiesConstants.ASALAE_INT_PASSWORD);


			ServerServiceLocator ssl = new ServerServiceLocator();
			WebServicePortType test = ssl.getWebServicePort(new URL(propertyReader
					.getProperty(SAEMPropertiesConstants.ASALAE_INT_URL) + "/webservices/service"));

			ActivitiScriptNode scriptNode = (ActivitiScriptNode) (exec.getVariable("bpm_package"));
			final NodeRef bpmPackage = scriptNode.getNodeRef();
			List<ChildAssociationRef> children = AuthenticationUtil
					.runAsSystem(new RunAsWork<List<ChildAssociationRef>>() {

						@Override
						public List<ChildAssociationRef> doWork() throws Exception {
							return nodeService.getChildAssocs(bpmPackage);
						}
					});

			//recuperation de l'archive (ou sous archive a eliminer)
			NodeRef archiveFolderNodeRef = children.get(0).getChildRef();
			// creation d'une copie temporaire afin de recuperer -dans le cas ou on elimine un sous-objet d'archive- les informations presentes a la racine de l'archive
			NodeRef tmpArchiveFolder = archiveFolderNodeRef;
			ArchiveTransferType archiveSedaObject;

			List<ArchiveType> archives = new ArrayList<ArchiveType>();

			ArchiveType archive = new ArchiveType();
			archive.setArchivalAgencyArchiveIdentifier(new ArchivesIDType());

			// Récupération du bordereau de versement
			if (nodeService.hasAspect(tmpArchiveFolder, SAEMModelConstants.ASPECT_SOUS_OBJET_ARCHIVE)) {
				ArchiveObjectType aot = new ArchiveObjectType();
				aot.setArchivalAgencyObjectIdentifier(new ArchivesIDType());
				aot.getArchivalAgencyObjectIdentifier().setValue((String) nodeService.getProperty(tmpArchiveFolder,
						SAEMModelConstants.PROP_SEDA_SOUS_OBJET_ARCHIVE_ARCHIVAL_AGENCY_OBJECT_IDENTIFIER));
				archive.addContains(aot);
			} else {
				archive.getArchivalAgencyArchiveIdentifier().setValue(
						(String) nodeService.getProperty(tmpArchiveFolder,
								SAEMModelConstants.PROP_SEDA_ARCHIVE_ARCHIVAL_AGENCY_ARCHIVE_IDENTIFIER));
			}

			archives.add(archive);

			while (tmpArchiveFolder != null
					&& (!nodeService.hasAspect(tmpArchiveFolder, SAEMModelConstants.ASPECT_ARCHIVABLE)
					 || nodeService.hasAspect(tmpArchiveFolder, SAEMModelConstants.ASPECT_SOUS_OBJET_ARCHIVE)))
				tmpArchiveFolder = nodeService.getPrimaryParent(tmpArchiveFolder).getParentRef();

			archiveSedaObject = (ArchiveTransferType) profilSEDAUtils.getSedaObject(tmpArchiveFolder,
					SAEMModelConstants.PROP_SEDA_ARCHIVE_XML_FULL);
			archiveSedaObject = fillSedaBlankFields(archiveSedaObject);

			String dri = (String) nodeService.getProperty(archiveFolderNodeRef, SAEMModelConstants.PROP_SEDA_ELIMINATION_ACKNOWLEDGMENT);

			ArchiveDestructionRequestType adrt = (ArchiveDestructionRequestType) new ArchiveDestructionRequestType();
			adrt.setDestructionRequestIdentifier(new ArchivesIDType());
			adrt.getDestructionRequestIdentifier().setValue(dri);
			adrt.setArchivalAgency(new OrganizationType());
			adrt.getArchivalAgency().setIdentification(new ArchivesIDType());
			adrt.getArchivalAgency().getIdentification()
			.setValue(archiveSedaObject.getArchivalAgency().getIdentification().getValue());

			if (exec.getVariable("bpm_comment") != null)
				adrt.setComment(exec.getVariable("bpm_comment").toString());
			else 			
				adrt.setComment("");

			adrt.setOriginatingAgency(new OrganizationType());
			adrt.getOriginatingAgency().setIdentification(new ArchivesIDType());
			adrt.getOriginatingAgency()
			.getIdentification()
			.setValue(
					archiveSedaObject.getContains().get(0).getContentDescription()
					.getOriginatingAgency().get(0).getIdentification().getValue());

			adrt.setArchive(archives);

			if (!test.wsElimination("bordereau.xml", Base64.encode(SEDAWriter.transformSEDAObjectToXML(adrt).getBytes("UTF8")).getBytes(), asalaeLogin, asalaePassword).equals("0"))
				LOG.error("Processus d'elimination de l'archive avorte");
			else {
				nodeService.removeAspect(archiveFolderNodeRef, SAEMModelConstants.ASPECT_ELIMINABLE);
				nodeService.removeAspect(archiveFolderNodeRef, SAEMModelConstants.ASPECT_RESTITUABLE);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Cette méthode permet de remplir les champ générés automatiquement par
	 * Asalae
	 * 
	 * @param archiveSedaObject
	 * @return
	 */
	private ArchiveTransferType fillSedaBlankFields(ArchiveTransferType archiveSedaObject) {

		// Insertion de la date du transfert
		archiveSedaObject.getDate().setDate(new Date());

		return archiveSedaObject;
	}
}
