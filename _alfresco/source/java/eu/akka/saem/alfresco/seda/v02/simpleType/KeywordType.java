package eu.akka.saem.alfresco.seda.v02.simpleType;

import eu.akka.saem.alfresco.seda.annotations.SEDA;

/**
 * Table des types de mots-clef
 * 
**/

public enum KeywordType {

/**
 * Collectivité
 * 
 **/ 
 @SEDA(PropertyTerm="corpname", FormName="Collectivité",FormDescription="")
CORPNAME,
/**
 * Nom de famille
 * 
 **/ 
 @SEDA(PropertyTerm="famname", FormName="Nom de famille",FormDescription="")
FAMNAME,
/**
 * Nom géographique
 * 
 **/ 
 @SEDA(PropertyTerm="geogname", FormName="Nom géographique",FormDescription="")
GEOGNAME,
/**
 * Nom
 * 
 **/ 
 @SEDA(PropertyTerm="name", FormName="Nom",FormDescription="")
NAME,
/**
 * Fonction
 * 
 **/ 
 @SEDA(PropertyTerm="occupation", FormName="Fonction",FormDescription="")
OCCUPATION,
/**
 * Nom de personne
 * 
 **/ 
 @SEDA(PropertyTerm="persname", FormName="Nom de personne",FormDescription="")
PERSNAME,
/**
 * Mot-matière
 * 
 **/ 
 @SEDA(PropertyTerm="subject", FormName="Mot-matière",FormDescription="")
SUBJECT,
/**
 * Type de document
 * 
 **/ 
 @SEDA(PropertyTerm="genreform", FormName="Type de document",FormDescription="")
GENREFORM,
/**
 * Activité
 * 
 **/ 
 @SEDA(PropertyTerm="function", FormName="Activité",FormDescription="")
FUNCTION,
/**
 * 
 * 
 **/ 
 @SEDA(PropertyTerm="", FormName="",FormDescription="")
EMPTY
}

