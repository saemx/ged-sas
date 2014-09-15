package eu.akka.saem.alfresco.webscript;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.alfresco.model.ContentModel;
import org.alfresco.service.cmr.repository.ContentReader;
import org.alfresco.service.cmr.repository.ContentService;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.util.Pair;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.extensions.webscripts.AbstractWebScript;
import org.springframework.extensions.webscripts.WebScriptRequest;
import org.springframework.extensions.webscripts.WebScriptResponse;

import eu.akka.saem.alfresco.constants.SAEMModelConstants;
import eu.akka.saem.alfresco.constants.SAEMPropertiesConstants;
import eu.akka.saem.alfresco.seda.v02.ArchiveTransferType;
import eu.akka.saem.alfresco.seda.v02.complexType.ArchiveObjectType;
import eu.akka.saem.alfresco.seda.v02.complexType.DocumentType;
import eu.akka.saem.alfresco.seda.v02.complexType.KeywordType;
import eu.akka.saem.alfresco.utils.ProfilSEDAUtils;

/**
 * 
 *   Récupération des propriétés SEDA pour affichage dans l'IHM
 * 
 * @Class         : GetSedaPropertiesBean.java
 * @Package       : eu.akka.saem.alfresco.webscript
 * @Date          : $Date: 25 févr. 2014 $:
 * @author        : $Author: THOMAS.POGNANT $
 * @version       : $Revision:  $:
 * @Id            : $Id: GetSedaPropertiesBean.java $
 *
 */
public class GetSedaPropertiesBean extends AbstractWebScript {

	private static final String NODE_REF_PARAMETER = "nodeRef";
	private NodeService nodeService;
	private ProfilSEDAUtils profilSEDAUtils;
	private ContentService contentService;
	private static final Log LOG = LogFactory.getLog(GetSedaPropertiesBean.class);

	@Override
	public void execute(WebScriptRequest req, WebScriptResponse res) throws IOException {
		String nodeRefParameter = req.getParameter(NODE_REF_PARAMETER);
		NodeRef nodeRef = new NodeRef(nodeRefParameter);
		if (nodeService.hasAspect(nodeRef, SAEMModelConstants.ASPECT_SOUS_OBJET_ARCHIVE)) {
			res.getWriter().write(getSousObjectArchiveProperties(nodeRef));
		} else if (nodeService.hasAspect(nodeRef, SAEMModelConstants.ASPECT_ARCHIVABLE)) {
			res.getWriter().write(getArchiveProperties(nodeRef));
		} else if(nodeService.hasAspect(nodeRef, SAEMModelConstants.ASPECT_DOCUMENT_ARCHIVE)){
			res.getWriter().write(getDocumentProperties(nodeRef));
		}
	}

	private String getDocumentProperties(NodeRef nodeRef) {
		DocumentType dt = (DocumentType) profilSEDAUtils.getSedaObject(nodeRef,
				SAEMModelConstants.PROP_SEDA_DOCUMENT_XML);
		List<Pair<String, String>> values = new ArrayList<>();
		
		if(dt.getType() != null && dt.getType().size() > 0){
			if(dt.getType().get(0).getValue() != null){
				values.add(new Pair<String, String>(SAEMPropertiesConstants.PROP_SEDA_DOCUMENT_TYPE,
						dt.getType().get(0).getValue().toString()));
			}
		}
		
		ContentReader cr = contentService.getReader(nodeRef, ContentModel.PROP_CONTENT);
		if(cr != null && cr.getSize() != 0){
			InputStream contentStream = cr.getContentInputStream();
			String md5 = computeHash(contentStream);
			if(md5 != "" && md5 != null){
				values.add(new Pair<String, String>(SAEMPropertiesConstants.PROP_SEDA_DOCUMENT_HASH,
						md5));
			}
		}
		
		return buildJson(values);
	}

	private String getSousObjectArchiveProperties(NodeRef nodeRef) {
		ArchiveObjectType aot = (ArchiveObjectType) profilSEDAUtils.getSedaObject(nodeRef,
				SAEMModelConstants.PROP_SEDA_SOUS_OBJET_ARCHIVE_XML);
		List<Pair<String, String>> values = new ArrayList<>();
		if (aot != null) {
			if (aot.getContentDescription()!=null){
				if (aot.getContentDescription().getOldestDate() != null) {
					values.add(new Pair<String, String>(SAEMPropertiesConstants.PROP_SEDA_DATE_DE_FIN,
							aot.getContentDescription().getOldestDate().getValue()));
				}
				if (aot.getContentDescription().getLatestDate() != null) {
					values.add(new Pair<String, String>(SAEMPropertiesConstants.PROP_SEDA_DATE_DE_DEBUT,
							aot.getContentDescription().getLatestDate().getValue()));
				}
				if (aot.getContentDescription().getDescription() != null) {
					values.add(new Pair<String, String>(SAEMPropertiesConstants.PROP_SEDA_DESCRIPTION,
							aot.getContentDescription().getDescription()));
				}
			}
			if(aot.getTransferringAgencyObjectIdentifier()!= null){
				if (aot.getTransferringAgencyObjectIdentifier().getValue() != null) {
					values.add(new Pair<String, String>(SAEMPropertiesConstants.PROP_SEDA_ORIGIN_IDENTIFIER,
							aot.getTransferringAgencyObjectIdentifier().getValue()));
				}
			}
		}
		
		return buildJson(values);
	}

