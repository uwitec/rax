<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>AddFaq</title>
</head>

<body>
<@s.url id="url" action="ware_list" includeParams="none"/>
<a href="${url}">返回</a><br /><br />

<div>
<@s.form action="ware_save">
    <@s.textfield label="名称" name="name" value="${(ware.name)}" />
    <@s.textfield label="条码" name="barcode" value="${(ware.barcode)}" />
    <@s.textfield label="成本" name="cost" value="#{(ware.cost);m2M2}" />
    <@s.textfield label="价格" name="price" value="#{(ware.price);m2M2}" />
    <@s.textfield label="数量" name="number" value="#{(ware.number)}" />
    <#if ware??>
    <@s.hidden name="id" value="#{ware.id}" />
    </#if>
    <@s.submit value="Submit"/>
</@s.form>
</div>

</body>
</html>
