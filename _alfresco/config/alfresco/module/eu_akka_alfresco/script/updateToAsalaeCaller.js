
function uploadBigFileToAsalae()
{
	if (logger.isLoggingEnabled()){
		logger.log('Appel du fichier java');
	}
	
	uploadToAsalaeTask.uploadBigFile();
	

}

uploadBigFileToAsalae();