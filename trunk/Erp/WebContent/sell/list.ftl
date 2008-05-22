<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>出库单列表</title>
</head>

<body>
<@s.url id="urlAdd" action="sell">
	<@s.param name="page" value="page"/>
</@s.url>
<a href="index.htm">返回首页</a>
<a href="sell_list.action?page=${page}">待处理发货单</a>
<a href="sell_listAll.action?page=${page}">所有发货单</a>
<br /><br />
<a href="${urlAdd}">添加新出库单</a>
<a href="sell_import_input.action">从淘宝地址导入出库单</a><br /><br />
<div>总数:${count}</div><br />

<#list 1..pageNum as i>
<#if i == page>
[${i}] 
<#else>
<@s.url id="urlPage" action="sell_list">
	<@s.param name="page" value="#{i}"/>
	<@s.param name="status" value="status"/>
</@s.url>
<a href="${urlPage}">[${i}]</a>  
</#if>
</#list><br /><br />

<#if sellList??>
<#list sellList as sell>
<div>
旺旺:${sell.customerWangwang}<br />
姓名:${sell.customerName}<br />
<#if status = -1>
日期:${sell.createDate?string("yyyy-MM-dd")}<br />
</#if>
地址:${sell.customerAddress}<br />
<a href="sell.action?id=${sell.id}">查看/编辑</a>
<a href="sell_item.action?sellId=${sell.id}">添加购买的宝贝</a>
<a href="express_input.action?sellId=${sell.id}" target="_balnk">打印快递单</a>
<a href="invoice_input.action?sellId=${sell.id}" target="_balnk">打印发货单</a>
<#if sell.status = 0>
<a href="sell_status.action?id=${sell.id}&status=1">设为已处理</a>
</#if>
</div>
<br />
</#list>
</#if>

</body>
</html>
