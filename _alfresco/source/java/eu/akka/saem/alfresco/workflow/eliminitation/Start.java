package eu.akka.saem.alfresco.workflow.eliminitation;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.List;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.alfresco.model.ContentModel;
import org.alfresco.repo.security.authentication.AuthenticationUtil;
import org.alfresco.repo.security.authentication.AuthenticationUtil.RunAsWork;
import org.alfresco.repo.workflow.activiti.ActivitiScriptNode;
import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.cmr.repository.ChildAssociationRef;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.cmr.security.PermissionService;
import org.apache.axis.encoding.Base64;
import org.apache.log4j.Logger;

import eu.akka.saem.alfresco.connector.asalae.ws.ServerServiceLocator;
import eu.akka.saem.alfresco.connector.asalae.ws.WebServicePortType;
import eu.akka.saem.alfresco.constants.SAEMBeanContants;
import eu.akka.saem.alfresco.constants.SAEMModelConstants;
import eu.akka.saem.alfresco.constants.SAEMPropertiesConstants;
import eu.akka.saem.alfresco.helper.PropertyReader;
import eu.akka.saem.alfresco.seda.loader.XMLLoader;
import eu.akka.saem.alfresco.seda.v02.ArchiveDestructionRequestType;
import eu.akka.saem.alfresco.seda.v02.complexType.ArchivesIDType;
import eu.akka.saem.alfresco.seda.writer.SEDAWriter;
import eu.akka.saem.alfresco.workflow.SAEMWorkflowTask;

/**
 * 
 *   Tache de demarage du workflow d'elimination
 * 
 * @Class         : Start.java
 * @Package       : eu.akka.saem.alfresco.workflow.eliminitation
 * @Date          : $Date: 25 févr. 2014 $:
 * @author        : $Author: THOMAS.POGNANT $
 * @version       : $Revision:  $:
 * @Id            : $Id: Start.java $
 *
 */
public class Start extends SAEMWorkflowTask implements TaskListener {

	final ServiceRegistry serviceRegistry = getServiceRegistry();
	final NodeService nodeService = serviceRegistry.getNodeService();
	final PermissionService permissionService = serviceRegistry.getPermissionService();
	final PropertyReader propertyReader = (PropertyReader) serviceRegistry
			.getService(SAEMBeanContants.PROPERTY_READER);

	private static Logger LOG = Logger.getLogger(Start.class);

	@Override
	public void notify(DelegateTask task) {
		DelegateExecution exec = task.getExecution();

		ActivitiScriptNode scriptNode = (ActivitiScriptNode) (exec.getVariable("bpm_package"));
		final NodeRef bpmPackage = scriptNode.getNodeRef();
		List<ChildAssociationRef> children = AuthenticationUtil
				.runAsSystem(new RunAsWork<List<ChildAssociationRef>>() {

					@Override
					public List<ChildAssociationRef> doWork() throws Exception {
						return nodeService.getChildAssocs(bpmPackage);
					}
				});

		final NodeRef archive = children.get(0).getChildRef();

		ArchiveDestructionRequestType adrt = new ArchiveDestructionRequestType();
		adrt.setDestructionRequestIdentifier(new ArchivesIDType());
		String bordereau = SEDAWriter.transformSEDAObjectToXML(adrt);

		try {
			ServerServiceLocator ssl = new ServerServiceLocator();
			WebServicePortType test = ssl.getWebServicePort(new URL(propertyReader
					.getProperty(SAEMPropertiesConstants.ASALAE_INT_URL) + "/webservices/service"));
			// Récupération des identifiants de connexion à asalae
			String asalaeLogin = propertyReader.getProperty(SAEMPropertiesConstants.ASALAE_INT_LOGIN);
			String asalaePassword = propertyReader.getProperty(SAEMPropertiesConstants.ASALAE_INT_PASSWORD);

			String bordereauSEDA = new String(Base64.decode(new String(test.wsCompleterBordereau(Base64
					.encode(bordereau.getBytes("UTF8")).getBytes(), asalaeLogin, asalaePassword))), "UTF8");
			ArchiveDestructionRequestType adrtresult = (ArchiveDestructionRequestType) XMLLoader
					.SEDALoader(new ByteArrayInputStream(bordereauSEDA.getBytes()));

			nodeService.setProperty(archive, SAEMModelConstants.PROP_SEDA_ELIMINATION_ACKNOWLEDGMENT,
					adrtresult.getDestructionRequestIdentifier().getValue());

		} catch (Exception ex) {
			LOG.error(ex);
		}

		exec.setVariable("bpm_workflowDescription",
				"Elimination - " + (String) nodeService.getProperty(archive, ContentModel.PROP_NAME));
	}
}
