<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>宝贝列表</title>
<style type="text/css">
.float { float:left; margin-right:12px; width:80px; }
</style>
</head>

<body>
<a href="../index.htm">返回首页</a>
<br /><br />

<div>库存数:${restore}</div><br />

<div class="float">日期</div>
<div class="float">利润</div>
<div class="float">营业额</div> 
<div>包裹数</div>
<#if weekProfitList??>
<#list weekProfitList as profit>
<div class="float">${profit.statDate?string("yyyy-MM-dd")}</div>
<div class="float">${profit.profit}</div>
<div class="float">${profit.amount}</div> 
<div>${profit.number}</div>
</#list>
</#if>
</div>

</body>
</html>
