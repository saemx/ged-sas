package eu.akka.saem.alfresco.seda.loader.agape;

import org.json.JSONException;
import org.json.JSONObject;

import eu.akka.saem.alfresco.seda.loader.JSONLoader;
import eu.akka.saem.alfresco.seda.writer.SEDAWriter;


/**
 * 
 *   Objet java récupérés depuis l'application AGAPE
 * 
 * @Class         : AGAPEObject.java
 * @Package       : eu.akka.saem.alfresco.seda.loader.agape
 * @Date          : $Date: 25 févr. 2014 $:
 * @author        : $Author: THOMAS.POGNANT $
 * @version       : $Revision:  $:
 * @Id            : $Id: AGAPEObject.java $
 *
 */
public class AGAPEObject {

	private static final long serialVersionUID = 2821215568982629674L;
	private Object object;
	private AGAPEDescriptor descriptor;
	
	public AGAPEObject(AGAPEDescriptor descriptor,Object object){
		this.object = object;
		this.descriptor = descriptor;
	}
	
	public Object getObject() {
		return object;
	}
	
	public void setObject(Object object) {
		this.object = object;
	}
	
	public AGAPEDescriptor getDescriptor() {
		return descriptor;
	}
	
	public void setDescriptor(AGAPEDescriptor descriptor) {
		this.descriptor = descriptor;
	}
	
	public String toString(){		
		return "";
	}
	
	public String toJSON(){
		JSONObject o = new JSONObject();
		try {
			o.put("object", SEDAWriter.transformSEDAObjectToJSON(object));
		
		o.put("descriptor", descriptor.toJSON());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return o.toString();		
	}
	
	public static AGAPEObject JSONToObject(String json){
		try {
			JSONObject o = new JSONObject(json);
			AGAPEObject ao = new AGAPEObject(AGAPEDescriptor.JSONToObject((JSONObject) o.get("descriptor")), JSONLoader.SEDALoader((JSONObject)o.get("object")));
			return ao;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
