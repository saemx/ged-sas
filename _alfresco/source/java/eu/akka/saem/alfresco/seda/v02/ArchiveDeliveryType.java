package eu.akka.saem.alfresco.seda.v02;


import java.util.ArrayList;
import java.util.List;

import eu.akka.saem.alfresco.seda.annotations.SEDA;
import eu.akka.saem.alfresco.seda.annotations.SEDAType;
import eu.akka.saem.alfresco.seda.v02.complexType.ArchiveType;
import eu.akka.saem.alfresco.seda.v02.complexType.ArchivesIDType;
import eu.akka.saem.alfresco.seda.v02.complexType.DateTimeType;
import eu.akka.saem.alfresco.seda.v02.complexType.HashCodeType;
import eu.akka.saem.alfresco.seda.v02.complexType.OrganizationType;
import eu.akka.saem.alfresco.seda.v02.complexType.SignatureType;

/**
 * Communication d'archives
 * @author benjamin.catinot
 */
@SEDA(PropertyTerm="ArchiveDelivery")
public class ArchiveDeliveryType {

		@SuppressWarnings("unused")
		private static final long serialVersionUID = 8106741656939374933L;

		private String ID;
		private String xmlns = "fr:gouv:ae:archive:draft:standard_echange_v0.2";
		private String Comment;
		private DateTimeType Date;
		private List<ArchivesIDType> DeliveryAuthorizationIdentifier;
		private ArchivesIDType DeliveryIdentifier;
		private ArchivesIDType DeliveryRequestIdentifier;
		private List<ArchivesIDType>UnitIdentifier;
		private OrganizationType Requester;
		private OrganizationType ArchivalAgency;
		private SignatureType Signature;
		private HashCodeType HashCode;
		private ArchiveType Archive;
		
		@SEDA(Position=0,Type=SEDAType.ATTRIBUTE,PropertyTerm="xmlns",Cardinality="1..1")
		public String getXmlns() {
			return xmlns;
		}

		public ArchiveDeliveryType(){
			setDeliveryAuthorizationIdentifier(new ArrayList<ArchivesIDType>());	
			UnitIdentifier = new ArrayList<ArchivesIDType>();	
		}
		
		/**
		 * Element
		 * @return Commentaires
		 */
		@SEDA(Position=2,Type=SEDAType.ELEMENT,PropertyTerm="Comment",Cardinality="0..1")
		public String getComment() {
			return Comment;
		}
		
		/**
		 * Element
		 * @param Commentaires
		 */
		@SEDA(Position=2,Type=SEDAType.ELEMENT,PropertyTerm="Comment",Cardinality="0..1")
		public void setComment(String comment) {
			Comment = comment;
		}
		
		/**
		 * Element
		 * @return Date du transfert
		 */
		@SEDA(Position=3,Type=SEDAType.ELEMENT,PropertyTerm="Date",Cardinality="1..1")
		public DateTimeType getDate() {
			return Date;
		}
		
		/**
		 * Element
		 * @param Date du transfert
		 */
		@SEDA(Position=3,Type=SEDAType.ELEMENT,PropertyTerm="Date",Cardinality="1..1")
		public void setDate(DateTimeType date) {
			Date = date;
		}
		
		
		/**
		 * Element
		 * @param Identifiant de l'autorisation de communication accordée par une autorité de contrôle
		 */
		@SEDA(Position=4,Type=SEDAType.ELEMENT,PropertyTerm="DeliveryAuthorizationIdentifier",Cardinality="0..n")
		public List<ArchivesIDType> getDeliveryAuthorizationIdentifier() {
			return DeliveryAuthorizationIdentifier;
		}

		/**
		 * Element
		 * @param Identifiant de l'autorisation de communication accordée par une autorité de contrôle
		 */
		@SEDA(Position=4,Type=SEDAType.ELEMENT,PropertyTerm="DeliveryAuthorizationIdentifier",Cardinality="0..n")
		public void setDeliveryAuthorizationIdentifier(
				List<ArchivesIDType> deliveryAuthorizationIdentifier) {
			DeliveryAuthorizationIdentifier = deliveryAuthorizationIdentifier;
		}
		
		/**
		 * Element
		 * @param Identifiant de l'autorisation de communication accordée par une autorité de contrôle
		 */
		@SEDA(Position=4,Type=SEDAType.ELEMENT,PropertyTerm="DeliveryAuthorizationIdentifier",Cardinality="0..n")
		public void setDeliveryAuthorizationIdentifier(
				ArchivesIDType deliveryAuthorizationIdentifier) {
			DeliveryAuthorizationIdentifier.add(deliveryAuthorizationIdentifier);
		}

		/**
		 * Element
		 * @param Identifiant de la communication
		 */
		@SEDA(Position=5,Type=SEDAType.ELEMENT,PropertyTerm="DeliveryIdentifier",Cardinality="1..1")
		public ArchivesIDType getDeliveryIdentifier() {
			return DeliveryIdentifier;
		}

