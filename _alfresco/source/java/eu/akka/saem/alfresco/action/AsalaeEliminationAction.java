package eu.akka.saem.alfresco.action;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.alfresco.model.ContentModel;
import org.alfresco.repo.action.executer.ActionExecuterAbstractBase;
import org.alfresco.service.cmr.action.Action;
import org.alfresco.service.cmr.action.ParameterDefinition;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.cmr.security.AuthenticationService;
import org.apache.axis.encoding.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import eu.akka.saem.alfresco.connector.asalae.ws.ServerServiceLocator;
import eu.akka.saem.alfresco.connector.asalae.ws.WebServicePortType;
import eu.akka.saem.alfresco.constants.SAEMModelConstants;
import eu.akka.saem.alfresco.constants.SAEMPropertiesConstants;
import eu.akka.saem.alfresco.helper.PropertyReader;
import eu.akka.saem.alfresco.seda.loader.XMLLoader;
import eu.akka.saem.alfresco.seda.v02.ArchiveDestructionRequestType;
import eu.akka.saem.alfresco.seda.v02.ArchiveTransferType;
import eu.akka.saem.alfresco.seda.v02.complexType.ArchiveObjectType;
import eu.akka.saem.alfresco.seda.v02.complexType.ArchiveType;
import eu.akka.saem.alfresco.seda.v02.complexType.ArchivesIDType;
import eu.akka.saem.alfresco.seda.v02.complexType.OrganizationType;
import eu.akka.saem.alfresco.seda.writer.SEDAWriter;
import eu.akka.saem.alfresco.utils.ProfilSEDAUtils;

/**
 * 
 *   Classe déclanchant le lancement du workflow d'élimination côté Asalae
 * 
 * @Class         : AsalaeEliminationAction.java
 * @Package       : eu.akka.saem.alfresco.action
 * @Date          : $Date: 25 févr. 2014 $:
 * @author        : $Author: THOMAS.POGNANT $
 * @version       : $Revision:  $:
 * @Id            : $Id: AsalaeEliminationAction.java $
 *
 */
public class AsalaeEliminationAction extends ActionExecuterAbstractBase{

	private NodeService nodeService;
	private ProfilSEDAUtils profilSEDAUtils;
	private PropertyReader propertyReader;
	private AuthenticationService authenticationService;
	
	private static final Log LOG = LogFactory.getLog(AsalaeEliminationAction.class);
		
