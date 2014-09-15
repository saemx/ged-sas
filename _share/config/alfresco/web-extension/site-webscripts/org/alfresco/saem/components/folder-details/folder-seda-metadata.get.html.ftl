<#assign el=args.htmlid?js_string>
<div id="${el}-body" class="folder-metadata-header folder-details-panel">
      <h2 id="${el}-heading" class="thin dark">
         ${msg("seda.properties.title")}
      </h2>
	  <div id="${el}-folder-seda-formContainer" class="form-container">
	       <div class="form-fields">
	           <div class="set">
	               <#list sedaproperties as item>
	                   <div class="form-field">
	                       <div class="viewmode-field">
	                       		<span class="viewmode-label">${msg(item.title)}: </span>
	                       		<span class="viewmode-value">${item.value}</span>
	                       </div>
	                   </div>
	               </#list>
	           </div>
	       </div>
	  </div>
	     <script type="text/javascript">//<![CDATA[
         Alfresco.util.createTwister("${el}-heading", "FolderSedaMetadata");
      //]]></script>
</div>