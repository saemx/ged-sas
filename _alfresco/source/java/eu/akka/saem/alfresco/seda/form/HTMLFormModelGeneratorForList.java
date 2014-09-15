package eu.akka.saem.alfresco.seda.form;

import java.util.LinkedHashMap;

import eu.akka.saem.alfresco.seda.loader.agape.AGAPEDescriptor;

/**
 * 
 *   Classe de génération du model pour les listes du formulaire
 * 
 * @Class         : HTMLFormModelGeneratorForList.java
 * @Package       : eu.akka.saem.alfresco.seda.form
 * @Date          : $Date: 25 févr. 2014 $:
 * @author        : $Author: THOMAS.POGNANT $
 * @version       : $Revision:  $:
 * @Id            : $Id: HTMLFormModelGeneratorForList.java $
 *
 */
public class HTMLFormModelGeneratorForList extends HTMLFormModelGenerator {
	
	private String HTMLGlobalTemplate = "<div class=\"saem-form-content-element saem-hover\" rel=\"##CHILDID##\">" +
			"##HELPTEXT##"+
			"<table class=\"saem-form-content-element-e\"><tbody>" +
				"<tr>" +					
					"<td class=\"saem-form-content-element-e-col2\">" +
						"<div>" +
							"<table class=\"saem-form-content-element-inf\">" +
								"<tbody>" +
									"<tr>" +
										"<td class=\"saem-form-content-element-inf-label\">##FIELDLABEL##</td>" +
										"<td class=\"saem-form-content-element-inf-erreur\"></td>" +
										"<td class=\"saem-form-content-element-inf-list-del\">##DELBUTTON##</td>" +
									"</tr>" +
								"</tbody>" +
							"</table>" +
						"</div>" +						
					"</td>" +
				"</tr>" +
				"</tbody>" +
			"</table>"+
            "</div>";
	
	public HTMLFormModelGeneratorForList(LinkedHashMap<String,String> formData, Object data, Object parentData, AGAPEDescriptor modelDescriptor, Object modelObject, Object parentModel, String key,Integer childId){
		result = HTMLGlobalTemplate;
		
		if(data==null && modelObject == null){
			result = "";
			return;
		}
		
		String help = HTMLFormUtils.getHelp(modelDescriptor);
		String label = HTMLFormUtils.getLabel(formData);
		
		addHelp(help);
		addLabel(label);
		addChildId(childId);
	}		

}
