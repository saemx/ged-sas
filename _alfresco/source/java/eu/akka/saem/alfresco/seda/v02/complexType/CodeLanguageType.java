package eu.akka.saem.alfresco.seda.v02.complexType;

import eu.akka.saem.alfresco.seda.annotations.SEDA;
import eu.akka.saem.alfresco.seda.annotations.SEDAType;
import eu.akka.saem.alfresco.seda.form.FieldType;
import eu.akka.saem.alfresco.seda.v02.simpleType.LanguageCodeType;
/**
 * Code correspondant à la langue (source ISO-639).
 * @author benjamin.catinot
 *
 */
public class CodeLanguageType {
	private String listVersionID;
	private LanguageCodeType value;

	@SEDA(Position=1,Type=SEDAType.VALUE,PropertyTerm="value",FormType=FieldType.COMBOBOX)
	public LanguageCodeType getValue() {
		return value;
	}

	@SEDA(Position=1,Type=SEDAType.VALUE,PropertyTerm="value",FormType=FieldType.COMBOBOX)
	public void setValue(LanguageCodeType value) {
		this.value = value;
	}

	/**
	 * Attribut
	 * @return The version of the code list.
	 */
	@SEDA(Position=2,Type=SEDAType.ATTRIBUTE,PropertyTerm="listVersionID",FormType=FieldType.TEXT)
	public String getListVersionID() {
		return listVersionID;
	}

	/**
	 * Attribut
	 * @param The version of the code list.
	 */
	@SEDA(Position=2,Type=SEDAType.ATTRIBUTE,PropertyTerm="listVersionID",FormType=FieldType.TEXT)
	public void setListVersionID(String listVersionID) {
		this.listVersionID = listVersionID;
	}
	
}
