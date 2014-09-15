package eu.akka.saem.alfresco.webscript;

import java.io.IOException;

import org.alfresco.model.ContentModel;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.simple.JSONObject;
import org.springframework.extensions.webscripts.AbstractWebScript;
import org.springframework.extensions.webscripts.Status;
import org.springframework.extensions.webscripts.WebScriptRequest;
import org.springframework.extensions.webscripts.WebScriptResponse;

import eu.akka.saem.alfresco.constants.SAEMArchiveStates;
import eu.akka.saem.alfresco.constants.SAEMModelConstants;

/**
 * 
 *   Webscript d'evaluator pour savoir si une archive est restituable
 * 
 * @Class         : IsRestitutionLaunchableBean.java
 * @Package       : eu.akka.saem.alfresco.webscript
 * @Date          : $Date: 25 févr. 2014 $:
 * @author        : $Author: THOMAS.POGNANT $
 * @version       : $Revision:  $:
 * @Id            : $Id: IsRestitutionLaunchableBean.java $
 *
 */
public class IsRestitutionLaunchableBean extends AbstractWebScript {

	private NodeService nodeService;

	private static final Log LOG = LogFactory.getLog(IsRestitutionLaunchableBean.class);

	@Override
	public void execute(WebScriptRequest req, WebScriptResponse res) throws IOException {
		JSONObject result = new JSONObject();

		if (req.getParameter("nodeRef") == null || req.getParameter("nodeRef") == "") {
			res.setStatus(Status.STATUS_BAD_REQUEST);
			LOG.error("Erreur de récupération du nodeRef");
			return;
		}

		try {
			NodeRef nodeRef = new NodeRef(req.getParameter("nodeRef"));
			
			if (nodeService.getProperty(nodeRef, SAEMModelConstants.PROP_ARCHIVE_STATE_PROP) != null){
				if (nodeService.hasAspect(nodeRef, SAEMModelConstants.ASPECT_ARCHIVABLE) 
						&& !nodeService.hasAspect(nodeRef, SAEMModelConstants.ASPECT_SOUS_OBJET_ARCHIVE) &&
							(nodeService.getProperty(nodeRef, SAEMModelConstants.PROP_ARCHIVE_STATE_PROP).equals(SAEMArchiveStates.INTERMEDIAIRE)
							|| nodeService.getProperty(nodeRef, SAEMModelConstants.PROP_ARCHIVE_STATE_PROP).equals(SAEMArchiveStates.DEFINITIF))
								&& nodeService.hasAspect(nodeRef, SAEMModelConstants.ASPECT_RESTITUABLE)
									&& nodeService.getType(nodeRef).equals(ContentModel.TYPE_FOLDER)){
					result.put("result", true);
					res.setStatus(Status.STATUS_OK);
					res.getWriter().write(JSONObject.toJSONString(result));
					return;
				}
			}
		} catch (Exception e) {
			res.setStatus(Status.STATUS_INTERNAL_SERVER_ERROR);
			LOG.error("Une erreur s'est produite au cours de la récupération du nodeRef", e);
		}

		result.put("result", false);
		res.setStatus(Status.STATUS_OK);
		res.getWriter().write(JSONObject.toJSONString(result));
	}

	//Setters pour spring
	public void setNodeService(NodeService nodeService) {
		this.nodeService = nodeService;
	}

}