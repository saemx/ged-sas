package eu.akka.saem.alfresco.seda.loader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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
import eu.akka.saem.alfresco.seda.loader.agape.AGAPEDescriptor;
import eu.akka.saem.alfresco.seda.loader.agape.AGAPEObject;
import eu.akka.saem.alfresco.seda.loader.agape.AGAPEType;
import eu.akka.saem.alfresco.seda.v02.ArchiveTransferType;
import eu.akka.saem.alfresco.utils.Utils;

/**
 * 
 *   Classes contenant diverses methodes de transformation d'objets AGAPE
 * 
 * @Class         : AGAPELoader.java
 * @Package       : eu.akka.saem.alfresco.seda.loader
 * @Date          : $Date: 25 févr. 2014 $:
 * @author        : $Author: THOMAS.POGNANT $
 * @version       : $Revision:  $:
 * @Id            : $Id: AGAPELoader.java $
 *
 */
public class AGAPELoader {
	private static final String ELEMENTNAME = "xsd:element";
	private static final String ELEMENT_ATTRIBUT_NAME = "name";
	private static final String ATTRIBUT_NAME = "xsd:attribute";
	private static final String ELEMENT_ATTRIBUT_MINOCCURS = "minOccurs";
	private static final String ELEMENT_ATTRIBUT_MAXOCCURS = "maxOccurs";
	private static final String ANNOTATION_NAME = "xsd:annotation";
	private static final String UNBOUNDED = "unbounded";
	private static final String ELEMENT_ATTRIBUT_USE = "use";
	private static final String ELEMENT_ATTRIBUT_FIXED = "fixed";
	private static final String PROHIBITED = "prohibited";



	/**
	 * Transforme un fichier AGAPE en ArchiveTransferType
	 * @param path Chemin du fichier AGAPE
	 * @return ArchiveTransferType
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 */
	public static AGAPEObject ArchiveTransferLoader(String path) throws ParserConfigurationException, SAXException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, SecurityException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException{
			return ArchiveTransferLoader(new File(path));		
	}
	
	/**
	 * Transforme un fichier AGAPE en ArchiveTransferType
	 * TODO Cette méthode doit appeller ArchiveTransferLoader(InputStream is)
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
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 */
	public static AGAPEObject ArchiveTransferLoader(File f) throws ParserConfigurationException, SAXException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, SecurityException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException{
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();			
		Document doc = docBuilder.parse(f);			
		return ArchiveTransferLoader(doc);		
	}
	
	
	/**
	 * Transforme un fichier AGAPE en ArchiveTransferType
	 * @param is Fichier XML
	 * @return ArchiveTransferType
	 * @throws ParserConfigurationException 
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 */
	public static AGAPEObject ArchiveTransferLoader(InputStream is) throws ParserConfigurationException, SAXException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, SecurityException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException{
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();			
		Document doc = docBuilder.parse(is);			
		return ArchiveTransferLoader(doc);		
	}
	
