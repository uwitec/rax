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
    <@s.textfield label="名称" name="name"/>
    <@s.textfield label="条码" name="barcode"/>
    <@s.textfield label="成本" name="cost"/>
    <@s.textfield label="价格" name="price"/>
    <@s.textfield label="数量" name="number"/>
    <@s.hidden name="id"/>
    <@s.submit value=" 提 交 "/>
    <@s.reset value=" 重 置 "/>
</@s.form>
</div>

<a href="ware_delete.action?id=${id}" onclick="return confirm('确实要删除这个宝贝么？')">删除</a>

</body>
</html>
