<import resource="classpath:/alfresco/templates/org/alfresco/import/alfresco-util.js">

function main() {
	var siteName = args.siteName;

	var connector = remote.connect("alfresco");
    model.preferences = AlfrescoUtil.getPreferences("org.alfresco.share.MyActions.dashlet");
    
//    var dashletResizer = {
//      id : "DashletResizer", 
//      name : "Alfresco.widget.DashletResizer",
//      useMessages : false
//   };
       
    var dashletTitleBarActions = {
      id : "DashletTitleBarActions", 
      name : "Alfresco.widget.DashletTitleBarActions",
      useMessages : false,
      options : {
         actions: [
            {
               cssClass: "help",
               bubbleOnClick:
               {
                  message: msg.get("dashlet.help")
               },
               tooltip: msg.get("dashlet.help.tooltip")
            }
         ]
      }
   };
    model.widgets = [/*dashletResizer,*/ dashletTitleBarActions];
      
}
main();