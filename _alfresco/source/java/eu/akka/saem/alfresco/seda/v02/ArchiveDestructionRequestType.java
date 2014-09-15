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
 * Demande d'élimination d'archives
 * @author benjamin.catinot
 */
@SEDA(PropertyTerm="ArchiveDestructionRequest")
public class ArchiveDestructionRequestType {

		@SuppressWarnings("unused")
		private static final long serialVersionUID = 8106741656939374933L;

		private String ID;
		private String xmlns = "fr:gouv:ae:archive:draft:standard_echange_v0.2";
		private String Comment;
		private DateTimeType Date;
		private ArchivesIDType DestructionRequestIdentifier;
		private List<ArchivesIDType>UnitIdentifier;
		private List<OrganizationType> ControlAuthority;
		private OrganizationType OriginatingAgency;
		private OrganizationType ArchivalAgency;
		private List<ArchiveType> Archive;
		private List<HashCodeType> Integrity;
		private SignatureType Authentication;
		
		@SEDA(Position=0,Type=SEDAType.ATTRIBUTE,PropertyTerm="xmlns",Cardinality="1..1")
		public String getXmlns() {
			return xmlns;
		}

		public ArchiveDestructionRequestType(){
			ControlAuthority = new ArrayList<OrganizationType>();
			UnitIdentifier = new ArrayList<ArchivesIDType>();
			setArchive(new ArrayList<ArchiveType>());
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
		 * @param Identifiant de la demande d'élimination ou de la demande d'autorisation d'élimination
		 */
		@SEDA(Position=4,Type=SEDAType.ELEMENT,PropertyTerm="DestructionRequestIdentifier",Cardinality="1..1")
		public ArchivesIDType getDestructionRequestIdentifier() {
			return DestructionRequestIdentifier;
		}

		/**
		 * Element
		 * @param Identifiant de la demande d'élimination ou de la demande d'autorisation d'élimination
		 */
		@SEDA(Position=4,Type=SEDAType.ELEMENT,PropertyTerm="DestructionRequestIdentifier",Cardinality="1..1")
		public void setDestructionRequestIdentifier(
				ArchivesIDType destructionRequestIdentifier) {
			DestructionRequestIdentifier = destructionRequestIdentifier;
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
		@SEDA(Position=6,Type=SEDAType.ELEMENT,PropertyTerm="ControlAuthority",Cardinality="0..n")
		public List<OrganizationType> getControlAuthority() {
			return ControlAuthority;
		}

		/**
		 * Element
		 * @param ervices de Contrôle
		 */
		@SEDA(Position=6,Type=SEDAType.ELEMENT,PropertyTerm="ControlAuthority",Cardinality="0..n")
		public void setControlAuthority(List<OrganizationType> controlAuthority) {
			ControlAuthority = controlAuthority;
		}

		/**
		 * Element
		 * @param ervices de Contrôle
		 */
		@SEDA(Position=6,Type=SEDAType.ELEMENT,PropertyTerm="ControlAuthority",Cardinality="0..n")
		public void setControlAuthority(OrganizationType controlAuthority) {
			ControlAuthority.add(controlAuthority);
		}
		
		
		/**
		 * Element
		 * @param 
		 */
		@SEDA(Position=7,Type=SEDAType.ELEMENT,PropertyTerm="OriginatingAgency",Cardinality="1..1")
		public OrganizationType getOriginatingAgency() {
			return OriginatingAgency;
		}

		/**
		 * Element
		 * @param 
		 */
		@SEDA(Position=7,Type=SEDAType.ELEMENT,PropertyTerm="OriginatingAgency",Cardinality="1..1")
		public void setOriginatingAgency(OrganizationType originatingAgency) {
			OriginatingAgency = originatingAgency;
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

		
		/**
		 * Element
		 * @param 
		 */
		@SEDA(Position=10,Type=SEDAType.ELEMENT,PropertyTerm="Archive",Cardinality="1..n")
		public List<ArchiveType> getArchive() {
			return Archive;
		}

		/**
		 * Element
		 * @param
		 */
		@SEDA(Position=10,Type=SEDAType.ELEMENT,PropertyTerm="Archive",Cardinality="1..n")
		public void setArchive(List<ArchiveType> archive) {
			Archive = archive;
		}
		
		/**
		 * Element
		 * @param
		 */
		@SEDA(Position=10,Type=SEDAType.ELEMENT,PropertyTerm="Archive",Cardinality="1..n")
		public void addArchive(ArchiveType archive) {
			Archive.add(archive);
		}

		/**
		 * Element
		 * @param 
		 */
		@SEDA(Position=11,Type=SEDAType.ELEMENT,PropertyTerm="Integrity",Cardinality="0..n")
		public List<HashCodeType> getIntegrity() {
			return Integrity;
		}

		/**
		 * Element
		 * @param 
		 */
		@SEDA(Position=11,Type=SEDAType.ELEMENT,PropertyTerm="Integrity",Cardinality="0..n")
		public void setIntegrity(List<HashCodeType> integrity) {
			Integrity = integrity;
		}
		
		/**
		 * Element
		 * @param 
		 */
		@SEDA(Position=11,Type=SEDAType.ELEMENT,PropertyTerm="Integrity",Cardinality="0..n")
		public void addIntegrity(HashCodeType integrity) {
			Integrity.add(integrity);
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
