package eu.akka.saem.alfresco.seda.v02.simpleType;

import eu.akka.saem.alfresco.seda.annotations.SEDA;

/**
 * Table des codes pour le sort final
 * @author benjamin.catinot
 *
 */
public enum IndicatorCodeType {
	/**
	 * Conserver
	 */
	@SEDA(PropertyTerm="true")
	TRUE,
	
	/**
	 * Détruire
	 */
	@SEDA(PropertyTerm="false")
	FALSE,
	
	@SEDA(PropertyTerm="")
	EMPTY
}
