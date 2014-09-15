package eu.akka.saem.alfresco.workflow;

import java.util.List;

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
import org.alfresco.service.cmr.site.SiteService;
import org.alfresco.service.cmr.workflow.WorkflowInstance;
import org.alfresco.service.cmr.workflow.WorkflowService;
import org.apache.log4j.Logger;

/**
 * 
 *   Tache d'envoi de mail
 * 
 * @Class         : SendMail.java
 * @Package       : eu.akka.saem.alfresco.workflow
 * @Date          : $Date: 25 févr. 2014 $:
 * @author        : $Author: THOMAS.POGNANT $
 * @version       : $Revision:  $:
 * @Id            : $Id: SendMail.java $
 *
 */
public class SendMail extends SAEMWorkflowTask implements TaskListener {

	private static Logger LOG = Logger.getLogger(SendMail.class);

	ServiceRegistry serviceRegistry = getServiceRegistry();
	NodeService nodeService = serviceRegistry.getNodeService();
	SiteService siteService = serviceRegistry.getSiteService();

	@Override
	public void notify(DelegateTask task) {
		ActivitiScriptNode scriptNode = (ActivitiScriptNode) (task.getVariable("bpm_package"));
		final NodeRef bpmPackage = scriptNode.getNodeRef();

		String workflowName = null;
		String archiveName;
		String siteName;
		boolean workflowTerminated;
		boolean svValidation = false;

		WorkflowService workflowService = serviceRegistry.getWorkflowService();
		WorkflowInstance workflowInstance = workflowService.getWorkflowById((String) task
				.getVariable("workflowinstanceid"));
		NodeRef initiator = workflowInstance.getInitiator();
		String initiatorUserName = (String) nodeService.getProperty(initiator, ContentModel.PROP_USERNAME);

		List<ChildAssociationRef> children = AuthenticationUtil
				.runAsSystem(new RunAsWork<List<ChildAssociationRef>>() {

					@Override
					public List<ChildAssociationRef> doWork() throws Exception {
						return nodeService.getChildAssocs(bpmPackage);
					}
				});

		final NodeRef archive = children.get(0).getChildRef();

		siteName = AuthenticationUtil.runAsSystem(new RunAsWork<String>() {

			@Override
			public String doWork() throws Exception {
				return siteService.getSite(archive).getShortName();
			}
		});
		archiveName = (String) nodeService.getProperty(archive, ContentModel.PROP_NAME);
		workflowTerminated = task.getTaskDefinitionKey().equals("svCorrection") ? false : true;
		
		if (task.getVariable("bpm_workflowDescription").toString().contains("Versement"))
			workflowName = "Versement";
		else if (task.getVariable("bpm_workflowDescription").toString().contains("Elimination")){
			workflowName = "Elimination";
			svValidation = task.getVariableLocal("saemwf_SAValidationApproveRejectOutcome").equals("Accepter") ? true : false;
		}
	
		try {
			SAEMWorkflowUtils
					.sendMail(serviceRegistry, (String) task.getVariable("workflowinstanceid"),
							workflowInstance.getDefinition().getDescription(), archiveName, bpmPackage,
							initiatorUserName, siteName, "activiti$" + task.getId(), workflowTerminated,
							workflowName, svValidation);
		} catch (Exception e) {
			LOG.error("Un mail de notification de workflow n'a pas pu être envoyé", e);
		}
	}

}
