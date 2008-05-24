<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>被隐藏的宝贝列表</title>
</head>

<body>
<@s.url id="urlList" action="ware_list">
	<@s.param name="page" value="page" />
</@s.url>
<a href="index.htm">返回首页</a>
<a href="${urlList}">返回宝贝列表</a>
<br /><br />
<div>总数:${count}</div><br />

<#if wareList??>
<#list wareList as ware>
<div>
<@s.url id="url" action="ware">
	<@s.param name="id" value="#{ware.id}"/>
	<@s.param name="page" value="page"/>
</@s.url>
<a href="${url}">${ware.name}</a>
</div>
</#list>
</#if>
<br />

</body>
</html>
