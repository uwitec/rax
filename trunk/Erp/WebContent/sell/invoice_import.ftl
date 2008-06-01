<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>导入卖出宝贝列表</title>
<style type="text/css">
label { cursor:pointer; }
</style>
</head>

<body>
<div>
<@s.form action="invoice_import">
    <@s.textarea label="列表" name="sellContent" cols="80" rows="3"/>
    <@s.hidden name="sellId"/>
    <@s.submit value=" 提 交 "/>
</@s.form>
</div>

</body>
</html>
