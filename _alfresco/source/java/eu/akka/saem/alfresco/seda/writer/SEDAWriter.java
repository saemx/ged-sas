package eu.akka.saem.alfresco.seda.writer;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import eu.akka.saem.alfresco.seda.annotations.SEDA;
import eu.akka.saem.alfresco.seda.annotations.SEDAType;
import eu.akka.saem.alfresco.seda.v02.complexType.OtherMetadataType;

/**
 * 
 *   Classe contenant des methodes de transformation des objets SEDA
 * 
 * @Class         : SEDAWriter.java
 * @Package       : eu.akka.saem.alfresco.seda.writer
 * @Date          : $Date: 25 févr. 2014 $:
 * @author        : $Author: THOMAS.POGNANT $
 * @version       : $Revision:  $:
 * @Id            : $Id: SEDAWriter.java $
 *
 */
public class SEDAWriter {

	public static String transformSEDAObjectToXML(Object object) {
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			Document doc = docBuilder.newDocument();
			SEDA sedaAnnotation = object.getClass().getAnnotation(SEDA.class);

			String tagName;
			if (sedaAnnotation != null) {
				tagName = sedaAnnotation.PropertyTerm();
			} else {
				tagName = object.getClass().getName();
			}

			Node node = doc.createElement(tagName);

			doc.appendChild(transform(object, node));

			// Transform the document in String
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			StringWriter writer = new StringWriter();
			transformer.transform(new DOMSource(doc), new StreamResult(writer));

			return writer.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	private static List<String> cleanExceptions = new ArrayList<String>() {{
		add("TransferIdentifier");
		add("Attachment");
		add("TransferRequestReplyIdentifier");
		add("ArchivalAgencyObjectIdentifier");
		add("ArchivalAgencyArchiveIdentifier");
	}};
	
	public static String cleanEmptyChild(String object) {
		try{
			InputStream result = new ByteArrayInputStream(object.getBytes());
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();			
			Document doc = docBuilder.parse(result);
		
			cleanEmptyChild(doc.getFirstChild());
		
			// Transform the document in String
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			StringWriter writer = new StringWriter();
			transformer.transform(new DOMSource(doc), new StreamResult(writer));

			return writer.toString();
		}
		catch(Exception ex){
			return null;
		}
		
	}

	private static boolean cleanEmptyChild(Node node) {

		List<Node> nodeToDel = new ArrayList<Node>();

		int count = node.getChildNodes().getLength();

		// On s'occupe des sous éléments
		for (int i = 0; i < node.getChildNodes().getLength(); i++) {

			if (!cleanExceptions.contains(node.getChildNodes().item(i).getNodeName()) && cleanEmptyChild(node.getChildNodes().item(i))) {
				nodeToDel.add(node.getChildNodes().item(i));
				count--;
			}

		}

		for (Node n : nodeToDel) {
			node.removeChild(n);
		}

		if (node.getTextContent().equals("") && count == 0) {
			return true;
		} else {
			return false;
		}
	}

	public static JSONObject transformSEDAObjectToJSON(Object object) {
		try {
			JSONObject json = new JSONObject();

			SEDA sedaAnnotation = object.getClass().getAnnotation(SEDA.class);

			String tagName;
			if (sedaAnnotation != null) {
				tagName = sedaAnnotation.PropertyTerm();
			} else {
				tagName = object.getClass().getName();
			}

			JSONArray result = new JSONArray();
			transformToJSON(object, result);
			json.put("PropertyTerm", tagName);
			json.put("value", result);

			return json;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	private static Node transform(Object object, Node node) throws Exception {
		// On parcours toutes les méthodes des classes
		List<Method> methods = Arrays.asList(object.getClass().getMethods());
		Collections.sort(methods, new SEDAMethodComparator());
		for (Method method : methods) {
			SEDA sedaAnnotation = method.getAnnotation(SEDA.class);
			if (method.getName().startsWith("get")) {
				Object result = method.invoke(object);

				if (result == null || sedaAnnotation == null)
					continue;

				if (sedaAnnotation.Type().equals(SEDAType.ATTRIBUTE)) {
					Node attribut = node.getOwnerDocument().createAttribute(
							sedaAnnotation.PropertyTerm());
					if (method.getReturnType().equals(String.class)){
						attribut.setNodeValue((String) result);
						String res = (String) result;
						if(res != null && !res.equals(""))
							node.getAttributes().setNamedItem(attribut);
					}
					else if (method.getReturnType().isEnum()){
						attribut.setNodeValue(findValueWithEnum(
								method.getReturnType(), result));
						
						String res = findValueWithEnum(method.getReturnType(),result);
						if(res != null && !res.equals(""))
							node.getAttributes().setNamedItem(attribut);
					}
					
				} else if (sedaAnnotation.Type().equals(SEDAType.VALUE)) {
					if (method.getReturnType().equals(String.class)){
						if(object.getClass().equals(OtherMetadataType.class)){
							node.appendChild(node.getOwnerDocument().createCDATASection((String) result));
						}
						else{
							node.setTextContent((String) result);
						}
					}
					else if (method.getReturnType().isEnum())
						node.setTextContent(findValueWithEnum(
								method.getReturnType(), result));
				} else if (sedaAnnotation.Type().equals(SEDAType.ELEMENT)) {
					if (sedaAnnotation.Cardinality().equals("0..n")
							|| sedaAnnotation.Cardinality().equals("1..n")) {
						for (Object o : (List<Object>) result) {
							Node n = node.getOwnerDocument().createElement(
									sedaAnnotation.PropertyTerm());
							if (o.getClass().equals(String.class)) {
								n.setTextContent((String) o);
							} else {
								n = transform(o, n);
							}
							node.appendChild(n);
						}
					} else {
						if (method.getReturnType().equals(String.class)) {
							Node n = node.getOwnerDocument().createElement(
									sedaAnnotation.PropertyTerm());
							n.setTextContent((String) result);
							node.appendChild(n);
						} else if (method.getReturnType().isEnum()) {
							Node n = node.getOwnerDocument().createElement(
									sedaAnnotation.PropertyTerm());
							n.setTextContent(findValueWithEnum(
									method.getReturnType(), result));
							node.appendChild(n);
						} else {
							Node n = node.getOwnerDocument().createElement(
									sedaAnnotation.PropertyTerm());
							n = transform(result, n);
							node.appendChild(n);
						}
					}
				}
			}
		}

		return node;
	}

	@SuppressWarnings("unchecked")
	private static void transformToJSON(Object object, JSONArray json)
			throws Exception {
		// On parcours toutes les méthodes des classes
		for (Method method : object.getClass().getMethods()) {
			SEDA sedaAnnotation = method.getAnnotation(SEDA.class);

			if (sedaAnnotation != null
					&& sedaAnnotation.PropertyTerm().equals(
							"TransferIdentifier")) {
				int i = 0;
			}
			if (method.getName().startsWith("get")) {
				Object result = method.invoke(object);

				if (result == null && method.getName().startsWith("getValue")) {
					result = "";
				}

				if (result == null || sedaAnnotation == null)
					continue;

				if (sedaAnnotation.Type().equals(SEDAType.ATTRIBUTE)) {
					if (method.getReturnType().equals(String.class)) {
						JSONObject childjson = new JSONObject();
						childjson.put("PropertyTerm",
								sedaAnnotation.PropertyTerm());
						childjson.put("value", (String) result);
						json.put(childjson);
					} else if (method.getReturnType().isEnum()) {
						JSONObject childjson = new JSONObject();
						childjson.put("PropertyTerm",
								sedaAnnotation.PropertyTerm());
						childjson.put(
								"value",
								findValueWithEnum(method.getReturnType(),
										result));
						json.put(childjson);
					}
				} else if (sedaAnnotation.Type().equals(SEDAType.VALUE)) {
					if (method.getReturnType().equals(String.class)) {
						JSONObject childjson = new JSONObject();
						childjson.put("PropertyTerm",
								sedaAnnotation.PropertyTerm());
						childjson.put("value", (String) result);
						json.put(childjson);
					} else if (method.getReturnType().isEnum()) {
						JSONObject childjson = new JSONObject();
						childjson.put("PropertyTerm",
								sedaAnnotation.PropertyTerm());
						childjson.put(
								"value",
								findValueWithEnum(method.getReturnType(),
										result));
						json.put(childjson);
					}
				} else if (sedaAnnotation.Type().equals(SEDAType.ELEMENT)) {
					if (sedaAnnotation.Cardinality().equals("0..n")
							|| sedaAnnotation.Cardinality().equals("1..n")) {
						for (Object o : (List<Object>) result) {
							if (o.getClass().equals(String.class)) {
								JSONObject childjson = new JSONObject();
								childjson.put("PropertyTerm",
										sedaAnnotation.PropertyTerm());
								childjson.put("value", (String) o);
								json.put(childjson);
							} else {
								JSONArray resulttranformJSON = new JSONArray();
								transformToJSON(o, resulttranformJSON);

								JSONObject childjson = new JSONObject();
								childjson.put("PropertyTerm",
										sedaAnnotation.PropertyTerm());
								childjson.put("value", resulttranformJSON);
								json.put(childjson);
							}

						}
					} else {
						if (method.getReturnType().equals(String.class)) {
							JSONObject childjson = new JSONObject();
							childjson.put("PropertyTerm",
									sedaAnnotation.PropertyTerm());
							childjson.put("value", (String) result);
							json.put(childjson);
						} else if (method.getReturnType().isEnum()) {
							JSONObject childjson = new JSONObject();
							childjson.put("PropertyTerm",
									sedaAnnotation.PropertyTerm());
							childjson.put(
									"value",
									findValueWithEnum(method.getReturnType(),
											result));
							json.put(childjson);
						} else {

							JSONArray resulttranformJSON = new JSONArray();
							transformToJSON(result, resulttranformJSON);
							JSONObject childjson = new JSONObject();
							childjson.put("PropertyTerm",
									sedaAnnotation.PropertyTerm());
							childjson.put("value", resulttranformJSON);
							json.put(childjson);
						}
					}
				}
			}
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
	private static String findValueWithEnum(Class<?> class1, Object obj)
			throws InstantiationException, IllegalAccessException {

		for (Field o : class1.getFields()) {
			SEDA sedaAnnotation = o.getAnnotation(SEDA.class);
			if (sedaAnnotation != null && o.get(class1).equals(obj))
				return sedaAnnotation.PropertyTerm();
		}

		return "";
	}

}
