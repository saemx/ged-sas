package eu.akka.saem.alfresco.seda.v02.simpleType;

import eu.akka.saem.alfresco.seda.annotations.SEDA;

/**
 * 
 * 
**/

public enum CharacterSetEncodingCodeContentType {

/**
 * ASCII 7 bit
 * ASCII 7 bit code.
 **/ 
 @SEDA(PropertyTerm="1", FormName="ASCII 7 bit",FormDescription="ASCII 7 bit code.")
NUM1,
/**
 * ASCII 8 bit
 * ASCII 8 bit code.
 **/ 
 @SEDA(PropertyTerm="2", FormName="ASCII 8 bit",FormDescription="ASCII 8 bit code.")
NUM2,
/**
 * Code page 500 (EBCDIC Multinational No. 5)
 * Encoding schema for the repertoire as defined by the code
             page.
 **/ 
 @SEDA(PropertyTerm="3", FormName="Code page 500 (EBCDIC Multinational No. 5)",FormDescription="Encoding schema for the repertoire as defined by the code             page.")
NUM3,
/**
 * Code page 850 (IBM PC Multinational)
 * Encoding schema for the repertoire as defined by the code
             page.
 **/ 
 @SEDA(PropertyTerm="4", FormName="Code page 850 (IBM PC Multinational)",FormDescription="Encoding schema for the repertoire as defined by the code             page.")
NUM4,
/**
 * UCS-2
 * Universal Multiple-Octet Coded Character Set (UCS)
             two-octet per character encoding schema as defined in
             ISO/IEC 10646-1.
 **/ 
 @SEDA(PropertyTerm="5", FormName="UCS-2",FormDescription="Universal Multiple-Octet Coded Character Set (UCS)             two-octet per character encoding schema as defined in             ISO/IEC 10646-1.")
NUM5,
/**
 * UCS-4
 * Universal Multiple-Octet Coded Character Set (UCS)
             four-octet per character encoding schema as defined in
             ISO/IEC 10646-1.
 **/ 
 @SEDA(PropertyTerm="6", FormName="UCS-4",FormDescription="Universal Multiple-Octet Coded Character Set (UCS)             four-octet per character encoding schema as defined in             ISO/IEC 10646-1.")
NUM6,
/**
 * UTF-8
 * UCS Transformation Format 8 (UTF-8) multi-octet (of
             length one to six octets) per character encoding schema
             as defined in ISO/IEC 10646-1, Annex R.
 **/ 
 @SEDA(PropertyTerm="7", FormName="UTF-8",FormDescription="UCS Transformation Format 8 (UTF-8) multi-octet (of             length one to six octets) per character encoding schema             as defined in ISO/IEC 10646-1, Annex R.")
NUM7,
/**
 * UTF-16
 * UCS Transformation Format 16 (UTF-16) two-octet per character encoding schema as defined in ISO/IEC 10646-1, Annex Q.
 **/ 
 @SEDA(PropertyTerm="8", FormName="UTF-16",FormDescription="UCS Transformation Format 16 (UTF-16) two-octet per character encoding schema as defined in ISO/IEC 10646-1, Annex Q.")
NUM8,
/**
 * Mutually agreed
 * Mutually agreed between trading partners
 **/ 
 @SEDA(PropertyTerm="zzz", FormName="Mutually agreed",FormDescription="Mutually agreed between trading partners")
NUMZZZ,
/**
 * 
 * 
 **/ 
 @SEDA(PropertyTerm="", FormName="",FormDescription="")
EMPTY
}

