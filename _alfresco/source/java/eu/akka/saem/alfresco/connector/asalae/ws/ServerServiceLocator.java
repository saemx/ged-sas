/**
 * ServerServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package eu.akka.saem.alfresco.connector.asalae.ws;

/**
 * 
 * @Class         : ServerServiceLocator.java
 * @Package       : eu.akka.saem.alfresco.connector.asalae.ws
 * @Date          : $Date: 25 févr. 2014 $:
 * @author        : $Author: THOMAS.POGNANT $
 * @version       : $Revision:  $:
 * @Id            : $Id: ServerServiceLocator.java $
 *
 */
public class ServerServiceLocator extends org.apache.axis.client.Service implements ServerService {

	public ServerServiceLocator() {
	}

	public ServerServiceLocator(org.apache.axis.EngineConfiguration config) {
		super(config);
	}

	public ServerServiceLocator(String wsdlLoc, javax.xml.namespace.QName sName)
			throws javax.xml.rpc.ServiceException {
		super(wsdlLoc, sName);
	}

	// Use to get a proxy class for WebServicePort
	private String WebServicePort_address = "http://asalae.demonstrations.adullact.org/webservices/service";

	public String getWebServicePortAddress() {
		return WebServicePort_address;
	}

	// The WSDD service name defaults to the port name.
	private String WebServicePortWSDDServiceName = "WebServicePort";

	public String getWebServicePortWSDDServiceName() {
		return WebServicePortWSDDServiceName;
	}

	public void setWebServicePortWSDDServiceName(String name) {
		WebServicePortWSDDServiceName = name;
	}

	public WebServicePortType getWebServicePort() throws javax.xml.rpc.ServiceException {
		java.net.URL endpoint;
		try {
			endpoint = new java.net.URL(WebServicePort_address);
		} catch (java.net.MalformedURLException e) {
			throw new javax.xml.rpc.ServiceException(e);
		}
		return getWebServicePort(endpoint);
	}

	public WebServicePortType getWebServicePort(java.net.URL portAddress)
			throws javax.xml.rpc.ServiceException {
		try {
			WebServiceBindingStub _stub = new WebServiceBindingStub(portAddress, this);
			_stub.setPortName(getWebServicePortWSDDServiceName());
			return _stub;
		} catch (org.apache.axis.AxisFault e) {
			return null;
		}
	}

	public void setWebServicePortEndpointAddress(String address) {
		WebServicePort_address = address;
	}

	/**
	 * For the given interface, get the stub implementation. If this service has
	 * no port for the given interface, then ServiceException is thrown.
	 */
	public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
		try {
			if (WebServicePortType.class.isAssignableFrom(serviceEndpointInterface)) {
				WebServiceBindingStub _stub = new WebServiceBindingStub(new java.net.URL(
						WebServicePort_address), this);
				_stub.setPortName(getWebServicePortWSDDServiceName());
				return _stub;
			}
		} catch (Throwable t) {
			throw new javax.xml.rpc.ServiceException(t);
		}
		throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  "
				+ (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
	}

	/**
	 * For the given interface, get the stub implementation. If this service has
	 * no port for the given interface, then ServiceException is thrown.
	 */
	public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface)
			throws javax.xml.rpc.ServiceException {
		if (portName == null) {
			return getPort(serviceEndpointInterface);
		}
		String inputPortName = portName.getLocalPart();
		if ("WebServicePort".equals(inputPortName)) {
			return getWebServicePort();
		} else {
			java.rmi.Remote _stub = getPort(serviceEndpointInterface);
			((org.apache.axis.client.Stub) _stub).setPortName(portName);
			return _stub;
		}
	}

	public javax.xml.namespace.QName getServiceName() {
		return new javax.xml.namespace.QName("urn:server", "serverService");
	}

	private java.util.HashSet ports = null;

	public java.util.Iterator getPorts() {
		if (ports == null) {
			ports = new java.util.HashSet();
			ports.add(new javax.xml.namespace.QName("urn:server", "WebServicePort"));
		}
		return ports.iterator();
	}

	/**
	 * Set the endpoint address for the specified port name.
	 */
	public void setEndpointAddress(String portName, String address) throws javax.xml.rpc.ServiceException {

		if ("WebServicePort".equals(portName)) {
			setWebServicePortEndpointAddress(address);
		} else { // Unknown Port Name
			throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port"
					+ portName);
		}
	}

	/**
	 * Set the endpoint address for the specified port name.
	 */
	public void setEndpointAddress(javax.xml.namespace.QName portName, String address)
			throws javax.xml.rpc.ServiceException {
		setEndpointAddress(portName.getLocalPart(), address);
	}

}
