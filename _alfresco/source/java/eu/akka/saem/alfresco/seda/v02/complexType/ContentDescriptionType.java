

package eu.akka.saem.alfresco.seda.v02.complexType;

import java.util.ArrayList;
import java.util.List;

import eu.akka.saem.alfresco.seda.annotations.SEDA;
import eu.akka.saem.alfresco.seda.annotations.SEDAType;
import eu.akka.saem.alfresco.seda.form.FieldType;

/**
 * Informations sur le contenu de l'archive ou l'objet.
 * @author benjamin.catinot
 *
 */
public class ContentDescriptionType {
	
	private String CustodialHistory;
	private String Description;
	private List<ArchivesIDType> FilePlanPosition;
	private List<String> Format;
	private List<CodeLanguageType> Language;
	private DateType LatestDate;
	private DateType OldestDate;
	private String OtherDescriptiveData;
	private List<ArchivesIDType> RelatedObjectReference;
	private List<MeasureType> Size;
	private List<OrganizationType> OriginatingAgency;
	private OrganizationType Repository;
	private List<KeywordType> ContentDescriptive;
	private List<AccessRestrictionRulesType> AccessRestriction;
	private List<OtherMetadataType> OtherMetadata;
	private String Id;
	
	public ContentDescriptionType(){
		
	}
	
	/**
	 * 
	 * @return Enumère les changements successifs de propriété, de responsabilité et de conservation de l'objet avant son entrée dans le lieu de conservation. On peut indiquer notamment comment s'est effectué le passage de l'application d'origine au fichier archivable.
	 */
	@SEDA(Position=1,Type=SEDAType.ELEMENT,PropertyTerm="CustodialHistory",Cardinality="0..1",FormType=FieldType.TEXT,FormName="Historique de conservation",FormDescription="Enumère les changements successifs de propriété, de responsabilité et de conservation de l'objet avant son entrée dans le lieu de conservation. On peut indiquer notamment comment s'est effectué le passage de l'application d'origine au fichier archivable.")
	public String getCustodialHistory() {
		return CustodialHistory;
	}
	
	/**
	 * 
	 * @param Enumère les changements successifs de propriété, de responsabilité et de conservation de l'objet avant son entrée dans le lieu de conservation. On peut indiquer notamment comment s'est effectué le passage de l'application d'origine au fichier archivable.
	 */
	@SEDA(Position=1,Type=SEDAType.ELEMENT,PropertyTerm="CustodialHistory",Cardinality="0..1",FormType=FieldType.TEXT,FormName="Historique de conservation",FormDescription="Enumère les changements successifs de propriété, de responsabilité et de conservation de l'objet avant son entrée dans le lieu de conservation. On peut indiquer notamment comment s'est effectué le passage de l'application d'origine au fichier archivable.")
	public void setCustodialHistory(String custodialHistory) {
		CustodialHistory = custodialHistory;
	}
	
	/**
	 * 
	 * @return Permet de donner des précisions sur le contenu de l'objet. Il permet aussi de donner des précisions réservées aux professionnels et auxquelles le public ne doit pas avoir accès.
	 */
	@SEDA(Position=2,Type=SEDAType.ELEMENT,PropertyTerm="Description",Cardinality="0..1",FormType=FieldType.TEXT,FormName="Description",FormDescription="Permet de donner des précisions sur le contenu de l'objet. Il permet aussi de donner des précisions réservées aux professionnels et auxquelles le public ne doit pas avoir accès.")
	public String getDescription() {
		return Description;
	}
	
	/**
	 * 
	 * @param Permet de donner des précisions sur le contenu de l'objet. Il permet aussi de donner des précisions réservées aux professionnels et auxquelles le public ne doit pas avoir accès.
	 */
	@SEDA(Position=2,Type=SEDAType.ELEMENT,PropertyTerm="Description",Cardinality="0..1",FormType=FieldType.TEXT,FormName="Description",FormDescription="Permet de donner des précisions sur le contenu de l'objet. Il permet aussi de donner des précisions réservées aux professionnels et auxquelles le public ne doit pas avoir accès.")
	public void setDescription(String description) {
		Description = description;
	}
	
