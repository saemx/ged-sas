/**
 * ServerServiceSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */
package eu.akka.alfresco.axis2.asalae;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

import server.WsMessageValidationStateResponse;
import sun.misc.BASE64Encoder;

/**
 * ServerServiceSkeleton java skeleton for the axisService
 */
public class ServerServiceSkeleton {

	/**
	 * Auto generated method signature Notification de modification d'archive
	 * dans As@lae
	 * 
	 * @param wsModificationNotification
	 * @return wsModificationNotificationResponse
	 */

	public server.WsModificationNotificationResponse wsModificationNotification(
			server.WsModificationNotification wsModificationNotification) {

		try {
			String bordereauFolder = System.getProperty("java.io.tmpdir") + "/asalaereponse/";
			File directory = new File(bordereauFolder);
			InputStream is = wsModificationNotification.getBordereau().getInputStream();

			if (!directory.exists()) {
				directory.mkdir();
			}

			String tmpFileName = Long.toString(new Date().getTime()) + ".xml";
			FileOutputStream outputStream = new FileOutputStream(
					new File(bordereauFolder + "/" + tmpFileName));

			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = is.read(bytes)) != -1) {
				outputStream.write(bytes, 0, read);
			}
			outputStream.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		callService(wsModificationNotification.getLogin(), wsModificationNotification.getPassword());
		server.WsModificationNotificationResponse wmnr = new server.WsModificationNotificationResponse();
		wmnr.setWsModificationNotificationReturn("0");

