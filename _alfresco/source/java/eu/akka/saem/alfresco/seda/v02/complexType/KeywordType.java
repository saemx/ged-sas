package eu.akka.saem.alfresco.seda.v02.complexType;

import eu.akka.saem.alfresco.seda.annotations.SEDA;
import eu.akka.saem.alfresco.seda.annotations.SEDAType;
import eu.akka.saem.alfresco.seda.form.FieldType;

/**
 * Mot-clé associé à une archive ou un objet d'archive.
 * @author benjamin.catinot
 *
 */
public class KeywordType {
	private AccessRestrictionRulesType AccessRestriction;
	private String KeywordContent;
	private ArchivesIDType KeywordReference;
	private CodeKeywordType KeywordType;
	
	/**
	 * Element
	 * @return Permet d'indiquer le caractère confidentiel d'un mot-clé
	 */
	@SEDA(Position=0,Type=SEDAType.ELEMENT,PropertyTerm="AccessRestriction",Cardinality="0..1",FormName="Régle de restriction d'accès",FormDescription="Permet d'indiquer le caractère confidentiel d'un mot-clé")
	public AccessRestrictionRulesType getAccessRestriction() {
		return AccessRestriction;
	}
	
	/**
	 * Element
	 * @param Permet d'indiquer le caractère confidentiel d'un mot-clé
	 */
	@SEDA(Position=0,Type=SEDAType.ELEMENT,PropertyTerm="AccessRestriction",Cardinality="0..1",FormName="Régle de restriction d'accès",FormDescription="Permet d'indiquer le caractère confidentiel d'un mot-clé")
	public void setAccessRestriction(AccessRestrictionRulesType accessRestriction) {
		AccessRestriction = accessRestriction;
	}
	
	/**
	 * Element
	 * @return Valeur du mot-clé.
	 */
	@SEDA(Position=1,Type=SEDAType.ELEMENT,PropertyTerm="KeywordContent",Cardinality="1..1",FormType=FieldType.TEXT,FormName="Mot-clé",FormDescription="Permet d'indiquer le caractère confidentiel d'un mot-clé")
	public String getKeywordContent() {
		return KeywordContent;
	}
	
	/**
	 * Element
	 * @param Valeur du mot-clé.
	 */
	@SEDA(Position=1,Type=SEDAType.ELEMENT,PropertyTerm="KeywordContent",Cardinality="1..1",FormType=FieldType.TEXT,FormName="Mot-clé",FormDescription="Permet d'indiquer le caractère confidentiel d'un mot-clé")
	public void setKeywordContent(String keywordContent) {
		KeywordContent = keywordContent;
	}
	
	/**
	 * Element
	 * @return Indique, s'il en a, l'identifiant du mot clé dans une liste déposée, par exemple pour un lieu son Code Officiel Géographique selon l'INSEE.
	 */
	@SEDA(Position=2,Type=SEDAType.ELEMENT,PropertyTerm="KeywordReference",Cardinality="0..1",FormName="Identifiant dans le référentiel associé",FormDescription="Indique, s'il en a, l'identifiant du mot clé dans une liste déposée, par exemple pour un lieu son Code Officiel Géographique selon l'INSEE.")
	public ArchivesIDType getKeywordReference() {
		return KeywordReference;
	}
	
	/**
	 * Element
	 * @param Indique, s'il en a, l'identifiant du mot clé dans une liste déposée, par exemple pour un lieu son Code Officiel Géographique selon l'INSEE.
	 */
	@SEDA(Position=2,Type=SEDAType.ELEMENT,PropertyTerm="KeywordReference",Cardinality="0..1",FormName="Identifiant dans le référentiel associé",FormDescription="Indique, s'il en a, l'identifiant du mot clé dans une liste déposée, par exemple pour un lieu son Code Officiel Géographique selon l'INSEE.")
	public void setKeywordReference(ArchivesIDType keywordReference) {
		KeywordReference = keywordReference;
	}
	
	/**
	 * Element
	 * @return Type de mot clé.
	 */
	@SEDA(Position=3,Type=SEDAType.ELEMENT,PropertyTerm="KeywordType",Cardinality="0..1",FormName="Type",FormDescription="Type de mot clé.")
	public CodeKeywordType getKeywordType() {
		return KeywordType;
	}
	
	/**
	 * Element
	 * @param Type de mot clé.
	 */
	@SEDA(Position=3,Type=SEDAType.ELEMENT,PropertyTerm="KeywordType",Cardinality="0..1",FormName="Type",FormDescription="Type de mot clé.")
	public void setKeywordType(CodeKeywordType keywordType) {
		KeywordType = keywordType;
	}
	
}
