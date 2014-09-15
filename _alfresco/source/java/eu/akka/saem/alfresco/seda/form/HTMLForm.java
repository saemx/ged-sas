package eu.akka.saem.alfresco.seda.form;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import eu.akka.saem.alfresco.seda.annotations.SEDA;
import eu.akka.saem.alfresco.seda.internationalisation.StructureMessageSEDA;
import eu.akka.saem.alfresco.seda.loader.agape.AGAPEDescriptor;
import eu.akka.saem.alfresco.seda.loader.agape.AGAPEObject;

/**
 * 
 *   Classe de génération du formulaire
 * 
 * @Class         : HTMLForm.java
 * @Package       : eu.akka.saem.alfresco.seda.form
 * @Date          : $Date: 25 févr. 2014 $:
 * @author        : $Author: THOMAS.POGNANT $
 * @version       : $Revision:  $:
 * @Id            : $Id: HTMLForm.java $
 *
 */
public class HTMLForm {
	
	private static HashMap<String,HTMLForm> listHTMLForm = new HashMap<String, HTMLForm>();
	
	private int counter = 0;
	
	private HashMap<String,Reference> formidsreference;
	public String formToken;
	private Integer idscounter = 0;
		
	private StructureMessageSEDA sms;
	private Object sedao;
	private AGAPEObject ao;
	public MODE currentMode;
	
	public enum MODE{
		READ,
		EDIT,
		NEW
	}
	
	public static HTMLForm getHTMLForm(String token){
		return listHTMLForm.get(token);
	}


	public HTMLForm(StructureMessageSEDA sms, Object sedao, AGAPEObject ao,MODE mode){
		formToken = Long.toString(new Date().getTime());
		listHTMLForm.put(formToken, this);
		
		this.sms = sms;
		this.sedao = sedao;
		this.ao = ao;
		this.formidsreference = new HashMap<String, Reference>();
		
		currentMode = mode;			

	}
	
	public Object getSedaObject(){
		return sedao;
	}
	
	public AGAPEObject getAGAPEObject(){
		return this.ao;
	}
	
	private class Reference{
		public Object data;
		public Object parentData;
		public LinkedHashMap<String,String> formData;
		public AGAPEDescriptor modelDescriptor;
		public Object modelObject;
		public Object parentModelObject;
		public String key;
		public Integer moduleId;
		public AGAPEDescriptor parentModelDescriptor;

		public Reference(Object data,Object parentData,LinkedHashMap<String,String> formData,AGAPEDescriptor modelDescriptor,AGAPEDescriptor parentModelDescriptor,Object modelObject,Object parentModelObject,String key,Integer moduleId){
			this.data = data;
			this.parentData = parentData;
			this.formData = formData;
			this.modelDescriptor = modelDescriptor;
			this.parentModelDescriptor = parentModelDescriptor;
			this.modelObject = modelObject;
			this.parentModelObject = parentModelObject;
			this.key = key;
			this.moduleId = moduleId;
		}
	}
	
	
	public String addIdsReference(Object data,Object parentData,LinkedHashMap<String,String> formData,AGAPEDescriptor modelDescriptor,AGAPEDescriptor parentModelDescriptor,Object modelObject,Object parentModelObject,String key,Integer parentModuleId){
		String id = formToken+"_"+idscounter;
		idscounter ++;
		
		formidsreference.put(id, new Reference(data,parentData,formData,modelDescriptor,parentModelDescriptor,modelObject,parentModelObject,key,parentModuleId));
		return id;
	}
	
		
	public Reference getIdsReference(String key){
		return formidsreference.get(key);
	}
	
	public void removeIdsReference(String key){
		this.formidsreference.remove(key);
	}
	
