package eu.akka.saem.alfresco.workflow.versement;

import java.util.List;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
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
 *   //TODO : To complete
 * 
 * @Class         : AllowPermissionSV.java
 * @Package       : eu.akka.saem.alfresco.workflow.versement
 * @Date          : $Date: 25 févr. 2014 $:
 * @author        : $Author: THOMAS.POGNANT $
 * @version       : $Revision:  $:
 * @Id            : $Id: AllowPermissionSV.java $
 *
 */
public class AllowPermissionSV extends SAEMWorkflowTask implements JavaDelegate {

	final ServiceRegistry serviceRegistry = getServiceRegistry();
	final NodeService nodeService = serviceRegistry.getNodeService();
	final PermissionService permissionService = serviceRegistry.getPermissionService();

	@Override
	public void execute(DelegateExecution exec) throws Exception {

		ActivitiScriptNode scriptNode = (ActivitiScriptNode) (exec.getVariable("bpm_package"));
		final NodeRef bpmPackage = scriptNode.getNodeRef();
		List<ChildAssociationRef> children = AuthenticationUtil
				.runAsSystem(new RunAsWork<List<ChildAssociationRef>>() {

					@Override
					public List<ChildAssociationRef> doWork() throws Exception {
						return nodeService.getChildAssocs(bpmPackage);
					}
				});

		final NodeRef document = children.get(0).getChildRef();

		AuthenticationUtil.runAsSystem(new RunAsWork<Void>() {

			@Override
			public Void doWork() throws Exception {
//				permissionService.setInheritParentPermissions(document, true);
//				List<AssociationRef> associationTargets = nodeService.getTargetAssocs(document,
//						SAEMModelConstants.PROP_SV_CANDIDATE_GROUPS);
//				for (AssociationRef association : associationTargets) {
//
//					permissionService.clearPermission(document, (String) nodeService.getProperty(
//							association.getTargetRef(), ContentModel.PROP_NAME));
//					permissionService.setPermission(document, (String) nodeService.getProperty(
//							association.getTargetRef(), ContentModel.PROP_NAME), "Collaborator", true);
//				}
				return null;
			}
		});
	}
}
