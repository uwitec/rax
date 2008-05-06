<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>导入买家收货信息</title>
<script type="text/javascript" language="javascript">
function setFee(value) {
	var obj = document.getElementById("sell_import_fee");
	obj.value = value;
}
function setFeeReal(value) {
	var obj = document.getElementById("sell_import_feeReal");
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
	obj = document.getElementById("sell_import_fee");
	onFeeChange(obj.value);
	obj = document.getElementById("sell_import_feeReal");
	onFeeRealChange(obj.value);
	//alert("onload");
}
</script>
</head>

<body>
<@s.url id="url" action="sell_list" includeParams="none"/>
<a href="${url}">返回</a> 
<br />

<div>
<@s.form action="sell_import">
    <@s.textarea label="地址" name="content" cols="80" rows="8"/>
    <@s.textfield label="旺旺" name="wangwang"/>
    <@s.textfield label="收取运费" name="fee" onkeyup="javascript:onFeeChange(this.value)"/>
    <@s.radio name="feeSel" list="{0, 5, 10, 12, 15, 20, 25}" onclick="javascript:setFee(this.value)"/>
    <@s.textfield label="实际运费" name="feeReal" onkeyup="javascript:onFeeRealChange(this.value)"/>
    <@s.radio name="feeRealSel" list="{0, 4, 5, 8, 10, 12, 15, 20, 25}" onclick="javascript:setFeeReal(this.value)"/>
    <@s.radio label="快递" name="expressId" list="expressSel"/>
    <@s.textfield label="备注" name="comment"/>
    <@s.textfield label="发件人" name="sender"/>
    <@s.hidden name="sellId"/>
    <@s.submit value=" 提 交 "/>
    <@s.reset value=" 重 置 "/>
</@s.form>
</div>

</body>
</html>
