package eu.akka.saem.alfresco.seda;

import eu.akka.saem.alfresco.seda.v02.complexType.DocumentType;

/**
 * 
 *   Bean pour les documents SEDA
 * 
 * @Class         : SEDADocument.java
 * @Package       : eu.akka.saem.alfresco.seda
 * @Date          : $Date: 25 févr. 2014 $:
 * @author        : $Author: THOMAS.POGNANT $
 * @version       : $Revision:  $:
 * @Id            : $Id: SEDADocument.java $
 *
 */
public class SEDADocument {

	private String description;
	private String name;
	private DocumentType SEDARelatedObject;
	

	public SEDADocument(){
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public DocumentType getSEDARelatedObject() {
		return SEDARelatedObject;
	}
	
	public void setSEDARelatedObject(DocumentType dt) {
		this.SEDARelatedObject = dt;
	}

}
