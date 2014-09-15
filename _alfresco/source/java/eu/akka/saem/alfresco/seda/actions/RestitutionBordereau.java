package eu.akka.saem.alfresco.seda.actions;

import java.io.File;

import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.cmr.model.FileFolderService;
import org.alfresco.service.cmr.repository.ContentService;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.cmr.search.SearchService;
import org.alfresco.service.cmr.site.SiteService;
import org.apache.log4j.Logger;

import eu.akka.saem.alfresco.seda.v02.ArchiveRestitutionRequestReplyType;
import eu.akka.saem.alfresco.seda.v02.ArchiveRestitutionRequestType;
import eu.akka.saem.alfresco.seda.v02.ArchiveRestitutionType;
import eu.akka.saem.alfresco.utils.ProfilSEDAUtils;

/**
 * 
 *   Classe d'analyse de bordereau pour le workflow de restitution
 * 
 * @Class         : RestitutionBordereau.java
 * @Package       : eu.akka.saem.alfresco.seda.actions
 * @Date          : $Date: 25 févr. 2014 $:
 * @author        : $Author: THOMAS.POGNANT $
 * @version       : $Revision:  $:
 * @Id            : $Id: RestitutionBordereau.java $
 *
 */
public class RestitutionBordereau extends Bordereau {
	
	private static Logger LOG = Logger.getLogger(RestitutionBordereau.class);
	
	private File file = null;
	
	public RestitutionBordereau(ContentService contentService,
			NodeService nodeService, SiteService siteService,
			SearchService searchService, ServiceRegistry serviceRegistry,
			ProfilSEDAUtils profilSEDAUtils, FileFolderService fileFolderService,File file) {
		super(contentService, nodeService, siteService, searchService, serviceRegistry,
				profilSEDAUtils, fileFolderService);
		this.file = file;
	}

	public static boolean canReadBordereau(Object SEDAObject){
		if (SEDAObject.getClass().equals(ArchiveRestitutionType.class))
			return true;
		else if (SEDAObject.getClass().equals(ArchiveRestitutionRequestType.class))
			return true;
		else if (SEDAObject.getClass().equals(ArchiveRestitutionRequestReplyType.class))
			return true;
		else		
			return false;
	}

	@Override
	protected void doActionBordereau(Object SEDAObject) throws Exception {
		
	}

	



}
