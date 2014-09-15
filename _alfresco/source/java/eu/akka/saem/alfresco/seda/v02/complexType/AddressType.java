package eu.akka.saem.alfresco.seda.v02.complexType;

import eu.akka.saem.alfresco.seda.annotations.SEDA;
import eu.akka.saem.alfresco.seda.annotations.SEDAType;
import eu.akka.saem.alfresco.seda.form.FieldType;

/**
 * Lieu où une organisation ou une personne peuvent être jointes ou trouvées.
 * @author benjamin.catinot
 *
 */
public class AddressType {
	private String BlockName;
	private String BuildingName;
	private String BuildingNumber;
	private String CityName;
	private String CitySubDivisionName;
	private ArchivesCountryType Country;
	private String FloorIdentification;
	private ArchivesCodeType Postcode;
	private String PostOfficeBox;
	private String RoomIdentification;
	private String StreetName;
	
	/**
	 * Element
	 * @return Nom du corps de bâtiment.
	 */
	@SEDA(Position=1,Type=SEDAType.ELEMENT,PropertyTerm="BlockName",Cardinality="0..1",FormType=FieldType.TEXT,FormName="Quartier",FormDescription="Nom du corps de bâtiment.")
	public String getBlockName() {
		return BlockName;
	}
	
	/**
	 * Element
	 * @param Nom du corps de bâtiment.
	 */
	@SEDA(Position=1,Type=SEDAType.ELEMENT,PropertyTerm="BlockName",Cardinality="0..1",FormType=FieldType.TEXT,FormName="Quartier",FormDescription="Nom du corps de bâtiment.")
	public void setBlockName(String blockName) {
		BlockName = blockName;
	}
	
	/**
	 * Element
	 * @return Nom du bâtiment.
	 */
	@SEDA(Position=2,Type=SEDAType.ELEMENT,PropertyTerm="BuildingName",Cardinality="0..1",FormType=FieldType.TEXT,FormName="Bâtiment",FormDescription="Nom du bâtiment.")
	public String getBuildingName() {
		return BuildingName;
	}
	
	/**
	 * Element
	 * @param Nom du bâtiment.
	 */
	@SEDA(Position=2,Type=SEDAType.ELEMENT,PropertyTerm="BuildingName",Cardinality="0..1",FormName="Bâtiment",FormType=FieldType.TEXT,FormDescription="Nom du bâtiment.")
	public void setBuildingName(String buildingName) {
		BuildingName = buildingName;
	}
	
	/**
	 * Element
	 * @return Numéro, exprimé en texte, d'un bâtiment sur la voie à cette adresse.
	 */
	@SEDA(Position=3,Type=SEDAType.ELEMENT,PropertyTerm="BuildingNumber",Cardinality="0..1",FormName="Numéro",FormType=FieldType.TEXT,FormDescription="Numéro, exprimé en texte, d'un bâtiment sur la voie à cette adresse.")
	public String getBuildingNumber() {
		return BuildingNumber;
	}
	
	/**
	 * Element
	 * @param Numéro, exprimé en texte, d'un bâtiment sur la voie à cette adresse.
	 */
	@SEDA(Position=3,Type=SEDAType.ELEMENT,PropertyTerm="BuildingNumber",Cardinality="0..1",FormName="Numéro",FormType=FieldType.TEXT,FormDescription="Numéro, exprimé en texte, d'un bâtiment sur la voie à cette adresse.")
	public void setBuildingNumber(String buildingNumber) {
		BuildingNumber = buildingNumber;
	}
	
