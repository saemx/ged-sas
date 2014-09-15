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
import org.alfresco.service.cmr.security.OwnableService;
import org.alfresco.service.cmr.security.PermissionService;

import eu.akka.saem.alfresco.workflow.SAEMWorkflowTask;

/**
 * 
 *   //TODO : To complete
 * 
 * @Class         : SuppressPermissionSV.java
 * @Package       : eu.akka.saem.alfresco.workflow.versement
 * @Date          : $Date: 25 févr. 2014 $:
 * @author        : $Author: THOMAS.POGNANT $
 * @version       : $Revision:  $:
 * @Id            : $Id: SuppressPermissionSV.java $
 *
 */
public class SuppressPermissionSV extends SAEMWorkflowTask implements JavaDelegate {

	final ServiceRegistry serviceRegistry = getServiceRegistry();
	final NodeService nodeService = serviceRegistry.getNodeService();
	final PermissionService permissionService = serviceRegistry.getPermissionService();
	final OwnableService ownableService = serviceRegistry.getOwnableService();

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
//				manageOwnerRecursif(document);
				
//				permissionService.setInheritParentPermissions(document, false);
//				List<AssociationRef> associationTargets = nodeService.getTargetAssocs(document,
//						SAEMModelConstants.PROP_SV_CANDIDATE_GROUPS);
//				for (AssociationRef association : associationTargets) {
					
//					permissionService.clearPermission(document, (String) nodeService.getProperty(association.getTargetRef(), ContentModel.PROP_NAME));
//					permissionService.setPermission(document, (String) nodeService.getProperty(association.getTargetRef(), ContentModel.PROP_NAME),
//							"Consumer", true);
//					
//				}
				
//				permissionService.setPermission(document, permissionService.CONTRIBUTOR, permissionService.getAllPermission(), false);
//				permissionService.setPermission(document, permissionService.CONSUMER, permissionService.getAllPermission(), false);
//				permissionService.setPermission(document, "collaborator", permissionService.getAllPermission(), false);
//				permissionService.setPermission(document, permissionService.CONTRIBUTOR, permissionService.READ, true);
//				permissionService.setPermission(document, permissionService.CONSUMER, permissionService.READ, true);
//				permissionService.setPermission(document, "collaborator", permissionService.READ, true);
				return null;
			}

			private void manageOwnerRecursif(NodeRef document) {
				ownableService.setOwner(document, null);
				for(ChildAssociationRef childassoc : nodeService.getChildAssocs(document)){
					if(childassoc.getTypeQName().equals(ContentModel.ASSOC_CONTAINS)){
						manageOwnerRecursif(childassoc.getChildRef());
					}
				}
			}
		});
	}
}
