<import resource="classpath:/alfresco/templates/org/alfresco/import/alfresco-util.js">

function main() {
	var siteName = args.siteName;

	var connector = remote.connect("alfresco");
	var data = connector.get("/saem/expired-dua");
    var result = eval('(' + data + ')');
    model.data = result;
    model.preferences = AlfrescoUtil.getPreferences("org.alfresco.share.ExpiredDua.dashlet");

    /*var prefSimpleView = preferences.simpleView!true;

    var expiredDua = {
    	id : "ExpiredDua",
    	name : "Alfresco.dashlet.ExpiredDua",
    	options : {
    		simpleView : prefSimpleView
    	}
    };*/
    
    var dashletResizer = {
      id : "DashletResizer", 
      name : "Alfresco.widget.DashletResizer",
      initArgs : ["\"" + args.htmlid + "\"", "\"" + instance.object.id + "\""],
      useMessages : false
   };
       
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
    model.widgets = [dashletResizer, dashletTitleBarActions];
      
}
main();