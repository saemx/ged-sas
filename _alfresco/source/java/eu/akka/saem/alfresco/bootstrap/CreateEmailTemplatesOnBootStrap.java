package eu.akka.saem.alfresco.bootstrap;

import java.io.InputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.alfresco.model.ContentModel;
import org.alfresco.repo.security.authentication.AuthenticationUtil;
import org.alfresco.repo.security.authentication.AuthenticationUtil.RunAsWork;
import org.alfresco.repo.transaction.RetryingTransactionHelper;
import org.alfresco.repo.transaction.RetryingTransactionHelper.RetryingTransactionCallback;
import org.alfresco.service.cmr.repository.ContentService;
import org.alfresco.service.cmr.repository.ContentWriter;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.namespace.NamespaceService;
import org.alfresco.service.namespace.QName;
import org.apache.log4j.Logger;

import eu.akka.saem.alfresco.constants.SAEMPropertiesConstants;
import eu.akka.saem.alfresco.helper.PropertyReader;

/**
 * 
 *   Classe permettant de créer les adresses email
 *   suivant des templates prédéfinis au lancement de l'application
 * 
 * @Class         : CreateEmailTemplatesOnBootStrap.java
 * @Package       : eu.akka.saem.alfresco.bootstrap
 * @Date          : $Date: 25 févr. 2014 $:
 * @author        : $Author: THOMAS.POGNANT $
 * @version       : $Revision:  $:
 * @Id            : $Id: CreateEmailTemplatesOnBootStrap.java $
 *
 */
@Deprecated
public class CreateEmailTemplatesOnBootStrap {

	public static final String EMAIL_TEMPLATES_FOLDER = "Modèles d'e-mail";
	public static final String WORKFLOW_NOTIFICATION_TEMPLATES_FOLDER = "Notification de workflow";
	public static final String SAEM_EMAIL = "saemwf-email.html.ftl";

	private RetryingTransactionHelper retryingTransactionHelper;
	private PropertyReader propertyReader;
	private ContentService contentService;
	private ContentWriter contentWriter;
	private NodeService nodeService;

	private static Logger LOG = Logger.getLogger(CreateEmailTemplatesOnBootStrap.class);

	public void init() {

		AuthenticationUtil.runAsSystem(new RunAsWork<Void>() {

			@Override
			public Void doWork() throws Exception {
				return retryingTransactionHelper.doInTransaction(new RetryingTransactionCallback<Void>() {
					@Override
					public Void execute() throws Throwable {
						runInit();
						return null;
					}
				}, false, true);
			}
		});

	}

	private void runInit() {
		String dataDictionnaryFolderNodeRefProperty = propertyReader
				.getProperty(SAEMPropertiesConstants.FOLDER_SYSTEM_DATA_DICTIONNARY_NODEREF);

		NodeRef dataDictionnaryFolderNodeRef = new NodeRef(dataDictionnaryFolderNodeRefProperty);
		if (!nodeService.exists(dataDictionnaryFolderNodeRef)) {
			LOG.error("Le dossier \"/Dictionnaire de donnee\" n'existe pas, le deploiement des modeles d'e-mail ne pourra se faire. Merci de creer ce dossier");
			return;
		}

		final NodeRef emailTemplatesFolderNoderef = nodeService.getChildByName(dataDictionnaryFolderNodeRef,
				ContentModel.ASSOC_CONTAINS, EMAIL_TEMPLATES_FOLDER);
		if (emailTemplatesFolderNoderef == null) {
			LOG.error("Le dossier \"/Dictionnaire de donnee/Modeles d'e-mail\" n'existe pas, le deploiement des modeles d'e-mail ne pourra se faire. Merci de creer ce dossier");
			return;
		}

		final NodeRef workflowEmailTemplatesFolderNoderef = nodeService.getChildByName(
				emailTemplatesFolderNoderef, ContentModel.ASSOC_CONTAINS,
				WORKFLOW_NOTIFICATION_TEMPLATES_FOLDER);
		if (workflowEmailTemplatesFolderNoderef == null) {
			LOG.error("Le dossier \"/Dictionnaire de donnee/Modeles d'e-mail/Notification de workflow\" n'existe pas, le deploiement des modeles d'e-mail ne pourra se faire. Merci de creer ce dossier");
			return;
		}

		NodeRef saemwfEmailTemplateNodeRef = nodeService.getChildByName(workflowEmailTemplatesFolderNoderef,
				ContentModel.ASSOC_CONTAINS, SAEM_EMAIL);
		if (saemwfEmailTemplateNodeRef == null) {
			Map<QName, Serializable> props = new HashMap<QName, Serializable>(1);
			props.put(ContentModel.PROP_NAME, SAEM_EMAIL);

			NodeRef newEmailTemplate = nodeService.createNode(workflowEmailTemplatesFolderNoderef,
					ContentModel.ASSOC_CONTAINS,
					QName.createQName(NamespaceService.CONTENT_MODEL_1_0_URI, SAEM_EMAIL),
					ContentModel.TYPE_CONTENT, props).getChildRef();

			contentWriter = contentService.getWriter(newEmailTemplate, ContentModel.TYPE_CONTENT, true);
			InputStream templateInputStream = Thread.currentThread().getContextClassLoader()
					.getResourceAsStream("/alfresco/module/eu_akka_alfresco/bootstrap/saemwf-email.html.ftl");
			contentWriter.putContent(templateInputStream);
		}

		return;
	}

	public void setContentService(ContentService contentService) {
		this.contentService = contentService;
	}

	public void setPropertyReader(PropertyReader propertyReader) {
		this.propertyReader = propertyReader;
	}

	public void setNodeService(NodeService nodeService) {
		this.nodeService = nodeService;
	}

	public void setRetryingTransactionHelper(RetryingTransactionHelper retryingTransactionHelper) {
		this.retryingTransactionHelper = retryingTransactionHelper;
	}

}