	/**
	 * 
	 * @return Classement de l'objet transféré dans le (ou les) plan(s) de classement du (ou des) producteur.
	 */
	@SEDA(Position=3,Type=SEDAType.ELEMENT,PropertyTerm="FilePlanPosition",Cardinality="0..n",FormName="FilePlanPosition",FormDescription="Classement de l'objet transféré dans le (ou les) plan(s) de classement du (ou des) producteur.")
	public List<ArchivesIDType> getFilePlanPosition() {
		return FilePlanPosition;
	}
	
	/**
	 * 
	 * @param Classement de l'objet transféré dans le (ou les) plan(s) de classement du (ou des) producteur.
	 */
	@SEDA(Position=3,Type=SEDAType.ELEMENT,PropertyTerm="FilePlanPosition",Cardinality="0..n",FormName="FilePlanPosition",FormDescription="Classement de l'objet transféré dans le (ou les) plan(s) de classement du (ou des) producteur.")
	public void setFilePlanPosition(List<ArchivesIDType> filePlanPosition) {
		FilePlanPosition = filePlanPosition;
	}
	
	/**
	 * 
	 * @param Classement de l'objet transféré dans le (ou les) plan(s) de classement du (ou des) producteur.
	 */
	@SEDA(Position=3,Type=SEDAType.ELEMENT,PropertyTerm="FilePlanPosition",Cardinality="0..n",FormName="FilePlanPosition",FormDescription="Classement de l'objet transféré dans le (ou les) plan(s) de classement du (ou des) producteur.")
	public void addFilePlanPosition(ArchivesIDType filePlanPosition) {
		if(FilePlanPosition == null)
			FilePlanPosition = new ArrayList<ArchivesIDType>();
		
		FilePlanPosition.add(filePlanPosition);
	}
	
	/**
	 * 
	 * @return Permet d'indiquer d'autres formats respectés par l'objet que ceux qui sont mentionnés dans les éléments BinaryObject (par exemple: le fichier pdf contient du texte avec des balises XML).
	 */
	@SEDA(Position=4,Type=SEDAType.ELEMENT,PropertyTerm="Format",Cardinality="0..n",FormName="Format",FormDescription="Permet d'indiquer d'autres formats respectés par l'objet que ceux qui sont mentionnés dans les éléments BinaryObject (par exemple: le fichier pdf contient du texte avec des balises XML).")
	public List<String> getFormat() {
		return Format;
	}
	
	/**
	 * 
	 * @param Permet d'indiquer d'autres formats respectés par l'objet que ceux qui sont mentionnés dans les éléments BinaryObject (par exemple: le fichier pdf contient du texte avec des balises XML).
	 */
	@SEDA(Position=4,Type=SEDAType.ELEMENT,PropertyTerm="Format",Cardinality="0..n",FormName="Format",FormDescription="Permet d'indiquer d'autres formats respectés par l'objet que ceux qui sont mentionnés dans les éléments BinaryObject (par exemple: le fichier pdf contient du texte avec des balises XML).")
	public void setFormat(List<String> format) {
		Format = format;
	}
	
	/**
	 * 
	 * @param Permet d'indiquer d'autres formats respectés par l'objet que ceux qui sont mentionnés dans les éléments BinaryObject (par exemple: le fichier pdf contient du texte avec des balises XML).
	 */
	@SEDA(Position=4,Type=SEDAType.ELEMENT,PropertyTerm="Format",Cardinality="0..n",FormName="Format",FormDescription="Permet d'indiquer d'autres formats respectés par l'objet que ceux qui sont mentionnés dans les éléments BinaryObject (par exemple: le fichier pdf contient du texte avec des balises XML).")
	public void addFormat(String format) {
		if(Format == null)
			Format = new ArrayList<String>();
		
		Format.add(format);
	}
	
	/**
	 * 
	 * @return Langue du contenu de l'objet.
	 */
	@SEDA(Position=5,Type=SEDAType.ELEMENT,PropertyTerm="Language",Cardinality="1..n",FormName="Langue du contenu",FormDescription="Langue du contenu")
	public List<CodeLanguageType> getLanguage() {
		return Language;
	}
	
