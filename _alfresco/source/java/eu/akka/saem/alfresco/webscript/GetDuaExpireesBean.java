/** SAEM-80 **/
package eu.akka.saem.alfresco.webscript;

import java.io.IOException;
import java.net.URL;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.xml.rpc.ServiceException;

import org.alfresco.model.ContentModel;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.cmr.search.ResultSet;
import org.alfresco.service.cmr.search.ResultSetRow;
import org.alfresco.service.cmr.search.SearchParameters;
import org.alfresco.service.cmr.search.SearchService;
import org.alfresco.web.bean.repository.Repository;
import org.apache.axis.utils.JavaUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.extensions.webscripts.AbstractWebScript;
import org.springframework.extensions.webscripts.WebScriptRequest;
import org.springframework.extensions.webscripts.WebScriptResponse;

import eu.akka.saem.alfresco.connector.asalae.ws.ServerServiceLocator;
import eu.akka.saem.alfresco.connector.asalae.ws.WebServicePortType;
import eu.akka.saem.alfresco.constants.SAEMPropertiesConstants;
import eu.akka.saem.alfresco.helper.PropertyReader;

/**
 * 
 *   Webscript de récupération des archives dont la DUA est expirée
 * 
 * @Class         : GetDuaExpireesBean.java
 * @Package       : eu.akka.saem.alfresco.webscript
 * @Date          : $Date: 25 févr. 2014 $:
 * @author        : $Author: THOMAS.POGNANT $
 * @version       : $Revision:  $:
 * @Id            : $Id: GetDuaExpireesBean.java $
 *
 */
public class GetDuaExpireesBean extends AbstractWebScript {

	private static final Log LOG = LogFactory.getLog(GetDuaExpireesBean.class);
	private PropertyReader propertyReader;
	private SearchService searchService;
	private NodeService nodeService;

	@SuppressWarnings("unchecked")
	@Override
	public void execute(WebScriptRequest req, WebScriptResponse res) throws IOException {

		try {

			Format formatter = new SimpleDateFormat("yyyy-MM-dd");
			ServerServiceLocator ssl = new ServerServiceLocator();
			String result = "";
						
			WebServicePortType wspt = ssl.getWebServicePort(new URL(propertyReader.getProperty(SAEMPropertiesConstants.ASALAE_INT_URL) + "/webservices/service"));

			String asalaeLogin = propertyReader.getProperty(SAEMPropertiesConstants.ASALAE_INT_LOGIN); 
			String asalaePassword = propertyReader.getProperty(SAEMPropertiesConstants.ASALAE_INT_PASSWORD);
			
			Object obj = wspt.wsGetDuaExpirees(asalaeLogin, asalaePassword);

			HashMap<String, String>[] hashtab = (HashMap<String, String>[]) JavaUtils.convert(obj, HashMap.class);
			List<HashMap<String, String>> values = new ArrayList<>();
			for(HashMap<String, String> val : hashtab){
				if (val.size() != 0)
					values.add(val);
			}
			
			
			for (HashMap<String, String> value : values) {

				SearchParameters sp = new SearchParameters();
				sp.addStore(Repository.getStoreRef());
				sp.setLanguage(SearchService.LANGUAGE_LUCENE);
				ResultSet queryResults;
				
				if(!value.get("id").contains("_")){
					sp.setQuery("@saem\\:seda_archive_ArchivalAgencyArchiveIdentifier:\""
							+ StringEscapeUtils.escapeHtml(value.get("id")) + "\"");

					queryResults = searchService.query(sp);
				}else{
					value.clear();
					continue;
					/*sp.setQuery("@saem\\:seda_sous_objet_archive_ArchivalAgencyObjectIdentifier:\""
							+ StringEscapeUtils.escapeHtml(value.get("id")) + "\"");

					queryResults = searchService.query(sp);*/
				}
				
				if (queryResults.length() == 0){
					value.clear();	
				}

				for (ResultSetRow row : queryResults) {					
					NodeRef currentNodeRef = row.getNodeRef();
					
					value.put("name_archive", (String) nodeService.getProperty(currentNodeRef, ContentModel.PROP_NAME));
					value.put("creator", (String) nodeService.getProperty(currentNodeRef, ContentModel.PROP_CREATOR));
					value.put("created", formatter.format(nodeService.getProperty(currentNodeRef, ContentModel.PROP_CREATED)));
					value.put("description", (String) nodeService.getProperty(currentNodeRef, ContentModel.PROP_DESCRIPTION));
					value.put("nodeRef", currentNodeRef.toString());
					value.put("type_archive", value.get("id").contains("_") ? "Objet d'archive" : "Archive");
				}
				
				queryResults.close();
				
			}
			
			if (req.getParameter("sort") != null){
				
				switch(req.getParameterValues("sort")[0]){
					case "nameasc":
						result = buildJson(sortName(values, true, true));
						break;
					case "namedesc":
						result = buildJson(sortName(values, false, true));
						break;
					case "dateasc":
						result = buildJson(sortName(values, true, false));
						break;
					case "datedesc":
						result = buildJson(sortName(values, false, false));
						break;
					default:
						result = buildJson(sortName(values, true, true));
						break;
				}
			}
			else{
				result = buildJson(values);
			}

			res.setContentEncoding("UTF-8");
			res.setContentType("application/json");
			res.getWriter().write(result);
		} catch (ServiceException e) {
			LOG.error("Une erreur s'est produite lors de la récupération des duaExpirées ...    ", e);
		}

	}

