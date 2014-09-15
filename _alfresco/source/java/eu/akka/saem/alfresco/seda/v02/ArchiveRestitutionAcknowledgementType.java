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
 * Réponse à une demande de restitution d'archives
 * @author benjamin.catinot
 */
@SEDA(PropertyTerm="ArchiveRestitutionAcknowledgement")
public class ArchiveRestitutionAcknowledgementType {

		@SuppressWarnings("unused")
		private static final long serialVersionUID = 8106741656939374933L;

		private String ID;
		private String xmlns = "fr:gouv:ae:archive:draft:standard_echange_v0.2";
		private String Comment;
		private DateTimeType Date;
		private CodeReplyType ReplyCode;
		private ArchivesIDType RestitutionAcknowledgementIdentifier;
		private ArchivesIDType RestitutionIdentifier;
		private List<ArchivesIDType> UnitIdentifier;
		private OrganizationType OriginatingAgency;
		private OrganizationType ArchivalAgency;
		private SignatureType NonRepudiation;
		private List<ArchiveType> Archive;
		
		@SEDA(Position=0,Type=SEDAType.ATTRIBUTE,PropertyTerm="xmlns",Cardinality="1..1")
		public String getXmlns() {
			return xmlns;
		}

		public ArchiveRestitutionAcknowledgementType(){
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
		 * @return Identifiant de la demande de restitution
		 */
		@SEDA(Position=4,Type=SEDAType.ELEMENT,PropertyTerm="RestitutionAcknowledgementIdentifier",Cardinality="0..1")
		public ArchivesIDType getRestitutionAcknowledgementIdentifier() {
			return RestitutionAcknowledgementIdentifier;
		}
		
		/**
		 * Element
		 * @param Identifiant de la demande de restitution
		 */
		@SEDA(Position=4,Type=SEDAType.ELEMENT,PropertyTerm="RestitutionAcknowledgementIdentifier",Cardinality="0..1")
		public void setRestitutionAcknowledgementIdentifier(ArchivesIDType restitutionAcknowledgementIdentifier) {
			RestitutionAcknowledgementIdentifier = restitutionAcknowledgementIdentifier;
		}
		
		/**
		 * Element
		 * @param Identifiant de la réponse à la demande de restitution
		 */
		@SEDA(Position=5,Type=SEDAType.ELEMENT,PropertyTerm="RestitutionIdentifier",Cardinality="1..1")
		public ArchivesIDType getRestitutionIdentifier() {
			return RestitutionIdentifier;
		}

		/**
		 * Element
		 * @param Identifiant de la réponse à la demande de restitution
		 */
		@SEDA(Position=5,Type=SEDAType.ELEMENT,PropertyTerm="RestitutionIdentifier",Cardinality="1..1")
		public void setRestitutionIdentifier(
				ArchivesIDType restitutionIdentifier) {
			RestitutionIdentifier = restitutionIdentifier;
		}

		/**
		 * Element
		 * @return Tout identifiant permettant de reconnaître le contenu à restituer
		 */
		@SEDA(Position=6,Type=SEDAType.ELEMENT,PropertyTerm="UnitIdentifier",Cardinality="1..n")
		public List<ArchivesIDType> getUnitIdentifier() {
			return UnitIdentifier;
		}
		
		/**
		 * Element
		 * @param Tout identifiant permettant de reconnaître le contenu à restituer
		 */
		@SEDA(Position=6,Type=SEDAType.ELEMENT,PropertyTerm="UnitIdentifier",Cardinality="1..n")
		public void setUnitIdentifier(List<ArchivesIDType> unitIdentifier) {
			UnitIdentifier = unitIdentifier;
		}
		
		/**
		 * Element
		 * @param Tout identifiant permettant de reconnaître le contenu à restituer
		 */
		@SEDA(Position=6,Type=SEDAType.ELEMENT,PropertyTerm="UnitIdentifier",Cardinality="1..n")
		public void addUnitIdentifier(ArchivesIDType unitIdentifier) {
			UnitIdentifier.add(unitIdentifier);
		}
		
				
		/**
		 * Element
		 * @return Service versant
		 */
		@SEDA(Position=8,Type=SEDAType.ELEMENT,PropertyTerm="OriginatingAgency",Cardinality="1..1")
		public OrganizationType getOriginatingAgency() {
			return OriginatingAgency;
		}
		
		/**
		 * Element
		 * @param Service versant
		 */
		@SEDA(Position=8,Type=SEDAType.ELEMENT,PropertyTerm="OriginatingAgency",Cardinality="1..1")
		public void setOriginatingAgency(OrganizationType originatingAgency) {
			OriginatingAgency = originatingAgency;
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
		

		
		@SEDA(Position=10,Type=SEDAType.ELEMENT,PropertyTerm="NonRepudiation",Cardinality="0..1")
		public SignatureType getNonRepudiation() {
			return NonRepudiation;
		}
		
		@SEDA(Position=10,Type=SEDAType.ELEMENT,PropertyTerm="NonRepudiation",Cardinality="0..1")
		public void setNonRepudiation(SignatureType nonRepudiation) {
			NonRepudiation = nonRepudiation;
		}
		
		@SEDA(Position=11,Type=SEDAType.ELEMENT,PropertyTerm="Archive",Cardinality="1..n")
		public List<ArchiveType> getArchive() {
			return Archive;
		}
		
		@SEDA(Position=11,Type=SEDAType.ELEMENT,PropertyTerm="Archive",Cardinality="1..n")
		public void setArchive(List<ArchiveType> archive) {
			Archive = archive;
		}
		
		@SEDA(Position=11,Type=SEDAType.ELEMENT,PropertyTerm="Archive",Cardinality="1..n")
		public void addArchive(ArchiveType archive) {
			Archive.add(archive);
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
