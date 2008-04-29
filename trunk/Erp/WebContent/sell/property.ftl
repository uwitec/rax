<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>AddFaq</title>
</head>

<body>
<@s.url id="url" action="sell_list" includeParams="none"/>
<a href="${url}">返回</a><br /><br />

<div>
<@s.form action="sell_save">
    <@s.textfield label="姓名" name="customerName"/>
    <@s.textfield label="地址" name="customerAddress"/>
    <@s.textfield label="电话1" name="customerPhone1"/>
    <@s.textfield label="电话2" name="customerPhone2"/>
    <@s.textfield label="邮编" name="customerPostCode"/>
    <@s.textfield label="旺旺" name="customerWangwang"/>
    <@s.textfield label="收取运费" name="fee"/>
    <@s.textfield label="实际运费" name="feeReal"/>
    <@s.radio label="打印" name="print" list="printSel" listKey="value" listValue="key" />
    <@s.textfield label="快递" name="expressId"/>
    <@s.textfield label="快递单号" name="expressBarcode"/>
    <@s.hidden name="id"/>
    <@s.submit value=" 提 交 "/>
    <@s.reset value=" 重 置 "/>
</@s.form>
</div>

</body>
</html>
