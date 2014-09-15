package eu.akka.saem.alfresco.seda.v02.complexType;

import eu.akka.saem.alfresco.seda.annotations.SEDA;
import eu.akka.saem.alfresco.seda.annotations.SEDAType;
import eu.akka.saem.alfresco.seda.form.FieldType;

/**
 * A character string (letters, figures, or symbols) that for brevity and/or languange independence may be used to represent or replace a definitive value or text of an attribute together with relevant supplementary information.
 * @author benjamin.catinot
 *
 */
public class ArchivesCodeType {
	private String listID;
	private String listAgencyName;
	private String listName;
	private String listVersionID;
	private String name;
	private String languageID;
	private String listURI;
	private String listSchemeURI;
	private String value;
	
	
	/**
	 * Attribut
	 * @return The identification of a list of codes.
	 */
	@SEDA(Position=1,Type=SEDAType.ATTRIBUTE,PropertyTerm="listID",FormType=FieldType.TEXT)
	public String getListID() {
		return listID;
	}
	
	/**
	 * Attribut
	 * @param The identification of a list of codes.
	 */
	@SEDA(Position=1,Type=SEDAType.ATTRIBUTE,PropertyTerm="listID",FormType=FieldType.TEXT)
	public void setListID(String listID) {
		this.listID = listID;
	}
	
	/**
	 * Attribut
	 * @return The name of the agency that maintains the code list.
	 */
	@SEDA(Position=2,Type=SEDAType.ATTRIBUTE,PropertyTerm="listAgencyName",FormType=FieldType.TEXT)
	public String getListAgencyName() {
		return listAgencyName;
	}
	
	/**
	 * Attribut
	 * @param The name of the agency that maintains the code list.
	 */
	@SEDA(Position=2,Type=SEDAType.ATTRIBUTE,PropertyTerm="listAgencyName",FormType=FieldType.TEXT)
	public void setListAgencyName(String listAgencyName) {
		this.listAgencyName = listAgencyName;
	}
	
	/**
	 * Attribut
	 * @return The name of a list of codes.
	 */
	@SEDA(Position=3,Type=SEDAType.ATTRIBUTE,PropertyTerm="listName",FormType=FieldType.TEXT)
	public String getListName() {
		return listName;
	}
	
	/**
	 * Attribut
	 * @param The name of a list of codes.
	 */
	@SEDA(Position=3,Type=SEDAType.ATTRIBUTE,PropertyTerm="listName",FormType=FieldType.TEXT)
	public void setListName(String listName) {
		this.listName = listName;
	}
	
	/**
	 * Attribut
	 * @return The version of the code list.
	 */
	@SEDA(Position=4,Type=SEDAType.ATTRIBUTE,PropertyTerm="listVersionID",FormType=FieldType.TEXT)
	public String getListVersionID() {
		return listVersionID;
	}
	
	/**
	 * Attribut
	 * @param The version of the code list.
	 */
	@SEDA(Position=4,Type=SEDAType.ATTRIBUTE,PropertyTerm="listVersionID",FormType=FieldType.TEXT)
	public void setListVersionID(String listVersionID) {
		this.listVersionID = listVersionID;
	}
	
	/**
	 * Attribut
	 * @return The textual equivalent of the code content.
	 */
	@SEDA(Position=5,Type=SEDAType.ATTRIBUTE,PropertyTerm="name",FormType=FieldType.TEXT)
	public String getName() {
		return name;
	}
	
	/**
	 * Attribut
	 * @param The textual equivalent of the code content.
	 */
	@SEDA(Position=5,Type=SEDAType.ATTRIBUTE,PropertyTerm="name",FormType=FieldType.TEXT)
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Attribut
	 * @return The identifier of the language used in the corresponding text String.
	 */
	@SEDA(Position=6,Type=SEDAType.ATTRIBUTE,PropertyTerm="languageID",FormType=FieldType.TEXT)
	public String getLanguageID() {
		return languageID;
	}
	
	/**
	 * Attribut
	 * @param The identifier of the language used in the corresponding text String.
	 */
	@SEDA(Position=6,Type=SEDAType.ATTRIBUTE,PropertyTerm="languageID",FormType=FieldType.TEXT)
	public void setLanguageID(String languageID) {
		this.languageID = languageID;
	}
	
	/**
	 * Attribut
	 * @return The Uniform Resource Identifier that identifies where the code list is located.
	 */
	@SEDA(Position=7,Type=SEDAType.ATTRIBUTE,PropertyTerm="listURI",FormType=FieldType.TEXT)
	public String getListURI() {
		return listURI;
	}
	
	/**
	 * Attribut
	 * @param The Uniform Resource Identifier that identifies where the code list is located.
	 */
	@SEDA(Position=7,Type=SEDAType.ATTRIBUTE,PropertyTerm="listURI",FormType=FieldType.TEXT)
	public void setListURI(String listURI) {
		this.listURI = listURI;
	}
	
	/**
	 * Attribut
	 * @return The Uniform Resource Identifier that identifies where the code list scheme is located.
	 */
	@SEDA(Position=8,Type=SEDAType.ATTRIBUTE,PropertyTerm="listSchemeURI",FormType=FieldType.TEXT)
	public String getListSchemeURI() {
		return listSchemeURI;
	}
	
	/**
	 * Attribut
	 * @param The Uniform Resource Identifier that identifies where the code list scheme is located.
	 */
	@SEDA(Position=8,Type=SEDAType.ATTRIBUTE,PropertyTerm="listSchemeURI",FormType=FieldType.TEXT)
	public void setListSchemeURI(String listSchemeURI) {
		this.listSchemeURI = listSchemeURI;
	}	
	
	
	@SEDA(Position=9,Type=SEDAType.VALUE,PropertyTerm="value",FormType=FieldType.TEXT)
	public String getValue() {
		return value;
	}
	
	@SEDA(Position=9,Type=SEDAType.VALUE,PropertyTerm="value",FormType=FieldType.TEXT)
	public void setValue(String value) {
		this.value = value;
	}	
	
}
