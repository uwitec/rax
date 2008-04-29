<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>ListWare</title>
</head>

<body>
<@s.url id="urlReturn" value="index.htm"/>
<@s.url id="urlAdd" action="sell" includeParams="none" />
<a href="${urlReturn}">返回</a> <a href="${urlAdd}">添加新出库单</a><br /><br />
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
姓名:${sell.customerName}<br />
地址:${sell.customerAddress}<br />
电话1:${(sell.customerPhone1)}<br />
电话2:${(sell.customerPhone2)}<br />
邮编:${sell.customerPostCode}<br />
旺旺:${sell.customerWangwang}<br />
收取运费:#{(sell.fee);m2M2}<br />
实际运费:#{(sell.feeReal);m2M2}<br />
日期:${sell.createDate?string("yyyy-MM-dd")}<br />
打印:${(sell.print)?string("已打印", "未打印")}<br />
快递:${sell.expressId}<br />
快递单号:#{sell.expressBarcode}
<a href="sell.action?id=${sell.id}">查看/编辑</a>
<a href="sell_delete.action?id=${sell.id}" onclick="return confirm('确实要删除这笔交易么？')">删除</a>
</div>
<br />
</#list>
</#if>

</body>
</html>
