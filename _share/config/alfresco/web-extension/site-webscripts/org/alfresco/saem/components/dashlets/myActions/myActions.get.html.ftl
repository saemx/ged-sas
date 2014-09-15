<@markup id="css" >
   <#-- CSS Dependencies -->
   <@link rel="stylesheet" type="text/css" href="/share/saem/components/dashlets/myActions/myActions.css" />
</@>

<@markup id="js">
   <#-- JavaScript Dependencies -->
	<@script type="text/javascript" src="/share/saem/components/dashlets/myActions/myActions.js"></@script>
	<@script type="text/javascript" src="${url.context}/res/modules/simple-dialog.js" />
</@>


<#assign id = args.htmlid>
<#assign jsid = args.htmlid?js_string>
<#assign prefSimpleView = preferences.simpleView!true>


<script type="text/javascript">//<![CDATA[
(function()
{

   new Alfresco.dashlet.MyActions("${jsid}").setOptions(
   {
      simpleView: ${prefSimpleView?string?js_string},
   }).setMessages(${messages});
})();
//]]>
</script>

<@markup id="widgets">
   <@createWidgets group="dashlets"/>
</@>

<@markup id="html">
   <@uniqueIdDiv>
		<div class="dashlet my-actions" style="width: 100%;">
		    <div class="title">${msg("header")}</div>
		    <div class="titleBarActions" style="opacity: 0;">
		        <div class="titleBarActionIcon help" title="Afficher l'aide pour ce dashlet"></div>
		    </div>
		    <div class="body" style="overflow: hidden;">	
		    
			    <div id="globalContainer">
		      		<td>
				      	<a class="aMyActions gauche" href="#" id="preparerVersement-button">
							<div class="logoAction"><img class="dashletimg" src="/share/res/components/documentlibrary/images/myActions/archive.png"/></div>
							<div class="nomDesc">	
								<p><b>Préparer un versement</b></p>
								<p>Lancer la préparation d'un nouveau versement</p>
							</div>
						</a>
			      	</td>
					
					  <td>
				     	<a class="aMyActions droite" href="#" id="rechercheAvance-button">
						    <div class="logoAction"><img class="dashletimg" src="/share/res/components/documentlibrary/images/myActions/recherche.png"/></div>
							<div class="nomDesc">	
					    		<p><b>Lancer une recherche</b></p>
					    		<p>Accéder à la recherche avancée</p>
				    		</div>
						</a>
				      </td>
					  
					  <td>
						<a class="aMyActions gauche bas" href="#" id="lancerVersement-button">
						    <div class="logoAction"><img class="dashletimg" src="/share/res/components/documentlibrary/images/myActions/versement.png"/></div>
							<div class="nomDesc">
					    		<p><b>Soumettre  un versement</b></p>
					    		<p>Démarrer un workflow de versement afin de soumettre un pré-versement</p>
				    		</div>
						</a>
				      </td>
					  
					  <td>
				      	<a class="aMyActions droite bas" href="#" id="lancerRestitution-button">
						    <div class="logoAction"><img class="dashletimg" src="/share/res/components/documentlibrary/images/myActions/restitution.png"/></div>
							<div class="nomDesc">	
					    		<p><b>Demander une restitution</b></p>
					    		<p>Démarrer un workflow de restitution afin de récupérer une archive</p>
				    		</div>
						</a>
				      </td>
			    </div>
		    </div>
		</div>
   </@>
</@>