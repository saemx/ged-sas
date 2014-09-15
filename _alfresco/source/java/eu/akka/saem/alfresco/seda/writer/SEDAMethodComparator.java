package eu.akka.saem.alfresco.seda.writer;

import java.lang.reflect.Method;
import java.util.Comparator;

import eu.akka.saem.alfresco.seda.annotations.SEDA;

/**
 * 
 *   Classe permettant de comparer les methodes
 * 
 * @Class         : SEDAMethodComparator.java
 * @Package       : eu.akka.saem.alfresco.seda.writer
 * @Date          : $Date: 25 févr. 2014 $:
 * @author        : $Author: THOMAS.POGNANT $
 * @version       : $Revision:  $:
 * @Id            : $Id: SEDAMethodComparator.java $
 *
 */
public class SEDAMethodComparator implements Comparator<Method>{
	 
    @Override
    public int compare(Method o1, Method o2) {
    	SEDA sedaAnnotation1 = o1.getAnnotation(SEDA.class);
    	SEDA sedaAnnotation2 = o2.getAnnotation(SEDA.class);
    	
    	if(sedaAnnotation1 == null || sedaAnnotation2 == null)
    		return 0;
    	
		if(sedaAnnotation1.Position() < sedaAnnotation2.Position())
			return -1;
		else if(sedaAnnotation1.Position() == sedaAnnotation2.Position())
			return 0;
		else
			return 1;
    }
}
