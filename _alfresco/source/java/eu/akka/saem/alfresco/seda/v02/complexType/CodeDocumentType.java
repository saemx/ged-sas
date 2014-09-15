package eu.akka.saem.alfresco.seda.v02.complexType;

import eu.akka.saem.alfresco.seda.annotations.SEDA;
import eu.akka.saem.alfresco.seda.annotations.SEDAType;
import eu.akka.saem.alfresco.seda.form.FieldType;
import eu.akka.saem.alfresco.seda.v02.simpleType.DocumentTypeCodeType;

/**
 * Code correspondant au type de document (source OAIS).
 * @author benjamin.catinot
 *
 */
public class CodeDocumentType {
	private String listVersionID;
	private DocumentTypeCodeType value;

	@SEDA(Position=0,Type=SEDAType.VALUE,PropertyTerm="value", FormType=FieldType.COMBOBOX)
	public DocumentTypeCodeType getValue() {
		return value;
	}

	@SEDA(Position=0,Type=SEDAType.VALUE,PropertyTerm="value", FormType=FieldType.COMBOBOX)
	public void setValue(DocumentTypeCodeType value) {
		this.value = value;
	}

	/**
	 * Attribut
	 * @return The version of the code list.
	 */
	@SEDA(Position=1,Type=SEDAType.ATTRIBUTE,PropertyTerm="listVersionID", FormType=FieldType.TEXT)
	public String getListVersionID() {
		return listVersionID;
	}

	/**
	 * Attribut
	 * @param The version of the code list.
	 */
	@SEDA(Position=1,Type=SEDAType.ATTRIBUTE,PropertyTerm="listVersionID", FormType=FieldType.TEXT)
	public void setListVersionID(String listVersionID) {
		this.listVersionID = listVersionID;
	}
	
}
