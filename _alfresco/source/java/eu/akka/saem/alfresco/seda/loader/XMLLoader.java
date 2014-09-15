package eu.akka.saem.alfresco.seda.loader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import eu.akka.saem.alfresco.seda.annotations.SEDA;

/**
 * 
 * Transforme un fichier XML SEDA v0.2
 * 
 * @Class         : XMLLoader.java
 * @Package       : eu.akka.saem.alfresco.seda.loader
 * @Date          : $Date: 25 févr. 2014 $:
 * @author        : $Author: THOMAS.POGNANT $
 * @version       : $Revision:  $:
 * @Id            : $Id: XMLLoader.java $
 *
 */
public class XMLLoader {
	
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
	 */
	public static Object SEDALoader(String path) throws ParserConfigurationException, SAXException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, SecurityException, NoSuchMethodException{
			return SEDALoader(new File(path));		
	}
	
	/**
	 * Transforme un fichier XML SEDA v0.2 en Objet SEDA
	 * @param f Fichier XML
	 * @return ArchiveTransferType
	 * @throws ParserConfigurationException 
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 */
	public static Object SEDALoader(File f) throws ParserConfigurationException, SAXException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, SecurityException, NoSuchMethodException{
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();			
		Document doc = docBuilder.parse(f);			
		return SEDALoader(doc);		
	}
	
	/**
	 * Transforme un fichier XML SEDA v0.2 en Objet SEDA
	 * @param f Fichier XML
	 * @return ArchiveTransferType
	 * @throws ParserConfigurationException 
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 */
	public static Object SEDALoader(InputStream is) throws ParserConfigurationException, SAXException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, SecurityException, NoSuchMethodException{
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();			
		Document doc = docBuilder.parse(is);			
		return SEDALoader(doc);		
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
	 */
	public static Object SEDALoader(Document doc) throws ParserConfigurationException, ClassNotFoundException, InstantiationException, IllegalAccessException, SecurityException, NoSuchMethodException{
		Node node = doc.getFirstChild();
		
		XMLLoader xmlloader = new XMLLoader();
		String version = getSEDAVersion(doc.getDocumentElement());
		Class<?> objectClass = null;
		try{
			objectClass = Class.forName("eu.akka.saem.alfresco.seda."+version+"."+node.getNodeName()+"Type");
		}catch(ClassNotFoundException e){
			objectClass = Class.forName(node.getNodeName());
		}
		
		Object ob = xmlloader.transform(objectClass,node);
		return ob;	
	}
	
	private static String getSEDAVersion(Element documentElement) {
		String version = documentElement.getAttribute("xmlns");
		if(version == null)
			return "";
		
		if(version.endsWith("v0.2"))
			return "v02";
		
		return "";
	}
	
	
		
	/**
	 * Permet de trouver la bonne méthode d'insertion de l'objet en fonction de nom de la balise XML SEDA
	 * @param obclass
	 * @param name
	 * @return
	 * @throws NoSuchMethodException 
	 */
	private Method findSetterMethodByName(Class<?> obclass, String name) throws NoSuchMethodException{
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
	private Method findMethodByName(Class<?> obclass, String name) throws NoSuchMethodException{
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
	 */
	private Object transform(Class<?> objectClass,Node node) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SecurityException, NoSuchMethodException{
		//On cree l'objet
		Object object = objectClass.newInstance();
		
		//On s'occupe des attributs
		for(int i=0; i < node.getAttributes().getLength(); i++){
			
			try{
				Method method = findSetterMethodByName(object.getClass(), node.getAttributes().item(i).getNodeName());
				
				if(method.getParameterTypes()[0].isEnum()){
					method.invoke(object, findValueOnEnum(method.getParameterTypes()[0],node.getAttributes().item(i).getNodeValue()));
				}
				else if(method.getParameterTypes()[0].equals(String.class)){
					method.invoke(object, node.getAttributes().item(i).getNodeValue());
				}				
			}
			catch(NoSuchMethodException nsme){
				System.out.println("eu.akka.saem.alfresco.seda.v02."+node.getNodeName()+"Type."+"set"+node.getAttributes().item(i).getNodeName()+" Introuvable");
			}
			catch (InvocationTargetException e) {
				System.out.println("eu.akka.saem.alfresco.seda.v02."+node.getNodeName()+"Type."+"set"+node.getAttributes().item(i).getNodeName()+" Erreur Invocation");
			}
			
		}
		
		try{
			Method method = findMethodByName(objectClass,"setValue");
			
			
				if(method.getParameterTypes()[0].equals(String.class)){
					if(node.getTextContent() != null && !node.getTextContent().equals("")){
					method.invoke(object,node.getTextContent());
					}
				}
				else if(method.getParameterTypes()[0].isEnum()){
					method.invoke(object, findValueOnEnum(method.getParameterTypes()[0],node.getTextContent()));
				}
			
		}
		catch(Exception ex){
		}
		
		//On s'occupe des sous éléments
		for(int i=0; i < node.getChildNodes().getLength(); i++){
			if(node.getChildNodes().item(i).getNodeName().equals("#text"))
				continue;
			
			try{
				Object objecttmp = object;
				Method method = findSetterMethodByName(object.getClass(), node.getChildNodes().item(i).getNodeName());
				
				if(method.getParameterTypes()[0].equals(String.class)){
					method.invoke(objecttmp, node.getChildNodes().item(i).getTextContent());
				}
				else if(method.getParameterTypes()[0].isEnum()){
					method.invoke(object, findValueOnEnum(method.getParameterTypes()[0],node.getChildNodes().item(i).getTextContent()));
				}
				else{
					method.invoke(objecttmp, transform(method.getParameterTypes()[0],node.getChildNodes().item(i)));
				}
				
			}
			catch(Exception nsme){
				nsme.printStackTrace();
			}
		}		
		//On retourne l'objet
		
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
	private Object findValueOnEnum(Class<?> class1, String nodeValue) throws InstantiationException, IllegalAccessException {
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
