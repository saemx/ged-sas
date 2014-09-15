package eu.akka.saem.alfresco.webscript;

import java.io.IOException;

import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.cmr.repository.StoreRef;
import org.alfresco.service.cmr.search.ResultSet;
import org.alfresco.service.cmr.search.SearchParameters;
import org.alfresco.service.cmr.search.SearchService;
import org.json.simple.JSONObject;
import org.springframework.extensions.webscripts.AbstractWebScript;
import org.springframework.extensions.webscripts.Status;
import org.springframework.extensions.webscripts.WebScriptRequest;
import org.springframework.extensions.webscripts.WebScriptResponse;
import eu.akka.saem.alfresco.constants.SAEMModelConstants;

/**
 * 
 *   Récupération de la réponse à la demande d'élimination d'un noderef
 * 
 * @Class         : GetEliminationResponse.java
 * @Package       : eu.akka.saem.alfresco.webscript
 * @Date          : $Date: 25 févr. 2014 $:
 * @author        : $Author: THOMAS.POGNANT $
 * @version       : $Revision:  $:
 * @Id            : $Id: GetEliminationResponse.java $
 *
 */
public class GetEliminationResponse extends AbstractWebScript {

	private NodeService nodeService;
	private SearchService searchService;

	@SuppressWarnings("unchecked")
	@Override
	public void execute(WebScriptRequest req, WebScriptResponse res) throws IOException {
		JSONObject result = new JSONObject();

		SearchParameters sp = new SearchParameters();

		String archiveIdentifier = req.getParameter("archiveIdentifier");
		NodeRef archive;
		
		//get noderef from archiveid
		sp.addStore(StoreRef.STORE_REF_WORKSPACE_SPACESSTORE);
		sp.setLanguage(SearchService.LANGUAGE_LUCENE);
		sp.setQuery("@saem\\:seda_elimination_acknowledgment:\"" + archiveIdentifier + "\"");

		ResultSet queryResults = searchService.query(sp);

		if (queryResults != null && queryResults.getNodeRef(0) != null)
			archive = queryResults.getNodeRef(0);
			
		else {
			sp.setQuery("@saem\\:seda_elimination_acknowledgment:\"" + archiveIdentifier + "\"");
			queryResults = searchService.query(sp);

			if (queryResults != null && queryResults.getNodeRef(0) != null)
				archive = queryResults.getNodeRef(0);
			else return;
		}

		if (nodeService.hasAspect(archive, SAEMModelConstants.ASPECT_ELIMINATION_STATE) && ((String) nodeService.getProperty(archive, SAEMModelConstants.PROP_STATE)).equalsIgnoreCase("accept")) {
			result.put("reponse", "OK");
			res.setStatus(Status.STATUS_OK);
			res.getWriter().write(JSONObject.toJSONString(result));
			return;
		}
		else if (nodeService.hasAspect(archive, SAEMModelConstants.ASPECT_ELIMINATION_STATE) && ((String) nodeService.getProperty(archive, SAEMModelConstants.PROP_STATE)).equalsIgnoreCase("reject")) {
			result.put("reponse", "NOK");
			res.setStatus(Status.STATUS_BAD_GATEWAY);
			res.getWriter().write(JSONObject.toJSONString(result));
			return;
		}
		else if (!nodeService.hasAspect(archive, SAEMModelConstants.ASPECT_ELIMINATION_STATE)) {
			result.put("reponse", "NOK");
			res.setStatus(Status.STATUS_FORBIDDEN);
			res.getWriter().write(JSONObject.toJSONString(result));
			return;
		}
	}

	public void setNodeService(NodeService nodeService) {
		this.nodeService = nodeService;
	}

	public void setSearchService(SearchService searchService) {
		this.searchService = searchService;
	}
}
