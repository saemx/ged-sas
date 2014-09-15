package eu.akka.saem.alfresco.seda.v02.complexType;

import java.util.ArrayList;
import java.util.List;

import eu.akka.saem.alfresco.seda.annotations.SEDA;
import eu.akka.saem.alfresco.seda.annotations.SEDAType;
import eu.akka.saem.alfresco.seda.form.FieldType;

/**
 * Sous-ensemble d'une archive.
 * @author benjamin.catinot
 *
 */
public class ArchiveObjectType {
	
	private ArchivesIDType ArchivalAgencyObjectIdentifier;
	private CodeDescriptionLevelType DescriptionLevel;
	private String Name;
	private ArchivesIDType TransferringAgencyObjectIdentifier;
	private ContentDescriptionType ContentDescription;
	private AppraisalRulesType Appraisal;
	private AccessRestrictionRulesType AccessRestriction;
	private List<DocumentType> Document;
	private List<ArchiveObjectType> Contains;
	
	public ArchiveObjectType(){
		
	}
	
	/**
	 * Element
	 * @return Identifiant de l'objet fourni par le service d'archives. L'identifiant pour l'émetteur du message est recommandé, si ce n'est obligatoire.
	 */
	@SEDA(Position=0,Type=SEDAType.ELEMENT,PropertyTerm="ArchivalAgencyObjectIdentifier",Cardinality="0..1",FormName="Identifiant unité documentaire service d'archive",FormDescription="Identifiant de l'objet fourni par le service d'archives. L'identifiant pour l'émetteur du message est recommandé, si ce n'est obligatoire.")
	public ArchivesIDType getArchivalAgencyObjectIdentifier() {
		return ArchivalAgencyObjectIdentifier;
	}
	
	/**
	 * Element
	 * @param Identifiant de l'objet fourni par le service d'archives. L'identifiant pour l'émetteur du message est recommandé, si ce n'est obligatoire.
	 */
	@SEDA(Position=0,Type=SEDAType.ELEMENT,PropertyTerm="ArchivalAgencyObjectIdentifier",Cardinality="0..1",FormName="Identifiant unité documentaire service d'archive",FormDescription="Identifiant de l'objet fourni par le service d'archives. L'identifiant pour l'émetteur du message est recommandé, si ce n'est obligatoire.")
	public void setArchivalAgencyObjectIdentifier(
			ArchivesIDType archivalAgencyObjectIdentifier) {
		ArchivalAgencyObjectIdentifier = archivalAgencyObjectIdentifier;
	}
	
	/**
	 * Element
	 * @return Indique si l'objet décrit est un groupe de documents, un sous-groupe de documents, un dossier, ou une pièce.
	 */
	@SEDA(Position=1,Type=SEDAType.ELEMENT,PropertyTerm="DescriptionLevel",Cardinality="0..1",FormName="Niveau de description",FormDescription="Indique si l'objet décrit est un groupe de documents, un sous-groupe de documents, un dossier, ou une pièce.")
	public CodeDescriptionLevelType getDescriptionLevel() {
		return DescriptionLevel;
	}
	
	/**
	 * Element
	 * @param Indique si l'objet décrit est un groupe de documents, un sous-groupe de documents, un dossier, ou une pièce.
	 */
	@SEDA(Position=1,Type=SEDAType.ELEMENT,PropertyTerm="DescriptionLevel",Cardinality="0..1",FormName="Niveau de description",FormDescription="Indique si l'objet décrit est un groupe de documents, un sous-groupe de documents, un dossier, ou une pièce.")
	public void setDescriptionLevel(CodeDescriptionLevelType descriptionLevel) {
		DescriptionLevel = descriptionLevel;
	}
	
	/**
	 * Element
	 * @return Intitulé du contenu d'information.
	 */
	@SEDA(Position=2,Type=SEDAType.ELEMENT,PropertyTerm="Name",Cardinality="1..1",FormType=FieldType.TEXT,FormName="Nom",FormDescription="Intitulé du contenu d'information.")
	public String getName() {
		return Name;
	}
	
	/**
	 * 
	 * @param Intitulé du contenu d'information.
	 */
	@SEDA(Position=2,Type=SEDAType.ELEMENT,PropertyTerm="Name",Cardinality="1..1",FormType=FieldType.TEXT,FormName="Nom",FormDescription="Intitulé du contenu d'information.")
	public void setName(String name) {
		Name = name;
	}
	
	/**
	 * 
	 * @return Identifiant de l'objet fourni par le service versant. L'identifiant pour l'émetteur du message est recommandé, si ce n'est obligatoire.Intitulé du contenu d'information.
	 */
	@SEDA(Position=3,Type=SEDAType.ELEMENT,PropertyTerm="TransferringAgencyObjectIdentifier",Cardinality="0..1",FormName="Identifiant d'origine",FormDescription="Identifiant de l'objet fourni par le service versant. L'identifiant pour l'émetteur du message est recommandé, si ce n'est obligatoire.Intitulé du contenu d'information.")
	public ArchivesIDType getTransferringAgencyObjectIdentifier() {
		return TransferringAgencyObjectIdentifier;
	}
	
