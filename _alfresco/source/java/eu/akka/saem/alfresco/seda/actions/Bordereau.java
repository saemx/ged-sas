package eu.akka.saem.alfresco.seda.actions;

import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.cmr.model.FileFolderService;
import org.alfresco.service.cmr.repository.ContentService;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.cmr.search.SearchService;
import org.alfresco.service.cmr.site.SiteService;

import eu.akka.saem.alfresco.utils.ProfilSEDAUtils;

/**
 * 
 *   Classe père d'analyse des bordereau de workflow
 * 
 * @Class         : Bordereau.java
 * @Package       : eu.akka.saem.alfresco.seda.actions
 * @Date          : $Date: 25 févr. 2014 $:
 * @author        : $Author: THOMAS.POGNANT $
 * @version       : $Revision:  $:
 * @Id            : $Id: Bordereau.java $
 *
 */
public abstract class Bordereau {

	protected ContentService contentService;
	protected NodeService nodeService;
	protected SearchService searchService;
	protected SiteService siteService;
	protected ServiceRegistry serviceRegistry;
	protected ProfilSEDAUtils profilSEDAUtils;
	protected FileFolderService fileFolderService;
	protected NodeRef destinationNodeRef;
	protected String filename;
	protected String state;
	
	public Bordereau(ContentService contentService,NodeService nodeService,SiteService siteService,SearchService searchService,ServiceRegistry serviceRegistry,ProfilSEDAUtils profilSEDAUtils,FileFolderService fileFolderService){
		setContentService(contentService);
		setNodeService(nodeService);
		setSearchService(searchService);
		setServiceRegistry(serviceRegistry);
		setSiteService(siteService);
		setProfilSEDAUtils(profilSEDAUtils);
		setFileFolderService(fileFolderService);
	}

	public static boolean canReadBordereau(Object SEDAObject){
		return false;
	}

	public void doAction(Object SEDAObject) throws Exception {

	}
	
	protected abstract void doActionBordereau(Object sEDAObject) throws Exception;

	public void setContentService(ContentService contentService) {
		this.contentService = contentService;
	}

	public void setNodeService(NodeService nodeService) {
		this.nodeService = nodeService;
	}

	public void setSearchService(SearchService searchService) {
		this.searchService = searchService;
	}

	public void setSiteService(SiteService siteService) {
		this.siteService = siteService;
	}

	public void setServiceRegistry(ServiceRegistry serviceRegistry) {
		this.serviceRegistry = serviceRegistry;
	}

	public void setProfilSEDAUtils(ProfilSEDAUtils profilSEDAUtils) {
		this.profilSEDAUtils = profilSEDAUtils;
	}

	public void setFileFolderService(FileFolderService fileFolderService) {
		this.fileFolderService = fileFolderService;
	}	
	
}
