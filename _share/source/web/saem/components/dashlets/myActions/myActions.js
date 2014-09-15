(function()
{
   var Dom = YAHOO.util.Dom,
       Event = YAHOO.util.Event,
       $siteURL = Alfresco.util.siteURL,
       Selector = YAHOO.util.Selector;

   var $html = Alfresco.util.encodeHTML,
       $links = Alfresco.util.activateLinks;

   Alfresco.dashlet.MyActions = function MyActions_constructor(htmlId)
   {
      Alfresco.dashlet.MyActions.superclass.constructor.call(this, "Alfresco.dashlet.MyActions", htmlId, ["animation"]);
      return this;
   };
   
   
   Alfresco.dashlet.MyActionsEvent = {

		onPreparerVersement: function(event)
		{			
			
			// Intercept before dialog show
			var doBeforeDialogShow = function DLTB_onNewFolder_doBeforeDialogShow(p_form, p_dialog)
			{
				Dom.get(p_dialog.id + "-dialogTitle").innerHTML = this.msg("Préparer un versement");
				Dom.get(p_dialog.id + "-dialogHeader").innerHTML = this.msg("Choisir le profil du versement");
				Dom.get(p_dialog.id + "-destination-hidden").value = "findlater";
			};

			var createArchive = new Alfresco.module.SimpleDialog(this.id + "-createArchive");

			createArchive.setOptions(
					{
						width: "40em",
						templateUrl:  Alfresco.constants.URL_SERVICECONTEXT + "components/form/create-archive-popup",
						actionUrl: Alfresco.constants.PROXY_URI + "/saem/submit-create-archive-popup",
						destroyOnHide: true,
						doBeforeDialogShow:
						{
							fn: doBeforeDialogShow,
							scope: this
						},
						onSuccess:
						{
							fn: function DLTB_onNewArchive_success(response)
							{
									var currentNodeRef = response.config.dataObj.destination;
									var profileNodeRef = response.config.dataObj.profils;
									
							    	  var scriptURL = "/share/proxy/alfresco/saem/preVersementDestination?profil="+profileNodeRef;

							   		  YAHOO.util.Connect.asyncRequest("GET", scriptURL,{
							   			  success: function(response){
												var msg = JSON.parse(response.responseText);
												
												window.location.assign(Alfresco.constants.URL_CONTEXT + "page/archive-management?" + "destination=" + msg.destinationPreVersement + "&profil=" + profileNodeRef);
							   		  		},
							   				failure: function(){
							   					console.log("erreur dans la recup de la destination");
							   				}
							   			},
							   			null);
									
							},
							scope: this
						},
						onFailure:
						{
							fn: function DLTB_onNewArchive_failure(response)
							{
									if (response)
									{
										var archiveName = Dom.get(this.id + "-profils").value;
										Alfresco.util.PopupManager.displayMessage(
												{
													text: this.msg("message.new-archive.failure", archiveName)
												});
									}
									else
									{
										Alfresco.util.PopupManager.displayMessage(
												{
													text: this.msg("message.failure")
												});
									}
							},
							scope: this
						}
					}).show();


		},
		   
		onRechercheAvance: function(event)
		{
			document.location.href = document.location.origin + "/share/page/advsearch";
		},
			    
		onLancerVersement: function(event)
		{
			var url = document.location.origin + "/share/page/";
			
			var destination;
	         var postBody =
	         {
	            workflowName: "versement"
	         };
	         if (destination)
	         {
	            postBody.destination = "";
	         };
	        	 
	         Alfresco.util.navigateTo($siteURL("start-workflow"), "POST", postBody);
		},
		   
		onLancerRestitution: function(event)
		{
			var url = document.location.origin + "/share/page/";
			
			var destination;
	         var postBody =
	         {
	            workflowName: "restitution"
	         };
	         if (destination)
	         {
	            postBody.destination = "";
	         };
	        	 
	         Alfresco.util.navigateTo($siteURL("start-workflow"), "POST", postBody);
		},

   };

   YAHOO.extend(Alfresco.dashlet.MyActions, Alfresco.component.Base,
   {
      onReady: function MyActions_onReady()
      {         
         Event.addListener("preparerVersement-button", "click", Alfresco.dashlet.MyActionsEvent.onPreparerVersement, this, true);
		 Event.addListener("rechercheAvance-button", "click", Alfresco.dashlet.MyActionsEvent.onRechercheAvance, this, true);
		 Event.addListener("lancerVersement-button", "click", Alfresco.dashlet.MyActionsEvent.onLancerVersement, this, true);
		 Event.addListener("lancerRestitution-button", "click", Alfresco.dashlet.MyActionsEvent.onLancerRestitution, this, true);

      },
   });
})();