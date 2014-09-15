package eu.akka.saem.alfresco.webscript;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
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
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.alfresco.model.ContentModel;
import org.alfresco.repo.content.MimetypeMap;
import org.alfresco.repo.security.authentication.AuthenticationUtil;
import org.alfresco.repo.security.authentication.AuthenticationUtil.RunAsWork;
import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.cmr.model.FileFolderService;
import org.alfresco.service.cmr.repository.ChildAssociationRef;
import org.alfresco.service.cmr.repository.ContentService;
import org.alfresco.service.cmr.repository.ContentWriter;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.cmr.repository.StoreRef;
import org.alfresco.service.cmr.search.ResultSet;
import org.alfresco.service.cmr.search.SearchParameters;
import org.alfresco.service.cmr.search.SearchService;
import org.alfresco.service.cmr.site.SiteService;
import org.alfresco.service.namespace.NamespaceService;
import org.alfresco.service.namespace.QName;
import org.alfresco.util.FileNameValidator;
import org.apache.axis.encoding.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;
import org.json.simple.JSONArray;
import org.springframework.extensions.webscripts.AbstractWebScript;
import org.springframework.extensions.webscripts.WebScriptRequest;
import org.springframework.extensions.webscripts.WebScriptResponse;

import eu.akka.saem.alfresco.connector.asalae.ws.ServerServiceLocator;
import eu.akka.saem.alfresco.connector.asalae.ws.WebServicePortType;
import eu.akka.saem.alfresco.constants.SAEMArchiveStates;
import eu.akka.saem.alfresco.constants.SAEMModelConstants;
import eu.akka.saem.alfresco.constants.SAEMPropertiesConstants;
import eu.akka.saem.alfresco.helper.PropertyReader;
import eu.akka.saem.alfresco.seda.actions.DestructionBordereau;
import eu.akka.saem.alfresco.seda.actions.ModificationBordereau;
import eu.akka.saem.alfresco.seda.actions.Reprise;
import eu.akka.saem.alfresco.seda.actions.RestitutionBordereau;
import eu.akka.saem.alfresco.seda.actions.TransferBordereau;
import eu.akka.saem.alfresco.seda.loader.XMLLoader;
import eu.akka.saem.alfresco.seda.v02.ArchiveDestructionNotificationType;
import eu.akka.saem.alfresco.seda.v02.ArchiveDestructionRequestReplyReplyAcknowledgementType;
import eu.akka.saem.alfresco.seda.v02.ArchiveDestructionRequestReplyType;
import eu.akka.saem.alfresco.seda.v02.ArchiveDestructionRequestType;
import eu.akka.saem.alfresco.seda.v02.ArchiveModificationNotificationType;
import eu.akka.saem.alfresco.seda.v02.ArchiveRestitutionAcknowledgementType;
import eu.akka.saem.alfresco.seda.v02.ArchiveRestitutionRequestReplyType;
import eu.akka.saem.alfresco.seda.v02.ArchiveRestitutionRequestType;
import eu.akka.saem.alfresco.seda.v02.ArchiveRestitutionType;
import eu.akka.saem.alfresco.seda.v02.ArchiveTransferAcceptanceType;
import eu.akka.saem.alfresco.seda.v02.ArchiveTransferReplyType;
import eu.akka.saem.alfresco.seda.v02.ArchiveTransferType;
import eu.akka.saem.alfresco.seda.v02.complexType.AppraisalRulesType;
import eu.akka.saem.alfresco.seda.v02.complexType.ArchiveObjectType;
import eu.akka.saem.alfresco.seda.v02.complexType.ArchiveType;
import eu.akka.saem.alfresco.seda.v02.complexType.ArchivesDurationType;
import eu.akka.saem.alfresco.seda.v02.complexType.ArchivesIDType;
import eu.akka.saem.alfresco.seda.v02.complexType.CodeAppraisalType;
import eu.akka.saem.alfresco.seda.v02.complexType.DateType;
import eu.akka.saem.alfresco.seda.v02.simpleType.AppraisalCodeType;
import eu.akka.saem.alfresco.seda.v02.simpleType.ReplyCodeType;
import eu.akka.saem.alfresco.seda.writer.SEDAWriter;
import eu.akka.saem.alfresco.utils.ProfilSEDAUtils;
import eu.akka.saem.alfresco.utils.Utils;
import eu.akka.saem.alfresco.workflow.SAEMWorkflowUtils;

/**
 * Classe permettant la gestion des messages envoyés par Asalae (suite aux
 * différentes étapes des workflows côté Asalae), avec gestion des metadonnées
 * régissant le cycle de vie des archives et des accusés (reception, acceptation
 * et refus)
 * 
 * @Class         : AsalaeReponseBean.java
 * @Package       : eu.akka.saem.alfresco.webscript
 * @Date          : $Date: 25 févr. 2014 $:
 * @author        : $Author: THOMAS.POGNANT $
 * @version       : $Revision:  $:
 * @Id            : $Id: AsalaeReponseBean.java $
 *
 */
public class AsalaeReponseBean extends AbstractWebScript {

	private ContentService contentService;
	private NodeService nodeService;
	private SearchService searchService;
	private SiteService siteService;
	private ServiceRegistry serviceRegistry;
	private ProfilSEDAUtils profilSEDAUtils;
	private FileFolderService fileFolderService;
	private PropertyReader propertyReader;
	protected NodeRef destinationNodeRef;
	protected String filename;
	protected String state;

	private static final Log LOG = LogFactory.getLog(AsalaeReponseBean.class);