	/**
	 * Element
	 * @return Localité. Eléments de la norme Afnor XP Z 10-011: Zone d'habitation : il s'agit en général de la commune d'implantation du destinataire. Elle est identifiée par son libellé INSEE sauf dans quelques cas ou le libellé postal diffère du libellé INSEE, généralement pour lever les ambiguïtés. Par exception, la localité de destination est dans certains cas un lieu dit si celui-ci est le siège d'un bureau distributeur.Exemple : Pyla-sur-Mer en Gironde (CP : 33115, commune 33529 la Teste de Buch).
	 */
	@SEDA(Position=4,Type=SEDAType.ELEMENT,PropertyTerm="CityName",Cardinality="0..1",FormName="Localité",FormType=FieldType.TEXT,FormDescription="Eléments de la norme Afnor XP Z 10-011: Zone d'habitation : il s'agit en général de la commune d'implantation du destinataire. Elle est identifiée par son libellé INSEE sauf dans quelques cas ou le libellé postal diffère du libellé INSEE, généralement pour lever les ambiguïtés. Par exception, la localité de destination est dans certains cas un lieu dit si celui-ci est le siège d'un bureau distributeur.Exemple : Pyla-sur-Mer en Gironde (CP : 33115, commune 33529 la Teste de Buch).")
	public String getCityName() {
		return CityName;
	}
	
	/**
	 * Element
	 * @param Localité. Eléments de la norme Afnor XP Z 10-011: Zone d'habitation : il s'agit en général de la commune d'implantation du destinataire. Elle est identifiée par son libellé INSEE sauf dans quelques cas ou le libellé postal diffère du libellé INSEE, généralement pour lever les ambiguïtés. Par exception, la localité de destination est dans certains cas un lieu dit si celui-ci est le siège d'un bureau distributeur.Exemple : Pyla-sur-Mer en Gironde (CP : 33115, commune 33529 la Teste de Buch).
	 */
	@SEDA(Position=4,Type=SEDAType.ELEMENT,PropertyTerm="CityName",Cardinality="0..1",FormName="Localité",FormType=FieldType.TEXT,FormDescription="Eléments de la norme Afnor XP Z 10-011: Zone d'habitation : il s'agit en général de la commune d'implantation du destinataire. Elle est identifiée par son libellé INSEE sauf dans quelques cas ou le libellé postal diffère du libellé INSEE, généralement pour lever les ambiguïtés. Par exception, la localité de destination est dans certains cas un lieu dit si celui-ci est le siège d'un bureau distributeur.Exemple : Pyla-sur-Mer en Gironde (CP : 33115, commune 33529 la Teste de Buch).")
	public void setCityName(String cityName) {
		CityName = cityName;
	}
	
	/**
	 * Element
	 * @return Nom d'une sous division de la localité où se trouve l'adresse, par exemple un arrondissement ou un quartier.
	 */
	@SEDA(Position=5,Type=SEDAType.ELEMENT,PropertyTerm="CitySub-DivisionName",Cardinality="0..1",FormType=FieldType.TEXT,FormName="Arrondissement / quartier",FormDescription="Nom d'une sous division de la localité où se trouve l'adresse, par exemple un arrondissement ou un quartier.")
	public String getCitySubDivisionName() {
		return CitySubDivisionName;
	}
	
	/**
	 * Element
	 * @param Nom d'une sous division de la localité où se trouve l'adresse, par exemple un arrondissement ou un quartier.
	 */
	@SEDA(Position=5,Type=SEDAType.ELEMENT,PropertyTerm="CitySub-DivisionName",Cardinality="0..1",FormType=FieldType.TEXT,FormName="Arrondissement / quartier",FormDescription="Nom d'une sous division de la localité où se trouve l'adresse, par exemple un arrondissement ou un quartier.")
	public void setCitySubDivisionName(String citySubDivisionName) {
		CitySubDivisionName = citySubDivisionName;
	}
	
	/**
	 * Element
	 * @return Identifiant unique du pays de l'adresse  (Reference ISO 3166 and UN/ECE Rec 3).
	 */
	@SEDA(Position=6,Type=SEDAType.ELEMENT,PropertyTerm="Country",Cardinality="0..1",FormName="Pays",FormType=FieldType.COMBOBOX,FormDescription="Identifiant unique du pays de l'adresse  (Reference ISO 3166 and UN/ECE Rec 3).")
	public ArchivesCountryType getCountry() {
		return Country;
	}
	
	/**
	 * Element
	 * @param Identifiant unique du pays de l'adresse  (Reference ISO 3166 and UN/ECE Rec 3).
	 */
	@SEDA(Position=6,Type=SEDAType.ELEMENT,PropertyTerm="Country",Cardinality="0..1",FormName="Pays",FormType=FieldType.COMBOBOX,FormDescription="Identifiant unique du pays de l'adresse  (Reference ISO 3166 and UN/ECE Rec 3).")
	public void setCountry(ArchivesCountryType country) {
		Country = country;
	}
	
