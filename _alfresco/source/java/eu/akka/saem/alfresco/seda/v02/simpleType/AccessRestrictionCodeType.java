package eu.akka.saem.alfresco.seda.v02.simpleType;

import eu.akka.saem.alfresco.seda.annotations.SEDA;

/**
 * Table des codes pour la restriction d'accès
 * Les différentes valeurs de cette table sont tirées de la loi archive.
**/

public enum AccessRestrictionCodeType {

/**
 * 0 an
 * Documents administratifs librement communicables. (Code du Patrimoine, art. L. 213-1)
 **/ 
 @SEDA(PropertyTerm="AR038", FormName="0 an",FormDescription="Documents administratifs librement communicables. (Code du Patrimoine, art. L. 213-1)")
AR038,
/**
 * 25 ans
 * Documents dont la communication porte atteinte au secret des délibérations du Gouvernement et des autorités responsables relevant du pouvoir exécutif, à la conduite des relations extérieures, à la monnaie et au crédit public, au secret en matière commerciale et industrielle, à la recherche par les services compétents des infractions fiscales et douanières. (Code du Patrimoine, art. L. 213-2, I, 1, a)
 **/ 
 @SEDA(PropertyTerm="AR039", FormName="25 ans",FormDescription="Documents dont la communication porte atteinte au secret des délibérations du Gouvernement et des autorités responsables relevant du pouvoir exécutif, à la conduite des relations extérieures, à la monnaie et au crédit public, au secret en matière commerciale et industrielle, à la recherche par les services compétents des infractions fiscales et douanières. (Code du Patrimoine, art. L. 213-2, I, 1, a)")
AR039,
/**
 * 25 ans
 * Documents qui ne sont pas considérés comme administratifs et mentionnés au dernier alinéa de l'article 1er de la loi n° 78-753 du 17 juillet 1978 : actes et documents élaborés ou détenus par les assemblées parlementaires, avis du Conseil d'Etat et des juridictions administratives, documents de la Cour des comptes mentionnés à l'article L. 140-9 du code des juridictions financières et les documents des chambres régionales des comptes mentionnés à l'article L. 241-6 du même code, documents d'instruction des réclamations adressées au Médiateur de la République, documents préalables à l'élaboration du rapport d'accréditation des établissements de santé prévu à l'article L. 6113-6 du code de la santé publique, rapports d'audit des établissements de santé mentionnés à l'article 40 de la loi de financement de la sécurité sociale pour 2001 (n° 2000-1257 du 23 décembre 2000). (Code du Patrimoine, art. L. 213-2, I, 1, b)
 **/ 
 @SEDA(PropertyTerm="AR040", FormName="25 ans",FormDescription="Documents qui ne sont pas considérés comme administratifs et mentionnés au dernier alinéa de l'article 1er de la loi n° 78-753 du 17 juillet 1978 : actes et documents élaborés ou détenus par les assemblées parlementaires, avis du Conseil d'Etat et des juridictions administratives, documents de la Cour des comptes mentionnés à l'article L. 140-9 du code des juridictions financières et les documents des chambres régionales des comptes mentionnés à l'article L. 241-6 du même code, documents d'instruction des réclamations adressées au Médiateur de la République, documents préalables à l'élaboration du rapport d'accréditation des établissements de santé prévu à l'article L. 6113-6 du code de la santé publique, rapports d'audit des établissements de santé mentionnés à l'article 40 de la loi de financement de la sécurité sociale pour 2001 (n° 2000-1257 du 23 décembre 2000). (Code du Patrimoine, art. L. 213-2, I, 1, b)")
AR040,
/**
 * 25 ans
 * Secret statistique. (Code du Patrimoine, art. L. 213-2, I, 1, a)
 **/ 
 @SEDA(PropertyTerm="AR041", FormName="25 ans",FormDescription="Secret statistique. (Code du Patrimoine, art. L. 213-2, I, 1, a)")
AR041,
/**
 * 25 ans
 * Documents élaborés dans le cadre d'un contrat de prestation de services exécuté pour le compte d'une ou de plusieurs personnes déterminées. (Code du Patrimoine, art. L. 213-2, I, 1, c)
 **/ 
 @SEDA(PropertyTerm="AR042", FormName="25 ans",FormDescription="Documents élaborés dans le cadre d'un contrat de prestation de services exécuté pour le compte d'une ou de plusieurs personnes déterminées. (Code du Patrimoine, art. L. 213-2, I, 1, c)")
AR042,
/**
 * 25 ans à compter de la date de décès de l'intéressé
 * Documents dont la communication est susceptible de porter atteinte au secret médical. (Code du Patrimoine, art. L. 213-2, I, 2)
 **/ 
 @SEDA(PropertyTerm="AR043", FormName="25 ans à compter de la date de décès de l'intéressé",FormDescription="Documents dont la communication est susceptible de porter atteinte au secret médical. (Code du Patrimoine, art. L. 213-2, I, 2)")
AR043,
/**
 * 25 ans à compter de la date de décès de l'intéressé si ce délai est plus court que le délai ordinaire de 75 ans
 * Etat civil (actes de naissance ou de mariage). (Code du Patrimoine, art. L. 213-2, I, 4, e)
 **/ 
 @SEDA(PropertyTerm="AR044", FormName="25 ans à compter de la date de décès de l'intéressé si ce délai est plus court que le délai ordinaire de 75 ans",FormDescription="Etat civil (actes de naissance ou de mariage). (Code du Patrimoine, art. L. 213-2, I, 4, e)")
AR044,
/**
 * 25 ans à compter de la date de décès de l'intéressé si ce délai est plus court que le délai ordinaire de 75 ans
 * Minutes et répertoires des officiers publics et ministériels. (Code du Patrimoine, art. L. 213-2, I, 4, d)
 **/ 
 @SEDA(PropertyTerm="AR045", FormName="25 ans à compter de la date de décès de l'intéressé si ce délai est plus court que le délai ordinaire de 75 ans",FormDescription="Minutes et répertoires des officiers publics et ministériels. (Code du Patrimoine, art. L. 213-2, I, 4, d)")
AR045,
/**
 * 25 ans à compter de la date de décès de l'intéressé si ce délai est plus court que le délai ordinaire de 75 ans
 * Documents relatifs aux enquêtes réalisées par les services de la police judiciaire. (Code du Patrimoine, art. L. 213-2, I, 4, b)
 **/ 
 @SEDA(PropertyTerm="AR046", FormName="25 ans à compter de la date de décès de l'intéressé si ce délai est plus court que le délai ordinaire de 75 ans",FormDescription="Documents relatifs aux enquêtes réalisées par les services de la police judiciaire. (Code du Patrimoine, art. L. 213-2, I, 4, b)")
AR046,
/**
 * 25 ans à compter de la date de décès de l'intéressé si ce délai est plus court que le délai ordinaire de 75 ans
 * Documents relatifs aux affaires portées devant les juridictions, sous réserve des dispositions particulières relatives aux jugements, et à l'exécution des décisions de justice. (Code du Patrimoine, art. L. 213-2, I, 4, c)
 **/ 
 @SEDA(PropertyTerm="AR047", FormName="25 ans à compter de la date de décès de l'intéressé si ce délai est plus court que le délai ordinaire de 75 ans",FormDescription="Documents relatifs aux affaires portées devant les juridictions, sous réserve des dispositions particulières relatives aux jugements, et à l'exécution des décisions de justice. (Code du Patrimoine, art. L. 213-2, I, 4, c)")
AR047,
/**
 * 50 ans
 * Documents dont la communication porte atteinte à la protection de la vie privée ou portant appréciation ou jugement de valeur sur une personne physique nommément désignée, ou facilement identifiable, ou qui font apparaître le comportement d'une personne dans des conditions susceptibles de lui porter préjudice. (Code du Patrimoine, art. L. 213-2, I, 3)
 **/ 
 @SEDA(PropertyTerm="AR048", FormName="50 ans",FormDescription="Documents dont la communication porte atteinte à la protection de la vie privée ou portant appréciation ou jugement de valeur sur une personne physique nommément désignée, ou facilement identifiable, ou qui font apparaître le comportement d'une personne dans des conditions susceptibles de lui porter préjudice. (Code du Patrimoine, art. L. 213-2, I, 3)")
AR048,
/**
 * 50 ans
 * Documents dont la communication porte atteinte au secret de la défense nationale, aux intérêts fondamentaux de l'État dans la conduite de la politique extérieure, à la sûreté de l'État, à la sécurité publique. (Code du Patrimoine, art. L. 213-2, I, 3)
 **/ 
 @SEDA(PropertyTerm="AR049", FormName="50 ans",FormDescription="Documents dont la communication porte atteinte au secret de la défense nationale, aux intérêts fondamentaux de l'État dans la conduite de la politique extérieure, à la sûreté de l'État, à la sécurité publique. (Code du Patrimoine, art. L. 213-2, I, 3)")
AR049,
/**
 * 50 ans
 * Documents relatifs à la construction, à l'équipement et au fonctionnement des ouvrages, bâtiments ou parties de bâtiments utilisés pour la détention de personnes ou recevant habituellement des personnes détenues. (Code du Patrimoine, art. L. 213-2, I, 3)
 **/ 
 @SEDA(PropertyTerm="AR050", FormName="50 ans",FormDescription="Documents relatifs à la construction, à l'équipement et au fonctionnement des ouvrages, bâtiments ou parties de bâtiments utilisés pour la détention de personnes ou recevant habituellement des personnes détenues. (Code du Patrimoine, art. L. 213-2, I, 3)")
AR050,
/**
 * 50 ans
 * Documents élaborés dans le cadre d'un contrat de prestation de services dont la communication porte atteinte au secret de la défense nationale, aux intérêts fondamentaux de l'État dans la conduite de la politique extérieure, à la sûreté de l'État, à la sécurité publique, à la protection de la vie privée, ou concernant des bâtiments employés pour la détention de personnes ou recevant habituellement des personnes détenues. (Code du Patrimoine, art. L. 213-2, I, 1, c)
 **/ 
 @SEDA(PropertyTerm="AR051", FormName="50 ans",FormDescription="Documents élaborés dans le cadre d'un contrat de prestation de services dont la communication porte atteinte au secret de la défense nationale, aux intérêts fondamentaux de l'État dans la conduite de la politique extérieure, à la sûreté de l'État, à la sécurité publique, à la protection de la vie privée, ou concernant des bâtiments employés pour la détention de personnes ou recevant habituellement des personnes détenues. (Code du Patrimoine, art. L. 213-2, I, 1, c)")
AR051,
/**
 * 75 ans
 * Secret statistique : données collectées au moyen de questionnaires ayant trait aux faits et aux comportements d'ordre privé. (Code du Patrimoine, art. L. 213-2, I, 4, a)
 **/ 
 @SEDA(PropertyTerm="AR052", FormName="75 ans",FormDescription="Secret statistique : données collectées au moyen de questionnaires ayant trait aux faits et aux comportements d'ordre privé. (Code du Patrimoine, art. L. 213-2, I, 4, a)")
AR052,
/**
 * 75 ans
 * Documents élaborés dans le cadre d'un contrat de prestation de services et pouvant être liés aux services de la police judiciaire ou aux affaires portées devant les juridictions. (Code du Patrimoine, art. L 213-2, I, 1, b)
 **/ 
 @SEDA(PropertyTerm="AR053", FormName="75 ans",FormDescription="Documents élaborés dans le cadre d'un contrat de prestation de services et pouvant être liés aux services de la police judiciaire ou aux affaires portées devant les juridictions. (Code du Patrimoine, art. L 213-2, I, 1, b)")
AR053,
/**
 * 75 ans
 * Etat civil (actes de naissance ou de mariage). (Code du Patrimoine, art. L. 213-2, I, 4, e)
 **/ 
 @SEDA(PropertyTerm="AR054", FormName="75 ans",FormDescription="Etat civil (actes de naissance ou de mariage). (Code du Patrimoine, art. L. 213-2, I, 4, e)")
AR054,
/**
 * 75 ans
 * Minutes et répertoires des officiers publics et ministériels. (Code du Patrimoine, art. L. 213-2, I, 4, d)
 **/ 
 @SEDA(PropertyTerm="AR055", FormName="75 ans",FormDescription="Minutes et répertoires des officiers publics et ministériels. (Code du Patrimoine, art. L. 213-2, I, 4, d)")
AR055,
/**
 * 75 ans
 * Documents relatifs aux enquêtes réalisées par les services de la police judiciaire. (Code du Patrimoine, art. L. 213-2, I, 4, b)
 **/ 
 @SEDA(PropertyTerm="AR056", FormName="75 ans",FormDescription="Documents relatifs aux enquêtes réalisées par les services de la police judiciaire. (Code du Patrimoine, art. L. 213-2, I, 4, b)")
AR056,
/**
 * 75 ans
 * Documents relatifs aux affaires portées devant les juridictions. (Code du Patrimoine, art. L. 213-2, I, 4, c)
 **/ 
 @SEDA(PropertyTerm="AR057", FormName="75 ans",FormDescription="Documents relatifs aux affaires portées devant les juridictions. (Code du Patrimoine, art. L. 213-2, I, 4, c)")
AR057,
/**
 * 100 ans
 * Documents évoquant des personnes mineures : statistiques, enquêtes de la police judiciaire, documents relatifs aux affaires portées devant les juridictions et à l'exécution des décisions de justice. (Code du Patrimoine, art. L. 213-2, I, 5)
 **/ 
 @SEDA(PropertyTerm="AR058", FormName="100 ans",FormDescription="Documents évoquant des personnes mineures : statistiques, enquêtes de la police judiciaire, documents relatifs aux affaires portées devant les juridictions et à l'exécution des décisions de justice. (Code du Patrimoine, art. L. 213-2, I, 5)")
AR058,
/**
 * 100 ans
 * Secret de la Défense nationale : documents couverts ou ayant été couverts par le secret de la défense nationale dont la communication est de nature à porter atteinte à la sécurité de personnes nommément désignées ou facilement identifiables (agents spéciaux, agents de renseignements...). (Code du Patrimoine, art. L. 213-2, I, 3)
 **/ 
 @SEDA(PropertyTerm="AR059", FormName="100 ans",FormDescription="Secret de la Défense nationale : documents couverts ou ayant été couverts par le secret de la défense nationale dont la communication est de nature à porter atteinte à la sécurité de personnes nommément désignées ou facilement identifiables (agents spéciaux, agents de renseignements...). (Code du Patrimoine, art. L. 213-2, I, 3)")
AR059,
/**
 * 100 ans
 * Documents dont la communication est de nature à porter atteinte à l'intimité de la vie sexuelle des personnes : enquêtes de la police judiciaire, documents relatifs aux affaires portées devant les juridictions. (Code du Patrimoine, art. L. 213-2, I, 5)
 **/ 
 @SEDA(PropertyTerm="AR060", FormName="100 ans",FormDescription="Documents dont la communication est de nature à porter atteinte à l'intimité de la vie sexuelle des personnes : enquêtes de la police judiciaire, documents relatifs aux affaires portées devant les juridictions. (Code du Patrimoine, art. L. 213-2, I, 5)")
AR060,
/**
 * 120 ans à compter de la date de naissance si la date de décès de l'intéressé n'est pas connue
 * Documents dont la communication est susceptible de porter atteinte au secret médical. (Code du Patrimoine, art. L. 213-2, I, 2)
 **/ 
 @SEDA(PropertyTerm="AR061", FormName="120 ans à compter de la date de naissance si la date de décès de l'intéressé n'est pas connue",FormDescription="Documents dont la communication est susceptible de porter atteinte au secret médical. (Code du Patrimoine, art. L. 213-2, I, 2)")
AR061,
/**
 * Illimitée
 * Archives publiques dont la communication est susceptible d'entraîner la diffusion d'informations permettant de concevoir, fabriquer, utiliser ou localiser des armes nucléaires, biologiques, chimiques ou toutes autres armes ayant des effets directs ou indirects de destruction d'un niveau analogue. (Code du Patrimoine, art. L. 213-2, II)
 **/ 
 @SEDA(PropertyTerm="AR062", FormName="Illimitée",FormDescription="Archives publiques dont la communication est susceptible d'entraîner la diffusion d'informations permettant de concevoir, fabriquer, utiliser ou localiser des armes nucléaires, biologiques, chimiques ou toutes autres armes ayant des effets directs ou indirects de destruction d'un niveau analogue. (Code du Patrimoine, art. L. 213-2, II)")
AR062,
/**
 * 
 * 
 **/ 
 @SEDA(PropertyTerm="", FormName="",FormDescription="")
EMPTY
}