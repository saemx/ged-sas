package eu.akka.saem.alfresco.seda.v02.complexType;

import java.util.ArrayList;
import java.util.List;

import eu.akka.saem.alfresco.seda.annotations.SEDA;
import eu.akka.saem.alfresco.seda.annotations.SEDAType;
import eu.akka.saem.alfresco.seda.form.FieldType;

/**
 * Informations sur une structure organisée constituée pour un objet particulier, comme une administration, une entreprise ou une association. Par extension il peut s'agir d'une personne physique.
 * @author benjamin.catinot
 *
 */
public class OrganizationType {
	private ArchivesCodeType BusinessType;
	private String Description;
	private ArchivesIDType Identification;
	private ArchivesCodeType LegalClassification;
	private String Name;
	private List<ContactType> Contact;
	private List<AddressType> Address;
	private List<CommunicationType> Communication;
	
	public OrganizationType(){
		
	}
	
	/**
	 * Element
	 * @return Code définissant la nature de l'activité de l'organisation. UN00000057 Organisation.Business Type.Code Répertoire SIRENE: Code APE (APEN ou APET suivant le niveau)
	 */
	@SEDA(Position=1,Type=SEDAType.ELEMENT,PropertyTerm="BusinessType",Cardinality="0..1",FormName="Code de l'activité",FormDescription="Code définissant la nature de l'activité de l'organisation. UN00000057 Organisation.Business Type.Code Répertoire SIRENE: Code APE (APEN ou APET suivant le niveau)")
	public ArchivesCodeType getBusinessType() {
		return BusinessType;
	}
	
	/**
	 * Element
	 * @param Code définissant la nature de l'activité de l'organisation. UN00000057 Organisation.Business Type.Code Répertoire SIRENE: Code APE (APEN ou APET suivant le niveau)
	 */
	@SEDA(Position=1,Type=SEDAType.ELEMENT,PropertyTerm="BusinessType",Cardinality="0..1",FormName="Code de l'activité",FormDescription="Code définissant la nature de l'activité de l'organisation. UN00000057 Organisation.Business Type.Code Répertoire SIRENE: Code APE (APEN ou APET suivant le niveau)")
	public void setBusinessType(ArchivesCodeType businessType) {
		BusinessType = businessType;
	}
	
	/**
	 * Element
	 * @return Description textuelle de l'organisation
	 */
	@SEDA(Position=2,Type=SEDAType.ELEMENT,PropertyTerm="Description",Cardinality="0..1",FormName="Description",FormDescription="Description textuelle de l'organisation",FormType=FieldType.TEXTEAREA)
	public String getDescription() {
		return Description;
	}
	
	/**
	 * Element
	 * @param Description textuelle de l'organisation
	 */
	@SEDA(Position=2,Type=SEDAType.ELEMENT,PropertyTerm="Description",Cardinality="0..1",FormName="Description",FormDescription="Description textuelle de l'organisation",FormType=FieldType.TEXTEAREA)
	public void setDescription(String description) {
		Description = description;
	}
	
	/**
	 * Element
	 * @return Identifiant unique de l'organisation. UN00000053 Organisation.Identification.Identifier.Par exemple dans le Répertoire SIRENE: SIREN ou SIRET suivant le niveau.
	 */
	@SEDA(Position=3,Type=SEDAType.ELEMENT,PropertyTerm="Identification",Cardinality="1..1",FormName="Identifiant",FormDescription="Identifiant unique de l'organisation. UN00000053 Organisation.Identification.Identifier.Par exemple dans le Répertoire SIRENE: SIREN ou SIRET suivant le niveau.")
	public ArchivesIDType getIdentification() {
		return Identification;
	}
	
	/**
	 * Element
	 * @param Identifiant unique de l'organisation. UN00000053 Organisation.Identification.Identifier.Par exemple dans le Répertoire SIRENE: SIREN ou SIRET suivant le niveau.
	 */
	@SEDA(Position=3,Type=SEDAType.ELEMENT,PropertyTerm="Identification",Cardinality="1..1",FormName="Identifiant",FormDescription="Identifiant unique de l'organisation. UN00000053 Organisation.Identification.Identifier.Par exemple dans le Répertoire SIRENE: SIREN ou SIRET suivant le niveau.")
	public void setIdentification(ArchivesIDType identification) {
		Identification = identification;
	}
	
	/**
	 * Element
	 * @return Code définissant la catégorie juridique de l'organisation. UN00000056 Organisation.Legal Classification.Code. Répertoire SIRENE: personne morale ou pour une entreprise individuelle, la catégorie professionnelle.
	 */
	@SEDA(Position=4,Type=SEDAType.ELEMENT,PropertyTerm="LegalClassification",Cardinality="0..1",FormName="Code de la catégorie juridique",FormDescription="Code définissant la catégorie juridique de l'organisation. UN00000056 Organisation.Legal Classification.Code. Répertoire SIRENE: personne morale ou pour une entreprise individuelle, la catégorie professionnelle.")
	public ArchivesCodeType getLegalClassification() {
		return LegalClassification;
	}
	
