package eu.akka.saem.alfresco.seda.v02;


import java.util.*;
import eu.akka.saem.alfresco.seda.annotations.SEDA;
import eu.akka.saem.alfresco.seda.annotations.SEDAType;
import eu.akka.saem.alfresco.seda.form.FieldType;
import eu.akka.saem.alfresco.seda.v02.complexType.ArchiveType;
import eu.akka.saem.alfresco.seda.v02.complexType.ArchivesIDType;
import eu.akka.saem.alfresco.seda.v02.complexType.DateTimeType;
import eu.akka.saem.alfresco.seda.v02.complexType.HashCodeType;
import eu.akka.saem.alfresco.seda.v02.complexType.OrganizationType;
import eu.akka.saem.alfresco.seda.v02.complexType.SignatureType;

/**
 * Transfer d'archives
 * @author benjamin.catinot
 */
@SEDA(PropertyTerm="ArchiveTransfer")
public class ArchiveTransferType {

		@SuppressWarnings("unused")
		private static final long serialVersionUID = 8106741656939374933L;

		private String ID;
		private String xmlns = "fr:gouv:ae:archive:draft:standard_echange_v0.2";
		private String Comment;
		private DateTimeType Date;
		private ArchivesIDType RelatedTransferReference;
		private ArchivesIDType TransferIdentifier;
		private ArchivesIDType TransferRequestReplyIdentifier;
		private OrganizationType TransferringAgency;
		private OrganizationType ArchivalAgency;
		private List<HashCodeType> Integrity;
		private SignatureType NonRepudiation;
		private List<ArchiveType> Contains;
		
		@SEDA(Position=0,Type=SEDAType.ATTRIBUTE,PropertyTerm="xmlns",Cardinality="1..1")
		public String getXmlns() {
			return xmlns;
		}

		public ArchiveTransferType(){
			Integrity = new ArrayList<HashCodeType>();
			Contains = new ArrayList<ArchiveType>();			
		}
		
		/**
		 * Element
		 * @return Commentaires
		 */
		@SEDA(Position=1,Type=SEDAType.ELEMENT,PropertyTerm="Comment",Cardinality="0..1",FormName="Commentaire",FormDescription="Commentaires",FormType=FieldType.TEXTEAREA)
		public String getComment() {
			return Comment;
		}
		
		/**
		 * Element
		 * @param Commentaires
		 */
		@SEDA(Position=1,Type=SEDAType.ELEMENT,PropertyTerm="Comment",Cardinality="0..1",FormName="Commentaire",FormDescription="Commentaires",FormType=FieldType.TEXTEAREA)
		public void setComment(String comment) {
			Comment = comment;
		}
		
		/**
		 * Element
		 * @return Date du transfert
		 */
		@SEDA(Position=2,Type=SEDAType.ELEMENT,PropertyTerm="Date",Cardinality="1..1",FormName="Date",FormDescription="Date du transfert")
		public DateTimeType getDate() {
			return Date;
		}
		
		/**
		 * Element
		 * @param Date du transfert
		 */
		@SEDA(Position=2,Type=SEDAType.ELEMENT,PropertyTerm="Date",Cardinality="1..1",FormName="Date",FormDescription="Date du transfert")
		public void setDate(DateTimeType date) {
			Date = date;
		}
		
		/**
		 * Element
		 * @return Référence à un autre transfert
		 */
		@SEDA(Position=3,Type=SEDAType.ELEMENT,PropertyTerm="RelatedTransferReference",Cardinality="0..1",FormName="Référence à un autre transfert",FormDescription="Référence à un autre transfert")
		public ArchivesIDType getRelatedTransferReference() {
			return RelatedTransferReference;
		}
		
		/**
		 * Element
		 * @param Référence à un autre transfert
		 */
		@SEDA(Position=3,Type=SEDAType.ELEMENT,PropertyTerm="RelatedTransferReference",Cardinality="0..1",FormName="Référence à un autre transfert",FormDescription="Référence à un autre transfert")
		public void setRelatedTransferReference(ArchivesIDType relatedTransferReference) {
			RelatedTransferReference = relatedTransferReference;
		}
		
		/**
		 * Element
		 * @return Identifiant du transfert
		 */
		@SEDA(Position=4,Type=SEDAType.ELEMENT,PropertyTerm="TransferIdentifier",Cardinality="1..1",FormName="Identifiant du transfert",FormDescription="Identifiant du transfert")
		public ArchivesIDType getTransferIdentifier() {
			return TransferIdentifier;
		}
		
		/**
		 * Element
		 * @param Identifiant du transfert
		 */
		@SEDA(Position=4,Type=SEDAType.ELEMENT,PropertyTerm="TransferIdentifier",Cardinality="1..1",FormName="Identifiant du transfert",FormDescription="Identifiant du transfert")
		public void setTransferIdentifier(ArchivesIDType transferIdentifier) {
			TransferIdentifier = transferIdentifier;
		}
		
