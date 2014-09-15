package eu.akka.saem.alfresco.seda.v02;


import java.util.ArrayList;
import java.util.List;

import eu.akka.saem.alfresco.seda.annotations.SEDA;
import eu.akka.saem.alfresco.seda.annotations.SEDAType;
import eu.akka.saem.alfresco.seda.v02.complexType.ArchiveType;
import eu.akka.saem.alfresco.seda.v02.complexType.ArchivesIDType;
import eu.akka.saem.alfresco.seda.v02.complexType.CodeReplyType;
import eu.akka.saem.alfresco.seda.v02.complexType.DateTimeType;
import eu.akka.saem.alfresco.seda.v02.complexType.OrganizationType;
import eu.akka.saem.alfresco.seda.v02.complexType.SignatureType;

/**
 * Accord pour la destruction d'archives
 * @author benjamin.catinot
 */
@SEDA(PropertyTerm="ArchiveDestructionAcceptance")
public class ArchiveDestructionAcceptanceType {

		@SuppressWarnings("unused")
		private static final long serialVersionUID = 8106741656939374933L;

		private String ID;
		private String xmlns = "fr:gouv:ae:archive:draft:standard_echange_v0.2";
		private String Comment;
		private DateTimeType Date;
		private ArchivesIDType DestructionAcceptanceIdentifier;
		private ArchivesIDType DestructionRequestIdentifier;
		private CodeReplyType ReplyCode; 
		private List<ArchivesIDType>UnitIdentifier;
		private List<OrganizationType> ControlAuthority;
		private OrganizationType OriginatingAgency;
		private OrganizationType ArchivalAgency;
		private SignatureType NonRepudiation;
		private List<ArchiveType> Archive;
		
		@SEDA(Position=0,Type=SEDAType.ATTRIBUTE,PropertyTerm="xmlns",Cardinality="1..1")
		public String getXmlns() {
			return xmlns;
		}

		public ArchiveDestructionAcceptanceType(){
			Archive = new ArrayList<ArchiveType>();
			UnitIdentifier = new ArrayList<ArchivesIDType>();
			ControlAuthority = new ArrayList<OrganizationType>();	
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
		 * @param Identification de l'accord pour élimination
		 */
		@SEDA(Position=3,Type=SEDAType.ELEMENT,PropertyTerm="DestructionAcceptanceIdentifier",Cardinality="1..1")
		public ArchivesIDType getDestructionAcceptanceIdentifier() {
			return DestructionAcceptanceIdentifier;
		}

		/**
		 * Element
		 * @param Identification de l'accord pour élimination
		 */
		@SEDA(Position=3,Type=SEDAType.ELEMENT,PropertyTerm="DestructionAcceptanceIdentifier",Cardinality="1..1")
		public void setDestructionAcceptanceIdentifier(
				ArchivesIDType destructionAcceptanceIdentifier) {
			DestructionAcceptanceIdentifier = destructionAcceptanceIdentifier;
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
		 * @param Tout identifiant permettant de reconnaître le contenu demandé
		 */
		@SEDA(Position=6,Type=SEDAType.ELEMENT,PropertyTerm="UnitIdentifier",Cardinality="1..n")
		public void setUnitIdentifier(List<ArchivesIDType> unitIdentifier) {
			UnitIdentifier = unitIdentifier;
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
		 * @param ervices de Contrôle
		 */
		@SEDA(Position=8,Type=SEDAType.ELEMENT,PropertyTerm="ControlAuthority",Cardinality="0..n")
		public List<OrganizationType> getControlAuthority() {
			return ControlAuthority;
		}

		/**
		 * Element
		 * @param ervices de Contrôle
		 */
		@SEDA(Position=8,Type=SEDAType.ELEMENT,PropertyTerm="ControlAuthority",Cardinality="0..n")
		public void setControlAuthority(List<OrganizationType> controlAuthority) {
			ControlAuthority = controlAuthority;
		}

		/**
		 * Element
		 * @param ervices de Contrôle
		 */
		@SEDA(Position=8,Type=SEDAType.ELEMENT,PropertyTerm="ControlAuthority",Cardinality="0..n")
		public void setControlAuthority(OrganizationType controlAuthority) {
			ControlAuthority.add(controlAuthority);
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
		 * @param 
		 */
		@SEDA(Position=11,Type=SEDAType.ELEMENT,PropertyTerm="NonRepudiation",Cardinality="0..1")
		public SignatureType getNonRepudiation() {
			return NonRepudiation;
		}

		/**
		 * Element
		 * @param 
		 */
		@SEDA(Position=11,Type=SEDAType.ELEMENT,PropertyTerm="NonRepudiation",Cardinality="0..1")
		public void setNonRepudiation(SignatureType nonRepudiation) {
			NonRepudiation = nonRepudiation;
		}

		/**
		 * Element
		 * @param 
		 */
		@SEDA(Position=12,Type=SEDAType.ELEMENT,PropertyTerm="Archive",Cardinality="1..n")
		public List<ArchiveType> getArchive() {
			return Archive;
		}

		/**
		 * Element
		 * @param 
		 */
		@SEDA(Position=12,Type=SEDAType.ELEMENT,PropertyTerm="Archive",Cardinality="1..n")
		public void setArchive(List<ArchiveType> archive) {
			Archive = archive;
		}
		
		/**
		 * Element
		 * @param 
		 */
		@SEDA(Position=12,Type=SEDAType.ELEMENT,PropertyTerm="Archive",Cardinality="1..n")
		public void addArchive(ArchiveType archive) {
			Archive.add(archive);
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
