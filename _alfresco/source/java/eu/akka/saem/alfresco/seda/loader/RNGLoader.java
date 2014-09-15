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

import eu.akka.saem.alfresco.parser.RNGParserUtils;
import eu.akka.saem.alfresco.seda.annotations.SEDA;
import eu.akka.saem.alfresco.seda.annotations.SEDAType;
import eu.akka.saem.alfresco.seda.loader.rng.RNGDescriptor;
import eu.akka.saem.alfresco.seda.loader.rng.RNGObject;
import eu.akka.saem.alfresco.seda.loader.rng.XMLType;
import eu.akka.saem.alfresco.seda.v02.ArchiveTransferType;

/**
 * 
 *   Classe de transformation d'objet en RNGObject
 * 
 * @Class         : RNGLoader.java
 * @Package       : eu.akka.saem.alfresco.seda.loader
 * @Date          : $Date: 25 févr. 2014 $:
 * @author        : $Author: THOMAS.POGNANT $
 * @version       : $Revision:  $:
 * @Id            : $Id: RNGLoader.java $
 *
 */
public class RNGLoader {
	
	/**
	 * Transforme un fichier RNG SEDA v0.2 en ArchiveTransferType
	 * @param path Chemin du fichier RNG
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
	public static RNGObject ArchiveTransferLoader(String path) throws ParserConfigurationException, SAXException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, SecurityException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException{
			return ArchiveTransferLoader(new File(path));		
	}
	
	/**
	 * Transforme un fichier RNG SEDA v0.2 en ArchiveTransferType
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
	public static RNGObject ArchiveTransferLoader(File f) throws ParserConfigurationException, SAXException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, SecurityException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException{
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();			
		Document doc = docBuilder.parse(f);			
		return ArchiveTransferLoader(doc);		
	}
	
	
	/**
	 * Transforme un fichier RNG SEDA v0.2 en ArchiveTransferType
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
	public static RNGObject ArchiveTransferLoader(InputStream is) throws ParserConfigurationException, SAXException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, SecurityException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException{
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();			
		Document doc = docBuilder.parse(is);			
		return ArchiveTransferLoader(doc);		
	}
	
	/**
	 * Transforme un Document RNG SEDA v0.2 en ArchiveTransferType
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
	public static RNGObject ArchiveTransferLoader(Document doc) throws ParserConfigurationException, ClassNotFoundException, InstantiationException, IllegalAccessException, SecurityException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException{
		Node ArchiveTransferDefineNode = RNGParserUtils.getDefineRefByName("ArchiveTransfer", doc);
		Node ArchiveTransferEleNode = RNGParserUtils.getChildElementNode(ArchiveTransferDefineNode).get(0);
		Node ArchiveTransferRefNode = RNGParserUtils.getChildRefNode(ArchiveTransferEleNode).get(0);
		Node ArchiveTransferNode = RNGParserUtils.getDefineRefByName(RNGParserUtils.getRefName(ArchiveTransferRefNode), doc);
		
		String version = getSEDAVersion(doc.getDocumentElement());
		RNGLoader xmlloader = new RNGLoader();
		Class<?> objectClass = Class.forName("eu.akka.saem.alfresco.seda."+version+"."+RNGParserUtils.getElementName(ArchiveTransferEleNode)+"Type");
		
		
		ArchiveTransferType at = (ArchiveTransferType) objectClass.newInstance();
		SEDA annotation = objectClass.getAnnotation(SEDA.class);
		RNGDescriptor rea = new RNGDescriptor(annotation.PropertyTerm(), XMLType.ELEMENT);
		xmlloader.transform(ArchiveTransferNode,rea,at);
		RNGObject rngfile = new RNGObject(rea,at);
		return rngfile;	
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
	private void transform(Node no, RNGDescriptor rea, Object resultObject) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SecurityException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException{
		//On cree l'objet
		
		for(Node node:RNGParserUtils.getChildAttributNode(no)){
			Method method = findSetterMethodByName(resultObject.getClass(), RNGParserUtils.getElementName(node));
			SEDA sedaAnnotation = method.getAnnotation(SEDA.class);
			
			RNGDescriptor childREA = new RNGDescriptor(sedaAnnotation.PropertyTerm(),XMLType.ATTRIBUT);
			configRNGElement(childREA,node);
			
			
			if(sedaAnnotation.Type().equals(SEDAType.ATTRIBUTE)){
				if(method.getParameterTypes()[0].equals(String.class)){
					rea.addChilds(childREA);
					method.invoke(resultObject, RNGParserUtils.getValueByElement(node));
				}
				else if(method.getParameterTypes()[0].isEnum()){
					rea.addChilds(childREA);
					method.invoke(resultObject, findValueOnEnum(method.getParameterTypes()[0],RNGParserUtils.getValueByElement(node)));
				}
			}
		}
		
		if(RNGParserUtils.getNodeValue(no)!=null){
			Node node = RNGParserUtils.getNodeValue(no);
			Method method = findMethodByName(resultObject.getClass(),"setValue");
			
			RNGDescriptor childREA = new RNGDescriptor("value",XMLType.VALUE);
			configRNGElement(childREA,node);
			
			if(method.getParameterTypes()[0].equals(String.class)){
				rea.addChilds(childREA);
				method.invoke(resultObject,node.getTextContent());				
			}
			else if(method.getParameterTypes()[0].isEnum()){
				rea.addChilds(childREA);
				method.invoke(resultObject, findValueOnEnum(method.getParameterTypes()[0],node.getTextContent()));
			}
		}
		
		int counter = 0;
		String counterName = "";
		for(Node node:RNGParserUtils.getChildElementNode(no)){
			Method method = findSetterMethodByName(resultObject.getClass(), RNGParserUtils.getElementName(node));
			SEDA sedaAnnotation = method.getAnnotation(SEDA.class);
			
			RNGDescriptor childREA = new RNGDescriptor(sedaAnnotation.PropertyTerm(),XMLType.ELEMENT);
			configRNGElement(childREA,node);
			
						
			if(sedaAnnotation.Cardinality().equals("0..n") || sedaAnnotation.Cardinality().equals("1..n")){
				
				if(counterName.equals("") || sedaAnnotation.PropertyTerm().equals(counterName)){
					counterName=sedaAnnotation.PropertyTerm();
					childREA.setName(childREA.getName()+"["+counter+"]");
					counter ++;
				}
				else{
					counter=0;
					counterName=sedaAnnotation.PropertyTerm();
					childREA.setName(childREA.getName()+"["+counter+"]");
					counter ++;
				}
				
			}
			else{
			   counter = 0;
			   counterName = "";
			}
			
			if(sedaAnnotation.Type().equals(SEDAType.ELEMENT)){
				if(method.getParameterTypes()[0].equals(String.class)){
					rea.addChilds(childREA);
					method.invoke(resultObject, RNGParserUtils.getValueByElement(node));
				}
				else if(method.getParameterTypes()[0].isEnum()){
					rea.addChilds(childREA);
					method.invoke(resultObject, findValueOnEnum(method.getParameterTypes()[0],RNGParserUtils.getValueByElement(node)));
				}
				else{
					rea.addChilds(childREA);
					Object object = method.getParameterTypes()[0].newInstance();
					transform(RNGParserUtils.getDefineNodeByElement(node),childREA,object);
					method.invoke(resultObject, object);					
				}
				
			}
		}
	}
	
	
	private void configRNGElement(RNGDescriptor childREA, Node node) {
		if(node.getParentNode().getNodeName().equals(RNGParserUtils.ELEMENT)){
			childREA.setMinOccurs(1);
			childREA.setMaxOccurs(1);			
		}
		
		if(node.getParentNode().getNodeName().equals(RNGParserUtils.ONEORMORE)){
			childREA.setMinOccurs(1);
			childREA.setMaxOccurs(-1);
		}
		
		if(node.getParentNode().getNodeName().equals(RNGParserUtils.ZEROORMORE)){
			childREA.setMinOccurs(0);
			childREA.setMaxOccurs(-1);
		}
		
		if(node.getParentNode().getNodeName().equals(RNGParserUtils.OPTIONAL)){
			childREA.setMinOccurs(0);
			childREA.setMaxOccurs(1);
		}
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
