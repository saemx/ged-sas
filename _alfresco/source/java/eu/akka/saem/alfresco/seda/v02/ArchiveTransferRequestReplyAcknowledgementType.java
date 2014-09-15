package eu.akka.saem.alfresco.seda.v02;

import eu.akka.saem.alfresco.seda.annotations.SEDA;
import eu.akka.saem.alfresco.seda.annotations.SEDAType;
import eu.akka.saem.alfresco.seda.v02.complexType.ArchivesIDType;
import eu.akka.saem.alfresco.seda.v02.complexType.CodeReplyType;
import eu.akka.saem.alfresco.seda.v02.complexType.DateTimeType;
import eu.akka.saem.alfresco.seda.v02.complexType.OrganizationType;

/**
 * Accusé de réception d'une réponse à une demande de transfert d'archives
 * @author benjamin.catinot
 */
@SEDA(PropertyTerm="ArchiveTransferRequestReplyAcknowledgement")
public class ArchiveTransferRequestReplyAcknowledgementType {

		@SuppressWarnings("unused")
		private static final long serialVersionUID = 8106741656939374933L;

		private String ID;
		private String xmlns = "fr:gouv:ae:archive:draft:standard_echange_v0.2";
		private String Comment;
		private DateTimeType Date;
		private CodeReplyType ReplyCode;
		private ArchivesIDType TransferRequestReplyAcknowledgementIdentifier;
		private ArchivesIDType TransferRequestReplyIdentifier;
		private OrganizationType TransferringAgency;
		private OrganizationType ArchivalAgency;
		
		@SEDA(Position=0,Type=SEDAType.ATTRIBUTE,PropertyTerm="xmlns",Cardinality="1..1")
		public String getXmlns() {
			return xmlns;
		}

		public ArchiveTransferRequestReplyAcknowledgementType(){		
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
		 * @param Identifiant de l'accusé de réception de la réponse à la demande de transfert
		 */
		@SEDA(Position=4,Type=SEDAType.ELEMENT,PropertyTerm="TransferRequestReplyAcknowledgementIdentifier",Cardinality="1..1")
		public ArchivesIDType getTransferRequestReplyAcknowledgementIdentifier() {
			return TransferRequestReplyAcknowledgementIdentifier;
		}

		/**
		 * Element
		 * @param Identifiant de l'accusé de réception de la réponse à la demande de transfert
		 */
		@SEDA(Position=4,Type=SEDAType.ELEMENT,PropertyTerm="TransferRequestReplyAcknowledgementIdentifier",Cardinality="1..1")
		public void setTransferRequestReplyAcknowledgementIdentifier(
				ArchivesIDType transferRequestReplyAcknowledgementIdentifier) {
			TransferRequestReplyAcknowledgementIdentifier = transferRequestReplyAcknowledgementIdentifier;
		}

		/**
		 * Element
		 * @param Identifiant de la réponse à la demande de transfert
		 */
		@SEDA(Position=5,Type=SEDAType.ELEMENT,PropertyTerm="TransferRequestReplyIdentifier",Cardinality="1..1")
		public ArchivesIDType getTransferRequestReplyIdentifier() {
			return TransferRequestReplyIdentifier;
		}

		/**
		 * Element
		 * @param Identifiant de la réponse à la demande de transfert
		 */
		@SEDA(Position=5,Type=SEDAType.ELEMENT,PropertyTerm="TransferRequestReplyIdentifier",Cardinality="1..1")
		public void setTransferRequestReplyIdentifier(
				ArchivesIDType transferRequestReplyIdentifier) {
			TransferRequestReplyIdentifier = transferRequestReplyIdentifier;
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
		
		@SEDA(Position=8,Type=SEDAType.ATTRIBUTE,PropertyTerm="Id",Cardinality="0..1")
		public String getId() {
			return ID;
		}

		@SEDA(Position=8,Type=SEDAType.ATTRIBUTE,PropertyTerm="Id",Cardinality="0..1")
		public void setId(String iD) {
			ID = iD;
		}
}