		/**
		 * Element
		 * @return Identifiant de la réponse à la demande de transfert (permet par exemple de rappeler l'accord donné par le service d'archives)
		 */
		@SEDA(Position=5,Type=SEDAType.ELEMENT,PropertyTerm="TransferRequestReplyIdentifier",Cardinality="0..1",FormName="Identifiant de la réponse à la demande de transfert",FormDescription="Identifiant de la réponse à la demande de transfert (permet par exemple de rappeler l'accord donné par le service d'archives)")
		public ArchivesIDType getTransferRequestReplyIdentifier() {
			return TransferRequestReplyIdentifier;
		}
		
		/**
		 * Element
		 * @param Identifiant de la réponse à la demande de transfert (permet par exemple de rappeler l'accord donné par le service d'archives)
		 */
		@SEDA(Position=5,Type=SEDAType.ELEMENT,PropertyTerm="TransferRequestReplyIdentifier",Cardinality="0..1",FormName="Identifiant de la réponse à la demande de transfert",FormDescription="Identifiant de la réponse à la demande de transfert (permet par exemple de rappeler l'accord donné par le service d'archives)")
		public void setTransferRequestReplyIdentifier(
				ArchivesIDType transferRequestReplyIdentifier) {
			TransferRequestReplyIdentifier = transferRequestReplyIdentifier;
		}
		
		/**
		 * Element
		 * @return Service versant
		 */
		@SEDA(Position=6,Type=SEDAType.ELEMENT,PropertyTerm="TransferringAgency",Cardinality="1..1",FormName="Service versant",FormDescription="")
		public OrganizationType getTransferringAgency() {
			return TransferringAgency;
		}
		
		/**
		 * Element
		 * @param Service versant
		 */
		@SEDA(Position=6,Type=SEDAType.ELEMENT,PropertyTerm="TransferringAgency",Cardinality="1..1",FormName="Service versant",FormDescription="")
		public void setTransferringAgency(OrganizationType transferringAgency) {
			TransferringAgency = transferringAgency;
		}
		
		
		/**
		 * Element
		 * @return Service d'archive
		 */
		@SEDA(Position=7,Type=SEDAType.ELEMENT,PropertyTerm="ArchivalAgency",Cardinality="1..1",FormName="Service d'archives",FormDescription="")
		public OrganizationType getArchivalAgency() {
			return ArchivalAgency;
		}
		
		/**
		 * Element
		 * @param Service d'archive
		 */
		@SEDA(Position=7,Type=SEDAType.ELEMENT,PropertyTerm="ArchivalAgency",Cardinality="1..1",FormName="Service d'archives",FormDescription="")
		public void setArchivalAgency(OrganizationType archivalAgency) {
			ArchivalAgency = archivalAgency;
		}
		

		@SEDA(Position=8,Type=SEDAType.ELEMENT,PropertyTerm="Integrity",Cardinality="0..n",FormName="Empreinte",FormDescription="")
		public List<HashCodeType> getIntegrity() {
			return Integrity;
		}
		
		@SEDA(Position=8,Type=SEDAType.ELEMENT,PropertyTerm="Integrity",Cardinality="0..n",FormName="Empreinte",FormDescription="")
		public void setIntegrity(List<HashCodeType> integrity) {
			Integrity = integrity;
		}
		
		@SEDA(Position=8,Type=SEDAType.ELEMENT,PropertyTerm="Integrity",Cardinality="0..n",FormName="Empreinte",FormDescription="")
		public void addIntegrity(HashCodeType integrity) {
			Integrity.add(integrity);
		}
		
		@SEDA(Position=10,Type=SEDAType.ELEMENT,PropertyTerm="NonRepudiation",Cardinality="0..1",FormName="Signature",FormDescription="")
		public SignatureType getNonRepudiation() {
			return NonRepudiation;
		}
		
		@SEDA(Position=10,Type=SEDAType.ELEMENT,PropertyTerm="NonRepudiation",Cardinality="0..1",FormName="Signature",FormDescription="")
		public void setNonRepudiation(SignatureType nonRepudiation) {
			NonRepudiation = nonRepudiation;
		}
		
		@SEDA(Position=11,Type=SEDAType.ELEMENT,PropertyTerm="Contains",Cardinality="1..n",FormName="Archives",FormDescription="")
		public List<ArchiveType> getContains() {
			return Contains;
		}
		
		@SEDA(Position=11,Type=SEDAType.ELEMENT,PropertyTerm="Contains",Cardinality="1..n",FormName="Archives",FormDescription="")
		public void setContains(List<ArchiveType> contains) {
			Contains = contains;
		}
		
		@SEDA(Position=11,Type=SEDAType.ELEMENT,PropertyTerm="Contains",Cardinality="1..n",FormName="Archives",FormDescription="")
		public void addContains(ArchiveType contains) {
			Contains.add(contains);
		}
		

		@SEDA(Position=12,Type=SEDAType.ATTRIBUTE,PropertyTerm="Id",Cardinality="0..1")
		public String getId() {
			return ID;
		}

		@SEDA(Position=12,Type=SEDAType.ATTRIBUTE,PropertyTerm="Id",Cardinality="0..1")
		public void setId(String iD) {
			ID = iD;
		}
}
