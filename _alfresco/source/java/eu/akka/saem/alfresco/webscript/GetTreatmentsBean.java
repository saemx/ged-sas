package eu.akka.saem.alfresco.webscript;

import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.alfresco.model.ContentModel;
import org.alfresco.query.PagingRequest;
import org.alfresco.query.PagingResults;
import org.alfresco.repo.security.authentication.AuthenticationUtil;
import org.alfresco.repo.security.authentication.AuthenticationUtil.RunAsWork;
import org.alfresco.service.cmr.repository.ChildAssociationRef;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.cmr.search.ResultSet;
import org.alfresco.service.cmr.search.ResultSetRow;
import org.alfresco.service.cmr.search.SearchParameters;
import org.alfresco.service.cmr.search.SearchService;
import org.alfresco.service.cmr.security.AuthenticationService;
import org.alfresco.service.cmr.security.AuthorityService;
import org.alfresco.service.cmr.security.PersonService;
import org.alfresco.service.cmr.security.PersonService.PersonInfo;
import org.alfresco.web.bean.repository.Repository;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.extensions.webscripts.AbstractWebScript;
import org.springframework.extensions.webscripts.WebScriptRequest;
import org.springframework.extensions.webscripts.WebScriptResponse;

import eu.akka.saem.alfresco.constants.SAEMArchiveStates;
import eu.akka.saem.alfresco.constants.SAEMModelConstants;
import eu.akka.saem.alfresco.helper.PropertyReader;

/**
 * 
 *   Récupération des nodeRef en cours d'utilisation dans un workflow
 * 
 * @Class         : GetTreatmentsBean.java
 * @Package       : eu.akka.saem.alfresco.webscript
 * @Date          : $Date: 25 févr. 2014 $:
 * @author        : $Author: THOMAS.POGNANT $
 * @version       : $Revision:  $:
 * @Id            : $Id: GetTreatmentsBean.java $
 *
 */
public class GetTreatmentsBean extends AbstractWebScript {

	private static final Log LOG = LogFactory.getLog(GetTreatmentsBean.class);
	private PropertyReader propertyReader;
	private SearchService searchService;
	private NodeService nodeService;
	private PersonService personService;
	private AuthorityService authorityService;
	private AuthenticationService authenticationService;

