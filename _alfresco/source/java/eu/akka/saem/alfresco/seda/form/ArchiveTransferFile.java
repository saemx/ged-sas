package eu.akka.saem.alfresco.seda.form;

import org.springframework.extensions.surf.util.Content;

/**
 * 
 * 
 * @Class         : ArchiveTransferFile.java
 * @Package       : eu.akka.saem.alfresco.seda.form
 * @Date          : $Date: 25 févr. 2014 $:
 * @author        : $Author: THOMAS.POGNANT $
 * @version       : $Revision:  $:
 * @Id            : $Id: ArchiveTransferFile.java $
 *
 */
public class ArchiveTransferFile {
	private String filename;
	private Content content;
	private String mimetype;
	
	public ArchiveTransferFile(String filename,Content content,String mimetype){
		this.filename = filename;
		this.content = content;
		this.mimetype = mimetype;
	}
	
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public Content getContent() {
		return content;
	}
	public void setContent(Content content) {
		this.content = content;
	}
	public String getMimetype() {
		return mimetype;
	}
	public void setMimetype(String mimetype) {
		this.mimetype = mimetype;
	}
	
	
}
