<import resource="classpath:/alfresco/templates/webscripts/org/alfresco/slingshot/documentlibrary/parse-args.lib.js">

function main()
{
	// Use helper function to get the arguments
	var parsedArgs = ParseArgs.getParsedArgs();
	if (parsedArgs === null)
	{
		return;
	}

	var node = ParseArgs.resolveNode(parsedArgs.nodeRef);

	var result = [];
	if (!(node.type == Packages.org.alfresco.model.ContentModel.TYPE_FOLDER))
	{	
		if (!(node.hasAspect("saem:not_verse")))
		{
			result.push({
				name: "activiti$wfVersement"
			}); 
		}
	}
	model.data = result;
}

main();