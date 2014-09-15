<import resource="classpath:/alfresco/site-webscripts/org/alfresco/components/documentlibrary/include/toolbar.lib.js">
<import resource="classpath:/alfresco/site-webscripts/org/alfresco/components/upload/uploadable.lib.js">

function widgets()
{
   var useTitle = "true";
   var docLibConfig = config.scoped["DocumentLibrary"];
   if (docLibConfig != null)
   {
      var tmp = docLibConfig["use-title"];
      useTitle = tmp != null ? tmp : "true";
   }

   var docListToolbar = {
      id: "DocListToolbar", 
      name: "Alfresco.DocListToolbar",
      options: {
         siteId: (page.url.templateArgs.site != null) ? page.url.templateArgs.site : "",
         rootNode: toolbar.rootNode != null ? toolbar.rootNode : "",
         hideNavBar: Boolean(toolbar.preferences.hideNavBar),
         googleDocsEnabled: toolbar.googleDocsEnabled,
         repositoryBrowsing: toolbar.rootNode != null,
         useTitle: (useTitle == "true"),
         syncMode: toolbar.syncMode != null ? toolbar.syncMode : "",
         createContentByTemplateEnabled: model.createContentByTemplateEnabled,
         createContentActions: model.createContent
      }
   };
   model.widgets = [docListToolbar];
}



function getCreateNewArchive(){
	var connector = remote.connect("alfresco");
	var siteUrl = url.getUrl();
	siteUrl = siteUrl.replace("/share/page/site/", "");
	var indexOfSlash = siteUrl.indexOf("/");
	var siteName = siteUrl.substring(0, indexOfSlash); 
	
	var data = connector.get("/saem/serviceversantinfos?siteName=" + siteName);
    var result = eval('(' + data + ')');
	
	model.siteServiceVersanttDocLibNodeRef=result.siteServiceVersantDocLibNodeRef;
	model.isInSiteServiceVersant=result.isSiteServiceVersant;
	model.hasAddChildrenPermission=result.hasAddChildrenPermission;
}

getCreateNewArchive();
widgets();
