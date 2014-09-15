package eu.akka.saem.alfresco.exception;

import org.alfresco.service.cmr.model.FileExistsException;
import org.alfresco.service.cmr.repository.NodeRef;

/**
 * 
 *   Classe Exception utilisé lorsqu'un noderef existe déjà
 * 
 * @Class         : FileAlreadyExistException.java
 * @Package       : eu.akka.saem.alfresco.exception
 * @Date          : $Date: 25 févr. 2014 $:
 * @author        : $Author: THOMAS.POGNANT $
 * @version       : $Revision:  $:
 * @Id            : $Id: FileAlreadyExistException.java $
 *
 */
public class FileAlreadyExistException extends Exception {

	private static final long serialVersionUID = 2084633284197024335L;

	private NodeRef parentNodeRef;
	private String name;

	public FileAlreadyExistException(FileExistsException e) {
		super("Existing file or folder " + e.getName() + " already exists");
		this.parentNodeRef = e.getParentNodeRef();
		this.name = e.getName();
	}
	
	public NodeRef getParentNodeRef() {
		return parentNodeRef;
	}

	public String getName() {
		return name;
	}
}
