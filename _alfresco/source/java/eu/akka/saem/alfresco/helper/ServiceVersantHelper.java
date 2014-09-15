package eu.akka.saem.alfresco.helper;

import org.alfresco.repo.processor.BaseProcessorExtension;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.security.AccessStatus;
import org.alfresco.service.cmr.security.PermissionService;
import org.alfresco.service.cmr.site.SiteInfo;
import org.alfresco.service.cmr.site.SiteService;

import eu.akka.saem.alfresco.constants.SAEMPropertiesConstants;

/**
 * 
 *   Classe utilitaire contenant des méthodes permettant
 *   de récupérer certaine donnée sur l'environnement Alfresco
 *   mis en place
 * 
 * @Class         : ServiceVersantHelper.java
 * @Package       : eu.akka.saem.alfresco.helper
 * @Date          : $Date: 25 févr. 2014 $:
 * @author        : $Author: THOMAS.POGNANT $
 * @version       : $Revision:  $:
 * @Id            : $Id: ServiceVersantHelper.java $
 *
 */
public class ServiceVersantHelper extends BaseProcessorExtension {

	private SiteService siteService;
	private PermissionService permissionService;
	private PropertyReader propertyReader;

	public boolean isSiteServiceVersant(String siteName) {
		if (siteName == null | siteName.equals("")) {
			return false;
		}
		String siteServiceVersantShortName = propertyReader
				.getProperty(SAEMPropertiesConstants.SITE_SERVICE_VERSANT_SHORTNAME);
		return siteName.equals(siteServiceVersantShortName);
	}

	public String getDocumentLibraryNodeRef() {
		String siteServiceVersantShortName = propertyReader
				.getProperty(SAEMPropertiesConstants.SITE_SERVICE_VERSANT_SHORTNAME);
		SiteInfo siteInfo = siteService.getSite(siteServiceVersantShortName);

		// récupération du dossier "documentLibrary"
		final NodeRef docLib = siteService.getContainer(siteInfo.getShortName(), "documentlibrary");

		return docLib.toString();
	}

	public boolean hasAddChildrenPermission() {
		String siteServiceVersantShortName = propertyReader
				.getProperty(SAEMPropertiesConstants.SITE_SERVICE_VERSANT_SHORTNAME);
		SiteInfo siteInfo = siteService.getSite(siteServiceVersantShortName);

		final NodeRef docLib = siteService.getContainer(siteInfo.getShortName(), "documentlibrary");
		AccessStatus access = permissionService.hasPermission(docLib, PermissionService.ADD_CHILDREN);
		return (access != null && access.equals(AccessStatus.ALLOWED));
	}

	public String getServiceVersantSiteName() {
		return propertyReader.getProperty(SAEMPropertiesConstants.SITE_SERVICE_VERSANT_SHORTNAME);
	}

	public void setSiteService(SiteService siteService) {
		this.siteService = siteService;
	}

	public void setPermissionService(PermissionService permissionService) {
		this.permissionService = permissionService;
	}

	public void setPropertyReader(PropertyReader propertyReader) {
		this.propertyReader = propertyReader;
	}
}