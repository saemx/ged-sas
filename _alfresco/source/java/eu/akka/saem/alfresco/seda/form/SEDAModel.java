package eu.akka.saem.alfresco.seda.form;

import java.lang.reflect.InvocationTargetException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import eu.akka.saem.alfresco.seda.loader.agape.AGAPEDescriptor;
import eu.akka.saem.alfresco.seda.loader.agape.AGAPEObject;
import eu.akka.saem.alfresco.seda.loader.rng.RNGDescriptor;
import eu.akka.saem.alfresco.seda.loader.rng.RNGObject;
import eu.akka.saem.alfresco.seda.v02.ArchiveTransferType;
import eu.akka.saem.alfresco.seda.writer.SEDAWriter;

/**
 * 
 *   Description du model SEDA pour le formulaire
 * 
 * @Class         : SEDAModel.java
 * @Package       : eu.akka.saem.alfresco.seda.form
 * @Date          : $Date: 25 févr. 2014 $:
 * @author        : $Author: THOMAS.POGNANT $
 * @version       : $Revision:  $:
 * @Id            : $Id: SEDAModel.java $
 *
 */
public class SEDAModel {

	private ArchiveTransferType archiveTransferType;
	private Object models;

	public SEDAModel(Object models) {
		this(models, null);
	}

	public SEDAModel(Object models, ArchiveTransferType archiveTransferType) {
		this.models = models;
		this.archiveTransferType = archiveTransferType;
	}
	

	public ArchiveTransferType getArchiveTransferType() {
		return archiveTransferType;
	}

	public void setArchiveTransferType(ArchiveTransferType archiveTransferType) {
		this.archiveTransferType = archiveTransferType;
	}

	public Object getModels() {
		return models;
	}

	public void setModels(Object models) {
		this.models = models;
	}

	public String toJSON() {
		try {
			JSONObject model = new JSONObject();
			JSONObject object = new JSONObject();

			if (archiveTransferType != null)
				object.put("data",
						archiveTransferTypeToJSON(archiveTransferType));

			JSONObject rngData = null;

			if (models.getClass().equals(AGAPEObject.class)) {
				AGAPEObject objectModel = ((AGAPEObject) models);
				rngData = SEDAWriter.transformSEDAObjectToJSON(objectModel
						.getObject());
				AGAPEObjectToJSON(objectModel.getDescriptor(), rngData);
				model.put("model", object);
			}
			else if(models.getClass().equals(JSONObject.class)){
				model.put("model", object);
				rngData = (JSONObject) models;
			}
			else if (models.getClass().equals(RNGObject.class)) {
				RNGObject objectModel = ((RNGObject) models);
				rngData = SEDAWriter.transformSEDAObjectToJSON(objectModel
						.getObject());
				RNGObjectToJSON(objectModel.getDescriptor(), rngData);
				model.put("model", object);
			}

			object.put("dataModel", rngData);			
			return model.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
			return "";
		}
	}

	private void AGAPEObjectToJSON(AGAPEDescriptor descriptor, JSONObject object)
			throws JSONException, NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
		object.put("minOccurs", descriptor.getMinOccurs());
		object.put("maxOccurs", descriptor.getMaxOccurs());
		object.put("comment", descriptor.getComment());
		object.put("length", descriptor.getLength());

		

		if (descriptor.getChilds() != null) {
			for (AGAPEDescriptor desc : descriptor.getChilds()) {
				if (object.get("value").getClass().equals(JSONArray.class)) {

					JSONArray array = (JSONArray) object.get("value");

					for (int i = 0; i < array.length(); i++) {
						JSONObject ob = array.getJSONObject(i);
						if (ob.get("PropertyTerm").equals(desc.getName())
								&& ob.isNull("minOccurs")) {
							AGAPEObjectToJSON(desc, ob);
							break;
						}
					}
				}
			}
		}

		if (object.get("value").getClass().equals(JSONArray.class)) {
			JSONArray array = (JSONArray) object.get("value");
			for (int i = 0; i < array.length(); i++) {
				JSONObject ob = array.getJSONObject(i);				
				if(!ob.has("minOccurs"))
					ob.put("minOccurs", descriptor.getMinOccurs());
				if(!ob.has("maxOccurs"))
					ob.put("maxOccurs", descriptor.getMaxOccurs());
				if(!ob.has("comment"))
					ob.put("comment", descriptor.getComment());
				if(!ob.has("length"))
					ob.put("length", descriptor.getLength());
			}
		}
		else if(object.get("value").getClass().equals(JSONObject.class)){
			JSONObject ob = (JSONObject) object.get("value");
			if(!ob.has("minOccurs"))
				ob.put("minOccurs", descriptor.getMinOccurs());
			if(!ob.has("maxOccurs"))
				ob.put("maxOccurs", descriptor.getMaxOccurs());
			if(!ob.has("comment"))
				ob.put("comment", descriptor.getComment());
			if(!ob.has("length"))
				ob.put("length", descriptor.getLength());
		}

	}

	private void RNGObjectToJSON(RNGDescriptor descriptor, JSONObject object)
			throws JSONException, NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
		object.put("minOccurs", descriptor.getMinOccurs());
		object.put("maxOccurs", descriptor.getMaxOccurs());
		object.put("length", descriptor.getLength());

		if (descriptor.getChilds() != null) {
			for (RNGDescriptor desc : descriptor.getChilds()) {
				if (object.get("value").getClass().equals(JSONArray.class)) {

					JSONArray array = (JSONArray) object.get("value");
					Integer num = 0;
					if (desc.getName().endsWith("]")) {
						num = Integer.parseInt((desc.getName().split("\\[")[1])
								.replace("]", ""));
					}

					for (int i = 0; i < array.length(); i++) {
						JSONObject ob = array.getJSONObject(i);
						if (desc.getName().startsWith(
								(String) ob.get("PropertyTerm"))) {
							if (num == 0) {
								RNGObjectToJSON(desc, ob);
								break;
							} else {
								num--;
							}
						}
					}
				}
			}
		} else {
			if (object.get("value").getClass().equals(JSONArray.class)) {
				JSONArray array = (JSONArray) object.get("value");
				for (int i = 0; i < array.length(); i++) {
					JSONObject ob = array.getJSONObject(i);
					ob.put("minOccurs", descriptor.getMinOccurs());
					ob.put("maxOccurs", descriptor.getMaxOccurs());
					ob.put("length", descriptor.getLength());
				}
			}
		}
	}

	private JSONObject archiveTransferTypeToJSON(Object object) {
		if (object == null)
			return null;

		return SEDAWriter.transformSEDAObjectToJSON(object);
	}

}
