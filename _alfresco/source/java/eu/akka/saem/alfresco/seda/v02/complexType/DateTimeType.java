package eu.akka.saem.alfresco.seda.v02.complexType;

import java.util.Date;
import eu.akka.saem.alfresco.seda.annotations.SEDA;
import eu.akka.saem.alfresco.seda.annotations.SEDAType;
import eu.akka.saem.alfresco.seda.form.FieldType;


public class DateTimeType {
	
	private Date date;
	
	
	@SEDA(Position=0,Type=SEDAType.VALUE,PropertyTerm="value",FormType=FieldType.DATETIMEWITHHOURS)
	public String getValue() {
		ISO8601 da = new ISO8601(date);
		return da.DateToCompleteString();
	}
	
	@SEDA(Position=0,Type=SEDAType.VALUE,PropertyTerm="value",FormType=FieldType.DATETIMEWITHHOURS)
	public void setValue(String date)  {
		ISO8601 da = new ISO8601(date);		
		this.date = da.getDate();
	}

	public void setDate(Date date){
		this.date = date;		
	}
    
}
