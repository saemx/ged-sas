package eu.akka.saem.alfresco.seda.form;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;

import eu.akka.saem.alfresco.seda.annotations.SEDA;
import eu.akka.saem.alfresco.seda.internationalisation.StructureMessageSEDA;
import eu.akka.saem.alfresco.seda.loader.agape.AGAPEDescriptor;

/**
 * 
 * 
 * @Class         : HTMLFormUtils.java
 * @Package       : eu.akka.saem.alfresco.seda.form
 * @Date          : $Date: 25 févr. 2014 $:
 * @author        : $Author: THOMAS.POGNANT $
 * @version       : $Revision:  $:
 * @Id            : $Id: HTMLFormUtils.java $
 *
 */
public class HTMLFormUtils {
	public static String getAffValue(LinkedHashMap<String,String> formData) {
		if(!formData.containsKey("affValue"))
			return null;
			
		return formData.get("affValue");
	}

	public static boolean isEditable(Object modelObject,String affValue) {
		try{
			if(modelObject == null){
				return false;
			}
			else{
				if(modelObject.getClass().equals(String.class) && !modelObject.equals(""))
					return false;
				else if(modelObject.getClass().isEnum() && !findValueWithEnum(modelObject.getClass(),modelObject).equals(""))
					return false;
				else {
					if(affValue!=null){
						Method m = findGetterMethodByName(modelObject.getClass(), affValue);
						Object res = m.invoke(modelObject);
						if(res.getClass().equals(String.class) && !res.equals(""))
							return false;
						else if(res.getClass().isEnum() && !findValueWithEnum(res.getClass(),res).equals(""))
							return false;
						else
							return true;					
					}
					else{
						return true;
					}
	
				}
			}

		}
		catch(Exception ex){
			//ex.printStackTrace();
			return true;
		}
	}
	
	public static String findValueWithEnum(Class<?> class1, Object obj) throws InstantiationException, IllegalAccessException {
		
		for(Field o:class1.getFields()){
			SEDA sedaAnnotation = o.getAnnotation(SEDA.class);
			if(sedaAnnotation != null && o.get(class1).equals(obj))
				return sedaAnnotation.PropertyTerm();			
		}
		
		return "";
	}

	public static boolean isObligatoire(AGAPEDescriptor modelDescriptor) {
		if(modelDescriptor == null)
			return false;
		if(modelDescriptor.getMinOccurs() > 0)
			return true;
		else
			return false;		
	}

	public static String getTypeInput(LinkedHashMap<String,String> formData) {
		return formData.get("type");
	}

	public static Object getValue(Object data,Object modelObject) {
		try {
			
			if(data!=null){
				return data;
			}
			else if(modelObject!=null){
				return modelObject;
			}
			
		} catch (Exception e) {
			//e.printStackTrace();
		}
		return null;
	}
	
	public static boolean isField(String key, StructureMessageSEDA sms,
			Object sedao2, AGAPEDescriptor ad, Object ao2) {
		if(sms.getCurrent().get(key).containsKey("type") && !sms.getCurrent().get(key).containsKey("listObject"))
			return true;
		return false;
	}

	public static boolean isModel(String key, StructureMessageSEDA sms,
			Object sedao2, AGAPEDescriptor ad, Object ao2) {
		if(sms.getCurrent().get(key).containsKey("modelObject"))
			return true;
		return false;
	}

	public static boolean isList(String key, StructureMessageSEDA sms,
			Object sedao2, AGAPEDescriptor ad, Object ao2) {
		if(sms.getCurrent().get(key).containsKey("listObject"))
			return true;
		return false;
	}
	
	
	public static String getLabel(LinkedHashMap<String,String> formData) {
		return formData.get("formName");
	}

	public static String getHelp(AGAPEDescriptor modelDescriptor) {
		if(modelDescriptor==null)
			return null;
		
		if(modelDescriptor.getComment()!=null && !modelDescriptor.getComment().equals(""))
			return modelDescriptor.getComment();
		else
			return null;
	}
	