	@Override
	public void execute(WebScriptRequest req, WebScriptResponse res) throws IOException {
		JSONArray errors = new JSONArray();
		JSONObject jsonresult = new JSONObject();

		res.setContentEncoding("UTF-8");
		res.setContentType("application/json");

		try {
			analyseBordereau(errors);

			if (errors.size() == 0) {
				jsonresult.put("result", "OK");
			} else {
				jsonresult.put("result", "KO");
				jsonresult.put("error", errors);

				Reprise rep = new Reprise(req.getParameter("login"), req.getParameter("pass"));
				rep.start();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		res.getWriter().write(jsonresult.toString());
	}

	/**
	 * Method : analyseBordereau()
	 * @param errors void
	 */
	public void analyseBordereau(JSONArray errors) {
		File[] files = null;
		String bordereauFolder = System.getProperty("java.io.tmpdir") + "/asalaereponse/";

		File directoryToScan = new File(bordereauFolder);
		files = directoryToScan.listFiles();

		for (File file : files) {

			if (!file.getName().endsWith(".xml"))
				continue;

			try {
				Object SEDAObject = XMLLoader.SEDALoader(file);
				String bordereauXML = SEDAWriter.transformSEDAObjectToXML(SEDAObject);

				if (TransferBordereau.canReadBordereau(SEDAObject)) {
					if (SEDAObject.getClass().equals(ArchiveTransferReplyType.class)) {
						doActionBordereauArchiveTransferReplyType((ArchiveTransferReplyType) SEDAObject);
						LOG.info("Fin de 'doActionBordereauArchiveTransferReplyType'");
					} else if (SEDAObject.getClass().equals(ArchiveTransferAcceptanceType.class)) {
						doActionBordereauArchiveTransferAcceptanceType((ArchiveTransferAcceptanceType) SEDAObject);
						LOG.info("Fin de 'doActionBordereauArchiveTransferAcceptanceType'");
					} else {
						throw new Exception("TransferBordereau cannot read this bordereau");
					}
				} else if (DestructionBordereau.canReadBordereau(SEDAObject)) {
					if (SEDAObject.getClass().equals(ArchiveDestructionRequestType.class)) {
						doActionBordereauArchiveDestructionRequestType((ArchiveDestructionRequestType) SEDAObject);
						LOG.info("Fin de 'doActionBordereauArchiveDestructionRequestType'");
					} else if (SEDAObject.getClass().equals(ArchiveDestructionRequestReplyType.class)) {
						doActionBordereauArchiveDestructionRequestReplyType((ArchiveDestructionRequestReplyType) SEDAObject);
						LOG.info("Fin de 'doActionBordereauArchiveDestructionRequestReplyType'");
					} else if (SEDAObject.getClass().equals(
							ArchiveDestructionRequestReplyReplyAcknowledgementType.class)) {
						doActionBordereauArchiveDestructionRequestReplyReplyAcknowledgementType((ArchiveDestructionRequestReplyReplyAcknowledgementType) SEDAObject);
						LOG.info("Fin de 'doActionBordereauArchiveDestructionRequestReplyReplyAcknowledgementType'");
					} else if (SEDAObject.getClass().equals(ArchiveDestructionNotificationType.class)) {
						doActionBordereauArchiveDestructionNotificationType((ArchiveDestructionNotificationType) SEDAObject);
						LOG.info("Fin de 'doActionBordereauArchiveDestructionNotificationType'");
					} else
						throw new Exception("TransferBordereau cannot read this bordereau");
				} else if (ModificationBordereau.canReadBordereau(SEDAObject)) {

					if (SEDAObject.getClass().equals(ArchiveModificationNotificationType.class)) {
						doActionBordereauArchiveModificationNotificationType((ArchiveModificationNotificationType) SEDAObject);
						LOG.info("Fin de 'doActionBordereauArchiveModificationNotificationType'");
					} else
						throw new Exception("TransferBordereau cannot read this bordereau");
				} else if (RestitutionBordereau.canReadBordereau(SEDAObject)) {
					if (SEDAObject.getClass().equals(ArchiveRestitutionType.class)) {
						doActionBordereauArchiveRestitutionType((ArchiveRestitutionType) SEDAObject, file);
						LOG.info("Fin de 'doActionBordereauArchiveRestitutionType'");
					} else if (SEDAObject.getClass().equals(ArchiveRestitutionRequestType.class)) {
						doActionBordereauArchiveRestitutionRequestType((ArchiveRestitutionRequestType) SEDAObject);
						LOG.info("Fin de 'doActionBordereauArchiveRestitutionRequestType'");
					} else if (SEDAObject.getClass().equals(ArchiveRestitutionRequestReplyType.class)) {
						doActionBordereauArchiveRestitutionRequestReplyType((ArchiveRestitutionRequestReplyType) SEDAObject);
						LOG.info("Fin de 'doActionBordereauArchiveRestitutionRequestReplyType'");
					} else
						throw new Exception("TransferBordereau cannot read this bordereau");
				} else {
					throw new Exception("Aucune classe ne connait ce type de bordereau");
				}

				Map<QName, Serializable> props = new HashMap<QName, Serializable>(1);
				props.put(ContentModel.PROP_NAME, filename);

				NodeRef childRef = nodeService
						.getChildByName(destinationNodeRef, ContentModel.ASSOC_CONTAINS, filename);
				if (childRef != null)
					nodeService.removeChild(destinationNodeRef, childRef);

				NodeRef createdFile = nodeService.createNode(destinationNodeRef, ContentModel.ASSOC_CONTAINS,
						QName.createQName(NamespaceService.CONTENT_MODEL_1_0_URI, filename), ContentModel.TYPE_CONTENT,
						props).getChildRef();

				ContentWriter contentWriter = contentService.getWriter(createdFile, ContentModel.PROP_CONTENT, true);
				contentWriter.setMimetype(MimetypeMap.MIMETYPE_TEXT_PLAIN);
				contentWriter.setEncoding("UTF-8");
				contentWriter.putContent(bordereauXML);

				Map<QName, Serializable> aspectProperties = new HashMap<>();
				aspectProperties.put(SAEMModelConstants.PROP_TYPE_ACCUSE, state);
				nodeService.addAspect(createdFile, SAEMModelConstants.ASPECT_IS_ACCUSE, aspectProperties);

				file.delete();

			} catch (Exception e) {
				JSONObject error = new JSONObject();
				try {
					error.put("message", e.getMessage());
				} catch (Exception ex) {
					e.printStackTrace();
				}
				errors.add(error);
				e.printStackTrace();
			}

		}
	}

	/**
	 * Method : doActionBordereauArchiveRestitutionRequestReplyType()
	 * @param arrrt
	 * @throws Exception void
	 */
	private void doActionBordereauArchiveRestitutionRequestReplyType(ArchiveRestitutionRequestReplyType arrrt)
			throws Exception {
		SearchParameters sp = new SearchParameters();
		String archive_identifier = "";
		String transferIdentifier = arrrt.getRestitutionRequestIdentifier().getValue();
		String replyIdentifier = arrrt.getRestitutionRequestReplyIdentifier().getValue();
		sp.addStore(StoreRef.STORE_REF_WORKSPACE_SPACESSTORE);
		sp.setLanguage(SearchService.LANGUAGE_LUCENE);
		sp.setQuery("@saem\\:seda_restitution_acknowledgment:\"" + transferIdentifier + "\"");
		ResultSet queryResults = searchService.query(sp);

		if (queryResults != null && queryResults.getNodeRefs().size() > 0 && queryResults.getNodeRef(0) != null) {
			destinationNodeRef = queryResults.getNodeRef(0);
			archive_identifier = (String) nodeService.getProperty(destinationNodeRef,
					SAEMModelConstants.PROP_SEDA_ARCHIVE_ARCHIVAL_AGENCY_ARCHIVE_IDENTIFIER);
		} else
			throw new Exception("Aucune archive trouver avec la requete suivante seda_restitution_acknowledgment:\""
					+ transferIdentifier + "\"");

		if (arrrt.getReplyCode() != null && arrrt.getReplyCode().getValue() != null
				&& arrrt.getReplyCode().getValue().equals(ReplyCodeType.NUM000)) {
			filename = archive_identifier + "__" + replyIdentifier + "_accuse_restitution.xml";
			state = "restitution demande";
			nodeService.removeAspect(destinationNodeRef, SAEMModelConstants.ASPECT_RESTITUABLE);
		} else {
			filename = archive_identifier + "__" + replyIdentifier + "_refus_restitution.xml";
			state = "restitution rejet";
			nodeService.addAspect(destinationNodeRef, SAEMModelConstants.ASPECT_RESTITUABLE, null);
		}
	}

	/**
	 * Method : doActionBordereauArchiveRestitutionRequestType()
	 * @param arrt
	 * @throws Exception void
	 */
	private void doActionBordereauArchiveRestitutionRequestType(ArchiveRestitutionRequestType arrt) throws Exception {
		SearchParameters sp = new SearchParameters();
		String archive_identifier = "";
		String transferIdentifier = arrt.getArchive().get(0).getArchivalAgencyArchiveIdentifier().getValue();
		String replyIdentifier = arrt.getRestitutionRequestIdentifier().getValue();

		sp.addStore(StoreRef.STORE_REF_WORKSPACE_SPACESSTORE);
		sp.setLanguage(SearchService.LANGUAGE_LUCENE);
		sp.setQuery("@saem\\:seda_archive_ArchivalAgencyArchiveIdentifier:\"" + transferIdentifier + "\"");
		ResultSet queryResults = searchService.query(sp);

		if (queryResults != null && queryResults.getNodeRef(0) != null) {
			destinationNodeRef = queryResults.getNodeRef(0);
			archive_identifier = (String) nodeService.getProperty(destinationNodeRef,
					SAEMModelConstants.PROP_SEDA_ARCHIVE_ARCHIVAL_AGENCY_ARCHIVE_IDENTIFIER);
			nodeService.setProperty(destinationNodeRef, SAEMModelConstants.PROP_SEDA_RESTITUTION_ACKNOWLEDGMENT,
					replyIdentifier);
		} else
			throw new Exception(
					"Aucune archive trouver avec la requete suivante seda_archive_ArchivalAgencyArchiveIdentifier:\""
							+ transferIdentifier + "\"");

		filename = archive_identifier + "__" + replyIdentifier + "_demande_de_restitution.xml";

		nodeService.removeAspect(destinationNodeRef, SAEMModelConstants.ASPECT_RESTITUABLE);

		state = "restitution demande";
	}

	/**
	 * Method : doActionBordereauArchiveRestitutionType()
	 * @param art
	 * @param file
	 * @throws Exception void
	 */
	private void doActionBordereauArchiveRestitutionType(ArchiveRestitutionType art, File file) throws Exception {
		SearchParameters sp = new SearchParameters();
		String archive_identifier = "";
		String transferIdentifier = art.getArchive().get(0).getArchivalAgencyArchiveIdentifier().getValue();

		sp.addStore(StoreRef.STORE_REF_WORKSPACE_SPACESSTORE);
		sp.setLanguage(SearchService.LANGUAGE_LUCENE);
		sp.setQuery("@saem\\:seda_archive_ArchivalAgencyArchiveIdentifier:\"" + transferIdentifier + "\"");

		ResultSet queryResults = searchService.query(sp);

		if (queryResults != null & queryResults.getNodeRef(0) != null) {
			destinationNodeRef = queryResults.getNodeRef(0);
			archive_identifier = (String) nodeService.getProperty(destinationNodeRef,
					SAEMModelConstants.PROP_SEDA_ARCHIVE_ARCHIVAL_AGENCY_ARCHIVE_IDENTIFIER);
		} else
			throw new Exception(
					"Aucune archive trouver avec la requete suivante seda_archive_ArchivalAgencyArchiveIdentifier:\""
							+ transferIdentifier + "\"");

		// Restitution des documents
		String zipname = file.getName().replace(".xml", ".zip");
		File zipfile = new File(file.getAbsolutePath().replace(".xml", ".zip"));

		if (zipfile.exists()) {

			unzip(zipfile, new File(System.getProperty("java.io.tmpdir") + "/asalaereponse/"));
			File dossier = new File(System.getProperty("java.io.tmpdir") + "/asalaereponse/" + archive_identifier);

			if (dossier.exists()) {
				for (File fichier : dossier.listFiles()) {

					if (fichier.getName().equals("documents")) {

						for (ArchiveType at : art.getArchive()) {
							// On récupère le noderef de l'archive
							SearchParameters sp1 = new SearchParameters();
							sp1.addStore(StoreRef.STORE_REF_WORKSPACE_SPACESSTORE);
							sp1.setLanguage(SearchService.LANGUAGE_LUCENE);
							sp1.setQuery("@saem\\:seda_archive_ArchivalAgencyArchiveIdentifier:\""
									+ at.getArchivalAgencyArchiveIdentifier().getValue() + "\"");

							ResultSet queryResults1 = searchService.query(sp1);

							if (queryResults1.getNodeRef(0) != null && queryResults1.getNodeRefs().size() > 0) {

								NodeRef atNodeRef = queryResults1.getNodeRef(0);
								// On recupère le path pour effectuer la requete
								// pour chaque fichier du zip
								String atPath = nodeService.getPath(atNodeRef).toPrefixString(
										serviceRegistry.getNamespaceService());

								for (File file1 : fichier.listFiles()) {
									SearchParameters sp11 = new SearchParameters();
									sp11.addStore(StoreRef.STORE_REF_WORKSPACE_SPACESSTORE);
									sp11.setLanguage(SearchService.LANGUAGE_LUCENE);
									sp11.setQuery("PATH:\"" + atPath + "//*\" AND @cm\\:name:\"" + file1.getName()
											+ "\"");

									ResultSet queryResults11 = searchService.query(sp11);

									if (queryResults11.getNodeRefs().size() > 0 && queryResults11.getNodeRef(0) != null) {

										FileInputStream input = new FileInputStream(file1);

										byte[] content = new byte[(int) file1.length()];
										input.read(content);

										ContentWriter writer = contentService.getWriter(queryResults11.getNodeRef(0),
												ContentModel.PROP_CONTENT, true);
										writer.putContent(new ByteArrayInputStream(content));

										input.close();
									} else {
										LOG.error("Impossible de trouver le fichier dont le nom est: "
												+ file1.getName());
									}

								}
							} else {
								LOG.error("Impossible de trouver l'archive ayant pour 'ArchivalAgencyArchiveIdentifier' la valeur: "
										+ at.getArchivalAgencyArchiveIdentifier().getValue());
							}
						}
						break;
					}
				}

				deleteFolder(dossier);
				zipfile.delete();
			}

			// Regeneration de tout les identifiants
			ServerServiceLocator ssl = new ServerServiceLocator();
			WebServicePortType test = ssl.getWebServicePort(new URL(propertyReader
					.getProperty(SAEMPropertiesConstants.ASALAE_INT_URL) + "/webservices/service"));
			ArchiveTransferType archiveSedaObject = (ArchiveTransferType) profilSEDAUtils.getSedaObject(
					destinationNodeRef, SAEMModelConstants.PROP_SEDA_ARCHIVE_XML_FULL);
			cleanSEDAAfterRestitution(archiveSedaObject);
			String bordereau = SEDAWriter.transformSEDAObjectToXML(archiveSedaObject);
			String bordereauSEDA = new String(Base64.decode(new String(test.wsCompleterBordereau(
					Base64.encode(bordereau.getBytes("UTF8")).getBytes(),
					propertyReader.getProperty(SAEMPropertiesConstants.ASALAE_INT_LOGIN),
					propertyReader.getProperty(SAEMPropertiesConstants.ASALAE_INT_PASSWORD)))), "UTF8");
			InputStream result = new ByteArrayInputStream(bordereauSEDA.getBytes());
			result.reset();
			ArchiveTransferType newArchiveTransferType = (ArchiveTransferType) XMLLoader.SEDALoader(result);
			insertDataInFolder(destinationNodeRef, newArchiveTransferType);

			// Envoi un ArchiveRestitutionAcknowledgementType à As@lae

			/* Correction envoi de l'acquitement a asalae def ou int */
			Boolean isArchiveDefinitive = false;

			if (nodeService.getProperty(destinationNodeRef, SAEMModelConstants.PROP_ARCHIVE_STATE_PROP).equals(
					SAEMArchiveStates.DEFINITIF)) {
				isArchiveDefinitive = true;
			}

			WebServicePortType wspt = ssl.getWebServicePort(new URL(propertyReader
					.getProperty(isArchiveDefinitive ? SAEMPropertiesConstants.ASALAE_DEF_URL
							: SAEMPropertiesConstants.ASALAE_INT_URL)
					+ "/webservices/service"));

			String asalaeLogin = propertyReader
					.getProperty(isArchiveDefinitive ? SAEMPropertiesConstants.ASALAE_DEF_LOGIN
							: SAEMPropertiesConstants.ASALAE_INT_LOGIN);
			String asalaePassword = propertyReader
					.getProperty(isArchiveDefinitive ? SAEMPropertiesConstants.ASALAE_DEF_PASSWORD
							: SAEMPropertiesConstants.ASALAE_INT_PASSWORD);
			/**/

			// String asalaeLogin =
			// propertyReader.getProperty(SAEMPropertiesConstants.ASALAE_INT_PASSWORD);
			// String asalaePassword =
			// propertyReader.getProperty(SAEMPropertiesConstants.ASALAE_INT_PASSWORD);

			ArchiveRestitutionAcknowledgementType arrt = new ArchiveRestitutionAcknowledgementType();
			arrt.setRestitutionIdentifier(new ArchivesIDType());
			arrt.getRestitutionIdentifier().setValue(art.getRestitutionIdentifier().getValue());
			bordereauSEDA = SEDAWriter.transformSEDAObjectToXML(arrt);
			wspt.wsRestitution("bordereau.xml", Base64.encode(bordereauSEDA.getBytes("UTF8")).getBytes(), null, null,
					null, asalaeLogin, asalaePassword);

		} else {
			throw new Exception("Aucun fichier Zip de ce nom: " + zipname);
		}

		filename = archive_identifier + "__" + transferIdentifier + "_accusé_de_reception_restitution.xml";
		state = "restitution reception";

		nodeService.removeAspect(destinationNodeRef, SAEMModelConstants.ASPECT_VERSE);
		nodeService.addAspect(destinationNodeRef, SAEMModelConstants.ASPECT_NON_VERSE, null);

		Utils.setArchiveState(nodeService, destinationNodeRef, SAEMArchiveStates.RESTITUER);
		nodeService.removeAspect(destinationNodeRef, SAEMModelConstants.ASPECT_ELIMINABLE);
	}

	/**
	 * Method : deleteFolder()
	 * @param folder void
	 */
	private void deleteFolder(File folder) {
		for (File file : folder.listFiles()) {
			if (file.isDirectory())
				deleteFolder(file);
			else
				file.delete();
		}
		folder.delete();
	}

	/**
	 * Method : insertDataInFolder()
	 * @param archive
	 * @param att void
	 */
	private void insertDataInFolder(NodeRef archive, ArchiveTransferType att) {
		// saem:seda_archive_ArchivalAgencyArchiveIdentifier
		nodeService.setProperty(archive, SAEMModelConstants.PROP_SEDA_ARCHIVE_ARCHIVAL_AGENCY_ARCHIVE_IDENTIFIER, att
				.getContains().get(0).getArchivalAgencyArchiveIdentifier().getValue());
		// saem:seda_archive_xml_full
		nodeService.setProperty(archive, SAEMModelConstants.PROP_SEDA_ARCHIVE_XML_FULL,
				SEDAWriter.transformSEDAObjectToXML(att));
		// saem:seda_restitution_acknowledgment
		nodeService.setProperty(archive, SAEMModelConstants.PROP_SEDA_RESTITUTION_ACKNOWLEDGMENT, "");
		// saem:seda_versement_acknowledgment
		nodeService.setProperty(archive, SAEMModelConstants.PROP_SEDA_VERSEMENT_ACKNOWLEDGMENT, att
				.getTransferIdentifier().getValue());

		// On edite
		for (ChildAssociationRef car : nodeService.getChildAssocs(archive)) {
			if (fileFolderService.getFileInfo(car.getChildRef()).isFolder()) {
				// On recherche l'unité documentaire qui porte le meme nom
				ArchiveObjectType aot = findSubArchive(att.getContains().get(0),
						(String) nodeService.getProperty(car.getChildRef(), ContentModel.PROP_NAME));
				insertDataInSubFolder(car.getChildRef(), aot);
			}
		}

		if (att.getContains().get(0).getDocument() != null)
			att.getContains().get(0).getDocument().clear();
		if (att.getContains().get(0).getContains() != null)
			att.getContains().get(0).getContains().clear();

		// saem:seda_archive_xml
		nodeService.setProperty(archive, SAEMModelConstants.PROP_SEDA_ARCHIVE_XML,
				SEDAWriter.transformSEDAObjectToXML(att));
	}

	/**
	 * Method : findSubArchive()
	 * @param archiveType
	 * @param name
	 * @return ArchiveObjectType
	 */
	private ArchiveObjectType findSubArchive(ArchiveType archiveType, String name) {
		if (archiveType.getContains() == null)
			return null;

		for (ArchiveObjectType aot : archiveType.getContains()) {
			String nameVerif = StringUtils.trim(name);
			nameVerif = FileNameValidator.getValidFileName(nameVerif);
			nameVerif = nameVerif.trim();
			if (nameVerif.equals(name))
				return aot;
		}

		return null;
	}

	/**
	 * Method : findSubArchive()
	 * @param archiveType
	 * @param name
	 * @return ArchiveObjectType
	 */
	private ArchiveObjectType findSubArchive(ArchiveObjectType archiveType, String name) {
		if (archiveType.getContains() == null)
			return null;

		for (ArchiveObjectType aot : archiveType.getContains()) {
			if (aot.getName().equals(name))
				return aot;
		}

		return null;
	}

	/**
	 * Method : insertDataInSubFolder()
	 * @param archive
	 * @param att void
	 */
	private void insertDataInSubFolder(NodeRef archive, ArchiveObjectType att) {
		// saem:seda_sous_objet_archive_ArchivalAgencyObjectIdentifier
		nodeService.setProperty(archive,
				SAEMModelConstants.PROP_SEDA_SOUS_OBJET_ARCHIVE_ARCHIVAL_AGENCY_OBJECT_IDENTIFIER, att
						.getArchivalAgencyObjectIdentifier().getValue());

		// On edite
		for (ChildAssociationRef car : nodeService.getChildAssocs(archive)) {
			if (fileFolderService.getFileInfo(car.getChildRef()).isFolder()) {
				// On recherche l'unité documentaire qui porte le meme nom
				ArchiveObjectType aot = findSubArchive(att,
						(String) nodeService.getProperty(car.getChildRef(), ContentModel.PROP_NAME));

				if (aot != null)
					insertDataInSubFolder(car.getChildRef(), aot);
			}
		}

		if (att.getContains() != null)
			att.getContains().clear();
		if (att.getDocument() != null)
			att.getDocument().clear();

		// saem:seda_sous_objet_archive_xml
		nodeService.setProperty(archive, SAEMModelConstants.PROP_SEDA_SOUS_OBJET_ARCHIVE_XML,
				SEDAWriter.transformSEDAObjectToXML(att));
	}

	/**
	 * Method : cleanSEDAAfterRestitution()
	 * @param att void
	 */
	private void cleanSEDAAfterRestitution(ArchiveTransferType att) {
		att.getTransferIdentifier().setValue("");
		for (ArchiveType at : att.getContains()) {
			cleanSEDAArchiveAfterResitution(at);
		}
	}

	/**
	 * Method : cleanSEDAArchiveAfterResitution()
	 * @param at void
	 */
	private void cleanSEDAArchiveAfterResitution(ArchiveType at) {
		at.getArchivalAgencyArchiveIdentifier().setValue("");
		for (ArchiveObjectType aot : at.getContains()) {
			cleanSEDAArchiveObjectAfterResitution(aot);
		}
	}

	/**
	 * Method : cleanSEDAArchiveObjectAfterResitution()
	 * @param at void
	 */
	private void cleanSEDAArchiveObjectAfterResitution(ArchiveObjectType at) {
		at.getArchivalAgencyObjectIdentifier().setValue("");
	}

	/**
	 * Method : unzip()
	 * @param zipfile
	 * @param folder
	 * @throws FileNotFoundException
	 * @throws IOException void
	 */
	private void unzip(File zipfile, File folder) throws FileNotFoundException, IOException {

		// création de la ZipInputStream qui va servir à lire les données du
		// fichier zip
		ZipInputStream zis = new ZipInputStream(
				new BufferedInputStream(new FileInputStream(zipfile.getCanonicalFile())));

		// extractions des entrées du fichiers zip (i.e. le contenu du zip)
		ZipEntry ze = null;
		try {
			while ((ze = zis.getNextEntry()) != null) {

				// Pour chaque entrée, on crée un fichier dans le répertoire de
				// sortie "folder"
				File f = new File(folder.getCanonicalPath(), ze.getName());

				// Si l'entrée est un répertoire, on le crée dans le répertoire
				// de sortie et on passe à l'entrée suivante (continue)
				if (ze.isDirectory()) {
					f.mkdirs();
					continue;
				}

				// L'entrée est un fichier, on crée une OutputStream pour écrire
				// le contenu du nouveau fichier
				f.getParentFile().mkdirs();
				OutputStream fos = new BufferedOutputStream(new FileOutputStream(f));

				// On écrit le contenu du nouveau fichier qu'on lit à partir de
				// la ZipInputStream au moyen d'un buffer (byte[])
				try {
					try {
						final byte[] buf = new byte[8192];
						int bytesRead;
						while (-1 != (bytesRead = zis.read(buf)))
							fos.write(buf, 0, bytesRead);
					} finally {
						fos.close();
					}
				} catch (final IOException ioe) {
					f.delete();
					throw ioe;
				}
			}
		} finally {
			zis.close();
		}
	}

	/**
	 * Method : doActionBordereauArchiveModificationNotificationType()
	 * @param amnt
	 * @throws Exception void
	 */
	private void doActionBordereauArchiveModificationNotificationType(ArchiveModificationNotificationType amnt)
			throws Exception {
		ArchiveTransferType archiveSedaObjectXML = null;
		ArchiveObjectType subObjectSedaObjectXML = null;
		String transferIdentifier = amnt.getUnitIdentifier().get(0).getValue();
		String commentaires = amnt.getComment();
		SearchParameters sp = new SearchParameters();
		String archive_identifier = "";

		if (StringUtils.isBlank(transferIdentifier))
			return;

		String duaValue = commentaires.split(";")[0].split(":")[1];
		String sortFinalValue = commentaires.split(";")[1].split(":")[1];

		sp.addStore(StoreRef.STORE_REF_WORKSPACE_SPACESSTORE);
		sp.setLanguage(SearchService.LANGUAGE_LUCENE);
		sp.setQuery("@saem\\:seda_archive_ArchivalAgencyArchiveIdentifier:\"" + transferIdentifier + "\"");

		ResultSet queryResults = searchService.query(sp);

		int dua = Integer.valueOf(duaValue.substring(1, duaValue.length() - 1));
		Format formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date tempDate = null;

		if (queryResults.length() > 0) {

			destinationNodeRef = queryResults.getNodeRef(0);
			archive_identifier = (String) nodeService.getProperty(destinationNodeRef,
					SAEMModelConstants.PROP_SEDA_ARCHIVE_ARCHIVAL_AGENCY_ARCHIVE_IDENTIFIER);

			// Récupération du bordereau de versement
			archiveSedaObjectXML = (ArchiveTransferType) profilSEDAUtils.getSedaObject(destinationNodeRef,
					SAEMModelConstants.PROP_SEDA_ARCHIVE_XML_FULL);

			changeDUASortFinalValues(duaValue, sortFinalValue, archiveSedaObjectXML);

			nodeService.setProperty(destinationNodeRef, SAEMModelConstants.PROP_SEDA_ARCHIVE_XML_FULL,
					SEDAWriter.transformSEDAObjectToXML(archiveSedaObjectXML));

			// Récupération du bordereau de versement
			archiveSedaObjectXML = (ArchiveTransferType) profilSEDAUtils.getSedaObject(destinationNodeRef,
					SAEMModelConstants.PROP_SEDA_ARCHIVE_XML);

			changeDUASortFinalValues(duaValue, sortFinalValue, archiveSedaObjectXML);

			nodeService.setProperty(destinationNodeRef, SAEMModelConstants.PROP_SEDA_ARCHIVE_XML,
					SEDAWriter.transformSEDAObjectToXML(archiveSedaObjectXML));

			// Récupération de la date de départ
			tempDate = (Date) formatter.parseObject(archiveSedaObjectXML.getContains().get(0).getAppraisal()
					.getStartDate().getValue());

		} else {

			sp.setQuery("@saem\\:seda_sous_objet_archive_ArchivalAgencyObjectIdentifier:\"" + transferIdentifier + "\"");

			queryResults = searchService.query(sp);

			destinationNodeRef = queryResults.getNodeRef(0);
			archive_identifier = (String) nodeService.getProperty(destinationNodeRef,
					SAEMModelConstants.PROP_SEDA_SOUS_OBJET_ARCHIVE_ARCHIVAL_AGENCY_OBJECT_IDENTIFIER);

			// Récupération du bordereau de versement
			subObjectSedaObjectXML = (ArchiveObjectType) profilSEDAUtils.getSedaObject(destinationNodeRef,
					SAEMModelConstants.PROP_SEDA_SOUS_OBJET_ARCHIVE_XML);

			changeDUASortFinalValues(duaValue, sortFinalValue, subObjectSedaObjectXML);

			nodeService.setProperty(destinationNodeRef, SAEMModelConstants.PROP_SEDA_SOUS_OBJET_ARCHIVE_XML,
					SEDAWriter.transformSEDAObjectToXML(subObjectSedaObjectXML));

			// Récupération du bordereau de versement
			NodeRef parentArchive = getParentArchive(destinationNodeRef);
			archiveSedaObjectXML = (ArchiveTransferType) profilSEDAUtils.getSedaObject(parentArchive,
					SAEMModelConstants.PROP_SEDA_ARCHIVE_XML_FULL);

			changeDUASortFinalValuesForSubObject(duaValue, sortFinalValue, archiveSedaObjectXML,
					subObjectSedaObjectXML.getArchivalAgencyObjectIdentifier());

			nodeService.setProperty(parentArchive, SAEMModelConstants.PROP_SEDA_ARCHIVE_XML_FULL,
					SEDAWriter.transformSEDAObjectToXML(archiveSedaObjectXML));

			// Récupération de la date de départ
			tempDate = (Date) formatter.parseObject(subObjectSedaObjectXML.getAppraisal().getStartDate().getValue());
		}

		Calendar cal = GregorianCalendar.getInstance();
		cal.setTime(tempDate);
		cal.add(Calendar.YEAR, dua);

		if (Utils.findValueOnEnum(AppraisalCodeType.class, sortFinalValue).equals(AppraisalCodeType.DETRUIRE)
				&& cal.compareTo(GregorianCalendar.getInstance()) <= 0) {
			nodeService.addAspect(destinationNodeRef, SAEMModelConstants.ASPECT_ELIMINABLE, null);
		} else {
			nodeService.removeAspect(destinationNodeRef, SAEMModelConstants.ASPECT_ELIMINABLE);
		}

		filename = archive_identifier + "__" + amnt.getModificationNotificationIdentifier().getValue() + "_"
				+ transferIdentifier + ".xml";
	}

	/**
	 * Method : getParentArchive()
	 * @param destinationNodeRef
	 * @return
	 * @throws Exception NodeRef
	 */
	private NodeRef getParentArchive(NodeRef destinationNodeRef) throws Exception {
		NodeRef parent = nodeService.getParentAssocs(destinationNodeRef).get(0).getParentRef();
		if (parent.equals(destinationNodeRef)) {
			throw new Exception("Impossible de récupérer l'archive parent pour le noderef : "
					+ destinationNodeRef.toString());
		}
		if (nodeService.hasAspect(parent, SAEMModelConstants.ASPECT_ARCHIVABLE)) {
			return parent;
		}
		return getParentArchive(parent);
	}

	/**
	 * Method : changeDUASortFinalValuesForSubObject()
	 * @param duaValue
	 * @param sortFinalValue
	 * @param archiveSedaObjectXML
	 * @param archivalAgencyObjectIdentifier
	 * @throws Exception void
	 */
	private void changeDUASortFinalValuesForSubObject(String duaValue, String sortFinalValue,
			ArchiveTransferType archiveSedaObjectXML, ArchivesIDType archivalAgencyObjectIdentifier) throws Exception {

		ArchiveObjectType toModif = null;
		if (archiveSedaObjectXML.getContains() != null) {
			for (ArchiveType archiveType : archiveSedaObjectXML.getContains()) {
				if (archiveType.getContains() != null) {
					for (ArchiveObjectType archiveObjectType : archiveType.getContains()) {
						toModif = getArchiveObjectTypeRecursif(archiveObjectType, archivalAgencyObjectIdentifier);
						if (toModif != null)
							break;
					}
				}
				if (toModif != null)
					break;
			}
		}
		if (toModif == null) {
			throw new Exception("Aucun sous objet d'archive ne correspond à cet identifiant");
		}

		changeDUASortFinalValues(duaValue, sortFinalValue, toModif);
	}

	/**
	 * Method : changeDUASortFinalValues()
	 * @param duaValue
	 * @param sortFinalValue
	 * @param subObjectSedaObjectXML void
	 */
	private void changeDUASortFinalValues(String duaValue, String sortFinalValue,
			ArchiveObjectType subObjectSedaObjectXML) {
		try {

			if (subObjectSedaObjectXML.getAppraisal() == null)
				subObjectSedaObjectXML.setAppraisal(createEmptyAppraisal());

			// Modif du sort final
			switch (sortFinalValue) {
			case "conserver":
				subObjectSedaObjectXML.getAppraisal().getCode().setValue(AppraisalCodeType.CONSERVER);
				break;
			case "detruire":
				subObjectSedaObjectXML.getAppraisal().getCode().setValue(AppraisalCodeType.DETRUIRE);
				break;
			default:
				subObjectSedaObjectXML.getAppraisal().getCode().setValue(AppraisalCodeType.EMPTY);
				break;
			}

			// Modif de la DUA
			subObjectSedaObjectXML.getAppraisal().getDuration().setValue(duaValue);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Method : getArchiveObjectTypeRecursif()
	 * @param archiveObjectType
	 * @param archivalAgencyObjectIdentifier
	 * @return ArchiveObjectType
	 */
	private ArchiveObjectType getArchiveObjectTypeRecursif(ArchiveObjectType archiveObjectType,
			ArchivesIDType archivalAgencyObjectIdentifier) {

		if (archiveObjectType.getArchivalAgencyObjectIdentifier() != null
				&& archiveObjectType.getArchivalAgencyObjectIdentifier().getValue()
						.equals(archivalAgencyObjectIdentifier.getValue())) {
			return archiveObjectType;
		}

		if (archiveObjectType.getContains() != null) {
			for (ArchiveObjectType newArchiveObjectType : archiveObjectType.getContains()) {
				ArchiveObjectType newArchiveObjectType2 = getArchiveObjectTypeRecursif(newArchiveObjectType,
						archivalAgencyObjectIdentifier);

				if (newArchiveObjectType2 != null) {
					return newArchiveObjectType2;
				}
			}
		}
		return null;
	}

	/**
	 * Method : changeDUASortFinalValues()
	 * @param duaValue
	 * @param sortFinalValue
	 * @param archiveSedaObject void
	 */
	private void changeDUASortFinalValues(String duaValue, String sortFinalValue, ArchiveTransferType archiveSedaObject) {

		try {

			if (archiveSedaObject.getContains().get(0).getAppraisal() == null)
				archiveSedaObject.getContains().get(0).setAppraisal(createEmptyAppraisal());

			// Modif du sort final
			switch (sortFinalValue) {
			case "conserver":
				archiveSedaObject.getContains().get(0).getAppraisal().getCode().setValue(AppraisalCodeType.CONSERVER);
				break;
			case "detruire":
				archiveSedaObject.getContains().get(0).getAppraisal().getCode().setValue(AppraisalCodeType.DETRUIRE);
				break;
			default:
				archiveSedaObject.getContains().get(0).getAppraisal().getCode().setValue(AppraisalCodeType.EMPTY);
				break;
			}

			// Modif de la DUA
			archiveSedaObject.getContains().get(0).getAppraisal().getDuration().setValue(duaValue);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method : createEmptyAppraisal()
	 * @return AppraisalRulesType
	 */
	private AppraisalRulesType createEmptyAppraisal() {
		AppraisalRulesType newAppraisalRulesType = new AppraisalRulesType();
		CodeAppraisalType newCodeAppraisalType = new CodeAppraisalType();
		newCodeAppraisalType.setValue(AppraisalCodeType.EMPTY);
		newAppraisalRulesType.setCode(newCodeAppraisalType);
		newAppraisalRulesType.setDuration(new ArchivesDurationType());
		DateType newDate = new DateType();
		newDate.setValue(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		newAppraisalRulesType.setStartDate(newDate);
		return newAppraisalRulesType;
	}

	/**
	 * Method : doActionBordereauArchiveDestructionNotificationType()
	 * @param adat
	 * @throws Exception void
	 */
	private void doActionBordereauArchiveDestructionNotificationType(ArchiveDestructionNotificationType adat)
			throws Exception {
		SearchParameters sp = new SearchParameters();
		String archive_identifier = "";
		String transferIdentifier = adat.getDestructionRequestIdentifier().getValue();

		sp.addStore(StoreRef.STORE_REF_WORKSPACE_SPACESSTORE);
		sp.setLanguage(SearchService.LANGUAGE_LUCENE);
		sp.setQuery("@saem\\:seda_elimination_acknowledgment:\"" + transferIdentifier + "\"");

		ResultSet queryResults = searchService.query(sp);

		if (queryResults != null && queryResults.getNodeRef(0) != null) {
			destinationNodeRef = queryResults.getNodeRef(0);
			archive_identifier = (String) nodeService.getProperty(destinationNodeRef,
					SAEMModelConstants.PROP_SEDA_ARCHIVE_ARCHIVAL_AGENCY_ARCHIVE_IDENTIFIER);
		} else
			throw new Exception("Aucune archive trouver avec la requete suivante seda_elimination_acknowledgment:\""
					+ transferIdentifier + "\"");

		filename = archive_identifier + "__" + transferIdentifier + "_accusé_d_acceptation_elimination.xml";
		state = "elimination acceptation";

		// L'archive a été éliminé dans As@lae
		Utils.setArchiveState(nodeService, destinationNodeRef, SAEMArchiveStates.ELIMINER);
	}

	/**
	 * Method : doActionBordereauArchiveDestructionRequestReplyReplyAcknowledgementType()
	 * @param adat
	 * @throws Exception void
	 */
	private void doActionBordereauArchiveDestructionRequestReplyReplyAcknowledgementType(
			ArchiveDestructionRequestReplyReplyAcknowledgementType adat) throws Exception {
		SearchParameters sp = new SearchParameters();
		String archive_identifier = "";
		String transferIdentifier = adat.getDestructionRequestReplyIdentifier().getValue();

		sp.addStore(StoreRef.STORE_REF_WORKSPACE_SPACESSTORE);
		sp.setLanguage(SearchService.LANGUAGE_LUCENE);
		sp.setQuery("@saem\\:seda_elimination_acknowledgment:\"" + transferIdentifier + "\"");

		ResultSet queryResults = searchService.query(sp);

		if (queryResults != null && queryResults.getNodeRef(0) != null) {
			destinationNodeRef = queryResults.getNodeRef(0);
			archive_identifier = (String) nodeService.getProperty(destinationNodeRef,
					SAEMModelConstants.PROP_SEDA_ARCHIVE_ARCHIVAL_AGENCY_ARCHIVE_IDENTIFIER);
		} else
			throw new Exception("Aucune archive trouver avec la requete suivante seda_elimination_acknowledgment:\""
					+ transferIdentifier + "\"");

		final NodeRef finalDestinationNodeRef = destinationNodeRef;

		filename = archive_identifier + "__" + transferIdentifier + "_accusé_de_rejet_elimination.xml";
		if (destinationNodeRef != null) {
			nodeService.addAspect(destinationNodeRef, SAEMModelConstants.ASPECT_ELIMINABLE, null);
			nodeService.addAspect(destinationNodeRef, SAEMModelConstants.ASPECT_RESTITUABLE, null);
		}
		state = "elimination rejet";
		nodeService.setProperty(destinationNodeRef, SAEMModelConstants.PROP_SEDA_ELIMINATION_ACKNOWLEDGMENT, "");

		// Envoi de mail
		String siteName = AuthenticationUtil.runAsSystem(new RunAsWork<String>() {

			@Override
			public String doWork() throws Exception {
				return siteService.getSite(finalDestinationNodeRef).getShortName();
			}
		});
		String archiveName = (String) nodeService.getProperty(destinationNodeRef, ContentModel.PROP_NAME);
		boolean workflowTerminated = false;

		String workflowName = "Elimination";
		boolean svValidation = false;

		String initiatorUserName = (String) nodeService.getProperty(destinationNodeRef,
				SAEMModelConstants.PROP_ELIMINATION_INITIATOR);

		SAEMWorkflowUtils.sendMail(serviceRegistry, "", "", archiveName, destinationNodeRef, initiatorUserName,
				siteName, "", workflowTerminated, workflowName, svValidation);
		
		nodeService.removeAspect(finalDestinationNodeRef, SAEMModelConstants.ASPECT_ARCHIVABLE);
		nodeService.removeAspect(finalDestinationNodeRef, SAEMModelConstants.ASPECT_NON_VERSE);
	}

	/**
	 * Method : doActionBordereauArchiveDestructionRequestReplyType()
	 * @param adrrt
	 * @throws Exception void
	 */
	private void doActionBordereauArchiveDestructionRequestReplyType(ArchiveDestructionRequestReplyType adrrt)
			throws Exception {
		SearchParameters sp = new SearchParameters();
		String archive_identifier = "";
		String transferIdentifier = adrrt.getDestructionRequestIdentifier().getValue();

		sp.addStore(StoreRef.STORE_REF_WORKSPACE_SPACESSTORE);
		sp.setLanguage(SearchService.LANGUAGE_LUCENE);
		sp.setQuery("@saem\\:seda_elimination_acknowledgment:\"" + transferIdentifier + "\"");

		ResultSet queryResults = searchService.query(sp);
		if (queryResults != null && queryResults.getNodeRef(0) != null) {
			destinationNodeRef = queryResults.getNodeRef(0);
			archive_identifier = (String) nodeService.getProperty(destinationNodeRef,
					SAEMModelConstants.PROP_SEDA_ARCHIVE_ARCHIVAL_AGENCY_ARCHIVE_IDENTIFIER);
		} else
			throw new Exception("Aucune archive trouver avec la requete suivante seda_elimination_acknowledgment:\""
					+ transferIdentifier + "\"");

		if (adrrt.getReplyCode().getValue().equals(ReplyCodeType.NUM000)) {
			filename = archive_identifier + "__" + transferIdentifier + "_accusé_de_reception_elimination.xml";
			state = "elimination reception";
		}
		
		nodeService.removeAspect(destinationNodeRef, SAEMModelConstants.ASPECT_ELIMINABLE);
		nodeService.removeAspect(destinationNodeRef, SAEMModelConstants.ASPECT_RESTITUABLE);
	}

	/**
	 * Method : doActionBordereauArchiveDestructionRequestType()
	 * @param adr
	 * @throws Exception void
	 */
	private void doActionBordereauArchiveDestructionRequestType(ArchiveDestructionRequestType adr) throws Exception {
		SearchParameters sp = new SearchParameters();
		String archive_identifier = "";

		String transferIdentifier = adr.getArchive().get(0).getArchivalAgencyArchiveIdentifier().getValue();

		sp.addStore(StoreRef.STORE_REF_WORKSPACE_SPACESSTORE);
		sp.setLanguage(SearchService.LANGUAGE_LUCENE);
		sp.setQuery("@saem\\:seda_archive_ArchivalAgencyArchiveIdentifier:\"" + transferIdentifier + "\"");
		ResultSet queryResults = searchService.query(sp);

		if (queryResults != null && queryResults.getNodeRef(0) != null) {
			destinationNodeRef = queryResults.getNodeRef(0);
			archive_identifier = (String) nodeService.getProperty(destinationNodeRef,
					SAEMModelConstants.PROP_SEDA_ARCHIVE_ARCHIVAL_AGENCY_ARCHIVE_IDENTIFIER);
		} else
			throw new Exception(
					"Aucune archive trouver avec la requete suivante seda_archive_ArchivalAgencyArchiveIdentifier:\""
							+ transferIdentifier + "\"");

		final NodeRef finalDestinationNodeRef = destinationNodeRef;

		filename = archive_identifier + "_demande_d_elimination.xml";

		nodeService.setProperty(finalDestinationNodeRef, SAEMModelConstants.PROP_SEDA_ELIMINATION_ACKNOWLEDGMENT, adr
				.getDestructionRequestIdentifier().getValue());
		nodeService.removeAspect(finalDestinationNodeRef, SAEMModelConstants.ASPECT_ELIMINABLE);

		state = "elimination demande";
		
	}

	/**
	 * Method : doActionBordereauArchiveTransferAcceptanceType()
	 * @param atat
	 * @throws Exception void
	 */
	private void doActionBordereauArchiveTransferAcceptanceType(ArchiveTransferAcceptanceType atat) throws Exception {

		String transferIdentifier = atat.getTransferIdentifier().getValue();
		String replyIdentifier = atat.getTransferAcceptanceIdentifier().getValue();
		SearchParameters sp = new SearchParameters();
		String archive_identifier = "";
		sp.addStore(StoreRef.STORE_REF_WORKSPACE_SPACESSTORE);
		sp.setLanguage(SearchService.LANGUAGE_LUCENE);
		sp.setQuery("@saem\\:seda_versement_acknowledgment:\"" + transferIdentifier + "\"");

		ResultSet queryResults = searchService.query(sp);

		if (queryResults != null && queryResults.getNodeRef(0) != null) {
			destinationNodeRef = queryResults.getNodeRef(0);
			archive_identifier = (String) nodeService.getProperty(destinationNodeRef,
					SAEMModelConstants.PROP_SEDA_ARCHIVE_ARCHIVAL_AGENCY_ARCHIVE_IDENTIFIER);
		} else
			throw new Exception("Aucune archive trouver avec la requete suivante seda_versement_acknowledgment:\""
					+ transferIdentifier + "\"");

		filename = archive_identifier + "__" + replyIdentifier + "_accusé_d_acceptation_versement.xml";

		nodeService.addAspect(destinationNodeRef, SAEMModelConstants.ASPECT_VERSE, null);
		nodeService.removeAspect(destinationNodeRef, SAEMModelConstants.ASPECT_NON_VERSE);
		
		if(!nodeService.getProperty(destinationNodeRef, SAEMModelConstants.PROP_ARCHIVE_STATE_PROP).equals(
				SAEMArchiveStates.INTERMEDIAIRE)) {
			Utils.removeContentAfterVersement(destinationNodeRef, nodeService, contentService);
		}
		
		state = "acceptation versement";

		if (nodeService.getProperty(destinationNodeRef, SAEMModelConstants.PROP_ARCHIVE_STATE_PROP).equals(
				SAEMArchiveStates.INTERMEDIAIRE)) {
			// Si etat intermediaire (Définitif)
			Utils.setArchiveState(nodeService, destinationNodeRef, SAEMArchiveStates.DEFINITIF);
			nodeService.addAspect(destinationNodeRef, SAEMModelConstants.ASPECT_RESTITUABLE, null);
		} else {
			// L'archive a été accepté dans As@lae (intermediaire)
			Utils.setArchiveState(nodeService, destinationNodeRef, SAEMArchiveStates.INTERMEDIAIRE);
			nodeService.addAspect(destinationNodeRef, SAEMModelConstants.ASPECT_RESTITUABLE, null);
		}
	}

	/**
	 * Method : doActionBordereauArchiveTransferReplyType()
	 * @param att
	 * @throws Exception void
	 */
	private void doActionBordereauArchiveTransferReplyType(ArchiveTransferReplyType att) throws Exception {

		String transferIdentifier = att.getTransferIdentifier().getValue();
		String replyIdentifier = att.getTransferReplyIdentifier().getValue();
		SearchParameters sp = new SearchParameters();
		String archive_identifier = "";

		sp.addStore(StoreRef.STORE_REF_WORKSPACE_SPACESSTORE);
		sp.setLanguage(SearchService.LANGUAGE_LUCENE);
		sp.setQuery("@saem\\:seda_versement_acknowledgment:\"" + transferIdentifier + "\"");

		ResultSet queryResults = searchService.query(sp);

		if (queryResults != null && queryResults.getNodeRef(0) != null) {
			destinationNodeRef = queryResults.getNodeRef(0);
			archive_identifier = (String) nodeService.getProperty(destinationNodeRef,
					SAEMModelConstants.PROP_SEDA_ARCHIVE_ARCHIVAL_AGENCY_ARCHIVE_IDENTIFIER);
		} else
			throw new Exception("Aucune archive trouver avec la requete suivante seda_versement_acknowledgment:\""
					+ transferIdentifier + "\"");

		if (att.getReplyCode().getValue().equals(ReplyCodeType.NUM000)) {
			filename = archive_identifier + "__" + replyIdentifier + "_accusé_de_reception_versement.xml";
			state = "reception versement";

			// L'archive a été versé dans As@lae (versé)
			Utils.setArchiveState(nodeService, destinationNodeRef, SAEMArchiveStates.VERSER);

		} else {
			filename = archive_identifier + "__" + replyIdentifier + "_accusé_de_rejet_versement.xml";
			if (destinationNodeRef != null) {
				nodeService.addAspect(destinationNodeRef, SAEMModelConstants.ASPECT_NON_VERSE, null);
			}
			state = "rejet versement";

			// L'archive a été refusé dans As@lae (normal)
			Utils.setArchiveState(nodeService, destinationNodeRef, SAEMArchiveStates.NORMAL);
		}
	}

	public void setContentService(ContentService contentService) {
		this.contentService = contentService;
	}

	public void setNodeService(NodeService nodeService) {
		this.nodeService = nodeService;
	}

	public void setSearchService(SearchService searchService) {
		this.searchService = searchService;
	}

	public void setSiteService(SiteService siteService) {
		this.siteService = siteService;
	}

	public void setServiceRegistry(ServiceRegistry serviceRegistry) {
		this.serviceRegistry = serviceRegistry;
	}

	public void setProfilSEDAUtils(ProfilSEDAUtils profilSEDAUtils) {
		this.profilSEDAUtils = profilSEDAUtils;
	}

	public void setFileFolderService(FileFolderService fileFolderService) {
		this.fileFolderService = fileFolderService;
	}

	public void setPropertyReader(PropertyReader propertyReader) {
		this.propertyReader = propertyReader;
	}
}