	/**
	 * 
	 * @param Identifiant de l'objet fourni par le service versant. L'identifiant pour l'émetteur du message est recommandé, si ce n'est obligatoire.Intitulé du contenu d'information.
	 */
	@SEDA(Position=3,Type=SEDAType.ELEMENT,PropertyTerm="TransferringAgencyObjectIdentifier",Cardinality="0..1",FormName="Identifiant d'origine",FormDescription="Identifiant de l'objet fourni par le service versant. L'identifiant pour l'émetteur du message est recommandé, si ce n'est obligatoire.Intitulé du contenu d'information.")
	public void setTransferringAgencyObjectIdentifier(
			ArchivesIDType transferringAgencyObjectIdentifier) {
		TransferringAgencyObjectIdentifier = transferringAgencyObjectIdentifier;
	}
	
	/**
	 * 
	 * @return Description du contenu
	 */
	@SEDA(Position=4,Type=SEDAType.ELEMENT,PropertyTerm="ContentDescription",Cardinality="0..1",FormName="Description du contenu",FormDescription="Description du contenu")
	public ContentDescriptionType getContentDescription() {
		return ContentDescription;
	}
	
	/**
	 * Element
	 * @param  Description du contenu
	 */
	@SEDA(Position=4,Type=SEDAType.ELEMENT,PropertyTerm="ContentDescription",Cardinality="0..1",FormName="Description du contenu",FormDescription="Description du contenu")
	public void setContentDescription(ContentDescriptionType contentDescription) {
		ContentDescription = contentDescription;
	}
	
	/**
	 * Element
	 * @return
	 */
	@SEDA(Position=5,Type=SEDAType.ELEMENT,PropertyTerm="Appraisal",Cardinality="0..1",FormName="Règle de sort final",FormDescription="Règle de sort final")
	public AppraisalRulesType getAppraisal() {
		return Appraisal;
	}
	
	/**
	 * Element
	 * @param appraisal
	 */
	@SEDA(Position=5,Type=SEDAType.ELEMENT,PropertyTerm="Appraisal",Cardinality="0..1",FormName="Règle de sort final",FormDescription="Règle de sort final")
	public void setAppraisal(AppraisalRulesType appraisal) {
		Appraisal = appraisal;
	}
	
	/**
	 * Element
	 * @return
	 */
	@SEDA(Position=6,Type=SEDAType.ELEMENT,PropertyTerm="AccessRestriction",Cardinality="0..1",FormName="Règle de restriction d'accès",FormDescription="Règle de restriction d'accès")
	public AccessRestrictionRulesType getAccessRestriction() {
		return AccessRestriction;
	}
	
	/**
	 * Element
	 * @param accessRestriction
	 */
	@SEDA(Position=6,Type=SEDAType.ELEMENT,PropertyTerm="AccessRestriction",Cardinality="0..1",FormName="Règle de restriction d'accès",FormDescription="Règle de restriction d'accès")
	public void setAccessRestriction(AccessRestrictionRulesType accessRestriction) {
		AccessRestriction = accessRestriction;
	}
	
	/**
	 * Element
	 * @return
	 */
	@SEDA(Position=7,Type=SEDAType.ELEMENT,PropertyTerm="Document",Cardinality="0..n",FormName="Document",FormDescription="Document")
	public List<DocumentType> getDocument() {
		return Document;
	}
	
	/**
	 * Element
	 * @param document
	 */
	@SEDA(Position=7,Type=SEDAType.ELEMENT,PropertyTerm="Document",Cardinality="0..n",FormName="Document",FormDescription="Document")
	public void setDocument(List<DocumentType> document) {
		Document = document;
	}
	
	@SEDA(Position=7,Type=SEDAType.ELEMENT,PropertyTerm="Document",Cardinality="0..n",FormName="Document jointe",FormDescription="Document")
	public void addDocument(DocumentType document) {
		if(Document == null)
			Document = new ArrayList<DocumentType>();
		
		Document.add(document);
	}
	
	/**
	 * Element
	 * @return
	 */
	@SEDA(Position=9,Type=SEDAType.ELEMENT,PropertyTerm="Contains",Cardinality="0..n",FormName="Unité documentaire",FormDescription="Unité documentaire")
	public List<ArchiveObjectType> getContains() {
		return Contains;
	}
	
	/**
	 * Element
	 * @param contains
	 */
	@SEDA(Position=9,Type=SEDAType.ELEMENT,PropertyTerm="Contains",Cardinality="0..n",FormName="Unité documentaire",FormDescription="Unité documentaire")
	public void setContains(List<ArchiveObjectType> contains) {
		Contains = contains;
	}
	
	/**
	 * Element
	 * @param contains
	 */
	@SEDA(Position=9,Type=SEDAType.ELEMENT,PropertyTerm="Contains",Cardinality="0..n",FormName="Unité documentaire",FormDescription="Unité documentaire")
	public void addContains(ArchiveObjectType contains) {
		if(Contains == null)
			Contains = new ArrayList<ArchiveObjectType>();
		
		Contains.add(contains);
	}
	
}
