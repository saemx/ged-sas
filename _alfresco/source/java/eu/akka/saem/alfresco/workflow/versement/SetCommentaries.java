package eu.akka.saem.alfresco.workflow.versement;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.apache.commons.lang.StringUtils;

import eu.akka.saem.alfresco.workflow.SAEMWorkflowTask;

/**
 * 
 *   Classe permettant d'attribuer des commentaires aux différentes étapes
 *   du workflow de versement
 * 
 * @Class         : SetCommentaries.java
 * @Package       : eu.akka.saem.alfresco.workflow.versement
 * @Date          : $Date: 25 févr. 2014 $:
 * @author        : $Author: THOMAS.POGNANT $
 * @version       : $Revision:  $:
 * @Id            : $Id: SetCommentaries.java $
 *
 */
public class SetCommentaries extends SAEMWorkflowTask implements TaskListener {

	@Override
	public void notify(DelegateTask task) {

		if (task.getEventName().equals(EVENTNAME_COMPLETE)) {
			if (StringUtils.isNotBlank((String) task.getVariable("bpm_comment")))
			{
				task.setVariable("saemwf_comment", task.getVariable("bpm_comment"));
			}
		}
		if (task.getEventName().equals(EVENTNAME_CREATE)) {
			if (StringUtils.isNotBlank((String) task.getVariable("bpm_comment")))
			{
				task.setVariable("saemwf_comment", task.getVariable("bpm_comment"));
				task.setVariable("bpm_comment", "");
			}
		}
	}
}
