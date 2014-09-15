package eu.akka.saem.alfresco.constants;

/**
 * 
 *   Interface contenant toute les propriétés
 * 
 * @Class         : SAEMPropertiesConstants.java
 * @Package       : eu.akka.saem.alfresco.constants
 * @Date          : $Date: 25 févr. 2014 $:
 * @author        : $Author: THOMAS.POGNANT $
 * @version       : $Revision:  $:
 * @Id            : $Id: SAEMPropertiesConstants.java $
 *
 */
public interface SAEMPropertiesConstants {

	/**
	 * Propriétés de configuration
	 */
	public static final String PROPERTIES_FILE = "saem.properties";
	public static final String CATEGORY_PROFIL_NODEREF = "category.profil.seda.noderef";

	public static final String FOLDER_SYSTEM_PROFILS_NAME = "profils.system.folder.name";
	public static final String FOLDER_SYSTEM_DATA_DICTIONNARY_NODEREF = "datadictionnary.system.folder.noderef";
	public static final String SITE_SERVICE_VERSANT_SHORTNAME = "service.versant.site.shortname";
	public static final String ASALAE_INT_URL = "asalae.int.url";
	public static final String ASALAE_INT_LOGIN = "asalae.int.login";
	public static final String ASALAE_INT_PASSWORD = "asalae.int.password";
	public static final String ASALAE_DEF_URL = "asalae.def.url";
	public static final String ASALAE_DEF_LOGIN = "asalae.def.login";
	public static final String ASALAE_DEF_PASSWORD = "asalae.def.password";
	
	public static final String SIZE_BIG_FILE = "size.big.file";
	public static final String HOST_TRANSFERT_BIG_FILE = "transfert.big.file.host";
	public static final String PORT_TRANSFERT_BIG_FILE = "transfert.big.file.port";
	public static final String NBBYTES_TRANSFERT_BIG_FILE = "transfert.big.file.nbbytes";
    
	/**
	 * Propriété d'internationalisation
	 */
	public static final String PROP_SEDA_SERVICE_VERSANT = "label.service.versant";
	public static final String PROP_SEDA_TRANSFER_IDENTIFIER = "label.transfert.identifier";
	public static final String PROP_SEDA_TRANSFER_DATE = "label.transfert.date";
	public static final String PROP_SEDA_DATE_DE_DEBUT = "label.unite.documentaire.date.debut";
	public static final String PROP_SEDA_DATE_DE_FIN = "label.unite.documentaire.date.fin";
	
	public static final String PROP_SEDA_DESCRIPTION_LEVEL = "label.niveau.description";
	public static final String PROP_SEDA_FINAL_SORT = "label.sort.final";
	public static final String PROP_SEDA_FINAL_SORT_RULE = "label.regle.sort.final";
	public static final String PROP_SEDA_VERSEMENT_COMMENT = "label.commentaire.versement";
	public static final String PROP_SEDA_KEYWORDS = "label.mots.cles";
	
	public static final String PROP_SEDA_ORIGIN_IDENTIFIER = "label.identifiant.origine";
	public static final String PROP_SEDA_DESCRIPTION = "label.description";
	public static final String PROP_SEDA_DOCUMENT_TYPE = "label.type.document";
	public static final String PROP_SEDA_DOCUMENT_HASH = "label.document.md5";
}
