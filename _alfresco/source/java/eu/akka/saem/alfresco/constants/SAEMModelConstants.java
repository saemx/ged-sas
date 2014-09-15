package eu.akka.saem.alfresco.constants;

import org.alfresco.service.namespace.QName;

/**
 * 
 *   Interface contenant tous les aspects alfresco
 * 
 * @Class         : SAEMModelConstants.java
 * @Package       : eu.akka.saem.alfresco.constants
 * @Date          : $Date: 25 févr. 2014 $:
 * @author        : $Author: THOMAS.POGNANT $
 * @version       : $Revision:  $:
 * @Id            : $Id: SAEMModelConstants.java $
 *
 */
public interface SAEMModelConstants {

	public static final String SAEM_MODEL_1_0_URI = "http://www.akka.com/model/content/1.0";
	public static final QName MODEL_SAEM = QName.createQName(SAEM_MODEL_1_0_URI, "contentmodel");

	/**
	 * ASPECTS
	 */
	public static final QName ASPECT_PROFILABLE = QName.createQName(SAEM_MODEL_1_0_URI, "profilable");
	public static final QName ASPECT_PROFIL = QName.createQName(SAEM_MODEL_1_0_URI, "profil");
	public static final QName ASPECT_ARCHIVABLE = QName.createQName(SAEM_MODEL_1_0_URI, "archivable");
	public static final QName ASPECT_SOUS_OBJET_ARCHIVE = QName.createQName(SAEM_MODEL_1_0_URI, "sous_objet_archive");
	public static final QName ASPECT_DOCUMENT_ARCHIVE = QName.createQName(SAEM_MODEL_1_0_URI, "document_archive");
	public static final QName ASPECT_VERSE = QName.createQName(SAEM_MODEL_1_0_URI, "verse");
	public static final QName ASPECT_NON_VERSE = QName.createQName(SAEM_MODEL_1_0_URI, "not_verse");
	public static final QName ASPECT_ELIMINABLE = QName.createQName(SAEM_MODEL_1_0_URI, "eliminable");
	public static final QName ASPECT_RESTITUABLE = QName.createQName(SAEM_MODEL_1_0_URI, "restituable");
	public static final QName ASPECT_SEARCHABLE_AFTER_VERSEMENT = QName.createQName(SAEM_MODEL_1_0_URI, "searchable_after_versement");
	public static final QName ASPECT_IS_ACCUSE = QName.createQName(SAEM_MODEL_1_0_URI, "is_accuse");
	public static final QName ASPECT_TRANSFERT_EN_COURS = QName.createQName(SAEM_MODEL_1_0_URI, "transfert_en_cours");
	public static final QName ASPECT_ELIMINATION_STATE = QName.createQName(SAEM_MODEL_1_0_URI, "elimination_state");
	public static final QName ASPECT_ARCHIVE_STATE = QName.createQName(SAEM_MODEL_1_0_URI, "archive_state");
	public static final QName ASPECT_PROFIL_NAME = QName.createQName(SAEM_MODEL_1_0_URI, "profil_name");
	