		/**
		 * Element
		 * @param Identifiant de la communication
		 */
		@SEDA(Position=5,Type=SEDAType.ELEMENT,PropertyTerm="DeliveryIdentifier",Cardinality="1..1")
		public void setDeliveryIdentifier(ArchivesIDType deliveryIdentifier) {
			DeliveryIdentifier = deliveryIdentifier;
		}

		/**
		 * Element
		 * @param Identifiant de la communication
		 */
		@SEDA(Position=6,Type=SEDAType.ELEMENT,PropertyTerm="DeliveryRequestIdentifier",Cardinality="1..1")
		public ArchivesIDType getDeliveryRequestIdentifier() {
			return DeliveryRequestIdentifier;
		}

		/**
		 * Element
		 * @param Identifiant de la communication
		 */
		@SEDA(Position=6,Type=SEDAType.ELEMENT,PropertyTerm="DeliveryRequestIdentifier",Cardinality="1..1")
		public void setDeliveryRequestIdentifier(
				ArchivesIDType deliveryRequestIdentifier) {
			DeliveryRequestIdentifier = deliveryRequestIdentifier;
		}

		/**
		 * Element
		 * @param Indique si le demandeur souhaite qu'une procédure de dérogation soit enclenchée en cas de non communicabilité de l'archive demandée en communication
		 */
		@SEDA(Position=7,Type=SEDAType.ELEMENT,PropertyTerm="UnitIdentifier",Cardinality="1..n")
		public List<ArchivesIDType> getUnitIdentifier() {
			return UnitIdentifier;
		}

		/**
		 * Element
		 * @param Tout identifiant permettant de reconnaître le contenu demandé
		 */
		@SEDA(Position=7,Type=SEDAType.ELEMENT,PropertyTerm="UnitIdentifier",Cardinality="1..n")
		public void setUnitIdentifier(List<ArchivesIDType> unitIdentifier) {
			UnitIdentifier = unitIdentifier;
		}
		
		/**
		 * Element
		 * @param Tout identifiant permettant de reconnaître le contenu demandé
		 */
		@SEDA(Position=7,Type=SEDAType.ELEMENT,PropertyTerm="UnitIdentifier",Cardinality="1..n")
		public void addUnitIdentifier(ArchivesIDType unitIdentifier) {
			UnitIdentifier.add(unitIdentifier);
		}

		
	
		/**
		 * Element
		 * @param 
		 */
		@SEDA(Position=8,Type=SEDAType.ELEMENT,PropertyTerm="Requester",Cardinality="1..1")
		public OrganizationType getRequester() {
			return Requester;
		}

		/**
		 * Element
		 * @param 
		 */
		@SEDA(Position=8,Type=SEDAType.ELEMENT,PropertyTerm="Requester",Cardinality="1..1")
		public void setRequester(OrganizationType requester) {
			Requester = requester;
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
		 * @param Service d'archive
		 */
		@SEDA(Position=10,Type=SEDAType.ELEMENT,PropertyTerm="Signature",Cardinality="0..1")
		public SignatureType getSignature() {
			return Signature;
		}

		/**
		 * Element
		 * @param Service d'archive
		 */
		@SEDA(Position=10,Type=SEDAType.ELEMENT,PropertyTerm="Signature",Cardinality="0..1")
		public void setSignature(SignatureType signature) {
			Signature = signature;
		}

		/**
		 * Element
		 * @param Service d'archive
		 */
		@SEDA(Position=11,Type=SEDAType.ELEMENT,PropertyTerm="HashCode",Cardinality="0..1")
		public HashCodeType getHashCode() {
			return HashCode;
		}

		/**
		 * Element
		 * @param Service d'archive
		 */
		@SEDA(Position=11,Type=SEDAType.ELEMENT,PropertyTerm="HashCode",Cardinality="0..1")
		public void setHashCode(HashCodeType hashCode) {
			HashCode = hashCode;
		}

		/**
		 * Element
		 * @param Service d'archive
		 */
		@SEDA(Position=12,Type=SEDAType.ELEMENT,PropertyTerm="Archive",Cardinality="1..1")
		public ArchiveType getArchive() {
			return Archive;
		}

		/**
		 * Element
		 * @param Service d'archive
		 */
		@SEDA(Position=12,Type=SEDAType.ELEMENT,PropertyTerm="Archive",Cardinality="1..1")
		public void setArchive(ArchiveType archive) {
			Archive = archive;
		}

		@SEDA(Position=13,Type=SEDAType.ATTRIBUTE,PropertyTerm="Id",Cardinality="0..1")
		public String getId() {
			return ID;
		}

		@SEDA(Position=13,Type=SEDAType.ATTRIBUTE,PropertyTerm="Id",Cardinality="0..1")
		public void setId(String iD) {
			ID = iD;
		}
}
