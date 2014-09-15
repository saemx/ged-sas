package eu.akka.saem.alfresco.seda.v02.complexType;


import java.util.ArrayList;
import java.util.List;
import eu.akka.saem.alfresco.seda.annotations.SEDA;
import eu.akka.saem.alfresco.seda.annotations.SEDAType;
import eu.akka.saem.alfresco.seda.form.FieldType;

/**
 * Ensemble de données relatives à une pièce physique ou électronique qui fournit des informations ou des preuves.
 * @author benjamin.catinot
 *
 */
public class DocumentType {
	private List<ArchivesBinaryObjectType> Attachment;
	private List<IndicatorType> Control;
	private IndicatorType Copy;
	private DateTimeType Creation;
	private String Description;
	private ArchivesIDType Identification;
	private DateTimeType Issue;
	private List<ArchivesIDType> ItemIdentifier;
	private String Purpose;
	private DateTimeType Receipt;
	private DateTimeType Response;
	private List<ArchivesCodeType> Status;
	private DateTimeType Submission;
	private List<CodeDocumentType> Type;
	private List<OtherMetadataType> OtherMetadata;
	
	public DocumentType(){
	}
	
	/**
	 * Element
	 * @return Pièce jointe ou annexée. Dans une période transitoire, le même format peut aussi servir à indiquer une pièce "papier" et sa localisation.
	 */
	@SEDA(Position=0,Type=SEDAType.ELEMENT,PropertyTerm="Attachment",Cardinality="1..n",FormName="Pièce jointe",FormDescription="Pièce jointe ou annexée.")
	public List<ArchivesBinaryObjectType> getAttachment() {
		return Attachment;
	}
	
	/**
	 * Element
	 * @param Pièce jointe ou annexée. Dans une période transitoire, le même format peut aussi servir à indiquer une pièce "papier" et sa localisation.
	 */
	@SEDA(Position=0,Type=SEDAType.ELEMENT,PropertyTerm="Attachment",Cardinality="1..n",FormName="Pièce jointe",FormDescription="Pièce jointe ou annexée.")
	public void setAttachment(List<ArchivesBinaryObjectType> attachment) {
		Attachment = attachment;
	}
	
	@SEDA(Position=0,Type=SEDAType.ELEMENT,PropertyTerm="Attachment",Cardinality="1..n",FormName="Pièce jointe",FormDescription="Pièce jointe ou annexée.")
	public void addAttachment(ArchivesBinaryObjectType attachment) {
		if(Attachment == null)
			Attachment = new ArrayList<ArchivesBinaryObjectType>();
		
		Attachment.add(attachment);
	}
	
	/**
	 * Element
	 * @return Indique si des exigences de contrôle portent sur le document.
	 */
	@SEDA(Position=2,Type=SEDAType.ELEMENT,PropertyTerm="Control",Cardinality="0..n",FormName="Présence d'exigences de contrôle",FormDescription="Indique si des exigences de contrôle portent sur le document.")
	public List<IndicatorType> getControl() {
		return Control;
	}
	
	/**
	 * Element
	 * @param Indique si des exigences de contrôle portent sur le document.
	 */
	@SEDA(Position=2,Type=SEDAType.ELEMENT,PropertyTerm="Control",Cardinality="0..n",FormName="Présence d'exigences de contrôle",FormDescription="Indique si des exigences de contrôle portent sur le document.")
	public void setControl(List<IndicatorType> control) {
		Control = control;
	}
	
	@SEDA(Position=2,Type=SEDAType.ELEMENT,PropertyTerm="Control",Cardinality="0..n",FormName="Présence d'exigences de contrôle",FormDescription="Indique si des exigences de contrôle portent sur le document.")
	public void addControl(IndicatorType control) {
		if(Control == null)
			Control = new ArrayList<IndicatorType>();
		
		Control.add(control);
	}
	
	/**
	 * Element
	 * @return Indique s'il s'agit d'un original ou d'une copie.
	 */
	@SEDA(Position=3,Type=SEDAType.ELEMENT,PropertyTerm="Copy",Cardinality="0..1",FormName="Exemplaire",FormDescription="Indique s'il s'agit d'un original ou d'une copie.")
	public IndicatorType getCopy() {
		return Copy;
	}
	
