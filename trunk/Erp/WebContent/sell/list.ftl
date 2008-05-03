<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>出库单列表</title>
</head>

<body>
<@s.url id="urlReturn" value="index.htm"/>
<@s.url id="urlAdd" action="sell" includeParams="none" />
<a href="${urlReturn}">返回</a> <a href="${urlAdd}">添加新出库单</a>
<a href="sell_import.action">从淘宝地址导入出库单</a><br /><br />
<div>总数:${count}</div><br />

<#assign pages=(count + pagePer - 1) / pagePer>
<#list 1..pages as i>
<#if i == page>
[${i}] 
<#else>
<a href="sell_list.action?page=${i}">[${i}]</a>  
</#if>
</#list><br /><br />

<#if sellList??>
<#list sellList as sell>
<div>
旺旺:${sell.customerWangwang}<br />
姓名:${sell.customerName}<br />
地址:${sell.customerAddress}<br />
<!-- 日期:${sell.createDate?string("yyyy-MM-dd")}<br /> //-->
<a href="sell.action?id=${sell.id}">查看/编辑</a>
<a href="invoice.action?sellId=${sell.id}">打印</a>
</div>
<br />
</#list>
</#if>

</body>
</html>
