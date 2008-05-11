<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>出库单</title>
<style type="text/css">
body { font-size:16px; }
</style>
</head>

<body>
<table border="0">
  <tr>
    <td colspan="5" align="center"> 购 物 清 单 - ${sell.customerName}(${sell.customerWangwang})</td>
  </tr>
  <tr>
    <td height="8" colspan="5" align="center"></td>
  </tr>
  <tr>
    <td >货品名称</td>
    <td width="10">&nbsp;</td>	
    <td width="40">数量</td>
    <td width="60">单价</td>
    <td width="60">小节</td>
  </tr>
  <#assign total=sell.fee/>
  <#list sellItemList as item>
  <#assign total=total + item.price * item.number/>
  <tr>
    <td>${item.ware.name}</td>
	<td>&nbsp;</td>	
    <td>${item.number}</td>
    <td>#{(item.price);m2M2}</td>
    <td>#{(item.price * item.number);m2M2}</td>
  </tr>
  </#list>
  <tr>
    <td height="5" colspan="5"></td>
  </tr>
  <tr>
    <td colspan="3"><#if sell.commentInvoice?length gt 0>备注: ${sell.commentInvoice}</#if></td>
    <td>邮费</td>
    <td>#{(sell.fee);m2M2}</td>
  </tr>
  <tr>
    <td><#if sell.sender?length gt 0>${sell.sender}<#else>冰心抹茶</#if> 祝您购物愉快!</td>
	<td>&nbsp;</td>	
    <td>&nbsp;</td>
    <td>合计</td>
    <td>#{total;m2M2}</td>
  </tr>
</table>
</body>
</html>
