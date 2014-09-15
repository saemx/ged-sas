package eu.akka.saem.alfresco.constants;

import org.alfresco.service.namespace.NamespaceService;
import org.alfresco.service.namespace.QName;

/**
 * 
 *   Classe contenant les constantes des BEANS à initialiser
 * 
 * @Class         : SAEMBeanContants.java
 * @Package       : eu.akka.saem.alfresco.constants
 * @Date          : $Date: 25 févr. 2014 $:
 * @author        : $Author: THOMAS.POGNANT $
 * @version       : $Revision:  $:
 * @Id            : $Id: SAEMBeanContants.java $
 *
 */
public class SAEMBeanContants {
	
	public static final QName PROPERTY_READER = QName.createQName(NamespaceService.ALFRESCO_URI, "propertyReader");
	public static final QName PROFIL_SEDA_UTILS = QName.createQName(NamespaceService.ALFRESCO_URI, "profilSEDAUtils");
	public static final QName SERVICE_VERSANT_HELPER = QName.createQName(NamespaceService.ALFRESCO_URI, "ServiceVersantHelper");
	public static final QName ASALAE_UTILS = QName.createQName(NamespaceService.ALFRESCO_URI, "asalaeUtils");
	public static final QName ZIP_UTILS = QName.createQName(NamespaceService.ALFRESCO_URI, "zipUtils");

}
