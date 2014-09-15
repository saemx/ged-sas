package eu.akka.saem.alfresco.seda.form;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import eu.akka.saem.alfresco.seda.internationalisation.StructureMessageSEDA;
import eu.akka.saem.alfresco.seda.loader.XMLLoader;
import eu.akka.saem.alfresco.seda.loader.agape.AGAPEDescriptor;
import eu.akka.saem.alfresco.seda.writer.SEDAWriter;

/**
 * 
 *   Classe de génération des listes pour le formulaire
 * 
 * @Class         : HTMLFormListGenerator.java
 * @Package       : eu.akka.saem.alfresco.seda.form
 * @Date          : $Date: 25 févr. 2014 $:
 * @author        : $Author: THOMAS.POGNANT $
 * @version       : $Revision:  $:
 * @Id            : $Id: HTMLFormListGenerator.java $
 *
 */
public class HTMLFormListGenerator {
public String result = "";
public List<String> listsresult = new ArrayList<String>();
	
	private String HTMLGlobalTemplate = "<div class=\"saem-form-content-element-list\">" +
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
										"<td class=\"saem-form-content-element-inf-list-add\">##ADDITEM##</td>" +
									"</tr>" +
								"</tbody>" +
							"</table>" +
						"</div>" +
						"<div class=\"saem-form-content-element-list-value\">" +
						"##ITEMEMPLACEMENT##" + 
						"</div>" +
					"</td>" +
				"</tr>" +
				"</tbody>" +
			"</table>"+
            "</div>";
	
	private String HTMLItemEmplacement = "<div class=\"saem-form-content-element-list-element\" style=\"display: block;\" ##RELITEM## >##LABELITEM##</div>";
	
	private String HTMLAddButtonTemplate = "<a class=\"saem-form-content-element-inf-list-add-button\"  ##IDLIST##  href=\"#\"></a>";
	private String HTMLAddButtonTemplateHide = "<a class=\"saem-form-content-element-inf-list-add-button\" style=\"display:none\"  ##IDLIST##  href=\"#\"></a>";
	
	private String HTMLHelpTextTemplate = "<div class=\"saem-form-content-element-help\" style=\"display:none\">" +
			"<div class=\"saem-form-content-element-help-balloon\">" +
				"<div class=\"saem-form-content-element-help-closeButton\">x</div>" +
					"<div class=\"text\">" +
							"##HELPTEXTCONTENT##" +
					"</div>" +
					"<div class=\"saem-form-content-element-help-balloon-arrow\"></div>" +
				"</div>" +
			"</div>";
	
	private String HTMLAddListButtonTemplate = "<a class=\"saem-form-content-element-inf-list-add-button\" id=\"addListItem\" href=\"#\"> </a>";
	
	
	private String HTMLHelpButtonTemplate = "<button type=\"button\" tabindex=\"0\" title=\"Aide\" style=\"\" class=\"saem-hover\"></button>";
	
	private void videList(List<Object> data){
		if(data != null){
			data.clear();
		}		
	}
	
