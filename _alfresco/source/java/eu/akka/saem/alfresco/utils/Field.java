package eu.akka.saem.alfresco.utils;

/**
 * 
 *   Bean pour les champs du Formulaire
 * 
 * @Class         : Field.java
 * @Package       : eu.akka.saem.alfresco.utils
 * @Date          : $Date: 25 févr. 2014 $:
 * @author        : $Author: THOMAS.POGNANT $
 * @version       : $Revision:  $:
 * @Id            : $Id: Field.java $
 *
 */
public class Field {

		private boolean editable;		
		private boolean multiple;
		private String ID;
		private String fielsName;
		private String fieldsDescription;
		private int nbmax;
		
		public Field(String ID, boolean editable, boolean multiple, int nbmax){
			this.ID = ID;
			this.multiple = multiple;
			this.nbmax = nbmax;
			this.editable = editable;
		}

		public boolean isEditable() {
			return editable;
		}

		public void setEditable(boolean editable) {
			this.editable = editable;
		}

		public boolean isMultiple() {
			return multiple;
		}

		public void setMultiple(boolean multiple) {
			this.multiple = multiple;
		}

		public int getNbmax() {
			return nbmax;
		}

		public void setNbmax(int nbmax) {
			this.nbmax = nbmax;
		}

		public String getID() {
			return ID;
		}

		public void setID(String iD) {
			ID = iD;
		}

		public String getFielsName() {
			return fielsName;
		}

		public void setFielsName(String fielsName) {
			this.fielsName = fielsName;
		}

		public String getFieldsDescription() {
			return fieldsDescription;
		}

		public void setFieldsDescription(String fieldsDescription) {
			this.fieldsDescription = fieldsDescription;
		}
}
