function main() {
	var siteName = args.siteName;
	
	model.isSiteServiceVersant = ServiceVersantHelper.isSiteServiceVersant(siteName).toString();
	model.siteServiceVersantDocLibNodeRef = ServiceVersantHelper.getDocumentLibraryNodeRef();
	model.hasAddChildrenPermission = ServiceVersantHelper.hasAddChildrenPermission().toString();
}
main();