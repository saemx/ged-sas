package eu.akka.saem.alfresco.seda.v02.complexType;

import eu.akka.saem.alfresco.seda.annotations.SEDA;
import eu.akka.saem.alfresco.seda.annotations.SEDAType;
import eu.akka.saem.alfresco.seda.form.FieldType;

public class ArchivesIDType {
	private String schemeID;
	private String schemeName;
	private String schemeAgencyName;
	private String schemeVersionID;
	private String schemeDataURI;
	private String schemeURI;
	private String value;
	
	/**
	 * Attribut
	 * The identification of the identification scheme.
	 * @return Identification Scheme. Identifier
	 */
	@SEDA(Position=0,Type=SEDAType.ATTRIBUTE,PropertyTerm="schemeID",FormType=FieldType.TEXT)
	public String getSchemeID() {
		return schemeID;
	}
	
	@SEDA(Position=1,Type=SEDAType.VALUE,PropertyTerm="value",FormType=FieldType.TEXT)
	public String getValue() {
		return value;
	}

	@SEDA(Position=1,Type=SEDAType.VALUE,PropertyTerm="value",FormType=FieldType.TEXT)
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * Attribut
	 * The identification of the identification scheme.
	 * @param Identification Scheme. Identifier
	 */
	@SEDA(Position=0,Type=SEDAType.ATTRIBUTE,PropertyTerm="schemeID",FormType=FieldType.TEXT)
	public void setSchemeID(String schemeID) {
		this.schemeID = schemeID;
	}
	
	/**
	 * Attribut
	 * @return The name of the identification scheme.
	 */
	@SEDA(Position=2,Type=SEDAType.ATTRIBUTE,PropertyTerm="schemeName",FormType=FieldType.TEXT)
	public String getSchemeName() {
		return schemeName;
	}
	
	/**
	 * Attribut
	 * @param The name of the identification scheme.
	 */
	@SEDA(Position=2,Type=SEDAType.ATTRIBUTE,PropertyTerm="schemeName",FormType=FieldType.TEXT)
	public void setSchemeName(String schemeName) {
		this.schemeName = schemeName;
	}
	
	/**
	 * Attribut
	 * @return The identification of the agency that maintains the identification scheme.
	 */
	@SEDA(Position=3,Type=SEDAType.ATTRIBUTE,PropertyTerm="schemeAgencyName",FormType=FieldType.TEXT)
	public String getSchemeAgencyName() {
		return schemeAgencyName;
	}
	
	/**
	 * Attribut
	 * @param The identification of the agency that maintains the identification scheme.
	 */
	@SEDA(Position=3,Type=SEDAType.ATTRIBUTE,PropertyTerm="schemeAgencyName",FormType=FieldType.TEXT)
	public void setSchemeAgencyName(String schemeAgencyName) {
		this.schemeAgencyName = schemeAgencyName;
	}
	
	/**
	 * Attribut
	 * @return The name of the agency that maintains the identification scheme.
	 */
	@SEDA(Position=4,Type=SEDAType.ATTRIBUTE,PropertyTerm="schemeVersionID",FormType=FieldType.TEXT)
	public String getSchemeVersionID() {
		return schemeVersionID;
	}
	
	/**
	 * Attribut
	 * @param The name of the agency that maintains the identification scheme.
	 */
	@SEDA(Position=4,Type=SEDAType.ATTRIBUTE,PropertyTerm="schemeVersionID",FormType=FieldType.TEXT)
	public void setSchemeVersionID(String schemeVersionID) {
		this.schemeVersionID = schemeVersionID;
	}
	
	/**
	 * Attribut
	 * @return The version of the identification scheme.
	 */
	@SEDA(Position=5,Type=SEDAType.ATTRIBUTE,PropertyTerm="schemeDataURI",FormType=FieldType.TEXT)
	public String getSchemeDataURI() {
		return schemeDataURI;
	}
	
	/**
	 * Attribut
	 * @param The version of the identification scheme.
	 */
	@SEDA(Position=5,Type=SEDAType.ATTRIBUTE,PropertyTerm="schemeDataURI",FormType=FieldType.TEXT)
	public void setSchemeDataURI(String schemeDataURI) {
		this.schemeDataURI = schemeDataURI;
	}
	
	/**
	 * Attribut
	 * @return The Uniform Resource Identifier that identifies where the identification scheme data is located.
	 */
	@SEDA(Position=6,Type=SEDAType.ATTRIBUTE,PropertyTerm="schemeURI",FormType=FieldType.TEXT)
	public String getSchemeURI() {
		return schemeURI;
	}
	
	/**
	 * Attribut
	 * @param The Uniform Resource Identifier that identifies where the identification scheme data is located.
	 */
	@SEDA(Position=6,Type=SEDAType.ATTRIBUTE,PropertyTerm="schemeURI",FormType=FieldType.TEXT)
	public void setSchemeURI(String schemeURI) {
		this.schemeURI = schemeURI;
	}
}
