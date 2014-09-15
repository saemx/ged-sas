package eu.akka.saem.alfresco.seda.v02.simpleType;

import eu.akka.saem.alfresco.seda.annotations.SEDA;

/**
 * Table des codes réponse
 * 
**/

public enum ReplyCodeType {

/**
 * OK
 * 
 **/ 
 @SEDA(PropertyTerm="000", FormName="OK",FormDescription="")
NUM000,
/**
 * OK (consulter les commentaires pour
							information)
 * 
 **/ 
 @SEDA(PropertyTerm="001", FormName="OK (consulter les commentaires pour information)",FormDescription="")
NUM001,
/**
 * OK (Demande aux autorités de contrôle effectuée)
 * 
 **/ 
 @SEDA(PropertyTerm="002", FormName="OK (Demande aux autorités de contrôle effectuée)",FormDescription="")
NUM002,
/**
 * Message mal formé.
 * 
 **/ 
 @SEDA(PropertyTerm="101", FormName="Message mal formé.",FormDescription="")
NUM101,
/**
 * Système momentanément indisponible.
 * 
 **/ 
 @SEDA(PropertyTerm="102", FormName="Système momentanément indisponible.",FormDescription="")
NUM102,
/**
 * Service producteur non reconnu.
 * 
 **/ 
 @SEDA(PropertyTerm="200", FormName="Service producteur non reconnu.",FormDescription="")
NUM200,
/**
 * Service d'archive non reconnu.
 * 
 **/ 
 @SEDA(PropertyTerm="201", FormName="Service d'archive non reconnu.",FormDescription="")
NUM201,
/**
 * Service versant non reconnu.
 * 
 **/ 
 @SEDA(PropertyTerm="202", FormName="Service versant non reconnu.",FormDescription="")
NUM202,
/**
 * Dépôt non conforme au profil de données.
 * 
 **/ 
 @SEDA(PropertyTerm="203", FormName="Dépôt non conforme au profil de données.",FormDescription="")
NUM203,
/**
 * Format de document non géré.
 * 
 **/ 
 @SEDA(PropertyTerm="204", FormName="Format de document non géré.",FormDescription="")
NUM204,
/**
 * Format de document non conforme au format déclaré.
 * 
 **/ 
 @SEDA(PropertyTerm="205", FormName="Format de document non conforme au format déclaré.",FormDescription="")
NUM205,
/**
 * Signature du message invalide.
 * 
 **/ 
 @SEDA(PropertyTerm="206", FormName="Signature du message invalide.",FormDescription="")
NUM206,
/**
 * Empreinte(s) invalide(s).
 * 
 **/ 
 @SEDA(PropertyTerm="207", FormName="Empreinte(s) invalide(s).",FormDescription="")
NUM207,
/**
 * Archive indisponible. Délai de communication non écoulé.
 * 
 **/ 
 @SEDA(PropertyTerm="208", FormName="Archive indisponible. Délai de communication non écoulé.",FormDescription="")
NUM208,
/**
 * Archive absente (élimination, restitution, transfert)
 * 
 **/ 
 @SEDA(PropertyTerm="209", FormName="Archive absente (élimination, restitution, transfert)",FormDescription="")
NUM209,
/**
 * Archive inconnue
 * 
 **/ 
 @SEDA(PropertyTerm="210", FormName="Archive inconnue",FormDescription="")
NUM210,
/**
 * Pièce attachée absente.
 * 
 **/ 
 @SEDA(PropertyTerm="211", FormName="Pièce attachée absente.",FormDescription="")
NUM211,
/**
 * Dérogation refusée.
 * 
 **/ 
 @SEDA(PropertyTerm="212", FormName="Dérogation refusée.",FormDescription="")
NUM212,
/**
 * Convention invalide.
 * 
 **/ 
 @SEDA(PropertyTerm="300", FormName="Convention invalide.",FormDescription="")
NUM300,
/**
 * Dépôt non conforme à la convention. Quota des versements dépassé.
 * 
 **/ 
 @SEDA(PropertyTerm="301", FormName="Dépôt non conforme à la convention. Quota des versements dépassé.",FormDescription="")
NUM301,
/**
 * Dépôt non conforme à la convention. Identifiant du producteur non conforme.
 * 
 **/ 
 @SEDA(PropertyTerm="302", FormName="Dépôt non conforme à la convention. Identifiant du producteur non conforme.",FormDescription="")
NUM302,
/**
 * Dépôt non conforme à la convention. Identifiant du service versant non conforme.
 * 
 **/ 
 @SEDA(PropertyTerm="303", FormName="Dépôt non conforme à la convention. Identifiant du service versant non conforme.",FormDescription="")
NUM303,
/**
 * Dépôt non conforme à la convention. Identifiant du service d'archives non conforme.
 * 
 **/ 
 @SEDA(PropertyTerm="304", FormName="Dépôt non conforme à la convention. Identifiant du service d'archives non conforme.",FormDescription="")
NUM304,
/**
 * Dépôt non conforme à la convention. Signature(s) de document(s) absente(s).
 * 
 **/ 
 @SEDA(PropertyTerm="305", FormName="Dépôt non conforme à la convention. Signature(s) de document(s) absente(s).",FormDescription="")
NUM305,
/**
 * Dépôt non conforme à la convention. Volume non conforme.
 * 
 **/ 
 @SEDA(PropertyTerm="306", FormName="Dépôt non conforme à la convention. Volume non conforme.",FormDescription="")
NUM306,
/**
 * Dépôt non conforme à la convention. Format non conforme.
 * 
 **/ 
 @SEDA(PropertyTerm="307", FormName="Dépôt non conforme à la convention. Format non conforme.",FormDescription="")
NUM307,
/**
 * Dépôt non conforme à la convention. Empreinte(s) non transmise(s).
 * 
 **/ 
 @SEDA(PropertyTerm="308", FormName="Dépôt non conforme à la convention. Empreinte(s) non transmise(s).",FormDescription="")
NUM308,
/**
 * Dépôt non conforme à la convention. Absence de signature du message.
 * 
 **/ 
 @SEDA(PropertyTerm="309", FormName="Dépôt non conforme à la convention. Absence de signature du message.",FormDescription="")
NUM309,
/**
 * Dépôt non conforme à la convention. Signature(s) de document(s) non valide(s).
 * 
 **/ 
 @SEDA(PropertyTerm="310", FormName="Dépôt non conforme à la convention. Signature(s) de document(s) non valide(s).",FormDescription="")
NUM310,
/**
 * Dépôt non conforme à la convention. Signature(s) de document(s) non vérifiée(s).
 * 
 **/ 
 @SEDA(PropertyTerm="311", FormName="Dépôt non conforme à la convention. Signature(s) de document(s) non vérifiée(s).",FormDescription="")
NUM311,
/**
 * Dépôt non conforme à la convention. Dates de début ou de fin non respectées.
 * 
 **/ 
 @SEDA(PropertyTerm="312", FormName="Dépôt non conforme à la convention. Dates de début ou de fin non respectées.",FormDescription="")
NUM312,
/**
 * 
 * 
 **/ 
 @SEDA(PropertyTerm="", FormName="",FormDescription="")
EMPTY
}

