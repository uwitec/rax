<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>分组宝贝列表</title>
<style type="text/css">
.categoryList ul{ list-style:none; }
.categoryList li{ float:left; margin-right:20px; } 
</style>
</head>

<body>
<a href="index.htm">返回首页</a>
<a href="ware_list_hid.action">列出隐藏的宝贝</a>
<a href="ware_list_limited.action">列出需处理宝贝</a>
<a href="ware_list_all.action">列出所有宝贝</a>
<a href="ware.action">添加新的宝贝</a>
<br /><br />

<div>当前分类宝贝数:<#if wareList??>${wareList.size()}<#else>0</#if></div>
<br />

<@s.form action="ware_search" target="_blank" theme="simple">
    <@s.textfield label="关键字" name="keyword"/>
    <@s.submit value=" 提 交 "/>
</@s.form>
<br />

<div class="categoryList">
<#if categoryList??>
<#list categoryList as category>
<a href="?categoryId=${category.id}" title="${category.num}">${category.name}</a> 
</#list>
</#if>
</div>
<br />

<#if wareList??>
<#list wareList as ware>
<div>
<@s.url id="url" action="ware">
	<@s.param name="id" value="#{ware.id}"/>
	<@s.param name="page" value="page"/>
</@s.url>
<a href="${url}">${ware.name} - (${ware.number})</a>
</div>
</#list>
</#if>
<br />

</body>
</html>
