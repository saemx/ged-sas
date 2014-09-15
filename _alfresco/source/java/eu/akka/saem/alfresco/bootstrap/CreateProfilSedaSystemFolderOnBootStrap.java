package eu.akka.saem.alfresco.bootstrap;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.alfresco.model.ContentModel;
import org.alfresco.repo.action.evaluator.IsSubTypeEvaluator;
import org.alfresco.repo.action.executer.AddFeaturesActionExecuter;
import org.alfresco.repo.action.executer.LinkCategoryActionExecuter;
import org.alfresco.repo.action.executer.RemoveFeaturesActionExecuter;
import org.alfresco.repo.security.authentication.AuthenticationUtil;
import org.alfresco.repo.security.authentication.AuthenticationUtil.RunAsWork;
import org.alfresco.repo.transaction.RetryingTransactionHelper;
import org.alfresco.repo.transaction.RetryingTransactionHelper.RetryingTransactionCallback;
import org.alfresco.service.cmr.action.Action;
import org.alfresco.service.cmr.action.ActionService;
import org.alfresco.service.cmr.action.CompositeAction;
import org.alfresco.service.cmr.model.FileFolderService;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.cmr.rule.Rule;
import org.alfresco.service.cmr.rule.RuleService;
import org.alfresco.service.cmr.rule.RuleType;
import org.apache.log4j.Logger;

import eu.akka.saem.alfresco.constants.SAEMModelConstants;
import eu.akka.saem.alfresco.constants.SAEMPropertiesConstants;
import eu.akka.saem.alfresco.helper.PropertyReader;

/**
 * 
 *   Classe permettant de générer les profils SEDA
 *   au lancement de l'application
 * 
 * @Class         : CreateProfilSedaSystemFolderOnBootStrap.java
 * @Package       : eu.akka.saem.alfresco.bootstrap
 * @Date          : $Date: 25 févr. 2014 $:
 * @author        : $Author: THOMAS.POGNANT $
 * @version       : $Revision:  $:
 * @Id            : $Id: CreateProfilSedaSystemFolderOnBootStrap.java $
 *
 */
public class CreateProfilSedaSystemFolderOnBootStrap {

	private final String ADD_SEDA_ASPECT = "Add Profil SEDA aspect";
	private final String REMOVE_SEDA_ASPECT = "Remove Profil SEDA aspect";
	private final String ADD_SEDA_CATEGORY = "Add SEDA category aspect";
	
	private NodeService nodeService;
	private RuleService ruleService;
	private FileFolderService fileFolderService;
	private PropertyReader propertyReader;
	private ActionService actionService;

	private RetryingTransactionHelper retryingTransactionHelper;

	private String profilFolderName;
	private NodeRef categoryProfilNodeRef;
	private String categoryProfilNodeRefProperty;
	private String dataDictionnaryFolderNodeRefProperty;
	private NodeRef dataDictionnaryNodeRef;

	private static Logger LOG = Logger.getLogger(CreateProfilSedaSystemFolderOnBootStrap.class);

