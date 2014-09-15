package eu.akka.saem.alfresco.seda.actions;

import java.net.HttpURLConnection;
import java.net.URL;

import sun.misc.BASE64Encoder;


/**
 * 
 *   Classe d'analyse du dossier asalaereponse afin de detecter la présence de bordereau
 * 
 * @Class         : Reprise.java
 * @Package       : eu.akka.saem.alfresco.seda.actions
 * @Date          : $Date: 25 févr. 2014 $:
 * @author        : $Author: THOMAS.POGNANT $
 * @version       : $Revision:  $:
 * @Id            : $Id: Reprise.java $
 *
 */
public class Reprise extends Thread{
	
	private String login;
	private String pass;

	public Reprise(String login, String pass) {
		this.login = login;
		this.pass = pass;
	}

	public void run(){
		System.out.println("Relancement de l'analyse du dossier asalaereponse");
		
		try {
			Thread.sleep(30000);
			
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
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
