package eu.akka.saem.alfresco.webscript;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;

import javax.xml.parsers.ParserConfigurationException;

import org.alfresco.model.ContentModel;
import org.alfresco.service.cmr.repository.ContentIOException;
import org.alfresco.service.cmr.repository.ContentReader;
import org.alfresco.service.cmr.repository.ContentService;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.extensions.webscripts.AbstractWebScript;
import org.springframework.extensions.webscripts.WebScriptRequest;
import org.springframework.extensions.webscripts.WebScriptResponse;
import org.xml.sax.SAXException;

import eu.akka.saem.alfresco.constants.SAEMModelConstants;
import eu.akka.saem.alfresco.seda.form.SEDAModel;
import eu.akka.saem.alfresco.seda.loader.AGAPELoader;
import eu.akka.saem.alfresco.seda.loader.XMLLoader;
import eu.akka.saem.alfresco.seda.loader.agape.AGAPEObject;
import eu.akka.saem.alfresco.seda.v02.ArchiveTransferType;

/**
 * 
 *   Classe de creation des archives dans Asalae 
 * 
 * @Class         : CreateArchiveBean.java
 * @Package       : eu.akka.saem.alfresco.webscript
 * @Date          : $Date: 25 févr. 2014 $:
 * @author        : $Author: THOMAS.POGNANT $
 * @version       : $Revision:  $:
 * @Id            : $Id: CreateArchiveBean.java $
 *
 */
public class CreateArchiveBean extends AbstractWebScript {

	private static final Log LOG = LogFactory.getLog(CreateArchiveBean.class);
	private ContentService contentService;
	private NodeService nodeService;

	@Override
	public void execute(WebScriptRequest req, WebScriptResponse res)
			throws IOException {
		String result = null;

		if (req.getParameter("archive") != null) {
			try {
				SEDAModel model = null;

			NodeRef archiveNodeRef = new NodeRef(req.getParameter("archive"));
			String profilContent = (String) nodeService.getProperty(archiveNodeRef,
					SAEMModelConstants.PROP_SEDA_ARCHIVE_PROFIL_MODEL);
			
			String dataContent = (String) nodeService.getProperty(archiveNodeRef,
					SAEMModelConstants.PROP_SEDA_ARCHIVE_XML_FULL);

			InputStream dataContentStream = new ByteArrayInputStream(dataContent.getBytes());
			
			model = new SEDAModel(new JSONObject(profilContent),(ArchiveTransferType) XMLLoader.SEDALoader(dataContentStream));
			
			result = model.toJSON();
			
			} catch (ContentIOException | ClassNotFoundException
					| InstantiationException | IllegalAccessException
					| SecurityException | NoSuchMethodException
					| IllegalArgumentException | ParserConfigurationException | SAXException | IOException | JSONException e) {
				LOG.error("Une erreur s'est produite lors de la récupération des données de l'archive", e);
			}

		} else if (req.getParameter("profil") != null) {
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
					AGAPEObject agapeobject = AGAPELoader
							.ArchiveTransferLoader(reader
									.getContentInputStream());
					model = new SEDAModel(agapeobject);
				}

				result = model.toJSON();

			} catch (ContentIOException | ClassNotFoundException
					| InstantiationException | IllegalAccessException
					| SecurityException | NoSuchMethodException
					| IllegalArgumentException | InvocationTargetException
					| ParserConfigurationException | SAXException | IOException e) {
				LOG.error("Une erreur s'est produite au parsing du profil SEDA", e);
			}
		}

		res.setContentEncoding("UTF-8");
		res.setContentType("application/json");
		res.getWriter().write(result);
	}

	public void setContentService(ContentService contentService) {
		this.contentService = contentService;
	}

	public void setNodeService(NodeService nodeService) {
		this.nodeService = nodeService;
	}

}
