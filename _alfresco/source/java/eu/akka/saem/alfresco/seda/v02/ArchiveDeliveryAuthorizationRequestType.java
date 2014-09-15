package eu.akka.saem.alfresco.seda.v02;


import java.util.ArrayList;
import java.util.List;

import eu.akka.saem.alfresco.seda.annotations.SEDA;
import eu.akka.saem.alfresco.seda.annotations.SEDAType;
import eu.akka.saem.alfresco.seda.v02.complexType.ArchiveType;
import eu.akka.saem.alfresco.seda.v02.complexType.ArchivesIDType;
import eu.akka.saem.alfresco.seda.v02.complexType.DateTimeType;
import eu.akka.saem.alfresco.seda.v02.complexType.OrganizationType;
import eu.akka.saem.alfresco.seda.v02.complexType.SignatureType;

/**
 * Demande d'autorisation pour une communication d'archives
 * @author benjamin.catinot
 */
@SEDA(PropertyTerm="ArchiveDeliveryAuthorizationRequest")
public class ArchiveDeliveryAuthorizationRequestType {

		@SuppressWarnings("unused")
		private static final long serialVersionUID = 8106741656939374933L;

		private String ID;
		private String xmlns = "fr:gouv:ae:archive:draft:standard_echange_v0.2";
		private String Comment;
		private DateTimeType Date;
		private ArchivesIDType DeliveryAuthorizationRequestIdentifier;
		private ArchivesIDType ArchiveDeliveryRequestIdentifier;
		private List<ArchivesIDType>UnitIdentifier;
		private List<OrganizationType> ControlAuthority;
		private OrganizationType Requester;
		private OrganizationType OriginatingAgency;
		private OrganizationType ArchivalAgency;
		private SignatureType Authentication;
		private ArchiveType Archive;
		
		@SEDA(Position=0,Type=SEDAType.ATTRIBUTE,PropertyTerm="xmlns",Cardinality="1..1")
		public String getXmlns() {
			return xmlns;
		}

