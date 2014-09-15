package eu.akka.saem.alfresco.form;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.alfresco.model.ContentModel;
import org.alfresco.repo.forms.FieldDefinition;
import org.alfresco.repo.forms.Form;
import org.alfresco.repo.forms.FormData;
import org.alfresco.repo.forms.PropertyFieldDefinition;
import org.alfresco.repo.forms.PropertyFieldDefinition.FieldConstraint;
import org.alfresco.repo.forms.processor.AbstractFilter;
import org.alfresco.repo.forms.processor.node.FormFieldConstants;
import org.alfresco.service.cmr.dictionary.ClassDefinition;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.namespace.NamespaceService;
import org.apache.log4j.Logger;

import eu.akka.saem.alfresco.constants.SAEMModelConstants;
import eu.akka.saem.alfresco.utils.ProfilSEDAUtils;

/**
 * Listener sur la génération des formulaires Modifie le formulaire d'un dossier
 * ayant l'aspect profilable pour ajouter sur la métadonnées Profil SEDA la
 * liste des profils SEDA récupérés dans le dossier système correspond
 * (administration de ce dossier systeme par le fichier de propriété
 * saem.properties
 * 
 * @Class         : ProfilArchivageFormFilter.java
 * @Package       : eu.akka.saem.alfresco.form
 * @Date          : $Date: 25 févr. 2014 $:
 * @author        : $Author: THOMAS.POGNANT $
 * @version       : $Revision:  $:
 * @Id            : $Id: ProfilArchivageFormFilter.java $
 *
 */
public class ProfilArchivageFormFilter extends AbstractFilter<Object, NodeRef> {

	private NamespaceService namespaceService;
	private NodeService nodeService;
	private ProfilSEDAUtils profilSEDAUtils;
	
	private static Logger LOG = Logger.getLogger(ProfilArchivageFormFilter.class);

	/**
	 * Point d'entrée
	 */
	@Override
	public void afterGenerate(Object item, List<String> fields, List<String> forcedFields, Form form,
			Map<String, Object> context) {
		boolean isProfilableFolderForm = false;
		if (item.getClass().getName().equals(NodeRef.class.getName())) {
			isProfilableFolderForm = ContentModel.TYPE_FOLDER.equals(nodeService.getType((NodeRef) item))
					&& nodeService.hasAspect((NodeRef) item, SAEMModelConstants.ASPECT_PROFILABLE);
		} else {
			ClassDefinition contentType = (ClassDefinition) item;
			isProfilableFolderForm = ContentModel.TYPE_FOLDER.equals(contentType.getName());
		}
		if (isProfilableFolderForm) {

			// lookup field definition
			final String PROP_PROFIL_DataKey = FormFieldConstants.PROP_DATA_PREFIX
					+ SAEMModelConstants.PROP_PROFIL_NAME.toPrefixString(this.namespaceService).replace(':', '_');

			final List<FieldDefinition> fieldDefinitions = form.getFieldDefinitions();

			for (FieldDefinition def : fieldDefinitions) {
				if (def instanceof PropertyFieldDefinition
						&& PROP_PROFIL_DataKey.equals(def.getDataKeyName()))
					addProfilConstraints(fieldDefinitions,(NodeRef) item);
			}
		}
	}

	@Override
	public void afterPersist(Object item, FormData data, NodeRef persistedObject) {
	}

	@Override
	public void beforeGenerate(Object item, List<String> fields, List<String> forcedFileds, Form form,
			Map<String, Object> context) {
	}

	@Override
	public void beforePersist(Object item, FormData data) {
	}

	/**
	 * Ajoute les contraintes sur le champ "profil"
	 * @param fieldDefinitions
	 * @param item 
	 */
	private void addProfilConstraints(final List<FieldDefinition> fieldDefinitions, NodeRef item) {

		final String lookupDataKey = FormFieldConstants.PROP_DATA_PREFIX
				+ SAEMModelConstants.PROP_PROFIL_NAME.toPrefixString(this.namespaceService).replace(':', '_');

		List<String> options = null;
		// collect your options		
		options = profilSEDAUtils.getProfilsSEDAList();

		LOG.debug("Ajout des contraintes sur le champ profil");
		addPropertyListOptions(lookupDataKey, fieldDefinitions, options);
	}

	/**
	 * Méthode générique réutilisable pour l'insertion d'une liste de contrainte dans un champ
	 * TODO Etudier la possibilité de sortir cette méthode dans une classe utilitaire
	 * @param lookupDataKey
	 * @param fieldDefinitions
	 * @param options
	 */
	private void addPropertyListOptions(final String lookupDataKey,
			final List<FieldDefinition> fieldDefinitions, final List<String> options) {
		PropertyFieldDefinition propertyDefinition = null;

		for (final FieldDefinition def : fieldDefinitions) {
			if (def instanceof PropertyFieldDefinition && lookupDataKey.equals(def.getDataKeyName())) {
				propertyDefinition = (PropertyFieldDefinition) def;
				break;
			}
		}

		if (propertyDefinition != null) {
			// build constraints
			final Map<String, Object> constraintParams = new HashMap<String, Object>();

			constraintParams.put("caseSensitive", true);
			constraintParams.put("allowedValues", options);

			// actually constrain the field
			final FieldConstraint constraint = new FieldConstraint("LIST", constraintParams);
			List<FieldConstraint> constraints = propertyDefinition.getConstraints();
			if (constraints == null) {
				constraints = new ArrayList<PropertyFieldDefinition.FieldConstraint>();
				propertyDefinition.setConstraints(constraints);
			}
			constraints.add(constraint);
		}
	}

	
	//Setters pour Spring
	public void setNamespaceService(NamespaceService namespaceService) {
		this.namespaceService = namespaceService;
	}

	public void setNodeService(NodeService nodeService) {
		this.nodeService = nodeService;
	}
	
	public void setProfilSEDAUtils(ProfilSEDAUtils profilSEDAUtils) {
		this.profilSEDAUtils = profilSEDAUtils;
	}


}
