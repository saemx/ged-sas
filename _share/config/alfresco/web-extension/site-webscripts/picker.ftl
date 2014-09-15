<#if field.endpointType?exists>

<#include "org/alfresco/components/form/controls/common/picker.inc.ftl" />


<#assign controlId = fieldHtmlId + "-cntrl">

<script type="text/javascript">//<![CDATA[
(function()
{
   <@renderPickerJS field "picker" />
   
   picker.setOptions(
   {
      itemType: "${field.control.params.itemType}",
      parentNodeRef: "${field.control.params.parentNodeRef!"alfresco://company/home"}",
      rootNode: "${field.control.params.rootNode!"alfresco://company/home"}",
      multipleSelectMode: ${(field.control.params.multipleSelectMode!true)?string},
      itemFamily: "${field.control.params.itemFamily}",
      params: "${field.control.params.params!""}",
      compactMode: false
   });
   
})();
//]]></script>

<div class="form-field">
   <#if form.mode == "view">
      <div id="${controlId}" class="viewmode-field">
         <#if (field.endpointMandatory!false || field.mandatory!false) && field.value == "">
            <span class="incomplete-warning"><img src="${url.context}/components/form/images/warning-16.png" title="${msg("form.field.incomplete")}" /><span>
         </#if>
         <span class="viewmode-label">${field.label?html}:</span>
         <span id="${controlId}-currentValueDisplay" class="viewmode-value current-values"></span>
      </div>
   <#else>
      <label for="${controlId}">${field.label?html}:<#if field.mandatory!false><span class="mandatory-indicator">${msg("form.required.fields.marker")}</span></#if></label>
      
      <div id="${controlId}" class="object-finder">
         
         <div id="${controlId}-currentValueDisplay" class="current-values"></div>
         
        <#if field.disabled == false>
            <input type="hidden" id="${fieldHtmlId}" name="${field.name}" value="${field.value?html}" />
            <input type="hidden" id="${controlId}-added" name="${field.name}_added" />
            <input type="hidden" id="${controlId}-removed" name="${field.name}_removed" />
            <div id="${controlId}-itemGroupActions" class="show-picker inlineable"></div>
            
            <@renderPickerHTML controlId />
         </#if>
      </div>
   </#if>
</div>

</#if>