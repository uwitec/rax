<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>检查买家收货信息</title>
<script type="text/javascript" language="javascript">
function setDate(value) {
	var year	= 0;
	var month	= 0;
	var day		= 0;
	var date	= new Date();
	var obj		= document.getElementById("express_date");
	date.setTime(date.getTime() + value * 86400000);
	year	= date.getFullYear();
	month	= date.getMonth() + 1;
	day		= date.getDate();
	obj.value = year + "-" + month + "-" + day;
}
window.onload = function() {
	var date	= new Date();
	var id		= date.getHours() >= 18 ? "express_dateSel1" : "express_dateSel0";
	var obj		= document.getElementById(id);
	obj.click();
}
</script>
</head>

<body>
<div>
<@s.form action="express">
    <@s.textfield label="姓名" name="sell.customerName"/>
    <@s.textarea label="地址" name="sell.customerAddress" cols="80" rows="3"/>
    <@s.textfield label="电话1" name="sell.customerPhone1"/>
    <@s.textfield label="电话2" name="sell.customerPhone2"/>
    <@s.textfield label="邮编" name="sell.customerPostCode"/>
    <@s.textfield label="日期" name="date"/>
    <@s.radio name="dateSel" list="dateSel" onclick="javascript:setDate(this.value)"/>
    <@s.radio name="dateSel1" list="{'今天':0, '明天':1, '后天':2}" />
    <@s.textarea label="快递单备注" name="sell.commentExpress" cols="80" rows="3"/>
    <@s.textfield label="发件人" name="sender"/>
    <@s.textfield label="发件人电话" name="senderPhone"/>
    <@s.textarea label="发件人地址" name="senderAddress" cols="80" rows="3"/>
    <@s.textfield label="发件人邮编" name="senderPostCode"/>
    <@s.hidden name="sell.expressId"/>
    <@s.hidden name="sellId"/>
    <@s.submit value=" 提 交 "/>
    <@s.reset value=" 重 置 "/>
</@s.form>
</div>

</body>
</html>
