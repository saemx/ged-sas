package eu.akka.saem.share.evaluator;

import java.io.IOException;
import java.util.Map;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.extensions.surf.RequestContext;
import org.springframework.extensions.surf.ServletUtil;
import org.springframework.extensions.surf.exception.ConnectorServiceException;
import org.springframework.extensions.surf.extensibility.impl.DefaultSubComponentEvaluator;
import org.springframework.extensions.webscripts.Status;
import org.springframework.extensions.webscripts.connector.Connector;
import org.springframework.extensions.webscripts.connector.Response;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * 
 *   Evaluator permettant de determiner s'il faut afficher ou masquer chaque propriété
 * 
 * @Class         : HideSedaProperties.java
 * @Package       : eu.akka.saem.share.evaluator
 * @Date          : $Date: 25 févr. 2014 $:
 * @author        : $Author: THOMAS.POGNANT $
 * @version       : $Revision:  $:
 * @Id            : $Id: HideSedaProperties.java $
 *
 */
public class HideSedaProperties extends DefaultSubComponentEvaluator {

	private static final Log LOG = LogFactory.getLog(HideSedaProperties.class);

	public boolean evaluate(RequestContext context, Map<String, String> params) {

		String nodeRef = context.getUriTokens().get("nodeRef");
		if (nodeRef == null) {
			nodeRef = context.getParameter("nodeRef");
		}

		try {
			Connector conn = context.getServiceRegistry().getConnectorService()
					.getConnector("alfresco", context.getUserId(), ServletUtil.getSession());
			Response response = conn.call("/api/node/" + nodeRef.replace(":/", ""));
			if (response.getStatus().getCode() == Status.STATUS_OK) {
				return !checkIfSEDAPropertyExist(response);
			}
		} catch (ConnectorServiceException e) {
			LOG.error("Erreur lors de la tentative de récupération des propriétés seda", e);
		}

		return true;
	}

	private boolean checkIfSEDAPropertyExist(Response response) {
		try {
			Document dom = DocumentBuilderFactory.newInstance().newDocumentBuilder()
					.parse(response.getResponseStream());
			NodeList list = dom.getElementsByTagName("cmis:propertyString");
			NodeList list2 = dom.getElementsByTagName("cmis:propertyBoolean");
			for (int i = 0; i < list.getLength(); i++) {
				Element element = (Element) list.item(i);
				String propertyName = element.getAttribute("propertyDefinitionId");
				//Pour les dossiers
				if (propertyName.equals("saem:seda_sous_objet_archive_xml")) {
					return true;
				}
				//Pour les documents
				if (propertyName.equals("saem:seda_document_xml")) {
					return true;
				}
			}
			for (int i = 0; i < list2.getLength(); i++) {
				Element element = (Element) list2.item(i);
				String propertyName = element.getAttribute("propertyDefinitionId");
				if (propertyName.equals("saem:isarchive")) {
					return true;
				}
			}
		} catch (SAXException | IOException | ParserConfigurationException e) {
			LOG.error("Erreur lors de la tentative de récupération des propriétés seda", e);
		}
		return false;
	}
}
