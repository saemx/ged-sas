package eu.akka.saem.alfresco.webscript;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.alfresco.model.ContentModel;
import org.alfresco.repo.workflow.WorkflowModel;
import org.alfresco.service.cmr.invitation.InvitationException;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.cmr.repository.StoreRef;
import org.alfresco.service.cmr.search.ResultSet;
import org.alfresco.service.cmr.search.SearchParameters;
import org.alfresco.service.cmr.search.SearchService;
import org.alfresco.service.cmr.workflow.WorkflowDefinition;
import org.alfresco.service.cmr.workflow.WorkflowPath;
import org.alfresco.service.cmr.workflow.WorkflowService;
import org.alfresco.service.cmr.workflow.WorkflowTask;
import org.alfresco.service.namespace.NamespaceService;
import org.alfresco.service.namespace.QName;
import org.springframework.extensions.webscripts.AbstractWebScript;
import org.springframework.extensions.webscripts.WebScriptRequest;
import org.springframework.extensions.webscripts.WebScriptResponse;

/**
 * 
 *   Classe d'elimination des archives
 * 
 * @Class         : EliminationConfirmation.java
 * @Package       : eu.akka.saem.alfresco.webscript
 * @Date          : $Date: 25 févr. 2014 $:
 * @author        : $Author: THOMAS.POGNANT $
 * @version       : $Revision:  $:
 * @Id            : $Id: EliminationConfirmation.java $
 *
 */
public class EliminationConfirmation extends AbstractWebScript {

	private NodeService nodeService;
	private WorkflowService workflowService;
	private SearchService searchService;

	@SuppressWarnings("unchecked")
	@Override
	public void execute(WebScriptRequest req, WebScriptResponse res) throws IOException {
		SearchParameters sp = new SearchParameters();

		String archiveIdentifier = req.getParameter("archiveIdentifier");
		NodeRef archive;
		
		//get noderef from archiveid
		sp.addStore(StoreRef.STORE_REF_WORKSPACE_SPACESSTORE);
		sp.setLanguage(SearchService.LANGUAGE_LUCENE);
		sp.setQuery("@saem\\:seda_elimination_acknowledgment:\"" + archiveIdentifier + "\"");

		ResultSet queryResults = searchService.query(sp);

		if (queryResults != null && !queryResults.getNodeRefs().isEmpty() && queryResults.getNodeRef(0) != null)
			archive = queryResults.getNodeRef(0);
			
		else {
			sp.setQuery("@saem\\:seda_elimination_acknowledgment:\"" + archiveIdentifier + "\"");
			queryResults = searchService.query(sp);

			if (queryResults != null && !queryResults.getNodeRefs().isEmpty() && queryResults.getNodeRef(0) != null)
				archive = queryResults.getNodeRef(0);
			else return;
		}

		NodeRef wfPackage = workflowService.createPackage(null);
	       nodeService.addChild(wfPackage, archive, ContentModel.ASSOC_CONTAINS,
	               QName.createQName(NamespaceService.CONTENT_MODEL_1_0_URI,
	               QName.createValidLocalName(
	               (String)nodeService.getProperty(archive, ContentModel.PROP_NAME))));
		Map<QName, Serializable> workflowProps = new HashMap(3);
		workflowProps.put(WorkflowModel.PROP_START_DATE, new Date());
		workflowProps.put(WorkflowModel.ASSOC_PACKAGE, wfPackage);
		workflowProps.put(WorkflowModel.PROP_WORKFLOW_DESCRIPTION, ("Elimination - " + nodeService.getProperty(archive, ContentModel.PROP_NAME)));
		
		WorkflowDefinition wfDefinition = this.workflowService.getDefinitionByName("activiti$wfElimination");
		if (wfDefinition == null) {
			// handle workflow definition does not exist
			Object objs[] = { "activiti$wfElimination" };
			throw new InvitationException("invitation.error.noworkflow", objs);
		}

		// start the workflow
		WorkflowPath wfPath = this.workflowService.startWorkflow(wfDefinition.getId(), workflowProps);

		String wfPathId = wfPath.getId();
		List<WorkflowTask> wfTasks = this.workflowService.getTasksForWorkflowPath(wfPathId);

		// throw an exception if no tasks where found on the workflow path
		if (wfTasks.size() == 0) {
			Object objs[] = { "activiti$wfElimination" };
			throw new InvitationException("invitation.error.notasks", objs);
		}

		try {
			WorkflowTask wfStartTask = (WorkflowTask) wfTasks.get(0);
			this.workflowService.endTask(wfStartTask.getId(), null);
		} catch (RuntimeException err) {
			err.printStackTrace();
			throw err;
		}
	}

	public void setNodeService(NodeService nodeService) {
		this.nodeService = nodeService;
	}

	public void setWorkflowService(WorkflowService workflowService) {
		this.workflowService = workflowService;
	}

	public void setSearchService(SearchService searchService) {
		this.searchService = searchService;
	}
}
