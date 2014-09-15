package eu.akka.saem.alfresco.seda;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 *   Catégories d'objets appartenant au formulaire
 * 
 * @Class         : FormCategory.java
 * @Package       : eu.akka.saem.alfresco.seda
 * @Date          : $Date: 25 févr. 2014 $:
 * @author        : $Author: THOMAS.POGNANT $
 * @version       : $Revision:  $:
 * @Id            : $Id: FormCategory.java $
 *
 */
public class FormCategory {
	private List<FormCategory> categories;
	private List<FormFields> fields;
	private String name;
	private String description;
	
	public FormCategory(){
		this.categories = new ArrayList<FormCategory>();
		this.fields = new ArrayList<FormFields>();
	}

	public List<FormCategory> getCategories() {
		return categories;
	}

	public void setCategories(List<FormCategory> categories) {
		this.categories = categories;
	}

	public List<FormFields> getFields() {
		return fields;
	}

	public void setFields(List<FormFields> fields) {
		this.fields = fields;
	}

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
