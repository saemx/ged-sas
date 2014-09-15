package eu.akka.saem.alfresco.workflow;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.alfresco.repo.notification.EMailNotificationProvider;
import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.cmr.notification.NotificationContext;
import org.alfresco.service.cmr.repository.ChildAssociationRef;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.search.ResultSet;
import org.alfresco.service.cmr.search.SearchParameters;
import org.alfresco.service.cmr.search.SearchService;
import org.alfresco.web.bean.repository.Repository;

/**
 * 
 *   Classe utilitaire contenant une méthode d'envoi de mail
 * 
 * @Class         : SAEMWorkflowUtils.java
 * @Package       : eu.akka.saem.alfresco.workflow
 * @Date          : $Date: 25 févr. 2014 $:
 * @author        : $Author: THOMAS.POGNANT $
 * @version       : $Revision:  $:
 * @Id            : $Id: SAEMWorkflowUtils.java $
 *
 */
public class SAEMWorkflowUtils {

	public static void sendMail(ServiceRegistry services, String workflowId, String workflowDescription,
			String archiveName, NodeRef workflowPackage, String initiator, String siteName, String taskId,
			boolean workflowTerminated, String workflowName, boolean svValidation) throws Exception {

		SearchService searchService = services.getSearchService();
		NotificationContext notificationContext = new NotificationContext();

		String subject = "";
		// Determine the subject of the notification
		if (workflowName.equals("Elimination"))
			subject = workflowTerminated ? 
					"Elimination de l'archive \"" + archiveName + "\" validée par le service d'archive" : 
						"Elimination de l'archive \"" + archiveName + "\" refusée par le service d'archive";
		else if (workflowName.equals("Versement"))
			subject = workflowTerminated ? 
					"Versement de l'archive \"" + archiveName + "\" validé par le service d'archive" : 
						"Versement de l'archive \"" + archiveName + "\" refusé par le service d'archive";

		// Récupération du template d'email
		SearchParameters sp = new SearchParameters();
		sp.addStore(Repository.getStoreRef());
		sp.setLanguage(SearchService.LANGUAGE_LUCENE);
		sp.setQuery("PATH:\"/app:company_home/app:dictionary/app:email_templates/cm:saem_email_templates/cm:saemwf-email-" + workflowName.toLowerCase() +".html.ftl\"");
		ResultSet rs = searchService.query(sp);
		if (rs.length() == 0){
			throw new Exception("Impossible de récupérer le template d'email");
		}	

		NodeRef emailTemplate = rs.getNodeRefs().get(0);

		notificationContext.setBodyTemplate(services.getFileFolderService()
				.getLocalizedSibling(emailTemplate));

		// Build the template args
		Map<String, Serializable> templateArgs = new HashMap<String, Serializable>(7);
		templateArgs.put("workflowTerminated", workflowTerminated);
		templateArgs.put("archiveName", archiveName);
		templateArgs.put("workflowId", workflowId);
		templateArgs.put("workflowDescription", workflowDescription);
		templateArgs.put("siteName", siteName);
		templateArgs.put("taskId", taskId);
		templateArgs.put("svValidation", svValidation);

		if (workflowName.equals("Versement")){
			if (workflowPackage != null) {
				// Add details of associated content
				List<ChildAssociationRef> assocs = services.getNodeService().getChildAssocs(workflowPackage);
				NodeRef[] docs = new NodeRef[assocs.size()];
				if (assocs.size() != 0) {
					int index = 0;
					for (ChildAssociationRef assoc : assocs) {
						docs[index] = assoc.getChildRef();
						index++;
					}
					templateArgs.put("workflowDocuments", docs);
				}
			}
		}
		else{
			if (workflowPackage != null) {
				NodeRef[] docs = new NodeRef[1];
				docs[0] = workflowPackage;
				templateArgs.put("workflowDocuments", docs);
			}
		}
		// Set the subject
		notificationContext.setSubject(subject);

		// Set the template args
		notificationContext.setTemplateArgs(templateArgs);

		// Set the notification recipient
		notificationContext.addTo(initiator);

		// Indicate that we want to execute the notification asynchronously
		notificationContext.setAsyncNotification(true);

		// Send email notification
		services.getNotificationService().sendNotification(EMailNotificationProvider.NAME,
				notificationContext);
	}
}