	@SuppressWarnings("unchecked")
	private String buildJson(List<HashMap<String, String>> values) {
		JSONObject result = new JSONObject();
		JSONArray items = new JSONArray();

		for (HashMap<String, String> value : values) {
			if (value.size() != 0){
				JSONObject jsonobject = new JSONObject();
				jsonobject.put("name", StringEscapeUtils.escapeHtml(value.get("id")));
				jsonobject.put("end_date", StringEscapeUtils.escapeHtml(value.get("end_date")));
				jsonobject.put("name_archive", StringEscapeUtils.escapeHtml(value.get("name_archive")));
				jsonobject.put("creator", StringEscapeUtils.escapeHtml(value.get("creator")));
				jsonobject.put("created", StringEscapeUtils.escapeHtml(value.get("created")));
				jsonobject.put("description", StringEscapeUtils.escapeHtml(value.get("description")));
				jsonobject.put("nodeRef", StringEscapeUtils.escapeHtml(value.get("nodeRef")));
				jsonobject.put("type_archive", StringEscapeUtils.escapeHtml(value.get("type_archive")));
				items.add(jsonobject);
			}
		}
		
		result.put("items", items);
		
		return result.toJSONString();

	}
	
	@SuppressWarnings("unused")
	private List<HashMap<String, String>> sortName (List<HashMap<String, String>> values, boolean ascendant, boolean isSortName){
		
		List<String> nomArchives = new ArrayList<>();
		List<HashMap<String, String>> sortedValues = new ArrayList<>();
		
		for (HashMap<String, String> value : values) {
			if(isSortName){
				if(value.get("name_archive") != null)
					nomArchives.add(value.get("name_archive"));
			}else{
				if(value.get("name_archive") != null && value.get("end_date") != null){
					nomArchives.add(value.get("end_date") + "_" + value.get("name_archive"));
				}
			}
		}
		
		if(ascendant)
			Collections.sort(nomArchives);
		else
			Collections.sort(nomArchives, Collections.reverseOrder());
		
		for (String nom : nomArchives){
			for(HashMap<String, String> value : values){
				if (isSortName){
					if (nom.equals(value.get("name_archive"))){
						sortedValues.add(value);
					}
				}
				else{
					if (nom.split("_")[0].equals(value.get("end_date")) && nom.split("_")[1].equals(value.get("name_archive"))){
						sortedValues.add(value);
					}
				}
			}
		}
		
		return sortedValues;
		
	}

	public void setSearchService(SearchService searchService) {
		this.searchService = searchService;
	}
	
	public void setPropertyReader(PropertyReader propertyReader) {
		this.propertyReader = propertyReader;
	}
	
	public void setNodeService(NodeService nodeService) {
		this.nodeService = nodeService;
	}

}
