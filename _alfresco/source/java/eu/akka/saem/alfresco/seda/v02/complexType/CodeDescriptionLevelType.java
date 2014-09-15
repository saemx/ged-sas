package eu.akka.saem.alfresco.seda.v02.complexType;

import eu.akka.saem.alfresco.seda.annotations.SEDA;
import eu.akka.saem.alfresco.seda.annotations.SEDAType;
import eu.akka.saem.alfresco.seda.form.FieldType;
import eu.akka.saem.alfresco.seda.v02.simpleType.LevelDescriptionType;

/**
 * Code correspondant au niveau de description archivistique (source: EAD).
 * @author benjamin.catinot
 *
 */
public class CodeDescriptionLevelType {
	private String listVersionID;
	private LevelDescriptionType value;

	@SEDA(Position=0,Type=SEDAType.VALUE,PropertyTerm="value",FormType=FieldType.COMBOBOX)
	public LevelDescriptionType getValue() {
		return value;
	}

	@SEDA(Position=0,Type=SEDAType.VALUE,PropertyTerm="value",FormType=FieldType.COMBOBOX)
	public void setValue(LevelDescriptionType value) {
		this.value = value;
	}

	/**
	 * Attribut
	 * @return The version of the code list.
	 */
	@SEDA(Position=1,Type=SEDAType.ATTRIBUTE,PropertyTerm="listVersionID",FormType=FieldType.COMBOBOX)
	public String getListVersionID() {
		return listVersionID;
	}

	/**
	 * Attribut
	 * @param The version of the code list.
	 */
	@SEDA(Position=1,Type=SEDAType.ATTRIBUTE,PropertyTerm="listVersionID",FormType=FieldType.COMBOBOX)
	public void setListVersionID(String listVersionID) {
		this.listVersionID = listVersionID;
	}
	
	
}