	public void init() {

		// Récupération des propriétés
		profilFolderName = propertyReader.getProperty(SAEMPropertiesConstants.FOLDER_SYSTEM_PROFILS_NAME);
		categoryProfilNodeRefProperty = propertyReader
				.getProperty(SAEMPropertiesConstants.CATEGORY_PROFIL_NODEREF);
		// TODO Trouver un autre moyen de récupérer ce dossier, on economiserait
		// une propriété et donc une configuration
		// Voir pour l'utilisation de companyHomePath, storeRef ou passer par
		// une recherche avec le searchService
		dataDictionnaryFolderNodeRefProperty = propertyReader
				.getProperty(SAEMPropertiesConstants.FOLDER_SYSTEM_DATA_DICTIONNARY_NODEREF);

		if (profilFolderName == null || categoryProfilNodeRefProperty == null
				|| dataDictionnaryFolderNodeRefProperty == null || profilFolderName.isEmpty()
				|| categoryProfilNodeRefProperty.isEmpty() || dataDictionnaryFolderNodeRefProperty.isEmpty()) {
			LOG.error("Les propriétés " + SAEMPropertiesConstants.FOLDER_SYSTEM_PROFILS_NAME + " et / ou "
					+ SAEMPropertiesConstants.CATEGORY_PROFIL_NODEREF + " et / ou "
					+ SAEMPropertiesConstants.FOLDER_SYSTEM_DATA_DICTIONNARY_NODEREF
					+ " ne sont pas correctement renseignés, le déploiement du dossier systeme"
					+ " \"Profils SEDA\" ne pourra se faire, veuillez renseigner "
					+ "cette propriété et redémarrer le serveur");
			return;
		}
		categoryProfilNodeRef = new NodeRef(categoryProfilNodeRefProperty);
		if (categoryProfilNodeRef == null) {
			LOG.error("La categorie Profil SEDA n'existe pas, veuillez la creer et la renseigner"
					+ " dans le fichier de propriete approprie");
			return;
		}

		dataDictionnaryNodeRef = new NodeRef(dataDictionnaryFolderNodeRefProperty);
		if (dataDictionnaryNodeRef == null) {
			LOG.error("Le dossier systeme dictionnaire de données renseigné dans les propriétés"
					+ " n'est pas le bon, veuillez le mettre à jour et redémarrer le serveur");
			return;
		}
		NodeRef profilFolderNodeRef = nodeService.getChildByName(dataDictionnaryNodeRef,
				ContentModel.ASSOC_CONTAINS, profilFolderName);
		if (profilFolderNodeRef == null) {
			profilFolderNodeRef = AuthenticationUtil.runAsSystem(new RunAsWork<NodeRef>() {

				@Override
				public NodeRef doWork() throws Exception {
					return retryingTransactionHelper.doInTransaction(
							new RetryingTransactionCallback<NodeRef>() {
								@Override
								public NodeRef execute() throws Throwable {
									return createProfilFolder(dataDictionnaryNodeRef);
								}
							}, false, true);
				}
			});

		}

		final NodeRef profilFolderNodeRefFinal = profilFolderNodeRef;
		AuthenticationUtil.runAsSystem(new RunAsWork<NodeRef>() {
			@Override
			public NodeRef doWork() throws Exception {
				return retryingTransactionHelper.doInTransaction(new RetryingTransactionCallback<NodeRef>() {
					@Override
					public NodeRef execute() throws Throwable {
						addRulesOnProfilFolder(profilFolderNodeRefFinal);
						return null;
					}
				}, false, true);
			}
		});

	}