	/**
	 * Element
	 * @param Indique s'il s'agit d'un original ou d'une copie.
	 */
	@SEDA(Position=3,Type=SEDAType.ELEMENT,PropertyTerm="Copy",Cardinality="0..1",FormName="Exemplaire",FormDescription="Indique s'il s'agit d'un original ou d'une copie.")
	public void setCopy(IndicatorType copy) {
		Copy = copy;
	}
	
	/**
	 * Element
	 * @return Date de création.
	 */
	@SEDA(Position=4,Type=SEDAType.ELEMENT,PropertyTerm="Creation",Cardinality="0..1",FormName="Date de création",FormDescription="Date de création")
	public DateTimeType getCreation() {
		return Creation;
	}
	
	/**
	 * Element
	 * @param Date de création.
	 */
	@SEDA(Position=4,Type=SEDAType.ELEMENT,PropertyTerm="Creation",Cardinality="0..1",FormName="Date de création",FormDescription="Date de création")
	public void setCreation(DateTimeType creation) {
		Creation = creation;
	}
	
	/**
	 * Element
	 * @return Description du document.
	 */
	@SEDA(Position=5,Type=SEDAType.ELEMENT,PropertyTerm="Description",Cardinality="0..1", FormType=FieldType.TEXT,FormName="Description",FormDescription="Description du document.")
	public String getDescription() {
		return Description;
	}
	
	/**
	 * Element
	 * @param Description du document.
	 */
	@SEDA(Position=5,Type=SEDAType.ELEMENT,PropertyTerm="Description",Cardinality="0..1", FormType=FieldType.TEXT,FormName="Description",FormDescription="Description du document.")
	public void setDescription(String description) {
		Description = description;
	}
	
	/**
	 * Element
	 * @return Identifiant unique du document.
	 */
	@SEDA(Position=6,Type=SEDAType.ELEMENT,PropertyTerm="Identification",Cardinality="0..1",FormName="Identifiant",FormDescription="Identifiant unique du document.")
	public ArchivesIDType getIdentification() {
		return Identification;
	}
	
	/**
	 * Element
	 * @param Identifiant unique du document.
	 */
	@SEDA(Position=6,Type=SEDAType.ELEMENT,PropertyTerm="Identification",Cardinality="0..1",FormName="Identifiant",FormDescription="Identifiant unique du document.")
	public void setIdentification(ArchivesIDType identification) {
		Identification = identification;
	}
	
	/**
	 * Element
	 * @return Date d'émission du document.
	 */
	@SEDA(Position=7,Type=SEDAType.ELEMENT,PropertyTerm="Issue",Cardinality="0..1",FormName="Date d'émission",FormDescription="Date d'émission du document.")
	public DateTimeType getIssue() {
		return Issue;
	}
	
	/**
	 * Element
	 * @param Date d'émission du document.
	 */
	@SEDA(Position=7,Type=SEDAType.ELEMENT,PropertyTerm="Issue",Cardinality="0..1",FormName="Date d'émission",FormDescription="Date d'émission du document.")
	public void setIssue(DateTimeType issue) {
		Issue = issue;
	}
	
	/**
	 * Element
	 * @return Identifiant unique d'un élément particulier dans le document.
	 */
	@SEDA(Position=8,Type=SEDAType.ELEMENT,PropertyTerm="ItemIdentifier",Cardinality="0..n",FormName="Identifiant unique d'un élément particulier dans le document",FormDescription="Identifiant unique d'un élément particulier dans le document.")
	public List<ArchivesIDType> getItemIdentifier() {
		return ItemIdentifier;
	}
	
	/**
	 * Element
	 * @param Identifiant unique d'un élément particulier dans le document.
	 */
	@SEDA(Position=8,Type=SEDAType.ELEMENT,PropertyTerm="ItemIdentifier",Cardinality="0..n",FormName="Identifiant unique d'un élément particulier dans le document",FormDescription="Identifiant unique d'un élément particulier dans le document.")
	public void setItemIdentifier(List<ArchivesIDType> itemIdentifier) {
		ItemIdentifier = itemIdentifier;
	}
	
