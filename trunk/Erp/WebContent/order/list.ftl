<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>进货单列表</title>
</head>

<body>
<a href="../index.htm">返回首页</a>
<a href="order_list.action?page=${page}">待处理进货单</a>
<a href="order_listAll.action?page=${page}">所有进货单</a>
<br /><br />
<a href="order.action?page=${page}">添加新进货单</a>
<br /><br />
<div>总数:${count}</div><br />

<#list 1..pageNum as i>
<#if i == page>
[${i}] 
<#else>
<@s.url id="urlPage" action="order_list">
	<@s.param name="page" value="#{i}"/>
	<@s.param name="status" value="status"/>
</@s.url>
<a href="${urlPage}">[${i}]</a>  
</#if>
</#list><br /><br />

<#if orderList??>
<#list orderList as order>
<div>
${order.createDate?string("yyyy-MM-dd")} ${order.comment}<br />
<a href="order.action?id=${order.id?c}">查看/编辑</a>
<a href="order_item.action?orderId=${order.id?c}">添加购买的宝贝</a>
<#if order.status = 0>
<a href="order_status.action?id=${order.id?c}&status=1">设为已处理</a>
</#if>
</div>
<br />
</#list>
</#if>

</body>
</html>
