<#include "include/alfresco-template.ftl" />

<@templateHeader />

<@templateBody>
   <div id="alf-hd">
      <@region id="header" scope="global" />
      <div id="template_x002e_title_x002e_repository_x0023_default">
	      <div class="page-title theme-bg-color-1 theme-border-1">
	         <h1 class="theme-color-3">Préparer un versement</h1>
	      </div>
    </div>
   </div>
   <div id="bd">
      <div class="share-form">
        <@region id="form" scope="template" />
      </div>
   </div>
   
</@>

<@templateFooter>
   <div id="alf-ft">
      <@region id="footer" scope="global" />
   </div>
</@>
