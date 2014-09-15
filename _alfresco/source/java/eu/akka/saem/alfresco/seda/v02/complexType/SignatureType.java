package eu.akka.saem.alfresco.seda.v02.complexType;

import eu.akka.saem.alfresco.seda.annotations.SEDA;
import eu.akka.saem.alfresco.seda.annotations.SEDAType;


/**
 * Informations de signature du contenu. Cette signature peut être binaire (PKCS7) ou XML (DSIG ou XADES).
 * @author benjamin.catinot
 *
 */
public class SignatureType {
	private ArchivesBinaryObjectType PKCS7Signature;
	private SignatureType XMLSignature;
	/**
	 * Element
	 * @return Signature Binaire de type PKCS7.
	 */
	@SEDA(Position=0,Type=SEDAType.ELEMENT,PropertyTerm="PKCS7Signature",Cardinality="1..1")
	public ArchivesBinaryObjectType getPKCS7Signature() {
		return PKCS7Signature;
	}
	
	/**
	 * Element
	 * @param Signature Binaire de type PKCS7.
	 */
	@SEDA(Position=0,Type=SEDAType.ELEMENT,PropertyTerm="PKCS7Signature",Cardinality="1..1")
	public void setPKCS7Signature(ArchivesBinaryObjectType pKCS7Signature) {
		PKCS7Signature = pKCS7Signature;
	}
	
	/**
	 * Element
	 * @return Signature XML de type XMLDsig ou XADES
	 */
	@SEDA(Position=1,Type=SEDAType.ELEMENT,PropertyTerm="XMLSignature",Cardinality="1..1")
	public SignatureType getXMLSignature() {
		return XMLSignature;
	}
	
	/**
	 * Element
	 * @param Signature Binaire de type PKCS7.
	 */
	@SEDA(Position=1,Type=SEDAType.ELEMENT,PropertyTerm="XMLSignature",Cardinality="1..1")
	public void setXMLSignature(SignatureType xMLSignature) {
		XMLSignature = xMLSignature;
	}
}