	/**
	 * Element
	 * @param Code définissant la catégorie juridique de l'organisation. UN00000056 Organisation.Legal Classification.Code. Répertoire SIRENE: personne morale ou pour une entreprise individuelle, la catégorie professionnelle.
	 */
	@SEDA(Position=4,Type=SEDAType.ELEMENT,PropertyTerm="LegalClassification",Cardinality="0..1",FormName="Code de la catégorie juridique",FormDescription="Code définissant la catégorie juridique de l'organisation. UN00000056 Organisation.Legal Classification.Code. Répertoire SIRENE: personne morale ou pour une entreprise individuelle, la catégorie professionnelle.")
	public void setLegalClassification(ArchivesCodeType legalClassification) {
		LegalClassification = legalClassification;
	}
	
	/**
	 * Element
	 * @return Dénomination ou enseigne: Nom sous lequel l'organisation exerce son activité.Ex. Service d'état civil.UN000000054 Organisation.Name.Text. Répertoire SIRENE.
	 */
	@SEDA(Position=5,Type=SEDAType.ELEMENT,PropertyTerm="Name",Cardinality="0..1",FormName="Nom",FormDescription="Dénomination ou enseigne: Nom sous lequel l'organisation exerce son activité.Ex. Service d'état civil.UN000000054 Organisation.Name.Text. Répertoire SIRENE.",FormType=FieldType.TEXT)
	public String getName() {
		return Name;
	}
	
	/**
	 * Element
	 * @param Dénomination ou enseigne: Nom sous lequel l'organisation exerce son activité.Ex. Service d'état civil.UN000000054 Organisation.Name.Text. Répertoire SIRENE.
	 */
	@SEDA(Position=5,Type=SEDAType.ELEMENT,PropertyTerm="Name",Cardinality="0..1",FormName="Nom",FormDescription="Dénomination ou enseigne: Nom sous lequel l'organisation exerce son activité.Ex. Service d'état civil.UN000000054 Organisation.Name.Text. Répertoire SIRENE.",FormType=FieldType.TEXT)
	public void setName(String name) {
		Name = name;
	}
	
	/**
	 * Element
	 * @return Contacts identifiés de l'organisation
	 */
	@SEDA(Position=6,Type=SEDAType.ELEMENT,PropertyTerm="Contact",Cardinality="0..n",FormName="Contact",FormDescription="Contacts identifiés de l'organisation")
	public List<ContactType> getContact() {
		return Contact;
	}
	
	
	/**
	 * Element
	 * @param Contacts identifiés de l'organisation
	 */
	@SEDA(Position=6,Type=SEDAType.ELEMENT,PropertyTerm="Contact",Cardinality="0..n",FormName="Contact",FormDescription="Contacts identifiés de l'organisation")
	public void setContact(List<ContactType> contact) {
		Contact = contact;
	}
	
	/**
	 * Element
	 * @param Contacts identifiés de l'organisation
	 */
	@SEDA(Position=6,Type=SEDAType.ELEMENT,PropertyTerm="Contact",Cardinality="0..n",FormName="Contact",FormDescription="Contacts identifiés de l'organisation")
	public void addContact(ContactType contact) {
		if(Contact == null)
			Contact = new ArrayList<ContactType>();
		
		Contact.add(contact);
	}
	
	/**
	 * Element
	 * @return Adresses de l'organisation.
	 */
	@SEDA(Position=7,Type=SEDAType.ELEMENT,PropertyTerm="Address",Cardinality="0..n",FormName="Adresse",FormDescription="Adresses de l'organisation.")
	public List<AddressType> getAddress() {
		return Address;
	}
	
	/**
	 * Element
	 * @param Adresses de l'organisation.
	 */
	@SEDA(Position=7,Type=SEDAType.ELEMENT,PropertyTerm="Address",Cardinality="0..n",FormName="Adresse",FormDescription="Adresses de l'organisation.")
	public void setAddress(List<AddressType> address) {
		Address = address;
	}
	
	/**
	 * Element
	 * @param Adresses de l'organisation.
	 */
	@SEDA(Position=7,Type=SEDAType.ELEMENT,PropertyTerm="Address",Cardinality="0..n",FormName="Adresse",FormDescription="Adresses de l'organisation.")
	public void addAddress(AddressType address) {
		if(Address == null)
			Address = new ArrayList<AddressType>();
		
		Address.add(address);
	}
	
	/**
	 * Element
	 * @return Moyen de communication de l'organisation.
	 */
	@SEDA(Position=10,Type=SEDAType.ELEMENT,PropertyTerm="Communication",Cardinality="0..n",FormName="Moyen de communication",FormDescription="Moyen de communication de l'organisation.")
	public List<CommunicationType> getCommunication() {
		return Communication;
	}	
	
	
	/**
	 * Element
	 * @param Moyen de communication de l'organisation.
	 */
	@SEDA(Position=10,Type=SEDAType.ELEMENT,PropertyTerm="Communication",Cardinality="0..n",FormName="Moyen de communication",FormDescription="Moyen de communication de l'organisation.")
	public void setCommunication(List<CommunicationType> communication) {
		Communication = communication;
	}
	
	/**
	 * Element
	 * @param Moyen de communication de l'organisation.
	 */
	@SEDA(Position=10,Type=SEDAType.ELEMENT,PropertyTerm="Communication",Cardinality="0..n",FormName="Moyen de communication",FormDescription="Moyen de communication de l'organisation.")
	public void addCommunication(CommunicationType communication) {
		if(Communication == null)
			Communication = new ArrayList<CommunicationType>();
		
		Communication.add(communication);
	}

}
