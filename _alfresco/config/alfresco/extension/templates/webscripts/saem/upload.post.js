var filename = null;
var content = null;
var destination = null;

// locate file attributes
for each (field in formdata.fields)
{
  if (field.name == "dest")
  {
    destination = field.value;
  }
  else if (field.name == "file" && field.isFile)
  {
    filename = field.filename;
    content = field.content;
  }
}

// ensure mandatory file attributes have been located
if (filename == undefined || content == undefined || destination == undefined)
{
  status.code = 400;
  status.message = "Uploaded file cannot be located in request";
  status.redirect = true;
}
else
{
  // create document in company home for uploaded file
  upload = search.findNode(destination);
  
  upload.properties.content.write(content);
  upload.properties.content.setEncoding("UTF-8");
  upload.properties.content.guessMimetype(filename);  
  upload.save();
 
  // setup model for response template
  model.upload = upload;
}