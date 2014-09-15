package eu.akka.saem.share.evaluator;

import org.alfresco.web.evaluator.BaseEvaluator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONException;
import org.json.simple.JSONObject;
import org.springframework.extensions.surf.RequestContext;
import org.springframework.extensions.surf.ServletUtil;
import org.springframework.extensions.surf.exception.ConnectorServiceException;
import org.springframework.extensions.surf.support.ThreadLocalRequestContext;
import org.springframework.extensions.webscripts.Status;
import org.springframework.extensions.webscripts.connector.Connector;
import org.springframework.extensions.webscripts.connector.Response;

/**
 * 
 *   Evaluator permettant de determiner s'il est possible de visualiser le bordereau d'archive
 * 
 * @Class         : IsArchiveBordereauViewable.java
 * @Package       : eu.akka.saem.share.evaluator
 * @Date          : $Date: 25 févr. 2014 $:
 * @author        : $Author: THOMAS.POGNANT $
 * @version       : $Revision:  $:
 * @Id            : $Id: IsArchiveBordereauViewable.java $
 *
 */
public class IsArchiveBordereauViewable extends BaseEvaluator {

	private static final String ALFRESCO = "alfresco";
	private static final String URL = "/saem/is-archive-bordereau-viewable?nodeRef=";

	private static final Log LOG = LogFactory.getLog(IsArchiveBordereauViewable.class);
	
	public boolean evaluate(JSONObject jsonObject) {

		String nodeRefString = (String) jsonObject.get("nodeRef");
		final RequestContext rc = ThreadLocalRequestContext.getRequestContext();

		Connector conn;
		try {
			conn = rc.getServiceRegistry().getConnectorService()
					.getConnector(ALFRESCO, rc.getUser().getId(), ServletUtil.getSession());

			Response response = conn.call(new StringBuilder(URL).append(nodeRefString).toString());

			if (response.getStatus().getCode() == Status.STATUS_OK) {
				org.json.JSONObject json = new org.json.JSONObject(response.getResponse());
				return (boolean) json.get("result");
			}

		} catch (ConnectorServiceException | JSONException e) {
			LOG.error("erreur lors de l'évaluation de possibilité d'affichage de 'Visualiser le bordereau de versement' dans le menu de l'archive", e);
		}

		return true;
	}
}
