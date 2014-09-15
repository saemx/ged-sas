package eu.akka.saem.alfresco.seda.v02.complexType;

import java.util.ArrayList;
import java.util.List;
import eu.akka.saem.alfresco.seda.annotations.SEDA;
import eu.akka.saem.alfresco.seda.annotations.SEDAType;
import eu.akka.saem.alfresco.seda.form.FieldType;

/**
 * Ensemble constitué d'un contenu d'information et de son information de pérennisation.
 * @author benjamin.catinot
 *
 */
public class ArchiveType {
	
	private ArchivesIDType ArchivalAgencyArchiveIdentifier;
	private ArchivesIDType ArchivalAgreement;
	private ArchivesIDType ArchivalProfile;
	private List<CodeLanguageType> DescriptionLanguage;
	private CodeDescriptionLevelType DescriptionLevel;
	private String Name;
	private List<ArchivesCodeType> ServiceLevel;
	private ArchivesIDType TransferringAgencyArchiveIdentifier;
	private ContentDescriptionType ContentDescription;
	private AppraisalRulesType Appraisal;
	private AccessRestrictionRulesType AccessRestriction;
	private List<DocumentType> Document;
	private List<ArchiveObjectType> Contains;
	
	public ArchiveType(){
	}
	
	
	/**
	 * Element
	 * @return Identifiant de l'archive attribué par le service d'archive. L'identifiant pour l'émetteur du message est recommandé, si ce n'est obligatoire.
	 */
	@SEDA(Position=1,Type=SEDAType.ELEMENT,PropertyTerm="ArchivalAgencyArchiveIdentifier",Cardinality="0..1",FormName="Identifiant de l'archive",FormDescription="Identifiant de l'archive attribué par le service d'archive. L'identifiant pour l'émetteur du message est recommandé, si ce n'est obligatoire")
	public ArchivesIDType getArchivalAgencyArchiveIdentifier() {
		return ArchivalAgencyArchiveIdentifier;
	}
	/**
	 * Element
	 * @param Identifiant de l'archive attribué par le service d'archive. L'identifiant pour l'émetteur du message est recommandé, si ce n'est obligatoire.
	 */
	@SEDA(Position=1,Type=SEDAType.ELEMENT,PropertyTerm="ArchivalAgencyArchiveIdentifier",Cardinality="0..1",FormName="Identifiant de l'archive",FormDescription="Identifiant de l'archive attribué par le service d'archive. L'identifiant pour l'émetteur du message est recommandé, si ce n'est obligatoire")
	public void setArchivalAgencyArchiveIdentifier(
			ArchivesIDType archivalAgencyArchiveIdentifier) {
		ArchivalAgencyArchiveIdentifier = archivalAgencyArchiveIdentifier;
	}
	
	/**
	 * Element
	 * @return Indique la convention d'archivage à appliquer pour l'archive..
	 */
	@SEDA(Position=2,Type=SEDAType.ELEMENT,PropertyTerm="ArchivalAgreement",Cardinality="0..1",FormName="Convention",FormDescription="Indique la convention d'archivage à appliquer pour l'archive..")
	public ArchivesIDType getArchivalAgreement() {
		return ArchivalAgreement;
	}
	
	/**
	 * Element
	 * @param Indique la convention d'archivage à appliquer pour l'archive..
	 */
	@SEDA(Position=2,Type=SEDAType.ELEMENT,PropertyTerm="ArchivalAgreement",Cardinality="0..1",FormName="Convention",FormDescription="Indique la convention d'archivage à appliquer pour l'archive..")
	public void setArchivalAgreement(ArchivesIDType archivalAgreement) {
		ArchivalAgreement = archivalAgreement;
	}
	
	/**
	 * Element
	 * @return Indique la méthodologie de production de l'archive appliquée (structure adaptée au domaine particulier traité, par exemple archivage d'un dossier de marché public).
	 */
	@SEDA(Position=3,Type=SEDAType.ELEMENT,PropertyTerm="ArchivalProfile",Cardinality="0..1",FormName="Profil",FormDescription="Indique la méthodologie de production de l'archive appliquée (structure adaptée au domaine particulier traité, par exemple archivage d'un dossier de marché public).")
	public ArchivesIDType getArchivalProfile() {
		return ArchivalProfile;
	}
	
