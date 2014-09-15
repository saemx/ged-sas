package eu.akka.saem.alfresco.connector.asalae.Exceptions;

import org.alfresco.error.AlfrescoRuntimeException;

/**
 * 
 *   Classe d'exception permettant de typer les erreurs Asalae
 * 
 * @Class         : AsalaeConnectorException.java
 * @Package       : eu.akka.saem.alfresco.connector.asalae.Exceptions
 * @Date          : $Date: 25 févr. 2014 $:
 * @author        : $Author: THOMAS.POGNANT $
 * @version       : $Revision:  $:
 * @Id            : $Id: AsalaeConnectorException.java $
 *
 */
public class AsalaeConnectorException extends AlfrescoRuntimeException{
    /**
	 * 
	 */
	private static final long serialVersionUID = -7128381561206860479L;

	public AsalaeConnectorException(String msgId) {
        super(msgId);
    }
}
