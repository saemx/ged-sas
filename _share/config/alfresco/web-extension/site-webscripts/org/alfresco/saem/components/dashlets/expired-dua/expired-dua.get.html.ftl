<@markup id="css" >
   <#-- CSS Dependencies -->
   <@link rel="stylesheet" type="text/css" href="/share/saem/components/dashlets/expired-dua/expired-dua.css" />
</@>

<@markup id="js">
   <#-- JavaScript Dependencies -->
	<@script type="text/javascript" src="/share/saem/components/dashlets/expired-dua/expired-dua.js"></@script>
</@>


<#assign id = args.htmlid>
<#assign jsid = args.htmlid?js_string>
<#assign prefSimpleView = preferences.simpleView!true>


<script type="text/javascript">//<![CDATA[
(function()
{

   new Alfresco.dashlet.ExpiredDua("${jsid}").setOptions(
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
		<div class="dashlet expired-dua">
		    <div class="title">${msg("header")}</div>
		    <div class="titleBarActions" style="opacity: 0;">
		        <div class="titleBarActionIcon help" title="Afficher l'aide pour ce dashlet"></div>
		    </div>
		    <div class="toolbar">
               <span class="expired-dua-sort-button">
                 <a href="#" id="sortbyname-button" class="theme-color-1">${msg("link.sortByName")}</a>
                 <a href="#" id="sortbydate-button" class="theme-color-1">${msg("link.sortByDate")}</a>
		     </div>
		    <div class="body scrollableList yui-dt">
		    	<div id="${id}-expired-dua"></div>
		    </div>
		</div>
   </@>
</@>