	@SEDA(Position=8,Type=SEDAType.ELEMENT,PropertyTerm="ItemIdentifier",Cardinality="0..n",FormName="Identifiant unique d'un élément particulier dans le document",FormDescription="Identifiant unique d'un élément particulier dans le document.")
	public void addItemIdentifier(ArchivesIDType itemIdentifier) {
		if(ItemIdentifier == null)
			ItemIdentifier = new ArrayList<ArchivesIDType>();
		
		ItemIdentifier.add(itemIdentifier);
	}
	
	/**
	 * Element
	 * @return Objet ou objectif du document.
	 */
	@SEDA(Position=9,Type=SEDAType.ELEMENT,PropertyTerm="Purpose",Cardinality="0..1", FormType=FieldType.TEXT,FormName="Objet",FormDescription="Objet ou objectif du document.")
	public String getPurpose() {
		return Purpose;
	}
	
	/**
	 * Element
	 * @param Objet ou objectif du document.
	 */
	@SEDA(Position=9,Type=SEDAType.ELEMENT,PropertyTerm="Purpose",Cardinality="0..1", FormType=FieldType.TEXT,FormName="Objet",FormDescription="Objet ou objectif du document.")
	public void setPurpose(String purpose) {
		Purpose = purpose;
	}
	
	/**
	 * Element
	 * @return Date de réception.
	 */
	@SEDA(Position=10,Type=SEDAType.ELEMENT,PropertyTerm="Receipt",Cardinality="0..1",FormName="Date de réception",FormDescription="Date de réception.")
	public DateTimeType getReceipt() {
		return Receipt;
	}
	
	/**
	 * Element
	 * @param Date de réception.
	 */
	@SEDA(Position=10,Type=SEDAType.ELEMENT,PropertyTerm="Receipt",Cardinality="0..1",FormName="Date de réception",FormDescription="Date de réception.")
	public void setReceipt(DateTimeType receipt) {
		Receipt = receipt;
	}
	
	/**
	 * Element
	 * @return Date de réponse.
	 */
	@SEDA(Position=11,Type=SEDAType.ELEMENT,PropertyTerm="Response",Cardinality="0..1",FormName="Date de réponse",FormDescription="Date de réponse.")
	public DateTimeType getResponse() {
		return Response;
	}
	
	/**
	 * Element
	 * @param Date de réponse.
	 */
	@SEDA(Position=11,Type=SEDAType.ELEMENT,PropertyTerm="Response",Cardinality="0..1",FormName="Date de réponse",FormDescription="Date de réponse.")
	public void setResponse(DateTimeType response) {
		Response = response;
	}
	
	/**
	 * Element 
	 * @return Etat du document (par rapport avec son cycle de vie). Permet par exemple d'indiquer si la signature d'un document a été vérifiée avant transfert aux archives.
	 */
	@SEDA(Position=12,Type=SEDAType.ELEMENT,PropertyTerm="Status",Cardinality="0..n",FormName="Etat",FormDescription="Etat du document (par rapport avec son cycle de vie).")
	public List<ArchivesCodeType> getStatus() {
		return Status;
	}
	
	/**
	 * Element
	 * @param Etat du document (par rapport avec son cycle de vie). Permet par exemple d'indiquer si la signature d'un document a été vérifiée avant transfert aux archives.
	 */
	@SEDA(Position=12,Type=SEDAType.ELEMENT,PropertyTerm="Status",Cardinality="0..n",FormName="Etat",FormDescription="Etat du document (par rapport avec son cycle de vie).")
	public void setStatus(List<ArchivesCodeType> status) {
		Status = status;
	}
	
