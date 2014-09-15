package eu.akka.saem.alfresco.seda.annotations;

import java.lang.annotation.Retention;
import eu.akka.saem.alfresco.seda.form.FieldType;

/**
 * 
 * Annotation accessible à l'execution
 * 
 * @Class         : SEDA.java
 * @Package       : eu.akka.saem.alfresco.seda.annotations
 * @Date          : $Date: 25 févr. 2014 $:
 * @author        : $Author: THOMAS.POGNANT $
 * @version       : $Revision:  $:
 * @Id            : $Id: SEDA.java $
 *
 */
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
public @interface SEDA {
	
	String Cardinality() default "0..1";
	String FormName() default "";
	String FormDescription() default "";
	FieldType FormType() default FieldType.DIV;
	String PropertyTerm() default "";
	SEDAType Type() default SEDAType.ELEMENT;
	int Position() default 0;
}
