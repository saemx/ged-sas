package eu.akka.saem.alfresco.webscript;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.rpc.ServiceException;

import org.alfresco.model.ContentModel;
import org.alfresco.repo.content.MimetypeMap;
import org.alfresco.service.cmr.model.FileExistsException;
import org.alfresco.service.cmr.model.FileFolderService;
import org.alfresco.service.cmr.repository.ContentReader;
import org.alfresco.service.cmr.repository.ContentService;
import org.alfresco.service.cmr.repository.ContentWriter;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.cmr.search.ResultSet;
import org.alfresco.service.cmr.search.SearchParameters;
import org.alfresco.service.cmr.search.SearchService;
import org.alfresco.service.namespace.NamespaceService;
import org.alfresco.service.namespace.QName;
import org.alfresco.util.FileNameValidator;
import org.alfresco.web.bean.repository.Repository;
import org.apache.axis.encoding.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.extensions.webscripts.AbstractWebScript;
import org.springframework.extensions.webscripts.WebScriptRequest;
import org.springframework.extensions.webscripts.WebScriptResponse;
import org.xml.sax.SAXException;

import eu.akka.saem.alfresco.connector.asalae.ws.ServerServiceLocator;
import eu.akka.saem.alfresco.connector.asalae.ws.WebServicePortType;
import eu.akka.saem.alfresco.constants.SAEMArchiveStates;
import eu.akka.saem.alfresco.constants.SAEMModelConstants;
import eu.akka.saem.alfresco.constants.SAEMPropertiesConstants;
import eu.akka.saem.alfresco.exception.FileAlreadyExistException;
import eu.akka.saem.alfresco.exception.NoDestinationException;
import eu.akka.saem.alfresco.exception.NoProfilableFolderException;
import eu.akka.saem.alfresco.helper.PropertyReader;
import eu.akka.saem.alfresco.helper.ServiceVersantHelper;
import eu.akka.saem.alfresco.seda.SEDADocument;
import eu.akka.saem.alfresco.seda.SEDAFolder;
import eu.akka.saem.alfresco.seda.form.CreateArchiveFormResult;
import eu.akka.saem.alfresco.seda.form.HTMLForm;
import eu.akka.saem.alfresco.seda.loader.XMLLoader;
import eu.akka.saem.alfresco.seda.v02.ArchiveTransferType;
import eu.akka.saem.alfresco.seda.v02.complexType.ArchiveObjectType;
import eu.akka.saem.alfresco.seda.v02.complexType.ArchiveType;
import eu.akka.saem.alfresco.seda.v02.complexType.DocumentType;
import eu.akka.saem.alfresco.seda.writer.SEDAWriter;
import eu.akka.saem.alfresco.utils.BrowsableSEDAUtils;
import eu.akka.saem.alfresco.utils.Utils;

/**
 * 
 *   Classe de creation des archives dans Asalae
 * 
 * @Class         : CreateArchiveBeanPost.java
 * @Package       : eu.akka.saem.alfresco.webscript
 * @Date          : $Date: 25 févr. 2014 $:
 * @author        : $Author: THOMAS.POGNANT $
 * @version       : $Revision:  $:
 * @Id            : $Id: CreateArchiveBeanPost.java $
 *
 */
public class CreateArchiveBeanPost extends AbstractWebScript {

	private static final String MESSAGE_JSON = "message";
	private static final String ERROR_JSON = "error";
	private static final Log LOG = LogFactory
			.getLog(CreateArchiveBeanPost.class);
	private FileFolderService fileFolderService;
	private NodeService nodeService;
	private ContentService contentService;
	private SearchService searchService;
	private NamespaceService namespaceService;
	private PropertyReader propertyReader;
	private ServiceVersantHelper serviceVersantHelper;

	public static File createTempDir(String prefix) throws IOException {
		File temp = File.createTempFile(prefix+Long.toString(new Date().getTime()), "",
				new File(System.getProperty("java.io.tmpdir")));
		temp.delete();
		temp.mkdir();
		return temp;
	}
	
	public InputStream getInputStreamInTmp(String name,File tmpdir){
		for(File f:tmpdir.listFiles()){
			if(f.getName().equals(name)){
				try {
					return new FileInputStream(f);
				} catch (FileNotFoundException e) {
					return null;
				}
			}
		}
		
		return null;
	}
	
