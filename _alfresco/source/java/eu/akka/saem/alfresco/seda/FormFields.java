package eu.akka.saem.alfresco.seda;

/**
 * 
 *   Constructeur des champs du formulaire
 * 
 * @Class         : FormFields.java
 * @Package       : eu.akka.saem.alfresco.seda
 * @Date          : $Date: 25 févr. 2014 $:
 * @author        : $Author: THOMAS.POGNANT $
 * @version       : $Revision:  $:
 * @Id            : $Id: FormFields.java $
 *
 */
public class FormFields {
	private String name;
	private String description;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
