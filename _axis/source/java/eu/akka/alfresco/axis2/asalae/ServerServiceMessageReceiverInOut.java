
/**
 * ServerServiceMessageReceiverInOut.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */
        package eu.akka.alfresco.axis2.asalae;

        /**
        *  ServerServiceMessageReceiverInOut message receiver
        */

        public class ServerServiceMessageReceiverInOut extends org.apache.axis2.receivers.AbstractInOutMessageReceiver{


        public void invokeBusinessLogic(org.apache.axis2.context.MessageContext msgContext, org.apache.axis2.context.MessageContext newMsgContext)
        throws org.apache.axis2.AxisFault{

        try {

        // get the implementation class for the Web Service
        Object obj = getTheImplementationObject(msgContext);

        ServerServiceSkeleton skel = (ServerServiceSkeleton)obj;
        //Out Envelop
        org.apache.axiom.soap.SOAPEnvelope envelope = null;
        //Find the axisOperation that has been set by the Dispatch phase.
        org.apache.axis2.description.AxisOperation op = msgContext.getOperationContext().getAxisOperation();
        if (op == null) {
        throw new org.apache.axis2.AxisFault("Operation is not located, if this is doclit style the SOAP-ACTION should specified via the SOAP Action to use the RawXMLProvider");
        }

        java.lang.String methodName;
        if((op.getName() != null) && ((methodName = org.apache.axis2.util.JavaUtils.xmlNameToJavaIdentifier(op.getName().getLocalPart())) != null)){


        

            if("wsDepot".equals(methodName)){
                
                server.WsDepotResponse wsDepotResponse1 = null;
	                        server.WsDepot wrappedParam =
                                                             (server.WsDepot)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    server.WsDepot.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               wsDepotResponse1 =
                                                   
                                                   
                                                         skel.wsDepot(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), wsDepotResponse1, false, new javax.xml.namespace.QName("urn:server",
                                                    "wsDepot"));
                                    } else 

            if("wsRestitution".equals(methodName)){
                
                server.WsRestitutionResponse wsRestitutionResponse3 = null;
	                        server.WsRestitution wrappedParam =
                                                             (server.WsRestitution)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    server.WsRestitution.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               wsRestitutionResponse3 =
                                                   
                                                   
                                                         skel.wsRestitution(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), wsRestitutionResponse3, false, new javax.xml.namespace.QName("urn:server",
                                                    "wsRestitution"));
                                    } else 

            if("wsCompleterBordereau".equals(methodName)){
                
                server.WsCompleterBordereauResponse wsCompleterBordereauResponse5 = null;
	                        server.WsCompleterBordereau wrappedParam =
                                                             (server.WsCompleterBordereau)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    server.WsCompleterBordereau.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               wsCompleterBordereauResponse5 =
                                                   
                                                   
                                                         skel.wsCompleterBordereau(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), wsCompleterBordereauResponse5, false, new javax.xml.namespace.QName("urn:server",
                                                    "wsCompleterBordereau"));
                                    } else 

            if("wsElimination".equals(methodName)){
                
                server.WsEliminationResponse wsEliminationResponse7 = null;
	                        server.WsElimination wrappedParam =
                                                             (server.WsElimination)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    server.WsElimination.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               wsEliminationResponse7 =
                                                   
                                                   
                                                         skel.wsElimination(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), wsEliminationResponse7, false, new javax.xml.namespace.QName("urn:server",
                                                    "wsElimination"));
                                    } else 

            if("wsGetDuaExpirees".equals(methodName)){
                
                server.WsGetDuaExpireesResponse wsGetDuaExpireesResponse9 = null;
	                        server.WsGetDuaExpirees wrappedParam =
                                                             (server.WsGetDuaExpirees)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    server.WsGetDuaExpirees.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               wsGetDuaExpireesResponse9 =
                                                   
                                                   
                                                         skel.wsGetDuaExpirees(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), wsGetDuaExpireesResponse9, false, new javax.xml.namespace.QName("urn:server",
                                                    "wsGetDuaExpirees"));
                                    } else 

            if("wsGetPathRepoBigFile".equals(methodName)){
                
                server.WsGetPathRepoBigFileResponse wsGetPathRepoBigFileResponse11 = null;
	                        server.WsGetPathRepoBigFile wrappedParam =
                                                             (server.WsGetPathRepoBigFile)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    server.WsGetPathRepoBigFile.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               wsGetPathRepoBigFileResponse11 =
                                                   
                                                   
                                                         skel.wsGetPathRepoBigFile(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), wsGetPathRepoBigFileResponse11, false, new javax.xml.namespace.QName("urn:server",
                                                    "wsGetPathRepoBigFile"));
                                    } else 

            if("wsModificationNotification".equals(methodName)){
                
                server.WsModificationNotificationResponse wsModificationNotificationResponse13 = null;
	                        server.WsModificationNotification wrappedParam =
                                                             (server.WsModificationNotification)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    server.WsModificationNotification.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               wsModificationNotificationResponse13 =
                                                   
                                                   
                                                         skel.wsModificationNotification(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), wsModificationNotificationResponse13, false, new javax.xml.namespace.QName("urn:server",
                                                    "wsModificationNotification"));
                                    } else 

            if("wsCreateMessageValidation".equals(methodName)){
                
                server.WsCreateMessageValidationResponse wsCreateMessageValidationResponse15 = null;
	                        server.WsCreateMessageValidation wrappedParam =
                                                             (server.WsCreateMessageValidation)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    server.WsCreateMessageValidation.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               wsCreateMessageValidationResponse15 =
                                                   
                                                   
                                                         skel.wsCreateMessageValidation(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), wsCreateMessageValidationResponse15, false, new javax.xml.namespace.QName("urn:server",
                                                    "wsCreateMessageValidation"));
                                    } else 

            if("wsMessageValidationState".equals(methodName)){
                
                server.WsMessageValidationStateResponse wsMessageValidationStateResponse17 = null;
	                        server.WsMessageValidationState wrappedParam =
                                                             (server.WsMessageValidationState)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    server.WsMessageValidationState.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               wsMessageValidationStateResponse17 =
                                                   
                                                   
                                                         skel.wsMessageValidationState(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), wsMessageValidationStateResponse17, false, new javax.xml.namespace.QName("urn:server",
                                                    "wsMessageValidationState"));
                                    } else 

            if("wsGetCompteur".equals(methodName)){
                
                server.WsGetCompteurResponse wsGetCompteurResponse19 = null;
	                        server.WsGetCompteur wrappedParam =
                                                             (server.WsGetCompteur)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    server.WsGetCompteur.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               wsGetCompteurResponse19 =
                                                   
                                                   
                                                         skel.wsGetCompteur(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), wsGetCompteurResponse19, false, new javax.xml.namespace.QName("urn:server",
                                                    "wsGetCompteur"));
                                    } else 

            if("wsGetVersion".equals(methodName)){
                
                server.WsGetVersionResponse wsGetVersionResponse21 = null;
	                        server.WsGetVersion wrappedParam =
                                                             (server.WsGetVersion)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    server.WsGetVersion.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               wsGetVersionResponse21 =
                                                   
                                                   
                                                         skel.wsGetVersion(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), wsGetVersionResponse21, false, new javax.xml.namespace.QName("urn:server",
                                                    "wsGetVersion"));
                                    } else 

            if("wsGetMessage".equals(methodName)){
                
                server.WsGetMessageResponse wsGetMessageResponse23 = null;
	                        server.WsGetMessage wrappedParam =
                                                             (server.WsGetMessage)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    server.WsGetMessage.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               wsGetMessageResponse23 =
                                                   
                                                   
                                                         skel.wsGetMessage(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), wsGetMessageResponse23, false, new javax.xml.namespace.QName("urn:server",
                                                    "wsGetMessage"));
                                    } else 

            if("wsFichiersVolumineux".equals(methodName)){
                
                server.WsFichiersVolumineuxResponse wsFichiersVolumineuxResponse25 = null;
	                        server.WsFichiersVolumineux wrappedParam =
                                                             (server.WsFichiersVolumineux)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    server.WsFichiersVolumineux.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               wsFichiersVolumineuxResponse25 =
                                                   
                                                   
                                                         skel.wsFichiersVolumineux(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), wsFichiersVolumineuxResponse25, false, new javax.xml.namespace.QName("urn:server",
                                                    "wsFichiersVolumineux"));
                                    } else 

            if("wsGSeda".equals(methodName)){
                
                server.WsGSedaResponse wsGSedaResponse27 = null;
	                        server.WsGSeda wrappedParam =
                                                             (server.WsGSeda)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    server.WsGSeda.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               wsGSedaResponse27 =
                                                   
                                                   
                                                         skel.wsGSeda(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), wsGSedaResponse27, false, new javax.xml.namespace.QName("urn:server",
                                                    "wsGSeda"));
                                    
            } else {
              throw new java.lang.RuntimeException("method not found");
            }
        

        newMsgContext.setEnvelope(envelope);
        }
        }
        catch (java.lang.Exception e) {
        throw org.apache.axis2.AxisFault.makeFault(e);
        }
        }
        
        //
            private  org.apache.axiom.om.OMElement  toOM(server.WsDepot param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(server.WsDepot.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(server.WsDepotResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(server.WsDepotResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(server.WsRestitution param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(server.WsRestitution.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(server.WsRestitutionResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(server.WsRestitutionResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(server.WsCompleterBordereau param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(server.WsCompleterBordereau.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(server.WsCompleterBordereauResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(server.WsCompleterBordereauResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(server.WsElimination param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(server.WsElimination.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(server.WsEliminationResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(server.WsEliminationResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(server.WsGetDuaExpirees param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(server.WsGetDuaExpirees.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(server.WsGetDuaExpireesResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(server.WsGetDuaExpireesResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(server.WsGetPathRepoBigFile param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(server.WsGetPathRepoBigFile.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(server.WsGetPathRepoBigFileResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(server.WsGetPathRepoBigFileResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(server.WsModificationNotification param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(server.WsModificationNotification.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(server.WsModificationNotificationResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(server.WsModificationNotificationResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(server.WsCreateMessageValidation param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(server.WsCreateMessageValidation.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(server.WsCreateMessageValidationResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(server.WsCreateMessageValidationResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(server.WsMessageValidationState param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(server.WsMessageValidationState.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(server.WsMessageValidationStateResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(server.WsMessageValidationStateResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(server.WsGetCompteur param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(server.WsGetCompteur.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(server.WsGetCompteurResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(server.WsGetCompteurResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(server.WsGetVersion param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(server.WsGetVersion.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(server.WsGetVersionResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(server.WsGetVersionResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(server.WsGetMessage param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(server.WsGetMessage.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(server.WsGetMessageResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(server.WsGetMessageResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(server.WsFichiersVolumineux param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(server.WsFichiersVolumineux.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(server.WsFichiersVolumineuxResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(server.WsFichiersVolumineuxResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(server.WsGSeda param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(server.WsGSeda.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(server.WsGSedaResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(server.WsGSedaResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, server.WsDepotResponse param, boolean optimizeContent, javax.xml.namespace.QName methodQName)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(server.WsDepotResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private server.WsDepotResponse wrapwsDepot(){
                                server.WsDepotResponse wrappedElement = new server.WsDepotResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, server.WsRestitutionResponse param, boolean optimizeContent, javax.xml.namespace.QName methodQName)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(server.WsRestitutionResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private server.WsRestitutionResponse wrapwsRestitution(){
                                server.WsRestitutionResponse wrappedElement = new server.WsRestitutionResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, server.WsCompleterBordereauResponse param, boolean optimizeContent, javax.xml.namespace.QName methodQName)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(server.WsCompleterBordereauResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private server.WsCompleterBordereauResponse wrapwsCompleterBordereau(){
                                server.WsCompleterBordereauResponse wrappedElement = new server.WsCompleterBordereauResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, server.WsEliminationResponse param, boolean optimizeContent, javax.xml.namespace.QName methodQName)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(server.WsEliminationResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private server.WsEliminationResponse wrapwsElimination(){
                                server.WsEliminationResponse wrappedElement = new server.WsEliminationResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, server.WsGetDuaExpireesResponse param, boolean optimizeContent, javax.xml.namespace.QName methodQName)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(server.WsGetDuaExpireesResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private server.WsGetDuaExpireesResponse wrapwsGetDuaExpirees(){
                                server.WsGetDuaExpireesResponse wrappedElement = new server.WsGetDuaExpireesResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, server.WsGetPathRepoBigFileResponse param, boolean optimizeContent, javax.xml.namespace.QName methodQName)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(server.WsGetPathRepoBigFileResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private server.WsGetPathRepoBigFileResponse wrapwsGetPathRepoBigFile(){
                                server.WsGetPathRepoBigFileResponse wrappedElement = new server.WsGetPathRepoBigFileResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, server.WsModificationNotificationResponse param, boolean optimizeContent, javax.xml.namespace.QName methodQName)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(server.WsModificationNotificationResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private server.WsModificationNotificationResponse wrapwsModificationNotification(){
                                server.WsModificationNotificationResponse wrappedElement = new server.WsModificationNotificationResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, server.WsCreateMessageValidationResponse param, boolean optimizeContent, javax.xml.namespace.QName methodQName)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(server.WsCreateMessageValidationResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private server.WsCreateMessageValidationResponse wrapwsCreateMessageValidation(){
                                server.WsCreateMessageValidationResponse wrappedElement = new server.WsCreateMessageValidationResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, server.WsMessageValidationStateResponse param, boolean optimizeContent, javax.xml.namespace.QName methodQName)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(server.WsMessageValidationStateResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private server.WsMessageValidationStateResponse wrapwsMessageValidationState(){
                                server.WsMessageValidationStateResponse wrappedElement = new server.WsMessageValidationStateResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, server.WsGetCompteurResponse param, boolean optimizeContent, javax.xml.namespace.QName methodQName)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(server.WsGetCompteurResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private server.WsGetCompteurResponse wrapwsGetCompteur(){
                                server.WsGetCompteurResponse wrappedElement = new server.WsGetCompteurResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, server.WsGetVersionResponse param, boolean optimizeContent, javax.xml.namespace.QName methodQName)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(server.WsGetVersionResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private server.WsGetVersionResponse wrapwsGetVersion(){
                                server.WsGetVersionResponse wrappedElement = new server.WsGetVersionResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, server.WsGetMessageResponse param, boolean optimizeContent, javax.xml.namespace.QName methodQName)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(server.WsGetMessageResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private server.WsGetMessageResponse wrapwsGetMessage(){
                                server.WsGetMessageResponse wrappedElement = new server.WsGetMessageResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, server.WsFichiersVolumineuxResponse param, boolean optimizeContent, javax.xml.namespace.QName methodQName)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(server.WsFichiersVolumineuxResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private server.WsFichiersVolumineuxResponse wrapwsFichiersVolumineux(){
                                server.WsFichiersVolumineuxResponse wrappedElement = new server.WsFichiersVolumineuxResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, server.WsGSedaResponse param, boolean optimizeContent, javax.xml.namespace.QName methodQName)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(server.WsGSedaResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private server.WsGSedaResponse wrapwsGSeda(){
                                server.WsGSedaResponse wrappedElement = new server.WsGSedaResponse();
                                return wrappedElement;
                         }
                    


        /**
        *  get the default envelope
        */
        private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory){
        return factory.getDefaultEnvelope();
        }


        private  java.lang.Object fromOM(
        org.apache.axiom.om.OMElement param,
        java.lang.Class type,
        java.util.Map extraNamespaces) throws org.apache.axis2.AxisFault{

        try {
        
                if (server.WsDepot.class.equals(type)){
                
                           return server.WsDepot.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (server.WsDepotResponse.class.equals(type)){
                
                           return server.WsDepotResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (server.WsRestitution.class.equals(type)){
                
                           return server.WsRestitution.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (server.WsRestitutionResponse.class.equals(type)){
                
                           return server.WsRestitutionResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (server.WsCompleterBordereau.class.equals(type)){
                
                           return server.WsCompleterBordereau.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (server.WsCompleterBordereauResponse.class.equals(type)){
                
                           return server.WsCompleterBordereauResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (server.WsElimination.class.equals(type)){
                
                           return server.WsElimination.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (server.WsEliminationResponse.class.equals(type)){
                
                           return server.WsEliminationResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (server.WsGetDuaExpirees.class.equals(type)){
                
                           return server.WsGetDuaExpirees.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (server.WsGetDuaExpireesResponse.class.equals(type)){
                
                           return server.WsGetDuaExpireesResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (server.WsGetPathRepoBigFile.class.equals(type)){
                
                           return server.WsGetPathRepoBigFile.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (server.WsGetPathRepoBigFileResponse.class.equals(type)){
                
                           return server.WsGetPathRepoBigFileResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (server.WsModificationNotification.class.equals(type)){
                
                           return server.WsModificationNotification.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (server.WsModificationNotificationResponse.class.equals(type)){
                
                           return server.WsModificationNotificationResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (server.WsCreateMessageValidation.class.equals(type)){
                
                           return server.WsCreateMessageValidation.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (server.WsCreateMessageValidationResponse.class.equals(type)){
                
                           return server.WsCreateMessageValidationResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (server.WsMessageValidationState.class.equals(type)){
                
                           return server.WsMessageValidationState.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (server.WsMessageValidationStateResponse.class.equals(type)){
                
                           return server.WsMessageValidationStateResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (server.WsGetCompteur.class.equals(type)){
                
                           return server.WsGetCompteur.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (server.WsGetCompteurResponse.class.equals(type)){
                
                           return server.WsGetCompteurResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (server.WsGetVersion.class.equals(type)){
                
                           return server.WsGetVersion.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (server.WsGetVersionResponse.class.equals(type)){
                
                           return server.WsGetVersionResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (server.WsGetMessage.class.equals(type)){
                
                           return server.WsGetMessage.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (server.WsGetMessageResponse.class.equals(type)){
                
                           return server.WsGetMessageResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (server.WsFichiersVolumineux.class.equals(type)){
                
                           return server.WsFichiersVolumineux.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (server.WsFichiersVolumineuxResponse.class.equals(type)){
                
                           return server.WsFichiersVolumineuxResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (server.WsGSeda.class.equals(type)){
                
                           return server.WsGSeda.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (server.WsGSedaResponse.class.equals(type)){
                
                           return server.WsGSedaResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
        } catch (java.lang.Exception e) {
        throw org.apache.axis2.AxisFault.makeFault(e);
        }
           return null;
        }



    

        /**
        *  A utility method that copies the namepaces from the SOAPEnvelope
        */
        private java.util.Map getEnvelopeNamespaces(org.apache.axiom.soap.SOAPEnvelope env){
        java.util.Map returnMap = new java.util.HashMap();
        java.util.Iterator namespaceIterator = env.getAllDeclaredNamespaces();
        while (namespaceIterator.hasNext()) {
        org.apache.axiom.om.OMNamespace ns = (org.apache.axiom.om.OMNamespace) namespaceIterator.next();
        returnMap.put(ns.getPrefix(),ns.getNamespaceURI());
        }
        return returnMap;
        }

        private org.apache.axis2.AxisFault createAxisFault(java.lang.Exception e) {
        org.apache.axis2.AxisFault f;
        Throwable cause = e.getCause();
        if (cause != null) {
            f = new org.apache.axis2.AxisFault(e.getMessage(), cause);
        } else {
            f = new org.apache.axis2.AxisFault(e.getMessage());
        }

        return f;
    }

        }//end of class
    