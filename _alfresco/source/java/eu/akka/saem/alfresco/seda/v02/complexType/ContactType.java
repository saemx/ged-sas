package eu.akka.saem.alfresco.seda.v02.complexType;

import java.util.ArrayList;
import java.util.List;

import eu.akka.saem.alfresco.seda.annotations.SEDA;
import eu.akka.saem.alfresco.seda.annotations.SEDAType;
import eu.akka.saem.alfresco.seda.form.FieldType;

/**
 * Informations relatives à une personne ou à une organisation qui agit comme point de contact avec une autre personne ou une autre organisation.
 * @author benjamin.catinot
 *
 */
public class ContactType {
	private String DepartmentName;
	private ArchivesIDType Identification;
	private String PersonName;
	private String Responsibility;
	private AddressType Address;
	private List<CommunicationType> Communication;
	
	public ContactType(){
	}
	
	/**
	 * Element
	 * Service auquel appartient le contact dans une organisation (exemple: Support).
	 * @return
	 */
	@SEDA(Position=1,Type=SEDAType.ELEMENT,PropertyTerm="DepartmentName",Cardinality="0..1",FormType=FieldType.TEXT,FormName="Service",FormDescription="Service auquel appartient le contact dans une organisation (exemple: Support).")
	public String getDepartmentName() {
		return DepartmentName;
	}
	
	/**
	 * Element
	 * @param Service auquel appartient le contact dans une organisation (exemple: Support).
	 */
	@SEDA(Position=1,Type=SEDAType.ELEMENT,PropertyTerm="DepartmentName",Cardinality="0..1",FormType=FieldType.TEXT,FormName="Service",FormDescription="Service auquel appartient le contact dans une organisation (exemple: Support).")
	public void setDepartmentName(String departmentName) {
		DepartmentName = departmentName;
	}
	
	/**
	 * Element
	 * @return Identifiant du contact. A titre indicatif, la longueur du champ peut être de 30 caractères.
	 */
	@SEDA(Position=2,Type=SEDAType.ELEMENT,PropertyTerm="Identification",Cardinality="0..1",FormName="Identifiant",FormDescription="Identifiant du contact. A titre indicatif, la longueur du champ peut être de 30 caractères.")
	public ArchivesIDType getIdentification() {
		return Identification;
	}
	
	/**
	 * Element
	 * @param Identifiant du contact. A titre indicatif, la longueur du champ peut être de 30 caractères.
	 */
	@SEDA(Position=2,Type=SEDAType.ELEMENT,PropertyTerm="Identification",Cardinality="0..1",FormName="Identifiant",FormDescription="Identifiant du contact. A titre indicatif, la longueur du champ peut être de 30 caractères.")
	public void setIdentification(ArchivesIDType identification) {
		Identification = identification;
	}
	
	/**
	 * Element
	 * @return Nom de la personne ou du service à contacter.
	 */
	@SEDA(Position=3,Type=SEDAType.ELEMENT,PropertyTerm="PersonName",Cardinality="0..1",FormType=FieldType.TEXT,FormName="Nom",FormDescription="Nom de la personne ou du service à contacter.")
	public String getPersonName() {
		return PersonName;
	}
	
	/**
	 * Element
	 * @param Nom de la personne ou du service à contacter.
	 */
	@SEDA(Position=3,Type=SEDAType.ELEMENT,PropertyTerm="PersonName",Cardinality="0..1",FormType=FieldType.TEXT,FormName="Nom",FormDescription="Nom de la personne ou du service à contacter.")
	public void setPersonName(String personName) {
		PersonName = personName;
	}
	
	/**
	 * Element
	 * @return Description textuelle des responsabilité(s) générales ou spécifiques du contact.
	 */
	@SEDA(Position=4,Type=SEDAType.ELEMENT,PropertyTerm="Responsibility",Cardinality="0..1",FormType=FieldType.TEXT,FormName="Attributions",FormDescription="Description textuelle des responsabilité(s) générales ou spécifiques du contact.")
	public String getResponsibility() {
		return Responsibility;
	}
	
	/**
	 * Element
	 * @param Description textuelle des responsabilité(s) générales ou spécifiques du contact.
	 */
	@SEDA(Position=4,Type=SEDAType.ELEMENT,PropertyTerm="Responsibility",Cardinality="0..1",FormType=FieldType.TEXT,FormName="Attributions",FormDescription="Description textuelle des responsabilité(s) générales ou spécifiques du contact.")
	public void setResponsibility(String responsibility) {
		Responsibility = responsibility;
	}
	
	/**
	 * Element
	 * @return Adressse du contact.
	 */
	@SEDA(Position=5,Type=SEDAType.ELEMENT,PropertyTerm="Address",Cardinality="0..1",FormName="Adressse",FormDescription="Adressse du contact.")
	public AddressType getAddress() {
		return Address;
	}
	
	/**
	 * Element
	 * @param Adressse du contact.
	 */
	@SEDA(Position=5,Type=SEDAType.ELEMENT,PropertyTerm="Address",Cardinality="0..1",FormName="Adressse",FormDescription="Adressse du contact.")
	public void setAddress(AddressType address) {
		Address = address;
	}
	
	/**
	 * Element
	 * @return Moyen de communication du contact.
	 */
	@SEDA(Position=6,Type=SEDAType.ELEMENT,PropertyTerm="Communication",Cardinality="0..n",FormName="Moyen de communication",FormDescription="Moyen de communication du contact.")
	public List<CommunicationType> getCommunication() {
		return Communication;
	}
	
	/**
	 * Element
	 * Moyen de communication du contact.
	 * @param communication
	 */
	@SEDA(Position=6,Type=SEDAType.ELEMENT,PropertyTerm="Communication",Cardinality="0..n",FormName="Moyen de communication",FormDescription="Moyen de communication du contact.")
	public void setCommunication(List<CommunicationType> communication) {
		Communication = communication;
	}
	
	/**
	 * Element
	 * Moyen de communication du contact.
	 * @param communication
	 */
	@SEDA(Position=6,Type=SEDAType.ELEMENT,PropertyTerm="Communication",Cardinality="0..n",FormName="Moyen de communication",FormDescription="Moyen de communication du contact.")
	public void addCommunication(CommunicationType communication) {
		if(Communication == null)
			Communication = new ArrayList<CommunicationType>();
		
		Communication.add(communication);
	}
	
}
