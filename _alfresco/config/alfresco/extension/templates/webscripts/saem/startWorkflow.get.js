function main()
{
	
	var nodeRef = args.nodeRef;
	var docNode = search.findNode(nodeRef);
	  
	// Start workflow
	var wfdef = workflow.getDefinitionByName("activiti$wfElimination");
	if (wfdef) {
		  	var wfparams = new Array();
		  	wfparams["bpm:workflowDescription"] = "Workflow d'elimination";
		//  	wfparams["bpm:candidate_group"] = people.getPerson("admin"); //personne assignée à la tache
		//  	wfparams['bpm:workflowPriority'] = 1;
		  	wfparams['wf:notifyMe'] = false;
		
		var wfpackage = workflow.createPackage(); 
		wfpackage.addNode(docNode);
		
		var wfpath = wfdef.startWorkflow(wfpackage, wfparams);
  	}
  
}

main();