package eu.akka.saem.alfresco.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.zip.Adler32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.Deflater;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.alfresco.model.ContentModel;
import org.alfresco.service.cmr.dictionary.DictionaryService;
import org.alfresco.service.cmr.repository.ContentReader;
import org.alfresco.service.cmr.repository.ContentService;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.namespace.QName;
import org.alfresco.util.TempFileProvider;
import org.springframework.extensions.surf.util.DataUtil;


/**
 * 
 *   Classe permettant de créer un zip
 *   a partir de noeuds alfresco
 * 
 * @Class         : ZipUtils.java
 * @Package       : eu.akka.saem.alfresco.utils
 * @Date          : $Date: 25 févr. 2014 $:
 * @author        : $Author: THOMAS.POGNANT $
 * @version       : $Revision:  $:
 * @Id            : $Id: ZipUtils.java $
 *
 */
public class ZipUtils {
	
	private NodeService nodeService;
	private ContentService contentService;
	private DictionaryService dictionaryService;
	
	//job for create the temporary zip file and return byte[]
    public byte[] createZipTemp(List<NodeRef> nodes) throws IOException {
        File zip = TempFileProvider.createTempFile("archive", ".zip");
        FileOutputStream stream = new FileOutputStream(zip);
        CheckedOutputStream checksum = new CheckedOutputStream(stream, new Adler32());
        BufferedOutputStream buff = new BufferedOutputStream(checksum);
        ZipOutputStream out = new ZipOutputStream(buff);
        out.setMethod(ZipOutputStream.DEFLATED);
        out.setLevel(Deflater.BEST_COMPRESSION);
        byte[] nodeBytes = new byte[0];
        try {
            //for each nodes in workflow package the addToZip function is call for do the job.
            for (NodeRef node : nodes) {
                addToZip(node, out);
            }

        } catch (Exception e) {
            
        } finally {
            //close all streams
            out.close();
            buff.close();
            checksum.close();
            stream.close();

            //transform ZIP file into byte Array, for encode more easily
            InputStream in = new FileInputStream(zip);
            InputStream nodeIs = new BufferedInputStream(in, 4096);
            try {
                nodeBytes = DataUtil.toByteArray(nodeIs);
            } catch (Exception e) {
                e.printStackTrace();
            }

            //delete the temp ZIP file, because we got the zip file as byteArray.
            zip.delete();
        }

        return nodeBytes;
    }

    //job for add a Document node to the Zip file.
    public void addToZip(NodeRef node, ZipOutputStream out) throws IOException {
		QName nodeQnameType = nodeService.getType(node);
		String nodeName = (String) nodeService.getProperty(node, ContentModel.PROP_NAME);

		if (dictionaryService.isSubClass(nodeQnameType, ContentModel.TYPE_CONTENT)) {
			ContentReader reader = contentService.getReader(node, ContentModel.PROP_CONTENT);
			if (reader != null) {
				InputStream is = reader.getContentInputStream();

				ZipEntry entry = new ZipEntry(nodeName);
				entry.setTime(((Date) nodeService.getProperty(node, ContentModel.PROP_MODIFIED)).getTime());

				entry.setSize(reader.getSize());
				out.putNextEntry(entry);

				byte buffer[] = new byte[4096];
				while (true) {
					int nRead = is.read(buffer, 0, buffer.length);
					if (nRead <= 0) {
						break;
					}

					out.write(buffer, 0, nRead);
				}
				is.close();
				out.closeEntry();
			}
		}
    }
    
    public void setNodeService(NodeService nodeService) {
		this.nodeService = nodeService;
	}

	public void setContentService(ContentService contentService) {
		this.contentService = contentService;
	}
	
	public void setDictionaryService(DictionaryService dictionaryService) {
		this.dictionaryService = dictionaryService;
	}
}
