(function()
{
   /**
	 * YUI Library aliases
	 */
   var Dom = YAHOO.util.Dom,
      Event = YAHOO.util.Event,
      $siteURL = Alfresco.util.siteURL,
      Selector = YAHOO.util.Selector;

   /**
	 * Alfresco Slingshot aliases
	 */
   var $html = Alfresco.util.encodeHTML,
      $links = Alfresco.util.activateLinks,
      $userProfile = Alfresco.util.userProfileLink,
      $siteDashboard = Alfresco.util.siteDashboardLink,
      $relTime = Alfresco.util.relativeTime;

   /**
	 * Dashboard MyFavourites constructor.
	 * 
	 * @param {String}
	 *            htmlId The HTML id of the parent element
	 * @return {Alfresco.dashlet.MyFavourites} The new component instance
	 * @constructor
	 */
   Alfresco.dashlet.MyTreatments = function MyTreatments_constructor(htmlId)
   {
	  return Alfresco.dashlet.MyTreatments.superclass.constructor.call(this, "Alfresco.dashlet.MyTreatments", htmlId, ["datatable", "datasource", "paginator", "autocomplete"]);
   };
   
   Alfresco.dashlet.MyTreatmentsEvent = {

		   	  onDeleteArchive: function(nodeRef)
		      {
		    	  var scriptURL = "/share/proxy/alfresco/saem/launch-elimination-action?noderef="+nodeRef;

		   		  YAHOO.util.Connect.asyncRequest("GET", scriptURL,{
		   			  success: function(){
		   				  	location.reload();
		   		  		},
		   				failure: function(){
		   					alert("Erreur dans le lancement d'élimination");
		   				}
		   			},
		   			null);
		      },
		      
				onLancerVersement: function(nodeRef)
				{
					var url = document.location.origin + "/share/page/";
					
					var destination;
			         var postBody =
			         {
		        		selectedItems: nodeRef,
			            workflowName: "versement"
			         };
			         if (destination)
			         {
			            postBody.destination = nodeRef;
			         };
			        	 
			         Alfresco.util.navigateTo($siteURL("start-workflow"), "POST", postBody);
				},
				   
				onLancerRestitution: function(nodeRef)
				{
					var url = document.location.origin + "/share/page/";
					
					var destination;
			         var postBody =
			         {
			        	selectedItems: nodeRef,
			            workflowName: "restitution"
			         };
			         if (destination)
			         {
			            postBody.destination = nodeRef;
			         };
			        	 
			         Alfresco.util.navigateTo($siteURL("start-workflow"), "POST", postBody);
				},
   };

   YAHOO.extend(Alfresco.dashlet.MyTreatments, Alfresco.component.Base,
   {
	   
       options:
	   {
	   
	   },
   
      bodyContainer: null,
      
      dataTable: null,
      
      dataSource: null,
	   
      /**
		 * Fired by YUI when parent element is available for scripting
		 * 
		 * @method onReady
		 */
      onReady: function MyTreatments_onReady()
      {
          this.bodyContainer = Dom.get(this.id + "-my-treatments");
          
	       this.init();
      },

      init: function MyTreatments_init()
      {
          if (this.dataTable)
          {
             this.dataTable.destroy();
          }
             this.loadData();
      },
      
      loadData: function MyTreatments_loadData()
      {
    	  
    	  var putActionsLogos = function(elCell, oRecord, oColumn, sData) {
    		  
    		  var actionsTab = oRecord._oData.actions.split(";");
    		  
    		  for(var i = 0; i < actionsTab.length; i++){
    			  
    			  switch(actionsTab[i]){
    			  
    			  	case "restituer":
      			         var scriptURL = "/share/proxy/alfresco/saem/is-restitution-launchable?nodeRef="+oRecord._oData.nodeRef;
   			  		  YAHOO.util.Connect.asyncRequest("GET", scriptURL,{
   			   			  success: function(response){
   								var msg = JSON.parse(response.responseText);
   								if (msg.result){
   									elCell.innerHTML += '<a class="lienActions" onclick="Alfresco.dashlet.MyTreatmentsEvent.onLancerRestitution(\'' + oRecord._oData.nodeRef + '\')"><img title="restituer" src="' + Alfresco.constants.URL_RESCONTEXT + 'components/documentlibrary/images/myTreatments/restituer-16.png"/>' + "</a>";
   								}
   			   				  
   			 		  		},
   			   				failure: function(){
   			   				}
   			   			},
   			   			null);
    			  		break;
    			  	case "verser":
   			         var scriptURL = "/share/proxy/alfresco/saem/is-workflow-launchable?nodeRef="+oRecord._oData.nodeRef;
			  		  YAHOO.util.Connect.asyncRequest("GET", scriptURL,{
			   			  success: function(response){
								var msg = JSON.parse(response.responseText);
								if (msg.result){
									elCell.innerHTML += '<a class="lienActions" onclick="Alfresco.dashlet.MyTreatmentsEvent.onLancerVersement(\'' + oRecord._oData.nodeRef + '\')"><img title="verser" src="' + Alfresco.constants.URL_RESCONTEXT + 'components/documentlibrary/images/myTreatments/conserve-16.png"/>' + "</a>";
								}
			   				  
			 		  		},
			   				failure: function(){
			   				}
			   			},
			   			null);
    			  		break;
    			  	case "eliminer":
    			         var scriptURL = "/share/proxy/alfresco/saem/is-elimination-launchable?nodeRef="+oRecord._oData.nodeRef;
    			  		  YAHOO.util.Connect.asyncRequest("GET", scriptURL,{
    			   			  success: function(response){
    								var msg = JSON.parse(response.responseText);
    								if (msg.result){
    			    			  		elCell.innerHTML += '<a class="lienActions" onclick="Alfresco.dashlet.MyTreatmentsEvent.onDeleteArchive(\'' + oRecord._oData.nodeRef + '\')"><img title="eliminer" src="' + Alfresco.constants.URL_RESCONTEXT + 'components/documentlibrary/images/myTreatments/suppr-16.png"/>' + "</a>";
    								}
    			   				  
    			 		  		},
    			   				failure: function(){
    			   				}
    			   			},
    			   			null);
    			  		break;
    			  }
    		  }
    	  }; 
    	  
    	  var putLogoArchive = function(elCell, oRecord, oColumn, sData) {

    		  var statut = oRecord._oData.statut;
    		  var img = "";
    		  
			  switch(statut){
			  
			  	case "restituer":
			  		img = Alfresco.constants.URL_RESCONTEXT + "components/documentlibrary/images/folder-32-violet.png";
			  		break;
			  	case "verser":
			  		img = Alfresco.constants.URL_RESCONTEXT + "components/documentlibrary/images/folder-32-bleu.png";
			  		break;
			  	case "definitif":
			  		img = Alfresco.constants.URL_RESCONTEXT + "components/documentlibrary/images/folder-32-marron.png";
			  		break;
			  	case "intermediaire":
			  		img = Alfresco.constants.URL_RESCONTEXT + "components/documentlibrary/images/folder-32-vert.png";
			  		break;
			  	case "normal":
			  		img = Alfresco.constants.URL_RESCONTEXT + "components/documentlibrary/images/folder-32.png";
			  		break;
			  	case "eliminer":
			  		img = Alfresco.constants.URL_RESCONTEXT + "components/documentlibrary/images/folder-32-rouge.png";
			  		break;
			  }
			  
			  elCell.innerHTML = "<center><img class='linkArchive' src=" + img + "/></center>";
    	  }
    	  
    	  var putLinkArchive = function(elCell, oRecord, oColumn, sData) {
    		  
    		  var nodeRef = oRecord._oData.nodeRef;
    		  var name = oRecord._oData.name;
    		  
	          var scriptURL = "/share/proxy/alfresco/saem/search?folderId="+nodeRef;
	  		
	   		  YAHOO.util.Connect.asyncRequest("GET", scriptURL,{
	   			  success: function(response){
	   				   var msg = JSON.parse(response.responseText);
			         
			         	var documentPath = "/share/page/site/" + encodeURIComponent(msg.items[0].site.shortName).toLowerCase() + "/" + encodeURIComponent(msg.items[0].container).toLowerCase() + "#filter=path" + encodeURIComponent("|/" + msg.items[0].path + "/" + msg.items[0].name);
			            
			            elCell.innerHTML = "<a href='" + documentPath + "'>" + name + "</a>";
	   		  		},
	   				failure: function(){
	   					console.log("Erreur dans la recup du lien du noderef");
	   				}
	   			}, 
	   			null);
    	  }; 
    	  
         var myColumnDefs = [
         	  {key:"logo", label: this.msg("dashlet.myTreatments.logo"), sortable:true,resizeable:true, formatter:putLogoArchive},
              {key:"name", label: this.msg("dashlet.myTreatments.nom"), sortable:true,resizeable:true, formatter:putLinkArchive},
              {key:"flux", label: this.msg("dashlet.myTreatments.flux"), sortable:true,resizeable:true},
              {key:"author", label: this.msg("dashlet.myTreatments.auteur"), sortable:true,resizeable:true},
              {key:"statut", label: this.msg("dashlet.myTreatments.statut"), sortable:true,resizeable:true},
              {key:"date", label: this.msg("dashlet.myTreatments.date"), sortable:true,resizeable:true},
              {key:"actions", label: this.msg("dashlet.myTreatments.actions"), sortable:true,resizeable:true, formatter:putActionsLogos}
          ];
          var myDataSource = new YAHOO.util.DataSource(Alfresco.constants.PROXY_URI + "saem/myTreatments");
          myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
          myDataSource.responseSchema = {
              resultsList: "items",
              fields: [
				"name",
				"statut",
				"flux",
				"author",
				"date",
				"nodeRef",
				"actions"],
             metaFields: {
            	 totalRecords: "totalRecords"
             }
          };
          myDataSource.subscribe("responseEvent", function(oArgs) {
          });
          var rowFormatter = function(elTr, oRecord) {
             return true;
          };
          
          var dtOptions = {
                formatRow: rowFormatter,
                paginator: new YAHOO.widget.Paginator({ 
	                rowsPerPage: 10,
	                rowExpansion: true,
	                template :  
        		        '<center><p class="pg-nav">' + 
        		            '{FirstPageLink} {PreviousPageLink} {PageLinks}' + 
        		            '{NextPageLink} {LastPageLink}' + 
        		        '</p></center>',
        		    
	                // Options for FirstPageLink component 
	                firstPageLinkLabel : "<< premier", 
	                firstPageLinkClass : "yui-pg-first",  
	                
	                // Options for LastPageLink component 
	                lastPageLinkLabel : "dernier >>", 
	                lastPageLinkClass : "yui-pg-last",  
	                	 
	                // Options for PreviousPageLink component 
	                previousPageLinkLabel : "< précédent", 
	                previousPageLinkClass : "yui-pg-previous",  
	                	 
	                // Options for NextPageLink component 
	                nextPageLinkLabel : "suivant >", 
	                nextPageLinkClass : "yui-pg-next"
                }),
                
                MSG_EMPTY: this.msg("empty.title"),
                MSG_LOADING: this.msg("msg.loading"),
                
          };
          var myDataTable = new YAHOO.widget.DataTable(this.bodyContainer, myColumnDefs, myDataSource, dtOptions);

          var me = this;
          
          this.dataSource = myDataSource;
          this.dataTable = myDataTable;
      }
	 
   });
})();
