package eu.akka.saem.alfresco.utils;

import java.lang.reflect.Field;

import org.alfresco.model.ContentModel;
import org.alfresco.repo.content.MimetypeMap;
import org.alfresco.repo.content.transform.ContentTransformer;
import org.alfresco.service.cmr.repository.ChildAssociationRef;
import org.alfresco.service.cmr.repository.ContentReader;
import org.alfresco.service.cmr.repository.ContentService;
import org.alfresco.service.cmr.repository.ContentWriter;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.cmr.repository.TransformationOptions;

import eu.akka.saem.alfresco.constants.SAEMModelConstants;
import eu.akka.saem.alfresco.seda.annotations.SEDA;

/**
 * 
 *   Classe utilitaire
 * 
 * @Class         : Utils.java
 * @Package       : eu.akka.saem.alfresco.utils
 * @Date          : $Date: 25 févr. 2014 $:
 * @author        : $Author: THOMAS.POGNANT $
 * @version       : $Revision:  $:
 * @Id            : $Id: Utils.java $
 *
 */
public class Utils {

	/**
	 * @param class1
	 * @param nodeValue
	 * @return
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public static Object findValueOnEnum(Class<?> class1, String nodeValue) throws InstantiationException, IllegalAccessException {
		if(nodeValue==null)
			nodeValue="";
		
		for(Field o:class1.getFields()){
			SEDA sedaAnnotation = o.getAnnotation(SEDA.class);
			if(sedaAnnotation != null && nodeValue.equals(sedaAnnotation.PropertyTerm()))
				return o.get(class1);			
		}
		
		return null;
	}
	
	
	/**
	 * Methode permettant de positionner l'état de l'archive
	 * sur chaque noeud en tant qu'aspect
	 * 
	 * Method : setArchiveState()
	 * @param nodeService
	 * @param nodeRef
	 * @param state void
	 */
	public static void setArchiveState(NodeService nodeService,NodeRef nodeRef, String state) {
		if(nodeService.getProperty(nodeRef, SAEMModelConstants.ASPECT_ARCHIVE_STATE) == null){
			nodeService.addAspect(nodeRef, SAEMModelConstants.ASPECT_ARCHIVE_STATE, null);
			nodeService.setProperty(nodeRef, SAEMModelConstants.PROP_ARCHIVE_STATE_PROP, state);
		}
		else{
			nodeService.setProperty(nodeRef, SAEMModelConstants.PROP_ARCHIVE_STATE_PROP, state);
		}
		
		putStateOnChildsRecursif(nodeService,nodeRef, state);
	}
	
	/**
	 * Method : putStateOnChildsRecursif()
	 * @param nodeService
	 * @param noderef
	 * @param state void
	 */
	public static void putStateOnChildsRecursif(NodeService nodeService,NodeRef noderef, String state){
		
		for (ChildAssociationRef fils : nodeService.getChildAssocs(noderef)){
			if(nodeService.getType(noderef).equals(ContentModel.TYPE_FOLDER)){
				nodeService.setProperty(fils.getChildRef(), SAEMModelConstants.PROP_ARCHIVE_STATE_PROP, state);
				putStateOnChildsRecursif(nodeService,fils.getChildRef(), state);
			}
		}
	}
	
	
	/**
	 * Methode qui efface le contenu des documents de l'archive tout en maintenant l'indexation full text. Le contenu des documents en question est déplacé en format texte dans la propriété hidden_content.
	 * 
	 * @param node Document dont le contenu doit être effacé et copié dans la metadonnée
	 * @param nodeService nodeService 
	 * @param contentService contentService
	 */
	public static void removeContentAfterVersement(NodeRef node, NodeService nodeService,
			ContentService contentService) {
		if (nodeService.getChildAssocs(node) != null) {
			for (ChildAssociationRef childAssoc : nodeService.getChildAssocs(node)) {
				NodeRef child = childAssoc.getChildRef();

				if (nodeService.getType(child).equals(ContentModel.TYPE_CONTENT)
						&& !nodeService.hasAspect(child, SAEMModelConstants.ASPECT_IS_ACCUSE)) {

					nodeService.addAspect(child, SAEMModelConstants.ASPECT_SEARCHABLE_AFTER_VERSEMENT, null);

					ContentReader contentReader = contentService.getReader(child, ContentModel.PROP_CONTENT);
					ContentWriter contentWriter = contentService.getWriter(child, ContentModel.PROP_CONTENT,
							true);
					ContentWriter hiddenContentWriter = contentService.getWriter(child,
							SAEMModelConstants.PROP_HIDDEN_CONTENT, true);
					hiddenContentWriter.setMimetype(MimetypeMap.MIMETYPE_TEXT_PLAIN);

					if (contentReader.getMimetype().equals(MimetypeMap.MIMETYPE_TEXT_PLAIN)) {
						hiddenContentWriter.putContent(contentReader.getContentString());
					}

					else {
						ContentTransformer contentTransformer = contentService.getTransformer(
								contentReader.getMimetype(), MimetypeMap.MIMETYPE_TEXT_PLAIN);

						TransformationOptions options = new TransformationOptions();
						options.setSourceNodeRef(child);
						options.setSourceContentProperty(ContentModel.PROP_CONTENT);
						options.setTargetNodeRef(child);
						options.setTargetContentProperty(SAEMModelConstants.PROP_HIDDEN_CONTENT);

						if (contentReader != null
								&& hiddenContentWriter != null
								&& contentTransformer != null
								&& contentTransformer.isTransformable(contentReader.getMimetype(),
										contentReader.getSize(), hiddenContentWriter.getMimetype(), options)) {
							try{
								contentTransformer.transform(contentReader, hiddenContentWriter);
							}
							catch(Exception ex){
								throw ex;
							}
						}
					}

					contentWriter.putContent("");

				} else if (nodeService.getType(child).equals(ContentModel.TYPE_FOLDER)) {
					removeContentAfterVersement(child, nodeService, contentService);
				}
			}
		}
	}
}
