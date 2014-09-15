package eu.akka.saem.alfresco.seda.v02.complexType;

import eu.akka.saem.alfresco.seda.annotations.SEDA;
import eu.akka.saem.alfresco.seda.annotations.SEDAType;
/**
 * Règle à appliquer en matière d'accès ou de conservation de l'archive ou l'objet d'archive.
 * @author benjamin.catinot
 *
 */
public class AccessRestrictionRulesType {
	
	
	private CodeAccessRestrictionType Code;
	private DateType StartDate;
	
	/**
	 * Element
	 * @return Indique la règle à appliquer
	 */
	@SEDA(Position=0,Type=SEDAType.ELEMENT,PropertyTerm="Code",Cardinality="1..1",FormName="Code",FormDescription="Indique la règle à appliquer")
	public CodeAccessRestrictionType getCode() {
		return Code;
	}
	
	/**
	 * Element
	 * @param Indique la règle à appliquer
	 */
	@SEDA(Position=0,Type=SEDAType.ELEMENT,PropertyTerm="Code",Cardinality="1..1",FormName="Code",FormDescription="Indique la règle à appliquer")
	public void setCode(CodeAccessRestrictionType code) {
		Code = code;
	}
	
	/**
	 * Element
	 * @return Date de départ du calcul
	 */
	@SEDA(Position=1,Type=SEDAType.ELEMENT,PropertyTerm="StartDate",Cardinality="1..1",FormName="Date de départ du calcul",FormDescription="Date de départ du calcul")
	public DateType getStartDate() {
		return StartDate;
	}
	
	/**
	 * Element
	 * @param Date de départ du calcul
	 */
	@SEDA(Position=1,Type=SEDAType.ELEMENT,PropertyTerm="StartDate",Cardinality="1..1",FormName="Date de départ du calcul",FormDescription="Date de départ du calcul")
	public void setStartDate(DateType startDate) {
		StartDate = startDate;
	}
	
}