	/**
	 * Element
	 * @param Indique la méthodologie de production de l'archive appliquée (structure adaptée au domaine particulier traité, par exemple archivage d'un dossier de marché public).
	 */
	@SEDA(Position=3,Type=SEDAType.ELEMENT,PropertyTerm="ArchivalProfile",Cardinality="0..1",FormName="Profil",FormDescription="Indique la méthodologie de production de l'archive appliquée (structure adaptée au domaine particulier traité, par exemple archivage d'un dossier de marché public).")
	public void setArchivalProfile(ArchivesIDType archivalProfile) {
		ArchivalProfile = archivalProfile;
	}
	
	/**
	 * Element
	 * @return Langue des descriptions.
	 */
	@SEDA(Position=4,Type=SEDAType.ELEMENT,PropertyTerm="DescriptionLanguage",Cardinality="1..n",FormName="Langue de la description",FormDescription="Langue des descriptions.")
	public List<CodeLanguageType> getDescriptionLanguage() {
		return DescriptionLanguage;
	}
	
	/**
	 * Element
	 * @param Langue des descriptions.
	 */
	@SEDA(Position=0,Type=SEDAType.ELEMENT,PropertyTerm="DescriptionLanguage",Cardinality="1..n",FormName="Langue de la description",FormDescription="Langue des descriptions.")
	public void setDescriptionLanguage(List<CodeLanguageType> descriptionLanguage) {
		DescriptionLanguage = descriptionLanguage;
	}
	
	/**
	 * Element
	 * @param Langue des descriptions.
	 */
	@SEDA(Position=4,Type=SEDAType.ELEMENT,PropertyTerm="DescriptionLanguage",Cardinality="1..n",FormName="Langue de la description",FormDescription="Langue des descriptions.")
	public void addDescriptionLanguage(CodeLanguageType descriptionLanguage) {
		if(DescriptionLanguage == null)
			DescriptionLanguage = new ArrayList<CodeLanguageType>();
		
		DescriptionLanguage.add(descriptionLanguage);
	}
	
	/**
	 * Element
	 * @return Indique si l'objet décrit est un groupe de documents, un sous-groupe de documents, un dossier, ou une pièce.
	 */
	@SEDA(Position=5,Type=SEDAType.ELEMENT,PropertyTerm="DescriptionLevel",Cardinality="1..1",FormName="Niveau de description",FormDescription="Indique si l'objet décrit est un groupe de documents, un sous-groupe de documents, un dossier, ou une pièce.")
	public CodeDescriptionLevelType getDescriptionLevel() {
		return DescriptionLevel;
	}
	
	/**
	 * Element
	 * @param Indique si l'objet décrit est un groupe de documents, un sous-groupe de documents, un dossier, ou une pièce.
	 */
	@SEDA(Position=5,Type=SEDAType.ELEMENT,PropertyTerm="DescriptionLevel",Cardinality="1..1",FormName="Niveau de description",FormDescription="Indique si l'objet décrit est un groupe de documents, un sous-groupe de documents, un dossier, ou une pièce.")
	public void setDescriptionLevel(CodeDescriptionLevelType descriptionLevel) {
		DescriptionLevel = descriptionLevel;
	}
	
	/**
	 * Element
	 * @return Intitulé du contenu d'information.
	 */
	@SEDA(Position=6,Type=SEDAType.ELEMENT,PropertyTerm="Name",Cardinality="1..1", FormType=FieldType.TEXT,FormName="Nom",FormDescription="Intitulé du contenu d'information.")
	public String getName() {
		return Name;
	}
	
	/**
	 * Element
	 * @param Intitulé du contenu d'information.
	 */
	@SEDA(Position=6,Type=SEDAType.ELEMENT,PropertyTerm="Name",Cardinality="1..1", FormType=FieldType.TEXT,FormName="Nom",FormDescription="Intitulé du contenu d'information.")
	public void setName(String name) {
		Name = name;
	}
	
