package eu.akka.saem.alfresco.seda.internationalisation;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 
 *   Description des structures des différents bordereaux afin de pouvoir les afficher
 *   dans le formulaire
 * 
 * @Class         : StructureMessageSEDA.java
 * @Package       : eu.akka.saem.alfresco.seda.internationalisation
 * @Date          : $Date: 25 févr. 2014 $:
 * @author        : $Author: THOMAS.POGNANT $
 * @version       : $Revision:  $:
 * @Id            : $Id: StructureMessageSEDA.java $
 *
 */
public class StructureMessageSEDA {
	LinkedHashMap<String,LinkedHashMap<String,LinkedHashMap<String,String>>> structures = new LinkedHashMap<String,LinkedHashMap<String,LinkedHashMap<String,String>>>();
	LinkedHashMap<String,LinkedHashMap<String,String>> cursor = null;
	List<LinkedHashMap<String,LinkedHashMap<String,String>>> histories = new ArrayList<LinkedHashMap<String,LinkedHashMap<String,String>>>();
	
	public StructureMessageSEDA(){
		
		structures.put("ArchiveTransferReply", new LinkedHashMap<String,LinkedHashMap<String,String>>(){{
			put("ArchiveTransferReplyEntete", new LinkedHashMap<String,String>(){{ put("entete","ArchiveTransferReplyEntete");}});			
		}});
		
		structures.put("ArchiveModificationNotification", new LinkedHashMap<String,LinkedHashMap<String,String>>(){{
			put("ArchiveModificationNotificationEntete", new LinkedHashMap<String,String>(){{ put("entete","ArchiveModificationNotificationEntete");}});			
		}});
		
		structures.put("ArchiveDestructionNotification", new LinkedHashMap<String,LinkedHashMap<String,String>>(){{
			put("ArchiveDestructionNotificationEntete", new LinkedHashMap<String,String>(){{ put("entete","ArchiveDestructionNotificationEntete");}});			
		}});
		
		structures.put("ArchiveDestructionRequestReply", new LinkedHashMap<String,LinkedHashMap<String,String>>(){{
			put("ArchiveDestructionRequestReplyEntete", new LinkedHashMap<String,String>(){{ put("entete","ArchiveDestructionRequestReplyEntete");}});			
		}});
		
		structures.put("ArchiveDestructionRequestReplyReplyAcknowledgement", new LinkedHashMap<String,LinkedHashMap<String,String>>(){{
			put("ArchiveDestructionRequestReplyReplyAcknowledgementEntete", new LinkedHashMap<String,String>(){{ put("entete","ArchiveDestructionRequestReplyReplyAcknowledgementEntete");}});			
		}});
		
		//THOMAS
		structures.put("ArchiveRestitutionRequest", new LinkedHashMap<String,LinkedHashMap<String,String>>(){{
			put("ArchiveRestitutionRequestEntete", new LinkedHashMap<String,String>(){{ put("entete","ArchiveRestitutionRequestEntete");}});	
			put("ArchiveRestitutionRequestDescription", new LinkedHashMap<String,String>(){{ put("description","ArchiveRestitutionRequestDescription");}});
		}});
		
		structures.put("ArchiveRestitutionRequestDescription", new LinkedHashMap<String,LinkedHashMap<String,String>>(){{
			put("Archive", new LinkedHashMap<String,String>(){{ put("listObject","ArchiveType"); put("formListName","Archives"); put("formName","Archive"); }});
		}});
		
		structures.put("ArchiveRestitutionAcknowledgement", new LinkedHashMap<String,LinkedHashMap<String,String>>(){{
			put("ArchiveRestitutionAcknowledgementEntete", new LinkedHashMap<String,String>(){{ put("entete","ArchiveRestitutionAcknowledgementEntete");}});			
		}});
		
		structures.put("ArchiveRestitutionRequestReply", new LinkedHashMap<String,LinkedHashMap<String,String>>(){{
			put("ArchiveRestitutionRequestReplyEntete", new LinkedHashMap<String,String>(){{ put("entete","ArchiveRestitutionRequestReplyEntete");}});			
		}});
		
		structures.put("ArchiveRestitutionRequestEntete", new LinkedHashMap<String,LinkedHashMap<String,String>>(){{
			put("init",null);
			put("Comment", new LinkedHashMap<String,String>(){{ put("type","TEXT"); put("formName","Commentaires"); }});
			put("Date", new LinkedHashMap<String,String>(){{ put("type","DATE"); put("affValue","value"); put("formName","Date"); }});
			put("OriginatingAgency", new LinkedHashMap<String,String>(){{ put("modelObject","OrganizationType"); put("formName","Service producteur"); }});
			put("ArchivalAgency", new LinkedHashMap<String,String>(){{ put("modelObject","OrganizationType"); put("formName","Service d'archive"); }});
			put("RestitutionRequestIdentifier", new LinkedHashMap<String,String>(){{ put("type","TEXT"); put("affValue","value"); put("formName","Identifiant de restitution"); }});
			//put("UnitIdentifier", new LinkedHashMap<String,String>(){{ put("listObject","ArchivesIDType"); put("type","TEXT"); put("affValue","value"); put("formListName","Identifiants d'archives"); put("formName","Identifiant d'archive"); }});		
		}});
		
		structures.put("ArchiveRestitutionAcknowledgementEntete", new LinkedHashMap<String,LinkedHashMap<String,String>>(){{
			put("init",null);
			put("Comment", new LinkedHashMap<String,String>(){{ put("type","TEXT"); put("formName","Commentaires"); }});
			put("Date", new LinkedHashMap<String,String>(){{ put("type","DATE"); put("affValue","value"); put("formName","Date"); }});
			put("OriginatingAgency", new LinkedHashMap<String,String>(){{ put("modelObject","OrganizationType"); put("formName","Service producteur"); }});
			put("ArchivalAgency", new LinkedHashMap<String,String>(){{ put("modelObject","OrganizationType"); put("formName","Service d'archive"); }});
			put("ReplyCode", new LinkedHashMap<String,String>(){{ put("type","COMBOBOX"); put("affValue","value"); put("formName","Code de réponse"); }});
			put("UnitIdentifier", new LinkedHashMap<String,String>(){{ put("listObject","ArchivesIDType"); put("type","TEXT"); put("affValue","value"); put("formListName","Identifiants d'archives"); put("formName","Identifiant d'archive"); }});
			put("RestitutionIdentifier", new LinkedHashMap<String,String>(){{ put("type","TEXT"); put("affValue","value"); put("formName","Identifiant de restitution"); }});
			put("RestitutionAcknowledgementIdentifier", new LinkedHashMap<String,String>(){{ put("type","TEXT"); put("affValue","value"); put("formName","Identifiant de l'accusé de refus de restitution"); }});
		}});

		structures.put("ArchiveRestitutionRequestReplyEntete", new LinkedHashMap<String,LinkedHashMap<String,String>>(){{
			put("init",null);
			put("Comment", new LinkedHashMap<String,String>(){{ put("type","TEXT"); put("formName","Commentaires"); }});
			put("Date", new LinkedHashMap<String,String>(){{ put("type","DATE"); put("affValue","value"); put("formName","Date"); }});
			put("OriginatingAgency", new LinkedHashMap<String,String>(){{ put("modelObject","OrganizationType"); put("formName","Service producteur"); }});
			put("ArchivalAgency", new LinkedHashMap<String,String>(){{ put("modelObject","OrganizationType"); put("formName","Service d'archive"); }});
			put("ReplyCode", new LinkedHashMap<String,String>(){{ put("type","COMBOBOX"); put("affValue","value"); put("formName","Code de réponse"); }});
			put("UnitIdentifier", new LinkedHashMap<String,String>(){{ put("listObject","ArchivesIDType"); put("type","TEXT"); put("affValue","value"); put("formListName","Identifiants d'archives"); put("formName","Identifiant d'archive"); }});
			put("RestitutionRequestIdentifier", new LinkedHashMap<String,String>(){{ put("type","TEXT"); put("affValue","value"); put("formName","Identifiant de restitution"); }});
			put("RestitutionRequestReplyIdentifier", new LinkedHashMap<String,String>(){{ put("type","TEXT"); put("affValue","value"); put("formName","Identifiant de l'accusé de restitution"); }});
		}});
		
		//THOMAS
		
		structures.put("ArchiveDestructionRequestReplyEntete", new LinkedHashMap<String,LinkedHashMap<String,String>>(){{
			put("init",null);
			put("Comment", new LinkedHashMap<String,String>(){{ put("type","TEXT"); put("formName","Commentaires"); }});
			put("Date", new LinkedHashMap<String,String>(){{ put("type","DATE"); put("affValue","value"); put("formName","Date"); }});
			put("ReplyCode", new LinkedHashMap<String,String>(){{ put("type","COMBOBOX"); put("affValue","value"); put("formName","Code de réponse"); }});
			put("DestructionRequestIdentifier", new LinkedHashMap<String,String>(){{ put("type","TEXT"); put("affValue","value"); put("formName","Identifiant d'élimination"); }});
			put("DestructionRequestReplyIdentifier", new LinkedHashMap<String,String>(){{ put("type","TEXT"); put("affValue","value"); put("formName","Identifiant de l'accusé d'élimination"); }});
			put("OriginatingAgency", new LinkedHashMap<String,String>(){{ put("modelObject","OrganizationType"); put("formName","Service producteur"); }});
			put("ArchivalAgency", new LinkedHashMap<String,String>(){{ put("modelObject","OrganizationType"); put("formName","Service d'archive"); }});
		}});
		
		structures.put("ArchiveDestructionNotificationEntete", new LinkedHashMap<String,LinkedHashMap<String,String>>(){{
			put("init",null);
			put("Comment", new LinkedHashMap<String,String>(){{ put("type","TEXT"); put("formName","Commentaires"); }});
			put("Date", new LinkedHashMap<String,String>(){{ put("type","DATE"); put("affValue","value"); put("formName","Date"); }});
			put("DestructionDate", new LinkedHashMap<String,String>(){{ put("type","DATE"); put("affValue","value"); put("formName","Date de destruction"); }});
			put("DestructionNotificationIdentifier", new LinkedHashMap<String,String>(){{ put("type","TEXT"); put("affValue","value"); put("formName","Identifiant de notification d'elimination"); }});
			put("DestructionRequestIdentifier", new LinkedHashMap<String,String>(){{ put("type","TEXT"); put("affValue","value"); put("formName","Identifiant d'élimination"); }});
			put("UnitIdentifier", new LinkedHashMap<String,String>(){{ put("listObject","ArchivesIDType"); put("type","TEXT"); put("affValue","value"); put("formListName","Identifiants d'archives"); put("formName","Identifiant d'archive"); }});
			put("OriginatingAgency", new LinkedHashMap<String,String>(){{ put("modelObject","OrganizationType"); put("formName","Service producteur"); }});
			put("ArchivalAgency", new LinkedHashMap<String,String>(){{ put("modelObject","OrganizationType"); put("formName","Service d'archive"); }});
		}});
		
		structures.put("ArchiveModificationNotificationEntete", new LinkedHashMap<String,LinkedHashMap<String,String>>(){{
			put("init",null);
			put("Comment", new LinkedHashMap<String,String>(){{ put("type","TEXT"); put("formName","Commentaires"); }});
			put("Date", new LinkedHashMap<String,String>(){{ put("type","DATE"); put("affValue","value"); put("formName","Date"); }});
			put("ModificationDate", new LinkedHashMap<String,String>(){{ put("type","DATE"); put("affValue","value"); put("formName","Date de modification"); }});
			put("ModificationNotificationIdentifier", new LinkedHashMap<String,String>(){{ put("type","TEXT"); put("affValue","value"); put("formName","Identifiant de modification"); }});
			put("UnitIdentifier", new LinkedHashMap<String,String>(){{ put("listObject","ArchivesIDType"); put("type","TEXT"); put("affValue","value"); put("formListName","Identifiants d'archives"); put("formName","Identifiant d'archive"); }});
			put("OriginatingAgency", new LinkedHashMap<String,String>(){{ put("modelObject","OrganizationType"); put("formName","Service producteur"); }});
			put("ArchivalAgency", new LinkedHashMap<String,String>(){{ put("modelObject","OrganizationType"); put("formName","Service d'archive"); }});
		}});
		
		structures.put("ArchiveTransferAcceptance", new LinkedHashMap<String,LinkedHashMap<String,String>>(){{
			put("ArchiveTransferAcceptanceEntete", new LinkedHashMap<String,String>(){{ put("entete","ArchiveTransferAcceptanceEntete");}});
			put("ArchiveTransferAcceptanceDescription", new LinkedHashMap<String,String>(){{ put("description","ArchiveTransferAcceptanceDescription");}});
		}});
		
		structures.put("ArchiveTransfer", new LinkedHashMap<String,LinkedHashMap<String,String>>(){{
			put("ArchiveTransferEntete", new LinkedHashMap<String,String>(){{ put("entete","ArchiveTransferEntete");}});
			put("ArchiveTransferDescription", new LinkedHashMap<String,String>(){{ put("description","ArchiveTransferDescription");}});
		}});
		
		structures.put("ArchiveTransferEntete", new LinkedHashMap<String,LinkedHashMap<String,String>>(){{
			put("TransferringAgency", new LinkedHashMap<String,String>(){{ put("modelObject","OrganizationType"); put("formName","Service versant"); }});
			put("ArchivalAgency", new LinkedHashMap<String,String>(){{ put("modelObject","OrganizationType"); put("formName","Service d'archive"); }});
		}});
		
		structures.put("ArchiveRestitution", new LinkedHashMap<String,LinkedHashMap<String,String>>(){{
			put("ArchiveRestitutionEntete", new LinkedHashMap<String,String>(){{ put("entete","ArchiveRestitutionEntete");}});
			put("ArchiveRestitutionDescription", new LinkedHashMap<String,String>(){{ put("description","ArchiveRestitutionDescription");}});
		}});
		
		structures.put("ArchiveRestitutionEntete", new LinkedHashMap<String,LinkedHashMap<String,String>>(){{
			put("Comment", new LinkedHashMap<String,String>(){{ put("type","TEXT"); put("formName","Commentaires"); }});
			put("Date", new LinkedHashMap<String,String>(){{ put("type","DATE"); put("affValue","value"); put("formName","Date"); }});
			put("RestitutionIdentifier", new LinkedHashMap<String,String>(){{ put("type","TEXT"); put("affValue","value"); put("formName","Identifiant de restitution"); }});
			put("UnitIdentifier", new LinkedHashMap<String,String>(){{ put("listObject","ArchivesIDType"); put("type","TEXT"); put("affValue","value"); put("formListName","Identifiants d'archives"); put("formName","Identifiant d'archive"); }});
			put("OriginatingAgency", new LinkedHashMap<String,String>(){{ put("modelObject","OrganizationType"); put("formName","Service producteur"); }});
			put("ArchivalAgency", new LinkedHashMap<String,String>(){{ put("modelObject","OrganizationType"); put("formName","Service d'archive"); }});
		}});
		
		structures.put("ArchiveRestitutionDescription", new LinkedHashMap<String,LinkedHashMap<String,String>>(){{
			put("Archive", new LinkedHashMap<String,String>(){{ put("listObject","ArchiveType"); put("formListName","Archives"); put("formName","Archive"); }});
		}});
		
		structures.put("ArchiveTransferDescription", new LinkedHashMap<String,LinkedHashMap<String,String>>(){{
			put("init",null);
			put("Comment", new LinkedHashMap<String,String>(){{ put("type","TEXT"); put("formName","Commentaires"); }});
			put("Contains", new LinkedHashMap<String,String>(){{ put("listObject","ArchiveTypeTransfer"); put("formListName","Archives"); put("formName","Archive"); }});
		}});
		
		structures.put("ArchiveTypeTransfer", new LinkedHashMap<String,LinkedHashMap<String,String>>(){{
			put("ArchivalAgreement", new LinkedHashMap<String,String>(){{ put("type","TEXT"); put("affValue","value"); put("formName","Convention"); }});
			put("ArchivalProfile", new LinkedHashMap<String,String>(){{ put("type","TEXT"); put("affValue","value"); put("formName","Profil"); }});
			put("DescriptionLanguage", new LinkedHashMap<String,String>(){{ put("listObject","CodeLanguageType"); put("type","COMBOBOX"); put("affValue","value"); put("formListName","Langues de la description"); put("formName","Langue de la description"); }});
			put("DescriptionLevel", new LinkedHashMap<String,String>(){{ put("type","COMBOBOX"); put("affValue","value"); put("formName","Niveau de description"); }});
			put("Name", new LinkedHashMap<String,String>(){{ put("type","TEXT"); put("formName","Nom"); put("affInMenu","true");}});
			put("ServiceLevel", new LinkedHashMap<String,String>(){{ put("listObject","ArchivesCodeType"); put("type","TEXT"); put("affValue","value"); put("formListName","Niveaux de service demandé"); put("formName","Niveau de service demandé"); }});
			put("TransferringAgencyArchiveIdentifier", new LinkedHashMap<String,String>(){{ put("type","TEXT");  put("affValue","value"); put("formName","Identifiant service versant"); }});
			put("ContentDescription", new LinkedHashMap<String,String>(){{ put("modelObject","ContentDescriptionType"); put("formName","Description du contenu"); }});
			put("Appraisal", new LinkedHashMap<String,String>(){{ put("modelObject","AppraisalRulesType"); put("formName","Règle de sort final"); }});
			put("AccessRestriction", new LinkedHashMap<String,String>(){{ put("modelObject","AccessRestrictionRulesType"); put("formName","Règle de restriction d'accès"); }});
			put("Document", new LinkedHashMap<String,String>(){{ put("listObject","DocumentType"); put("formListName","Documents"); put("formName","Document"); }});
			put("Contains", new LinkedHashMap<String,String>(){{ put("listObject","ArchiveObjectTypeTransfer"); put("formListName","Unités documentaires"); put("formName","Unité documentaire"); }});			
		}});
		
		structures.put("ArchiveObjectTypeTransfer", new LinkedHashMap<String,LinkedHashMap<String,String>>(){{
			put("DescriptionLevel", new LinkedHashMap<String,String>(){{ put("type","COMBOBOX"); put("affValue","value"); put("formName","Niveau de description"); }});
			put("Name", new LinkedHashMap<String,String>(){{ put("type","TEXT"); put("formName","Nom"); put("affInMenu","true");}});
			put("TransferringAgencyObjectIdentifier", new LinkedHashMap<String,String>(){{ put("type","TEXT"); put("affValue","value"); put("formName","Identifiant d'origine"); }});
			put("ContentDescription", new LinkedHashMap<String,String>(){{ put("modelObject","ContentDescriptionType"); put("formName","Description du contenu"); }});
			put("Appraisal", new LinkedHashMap<String,String>(){{ put("modelObject","AppraisalRulesType"); put("formName","Règle de sort final"); }});
			put("AccessRestriction", new LinkedHashMap<String,String>(){{ put("modelObject","AccessRestrictionRulesType"); put("formName","Règle de restriction d'accès"); }});
			put("Document", new LinkedHashMap<String,String>(){{ put("listObject","DocumentType"); put("formListName","Documents"); put("formName","Document"); }});
			put("Contains", new LinkedHashMap<String,String>(){{ put("listObject","ArchiveObjectType"); put("formListName","Unités documentaires"); put("formName","Unité documentaire"); }});			
		}});
		
		structures.put("ArchiveTransferAcceptanceEntete", new LinkedHashMap<String,LinkedHashMap<String,String>>(){{
			put("Comment", new LinkedHashMap<String,String>(){{ put("type","TEXT"); put("formName","Commentaires"); }});
			put("Date", new LinkedHashMap<String,String>(){{ put("type","DATE"); put("affValue","value"); put("formName","Date de la prise en charge du transfert"); }});
			put("ReplyCode", new LinkedHashMap<String,String>(){{ put("type","COMBOBOX"); put("affValue","value"); put("formName","Code de la réponse (OK, anomalie...)"); }});
			put("TransferAcceptanceIdentifier", new LinkedHashMap<String,String>(){{ put("type","TEXT"); put("affValue","value"); put("formName","Identifiant de la prise en charge du transfert"); }});
			put("TransferIdentifier", new LinkedHashMap<String,String>(){{ put("type","TEXT"); put("affValue","value"); put("formName","Identifiant du transfert"); }});
			put("TransferringAgency", new LinkedHashMap<String,String>(){{ put("modelObject","OrganizationType"); put("formName","Service versant"); }});
			put("ArchivalAgency", new LinkedHashMap<String,String>(){{ put("modelObject","OrganizationType"); put("formName","Service d'archive"); }});
		}});
		
		structures.put("ArchiveTransferAcceptanceDescription", new LinkedHashMap<String,LinkedHashMap<String,String>>(){{
			put("init",null);
			put("Archive", new LinkedHashMap<String,String>(){{ put("listObject","ArchiveType"); put("formListName","Archives"); put("formName","Archive"); }});
		}});
			
		structures.put("ArchiveType", new LinkedHashMap<String,LinkedHashMap<String,String>>(){{
			put("ArchivalAgencyArchiveIdentifier", new LinkedHashMap<String,String>(){{ put("type","TEXT"); put("affValue","value"); put("formName","Identifiant de l'archive"); }});
			put("ArchivalAgreement", new LinkedHashMap<String,String>(){{ put("type","TEXT"); put("affValue","value"); put("formName","Convention"); }});
			put("ArchivalProfile", new LinkedHashMap<String,String>(){{ put("type","TEXT"); put("affValue","value"); put("formName","Profil"); }});
			put("DescriptionLanguage", new LinkedHashMap<String,String>(){{ put("listObject","CodeLanguageType"); put("type","COMBOBOX"); put("affValue","value"); put("formListName","Langues de la description"); put("formName","Langue de la description"); }});
			put("DescriptionLevel", new LinkedHashMap<String,String>(){{ put("type","COMBOBOX"); put("affValue","value"); put("formName","Niveau de description"); }});
			put("Name", new LinkedHashMap<String,String>(){{ put("type","TEXT"); put("formName","Nom"); put("affInMenu","true");}});
			put("ServiceLevel", new LinkedHashMap<String,String>(){{ put("listObject","ArchivesCodeType"); put("type","TEXT");  put("affValue","value"); put("formListName","Niveaux de service demandé"); put("formName","Niveau de service demandé"); }});
			put("TransferringAgencyArchiveIdentifier", new LinkedHashMap<String,String>(){{ put("type","TEXT"); put("affValue","value"); put("formName","Identifiant service versant"); }});
			put("ContentDescription", new LinkedHashMap<String,String>(){{ put("modelObject","ContentDescriptionType"); put("formName","Description du contenu"); }});
			put("Appraisal", new LinkedHashMap<String,String>(){{ put("modelObject","AppraisalRulesType"); put("formName","Règle de sort final"); }});
			put("AccessRestriction", new LinkedHashMap<String,String>(){{ put("modelObject","AccessRestrictionRulesType"); put("formName","Règle de restriction d'accès"); }});
			put("Document", new LinkedHashMap<String,String>(){{ put("listObject","DocumentType"); put("formListName","Documents"); put("formName","Document"); }});
			put("Contains", new LinkedHashMap<String,String>(){{ put("listObject","ArchiveObjectType"); put("formListName","Unités documentaires"); put("formName","Unité documentaire"); }});			
		}});
		
		structures.put("ArchiveObjectType", new LinkedHashMap<String,LinkedHashMap<String,String>>(){{
			put("ArchivalAgencyObjectIdentifier", new LinkedHashMap<String,String>(){{ put("type","TEXT"); put("affValue","value"); put("formName","Identifiant unité documentaire service d'archive"); }});
			put("DescriptionLevel", new LinkedHashMap<String,String>(){{ put("type","COMBOBOX"); put("affValue","value"); put("formName","Niveau de description"); }});
			put("Name", new LinkedHashMap<String,String>(){{ put("type","TEXT"); put("formName","Nom"); put("affInMenu","true");}});
			put("TransferringAgencyObjectIdentifier", new LinkedHashMap<String,String>(){{ put("type","TEXT"); put("affValue","value"); put("formName","Identifiant d'origine"); }});
			put("ContentDescription", new LinkedHashMap<String,String>(){{ put("modelObject","ContentDescriptionType"); put("formName","Description du contenu"); }});
			put("Appraisal", new LinkedHashMap<String,String>(){{ put("modelObject","AppraisalRulesType"); put("formName","Règle de sort final"); }});
			put("AccessRestriction", new LinkedHashMap<String,String>(){{ put("modelObject","AccessRestrictionRulesType"); put("formName","Règle de restriction d'accès"); }});
			put("Document", new LinkedHashMap<String,String>(){{ put("listObject","DocumentType"); put("formListName","Documents"); put("formName","Document"); }});
			put("Contains", new LinkedHashMap<String,String>(){{ put("listObject","ArchiveObjectType"); put("formListName","Unités documentaires"); put("formName","Unité documentaire"); }});			
		}});
		
		structures.put("DocumentType", new LinkedHashMap<String,LinkedHashMap<String,String>>(){{
			put("Attachment", new LinkedHashMap<String,String>(){{ put("listObject","ArchivesBinaryObjectType"); put("type","FILE"); put("affValue","filename"); put("formListName","Pièces jointes"); put("formName","Pièce jointe"); }});
			put("Control", new LinkedHashMap<String,String>(){{ put("listObject","IndicatorType"); put("type","COMBOBOX"); put("affValue","value"); put("formListName","Présences d'exigences de contrôle"); put("formName","Présence d'exigences de contrôle"); }});
			put("Copy", new LinkedHashMap<String,String>(){{ put("type","COMBOBOX"); put("affValue","value"); put("formName","Exemplaire"); }});
			put("Creation", new LinkedHashMap<String,String>(){{ put("type","DATE"); put("affValue","value"); put("formName","Date de création"); }});
			put("Description", new LinkedHashMap<String,String>(){{ put("type","TEXTAREA"); put("affInMenu","true"); put("formName","Description"); }});
			put("Identification", new LinkedHashMap<String,String>(){{ put("type","TEXT"); put("affValue","value"); put("formName","Exemplaire"); }});
			put("Issue", new LinkedHashMap<String,String>(){{ put("type","DATE"); put("affValue","value"); put("formName","Date d'émission"); }});
			put("ItemIdentifier", new LinkedHashMap<String,String>(){{ put("listObject","ArchivesIDType"); put("affValue","filename"); put("formListName","Identifiants uniques d'un élément particulier dans le document"); put("formName","Identifiant unique d'un élément particulier dans le document"); }});
			put("Purpose", new LinkedHashMap<String,String>(){{ put("type","TEXT"); put("formName","Objet"); }});
			put("Receipt", new LinkedHashMap<String,String>(){{ put("type","DATE"); put("affValue","value"); put("formName","Date de réception"); }});
			put("Response", new LinkedHashMap<String,String>(){{ put("type","DATE"); put("affValue","value"); put("formName","Date de réponse"); }});
			put("Status", new LinkedHashMap<String,String>(){{ put("listObject","ArchivesCodeType"); put("affValue","value"); put("type","TEXT"); put("formListName","Etat"); put("formName","Etat"); }});
			put("Submission", new LinkedHashMap<String,String>(){{ put("type","DATE"); put("affValue","value"); put("formName","Date de soumission"); }});
			put("Type", new LinkedHashMap<String,String>(){{ put("listObject","CodeDocumentType"); put("type","COMBOBOX"); put("affValue","value"); put("formListName","Types"); put("formName","Type"); }});
			put("OtherMetadata", new LinkedHashMap<String,String>(){{ put("listObject","OtherMetadataType"); put("affValue","value"); put("type","TEXT"); put("formListName","Métadonnées métier"); put("formName","Métadonnées métier"); }});			
		}});		
		
		structures.put("ContentDescriptionType", new LinkedHashMap<String,LinkedHashMap<String,String>>(){{
			put("CustodialHistory", new LinkedHashMap<String,String>(){{ put("type","TEXT"); put("formName","Historique de conservation"); }});
			put("Description", new LinkedHashMap<String,String>(){{ put("type","TEXTAREA"); put("affInMenu","true"); put("formName","Description"); }});
			put("FilePlanPosition", new LinkedHashMap<String,String>(){{ put("listObject","ArchivesIDType"); put("affValue","value"); put("type","TEXT"); put("formListName","Plans de classement"); put("formName","Plan de classement"); }});
			put("Format", new LinkedHashMap<String,String>(){{ put("listObject","String"); put("affValue","value"); put("formListName","Formats"); put("formName","Format"); }});
			put("Language", new LinkedHashMap<String,String>(){{ put("listObject","CodeLanguageType"); put("type","COMBOBOX"); put("affValue","value"); put("formListName","Langues du contenu"); put("formName","Langue du contenu"); }});
			put("LatestDate", new LinkedHashMap<String,String>(){{ put("type","DATE"); put("affValue","value"); put("formName","Date de fin"); }});
			put("OldestDate", new LinkedHashMap<String,String>(){{ put("type","DATE"); put("affValue","value"); put("formName","Date de début"); }});
			put("OtherDescriptiveData", new LinkedHashMap<String,String>(){{ put("type","TEXT"); put("formName","Autres informations"); }});
			put("RelatedObjectReference", new LinkedHashMap<String,String>(){{ put("listObject","ArchivesIDType"); put("type","TEXT"); put("affValue","value"); put("formListName","Références complémentaires"); put("formName","Référence complémentaire"); }});
			put("Size", new LinkedHashMap<String,String>(){{ put("listObject","MeasureType"); put("type","TEXT"); put("affValue","value"); put("formListName","Tailles"); put("formName","Taille"); }});
			put("OriginatingAgency", new LinkedHashMap<String,String>(){{ put("listObject","OrganizationType"); put("formListName","Services producteurs"); put("formName","Service producteur"); }});
			put("Repository", new LinkedHashMap<String,String>(){{ put("modelObject","OrganizationType"); put("formName","Service d'archives"); }});
			put("ContentDescriptive", new LinkedHashMap<String,String>(){{ put("listObject","KeywordType"); put("formListName","Mots-clés"); put("formName","Mot-clé"); }});
			put("AccessRestriction", new LinkedHashMap<String,String>(){{ put("listObject","AccessRestrictionRulesType"); put("formListName","Règles de restriction d'accès"); put("formName","Règle de restriction d'accès"); }});			
			put("OtherMetadata", new LinkedHashMap<String,String>(){{ put("listObject","OtherMetadataType"); put("affValue","value"); put("type","TEXT"); put("formListName","Autres métadonnées"); put("formName","Autre métadonnées"); }});			
		}});
		

		structures.put("KeywordType", new LinkedHashMap<String,LinkedHashMap<String,String>>(){{
			put("AccessRestriction", new LinkedHashMap<String,String>(){{ put("modelObject","AccessRestrictionRulesType"); put("formName","Règle de restriction d'accès"); }});
			put("KeywordContent", new LinkedHashMap<String,String>(){{ put("type","TEXT"); put("formName","Mot-clé"); put("affInMenu","true"); }});
			put("KeywordReference", new LinkedHashMap<String,String>(){{ put("type","TEXT"); put("affValue","value"); put("formName","Identifiant dans le référentiel associé"); }});
			put("KeywordType", new LinkedHashMap<String,String>(){{ put("type","COMBOBOX"); put("affValue","value"); put("formName","Type de mot clé"); }});	
		}});
		
		structures.put("AppraisalRulesType", new LinkedHashMap<String,LinkedHashMap<String,String>>(){{
			put("Code", new LinkedHashMap<String,String>(){{ put("type","COMBOBOX"); put("affValue","value"); put("formName","Code"); }});
			put("Duration", new LinkedHashMap<String,String>(){{ put("type","TEXT"); put("affValue","value"); put("formName","Durée d'utilité administrative"); }});
			put("StartDate", new LinkedHashMap<String,String>(){{ put("type","DATE"); put("affValue","value"); put("formName","Date de départ du calcul"); }});
		}});
		
		structures.put("AccessRestrictionRulesType", new LinkedHashMap<String,LinkedHashMap<String,String>>(){{
			put("Code", new LinkedHashMap<String,String>(){{ put("type","COMBOBOX"); put("affValue","value"); put("formName","Code"); }});
			put("StartDate", new LinkedHashMap<String,String>(){{ put("type","DATE"); put("affValue","value"); put("formName","Date de départ du calcul"); }});
		}});
				
		structures.put("ArchiveTransferReplyEntete", new LinkedHashMap<String,LinkedHashMap<String,String>>(){{
			put("init",null);
			put("Comment", new LinkedHashMap<String,String>(){{ put("type","TEXT"); put("formName","Commentaires"); }});
			put("Date", new LinkedHashMap<String,String>(){{ put("type","DATE"); put("affValue","value"); put("formName","Date"); }});
			put("ReplyCode", new LinkedHashMap<String,String>(){{ put("type","COMBOBOX"); put("affValue","value"); put("formName","Code de réponse"); }});
			put("TransferIdentifier", new LinkedHashMap<String,String>(){{ put("type","TEXT"); put("affValue","value"); put("formName","Identifiant de transfert"); }});
			put("TransferReplyIdentifier", new LinkedHashMap<String,String>(){{ put("type","TEXT"); put("affValue","value"); put("formName","Identifiant de l'accusé de reception du transfert"); }});
			put("TransferringAgency", new LinkedHashMap<String,String>(){{ put("modelObject","OrganizationType"); put("formName","Service versant"); }});
			put("ArchivalAgency", new LinkedHashMap<String,String>(){{ put("modelObject","OrganizationType"); put("formName","Service d'archive"); }});
		}});
		
		structures.put("ArchiveDestructionRequestReplyReplyAcknowledgementEntete", new LinkedHashMap<String,LinkedHashMap<String,String>>(){{
			put("init",null);
			put("Comment", new LinkedHashMap<String,String>(){{ put("type","TEXT"); put("formName","Commentaires"); }});
			put("Date", new LinkedHashMap<String,String>(){{ put("type","DATE"); put("affValue","value"); put("formName","Date"); }});
			put("ReplyCode", new LinkedHashMap<String,String>(){{ put("type","COMBOBOX"); put("affValue","value"); put("formName","Code de réponse"); }});
			put("DestructionRequestReplyAcknowledgementIdentifier", new LinkedHashMap<String,String>(){{ put("type","TEXT"); put("affValue","value"); put("formName","Identifiant de l'accusé de refus d'élimination"); }});
			put("DestructionRequestReplyIdentifier", new LinkedHashMap<String,String>(){{ put("type","TEXT"); put("affValue","value"); put("formName","Identifiant de la demande d'élimination"); }});
			put("OriginatingAgency", new LinkedHashMap<String,String>(){{ put("modelObject","OrganizationType"); put("formName","Service versant"); }});
			put("ArchivalAgency", new LinkedHashMap<String,String>(){{ put("modelObject","OrganizationType"); put("formName","Service d'archive"); }});
		}});
		
		structures.put("OrganizationType", new LinkedHashMap<String,LinkedHashMap<String,String>>(){{
			put("BusinessType", new LinkedHashMap<String,String>(){{ put("type","TEXT"); put("affValue","value"); put("formName","Code de l'activité"); }});
			put("Description", new LinkedHashMap<String,String>(){{ put("type","TEXTAREA"); put("affInMenu","true"); put("affValue","value"); put("formName","Description"); }});
			put("Identification", new LinkedHashMap<String,String>(){{ put("type","TEXT"); put("affValue","value"); put("formName","Identifiant"); }});
			put("LegalClassification", new LinkedHashMap<String,String>(){{ put("type","TEXT"); put("affValue","value"); put("formName","Code de la catégorie juridique"); }});
			put("Name", new LinkedHashMap<String,String>(){{ put("type","TEXT"); put("formName","Nom"); put("affInMenu","true"); }});
			put("Contact", new LinkedHashMap<String,String>(){{ put("listObject","ContactType"); put("formListName","Contacts"); put("formName","Contact"); }});
			put("Address", new LinkedHashMap<String,String>(){{ put("listObject","AddressType"); put("formListName","Adresses"); put("formName","Adresse"); }});
			put("Communication", new LinkedHashMap<String,String>(){{ put("listObject","CommunicationType"); put("formListName","Moyens de communication"); put("formName","Moyen de communication"); }});			
		}});
		
		structures.put("AddressType", new LinkedHashMap<String,LinkedHashMap<String,String>>(){{
			put("BlockName", new LinkedHashMap<String,String>(){{ put("type","TEXT"); put("formName","Quartier"); }});
			put("BuildingName", new LinkedHashMap<String,String>(){{ put("type","TEXT"); put("formName","Bâtiment"); }});
			put("BuildingNumber", new LinkedHashMap<String,String>(){{ put("type","TEXT"); put("formName","Numéro"); }});
			put("CityName", new LinkedHashMap<String,String>(){{ put("type","TEXT"); put("formName","Localité"); }});
			put("CitySub-DivisionName", new LinkedHashMap<String,String>(){{ put("type","TEXT"); put("formName","Arrondissement / quartier"); }});
			put("Country", new LinkedHashMap<String,String>(){{ put("type","COMBOBOX"); put("affValue","value"); put("formName","Pays"); }});			
			put("FloorIdentification", new LinkedHashMap<String,String>(){{ put("type","TEXT"); put("formName","Etage"); }});
			put("Postcode", new LinkedHashMap<String,String>(){{ put("type","TEXT"); put("affValue","value"); put("formName","Code postal"); }});					
			put("PostOfficeBox", new LinkedHashMap<String,String>(){{ put("type","TEXT"); put("formName","Boite postale"); }});
			put("RoomIdentification", new LinkedHashMap<String,String>(){{ put("type","TEXT"); put("formName","Pièce"); }});
			put("StreetName", new LinkedHashMap<String,String>(){{ put("type","TEXT"); put("formName","Voie"); }});					
		}});
		
		structures.put("CommunicationType", new LinkedHashMap<String,LinkedHashMap<String,String>>(){{
			put("Channel", new LinkedHashMap<String,String>(){{ put("type","TEXT"); put("affValue","value"); put("formName","Type/Outil de communication"); }});					
			put("CompleteNumber", new LinkedHashMap<String,String>(){{ put("type","TEXT"); put("formName","Numéro"); }});
			put("URI", new LinkedHashMap<String,String>(){{ put("type","TEXT"); put("affValue","value"); put("formName","URI"); }});					
		}});
		
		structures.put("ContactType", new LinkedHashMap<String,LinkedHashMap<String,String>>(){{
			put("DepartmentName", new LinkedHashMap<String,String>(){{ put("type","TEXT"); put("formName","Service"); }});
			put("Identification", new LinkedHashMap<String,String>(){{ put("type","TEXT"); put("affValue","value"); put("formName","Identifiant"); }});
			put("PersonName", new LinkedHashMap<String,String>(){{ put("type","TEXT"); put("formName","Nom"); }});
			put("Responsibility", new LinkedHashMap<String,String>(){{ put("type","TEXT"); put("formName","Attributions"); }});
			put("Address", new LinkedHashMap<String,String>(){{ put("modelObject","AddressType"); put("formName","Adresse"); }});		
			put("Communication", new LinkedHashMap<String,String>(){{ put("listObject","CommunicationType"); put("formListName","Moyens de communication"); put("formName","Moyen de communication"); }});	
		}});
	}
	
