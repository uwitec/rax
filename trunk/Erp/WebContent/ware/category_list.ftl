<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>宝贝分类列表</title>
</head>

<body>
<a href="index.htm">返回首页</a>
<a href="ware_category.action">添加新的分类</a>
<br /><br />
<div>总数:<#if categoryList??>${categoryList.size()}</#if></div><br />

<#if categoryList??>
<#list categoryList as category>
<div>
<@s.url id="url_detail" action="ware_category">
	<@s.param name="id" value="#{category.id}"/>
</@s.url>
<a href="${url_detail}">${category.name}</a>
</div>
</#list>
</#if>
<br />

</body>
</html>
