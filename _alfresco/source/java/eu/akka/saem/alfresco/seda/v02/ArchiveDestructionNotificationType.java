package eu.akka.saem.alfresco.seda.v02;


import java.util.ArrayList;
import java.util.List;

import eu.akka.saem.alfresco.seda.annotations.SEDA;
import eu.akka.saem.alfresco.seda.annotations.SEDAType;
import eu.akka.saem.alfresco.seda.v02.complexType.ArchivesIDType;
import eu.akka.saem.alfresco.seda.v02.complexType.DateTimeType;
import eu.akka.saem.alfresco.seda.v02.complexType.OrganizationType;

/**
 * 
 * @author benjamin.catinot
 */
@SEDA(PropertyTerm="ArchiveDestructionNotification")
public class ArchiveDestructionNotificationType {

		@SuppressWarnings("unused")
		private static final long serialVersionUID = 8106741656939374933L;

		private String ID;
		private String xmlns = "fr:gouv:ae:archive:draft:standard_echange_v0.2";
		private String Comment;
		private DateTimeType Date;
		private ArchivesIDType DestructionAcceptanceIdentifier;
		private DateTimeType DestructionDate;
		private ArchivesIDType DestructionNotificationIdentifier;
		private ArchivesIDType DestructionRequestIdentifier;
		private List<ArchivesIDType>UnitIdentifier;
		private OrganizationType OriginatingAgency;
		private OrganizationType ArchivalAgency;
		
		@SEDA(Position=0,Type=SEDAType.ATTRIBUTE,PropertyTerm="xmlns",Cardinality="1..1")
		public String getXmlns() {
			return xmlns;
		}

		public ArchiveDestructionNotificationType(){
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
		 * @param 
		 */
		@SEDA(Position=3,Type=SEDAType.ELEMENT,PropertyTerm="DestructionAcceptanceIdentifier",Cardinality="0..1")
		public ArchivesIDType getDestructionAcceptanceIdentifier() {
			return DestructionAcceptanceIdentifier;
		}

		/**
		 * Element
		 * @param
		 */
		@SEDA(Position=3,Type=SEDAType.ELEMENT,PropertyTerm="DestructionAcceptanceIdentifier",Cardinality="0..1")
		public void setDestructionAcceptanceIdentifier(
				ArchivesIDType destructionAcceptanceIdentifier) {
			DestructionAcceptanceIdentifier = destructionAcceptanceIdentifier;
		}

		/**
		 * Element
		 * @param 
		 */
		@SEDA(Position=4,Type=SEDAType.ELEMENT,PropertyTerm="DestructionDate",Cardinality="1..1")
		public DateTimeType getDestructionDate() {
			return DestructionDate;
		}

		/**
		 * Element
		 * @param 
		 */
		@SEDA(Position=4,Type=SEDAType.ELEMENT,PropertyTerm="DestructionDate",Cardinality="1..1")
		public void setDestructionDate(DateTimeType destructionDate) {
			DestructionDate = destructionDate;
		}

		/**
		 * Element
		 * @param 
		 */
		@SEDA(Position=5,Type=SEDAType.ELEMENT,PropertyTerm="DestructionNotificationIdentifier",Cardinality="1..1")
		public ArchivesIDType getDestructionNotificationIdentifier() {
			return DestructionNotificationIdentifier;
		}

		/**
		 * Element
		 * @param 
		 */
		@SEDA(Position=5,Type=SEDAType.ELEMENT,PropertyTerm="DestructionNotificationIdentifier",Cardinality="1..1")
		public void setDestructionNotificationIdentifier(
				ArchivesIDType destructionNotificationIdentifier) {
			DestructionNotificationIdentifier = destructionNotificationIdentifier;
		}

		/**
		 * Element
		 * @param 
		 */
		@SEDA(Position=6,Type=SEDAType.ELEMENT,PropertyTerm="DestructionRequestIdentifier",Cardinality="1..1")
		public ArchivesIDType getDestructionRequestIdentifier() {
			return DestructionRequestIdentifier;
		}

		/**
		 * Element
		 * @param 
		 */
		@SEDA(Position=6,Type=SEDAType.ELEMENT,PropertyTerm="DestructionRequestIdentifier",Cardinality="1..1")
		public void setDestructionRequestIdentifier(
				ArchivesIDType destructionRequestIdentifier) {
			DestructionRequestIdentifier = destructionRequestIdentifier;
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
		
		
		@SEDA(Position=11,Type=SEDAType.ATTRIBUTE,PropertyTerm="Id",Cardinality="0..1")
		public String getId() {
			return ID;
		}

		@SEDA(Position=11,Type=SEDAType.ATTRIBUTE,PropertyTerm="Id",Cardinality="0..1")
		public void setId(String iD) {
			ID = iD;
		}
}
