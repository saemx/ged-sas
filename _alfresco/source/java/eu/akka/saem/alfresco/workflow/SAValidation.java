package eu.akka.saem.alfresco.workflow;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

/**
 * 
 * 
 * @Class         : SAValidation.java
 * @Package       : eu.akka.saem.alfresco.workflow
 * @Date          : $Date: 25 févr. 2014 $:
 * @author        : $Author: THOMAS.POGNANT $
 * @version       : $Revision:  $:
 * @Id            : $Id: SAValidation.java $
 *
 */
public class SAValidation extends SAEMWorkflowTask implements TaskListener {

	@Override
	public void notify(DelegateTask task) {
		if (task.getEventName().equals(EVENTNAME_COMPLETE)) {
			if (((String) (task.getVariableLocal("saemwf_SAValidationApproveRejectOutcome")))
					.equals("Accepter")) {
				task.setVariable("saemwf_SAValidation", true);
			}
		}
	}
}