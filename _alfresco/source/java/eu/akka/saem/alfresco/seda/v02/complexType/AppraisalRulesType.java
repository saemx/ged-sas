package eu.akka.saem.alfresco.seda.v02.complexType;

import eu.akka.saem.alfresco.seda.annotations.SEDA;
import eu.akka.saem.alfresco.seda.annotations.SEDAType;

/**
 * Règle à appliquer en matière d'accès ou de conservation de l'archive ou l'objet d'archive.
 * @author benjamin.catinot
 *
 */
public class AppraisalRulesType {
	
	private CodeAppraisalType Code;
	private ArchivesDurationType Duration;
	private DateType StartDate;
	
	/**
	 * Element
	 * @return Indique la règle à appliquer
	 */
	@SEDA(Position=0,Type=SEDAType.ELEMENT,PropertyTerm="Code",Cardinality="1..1",FormName="Code",FormDescription="Indique la règle à appliquer")
	public CodeAppraisalType getCode() {
		return Code;
	}
	
	/**
	 * Element
	 * @param Indique la règle à appliquer
	 */
	@SEDA(Position=0,Type=SEDAType.ELEMENT,PropertyTerm="Code",Cardinality="1..1",FormName="Code",FormDescription="Indique la règle à appliquer")
	public void setCode(CodeAppraisalType code) {
		Code = code;
	}
	
	/**
	 * Element
	 * @return Indique la durée à appliquer
	 */
	@SEDA(Position=1,Type=SEDAType.ELEMENT,PropertyTerm="Duration",Cardinality="1..1",FormName="Durée d'utilité administrative",FormDescription="Indique la durée à appliquer")
	public ArchivesDurationType getDuration() {
		return Duration;
	}
	
	/**
	 * Element
	 * @param Indique la durée à appliquer
	 */
	@SEDA(Position=1,Type=SEDAType.ELEMENT,PropertyTerm="Duration",Cardinality="1..1",FormName="Durée d'utilité administrative",FormDescription="Indique la durée à appliquer")
	public void setDuration(ArchivesDurationType duration) {
		Duration = duration;
	}
	
	/**
	 * Element
	 * @return Date de départ du calcul
	 */
	@SEDA(Position=2,Type=SEDAType.ELEMENT,PropertyTerm="StartDate",Cardinality="1..1",FormName="Date de départ du calcul",FormDescription="Date de départ du calcul")
	public DateType getStartDate() {
		return StartDate;
	}
	
	/**
	 * Element
	 * @param Date de départ du calcul
	 */
	@SEDA(Position=2,Type=SEDAType.ELEMENT,PropertyTerm="StartDate",Cardinality="0..1",FormName="Date de départ du calcul",FormDescription="Date de départ du calcul")
	public void setStartDate(DateType startDate) {
		StartDate = startDate;
	}
	

}
