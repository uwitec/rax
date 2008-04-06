<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>ListLinks</title>
</head>

<body>
<@s.url id="url" value="index.htm"/>
<a href="${url}">返回</a><br /><br />

<span>分类:${categoryId} 总数:${linkNum}</span><br />

${(category.title)?default('N/A')}
${(category.summary)?if_exists}
<br />

<@s.url id="url" action="listLink" includeParams="none"/>
<@s.select 
	label="Categorys" 
	list="categorys" 
	listValue="title" 
	listKey="id"
	name="categoryId"
	onchange="javascript:location.href='${url}?categoryId=' + this.value"
	/>
<br />
<br />

<#if links??>
<#list links as link>
<div>
id:${link.id}
<a href="${link.address}" title="${link.summary}" target="_blank">${link.title}</a>
</div>
</#list>
</#if>

</body>
</html>
