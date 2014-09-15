package eu.akka.saem.alfresco.seda.form;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;

import eu.akka.saem.alfresco.seda.annotations.SEDA;
import eu.akka.saem.alfresco.seda.loader.agape.AGAPEDescriptor;

/**
 * 
 *   Classe de génération des champs du formulaire
 * 
 * @Class         : HTMLFormFieldGenerator.java
 * @Package       : eu.akka.saem.alfresco.seda.form
 * @Date          : $Date: 25 févr. 2014 $:
 * @author        : $Author: THOMAS.POGNANT $
 * @version       : $Revision:  $:
 * @Id            : $Id: HTMLFormFieldGenerator.java $
 *
 */
public class HTMLFormFieldGenerator {
	
	public String result = "";
	
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
	
	
	private String HTMLInputTextTemplate = "<input ##AFFINMENU## ##IDINPUT## ##OBLIGATOIREFIELD## title=\"\" formType=\"text\" type=\"text\" style=\"width:100%\" ##EDITABLE## value=\"##VALUE##\">";
	
	private String HTMLInputFileTemplate = "<input formType=\"file\" ##OBLIGATOIREFIELD## ##IDINPUT## type=\"text\" value=\"##VALUE##\" disabled=\"disabled\"><div><iframe src=\"/share/proxy/alfresco/components/form/upload\" width=\"400\" height=\"60\" frameborder=\"0\"></iframe></div>";
	
	private String HTMLInputTextareaTemplate = "<textarea ##AFFINMENU## ##IDINPUT## ##OBLIGATOIREFIELD## formType=\"textearea\" style=\"width:100%\" ##EDITABLE##>##VALUE##</textarea>";
		
	private String HTMLInputDateTemplate = "<input ##AFFINMENU## formType=\"date\" title=\"\" ##OBLIGATOIREFIELD## ##IDINPUT## type=\"text\" ##EDITABLE##  value=\"##VALUE##\">";
	
	private String HTMLInputComboTemplate = "<select ##AFFINMENU## formType=\"combobox\" ##OBLIGATOIREFIELD## ##IDINPUT##  ##EDITABLE## >##SELECTOPTION##</select>";
	
	private String HTMLEditableTemplate = "disabled=\"disabled\"";
	
	public HTMLFormFieldGenerator(){		
	}
	
