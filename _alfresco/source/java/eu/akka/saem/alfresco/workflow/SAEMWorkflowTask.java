package eu.akka.saem.alfresco.workflow;

import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.context.Context;
import org.alfresco.repo.workflow.activiti.ActivitiConstants;
import org.alfresco.service.ServiceRegistry;

/**
 * 
 *   Utilitaire de récupération des BEANS
 * 
 * @Class         : SAEMWorkflowTask.java
 * @Package       : eu.akka.saem.alfresco.workflow
 * @Date          : $Date: 25 févr. 2014 $:
 * @author        : $Author: THOMAS.POGNANT $
 * @version       : $Revision:  $:
 * @Id            : $Id: SAEMWorkflowTask.java $
 *
 */
public abstract class SAEMWorkflowTask {
	
    /* taken from ActivitiScriptBase.java */
    protected ServiceRegistry getServiceRegistry() {
        ProcessEngineConfigurationImpl config = Context.getProcessEngineConfiguration();
        if (config != null) {
            // Fetch the registry that is injected in the activiti spring-configuration
            ServiceRegistry registry = (ServiceRegistry) config.getBeans().get(ActivitiConstants.SERVICE_REGISTRY_BEAN_KEY);
            if (registry == null) {
                throw new RuntimeException(
                        "Service-registry not present in ProcessEngineConfiguration beans, expected ServiceRegistry with key" + 
                                ActivitiConstants.SERVICE_REGISTRY_BEAN_KEY);
            }
            return registry;
        }
        throw new IllegalStateException("No ProcessEngineCOnfiguration found in active context");
    }   
}
