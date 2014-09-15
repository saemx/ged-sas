var createArchiveForm = {
    obj: null,
    // Default params
    params: {
		loaderId: "saem-loader",
		loadDataURL: null,
		editDataURL: null,
		addItemURL:null,
		removeDataURL:null,
		actionURL:null,
		editURL:null,
		debug: true,
		dataInsertion : "#saem-form-content-insertion",
		mode: "modif",
		htmlid : null
    },
	submit: {
		editsubmit:function(){
			 createArchiveForm.loader.setMessage("Vérification des champs");
	         createArchiveForm.loader.show();

	         if (!createArchiveForm.submit.checkField()) {
	             createArchiveForm.loader.hide();
	             return;
	         }

	         createArchiveForm.loader.setMessage("Envoi du bordereau de versement");
	         createArchiveForm.loader.show();
				
	         createArchiveForm.submit.sendFormDataForEdit();
		},
        submit: function() {
            createArchiveForm.loader.setMessage("Vérification des champs");
            createArchiveForm.loader.show();

            if (!createArchiveForm.submit.checkField()) {
                createArchiveForm.loader.hide();
                return;
            }

            createArchiveForm.loader.setMessage("Envoi du bordereau de versement");
            createArchiveForm.loader.show();
			
            createArchiveForm.submit.sendFormData();
        },
        sendFormDataForEdit: function() {
            var data = JSON.parse(JSON.stringify(createArchiveForm.params.actionParams));
            data.action = "edit";
            data.id = createArchiveForm.params.htmlid;
            $.ajax({
                type: "POST",
                dataType: 'json',
                url: createArchiveForm.params.editURL,
                data: data
            }).done(function(msg) {
                createArchiveForm.submit.sendFiles(msg);
            }).fail(function(jqXHR, textStatus) {
                createArchiveForm.loader.setMessage("");
                createArchiveForm.loader.hide();
            });
        },
        sendFormData: function() {
            var data = JSON.parse(JSON.stringify(createArchiveForm.params.actionParams));
            data.action = "save";
            data.id = createArchiveForm.params.htmlid;
            $.ajax({
                type: "POST",
                dataType: 'json',
                url: createArchiveForm.params.actionURL,
                data: data
            }).done(function(msg) {
                createArchiveForm.submit.sendFiles(msg);
            }).fail(function(jqXHR, textStatus) {
                createArchiveForm.loader.setMessage("");
                createArchiveForm.loader.hide();
            });
        },
        filesToUpload: null,
        filesName: null,
        sendFiles: function(msg) {
            createArchiveForm.submit.filesToUpload = [];
            createArchiveForm.submit.filesName = msg;
            var ifr = createArchiveForm.obj.find("#saem-form-content-insertion").find("iframe");
            for (var i = 0; i < ifr.length; i++) {
                $(ifr.get(i)).bind("load", createArchiveForm.submit.sendFileLoadEvent);
                createArchiveForm.submit.filesToUpload.push($(ifr.get(i)));
            }
            createArchiveForm.submit.sendFile();
        },
        sendFileLoadEvent: function() {
            if ($(this).contents().find("#result").length == 1) {
                createArchiveForm.submit.filesName.filesDestinations.splice(0, 1);
                createArchiveForm.submit.sendFile();
            }
            else {
                createArchiveForm.loader.setMessage("");
                createArchiveForm.loader.hide();
            }
        },
        sendFile: function() {
        	var findFile = false;
        	
        	if(createArchiveForm.submit.filesName.filesDestinations.length == 0) {
                $.ajax({
                    type: "GET",
                    cache: false,
                    url: "/share/proxy/alfresco/saem/search?folderId=" + createArchiveForm.submit.filesName.redirection
                }).done(function(msg) {
                    $("#saem-loading-info").text("Préparation de la redirection");
                    document.location.href = "/share/page/site/" + encodeURIComponent(msg.items[0].site.shortName).toLowerCase() + "/" + encodeURIComponent(msg.items[0].container).toLowerCase() + "#filter=path" + encodeURIComponent("|/" + msg.items[0].path + "/" + msg.items[0].name);
                }).fail(function(jqXHR, textStatus) {
                    document.location.href = "/share/page/";
                });
                return ;
            }
        	
        	
            for(var i = 0; i  < createArchiveForm.submit.filesToUpload.length ; i++){
            	if(createArchiveForm.submit.filesToUpload[i].contents().find("[name='file']").length == 0)
            		continue;
            	
            	var filename = createArchiveForm.submit.filesToUpload[i].contents().find("[name='file']").val().substring(createArchiveForm.submit.filesToUpload[i].contents().find("[name='file']").val().lastIndexOf('\\') + 1);
                		
            	if(filename == createArchiveForm.submit.filesName.filesDestinations[0].name){
            		findFile = true;
            		createArchiveForm.loader.setMessage("Téléversement du fichier" + createArchiveForm.submit.filesToUpload[i].contents().find("[name='file']").val().substring(createArchiveForm.submit.filesToUpload[i].contents().find("[name='file']").val().lastIndexOf('\\') + 1));
    	            createArchiveForm.submit.filesToUpload[i].contents().find("[name='dest']").val(createArchiveForm.submit.filesName.filesDestinations[0].dest);
    	            createArchiveForm.submit.filesToUpload[i].contents().find("form").submit();	
            	}
            }            		
	                
            if(!findFile){
                createArchiveForm.submit.filesName.filesDestinations.splice(0, 1);
                    createArchiveForm.submit.sendFile();
            }            
        },
        checkField: function() {
            var erreur = null;
            var inputs = createArchiveForm.obj.find("#saem-form-content-insertion").find("[formtype]");
            for (var i = 0; i < inputs.length; i++) {
                if (!createArchiveForm.form.form.checkFieldEmpty($(inputs.get(i)))) {
                    if (erreur == null) {
                        erreur = $(inputs.get(i));
						break;
                    }
                }
            }

            if (erreur != null) {
				var parent = erreur;
				while(parent.attr("moduleid")==undefined){
					parent = parent.parent();
				}
				
				createArchiveForm.form.utils.changeDisplay(parent.attr("moduleid"));				
                return false;
            }
			
            return true;
        }
    },
    utils: {
        click:function(div){
          div.click();  
        },
        ConvChar: function(str) {
            c = {'<': '&lt;', '>': '&gt;', '&': '&amp;', '"': '&quot;', "'": '&#039;',
                '#': '&#035;'};
            return str.replace(/[<&>'"#]/g, function(s) {
                return c[s];
            });
        }
    },
    start: function() {
       // On affiche le loader
	    createArchiveForm.loader.init();
        createArchiveForm.loader.show();
		
		//On récupere les données
		createArchiveForm.data.getInitData();
    },
	data:{
		getInitData:function(){
			createArchiveForm.loader.setMessage("Récupération des données");				
			$.ajax({
				type: "GET",
				dataType: 'json',
				cache: false,
				url: createArchiveForm.params.loadDataURL
			}).done(function(msg) {
				createArchiveForm.params.htmlid = msg.ifhtml;
				createArchiveForm.obj.find(createArchiveForm.params.dataInsertion).html(msg.htmldata);			
				createArchiveForm.form.init(0,createArchiveForm.form.endFunction);				
			}).fail(function(jqXHR, textStatus) {			
				createArchiveForm.warning.mess("Erreur lors du téléchargement du model et des données");
				createArchiveForm.loader.setMessage("");				
			});
		}
	},
	warning: {
        mess: function(text) {
            if (createArchiveForm.params.debug) {
                alert(text);
            }
			else{
				
			}
        }
    },
	form:{
		posx: null,
        mouseup: true,        
		initForm:function(){
			createArchiveForm.obj.find(".saem-root-cat-inf-td-folder-link").bind("click", function() {
                if ($(this).hasClass("close")) {
                    $(this).removeClass("close");
                    $(this).parent().parent().parent().parent().parent().parent().children(".saem-cat").show();
                }
                else {
                    $(this).addClass("close");
                    $(this).parent().parent().parent().parent().parent().parent().children(".saem-cat").hide();
                }
            });

            createArchiveForm.obj.find(".saem-form-create-content").bind("mouseover", function() {
                $(this).addClass("saem-form-create-content-hover");
            });

            createArchiveForm.obj.find(".saem-form-create-content").bind("mouseout", function() {
                $(this).removeClass("saem-form-create-content-hover");
            });

            createArchiveForm.obj.find(".saem-form-content-mode-buttons-first-child").bind("mouseover", function() {
                $(this).addClass("saem-form-content-mode-buttons-first-child-hover");
            });

            createArchiveForm.obj.find(".saem-form-content-mode-buttons-first-child").bind("mouseout", function() {
                $(this).removeClass("saem-form-content-mode-buttons-first-child-hover");
            });

           				
			if(createArchiveForm.obj.find("[entete]").length == 0)
				createArchiveForm.obj.find("#saem-entete").remove();

		
			if(createArchiveForm.obj.find("[description]").length == 0)
				createArchiveForm.obj.find("#saem-description").remove();
				
				
			createArchiveForm.obj.find(".saem-form-move-div").bind("mousedown", function(e) {
                createArchiveForm.form.mouseup = false;
                createArchiveForm.form.posx = e.pageX;
            });

            $(document).bind("mouseup", function() {
                createArchiveForm.form.mouseup = true;
            });

            $(document).bind("mousemove", function(e) {

                if (createArchiveForm.form.mouseup == false) {
                    var width = createArchiveForm.obj.find(".saem-form-menu").css("width").split('p')[0];

                    var newwidth = width - (createArchiveForm.form.posx - e.pageX);
                    if (newwidth >= 250 && newwidth <= 600) {
                        createArchiveForm.obj.find(".saem-form-menu").css("width", newwidth);
                        createArchiveForm.obj.find(".saem-form-content-margin").css("margin-left", newwidth + 10);

                        createArchiveForm.form.posx = e.pageX;
                    }
                }

            });
			
			createArchiveForm.obj.find(".saem-button-soumettre button").bind("click", createArchiveForm.submit.submit);
			
			createArchiveForm.obj.find(".saem-button-edit button").bind("click", createArchiveForm.submit.editsubmit);
			
			if(createArchiveForm.params.mode=="read"){
				$("#titlesaemform").text("Afficher un bordereau");
				createArchiveForm.obj.find(".saem-button-soumettre").hide();
				createArchiveForm.obj.find(".saem-button-edit").hide();
			}
			else if(createArchiveForm.params.mode=="edit"){
				$("#titlesaemform").text("Modifier un bordereau de versement");
				createArchiveForm.obj.find(".saem-button-soumettre").hide();
				createArchiveForm.obj.find(".saem-button-edit").show();
			}
			else{

				$("#titlesaemform").text("Création d'un bordereau de versement");
				createArchiveForm.obj.find(".saem-button-soumettre").show();
				createArchiveForm.obj.find(".saem-button-edit").hide();
			}
		},
		initModuleId:null,
		moduleToInit:[],
		func:null,
		endFunction:function(){
			if(createArchiveForm.form.initModuleId != null){
				createArchiveForm.form.utils.changeDisplay(createArchiveForm.form.initModuleId);
			}
			createArchiveForm.form.initForm();
		},
		init:function(moduleId,func){
			createArchiveForm.form.pushAllModuleToInit(moduleId);
			createArchiveForm.form.launchInitModule();
			
			if(func==null || func!=undefined){
				createArchiveForm.form.func = func;
			}
			else{
				createArchiveForm.form.func = null;
			}
		},
		launchInitModule:function(){
			if(createArchiveForm.form.moduleToInit.length > 0){
				window.setTimeout("createArchiveForm.form.initModule()",10);
			}
			else{
				if(createArchiveForm.form.func != null)
					createArchiveForm.form.func();
					
				createArchiveForm.loader.hide();
			}
		},
		initModule:function(){
			var module = createArchiveForm.form.moduleToInit[0];
			
			if(module.attr("init") == "true")
				createArchiveForm.form.initModuleId = module.attr("moduleid");
			
			createArchiveForm.form.moduleToInit.splice(0,1);
			if(createArchiveForm.form.moduleToInit.length > 0){
				window.setTimeout("createArchiveForm.form.initModule()",10);
			}	
			else{
				if(createArchiveForm.form.func != null)
					createArchiveForm.form.func();
				
				createArchiveForm.loader.hide();
			}
		},
		pushAllModuleToInit:function(moduleId){
			var module = createArchiveForm.obj.find("[moduleid=\""+moduleId+"\"]");
			createArchiveForm.form.moduleToInit.push(module);
			createArchiveForm.form.menu.initInMenu(module);
			createArchiveForm.form.form.initInForm(module);
			
			var modules = createArchiveForm.obj.find("[parentmoduleid=\""+moduleId+"\"]");
			for(var i = 0; i < modules.length ; i ++){
				createArchiveForm.form.pushAllModuleToInit($(modules.get(i)).attr("moduleid"));
			}
			
		},
		form:{
			removeItemList:function(id,obj){
				var data = {};
				data.action = "removeItem";
				data.id = id;
				$.ajax({
	                type: "POST",
	                dataType: 'json',
	                url: createArchiveForm.params.removeDataURL,
	                data: data
	            }).done(function(msg) {
	            	if(msg.result!="OK")
						createArchiveForm.warning.mess("Erreur lors de la mise à jour des données");	
					else{
						var parent = obj;
						while(!parent.hasClass("saem-form-content-element-list")){
							parent = parent.parent();
						}
						var addbutton = parent.find(".saem-form-content-element-inf-list-add-button");
						addbutton.show();
						var rel = obj.attr("rel")
						obj.remove();
						createArchiveForm.form.form.removeModule(rel);						
					}
	            }).fail(function(jqXHR, textStatus) {
	            	createArchiveForm.warning.mess("Erreur lors de la mise à jour des données");
	            });
			},
			removeModule:function(rel){
				var modulesid = createArchiveForm.obj.find("[moduleid=\""+rel+"\"]");
				
				var parents = createArchiveForm.obj.find("[parentmoduleid=\""+rel+"\"]");
				
				for(var i = 0; i < parents.length ; i ++){
					createArchiveForm.form.form.removeModule(parents.attr("moduleid"));
				}
				
				createArchiveForm.obj.find("[rel=\""+rel+"\"]").remove();
				modulesid.remove();
			},
			addItemList:function(id,zoneinsertion){
				var data = {};
				data.action = "additem";
				data.id = id;
				$.ajax({
	                type: "POST",
	                dataType: 'json',
	                url: createArchiveForm.params.addItemURL,
	                data: data
	            }).done(function(msg) {
					var htmllistext = $(msg.htmldata);
					var globalhtml = null;
					var rel = null;
					zoneinsertion.append(htmllistext);
					if(msg.globalhtml != null && msg.globalhtml != undefined && msg.globalhtml.length != 0){
						rel = htmllistext.attr("rel");
						for(var i = 0; i < msg.globalhtml.length; i++){
							var obj = $(msg.globalhtml[i]);
							obj.hide();
							createArchiveForm.obj.find("#saem-form-content-insertion").append(obj);						
						}
						createArchiveForm.form.init(rel);
					}
					
					createArchiveForm.form.form.initInForm(htmllistext);	
					
					if(msg.canAlwaysAdd != null && msg.canAlwaysAdd != undefined && msg.canAlwaysAdd == true){
						var parent = zoneinsertion;
						while(!parent.hasClass("saem-form-content-element-list")){
							parent = parent.parent();
						}
						var addbutton = parent.find(".saem-form-content-element-inf-list-add-button");
						addbutton.show();
					}
					else{
						var parent = zoneinsertion;
						while(!parent.hasClass("saem-form-content-element-list")){
							parent = parent.parent();
						}
						var addbutton = parent.find(".saem-form-content-element-inf-list-add-button");
						addbutton.hide();
					}
					
	            }).fail(function(jqXHR, textStatus) {
	            	createArchiveForm.warning.mess("Erreur lors de la mise à jour des données");
	            });
											
			},
			editField:function(id,val){
				var data = {};
				data.action = "edit";
				data.id = id;
				data.value = val;
				$.ajax({
	                type: "POST",
	                dataType: 'json',
	                url: createArchiveForm.params.editDataURL,
	                data: data
	            }).done(function(msg) {
	            	if(msg.result!="OK")
						createArchiveForm.warning.mess("Erreur lors de la mise à jour des données");						
	            }).fail(function(jqXHR, textStatus) {
	            	createArchiveForm.warning.mess("Erreur lors de la mise à jour des données");
	            });
			},
			initInForm:function(module){
				
				module.find("input[formType=\"date\"]").each(function( index ) {
					
					var result = "";
					var val = $(this).val().split("T")[0];
					
					if(val != undefined && val != ""){
						if(val.split("-").length == 1){
							result = val;
						}
						else if(val.split("-").length == 2){
							result = val.split("-")[1]+"/"+val.split("-")[0];
						}
						else if(val.split("-").length == 3){
							result = val.split("-")[2]+"/"+val.split("-")[1]+"/"+val.split("-")[0];
						}
                     }
					
					$(this).val(result);
					
					
					$(this).datepicker({			
							onSelect: function() {
								var val = $(this).val().split("T")[0];
								var result = "";								
								
								
								if(val != undefined && val != ""){
									if(val.split("/").length == 1){
										result = val;
									}
									else if(val.split("/").length == 2){
										result = val.split("/")[1]+"-"+val.split("/")[0];
									}
									else if(val.split("/").length == 3){
										result = val.split("/")[2]+"-"+val.split("/")[1]+"-"+val.split("/")[0];
									}
	                             }
								
								createArchiveForm.form.form.checkFieldEmpty($(this));
								createArchiveForm.form.form.refreshFieldIndicator(module.attr("moduleid"));											
								createArchiveForm.form.form.editField($(this).attr("id"),result);
							},
							dayNames: [ "Dimanche", "Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi" ],
							monthNames: [ "Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet", "Aout", "Septembre", "Octobre", "Novembre", "Décembre" ],
							monthNamesShort: [ "Janv", "Fevr", "Mars", "Avr", "Mai", "Juin", "Juil", "Aout", "Sept", "Octo", "Nove", "Déce" ],
							dayNamesShort: [ "Dim", "Lun", "Mar", "Mer", "Jeu", "Ven", "Sam" ],
							dateFormat: "dd/mm/yy",
							dayNamesMin: [ "Di", "Lu", "Ma", "Me", "Je", "Ve", "Sa" ],
							buttonImage: "/share/res/saem/css/calendar-16-2.png",
							showOn: "button",
							buttonImageOnly: true
						});	
					
					$(this).bind("blur",function(){
						createArchiveForm.form.form.checkFieldEmpty($(this));
						
						var val = $(this).val().split("T")[0];
						var result = "";
						
						if(val != undefined && val != ""){
							if(val.split("/").length == 1){
								result = val;
							}
							else if(val.split("/").length == 2){
								result = val.split("/")[1]+"-"+val.split("/")[0];
							}
							else if(val.split("/").length == 3){
								result = val.split("/")[2]+"-"+val.split("/")[1]+"-"+val.split("/")[0];
							}
                         }
						
						createArchiveForm.form.form.refreshFieldIndicator(module.attr("moduleid"));					
						createArchiveForm.form.form.editField($(this).attr("id"),result);
						
					});
				});
				
				module.find("input[formType=\"file\"]").each(function( index ) {
					var input = $(this);
					$(this).parent().find("iframe").bind("load", function() {
                        $(this).contents().find("[name=\"file\"]").bind("change", function() {
							input.val($(this).val().substring($(this).val().lastIndexOf('\\') + 1));
							createArchiveForm.form.form.checkFieldEmpty($(this));
							createArchiveForm.form.form.refreshFieldIndicator(module.attr("moduleid"));																		
							createArchiveForm.form.form.editField(input.attr("id"),input.val());
                        });
                    });
				});
				
				module.find("[formType=\"textearea\"]").bind("blur",function(){
					createArchiveForm.form.form.checkFieldEmpty($(this));
					createArchiveForm.form.form.refreshFieldIndicator(module.attr("moduleid"));					
					createArchiveForm.form.form.editField($(this).attr("id"),$(this).val());					
				});
				
				module.find("[affInMenu=\"true\"]").each(function(){
					var parent = $(this);
					while(parent.attr("moduleid") == undefined || parent.attr("moduleid") == null){
						parent = parent.parent();
					}
					
					var rel = parent.attr("moduleid");
					
					var val = $(this).val();
					if(val.length > 40){
						val = val.substring(0,40);
					}
					var menudiv =  createArchiveForm.obj.find(".saem-cat[rel=\""+rel+"\"]").find(".saem-cat-inf-td-name-link");
					var menuFirstText = menudiv.text().split(" - ")[0];
					if(val != ""){
						var newval = menuFirstText + " - " + val;					
						menudiv.text(newval);
					}
					$(this).bind("blur",function(){
						var val = $(this).val();
						
						if(val.length > 40){
							val = val.substring(0,40);
						}
						
						if(val != ""){
							var newval = menuFirstText + " - " + val;					
							menudiv.text(newval);
						}
						else{
							menudiv.text(menuFirstText);
						}
						menudiv.text(newval);
						createArchiveForm.form.utils.modifAriane(rel);
					});
					
				});
				
				module.find("[formType=\"text\"]").bind("blur",function(){
					createArchiveForm.form.form.checkFieldEmpty($(this));
					createArchiveForm.form.form.refreshFieldIndicator(module.attr("moduleid"));					
					createArchiveForm.form.form.editField($(this).attr("id"),$(this).val());					
				});
				
				module.find("[formType=\"combobox\"]").bind("blur",function(){
					createArchiveForm.form.form.checkFieldEmpty($(this));
					createArchiveForm.form.form.refreshFieldIndicator(module.attr("moduleid"));					
					createArchiveForm.form.form.editField($(this).attr("id"),$(this).val());					
				});
				
				module.find(".saem-form-content-element-e-col1 button").bind("click", function() {
					var parent = $(this);
					while(parent.find(".saem-form-content-element-help").length == 0){
						parent = parent.parent();
					}
					
					parent.find(".saem-form-content-element-help").first().show();
					var o2 = parent.find(".saem-form-content-element-help").first().find(".saem-form-content-element-help-balloon-arrow");
					parent.find(".saem-form-content-element-help").first().css("top", "-" + (o2.position().top + 15 ) + "px");					
				});
				
				module.find(".saem-form-content-element-help-closeButton").bind("click", function() {
					 var parent = $(this);
					while(parent.find(".saem-form-content-element-help").length == 0){
						parent = parent.parent();
					}
					
					parent.find(".saem-form-content-element-help").first().hide();
				});
				
				module.find(".saem-form-content-element-inf-list-add-button").bind("click",function(){
					var parent = $(this);
					while(!parent.hasClass("saem-form-content-element-list")){
						parent = parent.parent();
					}
					var zoneinsertion = parent.find(".saem-form-content-element-list-value");
					createArchiveForm.form.form.addItemList($(this).attr("id"),zoneinsertion);
				});
				
				module.find(".saem-form-content-element-inf-list-del-button").bind("click",function(){
					var parent = $(this);
					while(!parent.hasClass("saem-form-content-element")){
						parent = parent.parent();
					}
					createArchiveForm.form.form.removeItemList($(this).attr("id"),parent);
				});
				
				module.find("[rel]").bind("click",function(){
					createArchiveForm.form.utils.changeDisplay($(this).attr("rel"));
				});
				
				createArchiveForm.form.form.refreshFieldIndicator(module.attr("moduleid"));
			},
			checkFieldEmpty:function(obj){
				if(createArchiveForm.params.mode == "read")
					return true;
					
				if(obj.attr("obligatoire") != undefined){
					var erreur = obj.parent();
					while(erreur.find(".saem-form-content-element-inf-erreur").length == 0){
						erreur = erreur.parent();
					}
					
					erreur = erreur.find(".saem-form-content-element-inf-erreur");
					
					if(obj.val() == ""){
						erreur.text("Ce champ ne doit pas être vide");	
						return false;
					}	
					else{
						erreur.text("");
						return true;
					}
				}
				else{
					return true;
				}
			},
			refreshFieldIndicator:function(rel){
				if(createArchiveForm.params.mode == "read")
					return true;
					
				var divs = null;
				if(rel==undefined)
					divs = createArchiveForm.obj.find(".saem-form-menu").find("[rel]");
				else{
					divs = createArchiveForm.obj.find(".saem-form-menu").find("[rel=\""+rel+"\"]");
				}
				
				for (var i = 0; i < divs.length; i++) {
					var inputs = createArchiveForm.obj.find("[moduleid=\"" + $(divs.get(i)).attr("rel") + "\"]").find("[formtype]");
					count = 0;

					for (var j = 0; j < inputs.length; j++) {
						if(!createArchiveForm.form.form.checkFieldEmpty($(inputs.get(j)))){
							count ++;
						}
					}
					
					if ($(divs.get(i)).find(".saem-root-cat-title-nberreur").length > 0) {
						$(divs.get(i)).find(".saem-root-cat-title-nberreur").first().text(count);

					}
					else {
						$(divs.get(i)).find(".saem-cat-inf-nberreur").first().text(count);
					}

					if (count == 0) {
						if ($(divs.get(i)).find(".saem-root-cat-title-nberreur").length > 0) {
							$(divs.get(i)).find(".saem-root-cat-title-nberreur").first().text("");

						}
						else {
							$(divs.get(i)).find(".saem-cat-inf-nberreur").first().text("");
						}
					}
				}
			}
		},
		menu:{
			affMasFolder: function() {
				if ($(this).hasClass("close")) {
					$(this).removeClass("close");
					$(this).parent().parent().parent().parent().parent().children("div").show();
				}
				else {
					$(this).addClass("close");
					$(this).parent().parent().parent().parent().parent().children("div").hide();
				}
			},
	        simplifieVu: function(obj) {
	        	var isClass;
	        	for(var i = 0; i < obj.classList.length; i++){
	        		if(obj.classList[i] == "saem-form-content-mode-buttons-first-child-expert-hide"){
	        			isClass = true;
	        		}
	        	}
	        	
	            if (isClass) {
	            	$(obj).removeClass("saem-form-content-mode-buttons-first-child-expert-hide");
	            	$(obj).addClass("saem-form-content-mode-buttons-first-child-expert");


	                createArchiveForm.obj.find(".saem-form-content-mode-buttons-first-child-simple").addClass("saem-form-content-mode-buttons-first-child-simple-hide");
	                createArchiveForm.obj.find(".saem-form-content-mode-buttons-first-child-simple").removeClass("saem-form-content-mode-buttons-first-child-simple");

	                var inputs = createArchiveForm.obj.find("#saem-form-content-insertion").find(".saem-form-content-element");
	                for (var i = 0; i < inputs.length; i++) {
	                    var el = $(inputs.get(i));
	                    if (el.find('input').attr('disabled') == "disabled" && el.find('iframe').length == 0) {
	                        el.show();
	                    }
	                    if (el.find('textarea').attr('disabled') == "disabled") {
	                        el.show();
	                    }
	                    if (el.find('select').attr('disabled') == "disabled") {
	                        el.show();
	                    }
	                }

	                var lists = createArchiveForm.obj.find("#saem-form-content-insertion").find(".saem-form-content-element-list");
	                for (var i = 0; i < lists.length; i++) {
	                    var el = $(lists.get(i));
	                    if (el.find("input").attr('disabled') == "disabled" && el.find('iframe').length == 0) {
	                    	el.show();
	                        el.find("#addListItem").show();
	                        el.find("#delListItem").show();
	                    }
	                    if (el.find('textarea').attr('disabled') == "disabled") {
	                        el.show();
	                        el.find("#addListItem").show();
	                        el.find("#delListItem").show();
	                    }
	                    if (el.find('select').attr('disabled') == "disabled") {
	                        el.show();
	                        el.find("#addListItem").show();
	                        el.find("#delListItem").show();
	                    }
	                }
	            }

	        },
	        complexeVu: function(obj) {
	        	var isClass;
	        	for(var i = 0; i < obj.classList.length; i++){
	        		if(obj.classList[i] == "saem-form-content-mode-buttons-first-child-simple-hide"){
	        			isClass = true;
	        		}
	        	}
	        	
	            if (isClass) {
	                $(obj).removeClass("saem-form-content-mode-buttons-first-child-simple-hide");
	                $(obj).addClass("saem-form-content-mode-buttons-first-child-simple");


	                createArchiveForm.obj.find(".saem-form-content-mode-buttons-first-child-expert").addClass("saem-form-content-mode-buttons-first-child-expert-hide");
	                createArchiveForm.obj.find(".saem-form-content-mode-buttons-first-child-expert").removeClass("saem-form-content-mode-buttons-first-child-expert");

	                var inputs = createArchiveForm.obj.find("#saem-form-content-insertion").find(".saem-form-content-element");
	                for (var i = 0; i < inputs.length; i++) {
	                    var el = $(inputs.get(i));
	                    if (el.find('input').attr('disabled') == "disabled" && el.find('iframe').length == 0) {
	                        el.hide();
	                    }
	                    if (el.find('textarea').attr('disabled') == "disabled") {
	                        el.hide();
	                    }
	                    if (el.find('select').attr('disabled') == "disabled") {
	                        el.hide();
	                    }
	                }

	                var lists = createArchiveForm.obj.find("#saem-form-content-insertion").find(".saem-form-content-element-list");
	                for (var i = 0; i < lists.length; i++) {
	                    var el = $(lists.get(i));
	                    if (el.find("input").attr('disabled') == "disabled" && el.find('iframe').length == 0) {
	                    	el.hide();
	                        el.find("#addListItem").hide();
	                        el.find("#delListItem").hide();
	                    }
	                    if (el.find('select').attr('disabled') == "disabled") {
	                        el.hide();
	                        el.find("#addListItem").hide();
	                        el.find("#delListItem").hide();
	                    }
	                    if (el.find('textarea').attr('disabled') == "disabled") {
	                        el.hide();
	                        el.find("#addListItem").hide();
	                        el.find("#delListItem").hide();
	                    }
	                }
	            }

	        },
			initInMenu:function(template){
				
				if (template.attr("entete") == "true") {
                    var o = createArchiveForm.obj.find(".saem-form-menu #saem-entete");
                    o.attr("rel", template.attr("moduleid"));
                    o.find(".saem-root-cat-inf-td-name-link").bind("click", function() {
                        createArchiveForm.form.utils.changeDisplay(o.attr("rel"));
                    });
                    o.addClass("saem-hover");
                }
                else if (template.attr("description") == "true") {
                    var o = createArchiveForm.obj.find(".saem-form-menu #saem-description");
                    o.attr("rel", template.attr("moduleid"));
                    o.find(".saem-root-cat-inf-td-name-link").bind("click", function() {
                        createArchiveForm.form.utils.changeDisplay(o.attr("rel"));
                    });
                    o.addClass("saem-hover");
					
				}
                else {
                    var o = createArchiveForm.obj.find(".saem-form-menu [rel=" + template.attr("parentmoduleid") + "]");

                    if (o.children(".saem-cat").length == 0) {
                        var obj = o.find(".saem-cat-inf-td-file-link")
                        obj.removeClass("saem-cat-inf-td-file-link").addClass("saem-cat-inf-td-folder-link");
                        obj.parent().removeClass("saem-cat-inf-td-file").addClass("saem-cat-inf-td-folder");
						obj.unbind("click", createArchiveForm.form.menu.affMasFolder);
                        obj.bind("click", createArchiveForm.form.menu.affMasFolder);
						
                    }
                    var newo = $("<div class=\"saem-cat\"><table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" class=\"saem-cat-inf\"><tbody><tr><td class=\"saem-cat-inf-nberreur\"></td><td class=\"saem-cat-inf-td-file\"><a class=\"saem-cat-inf-td-file-link\" href=\"#\"> </a></td><td class=\"saem-cat-inf-td-name\"><span class=\"saem-cat-inf-td-name-link\"></span></td></tr></tbody></table></div>");
                    o.append(newo);
                    newo.attr("rel", template.attr("moduleid"));
                    
					var text = "";
                    if (createArchiveForm.obj.find("#saem-form-content-insertion").find("[moduleid=\"" + template.attr("parentmoduleid") + "\"]").find("[rel=\"" + template.attr("moduleid") + "\"]").find(".saem-form-content-element-inf-list-element-label").length > 0) {
                        text = createArchiveForm.obj.find("#saem-form-content-insertion").find("[moduleid=\"" + template.attr("parentmoduleid") + "\"]").find("[rel=\"" + template.attr("moduleid") + "\"]").find(".saem-form-content-element-inf-list-element-label").text();
                    }
                    else {
                        text = createArchiveForm.obj.find("#saem-form-content-insertion").find("[moduleid=\"" + template.attr("parentmoduleid") + "\"]").find("[rel=\"" + template.attr("moduleid") + "\"]").find(".saem-form-content-element-inf-label").text();
                    }
                    newo.find(".saem-cat-inf-td-name-link").text(text);
                    newo.find(".saem-cat-inf-td-name-link").bind("click", function() {
                    	createArchiveForm.form.utils.changeDisplay(newo.attr("rel"));                    	
                    });
                    newo.addClass("saem-hover");
                    
    	            createArchiveForm.obj.find(".saem-form-content-mode-buttons-first-child-expert").bind("click", function(){ 
    	            	createArchiveForm.form.menu.simplifieVu(this);
	            	});
    	            
    	            createArchiveForm.obj.find(".saem-form-content-mode-buttons-first-child-simple-hide").bind("click", function(){
    	            	createArchiveForm.form.menu.complexeVu(this);
	            	});
                }
				
			}
		},
		utils:{
			modifAriane:function(id){
				var menu = createArchiveForm.obj.find(".saem-form-menu").find("[rel=\"" + id + "\"]");
				var result = "";
				var count = null;
				while (!menu.hasClass("saem-form-menu")) {


					if (menu.attr("rel") != undefined) {
						var text = "";
						if (menu.find(".saem-root-cat-inf-td-name-link").length > 0) {
							text = $(menu.find(".saem-root-cat-inf-td-name-link").get(0)).text();
						}
						else {
							text = $(menu.find(".saem-cat-inf-td-name-link").get(0)).text();
						}
						if (result == "") {

							result = "<div rel=\"" + menu.attr("rel") + "\" class=\"saem-form-content-ariane-element\"><a href=\"#\" class=\"saem-form-content-ariane-element-folder\">&nbsp;</a><span class=\"saem-form-content-ariane-element-text\"><a href=\"\" class=\"saem-form-content-ariane-element-folder-label\">" + text + "</a></span></div>" + result;

						}
						else {
							if (count == null) {
								count = menu.attr("rel");
							}
							result = "<div rel=\"" + menu.attr("rel") + "\" class=\"saem-form-content-ariane-element\"><a href=\"#\" class=\"saem-form-content-ariane-element-folder\">&nbsp;</a><span class=\"saem-form-content-ariane-element-text\"><a href=\"\" class=\"saem-form-content-ariane-element-folder-label\">" + text + "</a></span></div>" + "<div class=\"saem-form-content-ariane-separator2\"> ></div>" + result;
						}
					}

					menu = menu.parent();
				}

				if (count == null) {
					result = "<div class=\"saem-form-content-ariane-element\"><span class=\"saem-form-content-ariane-element-first-child saem-root\"><button type=\"button\" tabindex=\"0\" title=\"Plus haut\"></button></span></div><div class=\"saem-form-content-ariane-separator\">&nbsp;</div>" + result;
				}
				else {
					result = "<div class=\"saem-form-content-ariane-element\" rel=\"" + count + "\"><span class=\"saem-form-content-ariane-element-first-child\"><button type=\"button\" tabindex=\"0\" title=\"Plus haut\"></button></span></div><div class=\"saem-form-content-ariane-separator\">&nbsp;</div>" + result;
				}
				createArchiveForm.obj.find(".saem-form-content-ariane").children().remove();
				var ariane = $(result);

				createArchiveForm.obj.find(".saem-form-content-ariane").append(ariane);
				createArchiveForm.obj.find(".saem-form-content-ariane").find(".saem-form-content-ariane-element").bind("click", function() {
					createArchiveForm.form.utils.changeDisplay($(this).attr("rel"));
					return false;
				});
				createArchiveForm.obj.find(".saem-form-content-ariane").addClass("saem-hover");
			},
			changeDisplay:function(id){
				createArchiveForm.obj.find("#saem-form-content-insertion").children().hide();
				createArchiveForm.obj.find("#saem-form-content-insertion").find("[moduleid=\"" + id + "\"]").show();
				createArchiveForm.obj.find(".saem-form-menu").find(".saem-cat-inf-sel").removeClass("saem-cat-inf-sel");
				createArchiveForm.obj.find(".saem-form-menu").find("[rel=\"" + id + "\"]").children(".saem-cat-inf").addClass("saem-cat-inf-sel");
				
				createArchiveForm.form.utils.modifAriane(id);
				var height = createArchiveForm.obj.find(".saem-form-content").height();
				var heighttmp = createArchiveForm.obj.find(".saem-form-menu").height();
				var heightpage = $(window).height() - 115 - 50;

				 /* THOMAS **/
				$(".saem-form-menu").css("height", heightpage);
				$(".saem-form-content").css("height", heightpage);
				/***/
				
				if (heighttmp > height) {
					height = heighttmp;
				}

				if (height < heightpage) {
					$(".saem-form-main").css("height", heightpage);
					height = heightpage;
				}
				
				createArchiveForm.obj.find(".saem-form-move-div").height(height);
				
				/**** THOMAS *****/
				var menuitem = createArchiveForm.obj.find(".saem-form-menu").find("[rel=" + id + "]");
				var itemoffset = menuitem.offset().top - $(".saem-form-menu").offset().top + $(".saem-form-menu").scrollTop();
				var heightmenu =  $(".saem-form-menu").height();
				
				var visiblemin = $(".saem-form-menu").scrollTop();
				var visiblemax = visiblemin + heightmenu;
				
				if(itemoffset < visiblemin){
					$(".saem-form-menu").scrollTop(0);
				}
				else if(itemoffset > visiblemax){
					$(".saem-form-menu").scrollTop(itemoffset - (itemoffset - heightmenu / 2));
				}
			
			}
		}		
	},
	loader: {
        object: [],
        loaderObject: null,        
        messageObject: null,
        state:false,
        init: function() {
            createArchiveForm.loader.loadObject = $("#" + createArchiveForm.params.loaderId);
            createArchiveForm.loader.messageObject = $("#" + createArchiveForm.params.loaderId).find(".saem-loader-text");
            //createArchiveForm.loader.object.push($("#saem-action"));
        },
        show: function() {
            for (var i = 0; i < createArchiveForm.loader.object.length; i++) {
                createArchiveForm.loader.object[i].hide();
            }
            createArchiveForm.loader.loadObject.show();
            createArchiveForm.loader.state = true;
        },
        hide: function() {
            for (var i = 0; i < createArchiveForm.loader.object.length; i++) {
                createArchiveForm.loader.object[i].show();
            }
            createArchiveForm.loader.loadObject.hide();            
            createArchiveForm.loader.state = false;
        },
        setMessage: function(text) {
            createArchiveForm.loader.messageObject.text(text);
        }
    }
};
(function($) {

    $.fn.createArchiveForm = function(params) {

        params = $.extend(createArchiveForm.params, params);

        if (createArchiveForm.obj == null) {
            createArchiveForm.obj = $(this[0]);
            createArchiveForm.start();
        }
    };

})(jQuery);