	public HTMLFormFieldGenerator(LinkedHashMap<String,String> formData, Object data, Object parentData, AGAPEDescriptor modelDescriptor, Object modelObject, Object parentModel, String key){
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

	protected void addAffInMenu(boolean affInMenu) {
		if(affInMenu)	
			result = result.replace("##AFFINMENU##", "affInMenu=\"true\"");
		else
			result = result.replace("##AFFINMENU##", "");
	}

	protected void addObligatoire(boolean isObligatoire) {
		if(isObligatoire){
			result = result.replace("##OBLIGATOIRE##", HTMLObligatoireTemplate);
			result = result.replace("##OBLIGATOIREFIELD##","obligatoire=\"obligatoire\"");
		}
		else{
			result = result.replace("##OBLIGATOIRE##", "");
			result = result.replace("##OBLIGATOIREFIELD##","");
		}
	}

	protected void addEditable(boolean isEditable) {
		if(!isEditable){
			result = result.replace("##EDITABLE##", HTMLEditableTemplate);
		}
		else{
			result = result.replace("##EDITABLE##", "");
		}		
	}

	protected void addInput(String typeInput,Object value,String affValue) {
		if(typeInput==null)
			return ;
		
		if(typeInput.equals("TEXT")){
			result = result.replace("##INPUT##", HTMLInputTextTemplate);
			if(value!=null && !value.equals("")){
				if(value.getClass().equals(String.class)){
//					if(value.equals("Accusés de réception du dossier de convocation")){
//						System.out.println("here");
//					}
					result = result.replace("##VALUE##", (String) value);
				}
				else if(affValue!=null){
					try {
						Method m = findGetterMethodByName(value.getClass(), affValue);
						result = result.replace("##VALUE##", (String) m.invoke(value));
					} catch (Exception e) {
						result = result.replace("##VALUE##", "");
						//e.printStackTrace();
					}
					
				}
				else{
					result = result.replace("##VALUE##", "");//TODO	
				}
			}
			else
				result = result.replace("##VALUE##", "");
		}
		else if(typeInput.equals("FILE")){
			result = result.replace("##INPUT##", HTMLInputFileTemplate);
			if(value!=null && !value.equals("")){
				if(value.getClass().equals(String.class))
					result = result.replace("##VALUE##", (String) value);
				else if(affValue!=null){
					try {
						Method m = findGetterMethodByName(value.getClass(), affValue);
						result = result.replace("##VALUE##", (String) m.invoke(value));
					} catch (Exception e) {
						result = result.replace("##VALUE##", "");
						//e.printStackTrace();
					}					
				}
				else{
					result = result.replace("##VALUE##", "");//TODO	
				}
			}
			else
				result = result.replace("##VALUE##", "");
		}
		else if(typeInput.equals("TEXTAREA")){
			result = result.replace("##INPUT##", HTMLInputTextareaTemplate);
			if(value!=null && !value.equals("")){
				if(value.getClass().equals(String.class))
					result = result.replace("##VALUE##", (String) value);
				else if(affValue!=null){
					try {
						Method m = findGetterMethodByName(value.getClass(), affValue);
						result = result.replace("##VALUE##", (String) m.invoke(value));
					} catch (Exception e) {
						result = result.replace("##VALUE##", "");
						//e.printStackTrace();
					}
					
				}
				else{
					result = result.replace("##VALUE##", "");//TODO	
				}
			}
			else
				result = result.replace("##VALUE##", "");
		}
		else if(typeInput.equals("DATE")){
			result = result.replace("##INPUT##", HTMLInputDateTemplate);
			if(affValue!=null){
				try {
					Method m = findGetterMethodByName(value.getClass(), affValue);
					result = result.replace("##VALUE##", (String) m.invoke(value));
				} catch (Exception e) {
						result = result.replace("##VALUE##", "");
						////e.printStackTrace();
				}
			}
		}
		else if(typeInput.equals("COMBOBOX")){
			result = result.replace("##INPUT##", HTMLInputComboTemplate);
				try {
					Method m = findGetterMethodByName(value.getClass(), affValue);
					
					String val = findValueWithEnum(m.invoke(value).getClass(), m.invoke(value));
					
					result = result.replace("##SELECTOPTION##", constructSelectBox(m.invoke(value).getClass(),val));
				} catch (Exception e) {
					result = result.replace("##SELECTOPTION##", "");
					
				}
			
		}
		else
			result = result.replace("##INPUT##", "");			
	}
	
	private Method findGetterMethodByName(Class<?> obclass, String name) throws NoSuchMethodException{
		for(Method m:obclass.getMethods()){
			SEDA sedaAnnotations = m.getAnnotation(SEDA.class);
			
			if(sedaAnnotations == null)
				break;
			
			if(sedaAnnotations.PropertyTerm().equals(name)){
				if(m.getName().startsWith("get"))
						return m;				
			}
		}
		
		throw new NoSuchMethodException("set"+name+" ou add"+name+" est introuvable dans la classe "+ obclass.getName());
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
	
	private static String findValueWithEnum(Class<?> class1, Object obj) throws InstantiationException, IllegalAccessException {
		
		for(Field o:class1.getFields()){
			SEDA sedaAnnotation = o.getAnnotation(SEDA.class);
			if(sedaAnnotation != null && o.get(class1).equals(obj))
				return sedaAnnotation.PropertyTerm();			
		}
		
		return "";
	}
	
	private String constructSelectBox(Class<?> class1, String value) throws InstantiationException, IllegalAccessException {
		boolean selected = false;
		String result = "";
		for(Field o:class1.getFields()){
			SEDA sedaAnnotation = o.getAnnotation(SEDA.class);
			
			String sep = " - ";
			
			if(sedaAnnotation.PropertyTerm().equals(""))
				sep = "";
			
			
			if(sedaAnnotation != null && sedaAnnotation.PropertyTerm().equals(value)){
				selected = true;
				result += "<option selected=\"selected\" value=\""+sedaAnnotation.PropertyTerm()+"\">"+sedaAnnotation.PropertyTerm() + sep + sedaAnnotation.FormName() + "</option>";
			}
			else if(sedaAnnotation!=null && !selected && sedaAnnotation.PropertyTerm().equals("")){
				result += "<option selected=\"selected\" value=\""+sedaAnnotation.PropertyTerm()+"\">"+sedaAnnotation.PropertyTerm() + sep + sedaAnnotation.FormName() + "</option>";
			}
			else if(sedaAnnotation != null){
				result += "<option value=\""+sedaAnnotation.PropertyTerm()+"\">"+sedaAnnotation.PropertyTerm() + sep + sedaAnnotation.FormName() + "</option>";
			}			
		}
		
		return result;
	}
}
