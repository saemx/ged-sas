package eu.akka.saem.alfresco.seda.loader;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.xml.parsers.ParserConfigurationException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import eu.akka.saem.alfresco.seda.annotations.SEDA;

/**
 * 
 *   Classe contenant diverses methodes de transformation d'objet JSON
 *   en objet SEDA
 * 
 * @Class         : JSONLoader.java
 * @Package       : eu.akka.saem.alfresco.seda.loader
 * @Date          : $Date: 25 févr. 2014 $:
 * @author        : $Author: THOMAS.POGNANT $
 * @version       : $Revision:  $:
 * @Id            : $Id: JSONLoader.java $
 *
 */
public class JSONLoader {

	/**
	 * Transforme un fichier XML SEDA v0.2 en Objet SEDA
	 * @param path Chemin du fichier XML
	 * @return ArchiveTransferType
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 * @throws JSONException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 */
	public static Object SEDALoader(String json) throws ParserConfigurationException, SAXException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, SecurityException, NoSuchMethodException, JSONException, IllegalArgumentException, InvocationTargetException{
			
		return SEDALoader(new JSONObject(json));		
	}
	
	/**
	 * Transforme un Document XML SEDA v0.2 en Objet SEDA
	 * @param doc Document XML
	 * @return ArchiveTransferType
	 * @throws ParserConfigurationException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 * @throws JSONException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 */
	public static Object SEDALoader(JSONObject object) throws ParserConfigurationException, ClassNotFoundException, InstantiationException, IllegalAccessException, SecurityException, NoSuchMethodException, JSONException, IllegalArgumentException, InvocationTargetException{
		
		Class<?> objectClass = Class.forName("eu.akka.saem.alfresco.seda.v02."+object.get("PropertyTerm")+"Type");
		
		Object ob = JSONLoader.transform(objectClass,object);
		return ob;	
	}
	
	private static String getSEDAVersion(Element documentElement) {
		String version = documentElement.getAttribute("xmlns");
		if(version == null)
			throw new IllegalArgumentException("La version du SEDA utilisé dans le fichier xml est inconnu");
		
		if(version.endsWith("v0.2"))
			return "v02";
		
		throw new IllegalArgumentException("La version du SEDA utilisé dans le fichier xml est inconnu");
	}
	
	
		
	/**
	 * Permet de trouver la bonne méthode d'insertion de l'objet en fonction de nom de la balise XML SEDA
	 * @param obclass
	 * @param name
	 * @return
	 * @throws NoSuchMethodException 
	 */
	private static Method findSetterMethodByName(Class<?> obclass, String name) throws NoSuchMethodException{
		for(Method m:obclass.getMethods()){
			SEDA sedaAnnotations = m.getAnnotation(SEDA.class);
			
			if(sedaAnnotations == null)
				break;
			
			if(sedaAnnotations.PropertyTerm().equals(name)){
				if(sedaAnnotations.Cardinality().equals("0..n") || sedaAnnotations.Cardinality().equals("1..n")){
					if(m.getName().startsWith("add"))
						return m;
				}
				else{
					if(m.getName().startsWith("set"))
						return m;
				}
			}
		}
		
		throw new NoSuchMethodException("set"+name+" ou add"+name+" est introuvable dans la classe "+ obclass.getName());
	}
	
	/**
	 * Permet de trouver la bonne méthode d'insertion de l'objet 
	 * @param obclass
	 * @param name
	 * @return
	 * @throws NoSuchMethodException 
	 */
	private static Method findMethodByName(Class<?> obclass, String name) throws NoSuchMethodException{
		for(Method m:obclass.getMethods()){
						
			if(m.getName().equals(name)){
				return m;
			}
		}
		
		throw new NoSuchMethodException("set"+name+" ou add"+name+" est introuvable dans la classe "+ obclass.getName());
	}
	
	/**
	 * Transforme un noeud XML SEDA en objet java SEDA
	 * @param objectClass
	 * @param node
	 * @return
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws JSONException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 */
	private static Object transform(Class<?> objectClass,JSONObject jsonobject) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SecurityException, NoSuchMethodException, JSONException, IllegalArgumentException, InvocationTargetException{
		//On cree l'objet
		Object object = objectClass.newInstance();
		
		//On s'occupe des attributs
		if(jsonobject.get("value").getClass().equals(JSONArray.class)){
			JSONArray array = (JSONArray) jsonobject.get("value");
			for(int i=0; i<array.length(); i++){
				try{
					
					Method m = findSetterMethodByName(objectClass,(String) ((JSONObject) array.get(i)).get("PropertyTerm"));
					if(m.getParameterTypes()[0].equals(String.class)){
						m.invoke(object,(String) ((JSONObject) array.get(i)).get("value"));
					}
					else if(m.getParameterTypes()[0].isEnum()){
						m.invoke(object,findValueOnEnum(m.getParameterTypes()[0],(String) ((JSONObject) array.get(i)).get("value")));
					}
					else{
						m.invoke(object, transform(m.getParameterTypes()[0],((JSONObject) array.get(i))));
					}
				}
				catch(Exception ex){
					
				}
				
			}
		}
		else if(jsonobject.get("value").getClass().equals(String.class)){
			
		}		
		
		return object;
	}

	/**
	 * 
	 * @param class1
	 * @param nodeValue
	 * @return
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	private static Object findValueOnEnum(Class<?> class1, String nodeValue) throws InstantiationException, IllegalAccessException {
		if(nodeValue==null)
			nodeValue="";
		
		for(Field o:class1.getFields()){
			SEDA sedaAnnotation = o.getAnnotation(SEDA.class);
			if(sedaAnnotation != null && nodeValue.equals(sedaAnnotation.PropertyTerm()))
				return o.get(class1);			
		}
		
		return null;
	}
		
}
