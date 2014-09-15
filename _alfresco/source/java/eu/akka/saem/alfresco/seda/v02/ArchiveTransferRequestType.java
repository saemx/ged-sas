package eu.akka.saem.alfresco.seda.v02;


import java.util.*;
import eu.akka.saem.alfresco.seda.annotations.SEDA;
import eu.akka.saem.alfresco.seda.annotations.SEDAType;
import eu.akka.saem.alfresco.seda.v02.complexType.ArchiveType;
import eu.akka.saem.alfresco.seda.v02.complexType.ArchivesIDType;
import eu.akka.saem.alfresco.seda.v02.complexType.DateTimeType;
import eu.akka.saem.alfresco.seda.v02.complexType.HashCodeType;
import eu.akka.saem.alfresco.seda.v02.complexType.OrganizationType;

/**
 * 
 * @author benjamin.catinot
 */
@SEDA(PropertyTerm="ArchiveTransferRequest")
public class ArchiveTransferRequestType {

		@SuppressWarnings("unused")
		private static final long serialVersionUID = 8106741656939374933L;

		private String ID;
		private String xmlns = "fr:gouv:ae:archive:draft:standard_echange_v0.2";
		private String Comment;
		private DateTimeType Date;
		private ArchivesIDType RelatedTransferReference;
		private DateTimeType TransferDate;
		private ArchivesIDType TransferRequestIdentifier;
		private OrganizationType TransferringAgency;
		private OrganizationType ArchivalAgency;
		private List<HashCodeType> Integrity;
		private List<ArchiveType> Contains;
		
		@SEDA(Position=0,Type=SEDAType.ATTRIBUTE,PropertyTerm="xmlns",Cardinality="1..1")
		public String getXmlns() {
			return xmlns;
		}

		public ArchiveTransferRequestType(){
			Integrity = new ArrayList<HashCodeType>();
			Contains = new ArrayList<ArchiveType>();			
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
		 * @param Date prévue pour le transfert
		 */
		@SEDA(Position=3,Type=SEDAType.ELEMENT,PropertyTerm="TransferDate",Cardinality="0..1")
		public DateTimeType getTransferDate() {
			return TransferDate;
		}

		/**
		 * Element
		 * @param Date prévue pour le transfert
		 */
		@SEDA(Position=3,Type=SEDAType.ELEMENT,PropertyTerm="TransferDate",Cardinality="0..1")
		public void setTransferDate(DateTimeType transferDate) {
			TransferDate = transferDate;
		}

		/**
		 * Element
		 * @param Identifiant de la demande de transfert
		 */
		@SEDA(Position=4,Type=SEDAType.ELEMENT,PropertyTerm="TransferRequestIdentifier",Cardinality="1..1")
		public ArchivesIDType getTransferRequestIdentifier() {
			return TransferRequestIdentifier;
		}

		/**
		 * Element
		 * @param Identifiant de la demande de transfert
		 */
		@SEDA(Position=4,Type=SEDAType.ELEMENT,PropertyTerm="TransferRequestIdentifier",Cardinality="1..1")
		public void setTransferRequestIdentifier(
				ArchivesIDType transferRequestIdentifier) {
			TransferRequestIdentifier = transferRequestIdentifier;
		}

		/**
		 * Element
		 * @return Référence à un autre transfert
		 */
		@SEDA(Position=5,Type=SEDAType.ELEMENT,PropertyTerm="RelatedTransferReference",Cardinality="0..1")
		public ArchivesIDType getRelatedTransferReference() {
			return RelatedTransferReference;
		}
		
		/**
		 * Element
		 * @param Référence à un autre transfert
		 */
		@SEDA(Position=5,Type=SEDAType.ELEMENT,PropertyTerm="RelatedTransferReference",Cardinality="0..1")
		public void setRelatedTransferReference(ArchivesIDType relatedTransferReference) {
			RelatedTransferReference = relatedTransferReference;
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
		

		@SEDA(Position=8,Type=SEDAType.ELEMENT,PropertyTerm="Integrity",Cardinality="0..n")
		public List<HashCodeType> getIntegrity() {
			return Integrity;
		}
		
		@SEDA(Position=8,Type=SEDAType.ELEMENT,PropertyTerm="Integrity",Cardinality="0..n")
		public void setIntegrity(List<HashCodeType> integrity) {
			Integrity = integrity;
		}
		
		@SEDA(Position=8,Type=SEDAType.ELEMENT,PropertyTerm="Integrity",Cardinality="0..n")
		public void addIntegrity(HashCodeType integrity) {
			Integrity.add(integrity);
		}
			
		@SEDA(Position=10,Type=SEDAType.ELEMENT,PropertyTerm="Contains",Cardinality="1..n")
		public List<ArchiveType> getContains() {
			return Contains;
		}
		
		@SEDA(Position=10,Type=SEDAType.ELEMENT,PropertyTerm="Contains",Cardinality="1..n")
		public void setContains(List<ArchiveType> contains) {
			Contains = contains;
		}
		
		@SEDA(Position=10,Type=SEDAType.ELEMENT,PropertyTerm="Contains",Cardinality="1..n")
		public void addContains(ArchiveType contains) {
			Contains.add(contains);
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
