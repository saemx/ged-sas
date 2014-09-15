package eu.akka.saem.alfresco.helper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import eu.akka.saem.alfresco.constants.SAEMPropertiesConstants;

/**
 * 
 *   Bean permettant la lecture
 *   des propriétés du fichier properties
 * 
 * @Class         : PropertyReader.java
 * @Package       : eu.akka.saem.alfresco.helper
 * @Date          : $Date: 25 févr. 2014 $:
 * @author        : $Author: THOMAS.POGNANT $
 * @version       : $Revision:  $:
 * @Id            : $Id: PropertyReader.java $
 *
 */
public class PropertyReader {

	private static Logger logger = Logger.getLogger(PropertyReader.class);
	public String property;
	Properties properties = new Properties();

	public PropertyReader() {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream inputStream = classLoader.getResourceAsStream(SAEMPropertiesConstants.PROPERTIES_FILE);
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			logger.error("Error while loading property file in PropertyReader() : " + e.getMessage());
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					logger.error("Error while closing property file in PropertyReader() : " + e.getMessage());
					e.printStackTrace();
				}
			}
		}
	}

	public String getProperty(String property) {
		return properties.getProperty(property);
	}

	public void setProperty(String property) {
		this.property = property;
	}
}

