<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>发货单详情</title>
<script type="text/javascript" language="javascript">
function setFee(value) {
	var obj = document.getElementById("sell_save_fee");
	obj.value = value;
}
function setFeeReal(value) {
	var obj = document.getElementById("sell_save_feeReal");
	obj.value = value;
}
function onFeeChange(value) {
	var objs = document.getElementsByName("feeSel");
	//alert("onFeeChange:" + value);
	for (var i = 0; i < objs.length; i++) {
		if (Math.floor(objs[i].value) == value) objs[i].checked = true
		else objs[i].checked = false;
	}
}
function onFeeRealChange(value) {
	var objs = document.getElementsByName("feeRealSel");
	//alert("onFeeRealChange:" + value);
	for (var i = 0; i < objs.length; i++) {
		if (Math.floor(objs[i].value) == value) objs[i].checked = true;
		else objs[i].checked = false;
	}
}
window.onload = function() {
	var obj;
	obj = document.getElementById("sell_save_fee");
	onFeeChange(obj.value);
	obj = document.getElementById("sell_save_feeReal");
	onFeeRealChange(obj.value);
	//alert("onload");
}
</script>
</head>

<body>
<@s.url id="url" action="sell_list"/>
<a href="${url}">返回发货单列表</a>
<a href="express_input.action?sellId=${id}" target="_balnk">打印快递单</a>
<a href="sell_import.action?sellId=${id}">从淘宝地址导入出库单</a>
<a href="sell_delete.action?id=${id}" onclick="return confirm('确实要删除这笔交易么？')">删除</a><br />
<br />

<div>
<@s.form action="sell_save">
    <@s.textfield label="旺旺" name="customerWangwang"/>
    <@s.textfield label="姓名" name="customerName"/>
    <@s.textarea label="地址" name="customerAddress" cols="80" rows="3"/>
    <@s.textfield label="电话1" name="customerPhone1"/>
    <@s.textfield label="电话2" name="customerPhone2"/>
    <@s.textfield label="邮编" name="customerPostCode"/>
    <@s.textfield label="收取运费" name="fee" onkeyup="javascript:onFeeChange(this.value)"/>
    <@s.radio name="feeSel" list="{0, 5, 10, 12, 15, 20, 25}" onclick="javascript:setFee(this.value)"/>
    <@s.textfield label="实际运费" name="feeReal" onkeyup="javascript:onFeeRealChange(this.value)"/>
    <@s.radio name="feeRealSel" list="{0, 4, 5, 8, 10, 12, 15, 20, 25}" onclick="javascript:setFeeReal(this.value)"/>
    <@s.radio label="快递" name="expressId" list="expressSel"/>
    <@s.textfield label="快递单备注" name="commentExpress"/>
    <@s.textfield label="发货单备注" name="commentInvoice"/>
    <@s.textfield label="发件人" name="sender"/>
    <@s.hidden name="id"/>
    <@s.submit value=" 提 交 "/>
</@s.form>
</div>

<a href="sell_item.action?sellId=${id}">添加新项目</a>
<a href="invoice_input.action?sellId=${id}" target="_balnk">打印发货单</a><br /><br />

<#if sellItemList??>
<#list sellItemList as item>
<div>
<a href="sell_item.action?id=${item.id}" title="${item.price} * ${(item.number)}">
${item.ware.name} * ${(item.number)} 
</a>
</div>
</#list>
</#if>

</body>
</html>
