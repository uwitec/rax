<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>检查买家订购列表</title>
</head>

<body>
<div>
<@s.form action="invoice">
    <@s.textarea label="备注" name="sell.commentInvoice" cols="80" rows="3"/>
    <@s.textfield label="发件人" name="sell.sender"/>
    <@s.hidden name="sellId"/>
    <@s.submit value=" 提 交 "/>
    <@s.reset value=" 重 置 "/>
</@s.form>
</div>

</body>
</html>
