package eu.akka.saem.alfresco.listener;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.alfresco.model.ContentModel;
import org.alfresco.repo.node.NodeServicePolicies;
import org.alfresco.repo.node.NodeServicePolicies.OnAddAspectPolicy;
import org.alfresco.repo.policy.Behaviour;
import org.alfresco.repo.policy.JavaBehaviour;
import org.alfresco.repo.policy.PolicyComponent;
import org.alfresco.service.cmr.model.FileFolderService;
import org.alfresco.service.cmr.repository.ContentIOException;
import org.alfresco.service.cmr.repository.ContentReader;
import org.alfresco.service.cmr.repository.ContentService;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.cmr.search.ResultSet;
import org.alfresco.service.cmr.search.SearchParameters;
import org.alfresco.service.cmr.search.SearchService;
import org.alfresco.service.namespace.NamespaceService;
import org.alfresco.service.namespace.QName;
import org.alfresco.util.FileNameValidator;
import org.alfresco.web.bean.repository.Repository;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.xml.sax.SAXException;

import eu.akka.saem.alfresco.constants.SAEMModelConstants;
import eu.akka.saem.alfresco.seda.SEDADocument;
import eu.akka.saem.alfresco.seda.SEDAFolder;
import eu.akka.saem.alfresco.seda.loader.RNGLoader;
import eu.akka.saem.alfresco.seda.loader.XMLLoader;
import eu.akka.saem.alfresco.seda.v02.ArchiveTransferType;
import eu.akka.saem.alfresco.seda.v02.complexType.ArchiveObjectType;
import eu.akka.saem.alfresco.seda.v02.complexType.ArchiveType;
import eu.akka.saem.alfresco.seda.v02.complexType.DocumentType;
import eu.akka.saem.alfresco.seda.writer.SEDAWriter;
import eu.akka.saem.alfresco.utils.BrowsableSEDAUtils;
import eu.akka.saem.alfresco.utils.ProfilSEDAUtils;

/**
 * 
 *   Classe d'écoute qui se lance aprés chaque positionnement
 *   de l'aspect Archivable sur un nodeRef
 * 
 * @Class         : AfterAddArchivableAspectListener.java
 * @Package       : eu.akka.saem.alfresco.listener
 * @Date          : $Date: 25 févr. 2014 $:
 * @author        : $Author: THOMAS.POGNANT $
 * @version       : $Revision:  $:
 * @Id            : $Id: AfterAddArchivableAspectListener.java $
 *
 */
@Deprecated
public class AfterAddArchivableAspectListener implements OnAddAspectPolicy, InitializingBean {

	private static final Log LOG = LogFactory.getLog(AfterAddArchivableAspectListener.class);

	private PolicyComponent policyComponent;
	private FileFolderService fileFolderService;
	private NodeService nodeService;
	private ContentService contentService;
	private ProfilSEDAUtils profilSEDAUtils;
	private SearchService searchService;
	private NamespaceService namespaceService;

	@Override
	public void onAddAspect(NodeRef nodeRef, QName aspectTypeQName) {

		NodeRef nodeRefProfilableFolder = getProfilableParentFolder(nodeRef);
		if (nodeRefProfilableFolder == null) {
			LOG.error("Pas de profil SEDA associé à ce dossier, l'arborescence ne pourra pas être créé");
			LOG.error("Nom de l'archive concerné : "
					+ nodeService.getProperty(nodeRef, ContentModel.PROP_NAME));
			return;
		}

		String profilName = (String) nodeService.getProperty(nodeRefProfilableFolder,
				SAEMModelConstants.PROP_PROFIL_NAME);
		if (profilName == null || profilName == "") {
			LOG.error("Pas de profil SEDA associé à ce dossier, l'arborescence ne pourra pas être créé");
			LOG.error("Nom de l'archive concerné : "
					+ nodeService.getProperty(nodeRef, ContentModel.PROP_NAME));
		}
		NodeRef profilSEDANodeRef = profilSEDAUtils.getProfilSEDA(profilName, nodeRefProfilableFolder);

		// Récupération du fichier RNG
		ContentReader reader = contentService.getReader(profilSEDANodeRef, ContentModel.PROP_CONTENT);
		try {
			ArchiveTransferType profilSEDA;
			if(profilName.split("\\.")[1].equals(".rng")){
				profilSEDA = (ArchiveTransferType) RNGLoader.ArchiveTransferLoader(
					reader.getContentInputStream()).getObject();
			}else{
				profilSEDA = (ArchiveTransferType) XMLLoader.SEDALoader(reader.getContentInputStream());
			}
			
			BrowsableSEDAUtils bsu = new BrowsableSEDAUtils(profilSEDA);

			// Creer tous les sous repertoire ainsi que leur sous repertoire
			List<SEDAFolder> subfolders = bsu.getRoot().getSubFolders();

			for (SEDAFolder subfolder : subfolders) {
				createFolderRecursif(subfolder, nodeRef);
			}

		} catch (ContentIOException | ClassNotFoundException | InstantiationException
				| IllegalAccessException | SecurityException | NoSuchMethodException
				| IllegalArgumentException | InvocationTargetException | ParserConfigurationException
				| SAXException | IOException e) {
			LOG.error("Une erreur s'est produite au parsing du profil SEDA", e);
		}
	}

