{
    "items": 
    [
    <#list data as item>
        {
            "name": "${item.name!''}"
            "end_date": "${item.end_date!''}"
            "name_archive": "${item.name_archive!''}"
            "type_archive": "${item.type_archive!''}"
            "creator": "${item.creator!''}"
            "created": "${item.created!''}"
            "description": "${item.description!''}"
            "nodeRef": "${item.nodeRef!''}"
        }<#if item_has_next>,</#if>
        </#list>
    ]
}