	/**
	 * Element
	 * @return Niveau de service demandé (disponibilité, sécurité...), en référence aux différents niveaux prévus par le contrat ou la convention passée entre le service versant et le service d'archives.
	 */
	@SEDA(Position=7,Type=SEDAType.ELEMENT,PropertyTerm="ServiceLevel",Cardinality="0..n",FormName="Niveau de service demandé",FormDescription="Niveau de service demandé (disponibilité, sécurité...), en référence aux différents niveaux prévus par le contrat ou la convention passée entre le service versant et le service d'archives.")
	public List<ArchivesCodeType> getServiceLevel() {
		return ServiceLevel;
	}
	
	/**
	 * Element
	 * @param Niveau de service demandé (disponibilité, sécurité...), en référence aux différents niveaux prévus par le contrat ou la convention passée entre le service versant et le service d'archives.
	 */
	@SEDA(Position=7,Type=SEDAType.ELEMENT,PropertyTerm="ServiceLevel",Cardinality="0..n",FormName="Niveau de service demandé",FormDescription="Niveau de service demandé (disponibilité, sécurité...), en référence aux différents niveaux prévus par le contrat ou la convention passée entre le service versant et le service d'archives.")
	public void setServiceLevel(List<ArchivesCodeType> serviceLevel) {
		ServiceLevel = serviceLevel;
	}
	
	/**
	 * Element
	 * @param Niveau de service demandé (disponibilité, sécurité...), en référence aux différents niveaux prévus par le contrat ou la convention passée entre le service versant et le service d'archives.
	 */
	@SEDA(Position=7,Type=SEDAType.ELEMENT,PropertyTerm="ServiceLevel",Cardinality="0..n",FormName="Niveau de service demandé",FormDescription="Niveau de service demandé (disponibilité, sécurité...), en référence aux différents niveaux prévus par le contrat ou la convention passée entre le service versant et le service d'archives.")
	public void addServiceLevel(ArchivesCodeType serviceLevel) {
		if(ServiceLevel == null)
			ServiceLevel = new ArrayList<ArchivesCodeType>();
		
		ServiceLevel.add(serviceLevel);
	}
	
	/**
	 * Element
	 * @return Identifiant d'archive fourni par le service versant. L'identifiant pour l'émetteur du message est recommandé, si ce n'est obligatoire..
	 */
	@SEDA(Position=8,Type=SEDAType.ELEMENT,PropertyTerm="TransferringAgencyArchiveIdentifier",Cardinality="0..1",FormName="Identifiant service versant",FormDescription="Identifiant d'archive fourni par le service versant. L'identifiant pour l'émetteur du message est recommandé, si ce n'est obligatoire..")
	public ArchivesIDType getTransferringAgencyArchiveIdentifier() {
		return TransferringAgencyArchiveIdentifier;
	}
	
	/**
	 * Element
	 * @param Identifiant d'archive fourni par le service versant. L'identifiant pour l'émetteur du message est recommandé, si ce n'est obligatoire..
	 */
	@SEDA(Position=8,Type=SEDAType.ELEMENT,PropertyTerm="TransferringAgencyArchiveIdentifier",Cardinality="0..1",FormName="Identifiant service versant",FormDescription="Identifiant d'archive fourni par le service versant. L'identifiant pour l'émetteur du message est recommandé, si ce n'est obligatoire..")
	public void setTransferringAgencyArchiveIdentifier(
			ArchivesIDType transferringAgencyArchiveIdentifier) {
		TransferringAgencyArchiveIdentifier = transferringAgencyArchiveIdentifier;
	}
	
	/**
	 * Element
	 * @return Description du contenu de l'archive.
	 */
	@SEDA(Position=9,Type=SEDAType.ELEMENT,PropertyTerm="ContentDescription",Cardinality="1..1",FormName="Description du contenu",FormDescription="Description du contenu de l'archive.")
	public ContentDescriptionType getContentDescription() {
		return ContentDescription;
	}
	