		public ArchiveDeliveryAuthorizationRequestType(){
			ControlAuthority = new ArrayList<OrganizationType>();
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
		 * @param Identifiant de la demande d'autorisation de communication
		 */
		@SEDA(Position=3,Type=SEDAType.ELEMENT,PropertyTerm="DeliveryAuthorizationRequestIdentifier",Cardinality="1..1")
		public ArchivesIDType getDeliveryAuthorizationRequestIdentifier() {
			return DeliveryAuthorizationRequestIdentifier;
		}

		/**
		 * Element
		 * @param Identifiant de la demande d'autorisation de communication
		 */
		@SEDA(Position=3,Type=SEDAType.ELEMENT,PropertyTerm="DeliveryAuthorizationRequestIdentifier",Cardinality="1..1")
		public void setDeliveryAuthorizationRequestIdentifier(
				ArchivesIDType deliveryAuthorizationRequestIdentifier) {
			DeliveryAuthorizationRequestIdentifier = deliveryAuthorizationRequestIdentifier;
		}

		/**
		 * Element
		 * @param Identifiant de la demande de communication (permet de rappeler la demande initiale du demandeur)
		 */
		@SEDA(Position=4,Type=SEDAType.ELEMENT,PropertyTerm="ArchiveDeliveryRequestIdentifier",Cardinality="0..1")
		public ArchivesIDType getArchiveDeliveryRequestIdentifier() {
			return ArchiveDeliveryRequestIdentifier;
		}

		/**
		 * Element
		 * @param Identifiant de la demande de communication (permet de rappeler la demande initiale du demandeur)
		 */
		@SEDA(Position=4,Type=SEDAType.ELEMENT,PropertyTerm="ArchiveDeliveryRequestIdentifier",Cardinality="0..1")
		public void setArchiveDeliveryRequestIdentifier(
				ArchivesIDType archiveDeliveryRequestIdentifier) {
			ArchiveDeliveryRequestIdentifier = archiveDeliveryRequestIdentifier;
		}

		/**
		 * Element
		 * @param Indique si le demandeur souhaite qu'une procédure de dérogation soit enclenchée en cas de non communicabilité de l'archive demandée en communication
		 */
		@SEDA(Position=5,Type=SEDAType.ELEMENT,PropertyTerm="UnitIdentifier",Cardinality="1..n")
		public List<ArchivesIDType> getUnitIdentifier() {
			return UnitIdentifier;
		}

		/**
		 * Element
		 * @param Tout identifiant permettant de reconnaître le contenu demandé
		 */
		@SEDA(Position=5,Type=SEDAType.ELEMENT,PropertyTerm="UnitIdentifier",Cardinality="1..n")
		public void setUnitIdentifier(List<ArchivesIDType> unitIdentifier) {
			UnitIdentifier = unitIdentifier;
		}
		
		/**
		 * Element
		 * @param Tout identifiant permettant de reconnaître le contenu demandé
		 */
		@SEDA(Position=5,Type=SEDAType.ELEMENT,PropertyTerm="UnitIdentifier",Cardinality="1..n")
		public void addUnitIdentifier(ArchivesIDType unitIdentifier) {
			UnitIdentifier.add(unitIdentifier);
		}

		
		/**
		 * Element
		 * @param ervices de Contrôle
		 */
		@SEDA(Position=7,Type=SEDAType.ELEMENT,PropertyTerm="ControlAuthority",Cardinality="0..n")
		public List<OrganizationType> getControlAuthority() {
			return ControlAuthority;
		}

		/**
		 * Element
		 * @param ervices de Contrôle
		 */
		@SEDA(Position=7,Type=SEDAType.ELEMENT,PropertyTerm="ControlAuthority",Cardinality="0..n")
		public void setControlAuthority(List<OrganizationType> controlAuthority) {
			ControlAuthority = controlAuthority;
		}

		/**
		 * Element
		 * @param ervices de Contrôle
		 */
		@SEDA(Position=7,Type=SEDAType.ELEMENT,PropertyTerm="ControlAuthority",Cardinality="0..n")
		public void setControlAuthority(OrganizationType controlAuthority) {
			ControlAuthority.add(controlAuthority);
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
		 * @param 
		 */
		@SEDA(Position=9,Type=SEDAType.ELEMENT,PropertyTerm="OriginatingAgency",Cardinality="1..1")
		public OrganizationType getOriginatingAgency() {
			return OriginatingAgency;
		}

		/**
		 * Element
		 * @param 
		 */
		@SEDA(Position=9,Type=SEDAType.ELEMENT,PropertyTerm="OriginatingAgency",Cardinality="1..1")
		public void setOriginatingAgency(OrganizationType originatingAgency) {
			OriginatingAgency = originatingAgency;
		}

		/**
		 * Element
		 * @return Service d'archive
		 */
		@SEDA(Position=10,Type=SEDAType.ELEMENT,PropertyTerm="ArchivalAgency",Cardinality="1..1")
		public OrganizationType getArchivalAgency() {
			return ArchivalAgency;
		}
		
		/**
		 * Element
		 * @param Service d'archive
		 */
		@SEDA(Position=10,Type=SEDAType.ELEMENT,PropertyTerm="ArchivalAgency",Cardinality="1..1")
		public void setArchivalAgency(OrganizationType archivalAgency) {
			ArchivalAgency = archivalAgency;
		}
		

		/**
		 * Element
		 * @param Service d'archive
		 */
		@SEDA(Position=11,Type=SEDAType.ELEMENT,PropertyTerm="Authentication",Cardinality="0..1")
		public SignatureType getAuthentication() {
			return Authentication;
		}

		/**
		 * Element
		 * @param Service d'archive
		 */
		@SEDA(Position=11,Type=SEDAType.ELEMENT,PropertyTerm="Authentication",Cardinality="0..1")
		public void setAuthentication(SignatureType authentication) {
			Authentication = authentication;
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
