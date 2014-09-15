package eu.akka.saem.alfresco.seda.v02.complexType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;


public class ISO8601 {
	private Date date;
	private String format = "";
	
	public ISO8601(String stringdate){		
		try {
			StringToComplete(stringdate);
			return ;
		} catch (ParseException e) {
		}
		try {
			StringToCompleteDateHoursMinutesSeconds(stringdate);
			return ;
		} catch (ParseException e) {
		}
		try {
			StringToCompleteDateHoursMinutes(stringdate);
			return ;
		} catch (ParseException e) {
		}
		try {
			StringToCompleteDate(stringdate);
			return ;
		} catch (ParseException e) {
		}
		try {
			StringToYearMonth(stringdate);
			return ;
		} catch (ParseException e) {
		}
		try {
			StringToYear(stringdate);
			return ;
		} catch (ParseException e) {
		}
		
		
		
		
		
	}
	
	public ISO8601(Date date){
		this.date = date;
	}
	
	private void StringToYear(String stringdate) throws ParseException{
		format = "yyyy";
		SimpleDateFormat df = new SimpleDateFormat(format);
		date = df.parse(stringdate);		        
	}
	
	private void StringToYearMonth(String stringdate) throws ParseException{
		format = "yyyy-MM";
		SimpleDateFormat df = new SimpleDateFormat(format);
		date = df.parse(stringdate);
	}
	
	private void StringToCompleteDate(String input) throws ParseException{
		format = "yyyy-MM-dd";
		SimpleDateFormat df = new SimpleDateFormat(format);
		date = df.parse(input);
	}
	
	private void StringToCompleteDateHoursMinutes(String input) throws ParseException{
		format = "yyyy-MM-dd'T'HH:mmz";
		SimpleDateFormat df = new SimpleDateFormat(format);        
		//this is zero time so we need to add that TZ indicator for 
        if (input.endsWith("Z") ) {
            input = input.substring(0,input.length()-1) + "GMT-00:00";
        } else {
            int inset = 6;        
            String s0 = input.substring( 0, input.length() - inset );
            String s1 = input.substring( input.length() - inset, input.length() );
            input = s0 + "GMT" + s1;
        }        
        
        date = df.parse(input);
	}
	
	private void StringToCompleteDateHoursMinutesSeconds(String input) throws ParseException{
		format = "yyyy-MM-dd'T'HH:mmz";
		SimpleDateFormat df = new SimpleDateFormat(format);        
		//this is zero time so we need to add that TZ indicator for 
        if (input.endsWith("Z") ) {
            input = input.substring(0,input.length()-1) + "GMT-00:00";
        } else {
            int inset = 6;        
            String s0 = input.substring( 0, input.length() - inset );
            String s1 = input.substring( input.length() - inset, input.length() );
            input = s0 + "GMT" + s1;
        }        
        
        date = df.parse(input);
	}
	
	private void StringToComplete(String input) throws ParseException{
		format = "yyyy-MM-dd'T'HH:mm:ssz";
		SimpleDateFormat df = new SimpleDateFormat(format);        
		//this is zero time so we need to add that TZ indicator for 
        if (input.endsWith("Z") ) {
            input = input.substring(0,input.length()-1) + "GMT-00:00";
        } else {
            int inset = 6;        
            String s0 = input.substring( 0, input.length() - inset );
            String s1 = input.substring( input.length() - inset, input.length() );
            input = s0 + "GMT" + s1;
        }        
        
        date = df.parse(input);
	}
	
	public String DateToString(String format){
		if(date == null)
			return "";
		
		SimpleDateFormat df = new SimpleDateFormat(format);        
        
		if(format.endsWith("z")){
			String output = df.format( date );
	        int inset0 = 2;
	        String s0 = output.substring( 0, output.length() - inset0 );
	        String s1 = output.substring( output.length() - inset0, output.length() );
	        return s0 + ":" + s1;
		}
		else{
			return df.format(date);		
		}
	}
	
	public String DateToString(){
		return DateToString(format);
	}
	
	public String DateToCompleteString(){
		if(date == null)
			return "";
		
		TimeZone tz = TimeZone.getTimeZone("UTC");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz");
		df.setTimeZone(tz);
		String output = df.format( date );
		output = output.replace("UTC","Z");
		return output;
	}
	
	public Date getDate(){
		return date;
	}
	
}
