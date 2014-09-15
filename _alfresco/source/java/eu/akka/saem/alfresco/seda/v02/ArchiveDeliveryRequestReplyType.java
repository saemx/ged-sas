package eu.akka.saem.alfresco.seda.v02;


import java.util.ArrayList;
import java.util.List;

import eu.akka.saem.alfresco.seda.annotations.SEDA;
import eu.akka.saem.alfresco.seda.annotations.SEDAType;
import eu.akka.saem.alfresco.seda.v02.complexType.ArchivesIDType;
import eu.akka.saem.alfresco.seda.v02.complexType.DateTimeType;
import eu.akka.saem.alfresco.seda.v02.complexType.IndicatorType;
import eu.akka.saem.alfresco.seda.v02.complexType.OrganizationType;
import eu.akka.saem.alfresco.seda.v02.complexType.SignatureType;

/**
 * Demande de communication d'archives
 * @author benjamin.catinot
 */
@SEDA(PropertyTerm="ArchiveDeliveryRequestReply")
public class ArchiveDeliveryRequestReplyType {

		@SuppressWarnings("unused")
		private static final long serialVersionUID = 8106741656939374933L;

		private String ID;
		private String xmlns = "fr:gouv:ae:archive:draft:standard_echange_v0.2";
		private String Comment;
		private DateTimeType Date;
		private ArchivesIDType DeliveryRequestIdentifier;
		private IndicatorType Derogation;
		private List<ArchivesIDType>UnitIdentifier;
		private OrganizationType AccessRequester;
		private OrganizationType ArchivalAgency;
		private SignatureType Authentication;
		
		@SEDA(Position=0,Type=SEDAType.ATTRIBUTE,PropertyTerm="xmlns",Cardinality="1..1")
		public String getXmlns() {
			return xmlns;
		}

		public ArchiveDeliveryRequestReplyType(){
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
		 * @param Identifiant de la demande de communication
		 */
		@SEDA(Position=3,Type=SEDAType.ELEMENT,PropertyTerm="DeliveryRequestIdentifier",Cardinality="1..1")
		public ArchivesIDType getDeliveryRequestIdentifier() {
			return DeliveryRequestIdentifier;
		}

		/**
		 * Element
		 * @param Identifiant de la demande de communication
		 */
		@SEDA(Position=3,Type=SEDAType.ELEMENT,PropertyTerm="DeliveryRequestIdentifier",Cardinality="1..1")
		public void setDeliveryRequestIdentifier(
				ArchivesIDType deliveryRequestIdentifier) {
			DeliveryRequestIdentifier = deliveryRequestIdentifier;
		}

		/**
		 * Element
		 * @param Indique si le demandeur souhaite qu'une procédure de dérogation soit enclenchée en cas de non communicabilité de l'archive demandée en communication
		 */
		@SEDA(Position=4,Type=SEDAType.ELEMENT,PropertyTerm="Derogation",Cardinality="1..1")
		public IndicatorType getDerogation() {
			return Derogation;
		}

		/**
		 * Element
		 * @param Indique si le demandeur souhaite qu'une procédure de dérogation soit enclenchée en cas de non communicabilité de l'archive demandée en communication
		 */
		@SEDA(Position=4,Type=SEDAType.ELEMENT,PropertyTerm="Derogation",Cardinality="1..1")
		public void setDerogation(IndicatorType derogation) {
			Derogation = derogation;
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
		 * @param 
		 */
		@SEDA(Position=7,Type=SEDAType.ELEMENT,PropertyTerm="AccessRequester",Cardinality="1..1")
		public OrganizationType getAccessRequester() {
			return AccessRequester;
		}

		/**
		 * Element
		 * @param 
		 */
		@SEDA(Position=7,Type=SEDAType.ELEMENT,PropertyTerm="AccessRequester",Cardinality="1..1")
		public void setAccessRequester(OrganizationType accessRequester) {
			AccessRequester = accessRequester;
		}

		/**
		 * Element
		 * @return Service d'archive
		 */
		@SEDA(Position=8,Type=SEDAType.ELEMENT,PropertyTerm="ArchivalAgency",Cardinality="1..1")
		public OrganizationType getArchivalAgency() {
			return ArchivalAgency;
		}
		
		/**
		 * Element
		 * @param Service d'archive
		 */
		@SEDA(Position=8,Type=SEDAType.ELEMENT,PropertyTerm="ArchivalAgency",Cardinality="1..1")
		public void setArchivalAgency(OrganizationType archivalAgency) {
			ArchivalAgency = archivalAgency;
		}
		

		/**
		 * Element
		 * @param Service d'archive
		 */
		@SEDA(Position=9,Type=SEDAType.ELEMENT,PropertyTerm="Authentication",Cardinality="0..1")
		public SignatureType getAuthentication() {
			return Authentication;
		}

		/**
		 * Element
		 * @param Service d'archive
		 */
		@SEDA(Position=9,Type=SEDAType.ELEMENT,PropertyTerm="Authentication",Cardinality="0..1")
		public void setAuthentication(SignatureType authentication) {
			Authentication = authentication;
		}

		@SEDA(Position=10,Type=SEDAType.ATTRIBUTE,PropertyTerm="Id",Cardinality="0..1")
		public String getId() {
			return ID;
		}

		@SEDA(Position=10,Type=SEDAType.ATTRIBUTE,PropertyTerm="Id",Cardinality="0..1")
		public void setId(String iD) {
			ID = iD;
		}
}
