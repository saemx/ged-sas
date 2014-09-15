/**
 * Copyright (C) 2005-2010 Alfresco Software Limited.
 *
 * This file is part of Alfresco
 *
 * Alfresco is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Alfresco is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Alfresco. If not, see <http://www.gnu.org/licenses/>.
 */

/**
 * Dashboard ExpiredDua component.
 *
 * @namespace Alfresco.dashlet
 * @class Alfresco.dashlet.ExpiredDua
 */
(function()
{
   /**
    * YUI Library aliases
    */
   var Dom = YAHOO.util.Dom,
       Event = YAHOO.util.Event,
       Selector = YAHOO.util.Selector;

   /**
    * Alfresco Slingshot aliases
    */
   var $html = Alfresco.util.encodeHTML,
       $links = Alfresco.util.activateLinks;

   /**
    * Use the getDomId function to get some unique names for global event handling
    */
   var DELETE_EVENTCLASS = Alfresco.util.generateDomId(null, "delete-archive");
   
   if (typeof jQuery === "undefined") {
	    var script = document.createElement('script');
	    script.src = 'http://code.jquery.com/jquery-latest.min.js';
	    script.type = 'text/javascript';
	    document.getElementsByTagName('head')[0].appendChild(script);
	}
   		
   Alfresco.dashlet.ExpiredDua = function ExpiredDua_constructor(htmlId)
   {
      Alfresco.dashlet.ExpiredDua.superclass.constructor.call(this, "Alfresco.dashlet.ExpiredDua", htmlId, ["datasource", "datatable", "animation"]);
      return this;
   };
   
   
   Alfresco.dashlet.ExpiredDuaEvent = {

   	  onDeleteArchive: function(nodeRef, deleteId)
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
      
      onSortNameArchive: function(event)
      {
          var dataTable = this.dataTable;
          if (dataTable)
          {
              var dataSource = dataTable.getDataSource();
              var url = dataSource.liveData;
              var urlSplit = url.split("?");
              if (urlSplit.length > 1){
            	  var params = urlSplit[1].split("&");
              
		           for (var i = 0; i < params.length; i++)
		           {
		              if (params[i].split("=")[0] == "sort")
		              {
		            		 switch(params[i].split("=")[1]){
		            		 
			            		 case "nameasc":
			            			 url = urlSplit[0] + "?sort=namedesc";
			            			 break;
			            		 case "namedesc":
			            			 url = urlSplit[0] + "?sort=nameasc";
			            			 break;
		            			 default:
			            			 url = urlSplit[0] + "?sort=nameasc";
		            		 }
		                 break;
		              }
		           }
              }
              else{
            	  url = urlSplit[0] + "?sort=nameasc";
              }
        	  
        	  dataSource.liveData = url;
        	  dataTable.load();
          }
      },
      
      onSortDateArchive: function(event)
      {
          var dataTable = this.dataTable;
          if (dataTable)
          {
              var dataSource = dataTable.getDataSource();
              var url = dataSource.liveData;
              var urlSplit = url.split("?");
              if (urlSplit.length > 1){
            	  var params = urlSplit[1].split("&");
              
		           for (var i = 0; i < params.length; i++)
		           {
		              if (params[i].split("=")[0] == "sort")
		              {
		            		 switch(params[i].split("=")[1]){
		            		 
			            		 case "dateasc":
			            			 url = urlSplit[0] + "?sort=datedesc";
			            			 break;
			            		 case "datedesc":
			            			 url = urlSplit[0] + "?sort=dateasc";
			            			 break;
		            			 default:
			            			 url = urlSplit[0] + "?sort=dateasc";
		            		 }
		                 break;
		              }
		           }
              }
              else{
            	  url = urlSplit[0] + "?sort=dateasc";
              }
        	  
        	  dataSource.liveData = url;
        	  dataTable.load();
          }
      },      
   };

   YAHOO.extend(Alfresco.dashlet.ExpiredDua, Alfresco.component.Base,
   {
      onReady: function ExpiredDua_onReady()
      {
         var me = this;
         
         Event.addListener("sortbyname-button", "click", Alfresco.dashlet.ExpiredDuaEvent.onSortNameArchive, this, true);
         Event.addListener("sortbydate-button", "click", Alfresco.dashlet.ExpiredDuaEvent.onSortDateArchive, this, true);
         
        
         var myColumnDefs = [
            { key: "icon", label: "Icon", sortable: false, formatter: this.bind(this.renderCellIcon), width: 64 },
            { key: "detail", label: "Description", sortable: false, formatter: this.bind(this.renderCellDetail) },
            { key: "actions", label: "Actions", sortable: false, formatter: this.bind(this.renderCellActions), width: 24 }
        ];
        
        var myDataSource = new YAHOO.util.DataSource(Alfresco.constants.PROXY_URI + "/saem/expired-dua");
        myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
        myDataSource.responseSchema = {
            resultsList: "items",
            fields: [
            "name",
		    "end_date",
		    "name_archive",
		    "type_archive",
		    "creator",
		    "created",
		    "description",
		    "nodeRef"],
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
              MSG_EMPTY: this.msg("expired-dua.empty.title"),
              MSG_LOADING: this.msg("message.datatable.loading")
        };
        
        var myDataTable = new YAHOO.widget.DataTable(this.id + "-expired-dua", myColumnDefs, myDataSource, dtOptions);

        var me = this;
        
        this.dataSource = myDataSource;
        this.dataTable = myDataTable;
        
         
         var registerEventHandler = function ExpiredDua_onReady_registerEventHandler(cssClass, fnHandler)
         {
            var fnEventHandler = function ExpiredDua_onReady_fnEventHandler(layer, args)
            {
               var owner = YAHOO.Bubbling.getOwnerByTagName(args[1].anchor, "div");
               if (owner !== null)
               {
                  fnHandler.call(me, args[1].target.offsetParent, owner);
               }

               return true;
            };
            YAHOO.Bubbling.addDefaultAction(cssClass, fnEventHandler);
         };

      },


      /**
       * Icon custom datacell formatter
       *
       * @method renderCellIcon
       * @param elCell {object}
       * @param oRecord {object}
       * @param oColumn {object}
       * @param oData {object|string}
       */
      renderCellIcon: function ExpiredDua_renderCellIcon(elCell, oRecord, oColumn, oData)
      {
         Dom.setStyle(elCell, "width", oColumn.width + "px");
         Dom.setStyle(elCell.parentNode, "width", oColumn.width + "px");
         
         var archive = oRecord.getData();
         
         if (!archive.isInfo)
    	 {
	          var scriptURL = "/share/proxy/alfresco/saem/search?folderId="+archive.nodeRef;
	  		
	   		  YAHOO.util.Connect.asyncRequest("GET", scriptURL,{
				  success: function(response){
						var msg = JSON.parse(response.responseText);
						var documentPath = "/share/page/site/" + encodeURIComponent(msg.items[0].site.shortName).toLowerCase() + "/" + encodeURIComponent(msg.items[0].container).toLowerCase() + "#filter=path" + encodeURIComponent("|/" + msg.items[0].path + "/" + msg.items[0].name);
						var img = "folder-64.png";
						elCell.innerHTML = '<a href="' + documentPath + '"><img src="' + Alfresco.constants.URL_RESCONTEXT + 'components/documentlibrary/images/folder-64.png"/></a>';
					},
				  failure: function(){
	   				}
   			},
	   			null);
    	 }
      },

      /**
       * Name & description custom datacell formatter
       *
       * @method renderCellDetail
       * @param elCell {object}
       * @param oRecord {object}
       * @param oColumn {object}
       * @param oData {object|string}
       */
      renderCellDetail: function ExpiredDua_renderCellDetail(elCell, oRecord, oColumn, oData)
      {
    	  var archive = oRecord.getData();
    	  
    	  if (!archive.isInfo)
		  {
		          var scriptURL = "/share/proxy/alfresco/saem/search?folderId="+archive.nodeRef;
		
		   		  YAHOO.util.Connect.asyncRequest("GET", scriptURL,{
		   			  success: function(response){
		   				   var msg = JSON.parse(response.responseText);
		   				   
				            desc = "";
				         
				         	var documentPath = "/share/page/site/" + encodeURIComponent(msg.items[0].site.shortName).toLowerCase() + "/" + encodeURIComponent(msg.items[0].container).toLowerCase() + "#filter=path" + encodeURIComponent("|/" + msg.items[0].path + "/" + msg.items[0].name);
				         	
				            // Description non-blank?
				            desc += '<a class="archive-name" href="' + documentPath  + '"><h2>' + archive.name_archive + '</h2></a>';
				            desc += '<div class="archive-creator"><span>Créée par : ' + archive.creator + '</span></div>';
				            desc += '<div class="archive-created"><span>le : ' + archive.created + '</span></div>';
				            desc += '<div class="archive-fin-dua"><span>Fin de DUA : ' + archive.end_date + '</span></div>';
				            desc += '<div class="archive-name"><span>Type : ' + archive.type_archive + '</span></div>';
				            
				            elCell.innerHTML = desc;
		   		  		},
		   				failure: function(){
		   				}
		   			}, 
		   			null);
		  }
      },
      
      /**
       * Actions custom datacell formatter
       *
       * @method renderCellActions
       * @param elCell {object}
       * @param oRecord {object}
       * @param oColumn {object}
       * @param oData {object|string}
       */
      renderCellActions: function ExpiredDua_renderCellActions(elCell, oRecord, oColumn, oData)
      {
    	 var archive = oRecord.getData();
    	 
    	 if(!archive.isInfo)
    	 {
	         Dom.setStyle(elCell, "width", oColumn.width + "px");
	         Dom.setStyle(elCell.parentNode, "width", oColumn.width + "px");
	         
	         var scriptURL = "/share/proxy/alfresco/saem/is-elimination-launchable?nodeRef="+archive.nodeRef;
	         
	  		  YAHOO.util.Connect.asyncRequest("GET", scriptURL,{
	   			  success: function(response){
	   				  
						var msg = JSON.parse(response.responseText);
						var desc = "";
						
						if (msg.result){
							desc += '<a id="' + DELETE_EVENTCLASS + '" onclick="Alfresco.dashlet.ExpiredDuaEvent.onDeleteArchive(\''+archive.nodeRef+'\',\''+DELETE_EVENTCLASS+'\')" title="Lancer le workflow d\'élimination"><img title="eliminer" src="' + Alfresco.constants.URL_RESCONTEXT + 'components/documentlibrary/images/myTreatments/suppr-16.png"/></a>';
						}
						
						elCell.innerHTML = desc;
	   				  
	 		  		},
	   				failure: function(){
						var desc = "";
						elCell.innerHTML = desc;
	   				}
	   			},
	   			null);
    	 }
      }
   });
})();