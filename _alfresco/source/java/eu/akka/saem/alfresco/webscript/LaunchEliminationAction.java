package eu.akka.saem.alfresco.webscript;

import java.io.IOException;

import org.alfresco.service.cmr.action.Action;
import org.alfresco.service.cmr.action.ActionService;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.cmr.workflow.WorkflowService;
import org.springframework.extensions.webscripts.AbstractWebScript;
import org.springframework.extensions.webscripts.WebScriptRequest;
import org.springframework.extensions.webscripts.WebScriptResponse;

import eu.akka.saem.alfresco.constants.SAEMModelConstants;


/**
 * 
 * Webscript permettant de lancer l'action d'élimination 
 * 
 * @Class         : LaunchEliminationAction.java
 * @Package       : eu.akka.saem.alfresco.webscript
 * @Date          : $Date: 25 févr. 2014 $:
 * @author        : $Author: THOMAS.POGNANT $
 * @version       : $Revision:  $:
 * @Id            : $Id: LaunchEliminationAction.java $
 *
 */
public class LaunchEliminationAction extends AbstractWebScript{

	public static final String ACTION_NAME = "asalae-elimination";
	private ActionService actionService;
	private NodeService nodeService;
	private WorkflowService workflowService;
	
	/**
	 *  @param req arguments passés via l'URL lors de la requête, ici un seul : le noderef de l'archive sur laquelle on lance l'élimination (qui a déjà été versée, dont la DUA est expirée et le sort final est à détruire)
	 *  @param rep réponse envoyée, vide dans ce cas puisqu'on ne fait qu'effectuer un traitement
	 */ 
	@Override
	public void execute(WebScriptRequest req, WebScriptResponse rep) throws IOException {

		//Recuperation du noderef sur lequel on lance l'action
		String nodeRefParameter = req.getParameter("noderef");
		NodeRef nodeRefToEliminate = new NodeRef(nodeRefParameter);

		
		synchronized (nodeRefToEliminate) {
			
			if (nodeService.hasAspect(nodeRefToEliminate, SAEMModelConstants.ASPECT_ELIMINABLE)
					&& nodeService.hasAspect(nodeRefToEliminate, SAEMModelConstants.ASPECT_ARCHIVABLE)
					&& workflowService.getWorkflowsForContent(nodeRefToEliminate, true).size() == 0) {
				//Récupération et lancement de l'action d'élimination
				Action eliminationAction = actionService.createAction(this.ACTION_NAME);
				actionService.executeAction(eliminationAction, nodeRefToEliminate);		
			}
			

		}
		
	}

	public void setActionService(ActionService actionService) {
		this.actionService = actionService;
	}
	
	public void setNodeService(NodeService nodeService) {
		this.nodeService = nodeService;
	}
	
	public void setWorkflowService(WorkflowService workflowService) {
		this.workflowService = workflowService;
	}

}