	public String editField(String id,String newValue){
		String result = "";
		Reference ref = getIdsReference(id);
		
		try{
			Method method = HTMLFormUtils.findSetterMethodByName(ref.parentData.getClass(), ref.key);
			
			if(method.getParameterTypes()[0].isEnum()){
				method.invoke(ref.parentData, HTMLFormUtils.findValueOnEnum(method.getParameterTypes()[0],newValue));
				result = "OK";
			}
			else if(method.getParameterTypes()[0].equals(String.class)){
				method.invoke(ref.parentData, newValue);
				result = "OK";
			}
			else{
				Method m = HTMLFormUtils.findGetterMethodByName(ref.parentData.getClass(), ref.key);
				Object resobject = m.invoke(ref.parentData);
				if(resobject.getClass().equals(ArrayList.class)){
					List<Object> objects = (List<Object>) m.invoke(ref.parentData);
					
					int index = objects.indexOf(ref.data);
					
					if(objects.get(index).getClass().isEnum()){
						objects.set(index, HTMLFormUtils.findValueOnEnum(objects.get(index).getClass(),newValue));
						result = "OK";
					}
					else if(objects.get(index).getClass().equals(String.class)){
						objects.set(index, newValue);
						result = "OK";
					}
					else if(ref.formData.containsKey("affValue")){
						Method m3 = HTMLFormUtils.findSetterMethodByName(objects.get(index).getClass(), ref.formData.get("affValue"));
						if(m3.getParameterTypes()[0].isEnum()){
							m3.invoke(objects.get(index), HTMLFormUtils.findValueOnEnum(m3.getParameterTypes()[0],newValue));
							result = "OK";
						}
						else if(m3.getParameterTypes()[0].equals(String.class)){
							m3.invoke(objects.get(index), newValue);
							result = "OK";
						}
						else{
							result = "NOK";
						}
					}
					else{
						result = "NOK";
					}
				}
				else if(ref.formData.containsKey("affValue")){
					Method m3 = HTMLFormUtils.findSetterMethodByName(resobject.getClass(), ref.formData.get("affValue"));
					if(m3.getParameterTypes()[0].isEnum()){
						m3.invoke(resobject, HTMLFormUtils.findValueOnEnum(m3.getParameterTypes()[0],newValue));
						result = "OK";
					}
					else if(m3.getParameterTypes()[0].equals(String.class)){
						m3.invoke(resobject, newValue);
						result = "OK";
					}
					else{
						result = "NOK";
					}
				}
				else{
					result = "NOK";
				}
				
			}
			
			
		}
		catch(Exception nsme){
			result = "NOK";
		}
		
		return result;
	}
	
	public class AddListResult{
		public String htmllistext;
		public List<String> globalhtml;
		public boolean canAlwaysAdd;
		
		public AddListResult(){
			this.globalhtml = new ArrayList<String>();
		}
	}
	
	public AddListResult addListItem(String id){
		AddListResult result = new AddListResult();
		
		Reference ref = getIdsReference(id);
		
		List<Object> o1 = null;
		List<Object> o2 = null;
		try{
			if(ref.parentData!=null){
				Method m = HTMLFormUtils.findGetterMethodByName(ref.parentData.getClass(), ref.key);
				o1 =  (List<Object>) m.invoke(ref.parentData);				
			}
			
			if(ref.parentModelObject!=null){
				Method m = HTMLFormUtils.findGetterMethodByName(ref.parentModelObject.getClass(), ref.key);
				o2 =  (List<Object>) m.invoke(ref.parentModelObject);				
			}	
			
			List<AGAPEDescriptor> ads = new ArrayList<AGAPEDescriptor>();	
			
			if(ref.parentModelDescriptor!=null){
				for(AGAPEDescriptor a: ref.parentModelDescriptor.getChilds()){
					if(a.getName().equals(ref.key))
						ads.add(a);
				}
			}
			
			HTMLFormListGenerator hflg = new HTMLFormListGenerator(ref.formData,o1,ref.parentData,ref.modelDescriptor,ads,ref.parentModelDescriptor, ref.modelObject, o2,ref.parentModelObject,ref.key,ref.moduleId,this);
			result.htmllistext = hflg.result;	
			result.globalhtml.addAll(hflg.listsresult);
			result.canAlwaysAdd = hflg.canAlwaysAdd(ref.formData,o1,ref.parentData,ref.modelDescriptor,ads,ref.parentModelDescriptor, ref.modelObject, o2,ref.parentModelObject,ref.key,ref.moduleId);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}

		return result;
	}
	