	/**
	 * Transforme un fichier AGAPE en ArchiveTransferType
	 * @param doc Document XML
	 * @return ArchiveTransferType
	 * @throws ParserConfigurationException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 */
	public static AGAPEObject ArchiveTransferLoader(Document doc) throws ParserConfigurationException, ClassNotFoundException, InstantiationException, IllegalAccessException, SecurityException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException{
		Node node = doc.getDocumentElement();		
		String version = getSEDAVersion(doc.getDocumentElement());	
		node = node.getChildNodes().item(1);
		
		Class<?> objectClass = null;
		
		try{
			objectClass = Class.forName("eu.akka.saem.alfresco.seda."+version+"."+node.getAttributes().getNamedItem(ELEMENT_ATTRIBUT_NAME).getNodeValue()+"Type");
		}catch(ClassNotFoundException e){
			throw new IllegalArgumentException("Le fichier XML en entrée est incorrecte");
		}
			
		
		
		AGAPELoader xmlloader = new AGAPELoader();
		ArchiveTransferType at = (ArchiveTransferType) objectClass.newInstance();
		SEDA annotation = objectClass.getAnnotation(SEDA.class);
		AGAPEDescriptor rea = new AGAPEDescriptor(annotation.PropertyTerm(), AGAPEType.ELEMENT);
		xmlloader.transform(node,rea,at);
		AGAPEObject agapefile = new AGAPEObject(rea,at);
		return agapefile;
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
	 * Permet de trouver la bonne méthode d'insertion de l'objet en fonction de nom de la balise RNG SEDA
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
	 * Transforme un noeud XML SEDA en objet java SEDA
	 * @param objectClass
	 * @param node
	 * @return
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 */
	private void transform(Node node, AGAPEDescriptor rea, Object objecttmp) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SecurityException, IllegalArgumentException, InvocationTargetException{
				
				try{
					Method m = findMethodByName(objecttmp.getClass(), "setValue");
					if(m.getParameterTypes()[0].equals(String.class)){
						if(node.getAttributes().getNamedItem(ELEMENT_ATTRIBUT_FIXED)!=null)
							m.invoke(objecttmp,node.getAttributes().getNamedItem(ELEMENT_ATTRIBUT_FIXED).getNodeValue());
						else
							m.invoke(objecttmp,"");												
					}
					else if(m.getParameterTypes()[0].isEnum()){
						if(node.getAttributes().getNamedItem(ELEMENT_ATTRIBUT_FIXED)!=null)
							m.invoke(objecttmp,Utils.findValueOnEnum(m.getParameterTypes()[0],node.getAttributes().getNamedItem(ELEMENT_ATTRIBUT_FIXED).getNodeValue()));							
						else
							m.invoke(objecttmp,Utils.findValueOnEnum(m.getParameterTypes()[0],""));									
					}
				}
				catch(Exception ex){
					
				}
		
				for(int i=0; i < node.getChildNodes().getLength(); i++){					
					try {
						Node no = node.getChildNodes().item(i);
						
						if(no.getAttributes()!=null && (no.getAttributes().getNamedItem(ELEMENT_ATTRIBUT_USE)==null || !no.getAttributes().getNamedItem(ELEMENT_ATTRIBUT_USE).getNodeValue().equals(PROHIBITED)) && no.getAttributes().getNamedItem(ELEMENT_ATTRIBUT_NAME)!=null){
							Method method = findSetterMethodByName(objecttmp.getClass(), no.getAttributes().getNamedItem(ELEMENT_ATTRIBUT_NAME).getNodeValue());										
							
							AGAPEDescriptor descriptor = insertAGAPEDescriptor(no);						
							
							if(method.getParameterTypes()[0].equals(String.class)){
								if(no.getAttributes().getNamedItem(ELEMENT_ATTRIBUT_FIXED)!=null){
									method.invoke(objecttmp, no.getAttributes().getNamedItem(ELEMENT_ATTRIBUT_FIXED).getNodeValue());
								}
								else{
									method.invoke(objecttmp, "");
								}
							}
							else if(method.getParameterTypes()[0].isEnum()){
								if(no.getAttributes().getNamedItem(ELEMENT_ATTRIBUT_FIXED)!=null){
									method.invoke(objecttmp, Utils.findValueOnEnum(method.getParameterTypes()[0],no.getAttributes().getNamedItem(ELEMENT_ATTRIBUT_FIXED).getNodeValue()));
								}
								else{
									method.invoke(objecttmp, Utils.findValueOnEnum(method.getParameterTypes()[0],""));
								}
							}
							else{
								Object object = method.getParameterTypes()[0].newInstance();
								
								transform(no,descriptor,object);
								method.invoke(objecttmp, object);
							}	
							
							rea.addChilds(descriptor);
						}
					} catch (NoSuchMethodException e) {
						e.printStackTrace();
					}
				}		
				
	}
	
	
	
	private AGAPEDescriptor insertAGAPEDescriptor(Node node) {
		AGAPEDescriptor descriptor = new AGAPEDescriptor(node.getAttributes().getNamedItem(ELEMENT_ATTRIBUT_NAME).getNodeValue());
		if(node.getNodeName().equals(ELEMENTNAME)){
			descriptor.setType(AGAPEType.ELEMENT);
		}
		else if(node.getNodeName().equals(ATTRIBUT_NAME)){
			descriptor.setType(AGAPEType.ATTRIBUT);
		}
		
		if(node.getAttributes().getNamedItem(ELEMENT_ATTRIBUT_MINOCCURS)!=null){
			descriptor.setMinOccurs(Integer.parseInt(node.getAttributes().getNamedItem(ELEMENT_ATTRIBUT_MINOCCURS).getNodeValue()));
		}
		else{
			descriptor.setMinOccurs(1);
		}
		
		if(node.getAttributes().getNamedItem(ELEMENT_ATTRIBUT_MAXOCCURS)!=null){
			if(node.getAttributes().getNamedItem(ELEMENT_ATTRIBUT_MAXOCCURS).getNodeValue().equals(UNBOUNDED))
				descriptor.setMaxOccurs(-1);
			else
				descriptor.setMaxOccurs(Integer.parseInt(node.getAttributes().getNamedItem(ELEMENT_ATTRIBUT_MAXOCCURS).getNodeValue()));
		}
		else{
			descriptor.setMaxOccurs(1);
		}
		
		for(int i=0; i < node.getChildNodes().getLength(); i++){					
			Node no = node.getChildNodes().item(i);
			if(no.getNodeName().equals(ANNOTATION_NAME)){
				descriptor.setComment(no.getLastChild().getTextContent());
				break;
			}
		}
		
		
		return descriptor;
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
}