	/**
	 * Element
	 * @return Etage ou niveau.
	 */
	@SEDA(Position=7,Type=SEDAType.ELEMENT,PropertyTerm="FloorIdentification",Cardinality="0..1",FormType=FieldType.TEXT,FormName="Etage",FormDescription="Etage ou niveau.")
	public String getFloorIdentification() {
		return FloorIdentification;
	}
	
	/**
	 * Element
	 * @param Etage ou niveau.
	 */
	@SEDA(Position=7,Type=SEDAType.ELEMENT,PropertyTerm="FloorIdentification",Cardinality="0..1",FormType=FieldType.TEXT,FormName="Etage",FormDescription="Etage ou niveau.")
	public void setFloorIdentification(String floorIdentification) {
		FloorIdentification = floorIdentification;
	}
	
	/**
	 * Element
	 * @return Code postal.
	 */
	@SEDA(Position=8,Type=SEDAType.ELEMENT,PropertyTerm="Postcode",Cardinality="0..1",FormName="Code postal",FormDescription="Code postal")
	public ArchivesCodeType getPostcode() {
		return Postcode;
	}
	
	/**
	 * Element
	 * @param Code postal.
	 */
	@SEDA(Position=8,Type=SEDAType.ELEMENT,PropertyTerm="Postcode",Cardinality="0..1",FormName="Code postal",FormDescription="Code postal")
	public void setPostcode(ArchivesCodeType postcode) {
		Postcode = postcode;
	}
	
	/**
	 * Element
	 * @return Boite postale.
	 */
	@SEDA(Position=9,Type=SEDAType.ELEMENT,PropertyTerm="PostOfficeBox",Cardinality="0..1",FormType=FieldType.TEXT,FormName="Boite postale",FormDescription="Boite postale")
	public String getPostOfficeBox() {
		return PostOfficeBox;
	}
	
	/**
	 * Element
	 * @param Boite postale.
	 */
	@SEDA(Position=9,Type=SEDAType.ELEMENT,PropertyTerm="PostOfficeBox",Cardinality="0..1",FormType=FieldType.TEXT,FormName="Boite postale",FormDescription="Boite postale")
	public void setPostOfficeBox(String postOfficeBox) {
		PostOfficeBox = postOfficeBox;
	}
	
	/**
	 * Element
	 * @return Identifiant de la salle ou de la pièce.
	 */
	@SEDA(Position=10,Type=SEDAType.ELEMENT,PropertyTerm="RoomIdentification",Cardinality="0..1",FormType=FieldType.TEXT,FormName="Pièce",FormDescription="Identifiant de la salle ou de la pièce.")
	public String getRoomIdentification() {
		return RoomIdentification;
	}
	
	/**
	 * Element
	 * @param Identifiant de la salle ou de la pièce.
	 */
	@SEDA(Position=10,Type=SEDAType.ELEMENT,PropertyTerm="RoomIdentification",Cardinality="0..1",FormType=FieldType.TEXT,FormName="Pièce",FormDescription="Identifiant de la salle ou de la pièce.")
	public void setRoomIdentification(String roomIdentification) {
		RoomIdentification = roomIdentification;
	}
	
	/**
	 * Element
	 * @return Nom de la voie.
	 */
	@SEDA(Position=11,Type=SEDAType.ELEMENT,PropertyTerm="StreetName",Cardinality="0..1",FormType=FieldType.TEXT,FormName="Voie",FormDescription="Nom de la voie.")
	public String getStreetName() {
		return StreetName;
	}
	
	/**
	 * Element
	 * @param Nom de la voie.
	 */
	@SEDA(Position=11,Type=SEDAType.ELEMENT,PropertyTerm="StreetName",Cardinality="0..1",FormType=FieldType.TEXT,FormName="Voie",FormDescription="Nom de la voie.")
	public void setStreetName(String streetName) {
		StreetName = streetName;
	}
	
}
