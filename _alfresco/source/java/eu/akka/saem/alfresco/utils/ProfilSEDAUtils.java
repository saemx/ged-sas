package eu.akka.saem.alfresco.utils;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.alfresco.model.ContentModel;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.cmr.search.ResultSet;
import org.alfresco.service.cmr.search.ResultSetRow;
import org.alfresco.service.cmr.search.SearchParameters;
import org.alfresco.service.cmr.search.SearchService;
import org.alfresco.service.namespace.NamespaceService;
import org.alfresco.service.namespace.QName;
import org.alfresco.web.bean.repository.Repository;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import eu.akka.saem.alfresco.seda.loader.XMLLoader;

/**
 * Classe utilitaire pour la gestion des profils SEDA au sein d'Alfresco
 * 
 * @Class         : ProfilSEDAUtils.java
 * @Package       : eu.akka.saem.alfresco.utils
 * @Date          : $Date: 25 févr. 2014 $:
 * @author        : $Author: THOMAS.POGNANT $
 * @version       : $Revision:  $:
 * @Id            : $Id: ProfilSEDAUtils.java $
 *
 */
public class ProfilSEDAUtils {

	private NodeService nodeService;
	private SearchService searchService;
	private NamespaceService namespaceService;

	private static Logger LOG = Logger.getLogger(ProfilSEDAUtils.class);

	// TODO Ne récupérer que les profils contenus dans le sous dossier
	// "Profil SEDA" correspondant
	public List<String> getProfilsSEDAList() {
		List<String> results = new ArrayList<>();

		LOG.info("Récupération de la liste des profils SEDA");
		SearchParameters sp = new SearchParameters();
		sp.addStore(Repository.getStoreRef());
		sp.setLanguage(SearchService.LANGUAGE_LUCENE);
		sp.setQuery("ASPECT:\"saem:profil\"");
		ResultSet rs = searchService.query(sp);

		for (ResultSetRow row : rs) {
			NodeRef currentNodeRef = row.getNodeRef();
			String profilName = (String) nodeService.getProperty(currentNodeRef, ContentModel.PROP_NAME);
			results.add(profilName);
		}
		rs.close();
		return results;
	}
	
	// TODO Faire la javadoc
	// TODO Ne récupérer que les profils contenus dans le sous dossier
	// "Profil SEDA" correspondant
	/**
	 * Not used anymore
	 * @param folder
	 * @return
	 */
	@Deprecated
	public List<String> getProfilsSEDAListInSubFolder(NodeRef folder) {
		List<String> results = new ArrayList<>();

		LOG.info("Récupération de la liste des profils SEDA");
		SearchParameters sp = new SearchParameters();
		sp.addStore(Repository.getStoreRef());
		sp.setLanguage(SearchService.LANGUAGE_LUCENE);
		ResultSet rs = null;
		
		sp.setQuery("ASPECT:\"saem:profil\" AND PATH:\""+nodeService.getPath(folder).toPrefixString(namespaceService)+"//*\"");
		rs = searchService.query(sp);
		
		
		for (ResultSetRow row : rs) {
			NodeRef currentNodeRef = row.getNodeRef();
			String profilName = (String) nodeService.getProperty(currentNodeRef, ContentModel.PROP_NAME);
			results.add(profilName);
		}

		rs.close();
		return results;
	}

	public NodeRef getProfilSEDA(String Name, NodeRef folder) {
		LOG.info("Récupération du profil SEDA " + Name);
		SearchParameters sp = new SearchParameters();
		sp.addStore(Repository.getStoreRef());
		sp.setLanguage(SearchService.LANGUAGE_LUCENE);
		// TODO @cm\\:name a changer peut être par un truc comme ContentModel.PROP_NAME.truc
		// critere 
		sp.setQuery("ASPECT:\"saem:profil\" AND @cm\\:name:\""+Name+"\"");
		ResultSet rs = searchService.query(sp);
		
		
		if (rs.length() > 1) {
			LOG.error("Plusieurs profils ont étés trouvés, veuillez contacter l'administrateur");
			return null;
		}
		else if(rs.length() == 0){
			LOG.error("Aucun profil n'a été trouvé, veuillez contacter l'administrateur");
			return null;
		}
		
		NodeRef result = rs.getRow(0).getNodeRef();
		rs.close();
		return result;
	}
	
	public Object getSedaObject(NodeRef nodeRef, QName propertyQName) {
		String xml = (String) nodeService.getProperty(nodeRef, propertyQName);

		Object SEDAObject = null;

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(new InputSource(new StringReader(xml)));
			doc.getDocumentElement().setAttribute("xmlns", "v0.2");
			SEDAObject = XMLLoader.SEDALoader(doc);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SecurityException
				| NoSuchMethodException | ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
			return null;
		}
		return SEDAObject;

	}

	// Setters pour spring
	public void setNodeService(NodeService nodeService) {
		this.nodeService = nodeService;
	}
	
	public void setSearchService(SearchService searchService) {
		this.searchService = searchService;
	}
	
	public void setNamespaceService(NamespaceService namespaceService) {
		this.namespaceService = namespaceService;
	}	

}
