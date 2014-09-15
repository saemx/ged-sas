<import resource="classpath:/alfresco/templates/webscripts/org/alfresco/slingshot/search/search.lib.js">

function main(){
	var siteName = ServiceVersantHelper.getServiceVersantSiteName();

	model.data = [];

	var request = "ASPECT:\"saem:profilable\" AND PATH:\"/app:company_home/st:sites/cm:" + siteName +"/cm:documentLibrary//*\"";
	var scriptResults = search.luceneSearch(request);

	var scriptNode, profilName, profilNodes;

	for(var i = 0 ; i < scriptResults.length ; i++){
		scriptNode = scriptResults[i];
		profilName = scriptNode.properties["saem:profilName"]
		profilNodes = search.luceneSearch("ASPECT:\"saem:profil\"");

		for (var j=0 ; j < profilNodes.length ; j++){
			if (profilNodes[j].properties["cm:name"] == profilName)
				model.data.push({
					nodeRef: profilNodes[j].nodeRef.toString(),
					name:((profilNodes[j].properties["cm:title"]===null || profilNodes[j].properties["cm:title"]=="") ? profilNodes[j].name : profilNodes[j].properties["cm:title"])
				});
		}
	}
};

main();