	public HTMLFormListGenerator(LinkedHashMap<String,String> formData, List<Object> data, Object parentData, List<AGAPEDescriptor> modelDescriptor,AGAPEDescriptor parentModelDescriptor, List<Object> modelObject, Object parentModel, String key,Integer moduleId,HTMLForm form){
		result = "";
		try{
			if(form.currentMode.equals(HTMLForm.MODE.READ)){
				result = HTMLGlobalTemplate;
				addHelp(null);
				canAddList(false);
				addLabel(HTMLFormUtils.getListLabel(formData));				
				
				for(Object d:data){
					
					if(formData.containsKey("type")){
						HTMLFormFieldGeneratorForList hffgfl = new HTMLFormFieldGeneratorForList(formData, d, parentData, null, null, null, key);
						result = result.replace("##ITEMEMPLACEMENT##", hffgfl.result+"##ITEMEMPLACEMENT##");
						result = result.replace("##DELBUTTON##", "");
					}
					else{
						StructureMessageSEDA sms = new StructureMessageSEDA();
						sms.getInto(formData);
						Integer childId = form.tranformElementToHTML(listsresult, sms, d, null, null,moduleId,null);
						sms.getOut();
						
						HTMLFormModelGeneratorForList hffgfl = new HTMLFormModelGeneratorForList(formData, d, parentData, null, null, null, key,childId);
						result = result.replace("##ITEMEMPLACEMENT##", hffgfl.result+"##ITEMEMPLACEMENT##");
						result = result.replace("##DELBUTTON##", "");						
					}					
				}				
				result = result.replace("##ITEMEMPLACEMENT##", "");				
			}
			else if(form.currentMode.equals(HTMLForm.MODE.NEW)){
				data.clear();
				
				int nummodel = 0;
				for(AGAPEDescriptor a:modelDescriptor){
					result += HTMLGlobalTemplate;
					addHelp(HTMLFormUtils.getHelp(a));
					addLabel(HTMLFormUtils.getListLabel(formData));	
					if(a.getMinOccurs().equals(a.getMaxOccurs())){
						canAddList(false);
					}
					else{
						canAddList(true);
						result = result.replace("##IDLIST##", "id=\""+form.addIdsReference(data, parentData, formData, a,parentModelDescriptor, modelObject.get(nummodel), parentModel, key,moduleId)+"\"");
					}
					
					for(Integer i = 0; i < a.getMinOccurs(); i++){
						String serialiElement = SEDAWriter.transformSEDAObjectToXML(modelObject.get(nummodel));
						InputStream is = new ByteArrayInputStream(serialiElement.getBytes());
						Object d = XMLLoader.SEDALoader(is);
						data.add(d);
						
						if(formData.containsKey("type")){
							if(formData.get("type").equals("file"))
								System.out.println("azeazeaeaze");
							
							HTMLFormFieldGeneratorForList hffgfl = new HTMLFormFieldGeneratorForList(formData, d, parentData, a, modelObject.get(nummodel), parentModel, key);
							result = result.replace("##ITEMEMPLACEMENT##", hffgfl.result+"##ITEMEMPLACEMENT##");
							result = result.replace("##IDINPUT##", "id=\""+form.addIdsReference(d, parentData, formData, a,parentModelDescriptor, modelObject.get(nummodel), parentModel, key,moduleId)+"\"");
							result = result.replace("##DELBUTTON##", "");
						}
						else{
							StructureMessageSEDA sms = new StructureMessageSEDA();
							sms.getInto(formData);
							Integer childId = form.tranformElementToHTML(listsresult, sms, d, a, modelObject.get(nummodel),moduleId,null);
							sms.getOut();
							
							HTMLFormModelGeneratorForList hffgfl = new HTMLFormModelGeneratorForList(formData, d, parentData, a, modelObject.get(nummodel), parentModel, key,childId);
							result = result.replace("##ITEMEMPLACEMENT##", hffgfl.result+"##ITEMEMPLACEMENT##");
							result = result.replace("##DELBUTTON##", "");						
						}
					}	

					result = result.replace("##ITEMEMPLACEMENT##", "");						
					nummodel ++;										
				}
					
			}
			else if(form.currentMode.equals(HTMLForm.MODE.EDIT)){
				int nummodel = 0;
				int counter = 0;
				for(AGAPEDescriptor a:modelDescriptor){
					result += HTMLGlobalTemplate;
					addHelp(HTMLFormUtils.getHelp(a));
					addLabel(HTMLFormUtils.getListLabel(formData));	
					if(a.getMinOccurs().equals(a.getMaxOccurs())){
						canAddList(false);
						result = result.replace("##IDLIST##", "id=\""+form.addIdsReference(data, parentData, formData, a,parentModelDescriptor, modelObject.get(nummodel), parentModel, key,moduleId)+"\"");
					}
					else{
						canAddList(true);
						result = result.replace("##IDLIST##", "id=\""+form.addIdsReference(data, parentData, formData, a,parentModelDescriptor, modelObject.get(nummodel), parentModel, key,moduleId)+"\"");
					}
					
					for(Integer i = 0; i < a.getLength(); i++){
						Object d = data.get(counter);
						counter ++;
						
						if(formData.containsKey("type")){
							if(formData.get("type").equals("file"))
								System.out.println("azeazeaeaze");
							
							HTMLFormFieldGeneratorForList hffgfl = new HTMLFormFieldGeneratorForList(formData, d, parentData, a, modelObject.get(nummodel), parentModel, key);
							result = result.replace("##ITEMEMPLACEMENT##", hffgfl.result+"##ITEMEMPLACEMENT##");
							result = result.replace("##IDINPUT##", "id=\""+form.addIdsReference(d, parentData, formData, a,parentModelDescriptor, modelObject.get(nummodel), parentModel, key,moduleId)+"\"");
							
							if(i < a.getMinOccurs())
								result = result.replace("##DELBUTTON##", "");
							else
								result = result.replace("##DELBUTTON##", "<a class=\"saem-form-content-element-inf-list-del-button saem-hover\" id=\""+form.addIdsReference(d, parentData, formData, a,parentModelDescriptor, modelObject.get(nummodel), parentModel, key,moduleId)+"\" href=\"#\"> </a>");

						}
						else{
							StructureMessageSEDA sms = new StructureMessageSEDA();
							sms.getInto(formData);
							Integer childId = form.tranformElementToHTML(listsresult, sms, d, a, modelObject.get(nummodel),moduleId,null);
							sms.getOut();
							
							HTMLFormModelGeneratorForList hffgfl = new HTMLFormModelGeneratorForList(formData, d, parentData, a, modelObject.get(nummodel), parentModel, key,childId);
							result = result.replace("##ITEMEMPLACEMENT##", hffgfl.result+"##ITEMEMPLACEMENT##");
							if(i < a.getMinOccurs())
								result = result.replace("##DELBUTTON##", "");
							else
								result = result.replace("##DELBUTTON##", "<a class=\"saem-form-content-element-inf-list-del-button saem-hover\" id=\""+form.addIdsReference(d, parentData, formData, a,parentModelDescriptor, modelObject.get(nummodel), parentModel, key,moduleId)+"\" href=\"#\"> </a>");
				
						}
					}	

					result = result.replace("##ITEMEMPLACEMENT##", "");						
					nummodel ++;										
				}
					
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
		}		
	}
	
	
	
	private Object cloneObject(Object o) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SecurityException, NoSuchMethodException, ParserConfigurationException, SAXException, IOException{
		String res = SEDAWriter.transformSEDAObjectToXML(o);
		InputStream in = new ByteArrayInputStream(res.getBytes()); 
		return XMLLoader.SEDALoader(in);
	}
	
	
	public HTMLFormListGenerator(LinkedHashMap<String, String> formData,
			List<Object> o1, Object parentData,
			AGAPEDescriptor modelDescriptor, List<AGAPEDescriptor> ads,
			AGAPEDescriptor parentModelDescriptor,Object modelObject, List<Object> o2,
			Object parentModelObject, String key, Integer moduleId,
			HTMLForm htmlForm) {
		result = "";
		try{
			String serialiElement = SEDAWriter.transformSEDAObjectToXML(modelObject);
			InputStream is = new ByteArrayInputStream(serialiElement.getBytes());
			Object d = XMLLoader.SEDALoader(is);
			
			int index = 0;
			for(AGAPEDescriptor a:ads){
				if(a.equals(modelDescriptor)){
					index += a.getLength();
					break;
				}
				else{
					index += a.getLength();
				}
			}
			
			
			o1.add(index,d);
			
			modelDescriptor.setLength(modelDescriptor.getLength() + 1);
						
			if(formData.containsKey("type")){
				HTMLFormFieldGeneratorForList hffgfl = new HTMLFormFieldGeneratorForList(formData, d, parentData, modelDescriptor, modelObject, parentModelObject, key);
				result = hffgfl.result;
				result = result.replace("##IDINPUT##", "id=\""+htmlForm.addIdsReference(d, parentData, formData, modelDescriptor,parentModelDescriptor, modelObject, parentModelObject, key,moduleId)+"\"");
				result = result.replace("##DELBUTTON##", "<a class=\"saem-form-content-element-inf-list-del-button saem-hover\" id=\""+htmlForm.addIdsReference(d, parentData, formData, modelDescriptor,parentModelDescriptor, modelObject, parentModelObject, key,moduleId)+"\" href=\"#\"> </a>");
			}
			else{
				StructureMessageSEDA sms = new StructureMessageSEDA();
				sms.getInto(formData);
				Integer childId = htmlForm.tranformElementToHTML(listsresult, sms, d, modelDescriptor, modelObject,moduleId,null);
				sms.getOut();
					
				HTMLFormModelGeneratorForList hffgfl = new HTMLFormModelGeneratorForList(formData, d, parentData, modelDescriptor, modelObject, parentModelObject, key,childId);
				result = hffgfl.result;
				result = result.replace("##DELBUTTON##", "<a class=\"saem-form-content-element-inf-list-del-button saem-hover\" id=\""+htmlForm.addIdsReference(d, parentData, formData, modelDescriptor,parentModelDescriptor, modelObject, parentModelObject, key,moduleId)+"\" href=\"#\"> </a>");						
			}			
		}
		catch(Exception ex){
			ex.printStackTrace();
		}	
	}

	protected String addId(HTMLForm form,String key, StructureMessageSEDA sms,Object sedao, AGAPEDescriptor ad, Object ao,Integer indexInObject, Integer indexInObjectModel,Integer indexModel,Integer childId) {
		String id = "45";//form.addIdsReferenceList(key,sms,sedao, ad, ao,indexInObject, indexInObjectModel,indexModel,childId);
		
		result = result.replace("##IDLIST##", "id=\""+id+"\"");	
		return id;
	}

	private void canAddList(boolean canadd) {
		if(canadd){
			result = result.replace("##ADDITEM##", HTMLAddButtonTemplate);
		}
		else{
			result = result.replace("##ADDITEM##", HTMLAddButtonTemplateHide);
		}		
	}

	private void addListLabel(String  label) {
		if(label == null || label.equals(""))
			result = result.replace("##FIELDLABEL##", "");
		else
			result = result.replace("##FIELDLABEL##", label);		
	}

	private void addChildId(Integer childId) {
		result = result.replace("##CHILDID##", childId.toString());
	}

	
	private void addLabel(String label) {
		if(label == null || label.equals(""))
			result = result.replace("##FIELDLABEL##", "");
		else
			result = result.replace("##FIELDLABEL##", label);
	}


	private void addHelp(String help) {
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

	public boolean canAlwaysAdd(LinkedHashMap<String, String> formData,
			List<Object> o1, Object parentData,
			AGAPEDescriptor modelDescriptor, List<AGAPEDescriptor> ads,
			AGAPEDescriptor parentModelDescriptor, Object modelObject,
			List<Object> o2, Object parentModelObject, String key,
			Integer moduleId) {
		
		if(modelDescriptor.getLength() == modelDescriptor.getMaxOccurs())
			return false;		
		return true;
	}
	
}