	/**
	 * 
	 * @param Langue du contenu de l'objet.
	 */
	@SEDA(Position=0,Type=SEDAType.ELEMENT,PropertyTerm="Language",Cardinality="1..n",FormName="Langue du contenu",FormDescription="Langue du contenu")
	public void setLanguage(List<CodeLanguageType> language) {
		Language = language;
	}
	
	/**
	 * 
	 * @param Langue du contenu de l'objet.
	 */
	@SEDA(Position=5,Type=SEDAType.ELEMENT,PropertyTerm="Language",Cardinality="1..n",FormName="Langue du contenu",FormDescription="Langue du contenu")
	public void addLanguage(CodeLanguageType language) {
		if(Language == null)
			Language = new ArrayList<CodeLanguageType>();
		
		Language.add(language);
	}
	
	/**
	 * 
	 * @return Date de fin du contenu.
	 */
	@SEDA(Position=6,Type=SEDAType.ELEMENT,PropertyTerm="LatestDate",Cardinality="0..1",FormName="Date de fin",FormDescription="Date de fin du contenu.")
	public DateType getLatestDate() {
		return LatestDate;
	}
	
	/**
	 * 
	 * @param Date de fin du contenu.
	 */
	@SEDA(Position=6,Type=SEDAType.ELEMENT,PropertyTerm="LatestDate",Cardinality="0..1",FormName="Date de fin",FormDescription="Date de fin du contenu.")
	public void setLatestDate(DateType latestDate) {
		LatestDate = latestDate;
	}
	
	/**
	 * 
	 * @return Date et heure de début du contenu d'information.
	 */
	@SEDA(Position=7,Type=SEDAType.ELEMENT,PropertyTerm="OldestDate",Cardinality="0..1",FormName="Date de début",FormDescription="Date et heure de début du contenu d'information.")
	public DateType getOldestDate() {
		return OldestDate;
	}
	
	/**
	 * 
	 * @param Date et heure de début du contenu d'information.
	 */
	@SEDA(Position=7,Type=SEDAType.ELEMENT,PropertyTerm="OldestDate",Cardinality="0..1",FormName="Date de début",FormDescription="Date et heure de début du contenu d'information.")
	public void setOldestDate(DateType oldestDate) {
		OldestDate = oldestDate;
	}
	
	/**
	 * 
	 * @return Autres informations sur l'objet.
	 */
	@SEDA(Position=8,Type=SEDAType.ELEMENT,PropertyTerm="OtherDescriptiveData",Cardinality="0..1",FormType=FieldType.TEXT,FormName="Autres informations",FormDescription="Autres informations sur l'objet.")
	public String getOtherDescriptiveData() {
		return OtherDescriptiveData;
	}
	
	/**
	 * 
	 * @param Autres informations sur l'objet.
	 */
	@SEDA(Position=8,Type=SEDAType.ELEMENT,PropertyTerm="OtherDescriptiveData",Cardinality="0..1",FormType=FieldType.TEXT,FormName="Autres informations",FormDescription="Autres informations sur l'objet.")
	public void setOtherDescriptiveData(String otherDescriptiveData) {
		OtherDescriptiveData = otherDescriptiveData;
	}
	
	/**
	 * 
	 * @return Permet d'indiquer la référence d'un autre objet et la nature du lien avec ce dernier.
	 */
	@SEDA(Position=9,Type=SEDAType.ELEMENT,PropertyTerm="RelatedObjectReference",Cardinality="0..n",FormName="Référence complémentaire",FormDescription="Permet d'indiquer la référence d'un autre objet et la nature du lien avec ce dernier.")
	public List<ArchivesIDType> getRelatedObjectReference() {
		return RelatedObjectReference;
	}
	
	/**
	 * 
	 * @param Permet d'indiquer la référence d'un autre objet et la nature du lien avec ce dernier.
	 */
	@SEDA(Position=9,Type=SEDAType.ELEMENT,PropertyTerm="RelatedObjectReference",Cardinality="0..n",FormName="Référence complémentaire",FormDescription="Permet d'indiquer la référence d'un autre objet et la nature du lien avec ce dernier.")
	public void setRelatedObjectReference(List<ArchivesIDType> relatedObjectReference) {
		RelatedObjectReference = relatedObjectReference;
	}
	
