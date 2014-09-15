package eu.akka.saem.alfresco.utils;

import eu.akka.saem.alfresco.seda.SEDADocument;
import eu.akka.saem.alfresco.seda.SEDAFolder;
import eu.akka.saem.alfresco.seda.v02.ArchiveTransferType;
import eu.akka.saem.alfresco.seda.v02.complexType.ArchiveObjectType;
import eu.akka.saem.alfresco.seda.v02.complexType.ArchiveType;
import eu.akka.saem.alfresco.seda.v02.complexType.DocumentType;

/**
 * 
 *   Classe contenant les méthodes de transformation d'objet en SEDA Folder
 * 
 * @Class         : BrowsableSEDAUtils.java
 * @Package       : eu.akka.saem.alfresco.utils
 * @Date          : $Date: 25 févr. 2014 $:
 * @author        : $Author: THOMAS.POGNANT $
 * @version       : $Revision:  $:
 * @Id            : $Id: BrowsableSEDAUtils.java $
 *
 */
public class BrowsableSEDAUtils {
	private SEDAFolder root;

	public BrowsableSEDAUtils(ArchiveTransferType att) {
		root = transformArchiveTransferToSEDAFolder(att);
	}

	private SEDAFolder transformArchiveTransferToSEDAFolder(ArchiveTransferType att) {
		SEDAFolder result = new SEDAFolder("Root");
		result.setSEDARelatedObject(att);

		if(att.getContains() != null){
			for (ArchiveType at : att.getContains()) {
				result.getSubFolders().add(transformArchiveTransferArchiveTypeToSEDAFolder(at));
			}
		}

		return result;
	}

	private SEDAFolder transformArchiveTransferArchiveTypeToSEDAFolder(ArchiveType att) {
		SEDAFolder folder = new SEDAFolder();
		folder.setSEDARelatedObject(att);

		if (att.getName() != null)
			folder.setName(att.getName());

		if (att.getContentDescription() != null)
			folder.setDescription(att.getContentDescription().getDescription());

		if(att.getContains() != null){
			for (ArchiveObjectType aot : att.getContains()) {
				folder.getSubFolders().add(transformArchiveTransferArchiveObjectTypeToSEDAFolder(aot));
			}
		}

		if (att.getDocument() != null) {
			for (DocumentType dt : att.getDocument()) {
				SEDADocument sedaDocument = transformDocumentTypeToSEDADocument(dt);
				if (sedaDocument != null) {
					folder.getDocuments().add(sedaDocument);
				}
			}
		}

		return folder;
	}

	private SEDAFolder transformArchiveTransferArchiveObjectTypeToSEDAFolder(ArchiveObjectType aot) {
		SEDAFolder folder = new SEDAFolder();

		folder.setSEDARelatedObject(aot);

		if (aot.getName() != null)
			folder.setName(aot.getName());

		if (aot.getContains() != null){
		for (ArchiveObjectType aot2 : aot.getContains()) {
			folder.getSubFolders().add(transformArchiveTransferArchiveObjectTypeToSEDAFolder(aot2));
		}
		}

		if(aot.getDocument() != null){
			for (DocumentType dt : aot.getDocument()) {
				SEDADocument sedaDocument = transformDocumentTypeToSEDADocument(dt);
				if (sedaDocument != null) {
					folder.getDocuments().add(sedaDocument);
				}
			}
		}

		return folder;
	}

	private SEDADocument transformDocumentTypeToSEDADocument(DocumentType dt) {
		if (dt.getAttachment() == null || dt.getAttachment().get(0) == null
				|| dt.getAttachment().get(0).getFilename() == null
				|| dt.getAttachment().get(0).getFilename() == "") {
			return null;
		}
		SEDADocument document = new SEDADocument();
		document.setSEDARelatedObject(dt);

		document.setName(dt.getAttachment().get(0).getFilename());

		if (dt.getDescription() != null) {
			document.setDescription(dt.getDescription());
		}

		return document;
	}

	public SEDAFolder getRoot() {
		return root;
	}

	public void setRoot(SEDAFolder root) {
		this.root = root;
	}

}
