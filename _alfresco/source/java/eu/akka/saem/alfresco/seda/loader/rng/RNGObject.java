package eu.akka.saem.alfresco.seda.loader.rng;


/**
 * 
 *   Objet java récupérés depuis l'application AGAPE
 * 
 * @Class         : RNGObject.java
 * @Package       : eu.akka.saem.alfresco.seda.loader.rng
 * @Date          : $Date: 25 févr. 2014 $:
 * @author        : $Author: THOMAS.POGNANT $
 * @version       : $Revision:  $:
 * @Id            : $Id: RNGObject.java $
 *
 */
public class RNGObject {
	private Object object;
	private RNGDescriptor descriptor;
	
	public RNGObject(RNGDescriptor descriptor,Object object){
		this.object = object;
		this.descriptor = descriptor;
	}
	
	
	public Object getObject() {
		return object;
	}
	
	public void setObject(Object object) {
		this.object = object;
	}
	
	public RNGDescriptor getDescriptor() {
		return descriptor;
	}
	
	public void setDescriptor(RNGDescriptor descriptor) {
		this.descriptor = descriptor;
	}
	
	public String toString(){
		return "";
	}
	
	
}
