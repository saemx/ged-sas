<import resource="classpath:/alfresco/templates/org/alfresco/import/alfresco-util.js">

function populateList(){	


	var result = remote.connect("alfresco").get("/saem/get-profils-seda");

	if (result.status == status.STATUS_OK)
	{
		var json = eval('(' + result.response + ')');
		model.items = json.items;
	}
	else{
		model.items = null;
	}
};

populateList();