package eu.akka.saem.alfresco.seda.v02.complexType;

import eu.akka.saem.alfresco.seda.annotations.SEDA;
import eu.akka.saem.alfresco.seda.annotations.SEDAType;
import eu.akka.saem.alfresco.seda.v02.simpleType.ReplyCodeType;

/**
 * Code retour du traitement (type d'erreur ou ok).
 * @author benjamin.catinot
 *
 */
public class CodeReplyType {
	private String listVersionID;
	private ReplyCodeType value;

	@SEDA(Position=0,Type=SEDAType.VALUE,PropertyTerm="value")
	public ReplyCodeType getValue() {
		return value;
	}

	@SEDA(Position=0,Type=SEDAType.VALUE,PropertyTerm="value")
	public void setValue(ReplyCodeType value) {
		this.value = value;
	}

	/**
	 * Attribut
	 * @return The version of the code list.
	 */
	@SEDA(Position=1,Type=SEDAType.ATTRIBUTE,PropertyTerm="listVersionID")
	public String getListVersionID() {
		return listVersionID;
	}

	/**
	 * Attribut
	 * @param The version of the code list.
	 */
	@SEDA(Position=1,Type=SEDAType.ATTRIBUTE,PropertyTerm="listVersionID")
	public void setListVersionID(String listVersionID) {
		this.listVersionID = listVersionID;
	}
	
}
