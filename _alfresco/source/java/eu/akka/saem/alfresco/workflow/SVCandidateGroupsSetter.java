package eu.akka.saem.alfresco.workflow;

import java.util.List;
import java.util.Set;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.task.IdentityLink;
import org.alfresco.model.ContentModel;
import org.alfresco.repo.security.authentication.AuthenticationUtil;
import org.alfresco.repo.security.authentication.AuthenticationUtil.RunAsWork;
import org.alfresco.repo.workflow.activiti.ActivitiScriptNode;
import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.cmr.repository.AssociationRef;
import org.alfresco.service.cmr.repository.ChildAssociationRef;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;

import eu.akka.saem.alfresco.constants.SAEMModelConstants;

/**
 * 
 * @Class         : SVCandidateGroupsSetter.java
 * @Package       : eu.akka.saem.alfresco.workflow
 * @Date          : $Date: 25 févr. 2014 $:
 * @author        : $Author: THOMAS.POGNANT $
 * @version       : $Revision:  $:
 * @Id            : $Id: SVCandidateGroupsSetter.java $
 *
 */
public class SVCandidateGroupsSetter extends SAEMWorkflowTask implements TaskListener {

	final ServiceRegistry serviceRegistry = getServiceRegistry();
	final NodeService nodeService = serviceRegistry.getNodeService();

	@Override
	public void notify(DelegateTask task) {
		if (task.getEventName().equals(EVENTNAME_CREATE)) {
			// getting folder selected by the initiator of the workflow
			ActivitiScriptNode scriptNode = (ActivitiScriptNode) task.getExecution().getVariable(
					"bpm_package");
			final NodeRef bpmPackage = scriptNode.getNodeRef();
			NodeRef profilableFolder = AuthenticationUtil
					.runAsSystem(new RunAsWork<NodeRef>() {

						@Override
						public NodeRef doWork() throws Exception {
							List<ChildAssociationRef> children = getServiceRegistry().getNodeService()
									.getChildAssocs(bpmPackage);
							NodeRef document = children.get(0).getChildRef();

							// finding the folder with profilable aspect
							while (!nodeService.hasAspect(document, SAEMModelConstants.ASPECT_PROFILABLE)) {
								document = nodeService.getPrimaryParent(document).getParentRef();
							}

							return document;
						}
					});

			// deleting current candidate groups
			Set<IdentityLink> candidates = task.getCandidates();
			for (IdentityLink candidate : candidates) {
				task.deleteCandidateGroup(candidate.getGroupId());
			}

			// set the effective candidate groups to the groups associated to
			// the profilable folder
			List<AssociationRef> associationTargets = nodeService.getTargetAssocs(profilableFolder,
					SAEMModelConstants.PROP_SV_CANDIDATE_GROUPS);
			for (AssociationRef association : associationTargets) {
				task.addCandidateGroup((String) nodeService.getProperty(association.getTargetRef(),
						ContentModel.PROP_AUTHORITY_NAME));
			}
		}
	}
}
