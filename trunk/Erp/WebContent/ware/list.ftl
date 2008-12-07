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
<a href="index.action">返回首页</a>
<a href="ware_list_hid.action">列出隐藏的宝贝</a>
<a href="ware_list_all.action">列出所有宝贝</a>
<a href="ware.action">添加新的宝贝</a>
<br /><br />

<@s.form action="ware_search" target="ware_search_result" theme="simple">
    搜索关键字:<@s.textfield label="关键字" name="keyword"/><@s.submit value=" 提 交 "/>
</@s.form>

<div>当前分类宝贝数:<#if wareList??>${wareList.size()}<#else>0</#if></div>
<div class="categoryList">
<#if categoryList??>
<#assign w = 0>
<#list categoryList as category>
<#if category.id = categoryId>
[ ${category.name} ]
<#else>
<a href="?categoryId=${category.id}" title="${category.num}">${category.name}</a>
</#if>
<#assign w = w + category.name?length/>
<#if 70 < w>
  <br />
  <#assign w = 0>
</#if>
</#list>
</#if>
</div>
<br />

<table>
<tr>
<td>数量</td>
<td>成本</td>
<td>宝贝名称</td>
</tr>
<#if wareList??>
<#list wareList as ware>
<tr>
<@s.url id="url" action="ware">
	<@s.param name="id" value="#{ware.id}"/>
	<@s.param name="page" value="page"/>
</@s.url>
<td>${ware.number}</td>
<td>#{ware.cost;m2M2}</td>
<td><a href="${url}">${ware.name}</a></td>
</tr>
</#list>
</#if>
</table>
<br />

</body>
</html>