	/**
	 * PROPERTIES
	 */
	public static final QName PROP_PROFIL_NAME = QName.createQName(SAEM_MODEL_1_0_URI, "profilName");
	public static final QName PROP_IS_PROFIL = QName.createQName(SAEM_MODEL_1_0_URI, "isprofil");
	public static final QName PROP_IS_ARCHIVE = QName.createQName(SAEM_MODEL_1_0_URI, "isarchive");
	public static final QName PROP_SEDA_ARCHIVE = QName.createQName(SAEM_MODEL_1_0_URI, "seda_archive");
	public static final QName PROP_SEDA_OBJECT_MODEL= QName.createQName(SAEM_MODEL_1_0_URI, "seda_object_model");
	public static final QName PROP_SEDA_ARCHIVE_XML = QName.createQName(SAEM_MODEL_1_0_URI, "seda_archive_xml");
	public static final QName PROP_SEDA_ARCHIVE_XML_FULL = QName.createQName(SAEM_MODEL_1_0_URI, "seda_archive_xml_full");
	public static final QName PROP_SEDA_ARCHIVE_PROFIL_MODEL = QName.createQName(SAEM_MODEL_1_0_URI, "seda_archive_profil_model");
	public static final QName PROP_SEDA_PROFIL_USE_NODEREF = QName.createQName(SAEM_MODEL_1_0_URI, "seda_archive_profil_use_noderef");
	public static final QName PROP_SEDA_VERSEMENT_ACKNOWLEDGMENT = QName.createQName(SAEM_MODEL_1_0_URI, "seda_versement_acknowledgment");
	public static final QName PROP_SEDA_ELIMINATION_ACKNOWLEDGMENT = QName.createQName(SAEM_MODEL_1_0_URI, "seda_elimination_acknowledgment");
	public static final QName PROP_SEDA_RESTITUTION_ACKNOWLEDGMENT = QName.createQName(SAEM_MODEL_1_0_URI, "seda_restitution_acknowledgment");;
	public static final QName PROP_SEDA_ARCHIVE_ARCHIVAL_AGENCY_ARCHIVE_IDENTIFIER = QName.createQName(SAEM_MODEL_1_0_URI, "seda_archive_ArchivalAgencyArchiveIdentifier");
	public static final QName PROP_SEDA_SOUS_OBJET_ARCHIVE = QName.createQName(SAEM_MODEL_1_0_URI, "seda_sous_objet_archive");
	public static final QName PROP_SEDA_SOUS_OBJET_ARCHIVE_XML = QName.createQName(SAEM_MODEL_1_0_URI, "seda_sous_objet_archive_xml");
	public static final QName PROP_SEDA_SOUS_OBJET_ARCHIVE_ARCHIVAL_AGENCY_OBJECT_IDENTIFIER = QName.createQName(SAEM_MODEL_1_0_URI, "seda_sous_objet_archive_ArchivalAgencyObjectIdentifier");
	public static final QName PROP_SEDA_DOCUMENT = QName.createQName(SAEM_MODEL_1_0_URI, "seda_document");
	public static final QName PROP_SEDA_DOCUMENT_XML = QName.createQName(SAEM_MODEL_1_0_URI, "seda_document_xml");
	public static final QName PROP_SV_CANDIDATE_GROUPS = QName.createQName(SAEM_MODEL_1_0_URI, "serviceVersant");
	public static final QName PROP_SC_CANDIDATE_GROUPS = QName.createQName(SAEM_MODEL_1_0_URI, "controlService");
	public static final QName PROP_IS_HIDDEN = QName.createQName(SAEM_MODEL_1_0_URI, "is_hidden");
	public static final QName PROP_HIDDEN_CONTENT = QName.createQName(SAEM_MODEL_1_0_URI, "hidden_content");
	public static final QName PROP_TYPE_ACCUSE = QName.createQName(SAEM_MODEL_1_0_URI, "type_accuse");
	public static final QName PROP_ELIMINATION_INITIATOR = QName.createQName(SAEM_MODEL_1_0_URI, "elimination_initiator");
	public static final QName PROP_TAILLE_TELECHARGE = QName.createQName(SAEM_MODEL_1_0_URI, "taille_telecharge");
	public static final QName PROP_POURCENTAGE_D_AVANCEMENT = QName.createQName(SAEM_MODEL_1_0_URI, "pourcentage_d_avancement");
	public static final QName PROP_STATE = QName.createQName(SAEM_MODEL_1_0_URI, "state");
	public static final QName PROP_ARCHIVE_STATE_PROP = QName.createQName(SAEM_MODEL_1_0_URI, "archive_state_prop");
	public static final QName PROP_ARCHIVE_PROFIL_NAME = QName.createQName(SAEM_MODEL_1_0_URI, "archive_profil_name");	
	
	/**
	 * Groupes et utilisateurs
	 */
	public static final String GROUP_GG_APP_GED_ARCHIVE = "GROUP_GG_APP_GED_ARCHIVE";
	public static final String GROUP_GG_APP_GED_VERSANT = "GROUP_GG_APP_GED_VERSANT";
}
