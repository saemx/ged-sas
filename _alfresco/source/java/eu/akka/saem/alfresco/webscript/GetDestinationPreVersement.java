package eu.akka.saem.alfresco.webscript;

import java.io.IOException;

import org.alfresco.model.ContentModel;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.cmr.search.ResultSet;
import org.alfresco.service.cmr.search.SearchParameters;
import org.alfresco.service.cmr.search.SearchService;
import org.alfresco.web.bean.repository.Repository;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.simple.JSONObject;
import org.springframework.extensions.webscripts.AbstractWebScript;
import org.springframework.extensions.webscripts.WebScriptRequest;
import org.springframework.extensions.webscripts.WebScriptResponse;

/**
 * 
 *   Webscript permettant de récupérer le noderef de destination du versement
 * 
 * @Class         : GetDestinationPreVersement.java
 * @Package       : eu.akka.saem.alfresco.webscript
 * @Date          : $Date: 25 févr. 2014 $:
 * @author        : $Author: THOMAS.POGNANT $
 * @version       : $Revision:  $:
 * @Id            : $Id: GetDestinationPreVersement.java $
 *
 */
public class GetDestinationPreVersement extends AbstractWebScript {

	private static final Log LOG = LogFactory.getLog(GetDestinationPreVersement.class);
	private SearchService searchService;
	private NodeService nodeService;

	@SuppressWarnings("unchecked")
	@Override
	public void execute(WebScriptRequest req, WebScriptResponse res) throws IOException {

		String destination = "";
		JSONObject jsonresult = new JSONObject();

		if (!StringUtils.isBlank(req.getParameter("profil"))) {
			
			NodeRef profil = new NodeRef(req.getParameter("profil"));
			
			String profilname = (String) nodeService.getProperty(profil, ContentModel.PROP_NAME);
			SearchParameters sp = new SearchParameters();
			sp.addStore(Repository.getStoreRef());
			sp.setLanguage(SearchService.LANGUAGE_LUCENE);
			sp.setQuery("ASPECT:\"saem:profilable\" AND @saem\\:profilName:\""+profilname+"\"");
			ResultSet queryResults = searchService.query(sp);
			
			if (queryResults != null && queryResults.getNodeRefs().size() > 0 && queryResults.getNodeRef(0) != null){
				destination = queryResults.getNodeRef(0).toString();
			}
		}
		
		jsonresult.put("destinationPreVersement", destination);
		
		res.setContentEncoding("UTF-8");
		res.setContentType("application/json");
		res.getWriter().write(jsonresult.toJSONString());
	}

	public void setSearchService(SearchService searchService) {
		this.searchService = searchService;
	}
	
	public void setNodeService(NodeService nodeService) {
		this.nodeService = nodeService;
	}

}
