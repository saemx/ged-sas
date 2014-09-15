package eu.akka.saem.alfresco.seda.v02.complexType;

import eu.akka.saem.alfresco.seda.annotations.SEDA;
import eu.akka.saem.alfresco.seda.annotations.SEDAType;
import eu.akka.saem.alfresco.seda.form.FieldType;

/**
 * 
 * @author benjamin.catinot
 *
 */
public class OtherMetadataType {

	private String value;

	@SEDA(Position=0,Type=SEDAType.VALUE,PropertyTerm="value",FormType=FieldType.TEXT)
	public String getValue() {
		return value;
	}

	@SEDA(Position=0,Type=SEDAType.VALUE,PropertyTerm="value",FormType=FieldType.TEXT)
	public void setValue(String value) {
		this.value = value;
	}
	
}