	@Override
	public void execute(WebScriptRequest req, WebScriptResponse res) throws IOException {

		String result = "";
		List<String> finalUsers = new ArrayList<>();
		List<HashMap<String, String>> values = new ArrayList<>();
		Format dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		
		//Récupération des groupes de l'utilisateur courant
		Set<String> groupsOfCurrentUser = 
			AuthenticationUtil.runAsSystem(new RunAsWork<Set<String>>() {
				@Override
				public Set<String> doWork() throws Exception {
					Set<String> groupsOfCurrentUser = authorityService.getAuthorities();
					return groupsOfCurrentUser;
				}
			});

		if(groupsOfCurrentUser == null || groupsOfCurrentUser.size() == 0){
			LOG.debug("L'utilisateur courant n'appartient à aucun groupe");
		}
		
		//On récupére tous les utilisateurs
		PagingResults<PersonInfo> listUsers = personService.getPeople("", null, null, new PagingRequest(10000));
		List<PersonInfo> users = listUsers.getPage();
		
		
		//On cherche une correspondance entre les groupes
		//de chaque utilisateur avec ceux de l'utilisateur courant
		for(PersonInfo user : users){
			
			final String username = user.getUserName();
			
			Set<String> groups = 
					AuthenticationUtil.runAsSystem(new RunAsWork<Set<String>>() {
						@Override
						public Set<String> doWork() throws Exception {
							Set<String> groupsOfCurrentUser = authorityService.getAuthoritiesForUser(username);
							return groupsOfCurrentUser;
						}
					});
			
			//On ajoute l'utilisateur qui a une correspondance
			//avec un groupe dans la liste finale
			if(groups != null && groups.size() > 0){
				for(String group : groups){
				
					if(groupsOfCurrentUser.contains(group)){
						finalUsers.add(username);
						break;
					}
				}
			}
		}
		
		//on ajoute l'utilisateur courant à la liste finale
		
			String currentUserName = AuthenticationUtil.runAsSystem(new RunAsWork<String>() {
				@Override
				public String doWork() throws Exception {
					return authenticationService.getCurrentUserName();
				}
			});
			
			if(!finalUsers.contains(currentUserName)){
				finalUsers.add(currentUserName);
			}
		
		//on recupere les informations de chaque archive
		for(String user: finalUsers){
			
			SearchParameters sp = new SearchParameters();
			sp.addStore(Repository.getStoreRef());
			sp.setLanguage(SearchService.LANGUAGE_LUCENE);
			ResultSet queryResults;
			
			sp.setQuery("PATH:\"/app:company_home/st:sites/cm:" + propertyReader.getProperty("service.versant.site.shortname") + "//*\" AND @cm\\:creator:\"" + user + "\"" + " AND ASPECT:\"saem:archivable\"");
			
			queryResults = searchService.query(sp);
			
			for(ResultSetRow row : queryResults){
				
				NodeRef currentNodeRef = row.getNodeRef();
				//Récupération du parent pour le profil d'archive
				
				
				NodeRef parent = null;
				
				for(ChildAssociationRef car: nodeService.getParentAssocs(currentNodeRef)){
					
					if (car.getTypeQName().getLocalName().equals("contains")){
						parent = car.getParentRef();
						break;
					}
				}
				
				HashMap<String, String> value = new HashMap<>();
				String archiveState = (String) nodeService.getProperty(currentNodeRef, SAEMModelConstants.PROP_ARCHIVE_STATE_PROP);
				
				if(archiveState == null){
					continue;
				}
				
				value.put("name", (String) nodeService.getProperty(currentNodeRef, ContentModel.PROP_NAME));
				value.put("statut", archiveState);
				value.put("flux", (String) nodeService.getProperty(parent, SAEMModelConstants.PROP_PROFIL_NAME));
				value.put("author", (String) nodeService.getProperty(currentNodeRef, ContentModel.PROP_CREATOR));
				value.put("date", dateFormatter.format(nodeService.getProperty(currentNodeRef, ContentModel.PROP_CREATED)));
				value.put("nodeRef", currentNodeRef.toString());
				value.put("actions", getActionsPossibles(archiveState));
				
				values.add(value);
			}
			
			queryResults.close();
			
		}		

		result = buildJson(values);

		res.setContentEncoding("UTF-8");
		res.setContentType("application/json");
		res.getWriter().write(result);
	}

	@SuppressWarnings("unchecked")
	private String buildJson(List<HashMap<String, String>> values) {
		JSONObject result = new JSONObject();
		JSONArray items = new JSONArray();

		for (HashMap<String, String> value : values) {
			if (value.size() != 0){
				JSONObject jsonobject = new JSONObject();
				jsonobject.put("name", StringEscapeUtils.escapeHtml(value.get("name")));
				jsonobject.put("statut", StringEscapeUtils.escapeHtml(value.get("statut")));
				jsonobject.put("flux", StringEscapeUtils.escapeHtml(value.get("flux")));
				jsonobject.put("author", StringEscapeUtils.escapeHtml(value.get("author")));
				jsonobject.put("date", StringEscapeUtils.escapeHtml(value.get("date")));
				jsonobject.put("nodeRef", StringEscapeUtils.escapeHtml(value.get("nodeRef")));
				jsonobject.put("actions", StringEscapeUtils.escapeHtml(value.get("actions")));
				items.add(jsonobject);
			}
		}
		
		result.put("items", items);
		
		return result.toJSONString();
	}
	
	private String getActionsPossibles(String etat){
		
		String actionsPossibles = "";
		
		switch(etat){
			case SAEMArchiveStates.RESTITUER:
				actionsPossibles = SAEMArchiveStates.VERSER + ";";
				break;
			case SAEMArchiveStates.NORMAL:
				actionsPossibles = SAEMArchiveStates.VERSER + ";";
				break;
			case SAEMArchiveStates.INTERMEDIAIRE:
				actionsPossibles = SAEMArchiveStates.ELIMINER + ";" + SAEMArchiveStates.RESTITUER;
				break;
			case SAEMArchiveStates.DEFINITIF:
				actionsPossibles = SAEMArchiveStates.ELIMINER + ";" + SAEMArchiveStates.RESTITUER;
				break;
			default:
				actionsPossibles = "Aucune action possible" + ";";
				break;
		}
		
		return actionsPossibles;
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

	public void setAuthenticationService(AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}
	
	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}
	
	public void setAuthorityService(AuthorityService authorityService) {
		this.authorityService = authorityService;
	}

}
