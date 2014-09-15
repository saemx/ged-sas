package eu.akka.saem.alfresco.seda.v02.complexType;


import eu.akka.saem.alfresco.seda.annotations.SEDA;
import eu.akka.saem.alfresco.seda.annotations.SEDAType;

/**
 * Empreinte associée à un fichier ou une partie de fichier aux fins d'en contrôler l'intégrité. Le mécanisme de description du HashCode reprend le mécanisme de référence décrit dans XMLDsig
 * @author benjamin.catinot
 *
 */
public class HashCodeType {
	
	private ArchivesHashcodeBinaryObjectType Contains;
	private ArchivesIDType UnitIdentifier;
	
	/**
	 * Element
	 * @return Empreinte d'un élément de l'archive.
	 */
	@SEDA(Position=0,Type=SEDAType.ELEMENT,PropertyTerm="Contains",Cardinality="1..1")
	public ArchivesHashcodeBinaryObjectType getContains() {
		return Contains;
	}
	
	/**
	 * Element
	 * @param Empreinte d'un élément de l'archive.
	 */
	@SEDA(Position=0,Type=SEDAType.ELEMENT,PropertyTerm="Contains",Cardinality="1..1")
	public void setContains(ArchivesHashcodeBinaryObjectType contains) {
		Contains = contains;
	}
	
	/**
	 * Element
	 * @return Identifiant permettant de reconnaître l'élément dont l'empreinte est founie, qui peut être une archive, ou tout autre objet qui la compose.
	 */
	@SEDA(Position=1,Type=SEDAType.ELEMENT,PropertyTerm="UnitIdentifier",Cardinality="1..1")
	public ArchivesIDType getUnitIdentifier() {
		return UnitIdentifier;
	}
	
	/**
	 * Element
	 * @param Identifiant permettant de reconnaître l'élément dont l'empreinte est founie, qui peut être une archive, ou tout autre objet qui la compose.
	 */
	@SEDA(Position=1,Type=SEDAType.ELEMENT,PropertyTerm="UnitIdentifier",Cardinality="1..1")
	public void setUnitIdentifier(ArchivesIDType unitIdentifier) {
		UnitIdentifier = unitIdentifier;
	}
	
}
