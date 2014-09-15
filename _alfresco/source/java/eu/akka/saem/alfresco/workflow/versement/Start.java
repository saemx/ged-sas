package eu.akka.saem.alfresco.workflow.versement;

import java.util.List;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.alfresco.model.ContentModel;
import org.alfresco.repo.security.authentication.AuthenticationUtil;
import org.alfresco.repo.security.authentication.AuthenticationUtil.RunAsWork;
import org.alfresco.repo.workflow.activiti.ActivitiScriptNode;
import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.cmr.repository.ChildAssociationRef;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.cmr.security.PermissionService;

import eu.akka.saem.alfresco.workflow.SAEMWorkflowTask;

/**
 * 
 *   Tache de démarrage du workflow de versement
 * 
 * @Class         : Start.java
 * @Package       : eu.akka.saem.alfresco.workflow.versement
 * @Date          : $Date: 25 févr. 2014 $:
 * @author        : $Author: THOMAS.POGNANT $
 * @version       : $Revision:  $:
 * @Id            : $Id: Start.java $
 *
 */
public class Start extends SAEMWorkflowTask implements JavaDelegate {

	final ServiceRegistry serviceRegistry = getServiceRegistry();
	final NodeService nodeService = serviceRegistry.getNodeService();
	final PermissionService permissionService = serviceRegistry.getPermissionService();

	@Override
	public void execute(DelegateExecution exec) throws Exception {
		exec.setVariable("saemwf_SAValidation", false);
		exec.setVariable("saemwf_comment", "");

		ActivitiScriptNode scriptNode = (ActivitiScriptNode) (exec.getVariable("bpm_package"));
		final NodeRef bpmPackage = scriptNode.getNodeRef();
		String docName = AuthenticationUtil
				.runAsSystem(new RunAsWork<String>() {

					@Override
					public String doWork() throws Exception {
						List<ChildAssociationRef> children =  nodeService.getChildAssocs(bpmPackage);
						NodeRef document = children.get(0).getChildRef();
						return (String) nodeService.getProperty(document, ContentModel.PROP_NAME);
					}
				});


		exec.setVariable("bpm_workflowDescription", "Versement - " + docName);
		
		
		// String xmlArchive = (String) nodeService.getProperty(document,
		// SAEMModelConstants.PROP_SEDA_ARCHIVE_XML);
		// nodeService.getProperties(document)
		// Object SEDAObject = null;
		//
		// DocumentBuilderFactory factory =
		// DocumentBuilderFactory.newInstance();
		// factory.setNamespaceAware(true);
		// try {
		// DocumentBuilder builder = factory.newDocumentBuilder();
		// Document doc = builder.parse(new InputSource(new
		// StringReader(xmlArchive)));
		// doc.getDocumentElement().setAttribute("xmlns", "v0.2");
		// SEDAObject = XMLLoader.SEDALoader(doc);
		// } catch (ClassNotFoundException | InstantiationException |
		// IllegalAccessException | SecurityException
		// | NoSuchMethodException | ParserConfigurationException | SAXException
		// | IOException e) {
		// e.printStackTrace();
		// }

	}
}
