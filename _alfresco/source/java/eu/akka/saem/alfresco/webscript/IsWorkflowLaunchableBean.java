package eu.akka.saem.alfresco.webscript;

import java.io.IOException;

import org.alfresco.model.ContentModel;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.cmr.workflow.WorkflowService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.simple.JSONObject;
import org.springframework.extensions.webscripts.AbstractWebScript;
import org.springframework.extensions.webscripts.Status;
import org.springframework.extensions.webscripts.WebScriptRequest;
import org.springframework.extensions.webscripts.WebScriptResponse;

import eu.akka.saem.alfresco.constants.SAEMModelConstants;

/**
 * 
 *   Webscript d'evaluator pour savoir si une archive est versable
 * 
 * @Class         : IsWorkflowLaunchableBean.java
 * @Package       : eu.akka.saem.alfresco.webscript
 * @Date          : $Date: 25 févr. 2014 $:
 * @author        : $Author: THOMAS.POGNANT $
 * @version       : $Revision:  $:
 * @Id            : $Id: IsWorkflowLaunchableBean.java $
 *
 */
public class IsWorkflowLaunchableBean extends AbstractWebScript {

	private NodeService nodeService;
	private WorkflowService workflowService;

	private static final Log LOG = LogFactory.getLog(IsWorkflowLaunchableBean.class);

	@Override
	public void execute(WebScriptRequest req, WebScriptResponse res) throws IOException {
		JSONObject result = new JSONObject();

		if (req.getParameter("nodeRef") == null || req.getParameter("nodeRef") == "") {
			res.setStatus(Status.STATUS_BAD_REQUEST);
			return;
		}

		try {
			NodeRef nodeRef = new NodeRef(req.getParameter("nodeRef"));

			if (!nodeService.getType(nodeRef).equals(ContentModel.TYPE_FOLDER)) {
				result.put("result", true);
				res.setStatus(Status.STATUS_OK);
				res.getWriter().write(JSONObject.toJSONString(result));
				return;
			}

			if (nodeService.hasAspect(nodeRef, SAEMModelConstants.ASPECT_NON_VERSE)
					&& !nodeService.hasAspect(nodeRef, SAEMModelConstants.ASPECT_VERSE)
					&& !nodeService.hasAspect(nodeRef, SAEMModelConstants.ASPECT_SOUS_OBJET_ARCHIVE)
					&& workflowService.getWorkflowsForContent(nodeRef, true).size() == 0) {
				result.put("result", true);
				res.setStatus(Status.STATUS_OK);
				res.getWriter().write(JSONObject.toJSONString(result));
				return;
			}
		} catch (Exception e) {
			res.setStatus(Status.STATUS_INTERNAL_SERVER_ERROR);
			LOG.error("Une erreur s'est produite au cours de la récupération du nodeRef", e);
		}

		result.put("result", false);
		res.getWriter().write(JSONObject.toJSONString(result));
	}

	//Setters pour spring
	public void setNodeService(NodeService nodeService) {
		this.nodeService = nodeService;
	}

	public void setWorkflowService(WorkflowService workflowService) {
		this.workflowService = workflowService;
	}

}
