package eu.akka.saem.alfresco.seda.v02.complexType;

import eu.akka.saem.alfresco.seda.annotations.SEDA;
import eu.akka.saem.alfresco.seda.annotations.SEDAType;
import eu.akka.saem.alfresco.seda.form.FieldType;

/**
 * A set of finite-length sequences of binary octets.
 * @author benjamin.catinot
 *
 */
public class ArchivesHashcodeBinaryObjectType {
	private String algorithme;
	private String value;

	/**
	 * Attribut
	 * @return Algorithme utilise pour encoder la valeur.
	 */
	@SEDA(Position=1,Type=SEDAType.ATTRIBUTE,PropertyTerm="algorithme",FormType=FieldType.TEXT)
	public String getAlgorithme() {
		return algorithme;
	}

	/**
	 * Attribut
	 * @param Algorithme utilise pour encoder la valeur.
	 */
	@SEDA(Position=1,Type=SEDAType.ATTRIBUTE,PropertyTerm="algorithme",FormType=FieldType.TEXT)
	public void setAlgorithme(String algorithme) {
		this.algorithme = algorithme;
	}

	@SEDA(Position=2,Type=SEDAType.VALUE,PropertyTerm="value",FormType=FieldType.TEXT)
	public String getValue() {
		return value;
	}

	@SEDA(Position=2,Type=SEDAType.VALUE,PropertyTerm="value",FormType=FieldType.TEXT)
	public void setValue(String value) {
		this.value = value;
	}
	
}
