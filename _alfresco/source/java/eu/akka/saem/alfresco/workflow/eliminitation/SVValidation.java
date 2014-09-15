package eu.akka.saem.alfresco.workflow.eliminitation;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.alfresco.repo.security.authentication.AuthenticationUtil;
import org.alfresco.repo.security.authentication.AuthenticationUtil.RunAsWork;
import org.alfresco.repo.workflow.activiti.ActivitiScriptNode;
import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.cmr.repository.ChildAssociationRef;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.cmr.security.PermissionService;
import org.alfresco.service.namespace.QName;

import eu.akka.saem.alfresco.constants.SAEMBeanContants;
import eu.akka.saem.alfresco.constants.SAEMModelConstants;
import eu.akka.saem.alfresco.helper.PropertyReader;
import eu.akka.saem.alfresco.workflow.SAEMWorkflowTask;


/**
 * 
 *   Tache de validation du workflow d'elimination 
 * 
 * @Class         : SVValidation.java
 * @Package       : eu.akka.saem.alfresco.workflow.eliminitation
 * @Date          : $Date: 25 févr. 2014 $:
 * @author        : $Author: THOMAS.POGNANT $
 * @version       : $Revision:  $:
 * @Id            : $Id: SVValidation.java $
 *
 */
public class SVValidation extends SAEMWorkflowTask implements TaskListener {

	final ServiceRegistry serviceRegistry = getServiceRegistry();
	final NodeService nodeService = serviceRegistry.getNodeService();
	final PermissionService permissionService = serviceRegistry.getPermissionService();
	final PropertyReader propertyReader = (PropertyReader) serviceRegistry
			.getService(SAEMBeanContants.PROPERTY_READER);
	
	@Override
	public void notify(DelegateTask task) {
		if (task.getEventName().equals(EVENTNAME_COMPLETE)) {
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

			Map<QName, Serializable> aspectProperties = new HashMap<>();

			if (((String) (task.getVariableLocal("saemwf_SVValidationApproveRejectOutcome")))
					.equals("Accepter")) {
				aspectProperties.put(SAEMModelConstants.PROP_STATE, "accept");
			}
			else {
				aspectProperties.put(SAEMModelConstants.PROP_STATE, "reject");
			}
			nodeService.addAspect(archive, SAEMModelConstants.ASPECT_ELIMINATION_STATE, aspectProperties);
		}
	}
}