<#include "include/toolbar.lib.ftl" />

<@markup id="css" >
   <#-- CSS Dependencies -->
   <@link rel="stylesheet" type="text/css" href="${url.context}/res/components/documentlibrary/toolbar.css" group="documentlibrary"/>
   <#-- SAEM-73 -->
   <@link rel="stylesheet" type="text/css" href="${url.context}/res/saem/components/documentlibrary/toolbar.css" />
   <@link rel="stylesheet" type="text/css" href="${url.context}/res/saem/css/create-archive-popup.css"/>
</@>

<@markup id="js">
   <#-- JavaScript Dependencies -->
   <@script type="text/javascript" src="${url.context}/res/components/documentlibrary/toolbar.js" group="documentlibrary"/>
   <#-- SAEM-73 -->
   <@script type="text/javascript" src="${url.context}/res/saem/components/documentlibrary/toolbar.js"></@script>
</@>

<@markup id="widgets">
   <@createWidgets group="documentlibrary"/>
</@>

<@uniqueIdDiv>
   <@markup id="html">
      <@toolbarTemplate/>
   </@>
</@>
