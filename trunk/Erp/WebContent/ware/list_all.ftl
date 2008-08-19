<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>宝贝列表</title>
</head>

<body>
<a href="index.htm">返回首页</a><br /><br />

<@s.form action="ware_search" target="ware_search_result" theme="simple">
    搜索关键字:<@s.textfield label="关键字" name="keyword"/><br />
    搜索库存量:<@s.textfield label="库存" name="min" size="2"/> - <@s.textfield name="max" size="2"/>
	<@s.submit value=" 提 交 "/>
</@s.form>
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
