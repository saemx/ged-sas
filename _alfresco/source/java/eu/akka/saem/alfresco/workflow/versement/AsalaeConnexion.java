package eu.akka.saem.alfresco.workflow.versement;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.rpc.ServiceException;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.alfresco.model.ContentModel;
import org.alfresco.repo.content.MimetypeMap;
import org.alfresco.repo.security.authentication.AuthenticationUtil;
import org.alfresco.repo.security.authentication.AuthenticationUtil.RunAsWork;
import org.alfresco.repo.workflow.activiti.ActivitiScriptNode;
import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.cmr.model.FileFolderService;
import org.alfresco.service.cmr.repository.ChildAssociationRef;
import org.alfresco.service.cmr.repository.ContentData;
import org.alfresco.service.cmr.repository.ContentService;
import org.alfresco.service.cmr.repository.ContentWriter;
import org.alfresco.service.cmr.repository.CopyService;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.cmr.search.ResultSet;
import org.alfresco.service.cmr.search.SearchParameters;
import org.alfresco.service.cmr.search.SearchService;
import org.alfresco.service.cmr.thumbnail.ThumbnailService;
import org.alfresco.service.namespace.NamespaceService;
import org.alfresco.service.namespace.QName;
import org.alfresco.util.FileNameValidator;
import org.alfresco.web.bean.repository.Repository;
import org.apache.axis.encoding.Base64;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;

import eu.akka.saem.alfresco.connector.asalae.util.AsalaeUtils;
import eu.akka.saem.alfresco.connector.asalae.ws.ServerServiceLocator;
import eu.akka.saem.alfresco.connector.asalae.ws.WebServicePortType;
import eu.akka.saem.alfresco.constants.SAEMBeanContants;
import eu.akka.saem.alfresco.constants.SAEMModelConstants;
import eu.akka.saem.alfresco.constants.SAEMPropertiesConstants;
import eu.akka.saem.alfresco.helper.PropertyReader;
import eu.akka.saem.alfresco.seda.v02.ArchiveTransferType;
import eu.akka.saem.alfresco.seda.writer.SEDAWriter;
import eu.akka.saem.alfresco.utils.ProfilSEDAUtils;
import eu.akka.saem.alfresco.utils.ZipUtils;
import eu.akka.saem.alfresco.workflow.SAEMWorkflowTask;

/**
 * 
 *   Tache de copie du noderef d'Alfresco vers Asalae
 * 
 * @Class         : AsalaeConnexion.java
 * @Package       : eu.akka.saem.alfresco.workflow.versement
 * @Date          : $Date: 25 févr. 2014 $:
 * @author        : $Author: THOMAS.POGNANT $
 * @version       : $Revision:  $:
 * @Id            : $Id: AsalaeConnexion.java $
 *
 */
public class AsalaeConnexion extends SAEMWorkflowTask implements JavaDelegate {

	final ServiceRegistry serviceRegistry = getServiceRegistry();
	final NodeService nodeService = serviceRegistry.getNodeService();
	final SearchService searchService = serviceRegistry.getSearchService();
	final NamespaceService namespaceService = serviceRegistry.getNamespaceService();
	final ContentService contentService = serviceRegistry.getContentService();
	ThumbnailService thumbnailService = serviceRegistry.getThumbnailService();
	final ProfilSEDAUtils profilSEDAUtils = (ProfilSEDAUtils) serviceRegistry
			.getService(SAEMBeanContants.PROFIL_SEDA_UTILS);
	final PropertyReader propertyReader = (PropertyReader) serviceRegistry
			.getService(SAEMBeanContants.PROPERTY_READER);
	final AsalaeUtils asalaeUtils = (AsalaeUtils) serviceRegistry.getService(SAEMBeanContants.ASALAE_UTILS);
	final ZipUtils zipUtils = (ZipUtils) serviceRegistry.getService(SAEMBeanContants.ZIP_UTILS);
	final FileFolderService fileFolderService = serviceRegistry.getFileFolderService();
	final CopyService copyService = serviceRegistry.getCopyService();