	public void removeItem(String id){
		Reference ref = getIdsReference(id);
		
		List<Object> o1 = null;
		try{
			if(ref.parentData!=null){
				Method m = HTMLFormUtils.findGetterMethodByName(ref.parentData.getClass(), ref.key);
				o1 =  (List<Object>) m.invoke(ref.parentData);
				
			}
			
		}
		catch(Exception ex){
			
		}
		
		int pos = o1.indexOf(ref.data);		
		ref.modelDescriptor.setLength(ref.modelDescriptor.getLength() - 1);
		o1.remove(pos);
	}
	
	public String fieldIsValide(String id){
		String result = "";
		
		return result;
	}	

	
	public String deleteListItem(String id){
		String result = "";
		
		return result;
	}
	
	public List<String> getHTML(){
		List<String> result = new ArrayList<String>();
		
		if(currentMode.equals(MODE.EDIT) || currentMode.equals(MODE.NEW)){
			SEDA annotation = (SEDA) sedao.getClass().getAnnotation(SEDA.class);
			sms.getInto(annotation.PropertyTerm());
			tranformElementToHTML(result,sms, sedao, ao.getDescriptor(),ao.getObject(),null,null);
		}
		else if(currentMode.equals(MODE.READ)){
			SEDA annotation = (SEDA) sedao.getClass().getAnnotation(SEDA.class);
			sms.getInto(annotation.PropertyTerm());
			tranformElementToHTML(result,sms, sedao, null,null,null,null);
		}

		
		return result;
	}
	
		
	private int generateModuleId(){
		return counter ++;
	}
	
	
	public Integer tranformElementToHTML(List<String> result,StructureMessageSEDA sms, Object sedao, AGAPEDescriptor ad, Object ao,Integer parentmoduleId,String type){
		LinkedHashMap<String,LinkedHashMap<String,String>> structure = sms.getCurrent();
		
		String res = "";
		int moduleId = generateModuleId();
		res += "<div class=\"saem-content\" moduleid=\""+moduleId+"\" ##PARENTMODULEID## ##TYPE## ##INIT## style=\"display: block;\">";
		
		if(type==null){
			res = res.replace("##TYPE##","");
		}
		else{
			res = res.replace("##TYPE##",type);
		}
		
		
		if(parentmoduleId!=null)
			res = res.replace("##PARENTMODULEID##", "parentmoduleid=\""+parentmoduleId+"\"");
		else
			res = res.replace("##PARENTMODULEID##", "");
		
		if(structure!=null){
		
			for(String key:structure.keySet()){
				
//				if(key.equals("TransferringAgencyArchiveIdentifier"))
//					System.out.println("test");
				if(key.equals("init")){
					res = res.replace("##INIT##","init=\"true\"");
				}
				else if(HTMLFormUtils.isField(key,sms,sedao,ad,ao)){			
					res += generateFieldHTML(key,sms,sedao,ad,ao,moduleId);
				}
				else if(HTMLFormUtils.isModel(key,sms,sedao,ad,ao)){
					res += generateModelHTML(result,key,sms,sedao,ad,ao,moduleId);					
				}
				else if(HTMLFormUtils.isList(key,sms,sedao,ad,ao)){
					res += generateListHTML(result,key,sms,sedao,ad,ao,moduleId);					
				}
				else{
					String t = null;
					if(HTMLFormUtils.isEntete(key,sms)){
						t = "entete=\"true\"";
					}
					else if(HTMLFormUtils.isDescription(key,sms)){
						t = "description=\"true\"";
					}
					else{
						t = null;
					}
					
					
					sms.getInto(key);
					tranformElementToHTML(result, sms, sedao, ad, ao,moduleId,t);
					sms.getOut();
				}
			}
			
		}
		
		res += "</div>";
		
		res = res.replace("##INIT##","");
		
		result.add(res);
		
		return moduleId;
	}


