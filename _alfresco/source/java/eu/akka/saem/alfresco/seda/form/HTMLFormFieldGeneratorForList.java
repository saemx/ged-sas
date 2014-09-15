package eu.akka.saem.alfresco.seda.form;

import java.util.LinkedHashMap;

import eu.akka.saem.alfresco.seda.loader.agape.AGAPEDescriptor;

/**
 * 
 *   Classe de génération des champs de formulaire pour les listes
 * 
 * @Class         : HTMLFormFieldGeneratorForList.java
 * @Package       : eu.akka.saem.alfresco.seda.form
 * @Date          : $Date: 25 févr. 2014 $:
 * @author        : $Author: THOMAS.POGNANT $
 * @version       : $Revision:  $:
 * @Id            : $Id: HTMLFormFieldGeneratorForList.java $
 *
 */
public class HTMLFormFieldGeneratorForList extends HTMLFormFieldGenerator{

	public HTMLFormFieldGeneratorForList(LinkedHashMap<String,String> formData, Object data, Object parentData, AGAPEDescriptor modelDescriptor, Object modelObject, Object parentModel, String key){
		result = HTMLGlobalTemplate;		
		
		
		String help = HTMLFormUtils.getHelp(modelDescriptor);
		String label = HTMLFormUtils.getLabel(formData);
		String affValue = HTMLFormUtils.getAffValue(formData);
		Object value = HTMLFormUtils.getValue(data,modelObject);
		String typeInput = HTMLFormUtils.getTypeInput(formData);
		boolean isObligatoire = HTMLFormUtils.isObligatoire(modelDescriptor);
		boolean isEditable = HTMLFormUtils.isEditable(modelObject,affValue);
		boolean affInMenu = HTMLFormUtils.isAffInMenu(formData);
		
		
		if(!isEditable && value==null){
			result = "";
			return;
		}
			
		addHelp(help);
		addLabel(label);
		addInput(typeInput,value,affValue);	
		addAffInMenu(affInMenu);		
		addEditable(isEditable);
		addObligatoire(isObligatoire);
	}

		
	private String HTMLGlobalTemplate = "<div class=\"saem-form-content-element\">" +
			"##HELPTEXT##"+
			"<table class=\"saem-form-content-element-e\"><tbody>" +
				"<tr>" +
					"<td class=\"saem-form-content-element-e-col1\">" +
							"##HELPBUTTON##" +
					"</td>" +
					"<td class=\"saem-form-content-element-e-col2\">" +
						"<div>" +
							"<table class=\"saem-form-content-element-inf\">" +
								"<tbody>" +
									"<tr>" +
										"<td class=\"saem-form-content-element-inf-label\">##FIELDLABEL## :##OBLIGATOIRE##</td>" +
										"<td class=\"saem-form-content-element-inf-erreur\"></td>" +
										"<td class=\"saem-form-content-element-inf-list-del\">##DELBUTTON##</td>" +
									"</tr>" +
								"</tbody>" +
							"</table>" +
						"</div>" +
						"<div class=\"saem-form-content-element-value\">" +
							"##INPUT##" +
						"</div>" +
					"</td>" +
				"</tr>" +
				"</tbody>" +
			"</table>"+
            "</div>";

}