	/**
	 * Permet de récupérer le nodeRef du parent
	 * "profilable le plus proche d'un noeud"
	 * 
	 * @param nodeRef
	 * @return le noderef du dossier profilable, null si il n'a pas de dossier
	 *         profilable
	 */
	private NodeRef getProfilableParentFolder(NodeRef nodeRef) {
		if (nodeRef == null) {
			return null;
		}
		if (nodeService.hasAspect(nodeRef, SAEMModelConstants.ASPECT_PROFILABLE)) {
			return nodeRef;
		}
		return getProfilableParentFolder(nodeService.getPrimaryParent(nodeRef).getParentRef());
	}

	/**
	 * Parcours l'objet SEDAFolder et crée automatiquement les dossiers et sous
	 * dossiers correspondants
	 * 
	 * @param folder
	 * @param nodeRef
	 */
	private void createFolderRecursif(SEDAFolder folder, NodeRef nodeRef) {

		String name = folder.getName();
		name = StringUtils.trim(name);

		if (name == null || name == "") {
			name = Long.toString(new Date().getTime());
		} else if (!FileNameValidator.isValid(name)) {
			name = FileNameValidator.getValidFileName(name);
		}
		StringUtils.trim(name);

		String description = folder.getDescription();
		StringUtils.trim(description);

		//Vérification qu'un dossier du meme nom n'existe pas déjà
		//FIXME LA requete n'est pas bonne
		SearchParameters sp = new SearchParameters();
		sp.addStore(Repository.getStoreRef());
		sp.setLanguage(SearchService.LANGUAGE_LUCENE);
		sp.setQuery("PATH:\""+nodeService.getPath(nodeRef).toPrefixString(namespaceService) + "//*\"" + " AND @cm\\:name:" + name +"*");
		ResultSet rs = searchService.query(sp);
		if(rs.length() != 0){
			name = name + (rs.length() + 1);
		}

		// Création du dossier dans Alfresco
		NodeRef newFolderNodeRef = fileFolderService.create(nodeRef, name, ContentModel.TYPE_FOLDER)
				.getNodeRef();
		// Ajout de la description au dossier dans Alfresco
		nodeService.setProperty(newFolderNodeRef, ContentModel.PROP_DESCRIPTION, description);
		// Ajout de l'aspect relatif aux dossiers SEDA
		nodeService.addAspect(newFolderNodeRef, SAEMModelConstants.ASPECT_SOUS_OBJET_ARCHIVE, null);

		List<ArchiveObjectType> subfoldersSave = null;
		List<DocumentType> documentsSave = null;
		// Récupération du XML et positionnement dans les propriétés
		//Suppression des contains pour qu'ils ne poluent pas le xml
		if(folder.getSEDARelatedObject().getClass().getName().equals(ArchiveObjectType.class.getName())){
			ArchiveObjectType SEDARelatedObjet = (ArchiveObjectType) folder.getSEDARelatedObject();
			subfoldersSave = SEDARelatedObjet.getContains();
			SEDARelatedObjet.setContains(null);
			documentsSave = SEDARelatedObjet.getDocument();
			SEDARelatedObjet.setDocument(null);
		}
		//Suppression des documents pour qu'ils ne poluent pas le xml
		if(folder.getSEDARelatedObject().getClass().getName().equals(ArchiveType.class.getName())){
			ArchiveType SEDARelatedObjet = (ArchiveType) folder.getSEDARelatedObject();
			subfoldersSave = SEDARelatedObjet.getContains();
			SEDARelatedObjet.setContains(null);
			documentsSave = SEDARelatedObjet.getDocument();
			SEDARelatedObjet.setDocument(null);
		}
		//Suppression des documents pour qu'ils ne poluent pas le xml
		if(folder.getSEDARelatedObject().getClass().getName().equals(ArchiveTransferType.class.getName())){
			ArchiveTransferType SEDARelatedObjet = (ArchiveTransferType) folder.getSEDARelatedObject();
			subfoldersSave = SEDARelatedObjet.getContains().get(0).getContains();
			SEDARelatedObjet.getContains().get(0).setContains(null);
			documentsSave = SEDARelatedObjet.getContains().get(0).getDocument();
			SEDARelatedObjet.getContains().get(0).setDocument(null);
		}
		
		nodeService.setProperty(newFolderNodeRef, SAEMModelConstants.PROP_SEDA_SOUS_OBJET_ARCHIVE_XML,
							SEDAWriter.transformSEDAObjectToXML(folder.getSEDARelatedObject()));
		
		//Repositionnement 
		if(folder.getSEDARelatedObject().getClass().getName().equals(ArchiveObjectType.class.getName())){
			ArchiveObjectType SEDARelatedObjet = (ArchiveObjectType) folder.getSEDARelatedObject();
			SEDARelatedObjet.setContains(subfoldersSave);
			SEDARelatedObjet.setDocument(documentsSave);
		}
		//Repositionnement 
		if(folder.getSEDARelatedObject().getClass().getName().equals(ArchiveType.class.getName())){
			ArchiveType SEDARelatedObjet = (ArchiveType) folder.getSEDARelatedObject();
			SEDARelatedObjet.setContains(subfoldersSave);
			SEDARelatedObjet.setDocument(documentsSave);
		}
		if(folder.getSEDARelatedObject().getClass().getName().equals(ArchiveTransferType.class.getName())){
			ArchiveTransferType SEDARelatedObjet = (ArchiveTransferType) folder.getSEDARelatedObject();
			SEDARelatedObjet.getContains().get(0).setContains(subfoldersSave);
			SEDARelatedObjet.getContains().get(0).setDocument(documentsSave);
		}
		
		// Création des documents associés
		createDocuments(folder.getDocuments(), newFolderNodeRef);

		// Création des sous-dossiers
		for (SEDAFolder subfolder : folder.getSubFolders()) {
			createFolderRecursif(subfolder, newFolderNodeRef);
		}
	}

