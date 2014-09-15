package eu.akka.saem.alfresco.actions;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.alfresco.model.ContentModel;
import org.alfresco.repo.jscript.BaseScopableProcessorExtension;
import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.cmr.model.FileFolderService;
import org.alfresco.service.cmr.repository.ChildAssociationRef;
import org.alfresco.service.cmr.repository.ContentReader;
import org.alfresco.service.cmr.repository.ContentService;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.cmr.search.ResultSet;
import org.alfresco.service.cmr.search.SearchParameters;
import org.alfresco.service.cmr.search.SearchService;
import org.alfresco.web.bean.repository.Repository;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mozilla.javascript.Scriptable;

import eu.akka.saem.alfresco.constants.SAEMModelConstants;
import eu.akka.saem.alfresco.constants.SAEMPropertiesConstants;
import eu.akka.saem.alfresco.helper.PropertyReader;

/**
 * 
 *   Classe permettant le transfert de fichier volumineux d'alfresco
 *   vers Asalae
 * 
 * @Class         : TransfertBigFileToAsalae.java
 * @Package       : eu.akka.saem.alfresco.actions
 * @Date          : $Date: 25 févr. 2014 $:
 * @author        : $Author: THOMAS.POGNANT $
 * @version       : $Revision:  $:
 * @Id            : $Id: TransfertBigFileToAsalae.java $
 *
 */
public class TransfertBigFileToAsalae extends BaseScopableProcessorExtension {

	private Scriptable scope;
	private ServiceRegistry serviceRegistry;
	
	private NodeService nodeService;
	private SearchService searchService;
	private FileFolderService fileFolderService;
	private ContentService contentService;
	private static boolean enCours;
	private PropertyReader propertyReader;

	private String host;
    private int port;
    private int nbbyte;
    
	private static final Log LOG = LogFactory.getLog(TransfertBigFileToAsalae.class);

	/**
	 * Method : uploadBigFile() void
	 */
	public void uploadBigFile(){
	    
		if (!enCours && propertyReader!=null && searchService!=null){
			enCours = true;
			
			try{
				List<NodeRef> nodesSearchCopy = getNodeRefToCopyIfExist();
				
				if (nodesSearchCopy.size() > 0){
					
					host = propertyReader.getProperty(SAEMPropertiesConstants.HOST_TRANSFERT_BIG_FILE);
				    port = Integer.valueOf(propertyReader.getProperty(SAEMPropertiesConstants.PORT_TRANSFERT_BIG_FILE));
				    nbbyte = Integer.valueOf(propertyReader.getProperty(SAEMPropertiesConstants.NBBYTES_TRANSFERT_BIG_FILE));
					
			        if(send("RESET").equals("FAILED")){
			        	throw new Exception("Echec de connection socket");
			        }
				    
			        sendFolder(nodesSearchCopy.get(0));
					
					//Une fois fini on supprime le repertoire
			        nodeService.deleteNode(nodesSearchCopy.get(0));
				}
				
				enCours = false;
			}
			catch (ConnectException ce){
				LOG.error("Impossible de se connecter au socket");
				enCours = false;
			}
			catch(Exception e){
				LOG.error(e);
				enCours = false;
			}
		}
	}

	/**
	 * Récupération de l'élément à copier s'il y en a un
	 * Method : getNodeRefToCopyIfExist()
	 * @return List<NodeRef>
	 */
	private List<NodeRef> getNodeRefToCopyIfExist() {
		SearchParameters sp = new SearchParameters();
		sp.addStore(Repository.getStoreRef());
		sp.setLanguage(SearchService.LANGUAGE_LUCENE);
		sp.setQuery("PATH:\"/app:company_home/app:dictionary\" +@cm\\:name:\"Copy\"");
		ResultSet rs = searchService.query(sp);
		List<NodeRef> nodesSearchCopy = rs.getNodeRefs();
		return nodesSearchCopy;
	}
    
    /**
     * Method : sendFolder()
     * @param nodeRef
     * @throws Exception void
     */
    private void sendFolder(NodeRef nodeRef) throws Exception
    {
    	for(ChildAssociationRef car: nodeService.getChildAssocs(nodeRef)){
    		if(fileFolderService.getFileInfo(car.getChildRef()).isFolder()){
    			//On envoie le nom du repertoire
                if(send("FOLDERNAME;"+ getName(car.getChildRef())).equals("FAILED"))
                	break;
                 
                //On envoi le repertoire
                sendFolder(car.getChildRef());
    		}
    		else{
    			//On envoi le fichier
                if(sendFile(car.getChildRef()).equals("FAILED"))
                	break;
    		}
    	}
    	
        if(send("ENDFOLDER").equals("FAILED")){
        }
    }
    
