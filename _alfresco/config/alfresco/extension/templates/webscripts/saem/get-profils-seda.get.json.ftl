<#escape x as jsonUtils.encodeJSONString(x)>
{
    "items": 
    [
    <#list data as item>
        {
            "nodeRef": "${item.nodeRef!''}",
            "name": "${item.name!''}"
        }<#if item_has_next>,</#if>
        </#list>
    ]
}
</#escape>