package eu.akka.saem.alfresco.seda.v02;


import java.util.ArrayList;
import java.util.List;

import eu.akka.saem.alfresco.seda.annotations.SEDA;
import eu.akka.saem.alfresco.seda.annotations.SEDAType;
import eu.akka.saem.alfresco.seda.v02.complexType.ArchivesIDType;
import eu.akka.saem.alfresco.seda.v02.complexType.CodeReplyType;
import eu.akka.saem.alfresco.seda.v02.complexType.DateTimeType;
import eu.akka.saem.alfresco.seda.v02.complexType.OrganizationType;
import eu.akka.saem.alfresco.seda.v02.complexType.SignatureType;

/**
 * Réponse donnée à une demande d'autorisation de communication ou d'élimination d'archives
 * @author benjamin.catinot
 */
@SEDA(PropertyTerm="ArchiveDeliveryAuthorizationRequestReply")
public class ArchiveDeliveryAuthorizationRequestReplyType {

		@SuppressWarnings("unused")
		private static final long serialVersionUID = 8106741656939374933L;

		private String ID;
		private String xmlns = "fr:gouv:ae:archive:draft:standard_echange_v0.2";
		private String Comment;
		private DateTimeType Date;
		private ArchivesIDType DeliveryAcknowledgementIdentifier;
		private ArchivesIDType DeliveryIdentifier;
		private CodeReplyType ReplyCode; 
		private List<ArchivesIDType>UnitIdentifier;
		private OrganizationType Requester;
		private OrganizationType ArchivalAgency;
		private SignatureType Signature;
		
		@SEDA(Position=0,Type=SEDAType.ATTRIBUTE,PropertyTerm="xmlns",Cardinality="1..1")
		public String getXmlns() {
			return xmlns;
		}

		public ArchiveDeliveryAuthorizationRequestReplyType(){
			UnitIdentifier = new ArrayList<ArchivesIDType>();		
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
		 * @param Identifiant de l'accusé de réception de la communication
		 */
		@SEDA(Position=3,Type=SEDAType.ELEMENT,PropertyTerm="DeliveryAcknowledgementIdentifier",Cardinality="1..1")
		public ArchivesIDType getDeliveryAcknowledgementIdentifier() {
			return DeliveryAcknowledgementIdentifier;
		}

		/**
		 * Element
		 * @param Identifiant de l'accusé de réception de la communication
		 */
		@SEDA(Position=3,Type=SEDAType.ELEMENT,PropertyTerm="DeliveryAcknowledgementIdentifier",Cardinality="1..1")
		public void setDeliveryAcknowledgementIdentifier(
				ArchivesIDType deliveryAcknowledgementIdentifier) {
			DeliveryAcknowledgementIdentifier = deliveryAcknowledgementIdentifier;
		}

		/**
		 * Element
		 * @param Identifiant de la communication
		 */
		@SEDA(Position=4,Type=SEDAType.ELEMENT,PropertyTerm="DeliveryIdentifier",Cardinality="1..1")
		public ArchivesIDType getDeliveryIdentifier() {
			return DeliveryIdentifier;
		}

		/**
		 * Element
		 * @param Identifiant de la communication
		 */
		@SEDA(Position=4,Type=SEDAType.ELEMENT,PropertyTerm="DeliveryIdentifier",Cardinality="1..1")
		public void setDeliveryIdentifier(ArchivesIDType deliveryIdentifier) {
			DeliveryIdentifier = deliveryIdentifier;
		}

		/**
		 * Element
		 * @param Code de la réponse (OK, anomalie...)
		 */
		@SEDA(Position=5,Type=SEDAType.ELEMENT,PropertyTerm="ReplyCode",Cardinality="1..1")
		public CodeReplyType getReplyCode() {
			return ReplyCode;
		}

		/**
		 * Element
		 * @param Code de la réponse (OK, anomalie...)
		 */
		@SEDA(Position=5,Type=SEDAType.ELEMENT,PropertyTerm="ReplyCode",Cardinality="1..1")
		public void setReplyCode(CodeReplyType replyCode) {
			ReplyCode = replyCode;
		}

		/**
		 * Element
		 * @param Indique si le demandeur souhaite qu'une procédure de dérogation soit enclenchée en cas de non communicabilité de l'archive demandée en communication
		 */
		@SEDA(Position=6,Type=SEDAType.ELEMENT,PropertyTerm="UnitIdentifier",Cardinality="1..n")
		public List<ArchivesIDType> getUnitIdentifier() {
			return UnitIdentifier;
		}

		/**
		 * Element
		 * @param 
		 */
		@SEDA(Position=6,Type=SEDAType.ELEMENT,PropertyTerm="Requester",Cardinality="1..1")
		public void setUnitIdentifier(List<ArchivesIDType> unitIdentifier) {
			UnitIdentifier = unitIdentifier;
		}
		
		/**
		 * Element
		 * @param 
		 */
		@SEDA(Position=7,Type=SEDAType.ELEMENT,PropertyTerm="Requester",Cardinality="1..1")
		public OrganizationType getRequester() {
			return Requester;
		}

		/**
		 * Element
		 * @param Tout identifiant permettant de reconnaître le contenu demandé
		 */
		@SEDA(Position=7,Type=SEDAType.ELEMENT,PropertyTerm="UnitIdentifier",Cardinality="1..n")
		public void setRequester(OrganizationType requester) {
			Requester = requester;
		}

		/**
		 * Element
		 * @param Tout identifiant permettant de reconnaître le contenu demandé
		 */
		@SEDA(Position=6,Type=SEDAType.ELEMENT,PropertyTerm="UnitIdentifier",Cardinality="1..n")
		public void addUnitIdentifier(ArchivesIDType unitIdentifier) {
			UnitIdentifier.add(unitIdentifier);
		}

		
		/**
		 * Element
		 * @return Service d'archive
		 */
		@SEDA(Position=9,Type=SEDAType.ELEMENT,PropertyTerm="ArchivalAgency",Cardinality="1..1")
		public OrganizationType getArchivalAgency() {
			return ArchivalAgency;
		}
		
		/**
		 * Element
		 * @param Service d'archive
		 */
		@SEDA(Position=9,Type=SEDAType.ELEMENT,PropertyTerm="ArchivalAgency",Cardinality="1..1")
		public void setArchivalAgency(OrganizationType archivalAgency) {
			ArchivalAgency = archivalAgency;
		}
		
		
		/**
		 * Element
		 * @param
		 */
		@SEDA(Position=10,Type=SEDAType.ELEMENT,PropertyTerm="Signature",Cardinality="0..1")
		public SignatureType getSignature() {
			return Signature;
		}

		/**
		 * Element
		 * @param 
		 */
		@SEDA(Position=10,Type=SEDAType.ELEMENT,PropertyTerm="Signature",Cardinality="0..1")
		public void setSignature(SignatureType signature) {
			Signature = signature;
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
