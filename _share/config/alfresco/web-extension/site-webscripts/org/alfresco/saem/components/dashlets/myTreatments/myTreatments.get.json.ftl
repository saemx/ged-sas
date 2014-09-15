{
    "items": 
    [
    <#list data as item>
        {
            "name": "${item.name!''}"
            "statut": "${item.statut!''}"
            "flux": "${item.flux!''}"
            "author": "${item.author!''}"
            "date": "${item.date!''}"
            "nodeRef": "${item.nodeRef!''}"
            "actions": "${item.actions!''}"
        }<#if item_has_next>,</#if>
        </#list>
    ]
}
