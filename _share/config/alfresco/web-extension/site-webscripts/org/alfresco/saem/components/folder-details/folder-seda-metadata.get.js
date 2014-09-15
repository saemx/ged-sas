<import resource="classpath:/alfresco/templates/org/alfresco/import/alfresco-util.js">

function main()
{
   AlfrescoUtil.param('nodeRef');
   AlfrescoUtil.param('site', null);
   AlfrescoUtil.param('formId', null);
   
   var connector = remote.connect("alfresco");
   var data = connector.get("/saem/get-seda-properties?nodeRef=" + model.nodeRef);
   var result = eval('(' + data + ')');
   
   model.sedaproperties = result.items;
}

main();