	/**
	 * Element
	 * @param Description du contenu de l'archive.
	 */
	@SEDA(Position=9,Type=SEDAType.ELEMENT,PropertyTerm="ContentDescription",Cardinality="1..1",FormName="Description du contenu",FormDescription="Description du contenu de l'archive.")
	public void setContentDescription(ContentDescriptionType contentDescription) {
		ContentDescription = contentDescription;
	}
	
	/**
	 * Element
	 * @return
	 */
	@SEDA(Position=10,Type=SEDAType.ELEMENT,PropertyTerm="Appraisal",Cardinality="0..1",FormName="Règle de sort final",FormDescription="Règle de sort final")
	public AppraisalRulesType getAppraisal() {
		return Appraisal;
	}
	
	/**
	 * Element
	 * @param appraisal
	 */
	@SEDA(Position=10,Type=SEDAType.ELEMENT,PropertyTerm="Appraisal",Cardinality="0..1",FormName="Règle de sort final",FormDescription="Règle de sort final")
	public void setAppraisal(AppraisalRulesType appraisal) {
		Appraisal = appraisal;
	}
	
	/**
	 * Element
	 * @return
	 */
	@SEDA(Position=11,Type=SEDAType.ELEMENT,PropertyTerm="AccessRestriction",Cardinality="0..1",FormName="Règle de restriction d'accès",FormDescription="Règle de restriction d'accès")
	public AccessRestrictionRulesType getAccessRestriction() {
		return AccessRestriction;
	}
	
	/**
	 * Element
	 * @param accessRestriction
	 */
	@SEDA(Position=11,Type=SEDAType.ELEMENT,PropertyTerm="AccessRestriction",Cardinality="0..1",FormName="Règle de restriction d'accès",FormDescription="Règle de restriction d'accès")
	public void setAccessRestriction(AccessRestrictionRulesType accessRestriction) {
		AccessRestriction = accessRestriction;
	}
	
	/**
	 * Element
	 * @return
	 */
	@SEDA(Position=12,Type=SEDAType.ELEMENT,PropertyTerm="Document",Cardinality="0..n",FormName="Document",FormDescription="Document")
	public List<DocumentType> getDocument() {
		return Document;
	}
	
	/**
	 * Element
	 * @param document
	 */
	@SEDA(Position=12,Type=SEDAType.ELEMENT,PropertyTerm="Document",Cardinality="0..n",FormName="Document",FormDescription="Document")
	public void setDocument(List<DocumentType> document) {
		Document = document;
	}
	

	
	/**
	 * Element
	 * @param document
	 */
	@SEDA(Position=12,Type=SEDAType.ELEMENT,PropertyTerm="Document",Cardinality="0..n",FormName="Document",FormDescription="Document")
	public void addDocument(DocumentType document) {
		if(Document == null)
			Document = new ArrayList<DocumentType>();
		
		Document.add(document);
	}
	
	
	/**
	 * Element
	 * @return
	 */
	@SEDA(Position=13,Type=SEDAType.ELEMENT,PropertyTerm="Contains",Cardinality="0..n",FormName="Unité documentaire",FormDescription="Unité documentaire")
	public List<ArchiveObjectType> getContains() {
		return Contains;
	}
	
	/**
	 * Element
	 * @param contains
	 */
	@SEDA(Position=13,Type=SEDAType.ELEMENT,PropertyTerm="Contains",Cardinality="0..n",FormName="Unité documentaire",FormDescription="Unité documentaire")
	public void setContains(List<ArchiveObjectType> contains) {
		Contains = contains;
	}
	
	/**
	 * Element
	 * @param contains
	 */
	@SEDA(Position=13,Type=SEDAType.ELEMENT,PropertyTerm="Contains",Cardinality="0..n",FormName="Unité documentaire",FormDescription="Unité documentaire")
	public void addContains(ArchiveObjectType contains) {
		if(Contains == null)
			Contains = new ArrayList<ArchiveObjectType>();
		Contains.add(contains);
	}
}
