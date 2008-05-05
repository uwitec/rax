<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>宝贝列表</title>
</head>

<body>
<@s.url id="urlReturn" value="index.htm"/>
<@s.url id="urlAdd" action="ware" includeParams="none" />
<@s.url id="urlAdd2" action="ware">
	<@s.param name="id" value="0" />
</@s.url>
<a href="${urlReturn}">返回</a> <a href="${urlAdd}">添加新的宝贝</a><br /><br />
<div>总数:${count}</div><br />

<#assign pages=(count + pagePer - 1) / pagePer>
<#list 1..pages as i>
<#if i == page>
[${i}] 
<#else>
<a href="ware_list.action?page=${i}">[${i}]</a>  
</#if>
</#list><br /><br />

<#if wareList??>
<#list wareList as ware>
<div>
<a href="ware.action?id=${ware.id}">${ware.id}:${ware.name}</a> 
</div>
</#list>
</#if>

</body>
</html>