	public static void delete(File file)
	    	throws IOException{
	 
	    	if(file.isDirectory()){
	 
	    		//directory is empty, then delete it
	    		if(file.list().length==0){
	 
	    		   file.delete();
	    		   System.out.println("Directory is deleted : " + file.getAbsolutePath());
	    		}else{
	 
	    		   //list all the directory contents
	        	   String files[] = file.list();
	 
	        	   for (String temp : files) {
	        	      //construct the file structure
	        	      File fileDelete = new File(file, temp);
	 
	        	      //recursive delete
	        	     delete(fileDelete);
	        	   }
	 
	        	   //check the directory again, if empty then delete it
	        	   if(file.list().length==0){
	           	     file.delete();
	        	     System.out.println("Directory is deleted : " + file.getAbsolutePath());
	        	   }
	    		}
	    	}else{
	    		//if file, then delete it
	    		file.delete();
	    		System.out.println("File is deleted : " + file.getAbsolutePath());
	    	}
	    }
	
	public void deleteFile(File tmpDir){
		try {
			delete(tmpDir);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public File createFile(InputStream inputStream, File tmpDir,String name) {
		File file = null;
		if (tmpDir.isDirectory()) {
			try {

				file = new File(tmpDir.getAbsolutePath()+"/"+name);
				// write the inputStream to a FileOutputStream
				OutputStream out = new FileOutputStream(file);

				int read = 0;
				byte[] bytes = new byte[1024];

				while ((read = inputStream.read(bytes)) != -1) {
					out.write(bytes, 0, read);
				}

				inputStream.close();
				out.flush();
				out.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return file;
	}

	@Override
	public void execute(WebScriptRequest req, WebScriptResponse res)
			throws IOException {
		CreateArchiveFormResult cafr = new CreateArchiveFormResult();

		NodeRef archivableFolderNodeRef = null;
		JSONObject jsonresult = new JSONObject();
		File f = null;
		File faccuse = null;

		try {
			jsonresult.put(ERROR_JSON, false);

			if (req.getParameter("action") != null
					&& req.getParameter("action").equals("save")) {
				cafr.setArchiveTransferType((ArchiveTransferType) HTMLForm
						.getHTMLForm(req.getParameter("id")).getSedaObject());
				cafr.setAGAPEObject(HTMLForm
						.getHTMLForm(req.getParameter("id")).getAGAPEObject());

				if (req.getParameter("profil") != null
						&& !req.getParameter("profil").isEmpty()) {
					cafr.setProfil(req.getParameter("profil"));
				}

				if (req.getParameter("destination") != null
						&& !req.getParameter("destination").isEmpty()) {
					cafr.setDestination(req.getParameter("destination"));
//					if(cafr.getDestination().equals("findlater")){
//						NodeRef profil = new NodeRef(cafr.getProfil());
//						
//						String profilname = (String) nodeService.getProperty(profil, ContentModel.PROP_NAME);
//						SearchParameters sp = new SearchParameters();
//						sp.addStore(Repository.getStoreRef());
//						sp.setLanguage(SearchService.LANGUAGE_LUCENE);
//						sp.setQuery("ASPECT:\"saem:profilable\" AND @saem\\:profilName:\""+profilname+"\"");
//						ResultSet queryResults = searchService.query(sp);
//						
//						if (queryResults != null && queryResults.getNodeRefs().size() > 0 && queryResults.getNodeRef(0) != null){
//							cafr.setDestination(queryResults.getNodeRef(0).toString());
//						}
//					}
				}

			} else if (req.getParameter("action") != null
					&& req.getParameter("action").equals("edit")) {
				cafr.setArchiveTransferType((ArchiveTransferType) HTMLForm
						.getHTMLForm(req.getParameter("id")).getSedaObject());
				cafr.setAGAPEObject(HTMLForm
						.getHTMLForm(req.getParameter("id")).getAGAPEObject());
				
				f = createTempDir("file");
				faccuse = createTempDir("accuse");
				sendFileInTmp(req.getParameter("archive"),f);	
				sendAccuseFileInTMP(req.getParameter("archive"),faccuse);
				
				cafr.setProfil((String) nodeService.getProperty(new NodeRef(req.getParameter("archive")),
								SAEMModelConstants.PROP_SEDA_PROFIL_USE_NODEREF)); 
				
				cafr.setDestination(nodeService.getPrimaryParent(new NodeRef(req.getParameter("archive"))).getChildRef().toString());
				
				nodeService.deleteNode(new NodeRef(req.getParameter("archive")));
			}

		} catch (SecurityException | IllegalArgumentException | JSONException e) {
			LOG.error("Erreur lors de la création de l'archive", e);
			try {
				jsonresult.put(ERROR_JSON, true);
				jsonresult.put(MESSAGE_JSON, "Erreur système (bug) lors de la lecture du formulaire");
			} catch (JSONException e1) {
				LOG.error("Erreur lors du traitement de l'erreur (message de retour)", e1);
			}
		}

		try {
			ServerServiceLocator ssl = new ServerServiceLocator();
			WebServicePortType test = ssl
					.getWebServicePort(new URL(
							propertyReader
									.getProperty(SAEMPropertiesConstants.ASALAE_INT_URL)
									+ "/webservices/service"));
			String bordereau = SEDAWriter.transformSEDAObjectToXML(cafr
					.getArchiveTransferType());
			// Récupération des identifiants de connexion à asalae
			String asalaeLogin = propertyReader
					.getProperty(SAEMPropertiesConstants.ASALAE_INT_LOGIN);
			String asalaePassword = propertyReader
					.getProperty(SAEMPropertiesConstants.ASALAE_INT_PASSWORD);
			String bordereauSEDA = new String(
					Base64.decode(new String(test.wsCompleterBordereau(Base64
							.encode(bordereau.getBytes("UTF8")).getBytes(),
							asalaeLogin, asalaePassword))), "UTF8");

			InputStream result = new ByteArrayInputStream(
					bordereauSEDA.getBytes());
			result.reset();
			cafr.setArchiveTransferType((ArchiveTransferType) XMLLoader
					.SEDALoader(result));
			// get appraisal
			ArchiveTransferType att = cafr.getArchiveTransferType();
			ArchiveType at = null;
			if (att.getContains() != null)
				at = att.getContains().get(0);

			BrowsableSEDAUtils bsu = new BrowsableSEDAUtils(
					cafr.getArchiveTransferType());

			if (cafr.getDestination() == null || cafr.getDestination() == "") {
				throw new NoDestinationException(
						"La destination n'est pas saisie, la création de l'archive est avortée");
			}

			NodeRef destination = new NodeRef(cafr.getDestination());
			NodeRef profilNodeRef = new NodeRef(cafr.getProfil());
			String profilName = (String) nodeService.getProperty(profilNodeRef,
					ContentModel.PROP_NAME);

			// Créer le dossier archivable au bon endroit
			archivableFolderNodeRef = createArchivableFolder(bsu.getRoot(),
					destination, profilName, cafr.getArchiveTransferType()
							.getTransferIdentifier().getValue(), at);
			
			//Ajout du profil name sur l'archive afin de pouvoir l'afficher dasn la dashlet "Mes traitements"
			if(cafr != null && cafr.getProfil() != null && archivableFolderNodeRef != null){
				String archiveProfilname = (String) nodeService.getProperty(new NodeRef(cafr.getProfil()), ContentModel.PROP_NAME);
				nodeService.addAspect(archivableFolderNodeRef, SAEMModelConstants.ASPECT_PROFIL_NAME, null);
				nodeService.setProperty(archivableFolderNodeRef, SAEMModelConstants.PROP_ARCHIVE_PROFIL_NAME, archiveProfilname);
			}
			
			if(faccuse!=null){
				String id = (String) nodeService.getProperty(archivableFolderNodeRef, SAEMModelConstants.PROP_SEDA_ARCHIVE_ARCHIVAL_AGENCY_ARCHIVE_IDENTIFIER);
				
				for(File fac:faccuse.listFiles()){
					if(fac.getName().startsWith(id+"__")){
						Map<QName, Serializable> props = new HashMap<QName, Serializable>(1);
						props.put(ContentModel.PROP_NAME, fac.getName());
						NodeRef createdFile = nodeService.createNode(archivableFolderNodeRef, ContentModel.ASSOC_CONTAINS,
						QName.createQName(NamespaceService.CONTENT_MODEL_1_0_URI, fac.getName()),
								ContentModel.TYPE_CONTENT, props).getChildRef();
	
						ContentWriter contentWriter = contentService.getWriter(createdFile,
								ContentModel.PROP_CONTENT, true);
						contentWriter.setMimetype(MimetypeMap.MIMETYPE_TEXT_PLAIN);
						contentWriter.setEncoding("UTF-8");
						InputStream in = getInputStreamInTmp(fac.getName(),faccuse);
						contentWriter.putContent(in);
	
						nodeService.addAspect(createdFile, SAEMModelConstants.ASPECT_IS_ACCUSE,null);
					}
				}
			}

			nodeService.setProperty(archivableFolderNodeRef,
					SAEMModelConstants.PROP_SEDA_ARCHIVE_PROFIL_MODEL, cafr
							.getAGAPEObject().toJSON());
			
			nodeService.setProperty(archivableFolderNodeRef,
					SAEMModelConstants.PROP_SEDA_PROFIL_USE_NODEREF, cafr.getProfil());

			List<NodeRef> createdDocuments = new ArrayList<>();
			// Créer les documents du dossier archivable
			createdDocuments.addAll(createDocuments(bsu.getRoot()
					.getSubFolders().get(0).getDocuments(),
					archivableFolderNodeRef));

			// Creer tous les sous repertoire ainsi que leur sous repertoire
			List<SEDAFolder> subfolders = bsu.getRoot().getSubFolders().get(0)
					.getSubFolders();

			for (SEDAFolder subfolder : subfolders) {
				createdDocuments.addAll(createFolderRecursif(subfolder,
						archivableFolderNodeRef,faccuse));
			}
			
			//Mise en place de la propriété "pré versement"
			Utils.setArchiveState(nodeService, archivableFolderNodeRef, SAEMArchiveStates.NORMAL);
			
			res.setContentEncoding("UTF-8");
			res.setContentType("application/json");

			jsonresult.put("redirection", archivableFolderNodeRef.toString());
			JSONArray array = new JSONArray();
			for (NodeRef nr : createdDocuments) {
				JSONObject o = new JSONObject();
				o.put("dest", nr.toString());
				
				String nodeName = (String) nodeService.getProperty(nr, ContentModel.PROP_NAME);
				
				if(f!=null){
					InputStream in = getInputStreamInTmp(nodeName,f);
					if(in==null)
						o.put("name", nodeName);
					else{
						try{
							ContentWriter writer = contentService.getWriter(nr, ContentModel.PROP_CONTENT, true);
							writer.putContent(in);
							o.put("name", nodeName);							
						}
						catch(Exception ex){
							o.put("name", nodeName);
						}						
					}
				}
				else{
					o.put("name", nodeName);
				}
				array.put(o);
			}
			jsonresult.put("filesDestinations", array);
			
			if(f!=null){
				deleteFile(f);
			}
			
			if(faccuse!=null){
				deleteFile(faccuse);
			}

		} catch (FileAlreadyExistException e) {
			try {
				jsonresult.put(ERROR_JSON, true);
				jsonresult.put(MESSAGE_JSON, "Le document " + e.getName()
						+ " est en double");
				LOG.error("Erreur lors de la soummission d'un formulaire", e);
			} catch (JSONException e1) {
				LOG.error("Erreur lors du renvoi du JSON d'erreur", e1);
			}
		} catch (NoDestinationException e) {
			try {
				jsonresult.put(ERROR_JSON, true);
				jsonresult
						.put(MESSAGE_JSON,
								"Erreur système (bug) la destination n'a pas pu être calculée");
				LOG.error("Erreur lors de la soummission d'un formulaire", e);
			} catch (JSONException e1) {
				LOG.error("Erreur lors du renvoi du JSON d'erreur", e1);
			}
		} catch (JSONException e) {
			try {
				jsonresult.put(ERROR_JSON, true);
				jsonresult.put(MESSAGE_JSON,
						"Erreur système (bug) de formattage du JSON");
				LOG.error("Erreur lors de la soummission d'un formulaire", e);
			} catch (JSONException e1) {
				LOG.error("Erreur lors du renvoi du JSON d'erreur", e1);
			}
		} catch (NoProfilableFolderException e) {
			try {
				jsonresult.put(ERROR_JSON, true);
				jsonresult.put(MESSAGE_JSON,
						"Aucun dossier profilable n'est associé à ce profil");
				LOG.error("Erreur lors de la soummission d'un formulaire", e);
			} catch (JSONException e1) {
				LOG.error("Erreur lors du renvoi du JSON d'erreur", e1);
			}
		} catch (ServiceException e) {
			try {
				jsonresult.put(ERROR_JSON, true);
				jsonresult.put(MESSAGE_JSON, "Asalae n'est pas joignable");
				LOG.error("Erreur lors de la soummission d'un formulaire", e);
			} catch (JSONException e1) {
				LOG.error("Erreur lors du renvoi du JSON d'erreur", e1);
			}
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | SecurityException
				| NoSuchMethodException | ParserConfigurationException
				| SAXException e) {
			try {
				jsonresult.put(ERROR_JSON, true);
				jsonresult
						.put(MESSAGE_JSON,
								"Erreur système (bug) lors de la lecture du formulaire");
				LOG.error("Erreur lors de la soummission d'un formulaire", e);
			} catch (JSONException e1) {
				LOG.error("Erreur lors du renvoi du JSON d'erreur", e1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			res.getWriter().write(jsonresult.toString());
		}
	}


	private void sendAccuseFileInTMP(String parameter, File faccuse) {
		NodeRef archive = new NodeRef(parameter);
		
		SearchParameters sp = new SearchParameters();
		sp.addStore(Repository.getStoreRef());
		sp.setLanguage(SearchService.LANGUAGE_LUCENE);
		sp.setQuery("PATH:\"" + nodeService.getPath(archive).toPrefixString(namespaceService)
				+ "//*\"" + " AND ASPECT:\"saem:is_accuse\"");
		ResultSet rs = searchService.query(sp);
		List<NodeRef> nodes = rs.getNodeRefs();
		
		for(NodeRef n:nodes){
			QName nodeQnameType = nodeService.getType(n);
			String nodeName = (String) nodeService.getProperty(n, ContentModel.PROP_NAME);

			ContentReader reader = contentService.getReader(n, ContentModel.PROP_CONTENT);
			if (reader != null) {
				InputStream is = reader.getContentInputStream();
				createFile(is, faccuse,nodeName);				
			}
		}
	}
	

	private void sendFileInTmp(String parameter,File tmp) {
		NodeRef archive = new NodeRef(parameter);
		
		SearchParameters sp = new SearchParameters();
		sp.addStore(Repository.getStoreRef());
		sp.setLanguage(SearchService.LANGUAGE_LUCENE);
		sp.setQuery("PATH:\"" + nodeService.getPath(archive).toPrefixString(namespaceService)
				+ "//*\"" + " AND ASPECT:\"saem:document_archive\"");
		ResultSet rs = searchService.query(sp);
		List<NodeRef> nodes = rs.getNodeRefs();
		
		for(NodeRef n:nodes){
			QName nodeQnameType = nodeService.getType(n);
			String nodeName = (String) nodeService.getProperty(n, ContentModel.PROP_NAME);

			ContentReader reader = contentService.getReader(n, ContentModel.PROP_CONTENT);
			if (reader != null) {
				InputStream is = reader.getContentInputStream();
				createFile(is, tmp,nodeName);				
			}
		}
	}


	private NodeRef createArchivableFolder(SEDAFolder root,
			NodeRef destination, String profil, String asalaeid, ArchiveType at)
			throws Exception {

		SearchParameters sp = new SearchParameters();
		sp.addStore(Repository.getStoreRef());
		sp.setLanguage(SearchService.LANGUAGE_LUCENE);
		sp.setQuery("PATH:\""
				+ nodeService.getPath(
						new NodeRef(serviceVersantHelper
								.getDocumentLibraryNodeRef())).toPrefixString(
						namespaceService) + "//*\""
				+ " AND ASPECT:\"saem\\:profilable\""
				+ " AND @saem\\:profilName:\"" + profil + "\"");
		ResultSet rs = searchService.query(sp);
		if (rs.length() == 0) {
			throw new NoProfilableFolderException(
					"Aucun dossier profilable associé au profil sélectionné");
		}
		NodeRef destinationFolder = rs.getRow(0).getNodeRef();

		searchService.query(sp);
		String name = root.getSubFolders().get(0).getName();
		name = StringUtils.trim(name);

		if (name == null || name == "") {
			name = Long.toString(new Date().getTime());
		} else if (!FileNameValidator.isValid(name)) {
			name = FileNameValidator.getValidFileName(name);
		}
		StringUtils.trim(name);

		NodeRef archivableFolderNodeRef = null;
		try {
			archivableFolderNodeRef = fileFolderService.create(
					destinationFolder, name, ContentModel.TYPE_FOLDER)
					.getNodeRef();
		} catch (FileExistsException e) {
			throw new FileAlreadyExistException(e);
		}

		String appraisal = null;
		if (at != null && at.getAppraisal() != null)
			appraisal = at.getAppraisal().getCode().getValue().toString();

		if (appraisal != null && appraisal.equalsIgnoreCase("detruire"))
			nodeService.addAspect(archivableFolderNodeRef,
					SAEMModelConstants.ASPECT_ELIMINABLE, null);

		nodeService
				.setProperty(archivableFolderNodeRef,
						SAEMModelConstants.PROP_SEDA_VERSEMENT_ACKNOWLEDGMENT,
						asalaeid);
		System.out.println("Identifiant accusé transfert"
				+ nodeService.getProperty(archivableFolderNodeRef,
						SAEMModelConstants.PROP_SEDA_VERSEMENT_ACKNOWLEDGMENT));

		nodeService.addAspect(archivableFolderNodeRef,
				SAEMModelConstants.ASPECT_ARCHIVABLE, null);
		nodeService.addAspect(archivableFolderNodeRef,
				SAEMModelConstants.ASPECT_NON_VERSE, null);

		String description = root.getSubFolders().get(0).getDescription();
		StringUtils.trim(description);
		nodeService.setProperty(archivableFolderNodeRef,
				ContentModel.PROP_DESCRIPTION, description);

		// TODO Cette propriété n'existe que parce que le code pour reconstituer
		// le XML dans l'ordre du bordeareau SEDA n'a pas été fait
		nodeService.setProperty(archivableFolderNodeRef,
				SAEMModelConstants.PROP_SEDA_ARCHIVE_XML_FULL, SEDAWriter
						.transformSEDAObjectToXML(root.getSEDARelatedObject()));

		List<ArchiveObjectType> subfoldersSave = null;
		List<DocumentType> documentsSave = null;
		saveAndDeleteSubElements(root.getSubFolders().get(0), subfoldersSave,
				documentsSave);
		nodeService.setProperty(archivableFolderNodeRef,
				SAEMModelConstants.PROP_SEDA_ARCHIVE_XML, SEDAWriter
						.transformSEDAObjectToXML(root.getSEDARelatedObject()));

		if (((ArchiveTransferType) root.getSEDARelatedObject()).getContains() != null) {
			ArchiveType localAt = ((ArchiveTransferType) root
					.getSEDARelatedObject()).getContains().get(0);
			if (localAt.getArchivalAgencyArchiveIdentifier() != null
					&& localAt.getArchivalAgencyArchiveIdentifier().getValue() != null) {
				nodeService
						.setProperty(
								archivableFolderNodeRef,
								SAEMModelConstants.PROP_SEDA_ARCHIVE_ARCHIVAL_AGENCY_ARCHIVE_IDENTIFIER,
								((ArchiveTransferType) root
										.getSEDARelatedObject()).getContains()
										.get(0)
										.getArchivalAgencyArchiveIdentifier()
										.getValue());
			}
		}

		// Restauration des sous elements
		restoreSubElements(root.getSubFolders().get(0), subfoldersSave,
				documentsSave);
		
		return archivableFolderNodeRef;
	}

	/**
	 * Parcours l'objet SEDAFolder et crée automatiquement les dossiers et sous
	 * dossiers correspondants
	 * 
	 * @param folder
	 * @param nodeRef
	 * @throws FileAlreadyExistException
	 */
	private List<NodeRef> createFolderRecursif(SEDAFolder folder,
			NodeRef nodeRef, File faccuse) throws FileAlreadyExistException {

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

		
		// Vérification qu'un dossier du meme nom n'existe pas déjà
		if(nodeService.getChildByName(nodeRef, ContentModel.PROP_NAME, name) != null){
			for (int i = 0; i < 100; i++){
				name += i;
				if(nodeService.getChildByName(nodeRef, ContentModel.PROP_NAME, name) == null){
					break;
				}
			}
		}

		// Création du dossier dans Alfresco
		NodeRef newFolderNodeRef = null;
		try {
			newFolderNodeRef = fileFolderService.create(nodeRef, name, ContentModel.TYPE_FOLDER).getNodeRef();
		} catch (FileExistsException e) {
			throw new FileAlreadyExistException(e);
		}
		// Ajout de la description au dossier dans Alfresco
		nodeService.setProperty(newFolderNodeRef, ContentModel.PROP_DESCRIPTION, description);
		// Ajout de l'aspect relatif aux dossiers SEDA
		nodeService.addAspect(newFolderNodeRef, SAEMModelConstants.ASPECT_SOUS_OBJET_ARCHIVE, null);

		// Récupération du XML et positionnement dans les propriétés
		// Suppression des sous elements pour qu'ils ne poluent pas le xml
		List<ArchiveObjectType> subfoldersSave = null;
		List<DocumentType> documentsSave = null;
		saveAndDeleteSubElements(folder, subfoldersSave, documentsSave);

		nodeService.setProperty(newFolderNodeRef, SAEMModelConstants.PROP_SEDA_SOUS_OBJET_ARCHIVE_XML,
				SEDAWriter.transformSEDAObjectToXML(folder.getSEDARelatedObject()));

		if (((ArchiveObjectType) folder.getSEDARelatedObject()).getAppraisal() != null) {
			if (((ArchiveObjectType) folder.getSEDARelatedObject()).getAppraisal().getCode() != null
					&& ((ArchiveObjectType) folder.getSEDARelatedObject()).getAppraisal().getCode().getValue()
							.toString().equalsIgnoreCase("detruire"))
				nodeService.addAspect(newFolderNodeRef, SAEMModelConstants.ASPECT_ELIMINABLE, null);
			LOG.info("Ajout de l'aspect éliminable sur le dossier : "
					+ nodeService.getProperty(newFolderNodeRef, ContentModel.PROP_NAME).toString());
		}

		if (((ArchiveObjectType) folder.getSEDARelatedObject()).getArchivalAgencyObjectIdentifier() != null) {
			if (((ArchiveObjectType) folder.getSEDARelatedObject()).getArchivalAgencyObjectIdentifier().getValue() != null) {
				nodeService.setProperty(newFolderNodeRef,
						SAEMModelConstants.PROP_SEDA_SOUS_OBJET_ARCHIVE_ARCHIVAL_AGENCY_OBJECT_IDENTIFIER,
						((ArchiveObjectType) folder.getSEDARelatedObject()).getArchivalAgencyObjectIdentifier()
								.getValue());
			}
		}
		
		if(faccuse!=null){
			String id = (String) nodeService.getProperty(newFolderNodeRef, SAEMModelConstants.PROP_SEDA_SOUS_OBJET_ARCHIVE_ARCHIVAL_AGENCY_OBJECT_IDENTIFIER);
			for(File fac:faccuse.listFiles()){
				if(fac.getName().startsWith(id+"__")){
					Map<QName, Serializable> props = new HashMap<QName, Serializable>(1);
					props.put(ContentModel.PROP_NAME, fac.getName());
					NodeRef createdFile = nodeService.createNode(newFolderNodeRef, ContentModel.ASSOC_CONTAINS,
					QName.createQName(NamespaceService.CONTENT_MODEL_1_0_URI, fac.getName()),
							ContentModel.TYPE_CONTENT, props).getChildRef();

					ContentWriter contentWriter = contentService.getWriter(createdFile,
							ContentModel.PROP_CONTENT, true);
					contentWriter.setMimetype(MimetypeMap.MIMETYPE_TEXT_PLAIN);
					contentWriter.setEncoding("UTF-8");
					InputStream in = getInputStreamInTmp(fac.getName(),faccuse);
					contentWriter.putContent(in);

					nodeService.addAspect(createdFile, SAEMModelConstants.ASPECT_IS_ACCUSE,null);
				}
			}
		}
		
		// Restauration des sous elements
		restoreSubElements(folder, subfoldersSave, documentsSave);

		// Création des documents associés
		List<NodeRef> createdDocuments = createDocuments(folder.getDocuments(),
				newFolderNodeRef);

		// Création des sous-dossiers
		for (SEDAFolder subfolder : folder.getSubFolders()) {
			createdDocuments.addAll(createFolderRecursif(subfolder,
					newFolderNodeRef,faccuse));
		}

		return createdDocuments;
	}

	private void saveAndDeleteSubElements(SEDAFolder folder,
			List<ArchiveObjectType> subfoldersSave,
			List<DocumentType> documentsSave) {
		// Suppression des contains
		if (folder.getSEDARelatedObject().getClass().getName()
				.equals(ArchiveObjectType.class.getName())) {
			ArchiveObjectType SEDARelatedObjet = (ArchiveObjectType) folder
					.getSEDARelatedObject();
			subfoldersSave = SEDARelatedObjet.getContains();
			SEDARelatedObjet.setContains(null);
			documentsSave = SEDARelatedObjet.getDocument();
			SEDARelatedObjet.setDocument(null);
		}
		// Suppression des documents
		if (folder.getSEDARelatedObject().getClass().getName()
				.equals(ArchiveType.class.getName())) {
			ArchiveType SEDARelatedObjet = (ArchiveType) folder
					.getSEDARelatedObject();
			subfoldersSave = SEDARelatedObjet.getContains();
			SEDARelatedObjet.setContains(null);
			documentsSave = SEDARelatedObjet.getDocument();
			SEDARelatedObjet.setDocument(null);
		}
		// Suppression des documents pour qu'ils ne poluent pas le xml
		if (folder.getSEDARelatedObject().getClass().getName()
				.equals(ArchiveTransferType.class.getName())) {
			ArchiveTransferType SEDARelatedObjet = (ArchiveTransferType) folder
					.getSEDARelatedObject();
			subfoldersSave = SEDARelatedObjet.getContains().get(0)
					.getContains();
			SEDARelatedObjet.getContains().get(0).setContains(null);
			documentsSave = SEDARelatedObjet.getContains().get(0).getDocument();
			SEDARelatedObjet.getContains().get(0).setDocument(null);
		}
	}

	private void restoreSubElements(SEDAFolder folder,
			List<ArchiveObjectType> subfoldersSave,
			List<DocumentType> documentsSave) {
		// Repositionnement des contains
		if (folder.getSEDARelatedObject().getClass().getName()
				.equals(ArchiveObjectType.class.getName())) {
			ArchiveObjectType SEDARelatedObjet = (ArchiveObjectType) folder
					.getSEDARelatedObject();
			SEDARelatedObjet.setContains(subfoldersSave);
			SEDARelatedObjet.setDocument(documentsSave);
		}
		// Repositionnement des documents
		if (folder.getSEDARelatedObject().getClass().getName()
				.equals(ArchiveType.class.getName())) {
			ArchiveType SEDARelatedObjet = (ArchiveType) folder
					.getSEDARelatedObject();
			SEDARelatedObjet.setContains(subfoldersSave);
			SEDARelatedObjet.setDocument(documentsSave);
		}
		if (folder.getSEDARelatedObject().getClass().getName()
				.equals(ArchiveTransferType.class.getName())) {
			ArchiveTransferType SEDARelatedObjet = (ArchiveTransferType) folder
					.getSEDARelatedObject();
			SEDARelatedObjet.getContains().get(0).setContains(subfoldersSave);
			SEDARelatedObjet.getContains().get(0).setDocument(documentsSave);
		}
	}

	private List<NodeRef> createDocuments(List<SEDADocument> documents,
			NodeRef destination) throws FileAlreadyExistException {

		List<NodeRef> results = new ArrayList<>();
		for (SEDADocument doc : documents) {
			String name = doc.getName();
			StringUtils.trim(name);
			name = FileNameValidator.getValidFileName(name);

			String description = doc.getDescription();
			StringUtils.trim(description);

			// Vérification qu'un dossier du meme nom n'existe pas déjà
			// FIXME La requete n'est pas bonne
			SearchParameters sp = new SearchParameters();
			sp.addStore(Repository.getStoreRef());
			sp.setLanguage(SearchService.LANGUAGE_LUCENE);
			sp.setQuery("PATH:\""
					+ nodeService.getPath(destination).toPrefixString(
							namespaceService) + "//*\"" + " AND @cm\\:name:"
					+ name + "*");
			ResultSet rs = searchService.query(sp);
			if (rs.length() != 0) {
				name = name + (rs.length() + 1);
			}
			
			
			// Création du dossier dans Alfresco
			NodeRef newDocumentNodeRef = null;
			try {
				newDocumentNodeRef = fileFolderService.create(destination,
						name, ContentModel.TYPE_CONTENT).getNodeRef();
			} catch (FileExistsException e) {
				throw new FileAlreadyExistException(e);
			}
			// Ajout de la description au dossier dans Alfresco
			nodeService.setProperty(newDocumentNodeRef,
					ContentModel.PROP_DESCRIPTION, description);

			nodeService.addAspect(newDocumentNodeRef,
					SAEMModelConstants.ASPECT_DOCUMENT_ARCHIVE, null);

			// Récupération du XML et positionnement dans les propriétés
			nodeService.setProperty(newDocumentNodeRef,
					SAEMModelConstants.PROP_SEDA_DOCUMENT_XML, SEDAWriter
							.transformSEDAObjectToXML(doc
									.getSEDARelatedObject()));

			results.add(newDocumentNodeRef);
		}
		return results;
	}

	public void setFileFolderService(FileFolderService fileFolderService) {
		this.fileFolderService = fileFolderService;
	}

	public void setNodeService(NodeService nodeService) {
		this.nodeService = nodeService;
	}

	public void setContentService(ContentService contentService) {
		this.contentService = contentService;
	}

	public void setSearchService(SearchService searchService) {
		this.searchService = searchService;
	}

	public void setNamespaceService(NamespaceService namespaceService) {
		this.namespaceService = namespaceService;
	}

	public void setPropertyReader(PropertyReader propertyReader) {
		this.propertyReader = propertyReader;
	}

	public void setServiceVersantHelper(
			ServiceVersantHelper serviceVersantHelper) {
		this.serviceVersantHelper = serviceVersantHelper;
	}

}
