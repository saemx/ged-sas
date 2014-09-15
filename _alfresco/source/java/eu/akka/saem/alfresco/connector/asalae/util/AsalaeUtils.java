package eu.akka.saem.alfresco.connector.asalae.util;

import eu.akka.saem.alfresco.connector.asalae.Exceptions.AsalaeConnectorException;

/**
 * 
 *   Classe utilitaire pour Asalae permettant d'identifier
 *   le type d'erreur
 * 
 * @Class         : AsalaeUtils.java
 * @Package       : eu.akka.saem.alfresco.connector.asalae.util
 * @Date          : $Date: 25 févr. 2014 $:
 * @author        : $Author: THOMAS.POGNANT $
 * @version       : $Revision:  $:
 * @Id            : $Id: AsalaeUtils.java $
 *
 */
public class AsalaeUtils {
	
    public void handleErrorsWsGDepot(String retour) throws AsalaeConnectorException {
        String s = "Erreur lors du dépot de l'archive : ";
        if (retour.equals("1"))
            throw new AsalaeConnectorException(s + "identifiant de connexion non trouvé");
        if (retour.equals("2"))
            throw new AsalaeConnectorException(s + "mot de passe incorrect");
        if (retour.equals("3"))
            throw new AsalaeConnectorException(s + "connecteur non actif");
    }

}
