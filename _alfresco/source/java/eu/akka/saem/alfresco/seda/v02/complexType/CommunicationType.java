package eu.akka.saem.alfresco.seda.v02.complexType;

import eu.akka.saem.alfresco.seda.annotations.SEDA;
import eu.akka.saem.alfresco.seda.annotations.SEDAType;
import eu.akka.saem.alfresco.seda.form.FieldType;

/**
 * Moyens de communication des personnes et/ou organisations (téléphone, fax, e-mail, etc.) sauf courrier postal.
 * @author benjamin.catinot
 *
 */
public class CommunicationType {
	private ArchivesCodeType Channel;
	private String CompleteNumber;
	private ArchivesIDType URI;
	
	/**
	 * Element
	 * @return Un numéro à composer complet.
	 */
	@SEDA(Position=1,Type=SEDAType.ELEMENT,PropertyTerm="CompleteNumber",FormType=FieldType.TEXT,Cardinality="0..1",FormName="Numéro",FormDescription="Un numéro à composer complet")
	public String getCompleteNumber() {
		return CompleteNumber;
	}
	
	/**
	 * Element
	 * @param Un numéro à composer complet.
	 */
	@SEDA(Position=1,Type=SEDAType.ELEMENT,PropertyTerm="CompleteNumber",FormType=FieldType.TEXT,Cardinality="0..1",FormName="Numéro",FormDescription="Un numéro à composer complet")
	public void setCompleteNumber(String completeNumber) {
		CompleteNumber = completeNumber;
	}
	
	/**
	 * Element
	 * @return Un identifiant URI (Uniform Resource Identifier), terme générique pour tous les types de noms et d'adresses qui réfèrent des objets sur le Word Wide Web (e. e-mail, URL, etc.).
	 */
	@SEDA(Position=2,Type=SEDAType.ELEMENT,PropertyTerm="URI",Cardinality="0..1",FormName="URI",FormDescription="Un identifiant URI (Uniform Resource Identifier), terme générique pour tous les types de noms et d'adresses qui réfèrent des objets sur le Word Wide Web (e. e-mail, URL, etc.).")
	public ArchivesIDType getURI() {
		return URI;
	}
	
	/**
	 * Element
	 * @param Un identifiant URI (Uniform Resource Identifier), terme générique pour tous les types de noms et d'adresses qui réfèrent des objets sur le Word Wide Web (e. e-mail, URL, etc.).
	 */
	@SEDA(Position=2,Type=SEDAType.ELEMENT,PropertyTerm="URI",Cardinality="0..1",FormName="URI",FormDescription="Un identifiant URI (Uniform Resource Identifier), terme générique pour tous les types de noms et d'adresses qui réfèrent des objets sur le Word Wide Web (e. e-mail, URL, etc.).")
	public void setURI(ArchivesIDType uRI) {
		URI = uRI;
	}	
	
	
	/**
	 * Element
	 * @return Un code spécifiant le canal ou la manière dont s'établit la communication (téléphone, e-mail, etc.).
	 */
	@SEDA(Position=0,Type=SEDAType.ELEMENT,PropertyTerm="Channel",Cardinality="0..1",FormName="Type/Outil de communication",FormDescription="Un code spécifiant le canal ou la manière dont s'établit la communication (téléphone, e-mail, etc.).")
	public ArchivesCodeType getChannel() {
		return Channel;
	}
	
	/**
	 * Element
	 * @param Un code spécifiant le canal ou la manière dont s'établit la communication (téléphone, e-mail, etc.).
	 */
	@SEDA(Position=0,Type=SEDAType.ELEMENT,PropertyTerm="Channel",Cardinality="0..1",FormName="Type/Outil de communication",FormDescription="Un code spécifiant le canal ou la manière dont s'établit la communication (téléphone, e-mail, etc.).")
	public void setChannel(ArchivesCodeType channel) {
		Channel = channel;
	}

}
