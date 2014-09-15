
function main(){
	var destinatioNodeRef, profileNodeRef, response;

	var clientRequest = json.toString();
	var clientJSON = eval('(' + clientRequest + ')');

	destinationNodeRef = clientJSON.destination.toString();
	profileNodeRef = clientJSON.profils.toString();
		
	model.url = "create-archive?" + "path=" + destinationNodeRef + "&profile=" + profileNodeRef;
	model.success = true;
};

main();