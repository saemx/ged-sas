package eu.akka.saem.alfresco.seda.loader.agape;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 
 *   Description du contenu des objets AGAPE récupérés dans l'application
 * 
 * @Class         : AGAPEDescriptor.java
 * @Package       : eu.akka.saem.alfresco.seda.loader.agape
 * @Date          : $Date: 25 févr. 2014 $:
 * @author        : $Author: THOMAS.POGNANT $
 * @version       : $Revision:  $:
 * @Id            : $Id: AGAPEDescriptor.java $
 *
 */
public class AGAPEDescriptor  implements Serializable {

	private static final long serialVersionUID = -4959911599344727426L;
	private Integer maxOccurs;
	private Integer minOccurs;
	private Integer length;
	private AGAPEType type;
	private String Comment;
	private List<AGAPEDescriptor> childs;
	
	private String name;
	
	public AGAPEDescriptor(String name){
		this.name = name;
		this.type = AGAPEType.ELEMENT;
		this.setMaxOccurs(1);
		this.setMinOccurs(1);
		this.length = 1;
		this.Comment = "";
	}
	
	public AGAPEDescriptor(String name, AGAPEType type){
		this.name = name;
		this.type = type;
		this.setMaxOccurs(1);
		this.setMinOccurs(1);
		this.length = 1;
		this.Comment = "";
	}
	
	public AGAPEDescriptor(String name,Integer maxOccurs,Integer minOccurs){
		this.name = name;
		this.setMaxOccurs(maxOccurs);
		this.setMinOccurs(minOccurs);
		this.length = minOccurs;
		this.Comment = "";
	}
	
	public AGAPEDescriptor(String name,Integer maxOccurs,Integer minOccurs,Integer length, String comment){
		this.name = name;
		this.setMaxOccurs(maxOccurs);
		this.setMinOccurs(minOccurs);
		this.length = length;
		this.Comment = comment;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<AGAPEDescriptor> getChilds() {
		return childs;
	}

	public void setChilds(List<AGAPEDescriptor> childs) {
		this.childs = childs;
	}	
	
	public void addChilds(AGAPEDescriptor child){
		if(this.childs == null)
			this.childs = new ArrayList<AGAPEDescriptor>();
		
		this.childs.add(child);
	}

	public AGAPEType getType() {
		return type;
	}

	public void setType(AGAPEType type) {
		this.type = type;
	}

	public Integer getMaxOccurs() {
		return maxOccurs;
	}

	public void setMaxOccurs(Integer maxOccurs) {
		this.maxOccurs = maxOccurs;
	}

	public Integer getMinOccurs() {
		return minOccurs;
	}

	public void setMinOccurs(Integer minOccurs) {
		this.minOccurs = minOccurs;
		this.length = minOccurs;
	}

	public String getComment() {
		return Comment;
	}

	public void setComment(String comment) {
		Comment = comment;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public JSONObject toJSON() {
		JSONObject o = new JSONObject();
		try {
			o.put("maxOccurs",maxOccurs);
		
		o.put("minOccurs",minOccurs);
		o.put("length",length);
		o.put("name",name);
		//o.put("type",type.toString());
		o.put("Comment",Comment);
		
		JSONArray array = new JSONArray();
		if(childs!=null){
		for(AGAPEDescriptor a:childs)
			array.put(a.toJSON());
		}
		
		o.put("childs", array);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return o;
	}

	public static AGAPEDescriptor JSONToObject(JSONObject object) {
		AGAPEDescriptor a = null;
		try {
			a = new AGAPEDescriptor(object.getString("name"),object.getInt("maxOccurs"),object.getInt("minOccurs"),object.getInt("length"),object.getString("Comment"));
			JSONArray array = object.getJSONArray("childs");
			for(int i = 0;i<array.length();i++){
				a.addChilds(AGAPEDescriptor.JSONToObject(array.getJSONObject(i)));
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return a;
	}
}
