<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>导入买家收货信息</title>
</head>

<body>
<@s.url id="url" action="sell_list" includeParams="none"/>
<a href="${url}">返回</a> 
<br />

<div>
<@s.form action="sell_import">
    <@s.textarea label="地址" name="content" cols="80" rows="8"/>
    <@s.hidden name="sellId"/>
    <@s.submit value=" 提 交 "/>
    <@s.reset value=" 重 置 "/>
</@s.form>
</div>

</body>
</html>
