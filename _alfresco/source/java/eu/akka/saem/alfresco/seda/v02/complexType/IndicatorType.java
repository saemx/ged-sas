package eu.akka.saem.alfresco.seda.v02.complexType;

import eu.akka.saem.alfresco.seda.annotations.SEDA;
import eu.akka.saem.alfresco.seda.annotations.SEDAType;
import eu.akka.saem.alfresco.seda.form.FieldType;
import eu.akka.saem.alfresco.seda.v02.simpleType.IndicatorCodeType;

/**
 * A list of two mutually exclusive values that express the only possible states of a property
 * @author benjamin.catinot
 *
 */
public class IndicatorType {

	private IndicatorCodeType value;

	@SEDA(Position=0,Type=SEDAType.VALUE,PropertyTerm="value", FormType=FieldType.TEXT)
	public IndicatorCodeType getValue() {
		return value;
	}

	@SEDA(Position=0,Type=SEDAType.VALUE,PropertyTerm="value", FormType=FieldType.TEXT)
	public void setValue(IndicatorCodeType value) {
		this.value = value;
	}
	
}