    /**
     * Method : sendFile()
     * @param nodeRef
     * @return
     * @throws Exception String
     */
    private String sendFile(NodeRef nodeRef) throws Exception{
        String result = "FAILED";
        
        int pos = getFilePos(nodeRef);
        
        Boolean EOF = false;
        ByteArrayOutputStream ous = new ByteArrayOutputStream();
        ContentReader reader = contentService.getReader(nodeRef, ContentModel.PROP_CONTENT);
        InputStream ios = reader.getContentInputStream();
        int nb = nbbyte;
        byte[] buffer = null;  
        if( getSize(nodeRef) - pos < nbbyte){
            buffer = new byte[(int) (getSize(nodeRef) - pos)]; 
            nb = (int) (getSize(nodeRef) - pos);
        }
        else{
            buffer = new byte[nbbyte];
            nb = nbbyte;
        }
            
        int read = 0;
        while ( (read = ios.read(buffer, 0, nb)) != -1 ) {
           Base64 base64 = new Base64();
           String req = new String(base64.encode(buffer));
           String empreinte = MD5(req);
           String response = send("FILE;" + getName(nodeRef) + ";" + empreinte + ";" + req);
           if(response.equals("FAILED"))
               throw new Exception("Echec");
           else if(response.equals("BADMD5")){
               continue;
           }
           else{
               pos += nb;
               setFilePos(nodeRef, pos);
               
               if(getSize(nodeRef) - pos < nbbyte){
	            buffer = new byte[(int) (getSize(nodeRef) - pos)]; 
	            nb = (int) (getSize(nodeRef) - pos);
                }
                else{
                    buffer = new byte[nbbyte];
                    nb = nbbyte;
                }
           }
           
           if(read < nbbyte){
               break;
           }
        }           
        
        ous.close();
        ios.close();
        
        result = "SUCCESS";
        
        return result;
    }
    
    /**
     * Method : MD5()
     * @param md5
     * @return
     * @throws NoSuchAlgorithmException String
     */
    public String MD5(String md5) throws NoSuchAlgorithmException {
    	
		java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
		byte[] array = md.digest(md5.getBytes());
		    
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < array.length; ++i) {
			sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
		}
	        
		return sb.toString();
	}
    
    /**
     * Method : send()
     * @param str
     * @return
     * @throws IOException String
     */
    public String send(String str) throws IOException{
        String result = "FAILED";
        
        //On envoi l'action
        Socket socket;
        PrintWriter out;
        socket = new Socket(host, port);
        out = new PrintWriter(socket.getOutputStream());
        out.println(str);            
        out.flush();
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        result=in.readLine();   
        socket.close();
        
        return result;
    }

    /**
     * Method : getFilePos()
     * @param nodeRef
     * @return
     * @throws Exception int
     */
    private int getFilePos(NodeRef nodeRef) throws Exception {
        int result = 0;
        if(nodeService.hasAspect(nodeRef, SAEMModelConstants.ASPECT_TRANSFERT_EN_COURS)){
            //Le transfer a deja ete commence
            result = safeLongToInt((Long) nodeService.getProperty(nodeRef, SAEMModelConstants.PROP_TAILLE_TELECHARGE));
        }
        else{
            //le transfer commence
            setFilePos(nodeRef, result);            
        }
        
        return result;
    }
    
    /**
     * Method : setFilePos()
     * @param nodeRef
     * @param pos
     * @throws Exception void
     */
    private void setFilePos(NodeRef nodeRef, int pos) throws Exception{
    	if(!nodeService.hasAspect(nodeRef, SAEMModelConstants.ASPECT_TRANSFERT_EN_COURS)){
    		nodeService.addAspect(nodeRef, SAEMModelConstants.ASPECT_TRANSFERT_EN_COURS, null);
    	}
    	
    	nodeService.setProperty(nodeRef, SAEMModelConstants.PROP_TAILLE_TELECHARGE, pos);
    	nodeService.setProperty(nodeRef, SAEMModelConstants.PROP_POURCENTAGE_D_AVANCEMENT, (pos * 100) / getSize(nodeRef));
    }
    
	/**
	 * Method : getName()
	 * @param nodeRef
	 * @return String
	 */
	private String getName(NodeRef nodeRef) {
		return (String)nodeService.getProperty(nodeRef, ContentModel.PROP_NAME);
	}
	
	/**
	 * Method : getSize()
	 * @param nodeRef
	 * @return int
	 */
	private int getSize(NodeRef nodeRef) {
		return safeLongToInt(contentService.getReader(nodeRef, ContentModel.PROP_CONTENT).getSize());
	}
	
	/**
	 * Method : safeLongToInt()
	 * @param l
	 * @return int
	 */
	private int safeLongToInt(long l) {
	    if (l < Integer.MIN_VALUE || l > Integer.MAX_VALUE) {
	        throw new IllegalArgumentException
	            (l + " cannot be cast to int without changing its value.");
	    }
	    return (int) l;
	}

	public ContentService getContentService() {
		return contentService;
	}

	public void setContentService(ContentService contentService) {
		this.contentService = contentService;
	}

	public FileFolderService getFileFolderService() {
		return fileFolderService;
	}

	public void setFileFolderService(FileFolderService fileFolderService) {
		this.fileFolderService = fileFolderService;
	}

	public ServiceRegistry getServiceRegistry() {
		return serviceRegistry;
	}

	public NodeService getNodeService() {
		return nodeService;
	}

	public void setNodeService(NodeService nodeService) {
		this.nodeService = nodeService;
	}

	public SearchService getSearchService() {
		return searchService;
	}

	public void setSearchService(SearchService searchService) {
		this.searchService = searchService;
	}

	public void setServiceRegistry(ServiceRegistry serviceRegistry) {
		this.serviceRegistry = serviceRegistry;
	}
	
	public PropertyReader getPropertyReader() {
		return propertyReader;
	}

	public void setPropertyReader(PropertyReader propertyReader) {
		this.propertyReader = propertyReader;
	}

}
