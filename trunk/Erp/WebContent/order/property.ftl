<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>进货单详情</title>
<script type="text/javascript" language="javascript">
function setFee(value) {
	var obj = document.getElementById("order_save_order_fee");
	obj.value = value;
}
function onFeeChange(value) {
	var objs = document.getElementsByName("feeSel");
	for (var i = 0; i < objs.length; i++) {
		if (Math.floor(objs[i].value) == value) objs[i].checked = true
		else objs[i].checked = false;
	}
}
window.onload = function() {
	var obj;
	obj = document.getElementById("order_save_order_fee");
	onFeeChange(obj.value);
}
</script>
</head>

<body>
<a href="order_list.action">返回进货单列表</a>
<br /><br />
<#if id != 0>
<a href="order_delete.action?id=${id}" onclick="return confirm('确实要删除这笔订单么？')">删除</a>
<#if order.status == 0>
<a href="order_status.action?id=${id}&status=1">确认到货</a>
<#else>
<a href="order_status.action?id=${id}&status=0">撤除订单</a>
</#if>
</#if>
<br />

<div>
<@s.form action="order_save">
    <#if order??>
	<@s.label label="创建" value="${order.createDate?string('yyyy-MM-dd')}"/>
	</#if>
    <@s.textfield label="备注" name="order.comment"/>
    <@s.textfield label="运费" name="order.fee" onkeyup="javascript:onFeeChange(this.value)"/>
    <@s.radio name="feeSel" list="{0, 5, 10, 12, 15, 20, 25}" onclick="javascript:setFee(this.value)"/>
    <@s.hidden name="id"/>
    <@s.submit value=" 提 交 "/>
</@s.form>
</div>

<#if id != 0>
<a href="order_item.action?orderId=${id}">添加新项目</a>
<br /><br />
</#if>

<#if orderItemList??>
<#list orderItemList as item>
<div>
<a href="order_item.action?id=${item.id}&orderId=${id}" title="${item.cost} * ${(item.number)}">
${item.ware.name} * ${(item.number)} 
</a>
</div>
</#list>
</#if>

</body>
</html>
