package eu.akka.saem.alfresco.seda.v02.complexType;

import eu.akka.saem.alfresco.seda.annotations.SEDA;
import eu.akka.saem.alfresco.seda.annotations.SEDAType;
import eu.akka.saem.alfresco.seda.form.FieldType;
import eu.akka.saem.alfresco.seda.v02.simpleType.CharacterSetCodeContentType;
import eu.akka.saem.alfresco.seda.v02.simpleType.CharacterSetEncodingCodeContentType;
import eu.akka.saem.alfresco.seda.v02.simpleType.FileTypeCodeType;
import eu.akka.saem.alfresco.seda.v02.simpleType.MIMEMediaTypeContentType;

/**
 * A set of finite-length sequences of binary octets.
 * @author benjamin.catinot
 *
 */
public class ArchivesBinaryObjectType {
	
	private FileTypeCodeType format;
	private MIMEMediaTypeContentType mimeCode;
	private CharacterSetEncodingCodeContentType encodingCode;
	private CharacterSetCodeContentType characterSetCode;
	private String uri;
	private String filename = "";
	private String value;
	
	/**
	 * Attribute
	 * @return he format of the binary content.
	 */
	@SEDA(Position=2,Type=SEDAType.ATTRIBUTE,PropertyTerm="format",FormType=FieldType.COMBOBOX)
	public FileTypeCodeType getFormat() {
		return format;
	}
	
	/**
	 * Attribute
	 * @param he format of the binary content.
	 */
	@SEDA(Position=2,Type=SEDAType.ATTRIBUTE,PropertyTerm="format",FormType=FieldType.COMBOBOX)
	public void setFormat(FileTypeCodeType format) {
		this.format = format;
	}
	
	/**
	 * Attribute
	 * @return The mime type of the binary object.
	 */
	@SEDA(Position=3,Type=SEDAType.ATTRIBUTE,PropertyTerm="mimeCode",FormType=FieldType.COMBOBOX)
	public MIMEMediaTypeContentType getMimeCode() {
		return mimeCode;
	}
	
	/**
	 * Attribute
	 * @param The mime type of the binary object.
	 */
	@SEDA(Position=3,Type=SEDAType.ATTRIBUTE,PropertyTerm="mimeCode",FormType=FieldType.COMBOBOX)
	public void setMimeCode(MIMEMediaTypeContentType mimeCode) {
		this.mimeCode = mimeCode;
	}
	
	/**
	 * Attribute
	 * @return The character set of the binary object if the mime type is text.
	 */
	@SEDA(Position=5,Type=SEDAType.ATTRIBUTE,PropertyTerm="characterSetCode",FormType=FieldType.COMBOBOX)
	public CharacterSetCodeContentType getCharacterSetCode() {
		return characterSetCode;
	}
	
	/**
	 * Attribute
	 * @param The character set of the binary object if the mime type is text.
	 */
	@SEDA(Position=5,Type=SEDAType.ATTRIBUTE,PropertyTerm="characterSetCode",FormType=FieldType.COMBOBOX)
	public void setCharacterSetCode(CharacterSetCodeContentType characterSetCode) {
		this.characterSetCode = characterSetCode;
	}
	
	/**
	 * Attribute
	 * @return The Uniform Resource Identifier that identifies where the binary object is located.
	 */
	@SEDA(Position=6,Type=SEDAType.ATTRIBUTE,PropertyTerm="uri",FormType=FieldType.TEXT)
	public String getUri() {
		return uri;
	}
	
	/**
	 * Attribute
	 * @param The Uniform Resource Identifier that identifies where the binary object is located.
	 */
	@SEDA(Position=6,Type=SEDAType.ATTRIBUTE,PropertyTerm="uri",FormType=FieldType.TEXT)
	public void setUri(String uri) {
		this.uri = uri;
	}
	
	/**
	 * Attribute
	 * @return Specifies the decoding algorithm of the binary object.
	 */
	@SEDA(Position=7,Type=SEDAType.ATTRIBUTE,PropertyTerm="encodingCode",FormType=FieldType.COMBOBOX)
	public CharacterSetEncodingCodeContentType getEncodingCode() {
		return encodingCode;
	}
	
	/**
	 * Attribute
	 * @param Specifies the decoding algorithm of the binary object.
	 */
	@SEDA(Position=7,Type=SEDAType.ATTRIBUTE,PropertyTerm="encodingCode",FormType=FieldType.COMBOBOX)
	public void setEncodingCode(CharacterSetEncodingCodeContentType encodingCode) {
		this.encodingCode = encodingCode;
	}
	
	/**
	 * Attribute
	 * @return The filename of the binary object.
	 */
	@SEDA(Position=8,Type=SEDAType.ATTRIBUTE,PropertyTerm="filename",FormType=FieldType.TEXT)
	public String getFilename() {
		return filename;
	}
	
	/**
	 * Attribute
	 * @param The filename of the binary object.
	 */
	@SEDA(Position=8,Type=SEDAType.ATTRIBUTE,PropertyTerm="filename",FormType=FieldType.TEXT)
	public void setFilename(String filename) {
		//this.value = filename;
		this.filename = filename;
	}
	
	
	@SEDA(Position=0,Type=SEDAType.VALUE,PropertyTerm="value",FormType=FieldType.DATETIME)
	public String getValue() {
		return value;
	}

	@SEDA(Position=0,Type=SEDAType.VALUE,PropertyTerm="value",FormType=FieldType.DATETIME)
	public void setValue(String value) {
		this.value = value;
	}
		
}
