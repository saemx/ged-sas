package eu.akka.saem.alfresco.exception;

/**
 * 
 *   Classe Exception utilisé dans le cas
 *   d'une création d'archive dans un dossier non profilé
 * 
 * @Class         : NoProfilableFolderException.java
 * @Package       : eu.akka.saem.alfresco.exception
 * @Date          : $Date: 25 févr. 2014 $:
 * @author        : $Author: THOMAS.POGNANT $
 * @version       : $Revision:  $:
 * @Id            : $Id: NoProfilableFolderException.java $
 *
 */
public class NoProfilableFolderException extends Exception {

	public NoProfilableFolderException(String string) {
		super(string);
	}

	private static final long serialVersionUID = -5512513840058697095L;

}
