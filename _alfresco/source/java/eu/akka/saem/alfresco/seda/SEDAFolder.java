package eu.akka.saem.alfresco.seda;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 *   Bean pour les dossiers SEDA
 * 
 * @Class         : SEDAFolder.java
 * @Package       : eu.akka.saem.alfresco.seda
 * @Date          : $Date: 25 févr. 2014 $:
 * @author        : $Author: THOMAS.POGNANT $
 * @version       : $Revision:  $:
 * @Id            : $Id: SEDAFolder.java $
 *
 */
public class SEDAFolder {

	private List<SEDAFolder> subFolders;
	private String name;
	private String description;
	private List<SEDADocument> documents;
	/**
	 * SEDARelatedObject peut être de type ArchiveObjectType ou ArchiveType
	 */
	private Object SEDARelatedObject;

	public SEDAFolder() {
		subFolders = new ArrayList<>();
		documents = new ArrayList<>();
	}

	public SEDAFolder(String name) {
		this();
		this.name = name;
	}

	public SEDAFolder(String name, List<SEDAFolder> subFolders) {
		this(name);
		subFolders.addAll(subFolders);
	}

	public List<SEDAFolder> getSubFolders() {
		return subFolders;
	}

	public void setSubFolders(List<SEDAFolder> subFolders) {
		this.subFolders = subFolders;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<SEDADocument> getDocuments() {
		return documents;
	}

	public void setDocuments(List<SEDADocument> documents) {
		this.documents = documents;
	}

	public Object getSEDARelatedObject() {
		return SEDARelatedObject;
	}

	public void setSEDARelatedObject(Object sEDARelatedObject) {
		SEDARelatedObject = sEDARelatedObject;
	}
	
}
