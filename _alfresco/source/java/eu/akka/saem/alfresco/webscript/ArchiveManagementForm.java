package eu.akka.saem.alfresco.webscript;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.alfresco.model.ContentModel;
import org.alfresco.service.cmr.repository.ContentIOException;
import org.alfresco.service.cmr.repository.ContentReader;
import org.alfresco.service.cmr.repository.ContentService;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.simple.JSONObject;
import org.springframework.extensions.webscripts.AbstractWebScript;
import org.springframework.extensions.webscripts.WebScriptRequest;
import org.springframework.extensions.webscripts.WebScriptResponse;
import org.xml.sax.SAXException;

import eu.akka.saem.alfresco.constants.SAEMModelConstants;
import eu.akka.saem.alfresco.seda.form.HTMLForm;
import eu.akka.saem.alfresco.seda.form.SEDAModel;
import eu.akka.saem.alfresco.seda.internationalisation.StructureMessageSEDA;
import eu.akka.saem.alfresco.seda.loader.AGAPELoader;
import eu.akka.saem.alfresco.seda.loader.XMLLoader;
import eu.akka.saem.alfresco.seda.loader.agape.AGAPEObject;
import eu.akka.saem.alfresco.seda.writer.SEDAWriter;


/**
 * Classe utilisé par le formulaire pour
 * la récupération des event lors du remplissage
 *   
 * 
 * @Class         : ArchiveManagementForm.java
 * @Package       : eu.akka.saem.alfresco.webscript
 * @Date          : $Date: 25 févr. 2014 $:
 * @author        : $Author: THOMAS.POGNANT $
 * @version       : $Revision:  $:
 * @Id            : $Id: ArchiveManagementForm.java $
 *
 */
public class ArchiveManagementForm extends AbstractWebScript {

	private static final Log LOG = LogFactory.getLog(ArchiveManagementForm.class);
	private ContentService contentService;
	private NodeService nodeService;

	@Override
	public void execute(WebScriptRequest req, WebScriptResponse res)
			throws IOException {
		String result = "";
		JSONObject jsonobject = new JSONObject();
		if (req.getParameter("profil") != null) {
			NodeRef profilNodeRef = null;
			profilNodeRef = new NodeRef(req.getParameter("profil"));

			// Récupération du fichier RNG
			ContentReader reader = contentService.getReader(profilNodeRef,
					ContentModel.PROP_CONTENT);
			try {
				SEDAModel model = null;

				String name = (String) nodeService.getProperty(profilNodeRef,
						ContentModel.PROP_NAME);
				/*if (name.endsWith(".rng")) {
					RNGObject rngobject = RNGLoader
							.ArchiveTransferLoader(reader
									.getContentInputStream());
					model = new SEDAModel(rngobject);
				} else*/
				if (name.endsWith(".xml")) {
					InputStream in = reader.getContentInputStream();
					AGAPEObject agapeobject = AGAPELoader
							.ArchiveTransferLoader(in);
					
					String sedawri = SEDAWriter.transformSEDAObjectToXML(agapeobject.getObject());
					InputStream in2 = new ByteArrayInputStream(sedawri.getBytes());
					Object o = XMLLoader.SEDALoader(in2);
					
					
					HTMLForm htmlform = new HTMLForm(new StructureMessageSEDA(),o,agapeobject,HTMLForm.MODE.NEW);
					
					jsonobject.put("ifhtml", htmlform.formToken);
					
					List<String> strings = htmlform.getHTML();
					
					for(String string: strings){
						result += string;
					}
				}

				jsonobject.put("htmldata", result.replaceAll("\"", "\\\""));

			} catch (ContentIOException | ClassNotFoundException
					| InstantiationException | IllegalAccessException
					| SecurityException | NoSuchMethodException
					| IllegalArgumentException | InvocationTargetException
					| ParserConfigurationException | SAXException | IOException e) {
				LOG.error(
						"Une erreur s'est produite au parsing du profil SEDA",
						e);
			}
		}
		else if(req.getParameter("nodeRef") != null){
			
			NodeRef node = new NodeRef(req.getParameter("nodeRef"));
			ContentReader cr = contentService.getReader(node, ContentModel.PROP_CONTENT);
			
			try {
				Object ao = XMLLoader.SEDALoader(cr.getContentInputStream());
				
				HTMLForm htmlform = new HTMLForm(new StructureMessageSEDA(),ao,null,HTMLForm.MODE.READ);
				
				List<String> strings = htmlform.getHTML();
				
				for(String string: strings){
					result += string;
				}
				
			} catch (ContentIOException | SecurityException
					| ParserConfigurationException | SAXException
					| ClassNotFoundException | InstantiationException
					| IllegalAccessException | NoSuchMethodException e) {
				e.printStackTrace();
			}
			
			jsonobject.put("htmldata", result.replaceAll("\"", "\\\""));
			
		}
		else if(req.getParameter("archive") != null){
			
			NodeRef node = new NodeRef(req.getParameter("archive"));
			String rest = (String) nodeService.getProperty(node, SAEMModelConstants.PROP_SEDA_ARCHIVE_XML_FULL);
			AGAPEObject ao = AGAPEObject.JSONToObject((String) nodeService.getProperty(node, SAEMModelConstants.PROP_SEDA_ARCHIVE_PROFIL_MODEL));
			InputStream is = new ByteArrayInputStream(rest.getBytes());
			try {
				InputStream in = new ByteArrayInputStream(rest.getBytes()); 
				
				HTMLForm htmlform = new HTMLForm(new StructureMessageSEDA(),XMLLoader.SEDALoader(is),ao,HTMLForm.MODE.EDIT);
				
				List<String> strings = htmlform.getHTML();
				
				for(String string: strings){
					result += string;
				}
				
				jsonobject.put("ifhtml", htmlform.formToken);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			jsonobject.put("htmldata", result.replaceAll("\"", "\\\""));
			
		}
		else if(req.getParameter("archiveRead") != null){
			
			NodeRef node = new NodeRef(req.getParameter("archiveRead"));
			String rest = (String) nodeService.getProperty(node, SAEMModelConstants.PROP_SEDA_ARCHIVE_XML_FULL);
			InputStream is = new ByteArrayInputStream(rest.getBytes());
			try {
				InputStream in = new ByteArrayInputStream(rest.getBytes()); 
				
				HTMLForm htmlform = new HTMLForm(new StructureMessageSEDA(),XMLLoader.SEDALoader(is),null,HTMLForm.MODE.READ);
				
				List<String> strings = htmlform.getHTML();
				
				for(String string: strings){
					result += string;
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			jsonobject.put("htmldata", result.replaceAll("\"", "\\\""));
			
		}

		res.setContentEncoding("UTF-8");
		res.getWriter().write(jsonobject.toJSONString());
	}

	public void setContentService(ContentService contentService) {
		this.contentService = contentService;
	}

	public void setNodeService(NodeService nodeService) {
		this.nodeService = nodeService;
	}

}
