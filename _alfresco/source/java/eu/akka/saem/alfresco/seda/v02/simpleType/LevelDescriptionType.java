package eu.akka.saem.alfresco.seda.v02.simpleType;

import eu.akka.saem.alfresco.seda.annotations.SEDA;

/**
 * Table des codes pour le niveau de description
 * 
**/

public enum LevelDescriptionType {

/**
 * Classe
 * Cette valeur, issue de la tradition archivistique allemande, ne correspond pas pour l'instant aux pratiques archivistiques françaises
 **/ 
 @SEDA(PropertyTerm="class", FormName="Classe",FormDescription="Cette valeur, issue de la tradition archivistique allemande, ne correspond pas pour l'instant aux pratiques archivistiques françaises")
CLASS,
/**
 * Collection
 * Réunion artificielle de documents en fonction de critères communs liés à leur contenu ou à leur support, sans considération de leur provenance, par opposition au fonds d'archives constitué de façon organique
 **/ 
 @SEDA(PropertyTerm="collection", FormName="Collection",FormDescription="Réunion artificielle de documents en fonction de critères communs liés à leur contenu ou à leur support, sans considération de leur provenance, par opposition au fonds d'archives constitué de façon organique")
COLLECTION,
/**
 * Dossier
 * ensemble de documents regroupés, soit par le producteur pour son usage courant, soit dans le processus du classement d'archives, parce qu'ils concernent un même sujet ou une même affaire; le dossier est ordinairement l'unité de base à l'intérieur d'une série organique
 **/ 
 @SEDA(PropertyTerm="file", FormName="Dossier",FormDescription="ensemble de documents regroupés, soit par le producteur pour son usage courant, soit dans le processus du classement d'archives, parce qu'ils concernent un même sujet ou une même affaire; le dossier est ordinairement l'unité de base à l'intérieur d'une série organique")
FILE,
/**
 * Fonds
 * ensemble de documents quels que soit leur type et leur support, créé ou reçu de manière organique et utilisé par une personne physique ou morale dans l'exercice de ses activités
 **/ 
 @SEDA(PropertyTerm="fonds", FormName="Fonds",FormDescription="ensemble de documents quels que soit leur type et leur support, créé ou reçu de manière organique et utilisé par une personne physique ou morale dans l'exercice de ses activités")
FONDS,
/**
 * Item
 * plus petite unité documentaire, par exemple une lettre, un mémoire, un rapport, une photographie, un enregistrement sonore
 **/ 
 @SEDA(PropertyTerm="item", FormName="Item",FormDescription="plus petite unité documentaire, par exemple une lettre, un mémoire, un rapport, une photographie, un enregistrement sonore")
ITEM,
/**
 * Groupe de documents
 * niveau de description intermédiaire qui ne correspond pas à une division organique (sous-fonds, série ou sous-série organique); parties au sein d'une collection, versements,  épaves d'un fonds, subdivisions de fonds dont on ne connait pas la nature exacte, sous-ensemble classés thématiquement
 **/ 
 @SEDA(PropertyTerm="recordgrp", FormName="Groupe de documents",FormDescription="niveau de description intermédiaire qui ne correspond pas à une division organique (sous-fonds, série ou sous-série organique); parties au sein d'une collection, versements,  épaves d'un fonds, subdivisions de fonds dont on ne connait pas la nature exacte, sous-ensemble classés thématiquement")
RECORDGRP,
/**
 * Serie organique
 * division organique d'un fonds, correspondant à un ensemble de dossiers maintenus groupés parce qu'ils résultent d'une même activité, se rapportent à une même fonction ou à un même sujet ou revêtent une même forme
 **/ 
 @SEDA(PropertyTerm="series", FormName="Serie organique",FormDescription="division organique d'un fonds, correspondant à un ensemble de dossiers maintenus groupés parce qu'ils résultent d'une même activité, se rapportent à une même fonction ou à un même sujet ou revêtent une même forme")
SERIES,
/**
 * Sous fonds
 * division organique d'un fonds correspondant aux divisions administratives de l'institution ou de l'organisme producteur, ou, à défaut,  à un regroupement géographique, chronologique, fonctionnel ou autre des documents; quand le producteur a une structure hiérarchique complexe , chaque sous-fonds est lui-même subdivisé, autant que nécessaire pour refléter les niveaux hiérarchiques
 **/ 
 @SEDA(PropertyTerm="subfonds", FormName="Sous fonds",FormDescription="division organique d'un fonds correspondant aux divisions administratives de l'institution ou de l'organisme producteur, ou, à défaut,  à un regroupement géographique, chronologique, fonctionnel ou autre des documents; quand le producteur a une structure hiérarchique complexe , chaque sous-fonds est lui-même subdivisé, autant que nécessaire pour refléter les niveaux hiérarchiques")
SUBFONDS,
/**
 * Sous-groupe de documents
 * subdivision du groupe de documents
 **/ 
 @SEDA(PropertyTerm="subgrp", FormName="Sous-groupe de documents",FormDescription="subdivision du groupe de documents")
SUBGRP,
/**
 * Sous-série organique
 * subdivision de la série organique
 **/ 
 @SEDA(PropertyTerm="subseries", FormName="Sous-série organique",FormDescription="subdivision de la série organique")
SUBSERIES,
/**
 * 
 * 
 **/ 
 @SEDA(PropertyTerm="", FormName="",FormDescription="")
EMPTY
}

