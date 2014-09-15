package eu.akka.saem.alfresco.seda.v02.simpleType;

import eu.akka.saem.alfresco.seda.annotations.SEDA;

/**
 * Table des codes pour le sort final
 * 
**/

public enum AppraisalCodeType {

/**
 * Conserver
 * 
 **/ 
 @SEDA(PropertyTerm="conserver", FormName="Conserver",FormDescription="")
CONSERVER,
/**
 * Détruire
 * 
 **/ 
 @SEDA(PropertyTerm="detruire", FormName="Détruire",FormDescription="")
DETRUIRE,
/**
 * 
 * 
 **/ 
 @SEDA(PropertyTerm="", FormName="",FormDescription="")
EMPTY
}

