ged-sas
=======
compatible : Alfresco community 4.2.C

objectifs
===========

Ce module Alfresco permettant l'interaction avec le libriciel (http://adullact.net/projects/asalae/ | as@lae).
Il permet de disposer d'un formulaire interactif alimenté par des profils SEDA (.xsd) et de superviser le processus d'archivage mettant en relation les services producteurs d'archives et les services d'archives.

Dans le dictionnaire de données d'Alfresco une catégorie SEDA a été créée. Le dépôt des profils SEDA dans le dictionnaire de données permet d'hériter de cette catégorie. 
Dans le modèle de données Alfresco, un aspect archives a été défini permettant de "profiler" un dossier (c'est-à-dire permettre l'association entre un dossier du plan de rangement et un profil SEDA) et d'écrire des métadonnées issues du processus d'archivage sur les dossiers à archiver.

Ce module contient également un web services permettant de transférer des versements vers as@lae encapsulés dans un bordereau xml SEDA et un service de transfert de documents par paquet pour répondre à la problématique du transfert des fichiers volumineux.

Installation
=============

Le code source mis à disposition doit être compilé à l'aide de l'outil ant et des librairies de compilation fournies par Alfresco share.sh et alfresco.sh

Dans le cas, où l'utilisateur souhaite simplement réutiliser en l'état le code mis à disposition, les paquets amp compilés sont également disponibles. Il est dès lors simplement nécessaire de placer ces fichiers amp dans les dossiers dédiés et de relancer le service alfresco.

Le fichier saem.properties doit être modifié pour correspondre aux noeuds du système cible et à l'url à laquelle est disponible l'instance asalae avec laquelle on souhaie interagir.
