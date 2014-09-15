package eu.akka.saem.alfresco.webscript;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;

import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.cmr.workflow.WorkflowService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.simple.JSONObject;
import org.springframework.extensions.webscripts.AbstractWebScript;
import org.springframework.extensions.webscripts.Status;
import org.springframework.extensions.webscripts.WebScriptRequest;
import org.springframework.extensions.webscripts.WebScriptResponse;

import eu.akka.saem.alfresco.constants.SAEMArchiveStates;
import eu.akka.saem.alfresco.constants.SAEMModelConstants;
import eu.akka.saem.alfresco.seda.loader.XMLLoader;
import eu.akka.saem.alfresco.seda.v02.ArchiveTransferType;
import eu.akka.saem.alfresco.seda.v02.complexType.AppraisalRulesType;
import eu.akka.saem.alfresco.seda.v02.complexType.ArchiveObjectType;
import eu.akka.saem.alfresco.seda.v02.complexType.ISO8601;
import eu.akka.saem.alfresco.seda.v02.simpleType.AppraisalCodeType;

/**
 * 
 * Webscript d'evaluator pour savoir si une archive est eliminable
 * 
 * @Class : IsEliminationLaunchableBean.java
 * @Package : eu.akka.saem.alfresco.webscript
 * @Date : $Date: 25 févr. 2014 $:
 * @author : $Author: THOMAS.POGNANT $
 * @version : $Revision: $:
 * @Id : $Id: IsEliminationLaunchableBean.java $
 * 
 */
public class IsEliminationLaunchableBean extends AbstractWebScript {

	private NodeService nodeService;

	private static final Log LOG = LogFactory
			.getLog(IsEliminationLaunchableBean.class);

	@Override
	public void execute(WebScriptRequest req, WebScriptResponse res)
			throws IOException {
		JSONObject result = new JSONObject();

		if (req.getParameter("nodeRef") == null
				|| req.getParameter("nodeRef") == "") {
			res.setStatus(Status.STATUS_BAD_REQUEST);
			return;
		}

		try {

			// NodeRef du dossier
			NodeRef nodeRef = new NodeRef(req.getParameter("nodeRef"));

			NodeRef archivableNode = nodeRef;
			
			while (archivableNode != null) {
				
				if(nodeService.hasAspect(archivableNode, SAEMModelConstants.ASPECT_ARCHIVABLE)){
					break;
				} else { 
					archivableNode = nodeService.getPrimaryParent(archivableNode).getParentRef();
				}
			}
			
			if (objectIsEliminable(nodeRef) && nodeService.hasAspect(archivableNode, SAEMModelConstants.ASPECT_VERSE)
					&& nodeService.hasAspect(nodeRef, SAEMModelConstants.ASPECT_ELIMINABLE)
					&& nodeService.getProperty(nodeRef, SAEMModelConstants.PROP_ARCHIVE_STATE_PROP) != null
					&& !SAEMArchiveStates.ELIMINER.equals((String) nodeService.getProperty(nodeRef, SAEMModelConstants.PROP_ARCHIVE_STATE_PROP))
					&& !SAEMArchiveStates.DEFINITIF.equals((String) nodeService.getProperty(nodeRef, SAEMModelConstants.PROP_ARCHIVE_STATE_PROP))) {
				result.put("result", true);
			} else
				result.put("result", false);
		} catch (Exception e) {
			res.setStatus(Status.STATUS_INTERNAL_SERVER_ERROR);
			LOG.error(
					"Une erreur s'est produite au cours de la récupération du nodeRef",
					e);
		}
		res.setStatus(Status.STATUS_OK);
		res.getWriter().write(JSONObject.toJSONString(result));
	}

	private boolean objectIsEliminable(NodeRef nodeRef) throws Exception {

		AppraisalRulesType art = null;

		if (nodeService.hasAspect(nodeRef,
				SAEMModelConstants.ASPECT_SOUS_OBJET_ARCHIVE)) {
			String bordereauSEDA = (String) nodeService.getProperty(nodeRef,
					SAEMModelConstants.PROP_SEDA_SOUS_OBJET_ARCHIVE_XML);
			InputStream is = new ByteArrayInputStream(bordereauSEDA.getBytes());
			art = ((ArchiveObjectType) XMLLoader.SEDALoader(is)).getAppraisal();
		} else if (nodeService.hasAspect(nodeRef,
				SAEMModelConstants.ASPECT_ARCHIVABLE)) {
			String bordereauSEDA = (String) nodeService.getProperty(nodeRef,
					SAEMModelConstants.PROP_SEDA_ARCHIVE_XML);
			InputStream is = new ByteArrayInputStream(bordereauSEDA.getBytes());
			ArchiveTransferType att = (ArchiveTransferType) XMLLoader
					.SEDALoader(is);
			art = att.getContains().get(0).getAppraisal();
		} else {
			return false;
		}

		String dua = null;
		String startDate = null;
		AppraisalCodeType sortFinal = null;

		if (art != null) {
			if (art.getDuration() != null)
				dua = art.getDuration().getValue();

			if (art.getStartDate() != null)
				startDate = art.getStartDate().getValue();

			if (art.getCode() != null)
				sortFinal = art.getCode().getValue();
		}

		int dua_int = 0;
		Date startDate_date = null;

		if (sortFinal.equals(AppraisalCodeType.CONSERVER))
			return false;
		else if (nodeService.hasAspect(nodeRef,
				SAEMModelConstants.ASPECT_ARCHIVABLE)
				&& (dua == null || startDate == null))
			return false;
		else if (nodeService.hasAspect(nodeRef,
				SAEMModelConstants.ASPECT_SOUS_OBJET_ARCHIVE)
				&& (dua == null || startDate == null))
			return objectIsEliminable(nodeService.getPrimaryParent(nodeRef)
					.getParentRef());
		else if (dua == null || startDate == null)
			throw new Exception(
					"La DUA ou la date de commencement n'existe pas");
		else {
			if (dua.startsWith("P") && dua.endsWith("Y")) {
				dua_int = Integer.parseInt(dua.replace("P", "")
						.replace("Y", ""));
			} else {
				throw new Exception("La DUA ne possede pas le format P*Y");
			}

			ISO8601 da = new ISO8601(startDate);
			startDate_date = da.getDate();

			Calendar startDate_cal = Calendar.getInstance();
			startDate_cal.setTime(startDate_date);
			startDate_cal.add(Calendar.YEAR, dua_int);

			Date now = new Date();

			if (startDate_cal.getTime().compareTo(now) <= 0) {
				return true;
			} else {
				return false;
			}
		}
	}

	public void setNodeService(NodeService nodeService) {
		this.nodeService = nodeService;
	}
}
