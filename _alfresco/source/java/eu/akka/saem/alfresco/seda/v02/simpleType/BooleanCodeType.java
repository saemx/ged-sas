package eu.akka.saem.alfresco.seda.v02.simpleType;

import eu.akka.saem.alfresco.seda.annotations.SEDA;

public enum BooleanCodeType {

/**
 * Conserver
 * 
 **/ 
 @SEDA(PropertyTerm="true", FormName="Vrai",FormDescription="")
TRUE,
/**
 * Détruire
 * 
 **/ 
 @SEDA(PropertyTerm="false", FormName="Faux",FormDescription="")
FALSE,
/**
 * 
 * 
 **/ 
 @SEDA(PropertyTerm="", FormName="",FormDescription="")
EMPTY
}