	public LinkedHashMap<String,LinkedHashMap<String,String>> getInto(String key){
		if(key == null || key.equals(""))
			return null;
		LinkedHashMap<String,LinkedHashMap<String,String>> result = null;
		
		if(cursor == null){
			if(structures.containsKey(key)){
				cursor = structures.get(key);
			}
			else{
				return null;
			}
		}
		else{
			if(cursor.containsKey(key)){
				
								
				if(cursor.get(key).containsKey("modelObject")){
					histories.add(cursor);
					cursor = structures.get(cursor.get(key).get("modelObject"));
				}
				else if(cursor.get(key).containsKey("entete")){
					histories.add(cursor);
					cursor = structures.get(cursor.get(key).get("entete"));
				}
				else if(cursor.get(key).containsKey("description")){
					histories.add(cursor);
					cursor = structures.get(cursor.get(key).get("description"));
				}
				else if(cursor.get(key).containsKey("listObject")){
					histories.add(cursor);
					cursor = structures.get(cursor.get(key).get("listObject"));
				}
				else{
					return null;
				}
				
				if(cursor == null)
					System.out.println(key + " inexistant");
			}
			else{
				return null;
			}
		}
		
		return result;		
	}
	
	public LinkedHashMap<String,LinkedHashMap<String,String>> getInto(LinkedHashMap<String,String> link){
		LinkedHashMap<String,LinkedHashMap<String,String>> result = null;
		
		if(link.containsKey("modelObject")){
					histories.add(cursor);
					cursor = structures.get(link.get("modelObject"));
				}
				else if(link.containsKey("entete")){
					histories.add(cursor);
					cursor = structures.get(link.get("entete"));
				}
				else if(link.containsKey("description")){
					histories.add(cursor);
					cursor = structures.get(link.get("description"));
				}
				else if(link.containsKey("listObject")){
					histories.add(cursor);
					cursor = structures.get(link.get("listObject"));
				}
				else{
					return null;
				}	
		return cursor;		
	}
	
	
	public LinkedHashMap<String,LinkedHashMap<String,String>> getCurrent(){
		return cursor;
	}
	
	public void setCurrent(LinkedHashMap<String,LinkedHashMap<String,String>> current){
		this.cursor = current;
	}	
	
	public LinkedHashMap<String,LinkedHashMap<String,String>> getOut(){
		if(histories.size() > 0){
			cursor = histories.get(histories.size() - 1);
			histories.remove(histories.size()-1);
			return cursor;		
		}
		
		return null;
	}
	
	
}
	
	
