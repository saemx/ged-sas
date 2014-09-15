package eu.akka.saem.alfresco.seda.v02;


import java.util.*;
import eu.akka.saem.alfresco.seda.annotations.SEDA;
import eu.akka.saem.alfresco.seda.annotations.SEDAType;
import eu.akka.saem.alfresco.seda.v02.complexType.ArchiveType;
import eu.akka.saem.alfresco.seda.v02.complexType.ArchivesIDType;
import eu.akka.saem.alfresco.seda.v02.complexType.CodeReplyType;
import eu.akka.saem.alfresco.seda.v02.complexType.DateTimeType;
import eu.akka.saem.alfresco.seda.v02.complexType.OrganizationType;
import eu.akka.saem.alfresco.seda.v02.complexType.SignatureType;

/**
 * Prise en charge d'un transfert à titre de versement
 * @author benjamin.catinot
 */
@SEDA(PropertyTerm="ArchiveTransferAcceptance")
public class ArchiveTransferAcceptanceType {

		@SuppressWarnings("unused")
		private static final long serialVersionUID = 8106741656939374933L;

		private String ID;
		private String xmlns = "fr:gouv:ae:archive:draft:standard_echange_v0.2";
		private String Comment;
		private DateTimeType Date;
		private CodeReplyType ReplyCode;
		private ArchivesIDType TransferAcceptanceIdentifier;
		private ArchivesIDType TransferIdentifier;
		private OrganizationType TransferringAgency;
		private OrganizationType ArchivalAgency;
		private SignatureType Approval;
		private List<ArchiveType> Archive;
		
		@SEDA(Position=0,Type=SEDAType.ATTRIBUTE,PropertyTerm="xmlns",Cardinality="1..1")
		public String getXmlns() {
			return xmlns;
		}

		public ArchiveTransferAcceptanceType(){
			Archive = new ArrayList<ArchiveType>();			
		}
		
		/**
		 * Element
		 * @return Commentaires
		 */
		@SEDA(Position=1,Type=SEDAType.ELEMENT,PropertyTerm="Comment",Cardinality="0..1")
		public String getComment() {
			return Comment;
		}
		
		/**
		 * Element
		 * @param Commentaires
		 */
		@SEDA(Position=1,Type=SEDAType.ELEMENT,PropertyTerm="Comment",Cardinality="0..1")
		public void setComment(String comment) {
			Comment = comment;
		}
		
		/**
		 * Element
		 * @return Date du transfert
		 */
		@SEDA(Position=2,Type=SEDAType.ELEMENT,PropertyTerm="Date",Cardinality="1..1")
		public DateTimeType getDate() {
			return Date;
		}
		
		/**
		 * Element
		 * @param Date du transfert
		 */
		@SEDA(Position=2,Type=SEDAType.ELEMENT,PropertyTerm="Date",Cardinality="1..1")
		public void setDate(DateTimeType date) {
			Date = date;
		}
		
		/**
		 * Element
		 * @param Code de la réponse (OK, anomalie...)
		 */
		@SEDA(Position=3,Type=SEDAType.ELEMENT,PropertyTerm="ReplyCode",Cardinality="1..1")
		public CodeReplyType getReplyCode() {
			return ReplyCode;
		}

		/**
		 * Element
		 * @param Code de la réponse (OK, anomalie...)
		 */
		@SEDA(Position=3,Type=SEDAType.ELEMENT,PropertyTerm="ReplyCode",Cardinality="1..1")
		public void setReplyCode(CodeReplyType replyCode) {
			ReplyCode = replyCode;
		}
		
		/**
		 * Element
		 * @return Référence à un autre transfert
		 */
		@SEDA(Position=4,Type=SEDAType.ELEMENT,PropertyTerm="TransferAcceptanceIdentifier",Cardinality="1..1")
		public ArchivesIDType getTransferAcceptanceIdentifier() {
			return TransferAcceptanceIdentifier;
		}
		
		/**
		 * Element
		 * @param Référence à un autre transfert
		 */
		@SEDA(Position=4,Type=SEDAType.ELEMENT,PropertyTerm="TransferAcceptanceIdentifier",Cardinality="1..1")
		public void setTransferAcceptanceIdentifier(ArchivesIDType transferAcceptanceIdentifier) {
			TransferAcceptanceIdentifier = transferAcceptanceIdentifier;
		}
		
		/**
		 * Element
		 * @return Identifiant du transfert
		 */
		@SEDA(Position=5,Type=SEDAType.ELEMENT,PropertyTerm="TransferIdentifier",Cardinality="1..1")
		public ArchivesIDType getTransferIdentifier() {
			return TransferIdentifier;
		}
		
		/**
		 * Element
		 * @param Identifiant du transfert
		 */
		@SEDA(Position=5,Type=SEDAType.ELEMENT,PropertyTerm="TransferIdentifier",Cardinality="1..1")
		public void setTransferIdentifier(ArchivesIDType transferIdentifier) {
			TransferIdentifier = transferIdentifier;
		}
		
		/**
		 * Element
		 * @return Service versant
		 */
		@SEDA(Position=6,Type=SEDAType.ELEMENT,PropertyTerm="TransferringAgency",Cardinality="1..1")
		public OrganizationType getTransferringAgency() {
			return TransferringAgency;
		}
		
		/**
		 * Element
		 * @param Service versant
		 */
		@SEDA(Position=6,Type=SEDAType.ELEMENT,PropertyTerm="TransferringAgency",Cardinality="1..1")
		public void setTransferringAgency(OrganizationType transferringAgency) {
			TransferringAgency = transferringAgency;
		}
		
		
		/**
		 * Element
		 * @return Service d'archive
		 */
		@SEDA(Position=7,Type=SEDAType.ELEMENT,PropertyTerm="ArchivalAgency",Cardinality="1..1")
		public OrganizationType getArchivalAgency() {
			return ArchivalAgency;
		}
		
		/**
		 * Element
		 * @param Service d'archive
		 */
		@SEDA(Position=7,Type=SEDAType.ELEMENT,PropertyTerm="ArchivalAgency",Cardinality="1..1")
		public void setArchivalAgency(OrganizationType archivalAgency) {
			ArchivalAgency = archivalAgency;
		}
		

		@SEDA(Position=8,Type=SEDAType.ELEMENT,PropertyTerm="Approval",Cardinality="0..1")
		public SignatureType getApproval() {
			return Approval;
		}
		
		@SEDA(Position=8,Type=SEDAType.ELEMENT,PropertyTerm="Approval",Cardinality="0..1")
		public void setApproval(SignatureType approval) {
			Approval = approval;
		}
		
		@SEDA(Position=9,Type=SEDAType.ELEMENT,PropertyTerm="Archive",Cardinality="0..n")
		public List<ArchiveType> getArchive() {
			return Archive;
		}
		
		@SEDA(Position=9,Type=SEDAType.ELEMENT,PropertyTerm="Archive",Cardinality="0..n")
		public void setArchive(List<ArchiveType> archive) {
			Archive = archive;
		}
		
		@SEDA(Position=9,Type=SEDAType.ELEMENT,PropertyTerm="Archive",Cardinality="0..n")
		public void addArchive(ArchiveType archive) {
			Archive.add(archive);
		}
		

		@SEDA(Position=11,Type=SEDAType.ATTRIBUTE,PropertyTerm="Id",Cardinality="0..1")
		public String getId() {
			return ID;
		}

		@SEDA(Position=11,Type=SEDAType.ATTRIBUTE,PropertyTerm="Id",Cardinality="0..1")
		public void setId(String iD) {
			ID = iD;
		}
}
