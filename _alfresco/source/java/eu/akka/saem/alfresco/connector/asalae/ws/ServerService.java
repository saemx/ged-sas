package eu.akka.saem.alfresco.connector.asalae.ws;

import javax.xml.rpc.ServiceException;
import javax.xml.rpc.Service;
import java.net.URL;

/**
 * 
 *   Classe d'accés aux webservices Asalae
 * 
 * @Class         : ServerService.java
 * @Package       : eu.akka.saem.alfresco.connector.asalae.ws
 * @Date          : $Date: 25 févr. 2014 $:
 * @author        : $Author: THOMAS.POGNANT $
 * @version       : $Revision:  $:
 * @Id            : $Id: ServerService.java $
 *
 */
public interface ServerService extends Service {
	public String getWebServicePortAddress();

	public WebServicePortType getWebServicePort() throws ServiceException;

	public WebServicePortType getWebServicePort(URL portAddress) throws ServiceException;
}
