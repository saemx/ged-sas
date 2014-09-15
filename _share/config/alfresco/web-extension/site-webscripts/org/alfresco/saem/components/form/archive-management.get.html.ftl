        <script type="text/javascript">

            function saemparam(key) {
                var result = new RegExp(key + "=([^&]*)", "i").exec(window.location.search);
                return result && result[1] || "";
            }


            $(document).ready(function() {

                var dataurl = null;
                var profil = null;
                var destination = null;
                var actionParams = {};
                
                if(saemparam("archive") != 0){
                	dataurl = "/share/proxy/alfresco/saem/archivemanagement?archive=" + saemparam('archive');
                    actionParams.archive = saemparam('archive');
                }
                
                if(saemparam("archiveRead") != 0){
                	dataurl = "/share/proxy/alfresco/saem/archivemanagement?archiveRead=" + saemparam('archiveRead');
                    actionParams.archive = saemparam('archiveRead');
                }
                
                
                if (saemparam("profil") != 0) {
                    dataurl = "/share/proxy/alfresco/saem/archivemanagement?profil=" + saemparam('profil');
                    profil = saemparam("profil");
                    actionParams.profil = profil;
                }
                
                if (saemparam("destination") != 0) {
                    destination = saemparam("destination");
                    actionParams.destination = destination;
                }
                
                if(saemparam("nodeRef") != 0){
                	dataurl = "/share/proxy/alfresco/saem/archivemanagement?nodeRef=" + saemparam('nodeRef');
                    //actionParams.nodeRef = saemparam("nodeRef");
                }
                
                mode = "new";
                if(saemparam("mode") != 0){
                	mode = saemparam("mode");
                }
                
                /** Affichage des bouttons suivant le mode "lecture" ou "modification" **/
                if(saemparam("mode")=="read"){
			  		$("div.saem-form-content-mode-buttons").css("display", "none");
			  	}
			  	/*****/
                
                dataurl += "&mode="+mode;  
                
                $(".saem-form-main").createArchiveForm({
					loadDataURL: dataurl,
					editDataURL: "/share/proxy/alfresco/saem/archivemanagement",
					addItemURL: "/share/proxy/alfresco/saem/archivemanagement",
					removeDataURL: "/share/proxy/alfresco/saem/archivemanagement",
					actionURL: "/share/proxy/alfresco/saem/create-archive",            
					editURL: "/share/proxy/alfresco/saem/create-archive",                    
					actionParams: actionParams,
					mode:mode
                });              
                
            });

        </script>
        
        <div class="saem-form-main">
            <div class="saem-form-main-top-border"></div>
            
            <div class="saem-form-menu">

                <div class="saem-root-cat" id="saem-entete">
                    <div class="saem-root-cat-title">
                                <table cellspacing="0" cellpadding="0" border="0" class="saem-root-cat-inf">
                                    <tbody>
                                        <tr>
                                            <td class="saem-root-cat-title-nberreur">
                                               
                                            </td>
                                            <td class="saem-root-cat-inf-td-folder">
                                                <a class="saem-root-cat-inf-td-folder-link" href="#"> </a>
                                            </td>
                                            <td class="saem-root-cat-inf-td-name">
                                                <span class="saem-root-cat-inf-td-name-link">En-tête du bordereau</span>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                    </div>

                </div>




                
                    <div class="saem-root-cat" id="saem-description">
                        <div class="saem-root-cat-title">
                                <table cellspacing="0" cellpadding="0" border="0" class="saem-root-cat-inf">
                                    <tbody>
                                        <tr>
                                            <td class="saem-root-cat-title-nberreur">
                                               
                                            </td>
                                            <td class="saem-root-cat-inf-td-folder">
                                                <a class="saem-root-cat-inf-td-folder-link" href="#"> </a>
                                            </td>
                                            <td class="saem-root-cat-inf-td-name">
                                                <span class="saem-root-cat-inf-td-name-link">Description du versement</span>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                    </div>
                    </div>

                <div class="saem-form-move-div">
                    <div class="saem-form-move-div-normal"></div>
                </div>
            </div>
            
            
            <div class="saem-form-content">
                <div class="saem-form-content-margin">
                <div class="saem-form-content-general-buttons">


                    <div class="saem-form-button"  style="display:inline-block;">
                        <div class="saem-form-create-content">
                            <span class="saem-form-first-child saem-button-edit" style="display:none">
                                <button type="button" tabindex="0">Modifier le bordereau de versement</button>
                            </span>
                        </div>
                    </div>

                    <div class="saem-form-button"  style="display:inline-block;">
                        <div class="saem-form-create-content">
                            <span class="saem-form-first-child saem-button-soumettre"  style="display:none">
                                <button type="button" tabindex="0">Soumettre le bordereau de versement</button>
                            </span>
                        </div>
                    </div>
                </div>

                <div class="saem-form-content-mode-buttons">

                    <div class="saem-form-content-mode-buttons-content">
                        <span class="saem-form-content-mode-buttons-first-child saem-form-content-mode-buttons-first-child-expert">
                            <button type="button" tabindex="0" title="Vue simplifiée"></button>
                        </span>
                    </div>

                    <div class="saem-form-content-mode-buttons-content">
                        <span class="saem-form-content-mode-buttons-first-child saem-form-content-mode-buttons-first-child-simple-hide">
                            <button type="button" tabindex="0" title="Vue détaillée"></button>
                        </span>
                    </div>

                </div>
                

                <div class="saem-form-content-ariane">

                    <div class="saem-form-content-ariane-element">
                        <span class="saem-form-content-ariane-element-first-child saem-root">
                            <button type="button" tabindex="0" title="Plus haut"></button>
                        </span>
                    </div>

                    <div class="saem-form-content-ariane-separator">&nbsp;</div>
                </div>

                <div id="saem-form-content-insertion">

                </div>
            </div>
            </div>
            

            <div id="saem-loader" class="saem-loader">
                <div class="saem-loader-zone">
                    <div align="center">
                        <img src="/share/res/saem/css/ajax-loader.gif" />
                    </div>
                    <div  align="center" class="saem-loader-text">
                        Chargement .....
                    </div>
                </div>
            </div>
        </div>
      
        