	/** 
	 * @param ruleAction pas utilisé dans cette fonction
	 * @param actionedUpondNodeRef: noderef sur lequel l'action est executée. Ici une archive qui a déjà été versée et acceptée dans Asalae, dont la DUA est expirée et le sort final à détruire
	 */
	public void executeImpl(Action ruleAction, NodeRef actionedUponNodeRef){

		try {
			// Récupération des identifiants de connexion à asalae
			String asalaeLogin = propertyReader.getProperty(SAEMPropertiesConstants.ASALAE_INT_LOGIN);
			String asalaePassword = propertyReader.getProperty(SAEMPropertiesConstants.ASALAE_INT_PASSWORD);

			WebServicePortType test;
			ServerServiceLocator ssl = new ServerServiceLocator();
			test = ssl.getWebServicePort(new URL(propertyReader.getProperty(SAEMPropertiesConstants.ASALAE_INT_URL) + "/webservices/service"));

			ArchiveDestructionRequestType adrt = new ArchiveDestructionRequestType();
			adrt.setDestructionRequestIdentifier(new ArchivesIDType());
			String bordereau = SEDAWriter.transformSEDAObjectToXML(adrt);

			String bordereauSEDA = new String(Base64.decode(new String(test.wsCompleterBordereau(Base64
					.encode(bordereau.getBytes("UTF8")).getBytes(), asalaeLogin, asalaePassword))), "UTF8");
			ArchiveDestructionRequestType adrtresult = (ArchiveDestructionRequestType) XMLLoader
					.SEDALoader(new ByteArrayInputStream(bordereauSEDA.getBytes()));

			nodeService.setProperty(actionedUponNodeRef, SAEMModelConstants.PROP_SEDA_ELIMINATION_ACKNOWLEDGMENT,
					adrtresult.getDestructionRequestIdentifier().getValue());
			
			String archiveName;
			
			// creation d'une copie temporaire afin de recuperer -dans le cas ou on elimine un sous-objet d'archive- les informations presentes a la racine de l'archive
			NodeRef tmpArchiveFolder = actionedUponNodeRef;
			ArchiveTransferType archiveSedaObject;

			List<ArchiveType> archives = new ArrayList<ArchiveType>();

			ArchiveType archive = new ArchiveType();
			archive.setArchivalAgencyArchiveIdentifier(new ArchivesIDType());

			// Récupération du bordereau de versement
			if (nodeService.hasAspect(actionedUponNodeRef, SAEMModelConstants.ASPECT_SOUS_OBJET_ARCHIVE)) {
				while (tmpArchiveFolder != null
						&& (!nodeService.hasAspect(tmpArchiveFolder, SAEMModelConstants.ASPECT_ARCHIVABLE)
								|| nodeService.hasAspect(tmpArchiveFolder, SAEMModelConstants.ASPECT_SOUS_OBJET_ARCHIVE)))
					tmpArchiveFolder = nodeService.getPrimaryParent(tmpArchiveFolder).getParentRef();
				ArchiveObjectType aot = new ArchiveObjectType();
				aot.setArchivalAgencyObjectIdentifier(new ArchivesIDType());
				aot.getArchivalAgencyObjectIdentifier().setValue((String) nodeService.getProperty(actionedUponNodeRef,
						SAEMModelConstants.PROP_SEDA_SOUS_OBJET_ARCHIVE_ARCHIVAL_AGENCY_OBJECT_IDENTIFIER));
				archive.addContains(aot);
				archiveName = nodeService.getProperty(tmpArchiveFolder, ContentModel.PROP_NAME) + "." + nodeService.getProperty( actionedUponNodeRef, ContentModel.PROP_NAME);
			} else {
				archive.getArchivalAgencyArchiveIdentifier().setValue(
						(String) nodeService.getProperty(tmpArchiveFolder,
								SAEMModelConstants.PROP_SEDA_ARCHIVE_ARCHIVAL_AGENCY_ARCHIVE_IDENTIFIER));
				archiveName = (String) nodeService.getProperty(tmpArchiveFolder, ContentModel.PROP_NAME);
			}

			archives.add(archive);

			archiveSedaObject = (ArchiveTransferType) profilSEDAUtils.getSedaObject(tmpArchiveFolder,
					SAEMModelConstants.PROP_SEDA_ARCHIVE_XML_FULL);
			archiveSedaObject = fillSedaBlankFields(archiveSedaObject);

			String dri = (String) nodeService.getProperty(actionedUponNodeRef, SAEMModelConstants.PROP_SEDA_ELIMINATION_ACKNOWLEDGMENT);

			adrt = (ArchiveDestructionRequestType) new ArchiveDestructionRequestType();
			adrt.setDestructionRequestIdentifier(new ArchivesIDType());
			adrt.getDestructionRequestIdentifier().setValue(dri);
			adrt.setArchivalAgency(new OrganizationType());
			adrt.getArchivalAgency().setIdentification(new ArchivesIDType());
			adrt.getArchivalAgency().getIdentification()
			.setValue(archiveSedaObject.getArchivalAgency().getIdentification().getValue());

			adrt.setComment("Demande d’élimination règlementaire portant sur " + archiveName);

			adrt.setOriginatingAgency(new OrganizationType());
			adrt.getOriginatingAgency().setIdentification(new ArchivesIDType());
			adrt.getOriginatingAgency()
			.getIdentification()
			.setValue(
					archiveSedaObject.getContains().get(0).getContentDescription()
					.getOriginatingAgency().get(0).getIdentification().getValue());

			
			adrt.setArchive(archives);
			
			nodeService.setProperty(actionedUponNodeRef, SAEMModelConstants.PROP_ELIMINATION_INITIATOR, authenticationService.getCurrentUserName());

			if (!test.wsElimination("bordereau.xml", Base64.encode(SEDAWriter.transformSEDAObjectToXML(adrt).getBytes("UTF8")).getBytes(), asalaeLogin, asalaePassword).equals("0"))
				LOG.error("Processus d'elimination de l'archive avorte");
			else
				nodeService.removeAspect(actionedUponNodeRef, SAEMModelConstants.ASPECT_ELIMINABLE);		 
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	@Override
	protected void addParameterDefinitions(List<ParameterDefinition> paramList) {

	}

	
	public void setNodeService(NodeService nodeService) {
		this.nodeService = nodeService;
	}

	public void setProfilSEDAUtils(ProfilSEDAUtils profilSEDAUtils) {
		this.profilSEDAUtils = profilSEDAUtils;
	}

	public void setPropertyReader(PropertyReader propertyReader) {
		this.propertyReader = propertyReader;
	}


	public void setAuthenticationService(AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}

	private ArchiveTransferType fillSedaBlankFields(ArchiveTransferType archiveSedaObject) {

		// Insertion de la date du transfert
		archiveSedaObject.getDate().setDate(new Date());

		return archiveSedaObject;
	}
}