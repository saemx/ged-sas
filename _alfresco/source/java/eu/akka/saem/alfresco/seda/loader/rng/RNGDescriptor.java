package eu.akka.saem.alfresco.seda.loader.rng;

import java.util.ArrayList;
import java.util.List;

/**
 * Description du contenu des objets RNG récupérés dans l'application
 * 
 * @Class         : RNGDescriptor.java
 * @Package       : eu.akka.saem.alfresco.seda.loader.rng
 * @Date          : $Date: 25 févr. 2014 $:
 * @author        : $Author: THOMAS.POGNANT $
 * @version       : $Revision:  $:
 * @Id            : $Id: RNGDescriptor.java $
 *
 */
public class RNGDescriptor {
	private Integer maxOccurs;
	private Integer minOccurs;
	private Integer length;
	private XMLType type;
	private List<RNGDescriptor> childs;
	
	private String name;
	
	public RNGDescriptor(String name){
		this.name = name;
		this.type = XMLType.ELEMENT;
		this.setMaxOccurs(1);
		this.setMinOccurs(1);
		this.length = 1;
	}
	
	public RNGDescriptor(String name, XMLType type){
		this.name = name;
		this.type = type;
		this.setMaxOccurs(1);
		this.setMinOccurs(1);
		this.length = 1;
	}
	
	public RNGDescriptor(String name,Integer maxOccurs,Integer minOccurs){
		this.name = name;
		this.setMaxOccurs(maxOccurs);
		this.setMinOccurs(minOccurs);
		this.length = 1;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<RNGDescriptor> getChilds() {
		return childs;
	}

	public void setChilds(List<RNGDescriptor> childs) {
		this.childs = childs;
	}	
	
	public void addChilds(RNGDescriptor child){
		if(this.childs == null)
			this.childs = new ArrayList<RNGDescriptor>();
		
		this.childs.add(child);
	}

	public XMLType getType() {
		return type;
	}

	public void setType(XMLType type) {
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
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}
}