	private void createDocuments(List<SEDADocument> documents, NodeRef destination) {

		for (SEDADocument doc : documents) {
			String name = doc.getName();
			StringUtils.trim(name);
			name = FileNameValidator.getValidFileName(name);

			String description = doc.getDescription();
			StringUtils.trim(description);
			
			//Vérification qu'un dossier du meme nom n'existe pas déjà
			//FIXME La requete n'est pas bonne
			SearchParameters sp = new SearchParameters();
			sp.addStore(Repository.getStoreRef());
			sp.setLanguage(SearchService.LANGUAGE_LUCENE);
			sp.setQuery("PATH:\""+nodeService.getPath(destination).toPrefixString(namespaceService) + "//*\"" + " AND @cm\\:name:" + name +"*");
			ResultSet rs = searchService.query(sp);
			if(rs.length() != 0){
				name = name + (rs.length() + 1);
			}
			// Création du dossier dans Alfresco
			NodeRef newDocumentNodeRef = fileFolderService.create(destination, name, ContentModel.TYPE_CONTENT)
					.getNodeRef();
			// Ajout de la description au dossier dans Alfresco
			nodeService.setProperty(newDocumentNodeRef, ContentModel.PROP_DESCRIPTION, description);

			nodeService.addAspect(newDocumentNodeRef, SAEMModelConstants.ASPECT_DOCUMENT_ARCHIVE, null);

			// Récupération du XML et positionnement dans les propriétés
			nodeService.setProperty(newDocumentNodeRef, SAEMModelConstants.PROP_SEDA_DOCUMENT_XML,
					SEDAWriter.transformSEDAObjectToXML(doc.getSEDARelatedObject()));
			
			//TODO utiliser le contentservice pour uploader le fichier
		}

	}

	/**
	 * Bind de la classe sur l'evenenent d'ajout de l'aspect archivable
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		policyComponent.bindClassBehaviour(NodeServicePolicies.OnAddAspectPolicy.QNAME,
				SAEMModelConstants.ASPECT_ARCHIVABLE, new JavaBehaviour(this, "onAddAspect",
						Behaviour.NotificationFrequency.TRANSACTION_COMMIT));
	}
}
