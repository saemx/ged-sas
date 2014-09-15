package eu.akka.saem.alfresco.seda.v02.complexType;

import eu.akka.saem.alfresco.seda.annotations.SEDA;
import eu.akka.saem.alfresco.seda.annotations.SEDAType;
import eu.akka.saem.alfresco.seda.v02.simpleType.ISOTwoletterCountryCodeIdentifierContentType;

/**
 * Identifiants des noms de pays (source ISO 3166-1 2A).
 * @author benjamin.catinot
 *
 */
public class ArchivesCountryType {
	private String listVersionID;
	private ISOTwoletterCountryCodeIdentifierContentType value;

	@SEDA(Position=0,Type=SEDAType.VALUE,PropertyTerm="value")
	public ISOTwoletterCountryCodeIdentifierContentType getValue() {
		return value;
	}

	@SEDA(Position=0,Type=SEDAType.VALUE,PropertyTerm="value")
	public void setValue(ISOTwoletterCountryCodeIdentifierContentType value) {
		this.value = value;
	}

	/**
	 * Attribut
	 * @return The version of the code list.
	 */
	@SEDA(Position=1,Type=SEDAType.ATTRIBUTE,PropertyTerm="listVersionID")
	public String getListVersionID() {
		return listVersionID;
	}

	/**
	 * Attribut
	 * @param The version of the code list.
	 */
	@SEDA(Position=1,Type=SEDAType.ATTRIBUTE,PropertyTerm="listVersionID")
	public void setListVersionID(String listVersionID) {
		this.listVersionID = listVersionID;
	}
	
}
