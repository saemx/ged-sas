package eu.akka.saem.alfresco.seda.form;

import java.util.ArrayList;
import java.util.List;

import eu.akka.saem.alfresco.seda.loader.agape.AGAPEObject;
import eu.akka.saem.alfresco.seda.v02.ArchiveTransferType;

/**
 * 
 *   Résultat de la création du formulaire
 * 
 * @Class         : CreateArchiveFormResult.java
 * @Package       : eu.akka.saem.alfresco.seda.form
 * @Date          : $Date: 25 févr. 2014 $:
 * @author        : $Author: THOMAS.POGNANT $
 * @version       : $Revision:  $:
 * @Id            : $Id: CreateArchiveFormResult.java $
 *
 */
public class CreateArchiveFormResult {
	private ArchiveTransferType att;
	private String profil;
	private String destination;
	private List<ArchiveTransferFile> atf;
	private AGAPEObject agapeobject;
	private ArchiveTransferType oldArchiveTransferType;

	public CreateArchiveFormResult(){
		this.atf = new ArrayList<ArchiveTransferFile>();
	}
	
	public ArchiveTransferType getArchiveTransferType() {
		return att;
	}

	public void setArchiveTransferType(ArchiveTransferType att) {
		this.att = att;
	}

	public List<ArchiveTransferFile> getArchiveTransferFile() {
		return atf;
	}

	public void setArchiveTransferFile(List<ArchiveTransferFile> atf) {
		this.atf = atf;
	}

	public String getProfil() {
		return profil;
	}

	public void setProfil(String profil) {
		this.profil = profil;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public AGAPEObject getAGAPEObject(){
		return agapeobject;
	}
	
	public void setAGAPEObject(AGAPEObject agapeObject) {
		this.agapeobject = agapeObject;
	}
	
	public ArchiveTransferType getOldArchiveTransferType(){
		return oldArchiveTransferType;
	}

	public void setOldArchiveTransferType(ArchiveTransferType sedaLoader) {
		this.oldArchiveTransferType = sedaLoader;
	}
	

}
