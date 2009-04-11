<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>所有宝贝列表</title>
</head>

<body>
<a href="index.action">返回首页</a>
<a href="ware_list.action">返回宝贝列表</a>
<br /><br />

<@s.form action="ware_search" target="ware_search_result" theme="simple">
    搜索关键字:<@s.textfield label="关键字" name="keyword"/><@s.submit value=" 提 交 "/>
</@s.form>
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

<div>
<span>页数:${pager.currentPage}/${pager.totalPage}</span>&nbsp;&nbsp;
<span>${pager.getPreviousSlide()}</span>
<span>${pager.getBackLink()}</span>
<span>${pager.getPageLinks()}</span>
<span>${pager.getNextLink()}</span>
<span>${pager.getNextSlide()}</span>&nbsp;&nbsp;
<span>总数:${pager.totalItems}</span>
<div>

</body>
</html>
