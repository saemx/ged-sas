package eu.akka.saem.alfresco.seda.v02.simpleType;

import eu.akka.saem.alfresco.seda.annotations.SEDA;

/**
 * Table des types d'information
 * 
**/

public enum DocumentTypeCodeType {

/**
 * Contenu de données
 * Objet numérique ou physique qui est l'objet principal de
							la pérennisation. ISO 14721:2003(Space data and information transfer
							systems -- Open archival information system --Reference
						model)
 **/ 
 @SEDA(PropertyTerm="CDO", FormName="Contenu de données",FormDescription="Objet numérique ou physique qui est l'objet principal de							la pérennisation. ISO 14721:2003(Space data and information transfer							systems -- Open archival information system --Reference						model)")
CDO,
/**
 * Information de représentation
 * Information qui traduit un contenu de données en des
							concepts plus explicites. Par exemple, la définition du code ASCII
							décrit comment une séquence de bits (un contenu de données) est
							convertie en caractères. L'information de représentation peut être de
							structure ou sémantique. ISO 14721:2003 (Space data and information
							transfer systems -- Open archival information system -- Reference
						model
 **/ 
 @SEDA(PropertyTerm="RI", FormName="Information de représentation",FormDescription="Information qui traduit un contenu de données en des							concepts plus explicites. Par exemple, la définition du code ASCII							décrit comment une séquence de bits (un contenu de données) est							convertie en caractères. L'information de représentation peut être de							structure ou sémantique. ISO 14721:2003 (Space data and information							transfer systems -- Open archival information system -- Reference						model")
RI,
/**
 * Information de structure
 * Information de représentation qui explique la façon dont
							d'autres informations sont organisées. Elle établit par exemple une
							correspondance entre les trains de bits et les types de données courants
							sur ordinateurs (tels que caractères, nombres, pixels ou agrégats de ces
							types tels que chaînes de caractères et tableaux). ISO 14721:2003 (Space
							data and information transfer systems -- Open archival information
							system -- Reference model)
 **/ 
 @SEDA(PropertyTerm="RISTR", FormName="Information de structure",FormDescription="Information de représentation qui explique la façon dont							d'autres informations sont organisées. Elle établit par exemple une							correspondance entre les trains de bits et les types de données courants							sur ordinateurs (tels que caractères, nombres, pixels ou agrégats de ces							types tels que chaînes de caractères et tableaux). ISO 14721:2003 (Space							data and information transfer systems -- Open archival information							system -- Reference model)")
RISTR,
/**
 * Information sémantique
 * Information de représentation qui complète l'information
							de structure pour donner par exemple la signification particulière
							associée à chacun des éléments de la structure, les opérations
							réalisables sur chaque type de données, leurs corrélations... ISO
							14721:2003 (Space data and information systemstransfe -- Open archival
							information system -- Reference model)
 **/ 
 @SEDA(PropertyTerm="RISEM", FormName="Information sémantique",FormDescription="Information de représentation qui complète l'information							de structure pour donner par exemple la signification particulière							associée à chacun des éléments de la structure, les opérations							réalisables sur chaque type de données, leurs corrélations... ISO							14721:2003 (Space data and information systemstransfe -- Open archival							information system -- Reference model)")
RISEM,
/**
 * Information de pérennisation
 * Information nécessaire à une bonne conservation du contenu
							d'information, et qui peut être décomposée en informations de
							provenance, d'identification, d'intégrité et de contexte. ISO 14721:2003
							(Space data and information transfer systems -- Open archival
							information system -- Reference model)
 **/ 
 @SEDA(PropertyTerm="PDI", FormName="Information de pérennisation",FormDescription="Information nécessaire à une bonne conservation du contenu							d'information, et qui peut être décomposée en informations de							provenance, d'identification, d'intégrité et de contexte. ISO 14721:2003							(Space data and information transfer systems -- Open archival							information system -- Reference model)")
PDI,
/**
 * Information de provenance
 * Information de pérennisation qui documente l'historique du
							contenu d'information. Cette information renseigne sur l'origine ou la
							source du contenu d'information, sur toute modification intervenue
							depuis sa création et sur ceux qui en ont eu la responsabilité. Exemple
							: nom du principal responsable de l'enregistrement des données,
							informations relatives au stockage, à la manipulation et à la migration
							des données. ISO 14721:2003 (Space data and information transfer systems
							-- Open archival information system -- Reference
						model)
 **/ 
 @SEDA(PropertyTerm="PDIPRO", FormName="Information de provenance",FormDescription="Information de pérennisation qui documente l'historique du							contenu d'information. Cette information renseigne sur l'origine ou la							source du contenu d'information, sur toute modification intervenue							depuis sa création et sur ceux qui en ont eu la responsabilité. Exemple							: nom du principal responsable de l'enregistrement des données,							informations relatives au stockage, à la manipulation et à la migration							des données. ISO 14721:2003 (Space data and information transfer systems							-- Open archival information system -- Reference						model)")
PDIPRO,
/**
 * Information d'identification
 * Information de pérennisation qui identifie, et si
							nécessaire décrit, le ou les mécanismes d'attribution des
							identificateurs au contenu d'information. Elle inclut aussi les
							identificateurs qui permettent à un système externe de se référer sans
							équivoque à un contenu d'information particulier. ISO 14721:2003 (Space
							data and information transfer systems -- Open archival information
							system -- Reference model)
 **/ 
 @SEDA(PropertyTerm="PDIREF", FormName="Information d'identification",FormDescription="Information de pérennisation qui identifie, et si							nécessaire décrit, le ou les mécanismes d'attribution des							identificateurs au contenu d'information. Elle inclut aussi les							identificateurs qui permettent à un système externe de se référer sans							équivoque à un contenu d'information particulier. ISO 14721:2003 (Space							data and information transfer systems -- Open archival information							system -- Reference model)")
PDIREF,
/**
 * Information d'intégrité
 * Information de pérennisation qui décrit les mécanismes et
							des clés d'authentification garantissant que le contenu d'information
							n'a pas subi de modification sans que celle-ci ait été tracée. Par
							exemple, le code CRC (contrôl de redondance cyclique) pour un fichier.
							ISO 14721:2003 (Space data and information transfer systems -- Open
							archival information system -- Reference model)
 **/ 
 @SEDA(PropertyTerm="PDIFIX", FormName="Information d'intégrité",FormDescription="Information de pérennisation qui décrit les mécanismes et							des clés d'authentification garantissant que le contenu d'information							n'a pas subi de modification sans que celle-ci ait été tracée. Par							exemple, le code CRC (contrôl de redondance cyclique) pour un fichier.							ISO 14721:2003 (Space data and information transfer systems -- Open							archival information system -- Reference model)")
PDIFIX,
/**
 * Information de contexte
 * Information de pérennisation qui décrit les liens entre un
							contenu d'information et son environnement. Elle inclut entre autres les
							raisons de la création de ce contenu d'information et son rapport avec
							d'autres Objets-contenu d'information. ISO 14721:2003 (Space data and
							information transfer systems -- Open archival information system --
							Reference model)
 **/ 
 @SEDA(PropertyTerm="PDICTX", FormName="Information de contexte",FormDescription="Information de pérennisation qui décrit les liens entre un							contenu d'information et son environnement. Elle inclut entre autres les							raisons de la création de ce contenu d'information et son rapport avec							d'autres Objets-contenu d'information. ISO 14721:2003 (Space data and							information transfer systems -- Open archival information system --							Reference model)")
PDICTX,
/**
 * 
 * 
 **/ 
 @SEDA(PropertyTerm="", FormName="",FormDescription="")
EMPTY
}