	/**
	 * 
	 * @param Permet d'indiquer la référence d'un autre objet et la nature du lien avec ce dernier.
	 */
	@SEDA(Position=9,Type=SEDAType.ELEMENT,PropertyTerm="RelatedObjectReference",Cardinality="0..n",FormName="Référence complémentaire",FormDescription="Permet d'indiquer la référence d'un autre objet et la nature du lien avec ce dernier.")
	public void addRelatedObjectReference(ArchivesIDType relatedObjectReference) {
		if(RelatedObjectReference == null)
			RelatedObjectReference = new ArrayList<ArchivesIDType>();
		
		RelatedObjectReference.add(relatedObjectReference);
	}
	
	/**
	 * 
	 * @return Taille de l'objet en octets, nombre d'entregistrements...
	 */
	@SEDA(Position=10,Type=SEDAType.ELEMENT,PropertyTerm="Size",Cardinality="0..n",FormName="Taille",FormDescription="Taille de l'objet en octets, nombre d'entregistrements...")
	public List<MeasureType> getSize() {
		return Size;
	}
	
	/**
	 * 
	 * @param Taille de l'objet en octets, nombre d'entregistrements...
	 */
	@SEDA(Position=10,Type=SEDAType.ELEMENT,PropertyTerm="Size",Cardinality="0..n",FormName="Taille",FormDescription="Taille de l'objet en octets, nombre d'entregistrements...")
	public void setSize(List<MeasureType> size) {
		Size = size;
	}
	

	/**
	 * 
	 * @param Taille de l'objet en octets, nombre d'entregistrements...
	 */
	@SEDA(Position=10,Type=SEDAType.ELEMENT,PropertyTerm="Size",Cardinality="0..n",FormName="Taille",FormDescription="Taille de l'objet en octets, nombre d'entregistrements...")
	public void addSize(MeasureType size) {
		if(Size == null)
			Size = new ArrayList<MeasureType>();
		
		Size.add(size);
	}
	
	/**
	 * 
	 * @return
	 */
	@SEDA(Position=11,Type=SEDAType.ELEMENT,PropertyTerm="OriginatingAgency",Cardinality="0..n",FormName="Service producteur",FormDescription="Service producteur")
	public List<OrganizationType> getOriginatingAgency() {
		return OriginatingAgency;
	}
	
	/**
	 * 
	 * @param originatingAgency
	 */
	@SEDA(Position=11,Type=SEDAType.ELEMENT,PropertyTerm="OriginatingAgency",Cardinality="0..n",FormName="Service producteur",FormDescription="Service producteur")
	public void setOriginatingAgency(List<OrganizationType> originatingAgency) {
		OriginatingAgency = originatingAgency;
	}
	
	/**
	 * 
	 * @param originatingAgency
	 */
	@SEDA(Position=11,Type=SEDAType.ELEMENT,PropertyTerm="OriginatingAgency",Cardinality="0..n",FormName="Service producteur",FormDescription="Service producteur")
	public void addOriginatingAgency(OrganizationType originatingAgency) {
		if(OriginatingAgency == null)
			OriginatingAgency = new ArrayList<OrganizationType>();
		
		OriginatingAgency.add(originatingAgency);
	}
	
	/**
	 * 
	 * @return
	 */
	@SEDA(Position=12,Type=SEDAType.ELEMENT,PropertyTerm="Repository",Cardinality="0..1",FormName="Service d'archives",FormDescription="Service d'archives")
	public OrganizationType getRepository() {
		return Repository;
	}
	
	/**
	 * 
	 * @param repository
	 */
	@SEDA(Position=12,Type=SEDAType.ELEMENT,PropertyTerm="Repository",Cardinality="0..1",FormName="Service d'archives",FormDescription="Service d'archives")
	public void setRepository(OrganizationType repository) {
		Repository = repository;
	}
	
