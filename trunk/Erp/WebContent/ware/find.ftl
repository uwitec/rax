<?xml version="1.0" encoding="UTF-8" ?>
<#if wareList??>
<warelist>
<#list wareList as ware>
<ware>
<name>${ware.name}</name>
<barcode>${ware.barcode}</barcode>
<cost>#{(ware.cost);m2M2}</cost>
<price>#{(ware.price);m2M2}</price>
<number>#{ware.number}</number>
</ware>
</#list>
</warelist>
</#if>