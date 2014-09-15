package eu.akka.saem.alfresco.connector.asalae.ws;

import java.rmi.RemoteException;

/**
 * 
 *   Classe contenant les méthodes du webservice
 * 
 * @Class         : WebServicePortType.java
 * @Package       : eu.akka.saem.alfresco.connector.asalae.ws
 * @Date          : $Date: 25 févr. 2014 $:
 * @author        : $Author: THOMAS.POGNANT $
 * @version       : $Revision:  $:
 * @Id            : $Id: WebServicePortType.java $
 *
 */
public interface WebServicePortType extends java.rmi.Remote {
	
	/**
	 * Dépose une archive et son bordereau de transfert dans AS@LAE
	 *
	 * Method : wsDepot()
	 * @param nomBordereau
	 * @param bordereau
	 * @param nomDocument
	 * @param document
	 * @param typeDocument
	 * @param login
	 * @param password
	 * @return
	 * @throws RemoteException String
	 */
	public String wsDepot(String nomBordereau, byte[] bordereau, String nomDocument, byte[] document,
			String typeDocument, String login, String password) throws RemoteException;

	/**
	 * Faire une demande de restitution
	 *
	 * Method : wsRestitution()
	 * @param nomBordereau
	 * @param bordereau
	 * @param nomDocument
	 * @param document
	 * @param typeDocument
	 * @param login
	 * @param password
	 * @return
	 * @throws RemoteException String
	 */
	public String wsRestitution(String nomBordereau, byte[] bordereau, String nomDocument, byte[] document,
			String typeDocument, String login, String password) throws RemoteException;

	
	/**
	 * Créer un bordereau SEDA à partir d'un tableau d'information.
	 * 
	 * Method : wsGSeda()
	 * @param nomBordereau
	 * @param login
	 * @param password
	 * @return
	 * @throws RemoteException String
	 */
	public String wsGSeda(Object nomBordereau, String login, String password) throws RemoteException;
	
    /**
     * Crée une demande d'élimination dans AS@LAE
     * Method : wsElimination()
     * @param nomBordereau
     * @param bordereau
     * @param login
     * @param password
     * @return
     * @throws RemoteException java.lang.String
     */
    public java.lang.String wsElimination(String nomBordereau, byte[] bordereau, String login, String password) throws RemoteException;

	/**
	 * Permet de recuperer la version d'asalae
	 * 
	 * @param login
	 * @param password
	 * @return
	 * @throws RemoteException
	 */
	public String wsGetVersion(String login, String password) throws RemoteException;

	/**
	 * Permet de recuperer les profils d'asalae
	 * 
	 * Method : wsGetProfil()
	 * @param login
	 * @param password
	 * @return
	 * @throws RemoteException String
	 */
	public String wsGetProfil(String login, String password) throws RemoteException;
	
	/**
	 *  SAEM-80 : Webservice pour la récupération des DUA expirées
     * 
     * Method : wsGetDuaExpirees()
     * @param login
     * @param password
     * @return
     * @throws RemoteException Object
     */
    public Object wsGetDuaExpirees(String login, String password) throws RemoteException;   

    /** 
     * SAEM-80 : Webservice pour la complétion du bordereau
     * 
	 * Method : wsCompleterBordereau()
	 * @param bordereau
	 * @param login
	 * @param password
	 * @return byte[]
	 */
	public byte[] wsCompleterBordereau(Object bordereau, String login, String password);

	/**
	 * Method : wsGetMessage()
	 * @param typeEchange
	 * @param typeMessage
	 * @param identifiantMessage
	 * @param login
	 * @param password
	 * @return
	 * @throws RemoteException String
	 */
	public String wsGetMessage(String typeEchange, String typeMessage, String identifiantMessage,
			String login, String password) throws RemoteException;

}
