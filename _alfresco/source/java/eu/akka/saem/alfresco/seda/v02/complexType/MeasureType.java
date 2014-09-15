package eu.akka.saem.alfresco.seda.v02.complexType;

import eu.akka.saem.alfresco.seda.annotations.SEDA;
import eu.akka.saem.alfresco.seda.annotations.SEDAType;
import eu.akka.saem.alfresco.seda.form.FieldType;
import eu.akka.saem.alfresco.seda.v02.simpleType.MeasurementUnitCommonCodeContentType;

/**
 * A numeric value determined by measuring an object along with the unit of measure specified or implied.
 * @author benjamin.catinot
 *
 */
public class MeasureType {
	private String value;
	private MeasurementUnitCommonCodeContentType unitCode;

	/**
	 * 
	 * @return The type of unit of measure.
	 */
	@SEDA(Position=0,Type=SEDAType.ATTRIBUTE,PropertyTerm="unitCode",FormType=FieldType.COMBOBOX)
	public MeasurementUnitCommonCodeContentType getUnitCode() {
		return unitCode;
	}

	/**
	 * 
	 * @param The type of unit of measure.
	 */
	@SEDA(Position=0,Type=SEDAType.ATTRIBUTE,PropertyTerm="unitCode",FormType=FieldType.COMBOBOX)
	public void setUnitCode(MeasurementUnitCommonCodeContentType unitCode) {
		this.unitCode = unitCode;
	}

	@SEDA(Position=1,Type=SEDAType.VALUE,PropertyTerm="value",FormType=FieldType.TEXT)
	public String getValue() {
		return value;
	}

	@SEDA(Position=1,Type=SEDAType.VALUE,PropertyTerm="value",FormType=FieldType.TEXT)
	public void setValue(String value) {
		this.value = value;
	}
		
}
