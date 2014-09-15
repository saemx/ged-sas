package eu.akka.saem.alfresco.seda.v02.complexType;

import eu.akka.saem.alfresco.seda.annotations.SEDA;
import eu.akka.saem.alfresco.seda.annotations.SEDAType;
import eu.akka.saem.alfresco.seda.form.FieldType;
import eu.akka.saem.alfresco.seda.v02.simpleType.AccessRestrictionCodeType;

/**
 * Code correspondant au niveau de restriction d'access (source: loi archive).
 * @author benjamin.catinot
 *
 */
public class CodeAccessRestrictionType {
	private String listVersionID;
	private AccessRestrictionCodeType value;

	@SEDA(Position=0,Type=SEDAType.VALUE,PropertyTerm="value",FormType=FieldType.COMBOBOX)
	public AccessRestrictionCodeType getValue() {
		return value;
	}

	@SEDA(Position=0,Type=SEDAType.VALUE,PropertyTerm="value",FormType=FieldType.COMBOBOX)
	public void setValue(AccessRestrictionCodeType value) {
		this.value = value;
	}

	/**
	 * Attribut
	 * @return The version of the code list.
	 */
	@SEDA(Position=1,Type=SEDAType.ATTRIBUTE,PropertyTerm="listVersionID",FormType=FieldType.TEXT)
	public String getListVersionID() {
		return listVersionID;
	}

	/**
	 * Attribut
	 * @param The version of the code list.
	 */
	@SEDA(Position=1,Type=SEDAType.ATTRIBUTE,PropertyTerm="listVersionID",FormType=FieldType.TEXT)
	public void setListVersionID(String listVersionID) {
		this.listVersionID = listVersionID;
	}	
}