	private String generateListHTML(List<String> result,String key,StructureMessageSEDA sms, Object sedao, AGAPEDescriptor ad, Object ao,Integer moduleId) {
		String res = "";	
		
		//On crée le container
		try{
			List<Object> o1 = null;
			if(sedao!=null){
				Method m = HTMLFormUtils.findGetterMethodByName(sedao.getClass(), key);
				o1 =  (List<Object>) m.invoke(sedao);
			}
			
			List<Object> o2 = null;
			if(ao!=null){
				Method m2 = HTMLFormUtils.findGetterMethodByName(ao.getClass(), key);
				o2 = (List<Object>) m2.invoke(ao);
			}	
			
			List<AGAPEDescriptor> ads = new ArrayList<AGAPEDescriptor>();	
			
			if(ad!=null){
				for(AGAPEDescriptor a: ad.getChilds()){
					if(a.getName().equals(key))
						ads.add(a);
				}
			}
			
			if(o1 == null && o2 == null)
				return "";
			
			HTMLFormListGenerator hflg = new HTMLFormListGenerator(sms.getCurrent().get(key),o1,sedao,ads,ad, o2,ao,key,moduleId,this);
			res = hflg.result;	
			result.addAll(hflg.listsresult);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		
		return res;
	}

	private String generateModelHTML(List<String> result,String key,StructureMessageSEDA sms, Object sedao, AGAPEDescriptor ad, Object ao,Integer moduleId) {
		String res = "";		
		
		try{
			Object o1 = null;
			if(sedao!=null){
				Method m = HTMLFormUtils.findGetterMethodByName(sedao.getClass(), key);
				o1 =  m.invoke(sedao);
			}
			
			Object o2 = null;
			if(ao!=null){
				Method m2 = HTMLFormUtils.findGetterMethodByName(ao.getClass(), key);
				o2 = m2.invoke(ao);
			}	
			
			if(o1 == null && o2 == null)
				return "";

			sms.getInto(key);
			Integer childId = tranformElementToHTML(result, sms, o1, HTMLFormUtils.findChildDescriptor(ad,key,0), o2,moduleId,null);
			sms.getOut();
			
			HTMLFormModelGenerator hfmg = new HTMLFormModelGenerator(sms.getCurrent().get(key),o1,sedao,HTMLFormUtils.findChildDescriptor(ad, key, 0), o2,ao,key,childId);
			res = hfmg.result;	
			//res = res.replace("##ID##", addIdsReference(o1, sedao, sms.getCurrent().get(key), HTMLFormUtils.findChildDescriptor(ad, key, 0), o2, ao, key,moduleId));

						
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		
		return res;
	}

	private String generateFieldHTML(String key,StructureMessageSEDA sms, Object sedao,
			AGAPEDescriptor ad, Object ao,Integer moduleId) {
		String result = "";
		
		try{
			Object o1 = null;
			if(sedao!=null){
				Method m = HTMLFormUtils.findGetterMethodByName(sedao.getClass(), key);
				o1 =  m.invoke(sedao);
			}
			
			Object o2 = null;
			if(ao!=null){
				Method m = HTMLFormUtils.findGetterMethodByName(ao.getClass(), key);
				o2 =  m.invoke(ao);
			}
			
			result = new HTMLFormFieldGenerator(sms.getCurrent().get(key),o1,sedao,HTMLFormUtils.findChildDescriptor(ad, key, 0), o2,ao,key).result;
			result = result.replace("##IDINPUT##", "id=\""+addIdsReference(o1, sedao, sms.getCurrent().get(key), HTMLFormUtils.findChildDescriptor(ad, key, 0),ad, o2, ao, key,moduleId)+"\"");
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		
		return result;
	}

	



	
	

}
