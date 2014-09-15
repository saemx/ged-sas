<#include "include/documentlist.lib.ftl" />
<#include "../form/form.dependencies.inc">

<@markup id="css" >
   <#-- CSS Dependencies -->
   <@link rel="stylesheet" type="text/css" href="${url.context}/res/components/documentlibrary/documentlist.css" group="documentlibrary"/>
</@>

<@markup id="js">
   <#-- JavaScript Dependencies -->
   <@script type="text/javascript" src="${url.context}/res/components/documentlibrary/documentlist.js" group="documentlibrary"/>
   <@script type="text/javascript" src="${url.context}/res/components/documentlibrary/documentlist-view-detailed.js" group="documentlibrary"/>
   <@script type="text/javascript" src="${url.context}/res/components/documentlibrary/documentlist-view-simple.js" group="documentlibrary"/>
   <@script type="text/javascript" src="${url.context}/res/components/documentlibrary/documentlist-view-gallery.js" group="documentlibrary"/>
   <@script type="text/javascript" src="${url.context}/res/yui/slider/slider.js" group="documentlibrary"/>
   <@script type="text/javascript" src="${url.context}/res/saem/components/documentlibrary/documentlist-view-detailed.js"></@script>
   <@script type="text/javascript" src="${url.context}/res/saem/components/documentlibrary/documentlist-view-simple.js"></@script>
   <@script type="text/javascript" src="${url.context}/res/saem/components/documentlibrary/documentlist-view-gallery.js"></@script>
</@>

<@markup id="widgets">
   <@createWidgets group="documentlibrary"/>
</@>

<@uniqueIdDiv>
   <@markup id="html">
      <@documentlistTemplate/>
   </@>
</@>
