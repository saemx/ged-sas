package eu.akka.saem.alfresco.seda.form;

import java.util.LinkedHashMap;

import eu.akka.saem.alfresco.seda.loader.agape.AGAPEDescriptor;

/**
 * 
 *   Classe de génération du model pour le formulaire
 * 
 * @Class         : HTMLFormModelGenerator.java
 * @Package       : eu.akka.saem.alfresco.seda.form
 * @Date          : $Date: 25 févr. 2014 $:
 * @author        : $Author: THOMAS.POGNANT $
 * @version       : $Revision:  $:
 * @Id            : $Id: HTMLFormModelGenerator.java $
 *
 */
public class HTMLFormModelGenerator {
	public String result = "";
	
	private String HTMLGlobalTemplate = "<div class=\"saem-form-content-element saem-hover\" rel=\"##CHILDID##\">" +
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
										"<td class=\"saem-form-content-element-inf-label\">##FIELDLABEL##</td>" +
										"<td class=\"saem-form-content-element-inf-erreur\"></td>" +
									"</tr>" +
								"</tbody>" +
							"</table>" +
						"</div>" +						
					"</td>" +
				"</tr>" +
				"</tbody>" +
			"</table>"+
            "</div>";
	
	private String HTMLHelpTextTemplate = "<div class=\"saem-form-content-element-help\" style=\"display:none\">" +
			"<div class=\"saem-form-content-element-help-balloon\">" +
				"<div class=\"saem-form-content-element-help-closeButton\">x</div>" +
					"<div class=\"text\">" +
							"##HELPTEXTCONTENT##" +
					"</div>" +
					"<div class=\"saem-form-content-element-help-balloon-arrow\"></div>" +
				"</div>" +
			"</div>";
	
	
	private String HTMLHelpButtonTemplate = "<button type=\"button\" tabindex=\"0\" title=\"Aide\" style=\"\" class=\"saem-hover\"></button>";
	
	private String HTMLObligatoireTemplate = "<span class=\"saem-form-content-element-inf-obligatoire\"> *</span>";
	
	private String HTMLInputTextTemplate = "<input title=\"\" type=\"text\" style=\"width:100%\" ##EDITABLE## value=\"##VALUE##\">";
	
	private String HTMLEditableTemplate = "disabled=\"disabled\"";
	
	public HTMLFormModelGenerator(){
		
	}
	
	public HTMLFormModelGenerator(LinkedHashMap<String,String> formData, Object data, Object parentData, AGAPEDescriptor modelDescriptor, Object modelObject, Object parentModel, String key,Integer childId){
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


	

	protected void addChildId(Integer childId) {
		result = result.replace("##CHILDID##", childId.toString());
	}




	protected void addLabel(String label) {
		if(label == null || label.equals(""))
			result = result.replace("##FIELDLABEL##", "");
		else
			result = result.replace("##FIELDLABEL##", label);
	}


	protected void addHelp(String help) {
		if(help!=null && !help.equals(""))
			result = result.replace("##HELPBUTTON##", HTMLHelpButtonTemplate);
		else
			result = result.replace("##HELPBUTTON##", "");
		
		if(help!=null && !help.equals(""))
			result = result.replace("##HELPTEXT##", HTMLHelpTextTemplate);
		else
			result = result.replace("##HELPTEXT##", "");
		
		if(help!=null && !help.equals(""))
			result = result.replace("##HELPTEXTCONTENT##", help);
		else
			result = result.replace("##HELPTEXTCONTENT##", "");
		
	}
	
}
