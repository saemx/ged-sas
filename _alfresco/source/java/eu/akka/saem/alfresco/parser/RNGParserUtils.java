package eu.akka.saem.alfresco.parser;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * 
 * @Class         : RNGParserUtils.java
 * @Package       : eu.akka.saem.alfresco.parser
 * @Date          : $Date: 25 févr. 2014 $:
 * @author        : $Author: THOMAS.POGNANT $
 * @version       : $Revision:  $:
 * @Id            : $Id: RNGParserUtils.java $
 *
 */
public class RNGParserUtils {
	public static String START = "rng:start";
	public static String REF = "rng:ref";
	public static String REF_NAME_ATTRIBUT = "name";
	public static String DEFINE = "rng:define";
	public static String DEFINE_NAME_ATTRIBUT = "name";
	public static String ELEMENT = "rng:element";
	public static String ONEORMORE = "rng:oneOrMore";
	public static String ZEROORMORE = "rng:zeroOrMore";
	public static String OPTIONAL = "rng:optional";
	public static String ELEMENT_NAME_ATTRIBUT = "name";
	public static String VALUE = "rng:value";
	public static String ATTRIBUT = "rng:attribute";
	public static String ATTRIBUT_NAME_ATTRIBUT = "name";
		
	public static String getElementName(Node node){
		return node.getAttributes().getNamedItem(ELEMENT_NAME_ATTRIBUT).getNodeValue();
	}
	
	public static String getDefineName(Node node){
		return node.getAttributes().getNamedItem(DEFINE_NAME_ATTRIBUT).getNodeValue();
	}
	
	public static String getRefName(Node node){
		return node.getAttributes().getNamedItem(REF_NAME_ATTRIBUT).getNodeValue();
	}
	
	public static List<Node> getChildRefNode(Node node){
		List<Node> nodes = new ArrayList<Node>();
		
		for(int i=0; i < node.getChildNodes().getLength(); i++){
			Node n = node.getChildNodes().item(i);
			if(n.getNodeName().equals(REF)){
				nodes.add(n);
			}
		}		
		return nodes;
	}
	
	public static List<Node> getChildElementNode(Node node){
		List<Node> nodes = new ArrayList<Node>();
		
		for(int i=0; i < node.getChildNodes().getLength(); i++){
			Node n = node.getChildNodes().item(i);
			if(n.getNodeName().equals(ELEMENT)){
				nodes.add(n);
			}
			
			if(n.getNodeName().equals(ONEORMORE)){
				nodes.addAll(getChildElementNode(n));
			}
			
			if(n.getNodeName().equals(ZEROORMORE)){
				nodes.addAll(getChildElementNode(n));
			}
			
			if(n.getNodeName().equals(OPTIONAL)){
				nodes.addAll(getChildElementNode(n));
			}
						
		}
		
		return nodes;
	}
		
	public static Node getNodeValue(Node node) {
		
		for(int i=0; i < node.getChildNodes().getLength(); i++){
			Node n = node.getChildNodes().item(i);
			if(n.getNodeName().equals(VALUE)){
				return n;
			}
		}
		
		return null;
	}

	public static Node getDefineRefByName(String name,Document doc){
		NodeList nodes = doc.getElementsByTagName(DEFINE);
		
		for(int i=0; i < nodes.getLength(); i++){
			Node n = nodes.item(i);
			if(n.getAttributes().getNamedItem(DEFINE_NAME_ATTRIBUT).getNodeValue().equals(name)){
				return n;
			}
		}
		
		return null;
	}

	public static String getValueByElement(Node node) {
		List<Node> ref_nodes = getChildRefNode(node);

		if(ref_nodes.size() == 1)
		{
			Node define_node = getDefineRefByName(getRefName(ref_nodes.get(0)), node.getOwnerDocument());
			return getValueByElement(define_node);
		}
		else if(getNodeValue(node)!=null){
			return getNodeValue(node).getTextContent();
		}
		else{
			return "";
		}
	}

	public static Node getDefineNodeByElement(Node node) {
		List<Node> ref_nodes = getChildRefNode(node);
		
		if(ref_nodes.size() == 1 && getRefName(ref_nodes.get(0)).startsWith(getElementName(node)))
		{
			Node define_node = getDefineRefByName(getRefName(ref_nodes.get(0)), node.getOwnerDocument());
			return define_node;
		}
		
		return node;
	}

	public static List<Node> getChildAttributNode(Node node) {
		List<Node> nodes = new ArrayList<Node>();
		
		for(int i=0; i < node.getChildNodes().getLength(); i++){
			Node n = node.getChildNodes().item(i);
			if(n.getNodeName().equals(ATTRIBUT)){
				nodes.add(n);
			}
		}
		
		return nodes;
	}

	public static Object getAttributName(Node node) {
		return node.getAttributes().getNamedItem(ATTRIBUT_NAME_ATTRIBUT).getNodeValue();
	}

}