	private static Logger LOG = Logger.getLogger(AsalaeConnexion.class);
	
	NodeRef folderRef2 = null;
	NodeRef createdFile = null;
	NodeRef createdFile2 = null;

	@Override
	public void execute(DelegateExecution exec) throws Exception {
		ActivitiScriptNode scriptNode = (ActivitiScriptNode) (exec.getVariable("bpm_package"));
		final NodeRef bpmPackage = scriptNode.getNodeRef();
		List<ChildAssociationRef> children = AuthenticationUtil
				.runAsSystem(new RunAsWork<List<ChildAssociationRef>>() {

					@Override
					public List<ChildAssociationRef> doWork() throws Exception {
						return nodeService.getChildAssocs(bpmPackage);
					}
				});

		final NodeRef archiveFolderNodeRef = children.get(0).getChildRef();

		// Récupération du bordereau de versement
		ArchiveTransferType archiveSedaObject = (ArchiveTransferType) profilSEDAUtils.getSedaObject(
				archiveFolderNodeRef, SAEMModelConstants.PROP_SEDA_ARCHIVE_XML_FULL);

		archiveSedaObject = fillSedaBlankFields(archiveSedaObject);

		transfertToAsalae(archiveSedaObject, archiveFolderNodeRef);

	}

	private void transfertToAsalae(ArchiveTransferType archiveSedaObject, final NodeRef archiveFolderNodeRef)
			throws ServiceException, IOException {
		ServerServiceLocator ssl = new ServerServiceLocator();
		WebServicePortType test = ssl.getWebServicePort(new URL(propertyReader
				.getProperty(SAEMPropertiesConstants.ASALAE_INT_URL) + "/webservices/service"));

		// Transformation du SEDA en string
		String bordereau = SEDAWriter.transformSEDAObjectToXML(archiveSedaObject);

		String archiveName = (String) nodeService.getProperty(archiveFolderNodeRef, ContentModel.PROP_NAME);
		String zipName = FileNameValidator.getValidFileName(FilenameUtils.normalizeNoEndSeparator(archiveName
				+ ".zip"));

		// Récupération des identifiants de connexion à asalae
		String asalaeLogin = propertyReader.getProperty(SAEMPropertiesConstants.ASALAE_INT_LOGIN);
		String asalaePassword = propertyReader.getProperty(SAEMPropertiesConstants.ASALAE_INT_PASSWORD);

		// Récupération de la liste de tous les documents à transferer
		SearchParameters sp = new SearchParameters();
		sp.addStore(Repository.getStoreRef());
		sp.setLanguage(SearchService.LANGUAGE_LUCENE);
		sp.setQuery("PATH:\"" + nodeService.getPath(archiveFolderNodeRef).toPrefixString(namespaceService)
				+ "//*\"" + " AND ASPECT:\"saem:document_archive\"");
		ResultSet rs = searchService.query(sp);
		List<NodeRef> nodes = rs.getNodeRefs();
		
		
		boolean bigFile = false;
		for (NodeRef node : nodes){
			ContentData content = (ContentData) nodeService.getProperty(node, ContentModel.PROP_CONTENT);
			bigFile = content != null ? (int)content.getSize() > Integer.valueOf(propertyReader.getProperty(SAEMPropertiesConstants.SIZE_BIG_FILE)) : false;
			if(bigFile)
				break;
		}
		
		if(bigFile){
			
			final String newName = (String) nodeService.getProperty(archiveFolderNodeRef, ContentModel.PROP_NAME);
			
			//Création d'une sauvegarde en cas de coupure lors du transfert
			try {
				
				//récupération du dossier ou créer la copie
				SearchParameters sp2 = new SearchParameters();
				sp2.addStore(Repository.getStoreRef());
				sp2.setLanguage(SearchService.LANGUAGE_LUCENE);
				sp2.setQuery("PATH:\"/app:company_home/app:dictionary\"");
				ResultSet rs2 = searchService.query(sp2);
				final List<NodeRef> nodeRefWhereCopy = rs2.getNodeRefs();

				
				//créer la copie
				AuthenticationUtil.runAsSystem(new RunAsWork<Void>() {
					@Override
					public Void doWork() throws Exception {
						NodeRef folderRef = fileFolderService.create(nodeRefWhereCopy.get(0), "Copy", ContentModel.TYPE_FOLDER).getNodeRef();
						folderRef2 = fileFolderService.create(folderRef, newName, ContentModel.TYPE_FOLDER).getNodeRef();
						copyService.copy(archiveFolderNodeRef, folderRef2);
						return null;
					}
				});
				
				//créer le bordereau
				final Map<QName, Serializable> props = new HashMap<QName, Serializable>(1);
				props.put(ContentModel.PROP_NAME, "bordereau.xml");

				AuthenticationUtil.runAsSystem(new RunAsWork<Void>() {
					@Override
					public Void doWork() throws Exception {
						createdFile = nodeService.createNode(folderRef2, ContentModel.ASSOC_CONTAINS,
								QName.createQName(NamespaceService.CONTENT_MODEL_1_0_URI, "bordereau.xml"),
								ContentModel.TYPE_CONTENT, props).getChildRef();
						return null;
					}
				});
				
				ContentWriter contentWriter = contentService.getWriter(createdFile,
						ContentModel.PROP_CONTENT, true);
				contentWriter.setMimetype(MimetypeMap.MIMETYPE_TEXT_PLAIN);
				contentWriter.setEncoding("UTF-8");
				contentWriter.putContent(SEDAWriter.cleanEmptyChild(bordereau));
				
				
				final Map<QName, Serializable> props2 = new HashMap<QName, Serializable>(1);
				props2.put(ContentModel.PROP_NAME, "bigFile.conf");

				AuthenticationUtil.runAsSystem(new RunAsWork<Void>() {
					@Override
					public Void doWork() throws Exception {
						createdFile2 = nodeService.createNode(folderRef2, ContentModel.ASSOC_CONTAINS,
								QName.createQName(NamespaceService.CONTENT_MODEL_1_0_URI, "bigFile.conf"),
								ContentModel.TYPE_CONTENT, props2).getChildRef();
						return null;
					}
				});
				ContentWriter contentWriter2 = contentService.getWriter(createdFile2,
						ContentModel.PROP_CONTENT, true);
				contentWriter2.setMimetype(MimetypeMap.MIMETYPE_TEXT_PLAIN);
				contentWriter2.setEncoding("UTF-8");
				contentWriter2.putContent(propertyReader.getProperty(SAEMPropertiesConstants.ASALAE_INT_URL)+"####"+asalaeLogin+"####"+asalaePassword);

				
				// Maj des aspects pour signifier que l'archive a bien ete versee (mais pas encore acceptée)
				nodeService.removeAspect(archiveFolderNodeRef, SAEMModelConstants.ASPECT_NON_VERSE);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		else{

			// Création du zip
			byte[] zippedDocument = zipUtils.createZipTemp(nodes);

			// Maj des aspects pour signifier que l'archive a bien ete versee (mais pas encore acceptée)
			nodeService.removeAspect(archiveFolderNodeRef, SAEMModelConstants.ASPECT_NON_VERSE);

			// Web service depot
			String returnValue = test.wsDepot("bordereau.xml", Base64.encode(SEDAWriter.cleanEmptyChild(bordereau).getBytes("UTF8"))
					.getBytes(), zipName, Base64.encode(zippedDocument).getBytes(), "ZIP", asalaeLogin,
					asalaePassword);

			asalaeUtils.handleErrorsWsGDepot(returnValue);
			
		}

	}

	/**
	 * Cette méthode permet de remplir les champ générés automatiquement par
	 * Asalae
	 * 
	 * @param archiveSedaObject
	 * @return
	 */
	private ArchiveTransferType fillSedaBlankFields(ArchiveTransferType archiveSedaObject) {

		// Insertion de la date du transfert
		archiveSedaObject.getDate().setDate(new Date());
		return archiveSedaObject;
	}
}
