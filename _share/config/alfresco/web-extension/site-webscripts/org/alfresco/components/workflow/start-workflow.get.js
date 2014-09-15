<import resource="classpath:alfresco/site-webscripts/org/alfresco/components/workflow/workflow.lib.js">

function getSAEMWorkflowDefinitions()
{
   var hiddenWorkflowNames = getHiddenWorkflowNames(),
   connector = remote.connect("alfresco");
   
   var noderef = null;
   var hasNodeRef = url.getQueryString().search("selectedItems=workspace://");
   if(hasNodeRef >= 0){
	   noderef =  url.getQueryString().substr(hasNodeRef, 74);
	   noderef = noderef.replace("selectedItems=", "");
	   var result = connector.get("/saem/denied-workflows/" + noderef.replace(":/", ""));
	   var deniededWorkflows = eval('(' + result +')').items;
	   for (i = 0 ; i < deniededWorkflows.length ; i++){
		   hiddenWorkflowNames.push(deniededWorkflows[i].name);
	   }
   }
      
   result = connector.get("/api/workflow-definitions?exclude=" + hiddenWorkflowNames.join(","));
   if (result.status == 200)
   {
      var workflows = eval('(' + result + ')').data;
      workflows.sort(sortByTitle);
      return workflows;
   }
   return [];
}

model.workflowDefinitions = getSAEMWorkflowDefinitions();

function main()
{
   // Widget instantiation metadata...
   var startWorkflow = {
      id : "StartWorkflow", 
      name : "Alfresco.component.StartWorkflow",
      options : {
         failureMessage : "message.failure",
         submitButtonMessageKey : "button.startWorkflow",
         defaultUrl : getSiteUrl("my-tasks"),
         selectedItems : (page.url.args.selectedItems != null) ? page.url.args.selectedItems: "",
         destination : (page.url.args.destination != null) ? page.url.args.destination : "",
         workflowDefinitions : model.workflowDefinitions,
         workflowName : (page.url.args.workflowName != null) ? page.url.args.workflowName : ""
      }
   };
   model.widgets = [startWorkflow];
}

main();
