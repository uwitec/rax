<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>淘宝在线</title>
<link type="text/css" href="../css/smoothness/jquery-ui-1.8.11.css" rel="stylesheet" />
<script language="javascript" type="text/javascript" src="../js/jquery-1.5.1.min.js"></script>
<script language="javascript" type="text/javascript" src="../js/jquery-ui-1.8.11.min.js"></script>
<script language="javascript" type="text/javascript">

$(document).ready(function() {

	$("tr.item").hover(
		function() { $(this).addClass("over"); },
		function() { $(this).removeClass("over"); }
	).click(function() {
		var itemObj = $(this);
		var itemIid = $(this).find("img").attr("title");
		var itemName = $(this).find(".item_name").text();
		var params = { keyword: itemName };
		$.ajax({
			url: "/erp/json/ware_find_fulltext.action",
			type: "POST",
			cache: false,
			dataType: "json",
			data: params,
			success: function(data, textStatus, jqXHR) {
				var ul = $("#selection > ul");
				var li;
				console.debug(ul);
				ul.empty();
				$.each(data.wareList, function(index, obj) {
					console.debug("id:" + obj.id + " name:" + obj.name);
					li = $("<li />");
					li.text(obj.name);
					li.data("id", obj.id);
					li.click(function() {
						itemObj.data("wareId", $(this).data("id"));
						console.debug("wareId:" + obj.id);
						itemObj.find(".item_ware").text($(this).text());
						ul.empty();
					});
					ul.append(li);
				});
			}
		});
	});

});

</script>
<style media="print">
body { display: none; }
</style>
<style type="text/css">
td { padding: 3px; }
.comment { color: red; }
.title { background: #DDD; }
.title td { padding: 6px; }
.item.over { background: #EEE; }
</style>
</head>

<body>
<a href="trade_list.action">返回</a>
<br /><br />

<div>
<@s.form action="sell_import">
	<@s.textfield label="买家ID" name="buyerNick"/>
    <@s.textfield label="收件人姓名" name="receiverName"/>
    <@s.textarea label="收件人地址" name="receiverAddress" cols="80" rows="4"/>
    <@s.textfield label="收件人移动电话" name="receiverMobile"/>
    <@s.textfield label="收件人固定电话" name="receiverPhone"/>
    <@s.textfield label="收件人邮编" name="receiverZip"/>
    <@s.textfield label="发货日期" name="created"/>
    <@s.radio name="deliverDate" list="deliverDateOption"/>
    <@s.textfield label="收取运费" name="postFee"/>
    <@s.textfield label="实际运费" name="postFeeReal"/>
    <@s.radio name="postFeeRealOption" list="{0, 3.5, 4, 5, 8, 10, 12, 15, 20, 25}"/>
    <@s.radio label="快递" name="expressId" list="expressOption"/>
    <@s.textfield label="买家留言" name="buyerMessage"/>
    <@s.textfield label="买家备注" name="buyerMemo"/>
    <@s.textfield label="卖家备注" name="sellerMemo"/>
    <@s.submit value=" 提 交 "/>
</@s.form>
</div>

<!--
	private String payment;
	private String postFee;
	private String totalFee;
	private String discountFee;
	private String adjustFee;
-->

<#if orders??>
<table>
<tr class="title">
<td class="" colspan="2">名称</td>
<td class="">标价</td>
<td class="">数量</td>
<td class="">关联宝贝</td>
</tr>
<#list orders as o>
<tr class="item">
<td class=""><img src="${o.picPath}" width="50" title="${o.numIid}" /></td>
<td class="item_name">${o.title}</td>
<td class="">${o.price}</td>
<td class="">${o.num}</td>
<td class="item_ware"></td>
</tr>
</#list>

</table>
</#if>

<div id="selection">
<ul>
</ul>
</div>

<br /><br />

</body>
</html>
