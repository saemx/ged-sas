package eu.akka.saem.alfresco.exception;

/**
 * 
 *   Classe Exception utilisé dans le cas d'une 
 *   erreur de création de conteneur
 * 
 * @Class         : NoDestinationException.java
 * @Package       : eu.akka.saem.alfresco.exception
 * @Date          : $Date: 25 févr. 2014 $:
 * @author        : $Author: THOMAS.POGNANT $
 * @version       : $Revision:  $:
 * @Id            : $Id: NoDestinationException.java $
 *
 */
public class NoDestinationException extends Exception{

	public NoDestinationException(String string) {
		super(string);
	}

	private static final long serialVersionUID = 3969081245957382686L;

}
