package eu.akka.saem.alfresco.webscript;

import java.io.IOException;

import org.alfresco.service.cmr.repository.ContentService;
import org.alfresco.service.cmr.repository.NodeService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.extensions.webscripts.AbstractWebScript;
import org.springframework.extensions.webscripts.WebScriptRequest;
import org.springframework.extensions.webscripts.WebScriptResponse;

import eu.akka.saem.alfresco.seda.form.HTMLForm;
import eu.akka.saem.alfresco.seda.form.HTMLForm.AddListResult;


/**
 * Classe utilisé par le formulaire pour
 *   la soumission
 * 
 * @Class         : ArchiveManagementFormPost.java
 * @Package       : eu.akka.saem.alfresco.webscript
 * @Date          : $Date: 25 févr. 2014 $:
 * @author        : $Author: THOMAS.POGNANT $
 * @version       : $Revision:  $:
 * @Id            : $Id: ArchiveManagementFormPost.java $
 *
 */
public class ArchiveManagementFormPost extends AbstractWebScript {

	private static final Log LOG = LogFactory.getLog(ArchiveManagementFormPost.class);
	private ContentService contentService;
	private NodeService nodeService;

	@Override
	public void execute(WebScriptRequest req, WebScriptResponse res)
			throws IOException {
		String result = "";
		JSONObject jsonobject = new JSONObject();
		
		if(req.getParameter("id") != null){
			String id = req.getParameter("id");
			String formId = id.split("_")[0];
			
			if(req.getParameter("action") != null && req.getParameter("action").equals("edit")){
				HTMLForm htmlform = HTMLForm.getHTMLForm(formId);
				htmlform.editField(id, req.getParameter("value"));
				jsonobject.put("result", "OK");
			}
			
			if(req.getParameter("action") != null && req.getParameter("action").equals("removeItem")){
				HTMLForm htmlform = HTMLForm.getHTMLForm(formId);
				htmlform.removeItem(id);
				jsonobject.put("result", "OK");
			}
			
			if(req.getParameter("action") != null && req.getParameter("action").equals("additem")){
				HTMLForm htmlform = HTMLForm.getHTMLForm(formId);
				AddListResult alr = htmlform.addListItem(id);
				
				jsonobject.put("htmldata", alr.htmllistext.replaceAll("\"", "\\\""));
				
				JSONArray array = new JSONArray();
				for(String string: alr.globalhtml){
					array.add(string.replaceAll("\"", "\\\""));
				}				
				jsonobject.put("globalhtml", array);
				jsonobject.put("canAlwaysAdd", alr.canAlwaysAdd);
			}	
		}

		res.setContentEncoding("UTF-8");
		res.getWriter().write(jsonobject.toJSONString());
	}

	public void setContentService(ContentService contentService) {
		this.contentService = contentService;
	}

	public void setNodeService(NodeService nodeService) {
		this.nodeService = nodeService;
	}

}