	private String getArchiveProperties(NodeRef nodeRef) {
		ArchiveTransferType archiveSedaObject = (ArchiveTransferType) profilSEDAUtils.getSedaObject(nodeRef,
				SAEMModelConstants.PROP_SEDA_ARCHIVE_XML);
		List<Pair<String, String>> values = new ArrayList<>();
		if (archiveSedaObject != null) {
			if (archiveSedaObject.getTransferringAgency().getName() != null) {
				values.add(new Pair<String, String>(SAEMPropertiesConstants.PROP_SEDA_SERVICE_VERSANT,
						archiveSedaObject.getTransferringAgency().getName()));
			}
			if (archiveSedaObject.getTransferIdentifier().getValue() != null) {
				values.add(new Pair<String, String>(SAEMPropertiesConstants.PROP_SEDA_TRANSFER_IDENTIFIER,
						archiveSedaObject.getTransferIdentifier().getValue()));
			}
			if (archiveSedaObject.getDate().getValue() != null && archiveSedaObject.getDate().getValue() != "") {
				values.add(new Pair<String, String>(SAEMPropertiesConstants.PROP_SEDA_TRANSFER_DATE,
						archiveSedaObject.getDate().getValue()));
			}
			//NEW SAEM 123
			if(archiveSedaObject.getContains() != null && archiveSedaObject.getContains().size() > 0){
				if(archiveSedaObject.getContains().get(0).getDescriptionLevel() != null){
					if (archiveSedaObject.getContains().get(0).getDescriptionLevel().getValue() != null) {
						values.add(new Pair<String, String>(SAEMPropertiesConstants.PROP_SEDA_DESCRIPTION_LEVEL,
								archiveSedaObject.getContains().get(0).getDescriptionLevel().getValue().toString()));
					}
				}
				if(archiveSedaObject.getContains().get(0).getAppraisal() != null){
					if (archiveSedaObject.getContains().get(0).getAppraisal().getCode().getValue() != null) {
						values.add(new Pair<String, String>(SAEMPropertiesConstants.PROP_SEDA_FINAL_SORT,
								archiveSedaObject.getContains().get(0).getAppraisal().getCode().getValue().toString()));
					}
				}
				if(archiveSedaObject.getContains().get(0).getAccessRestriction() != null){
					if (archiveSedaObject.getContains().get(0).getAccessRestriction().getCode().getValue() != null) {
						values.add(new Pair<String, String>(SAEMPropertiesConstants.PROP_SEDA_FINAL_SORT_RULE,
								archiveSedaObject.getContains().get(0).getAccessRestriction().getCode().getValue().toString()));
					}
				}
				if(archiveSedaObject.getContains().get(0).getContentDescription() != null && archiveSedaObject.getContains().get(0).getContentDescription().getContentDescriptive() != null){
					for(KeywordType kt : archiveSedaObject.getContains().get(0).getContentDescription().getContentDescriptive()){
						if (kt.getKeywordContent() != null) {
							values.add(new Pair<String, String>(SAEMPropertiesConstants.PROP_SEDA_KEYWORDS,
									kt.getKeywordContent()));
						}
					}
				}
			}
			
		}
		return buildJson(values);
	}

	@SuppressWarnings("unchecked")
	private String buildJson(List<Pair<String, String>> values) {
		JSONObject result = new JSONObject();

		JSONArray items = new JSONArray();

		for (Pair<String, String> value : values) {
			JSONObject jsonobject = new JSONObject();
			jsonobject.put("title", StringEscapeUtils.escapeHtml(value.getFirst()));
			jsonobject.put("value", StringEscapeUtils.escapeHtml(value.getSecond()));
			items.add(jsonobject);
		}

		result.put("items", items);

		return result.toJSONString();
	}
	
    private String computeHash(InputStream contentStream) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            LOG.error("Unable to process algorith type MD5");
            return null;
        }
        messageDigest.reset();
        byte[] buffer = new byte[1024];
        int bytesRead = -1;
        try {
            while ((bytesRead = contentStream.read(buffer)) > -1) {
                messageDigest.update(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
        	LOG.error("Unable to read content stream.", e);
            return null;
        } finally {
            try {
                contentStream.close();
            } catch (IOException e) {}
        }
        byte[] digest = messageDigest.digest();
        return convertByteArrayToHex(digest);
    }
 
    private String convertByteArrayToHex(byte[] array) {
        StringBuffer hashValue = new StringBuffer();
        for (int i = 0; i < array.length; i++) {
            String hex = Integer.toHexString(0xFF & array[i]);
            if (hex.length() == 1) {
                hashValue.append('0');
            }
            hashValue.append(hex);
        }
        return hashValue.toString().toUpperCase();
    }

	// Setters pour spring
	public void setNodeService(NodeService nodeService) {
		this.nodeService = nodeService;
	}

	public void setProfilSEDAUtils(ProfilSEDAUtils profilSEDAUtils) {
		this.profilSEDAUtils = profilSEDAUtils;
	}
	
	public void setContentService(ContentService contentService) {
		this.contentService = contentService;
	}

}
