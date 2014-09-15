package eu.akka.saem.alfresco.connector.asalae.ws;

/**
 * 
 * @Class         : WebServiceBindingStub.java
 * @Package       : eu.akka.saem.alfresco.connector.asalae.ws
 * @Date          : $Date: 25 févr. 2014 $:
 * @author        : $Author: THOMAS.POGNANT $
 * @version       : $Revision:  $:
 * @Id            : $Id: WebServiceBindingStub.java $
 *
 */
public class WebServiceBindingStub extends org.apache.axis.client.Stub implements WebServicePortType {
	private java.util.Vector cachedSerClasses = new java.util.Vector();
	private java.util.Vector cachedSerQNames = new java.util.Vector();
	private java.util.Vector cachedSerFactories = new java.util.Vector();
	private java.util.Vector cachedDeserFactories = new java.util.Vector();

	static org.apache.axis.description.OperationDesc[] _operations;

	static {
		_operations = new org.apache.axis.description.OperationDesc[9];
		_initOperationDesc1();
	}

	private static void _initOperationDesc1() {
		org.apache.axis.description.OperationDesc oper;
		org.apache.axis.description.ParameterDesc param;
		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("wsDepot");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("",
				"nomBordereau"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName(
						"http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "bordereau"),
				org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName(
						"http://www.w3.org/2001/XMLSchema", "base64Binary"), byte[].class, false, false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(
				new javax.xml.namespace.QName("", "nomDocument"),
				org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName(
						"http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "document"),
				org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName(
						"http://www.w3.org/2001/XMLSchema", "base64Binary"), byte[].class, false, false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("",
				"typeDocument"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName(
						"http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "login"),
				org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName(
						"http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "password"),
				org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName(
						"http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		oper.setReturnClass(String.class);
		oper.setReturnQName(new javax.xml.namespace.QName("", "wsDepotReturn"));
		oper.setStyle(org.apache.axis.constants.Style.RPC);
		oper.setUse(org.apache.axis.constants.Use.ENCODED);
		_operations[0] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("wsGSeda");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "params"),
				org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName(
						"http://www.w3.org/2001/XMLSchema", "anyType"), Object.class, false, false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "login"),
				org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName(
						"http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "password"),
				org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName(
						"http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		oper.setReturnClass(String.class);
		oper.setReturnQName(new javax.xml.namespace.QName("", "wsGSedaReturn"));
		oper.setStyle(org.apache.axis.constants.Style.RPC);
		oper.setUse(org.apache.axis.constants.Use.ENCODED);
		_operations[1] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("wsGetVersion");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "login"),
				org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName(
						"http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "password"),
				org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName(
						"http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		oper.setReturnClass(String.class);
		oper.setReturnQName(new javax.xml.namespace.QName("", "wsGetVersionReturn"));
		oper.setStyle(org.apache.axis.constants.Style.RPC);
		oper.setUse(org.apache.axis.constants.Use.ENCODED);
		_operations[2] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("wsGetMessage");
		param = new org.apache.axis.description.ParameterDesc(
				new javax.xml.namespace.QName("", "typeEchange"),
				org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName(
						"http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(
				new javax.xml.namespace.QName("", "typeMessage"),
				org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName(
						"http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("",
				"identifiantMessage"), org.apache.axis.description.ParameterDesc.IN,
				new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class,
				false, false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "login"),
				org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName(
						"http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "password"),
				org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName(
						"http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		oper.setReturnClass(String.class);
		oper.setReturnQName(new javax.xml.namespace.QName("", "wsGetMessageReturn"));
		oper.setStyle(org.apache.axis.constants.Style.RPC);
		oper.setUse(org.apache.axis.constants.Use.ENCODED);
		_operations[3] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("wsGetProfil");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "login"),
				org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName(
						"http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "password"),
				org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName(
						"http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		oper.setReturnClass(String.class);
		oper.setReturnQName(new javax.xml.namespace.QName("", "wsGetProfilReturn"));
		oper.setStyle(org.apache.axis.constants.Style.RPC);
		oper.setUse(org.apache.axis.constants.Use.ENCODED);
		_operations[4] = oper;

		/** SAEM-80 : Webservice pour la récupération des DUA expirées **/
		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("wsGetDuaExpirees");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "login"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyType"));
		oper.setReturnClass(Object.class);
		oper.setReturnQName(new javax.xml.namespace.QName("", "wsGetDuaExpireesReturn"));
		oper.setStyle(org.apache.axis.constants.Style.RPC);
		oper.setUse(org.apache.axis.constants.Use.ENCODED);
		_operations[5] = oper;
		/******************************************************************/

		/** SAEM-80 : Webservice de complétion du bordereau **/
		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("wsCompleterBordereau");
		param = new org.apache.axis.description.ParameterDesc(
				new javax.xml.namespace.QName("", "bordereau"),
				org.apache.axis.description.ParameterDesc.IN,
				new javax.xml.namespace.QName(
						"http://www.w3.org/2001/XMLSchema", "base64Binary"),
						byte[].class, false, false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(
				new javax.xml.namespace.QName("", "login"),
				org.apache.axis.description.ParameterDesc.IN,
				new javax.xml.namespace.QName(
						"http://www.w3.org/2001/XMLSchema", "string"),
						String.class, false, false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(
				new javax.xml.namespace.QName("", "password"),
				org.apache.axis.description.ParameterDesc.IN,
				new javax.xml.namespace.QName(
						"http://www.w3.org/2001/XMLSchema", "string"),
						String.class, false, false);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName(
				"http://www.w3.org/2001/XMLSchema", "base64Binary"));
		oper.setReturnClass(byte[].class);
		oper.setReturnQName(new javax.xml.namespace.QName("",
				"wsCompleterBordereauReturn"));
		oper.setStyle(org.apache.axis.constants.Style.RPC);
		oper.setUse(org.apache.axis.constants.Use.ENCODED);
		_operations[6] = oper;
		/*****************************************************/
		
		 
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("wsElimination");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "nomBordereau"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "bordereau"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "base64Binary"), byte[].class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "login"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "wsEliminationReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[7] = oper;
        
        
        oper = new org.apache.axis.description.OperationDesc();
		oper.setName("wsRestitution");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("",
				"nomBordereau"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName(
						"http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "bordereau"),
				org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName(
						"http://www.w3.org/2001/XMLSchema", "base64Binary"), byte[].class, false, false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(
				new javax.xml.namespace.QName("", "nomDocument"),
				org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName(
						"http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "document"),
				org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName(
						"http://www.w3.org/2001/XMLSchema", "base64Binary"), byte[].class, false, false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("",
				"typeDocument"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName(
						"http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "login"),
				org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName(
						"http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "password"),
				org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName(
						"http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		oper.setReturnClass(String.class);
		oper.setReturnQName(new javax.xml.namespace.QName("", "wsRestitutionReturn"));
		oper.setStyle(org.apache.axis.constants.Style.RPC);
		oper.setUse(org.apache.axis.constants.Use.ENCODED);
		_operations[8] = oper;
	}

	public WebServiceBindingStub() throws org.apache.axis.AxisFault {
		this(null);
	}

	public WebServiceBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service)
			throws org.apache.axis.AxisFault {
		this(service);
		super.cachedEndpoint = endpointURL;
	}

	public WebServiceBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
		if (service == null) {
			super.service = new org.apache.axis.client.Service();
		} else {
			super.service = service;
		}
		((org.apache.axis.client.Service) super.service).setTypeMappingVersion("1.2");
	}

	protected org.apache.axis.client.Call createCall() throws java.rmi.RemoteException {
		try {
			org.apache.axis.client.Call _call = super._createCall();
			if (super.maintainSessionSet) {
				_call.setMaintainSession(super.maintainSession);
			}
			if (super.cachedUsername != null) {
				_call.setUsername(super.cachedUsername);
			}
			if (super.cachedPassword != null) {
				_call.setPassword(super.cachedPassword);
			}
			if (super.cachedEndpoint != null) {
				_call.setTargetEndpointAddress(super.cachedEndpoint);
			}
			if (super.cachedTimeout != null) {
				_call.setTimeout(super.cachedTimeout);
			}
			if (super.cachedPortName != null) {
				_call.setPortName(super.cachedPortName);
			}
			java.util.Enumeration keys = super.cachedProperties.keys();
			while (keys.hasMoreElements()) {
				String key = (String) keys.nextElement();
				_call.setProperty(key, super.cachedProperties.get(key));
			}
			return _call;
		} catch (Throwable _t) {
			throw new org.apache.axis.AxisFault("Failure trying to get the Call object", _t);
		}
	}

	public String wsDepot(String nomBordereau, byte[] bordereau, String nomDocument, byte[] document,
			String typeDocument, String login, String password) throws java.rmi.RemoteException {
		if (super.cachedEndpoint == null) {
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[0]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("urn:WebServiceAction");
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("urn:server", "wsDepot"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			Object _resp = _call.invoke(new Object[] { nomBordereau, bordereau, nomDocument, document,
					typeDocument, login, password });

			if (_resp instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException) _resp;
			} else {
				extractAttachments(_call);
				try {
					return (String) _resp;
				} catch (Exception _exception) {
					return (String) org.apache.axis.utils.JavaUtils.convert(_resp, String.class);
				}
			}
		} catch (org.apache.axis.AxisFault axisFaultException) {
			throw axisFaultException;
		}
	}
	
	public String wsRestitution(String nomBordereau, byte[] bordereau, String nomDocument, byte[] document,
			String typeDocument, String login, String password) throws java.rmi.RemoteException {
		if (super.cachedEndpoint == null) {
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[8]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("urn:WebServiceAction");
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("urn:server", "wsRestitution"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			Object _resp = _call.invoke(new Object[] { nomBordereau, bordereau, nomDocument, document,
					typeDocument, login, password });

			if (_resp instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException) _resp;
			} else {
				extractAttachments(_call);
				try {
					return (String) _resp;
				} catch (Exception _exception) {
					return (String) org.apache.axis.utils.JavaUtils.convert(_resp, String.class);
				}
			}
		} catch (org.apache.axis.AxisFault axisFaultException) {
			throw axisFaultException;
		}
	}
	
	 public String wsElimination(String nomBordereau, byte[] bordereau, String login, String password) throws java.rmi.RemoteException {
	        if (super.cachedEndpoint == null) {
	            throw new org.apache.axis.NoEndPointException();
	        }
	        org.apache.axis.client.Call _call = createCall();
	        _call.setOperation(_operations[7]);
	        _call.setUseSOAPAction(true);
	        _call.setSOAPActionURI("urn:WebServiceAction");
	        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
	        _call.setOperationName(new javax.xml.namespace.QName("urn:server", "wsElimination"));

	        setRequestHeaders(_call);
	        setAttachments(_call);
	 try {        Object _resp = _call.invoke(new Object[] {nomBordereau, bordereau, login, password});

	        if (_resp instanceof java.rmi.RemoteException) {
	            throw (java.rmi.RemoteException)_resp;
	        }
	        else {
	            extractAttachments(_call);
	            try {
	                return (String) _resp;
	            } catch (Exception _exception) {
	                return (String) org.apache.axis.utils.JavaUtils.convert(_resp, String.class);
	            }
	        }
	  } catch (org.apache.axis.AxisFault axisFaultException) {
	  throw axisFaultException;
	}
	    }


	public String wsGSeda(Object nomBordereau, String login, String password) throws java.rmi.RemoteException {
		if (super.cachedEndpoint == null) {
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[1]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("urn:WebServiceAction");
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("urn:server", "wsGSeda"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			Object _resp = _call.invoke(new Object[] { nomBordereau, login, password });

			if (_resp instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException) _resp;
			} else {
				extractAttachments(_call);
				try {
					return (String) _resp;
				} catch (Exception _exception) {
					return (String) org.apache.axis.utils.JavaUtils.convert(_resp, String.class);
				}
			}
		} catch (org.apache.axis.AxisFault axisFaultException) {
			throw axisFaultException;
		}
	}

	public String wsGetProfil(String login, String password) throws java.rmi.RemoteException {
		if (super.cachedEndpoint == null) {
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[4]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("urn:WebServiceAction");
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("urn:server", "wsGetProfil"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			Object _resp = _call.invoke(new Object[] { login, password });

			if (_resp instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException) _resp;
			} else {
				extractAttachments(_call);
				try {
					return (String) _resp;
				} catch (Exception _exception) {
					return (String) org.apache.axis.utils.JavaUtils.convert(_resp, String.class);
				}
			}
		} catch (org.apache.axis.AxisFault axisFaultException) {
			throw axisFaultException;
		}

	}

	public String wsGetVersion(String login, String password) throws java.rmi.RemoteException {
		if (super.cachedEndpoint == null) {
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[2]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("urn:WebServiceAction");
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("urn:server", "wsGetVersion"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			Object _resp = _call.invoke(new Object[] { login, password });

			if (_resp instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException) _resp;
			} else {
				extractAttachments(_call);
				try {
					return (String) _resp;
				} catch (Exception _exception) {
					return (String) org.apache.axis.utils.JavaUtils.convert(_resp, String.class);
				}
			}
		} catch (org.apache.axis.AxisFault axisFaultException) {
			throw axisFaultException;
		}

	}

	public String wsGetMessage(String typeEchange, String typeMessage, String identifiantMessage,
			String login, String password) throws java.rmi.RemoteException {
		if (super.cachedEndpoint == null) {
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[3]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("urn:WebServiceAction");
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("urn:server", "wsGetMessage"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			Object _resp = _call.invoke(new Object[] { typeEchange, typeMessage, identifiantMessage, login,
					password });

			if (_resp instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException) _resp;
			} else {
				extractAttachments(_call);
				try {
					return (String) _resp;
				} catch (Exception _exception) {
					return (String) org.apache.axis.utils.JavaUtils.convert(_resp, String.class);
				}
			}
		} catch (org.apache.axis.AxisFault axisFaultException) {
			throw axisFaultException;
		}

	}

	/**
	 * SAEM-80 Webservice pour la récupération des DUA expirées
	 * @author romain.chiquois
	 */
	public Object wsGetDuaExpirees(String login, String password) throws java.rmi.RemoteException {
		if (super.cachedEndpoint == null) {
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[5]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("urn:WebServiceAction");
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("urn:server", "wsGetDuaExpirees"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {        Object _resp = _call.invoke(new Object[] {login, password});

		if (_resp instanceof java.rmi.RemoteException) {
			throw (java.rmi.RemoteException)_resp;
		}
		else {
			extractAttachments(_call);
			return _resp;		 
		}
		} catch (org.apache.axis.AxisFault axisFaultException) {
			throw axisFaultException;
		}
	}

	/**
	 * SAEM-80 Webservice de complétion du bordereau
	 * @author romain.chiquois
	 */
	public byte[] wsCompleterBordereau(Object bordereau, String login,
			String password) {
		try{
		if (super.cachedEndpoint == null) {
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[6]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("urn:WebServiceAction");
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("urn:server",
				"wsCompleterBordereau"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			Object _resp = _call.invoke(new Object[] { bordereau, login,
					password });

			if (_resp instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException) _resp;
			} else {
				extractAttachments(_call);
				try {
					return (byte[]) _resp;
				} catch (Exception _exception) {
					return (byte[]) org.apache.axis.utils.JavaUtils.convert(
							_resp, byte[].class);
				}
			}
		} catch (org.apache.axis.AxisFault axisFaultException) {
			throw axisFaultException;
		}
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}


}