		return wmnr;

	}
	
	  /**
     * Auto generated method signature
     * Récupérer le chemin de dépot des fichiers volumineux
                                 * @param wsGetPathRepoBigFile 
         * @return wsGetPathRepoBigFileResponse 
     */
    
       public server.WsGetPathRepoBigFileResponse wsGetPathRepoBigFile(server.WsGetPathRepoBigFile wsGetPathRepoBigFile)
        {
    	   server.WsGetPathRepoBigFileResponse ret = new server.WsGetPathRepoBigFileResponse();
    	   ret.setWsGetPathRepoBigFileReturn(System.getProperty("java.io.tmpdir") + "/asalaereponse");
    	   return ret;
        }
 

	/**
	 * Auto generated method signature Dépose une archive et son bordereau de
	 * transfert dans AS@LAE
	 * 
	 * @param wsDepot
	 * @return wsDepotResponse
	 */

	public server.WsDepotResponse wsDepot(server.WsDepot wsDepot) {

		server.WsDepotResponse response = new server.WsDepotResponse();

		try {
			String bordereauFolder = System.getProperty("java.io.tmpdir") + "/asalaereponse/";
			File directory = new File(bordereauFolder);
			InputStream is = wsDepot.getBordereau().getInputStream();

			if (!directory.exists()) {
				directory.mkdir();
			}

			String tmpFileName = Long.toString(new Date().getTime()) + ".xml";
			FileOutputStream outputStream = new FileOutputStream(
					new File(bordereauFolder + "/" + tmpFileName));

			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = is.read(bytes)) != -1) {
				outputStream.write(bytes, 0, read);
			}
			outputStream.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		callService(wsDepot.getLogin(), wsDepot.getPassword());

		response.setWsDepotReturn("0");

		return response;
	}

	private void callService(String login, String pass) {
		try {
			HttpURLConnection connection = (HttpURLConnection) new URL(
					"http://localhost:8080/alfresco/service/saem/asalaereponse?login="+login+"&pass="+pass).openConnection();
			connection.setRequestMethod("GET");

			// Write auth header
			BASE64Encoder encoder = new BASE64Encoder();
			String encodedCredential = encoder.encode((login + ":" + pass).getBytes());
			connection.setRequestProperty("Authorization", "BASIC " + encodedCredential);

			// Do request
			connection.connect();
			connection.getInputStream();
			connection.disconnect();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Auto generated method signature Complétion du bordereau de transfert dans
	 * AS@LAE
	 * 
	 * @param wsCompleterBordereau
	 * @return wsCompleterBordereauResponse
	 */

	public server.WsCompleterBordereauResponse wsCompleterBordereau(
			server.WsCompleterBordereau wsCompleterBordereau) {
		// TODO : fill this with the necessary business logic
		throw new java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName()
				+ "#wsCompleterBordereau");
	}

	/**
	 * Auto generated method signature Crée une demande d'élimintation dans AS@LAE
	 * 
	 * @param wsElimination
	 * @return wsEliminationResponse
	 */
	public server.WsEliminationResponse wsElimination(server.WsElimination wsElimination) {
		server.WsEliminationResponse response = new server.WsEliminationResponse();

		try {
			String bordereauFolder = System.getProperty("java.io.tmpdir") + "/asalaereponse/";
			File directory = new File(bordereauFolder);
			InputStream is = wsElimination.getBordereau().getInputStream();

			if (!directory.exists()) {
				directory.mkdir();
			}

			String tmpFileName = Long.toString(new Date().getTime()) + ".xml";
			FileOutputStream outputStream = new FileOutputStream(
					new File(bordereauFolder + "/" + tmpFileName));

			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = is.read(bytes)) != -1) {
				outputStream.write(bytes, 0, read);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		callService(wsElimination.getLogin(), wsElimination.getPassword());

		response.setWsEliminationReturn("0");
		return response;
	}

	/**
	 * Auto generated method signature Récupère les archives dont la DUA est
	 * expirée
	 * 
	 * @param wsGetDuaExpirees
	 * @return wsGetDuaExpireesResponse
	 */

	public server.WsGetDuaExpireesResponse wsGetDuaExpirees(server.WsGetDuaExpirees wsGetDuaExpirees) {
		// TODO : fill this with the necessary business logic
		throw new java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName()
				+ "#wsGetDuaExpirees");
	}

	/**
	 * Auto generated method signature Récupère la version courante de ASALAE
	 * 
	 * @param wsGetVersion
	 * @return wsGetVersionResponse
	 */

	public server.WsGetVersionResponse wsGetVersion(server.WsGetVersion wsGetVersion) {

		// try {
		// HttpURLConnection connection = (HttpURLConnection) new URL(
		// "http://localhost:8080/alfresco/service/saem/elimination-confirmation?nodeRef=workspace://SpacesStore/5531469c-2152-463a-84be-8105a95934d7")
		// .openConnection();
		// connection.setRequestMethod("GET");
		//
		// // Write auth header
		// BASE64Encoder encoder = new BASE64Encoder();
		// String encodedCredential = encoder.encode((wsGetVersion.getLogin() +
		// ":" + wsGetVersion
		// .getPassword()).getBytes());
		// connection.setRequestProperty("Authorization", "BASIC " +
		// encodedCredential);
		//
		// // Do request
		// connection.connect();
		// connection.getInputStream();
		// connection.disconnect();
		// } catch (Exception ex) {
		// ex.printStackTrace();
		// }

		server.WsGetVersionResponse response = new server.WsGetVersionResponse();
		response.setWsGetVersionReturn("resultat");
		return response;
	}

	/**
	 * Auto generated method signature Récupérer un message d'échange SEDA suite
	 * à un envoi sur AS@LAE
	 * 
	 * @param wsGetMessage
	 * @return wsGetMessageResponse
	 */

	public server.WsGetMessageResponse wsGetMessage(server.WsGetMessage wsGetMessage) {
		// TODO : fill this with the necessary business logic
		throw new java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName()
				+ "#wsGetMessage");
	}

	/**
	 * Auto generated method signature Créer un bordereau SEDA à partir d'un
	 * tableau d'informations
	 * 
	 * @param wsGSeda
	 * @return wsGSedaResponse
	 */

	public server.WsGSedaResponse wsGSeda(server.WsGSeda wsGSeda) {
		// TODO : fill this with the necessary business logic
		throw new java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName()
				+ "#wsGSeda");
	}

	/**
	 * Auto generated method signature Envoie une demande de validation
	 * 
	 * @param wsCreateMessageValidation
	 * @return wsCreateMessageValidationResponse
	 */

	public server.WsCreateMessageValidationResponse wsCreateMessageValidation(
			server.WsCreateMessageValidation wsCreateMessageValidation) {
		// TODO : fill this with the necessary business logic

		String archiveIdentifier = wsCreateMessageValidation.getArchiveDestructionId();

		try {
			HttpURLConnection connection = (HttpURLConnection) new URL(
					"http://localhost:8080/alfresco/service/saem/elimination-confirmation?archiveIdentifier="
							+ archiveIdentifier).openConnection();
			connection.setRequestMethod("GET");

			// Write auth header
			BASE64Encoder encoder = new BASE64Encoder();
			String encodedCredential = encoder
					.encode((wsCreateMessageValidation.getLogin() + ":" + wsCreateMessageValidation
							.getPassword()).getBytes());
			connection.setRequestProperty("Authorization", "BASIC " + encodedCredential);

			// Do request
			connection.connect();
			connection.getInputStream();
			connection.disconnect();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		server.WsCreateMessageValidationResponse result = new server.WsCreateMessageValidationResponse();
		result.setWsCreateMessageValidationReturn("0");
		return result;
	}
	
    /**
     * Auto generated method signature
     * Permet de faire une demande de restitution
                                 * @param wsRestitution 
         * @return wsRestitutionResponse 
     */
    
             public server.WsRestitutionResponse wsRestitution
              (
              server.WsRestitution wsRestitution
              )
        {
            	 try {
         			String bordereauFolder = System.getProperty("java.io.tmpdir") + "/asalaereponse/";
         			File directory = new File(bordereauFolder);
         			InputStream is = wsRestitution.getBordereau().getInputStream();

         			if (!directory.exists()) {
         				directory.mkdir();
         			}
         			
         			String startName = Long.toString(new Date().getTime());
         			
         			//On stocke le bordereau
         			String tmpFileName = startName + ".xml";
         			FileOutputStream outputStream = new FileOutputStream(
         					new File(bordereauFolder + "/" + tmpFileName));

         			int read = 0;
         			byte[] bytes = new byte[1024];

         			while ((read = is.read(bytes)) != -1) {
         				outputStream.write(bytes, 0, read);
         			}
         			outputStream.close();
         			
         			//On stocke le fichier zip
         			if(wsRestitution.getDocument()!=null && wsRestitution.getDocument().getInputStream()!=null){
         				InputStream iszip = wsRestitution.getDocument().getInputStream();
         				String tmpZipName = startName + ".zip";
             			FileOutputStream outputStreamzip = new FileOutputStream(
             					new File(bordereauFolder + "/" + tmpZipName));

             			int readzip = 0;
             			byte[] byteszip = new byte[1024];

             			while ((readzip = iszip.read(byteszip)) != -1) {
             				outputStreamzip.write(byteszip, 0, readzip);
             			}
             			outputStreamzip.close();
         			}
         			
         		} catch (Exception ex) {
         			ex.printStackTrace();
         		}

         		callService(wsRestitution.getLogin(), wsRestitution.getPassword());
         		server.WsRestitutionResponse wmnr = new server.WsRestitutionResponse();
         		wmnr.setWsRestitutionReturn("0");

         		return wmnr;
    }
             
             /**
              * Auto generated method signature
              * Récupérer la valeur d'un compteur
                                          * @param wsGetCompteur 
                  * @return wsGetCompteurResponse 
              */
             
                      public server.WsGetCompteurResponse wsGetCompteur
                       (
                       server.WsGetCompteur wsGetCompteur
                       )
                 {
                     //TODO : fill this with the necessary business logic
                     throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#wsGetCompteur");
             }
          
             
             /**
              * Auto generated method signature
              * Dépose une archive et son bordereau de transfert dans AS@LAE
                                          * @param wsFichiersVolumineux 
                  * @return wsFichiersVolumineuxResponse 
              */
             
                      public server.WsFichiersVolumineuxResponse wsFichiersVolumineux
                       (
                       server.WsFichiersVolumineux wsFichiersVolumineux
                       )
                 {
                    	  File rep = new File(wsFichiersVolumineux.getRep());
                    	  String bordereauFolder = System.getProperty("java.io.tmpdir") + "/asalaereponse/";
               			
                    	  for(File child:rep.listFiles()){
                    		  File newFile = new File (bordereauFolder+child.getName());
                    		  child.renameTo(newFile);                    		  
                    	  }
                    	  
                    	  callService(wsFichiersVolumineux.getLogin(), wsFichiersVolumineux.getPassword());

                    	  
                    	  server.WsFichiersVolumineuxResponse result = new server.WsFichiersVolumineuxResponse();
                    	  result.setWsFichiersVolumineuxReturn("0");
                    	  return result;
             }

	/**
	 * Auto generated method signature Récupere l'etat d'une demande de
	 * validation
	 * 
	 * @param wsMessageValidationState
	 * @return wsMessageValidationStateResponse
	 */

	public server.WsMessageValidationStateResponse wsMessageValidationState(
			server.WsMessageValidationState wsMessageValidationState) {
		String archiveIdentifier = wsMessageValidationState.getArchiveDestructionId();
		int response = 0;

		try {
			HttpURLConnection connection = (HttpURLConnection) new URL(
					"http://localhost:8080/alfresco/service/saem/get-elimination-response?archiveIdentifier="
							+ archiveIdentifier).openConnection();
			connection.setRequestMethod("GET");

			// Write auth header
			BASE64Encoder encoder = new BASE64Encoder();
			String encodedCredential = encoder
					.encode((wsMessageValidationState.getLogin() + ":" + wsMessageValidationState
							.getPassword()).getBytes());
			connection.setRequestProperty("Authorization", "BASIC " + encodedCredential);

			// Do request
			connection.connect();
			response = connection.getResponseCode();
			connection.disconnect();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		WsMessageValidationStateResponse result = new server.WsMessageValidationStateResponse();
		if(response == 200){
			result.setWsMessageValidationStateReturn("OK");
		} else if (response == 502) {
			result.setWsMessageValidationStateReturn("NOK");
		} else {
			result.setWsMessageValidationStateReturn("");
		}
		return result;
	}

}