	private void addRulesOnProfilFolder(NodeRef profilFolderNodeRef) {
		List<Rule> existingRules = ruleService.getRules(profilFolderNodeRef);

		Rule ruleAddAspect = null;
		Rule ruleRemoveAspect = null;
		Rule ruleAddCategory = null;

		for (Rule rule : existingRules) {
			if (rule.getTitle().equals(ADD_SEDA_ASPECT)
					|| rule.getTitle().equals(REMOVE_SEDA_ASPECT)
					|| rule.getTitle().equals(ADD_SEDA_CATEGORY)){
				ruleService.removeRule(profilFolderNodeRef, rule);
			}
		}	

		ruleAddAspect = new Rule();
			ruleAddAspect.setTitle(ADD_SEDA_ASPECT);
			ruleAddAspect
			.setDescription("Ajoute l'aspect \"profil SEDA\" à tous les éléments nouvellement créés dans le dossier");
			ruleAddAspect.applyToChildren(false);
			ruleAddAspect.setExecuteAsynchronously(false);
			ruleAddAspect.setRuleDisabled(false);
			ruleAddAspect.setRuleType(RuleType.INBOUND);
			CompositeAction compositeActionAddAspect = actionService.createCompositeAction();
			ruleAddAspect.setAction(compositeActionAddAspect);

			// Conditions for the Rule
			Map<String, Serializable> actionAddAspectMap = new HashMap<String, Serializable>();
			actionAddAspectMap.put(IsSubTypeEvaluator.PARAM_TYPE, ContentModel.TYPE_CONTENT);
			compositeActionAddAspect.addActionCondition(actionService.createActionCondition(
					IsSubTypeEvaluator.NAME, actionAddAspectMap));

			// Action
			Action actionAddAspect = actionService.createAction(AddFeaturesActionExecuter.NAME);
			compositeActionAddAspect.addAction(actionAddAspect);

			// Parameters for the action
			Map<String, Serializable> actionAddAspectProps = compositeActionAddAspect.getParameterValues();
			actionAddAspectProps.put(AddFeaturesActionExecuter.PARAM_ASPECT_NAME,
					SAEMModelConstants.ASPECT_PROFIL);
			actionAddAspect.setParameterValues(actionAddAspectProps);

			ruleService.saveRule(profilFolderNodeRef, ruleAddAspect);


			// Ajout d'une règle retrait de l'aspect profil SEDA
			ruleRemoveAspect = new Rule();
			ruleRemoveAspect.setTitle(REMOVE_SEDA_ASPECT);
			ruleRemoveAspect
			.setDescription("Retire l'aspect \"profil SEDA\" à tous les éléments supprimés du dossier");
			ruleRemoveAspect.applyToChildren(false);
			ruleRemoveAspect.setExecuteAsynchronously(false);
			ruleRemoveAspect.setRuleDisabled(false);
			ruleRemoveAspect.setRuleType(RuleType.OUTBOUND);
			CompositeAction compositeActionRemoveAspect = actionService.createCompositeAction();
			ruleRemoveAspect.setAction(compositeActionRemoveAspect);

			// Conditions for the Rule
			Map<String, Serializable> actionRemoveAspectMap = new HashMap<String, Serializable>();
			actionRemoveAspectMap.put(IsSubTypeEvaluator.PARAM_TYPE, ContentModel.TYPE_CONTENT);
			compositeActionRemoveAspect.addActionCondition(actionService.createActionCondition(
					IsSubTypeEvaluator.NAME, actionRemoveAspectMap));

			// Action
			Action actionRemoveAspect = actionService.createAction(RemoveFeaturesActionExecuter.NAME);
			compositeActionRemoveAspect.addAction(actionRemoveAspect);

			// Parameters for the action
			Map<String, Serializable> actionRemoveAspectProps = compositeActionRemoveAspect.getParameterValues();
			actionRemoveAspectProps.put(RemoveFeaturesActionExecuter.PARAM_ASPECT_NAME,
					SAEMModelConstants.ASPECT_PROFIL);
			actionRemoveAspect.setParameterValues(actionRemoveAspectProps);

			ruleService.saveRule(profilFolderNodeRef, ruleRemoveAspect);

			// Ajoute une règle d'ajout catégorie
			ruleAddCategory = new Rule();
			ruleAddCategory.setTitle(ADD_SEDA_CATEGORY);
			ruleAddCategory
			.setDescription("Ajoute la catégorie \"profil SEDA\" à tous les éléments nouvellement créés dans le dossier");
			ruleAddCategory.applyToChildren(false);
			ruleAddCategory.setExecuteAsynchronously(false);
			ruleAddCategory.setRuleDisabled(false);
			ruleAddCategory.setRuleType(RuleType.INBOUND);
			CompositeAction compositeAction = actionService.createCompositeAction();
			ruleAddCategory.setAction(compositeAction);

			// Conditions for the Rule
			Map<String, Serializable> actionMap = new HashMap<String, Serializable>();
			actionMap.put(IsSubTypeEvaluator.PARAM_TYPE, ContentModel.TYPE_CONTENT);
			compositeAction.addActionCondition(actionService.createActionCondition(IsSubTypeEvaluator.NAME,
					actionMap));

			// Action
			Action action = actionService.createAction(LinkCategoryActionExecuter.NAME);
			compositeAction.addAction(action);

			// Parameters for the action
			Map<String, Serializable> actionProps = compositeAction.getParameterValues();
			actionProps.put(LinkCategoryActionExecuter.PARAM_CATEGORY_ASPECT,
					ContentModel.ASPECT_GEN_CLASSIFIABLE);

			actionProps.put(LinkCategoryActionExecuter.PARAM_CATEGORY_VALUE, categoryProfilNodeRef);
			action.setParameterValues(actionProps);

			ruleService.saveRule(profilFolderNodeRef, ruleAddCategory);
		}

		private NodeRef createProfilFolder(NodeRef parentNodeRef) {
			NodeRef newFolderNodeRef = fileFolderService.create(parentNodeRef, profilFolderName,
					ContentModel.TYPE_FOLDER).getNodeRef();
			return newFolderNodeRef;
		}

		public void setNodeService(NodeService nodeService) {
			this.nodeService = nodeService;
		}

		public void setRuleService(RuleService ruleService) {
			this.ruleService = ruleService;
		}

		public void setFileFolderService(FileFolderService fileFolderService) {
			this.fileFolderService = fileFolderService;
		}

		public void setPropertyReader(PropertyReader propertyReader) {
			this.propertyReader = propertyReader;
		}

		public void setActionService(ActionService actionService) {
			this.actionService = actionService;
		}

		public void setRetryingTransactionHelper(RetryingTransactionHelper retryingTransactionHelper) {
			this.retryingTransactionHelper = retryingTransactionHelper;
		}
	}