	public static Method findGetterMethodByName(Class<?> obclass, String name) throws NoSuchMethodException{
		for(Method m:obclass.getMethods()){
			SEDA sedaAnnotations = m.getAnnotation(SEDA.class);
			
			if(sedaAnnotations == null)
				break;
			
			if(sedaAnnotations.PropertyTerm().equals(name)){
				if(m.getName().startsWith("get"))
						return m;				
			}
		}
		
		throw new NoSuchMethodException("get"+name+" est introuvable dans la classe "+ obclass.getName());
	}
	
	public static Method findSetterMethodByName(Class<?> obclass, String name) throws NoSuchMethodException{
		for(Method m:obclass.getMethods()){
			SEDA sedaAnnotations = m.getAnnotation(SEDA.class);
			
			if(sedaAnnotations == null)
				break;
			
			if(sedaAnnotations.PropertyTerm().equals(name)){
				if(m.getName().startsWith("set") || m.getName().startsWith("add"))
						return m;				
			}
		}
		
		throw new NoSuchMethodException("set"+name+" ou add"+name+" est introuvable dans la classe "+ obclass.getName());
	}
	
	
	public static AGAPEDescriptor findChildDescriptor(AGAPEDescriptor ad, String key, Integer nb) {
		if(ad == null)
			return null;
		
		Integer counter = 0;
		for(AGAPEDescriptor a:ad.getChilds()){
			if(a.getName().equals(key) && counter == nb){
				return a;
			}
			else if(a.getName().equals(key)){
				counter ++;
			}
		}		
		return null;
	}
	
	public static boolean isEntete(String key,StructureMessageSEDA sms) {
		if(sms.getCurrent()!=null && sms.getCurrent().get(key).containsKey("entete"))
			return true;

		return false;
	}

	public static boolean isDescription(String key,StructureMessageSEDA sms) {
		if(sms.getCurrent()!=null && sms.getCurrent().get(key).containsKey("description"))
			return true;
		
		return false;
	}

	public static String getListLabel(LinkedHashMap<String,String> formData) {
		return formData.get("formListName");
	}
	
	public static Object findValueOnEnum(Class<?> class1, String nodeValue) throws InstantiationException, IllegalAccessException {
		if(nodeValue==null)
			nodeValue="";
		
		for(Field o:class1.getFields()){
			SEDA sedaAnnotation = o.getAnnotation(SEDA.class);
			if(sedaAnnotation != null && nodeValue.equals(sedaAnnotation.PropertyTerm()))
				return o.get(class1);			
		}
		
		return null;
	}

	public static String getHelpList(String key, AGAPEDescriptor ad,
			Integer modelIndex) {
		if(ad==null)
			return null;
		
		return ad.getChilds().get(modelIndex).getComment();
	}

	public static boolean isObligatoireList(String key, AGAPEDescriptor ad,
			Integer modelIndex) {
		
		if(ad == null)
			return false;
		else{
				if(ad.getChilds().get(modelIndex).getMinOccurs() > 0)
					return true;
				else
					return false;
			
		}	
	}

	public static boolean isEditableList(String key, StructureMessageSEDA sms,
			AGAPEDescriptor ad, Object ao, Integer modelIndex, Integer index) {
		/*if(ad==null)
			return false;
		else{
			
			List<Object> o1 = (List<Object>) ao;
			
			
			Object result = o1.get(index);
			
			if(result!=null)
			{
				if(result.getClass().equals(String.class)){
					if(result.equals("")){
						return true;
					}
					else{
						return false;
					}
				}
				else if(getAffValue(key, sms)!=null){
					try {
						String aff = getAffValue(key, sms);
						Method m = findGetterMethodByName(result.getClass(), aff);
						Object res = m.invoke(result);
						if(res == null)
							return true;
						else if(res.getClass().equals(String.class) && !((String) res).equals(""))
							return false;
						else if(!HTMLFormUtils.findValueWithEnum(res.getClass(), res).equals(""))
							return false;
						else
							return true;
					} catch (Exception e) {
								
						e.printStackTrace();
					}
				}
				else{
					return false;
				}
			}
			else
				return false;
		}*/
		return false;
	}

	public static boolean isAffInMenu(LinkedHashMap<String, String> formData) {
		return formData.containsKey("affInMenu");
	}


	
}
