<#assign el=args.htmlid?html>
<div id="${el}-createArchive" class="create-archive">
   <div id="${el}-dialogTitle" class="hd">${msg("title")}</div>
   <div class="bd">
      <form id="${el}-form" action="" method="post">
       	 <div class="yui-g">
            <h2 id="${el}-dialogHeader">${msg("header")}</h2>
         </div>
      	 <div class="yui-gd">
            <div class="yui-u" style="margin-left:8em">
      	    	<div class="saem-select">
			        <#if items?has_content>
	        	    	<select id="${args.htmlid}-profils" name="profils">
	            	  		<#list items as item><option value="${item.nodeRef}">${item.name}</option></#list>
	               	  	</select>
    		    	<#else>
    		    		<div class="missing-profils">${msg("message.missing-profils")}</div>
					</#if>
            	</div>
            </div>
         </div>
         <div class="bdft">
	        <#if items?has_content>
    	        <input type="button" id="${el}-ok" value="${msg("button.ok")}" tabindex="0" />
        	</#if>
            <input type="button" id="${el}-cancel" value="${msg("button.cancel")}" tabindex="0" />
         </div>
    	 <input type="hidden" id="${el}-destination-hidden" name="destination" value=""/>
      </form>
   </div>
</div>