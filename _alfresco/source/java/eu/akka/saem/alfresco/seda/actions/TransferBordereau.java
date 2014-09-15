package eu.akka.saem.alfresco.seda.actions;


import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.cmr.model.FileFolderService;
import org.alfresco.service.cmr.repository.ContentService;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.cmr.search.SearchService;
import org.alfresco.service.cmr.site.SiteService;
import org.apache.log4j.Logger;

import eu.akka.saem.alfresco.seda.v02.ArchiveTransferAcceptanceType;
import eu.akka.saem.alfresco.seda.v02.ArchiveTransferReplyType;
import eu.akka.saem.alfresco.utils.ProfilSEDAUtils;

/**
 * 
 *   Classe d'analyse de bordereau pour le worklow de transfere
 * 
 * @Class         : TransferBordereau.java
 * @Package       : eu.akka.saem.alfresco.seda.actions
 * @Date          : $Date: 25 févr. 2014 $:
 * @author        : $Author: THOMAS.POGNANT $
 * @version       : $Revision:  $:
 * @Id            : $Id: TransferBordereau.java $
 *
 */
public class TransferBordereau extends Bordereau {
	
	private static Logger LOG = Logger.getLogger(TransferBordereau.class);

	public TransferBordereau(ContentService contentService,
			NodeService nodeService, SiteService siteService,
			SearchService searchService, ServiceRegistry serviceRegistry,
			ProfilSEDAUtils profilSEDAUtils, FileFolderService fileFolderService) {
		super(contentService, nodeService, siteService, searchService, serviceRegistry,
				profilSEDAUtils, fileFolderService);
	}
	
	public static boolean canReadBordereau(Object SEDAObject){
		if (SEDAObject.getClass().equals(ArchiveTransferReplyType.class))
			return true;
		else if (SEDAObject.getClass().equals(ArchiveTransferAcceptanceType.class))
			return true;
		else		
			return false;
	}

	@Override
	protected void doActionBordereau(Object SEDAObject) throws Exception {
		
	}

	

}