	/**
	 * 
	 * @return
	 */
	@SEDA(Position=13,Type=SEDAType.ELEMENT,PropertyTerm="ContentDescriptive",Cardinality="0..n",FormName="Mot-clé",FormDescription="Mot-clé")
	public List<KeywordType> getContentDescriptive() {
		return ContentDescriptive;
	}
	
	/**
	 * 
	 * @param contentDescriptive
	 */
	@SEDA(Position=13,Type=SEDAType.ELEMENT,PropertyTerm="ContentDescriptive",Cardinality="0..n",FormName="Mot-clé",FormDescription="Mot-clé")
	public void setContentDescriptive(List<KeywordType> contentDescriptive) {
		ContentDescriptive = contentDescriptive;
	}
	
	/**
	 * 
	 * @param contentDescriptive
	 */
	@SEDA(Position=13,Type=SEDAType.ELEMENT,PropertyTerm="ContentDescriptive",Cardinality="0..n",FormName="Mot-clé",FormDescription="Mot-clé")
	public void addContentDescriptive(KeywordType contentDescriptive) {
		if(ContentDescriptive == null)
			ContentDescriptive = new ArrayList<KeywordType>();
		
		ContentDescriptive.add(contentDescriptive);
	}
	
	/**
	 * 
	 * @return
	 */
	@SEDA(Position=14,Type=SEDAType.ELEMENT,PropertyTerm="AccessRestriction",Cardinality="0..n",FormName="Régle de restriction d'accès",FormDescription="Régle de restriction d'accès")
	public List<AccessRestrictionRulesType> getAccessRestriction() {
		return AccessRestriction;
	}
	
	/**
	 * 
	 * @param accessRestriction
	 */
	@SEDA(Position=14,Type=SEDAType.ELEMENT,PropertyTerm="AccessRestriction",Cardinality="0..n",FormName="Régle de restriction d'accès",FormDescription="Régle de restriction d'accès")
	public void setAccessRestriction(List<AccessRestrictionRulesType> accessRestriction) {
		AccessRestriction = accessRestriction;
	}
	
	/**
	 * 
	 * @param accessRestriction
	 */
	@SEDA(Position=14,Type=SEDAType.ELEMENT,PropertyTerm="AccessRestriction",Cardinality="0..n",FormName="Régle de restriction d'accès",FormDescription="Régle de restriction d'accès")
	public void addAccessRestriction(AccessRestrictionRulesType accessRestriction) {
		if(AccessRestriction == null)
			AccessRestriction = new ArrayList<AccessRestrictionRulesType>();
		
		AccessRestriction.add(accessRestriction);
	}
	
	/**
	 * 
	 * @return
	 */
	@SEDA(Position=15,Type=SEDAType.ELEMENT,PropertyTerm="OtherMetadata",Cardinality="0..n",FormName="Autre métadonnées",FormDescription="Autre métadonnées")
	public List<OtherMetadataType> getOtherMetadata() {
		return OtherMetadata;
	}
	
	/**
	 * 
	 * @param otherMetadata
	 */
	@SEDA(Position=15,Type=SEDAType.ELEMENT,PropertyTerm="OtherMetadata",Cardinality="0..n",FormName="Autre métadonnées",FormDescription="Autre métadonnées")
	public void setOtherMetadata(List<OtherMetadataType> otherMetadata) {
		OtherMetadata = otherMetadata;
	}
	
	/**
	 * 
	 * @param otherMetadata
	 */
	@SEDA(Position=15,Type=SEDAType.ELEMENT,PropertyTerm="OtherMetadata",Cardinality="0..n",FormName="Autre métadonnées",FormDescription="Autre métadonnées")
	public void addOtherMetadata(OtherMetadataType otherMetadata) {
		if(OtherMetadata == null)
			OtherMetadata = new ArrayList<OtherMetadataType>();
		
		OtherMetadata.add(otherMetadata);
	}

	@SEDA(Type=SEDAType.ATTRIBUTE,PropertyTerm="Id",Cardinality="0..1")
	public String getId() {
		return Id;
	}

	@SEDA(Type=SEDAType.ATTRIBUTE,PropertyTerm="Id",Cardinality="0..1")
	public void setId(String id) {
		Id = id;
	}
	
}
