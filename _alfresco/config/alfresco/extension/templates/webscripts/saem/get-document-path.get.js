<import resource="classpath:/alfresco/templates/webscripts/org/alfresco/slingshot/search/search.lib.js">
function main()
{
     
   model.data = processResults([search.findNode(args.folderId)],1,resolveRootNode("alfresco://company/home"));
}

main();