	/**
	 * Element
	 * @param Etat du document (par rapport avec son cycle de vie). Permet par exemple d'indiquer si la signature d'un document a été vérifiée avant transfert aux archives.
	 */
	@SEDA(Position=12,Type=SEDAType.ELEMENT,PropertyTerm="Status",Cardinality="0..n",FormName="Etat",FormDescription="Etat du document (par rapport avec son cycle de vie).")
	public void addStatus(ArchivesCodeType status) {
		if(Status == null)
			Status = new ArrayList<ArchivesCodeType>();
		
		Status.add(status);
	}
	
	/**
	 * Element
	 * @return Date de soumission du document par un émetteur à un destinataire.
	 */
	@SEDA(Position=13,Type=SEDAType.ELEMENT,PropertyTerm="Submission",Cardinality="0..1",FormName="Date de soumission",FormDescription="Date de soumission du document par un émetteur à un destinataire.")
	public DateTimeType getSubmission() {
		return Submission;
	}
	
	/**
	 * Element
	 * @param Date de soumission du document par un émetteur à un destinataire.
	 */
	@SEDA(Position=13,Type=SEDAType.ELEMENT,PropertyTerm="Submission",Cardinality="0..1",FormName="Date de soumission",FormDescription="Date de soumission du document par un émetteur à un destinataire.")
	public void setSubmission(DateTimeType submission) {
		Submission = submission;
	}
	
	/**
	 * Element
	 * @return Type de document, permet notamment de différencier un objet contenu et l'information de représentation ou de pérennisation de cet objet (OAIS), par exemple les données d'une base de données et le descriptif de sa structure.
	 */
	@SEDA(Position=14,Type=SEDAType.ELEMENT,PropertyTerm="Type",Cardinality="0..n",FormName="Type",FormDescription="")
	public List<CodeDocumentType> getType() {
		return Type;
	}
	
	/**
	 * Element
	 * @param Type de document, permet notamment de différencier un objet contenu et l'information de représentation ou de pérennisation de cet objet (OAIS), par exemple les données d'une base de données et le descriptif de sa structure.
	 */
	@SEDA(Position=14,Type=SEDAType.ELEMENT,PropertyTerm="Type",Cardinality="0..n",FormName="Type",FormDescription="Type de document")
	public void setType(List<CodeDocumentType> type) {
		Type = type;
	}
	
	/**
	 * Element
	 * @param Type de document, permet notamment de différencier un objet contenu et l'information de représentation ou de pérennisation de cet objet (OAIS), par exemple les données d'une base de données et le descriptif de sa structure.
	 */
	@SEDA(Position=14,Type=SEDAType.ELEMENT,PropertyTerm="Type",Cardinality="0..n",FormName="Type",FormDescription="Type de document")
	public void addType(CodeDocumentType type) {
		if(Type == null)
			Type = new ArrayList<CodeDocumentType>();
		
		Type.add(type);
	}
	
	/**
	 * Element
	 * @return Métadonnées métier (par exemple IPTC).
	 */
	@SEDA(Position=15,Type=SEDAType.ELEMENT,PropertyTerm="OtherMetadata",Cardinality="0..n",FormName="Métadonnées métier",FormDescription="Métadonnées métier")
	public List<OtherMetadataType> getOtherMetadata() {
		return OtherMetadata;
	}
	
	/**
	 * Element
	 * @param Métadonnées métier (par exemple IPTC).
	 */
	@SEDA(Position=15,Type=SEDAType.ELEMENT,PropertyTerm="OtherMetadata",Cardinality="0..n",FormName="Métadonnées métier",FormDescription="Métadonnées métier")
	public void setOtherMetadata(List<OtherMetadataType> otherMetadata) {
		OtherMetadata = otherMetadata;
	}
	
	/**
	 * Element
	 * @param Métadonnées métier (par exemple IPTC).
	 */
	@SEDA(Position=15,Type=SEDAType.ELEMENT,PropertyTerm="OtherMetadata",Cardinality="0..n",FormName="Métadonnées métier",FormDescription="Métadonnées métier")
	public void addOtherMetadata(OtherMetadataType otherMetadata) {
		if(OtherMetadata == null)
			OtherMetadata = new ArrayList<OtherMetadataType>();
		
		OtherMetadata.add(otherMetadata);
	}
	
}
