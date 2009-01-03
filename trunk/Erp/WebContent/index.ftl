<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>ERP</title>
<script language="javascript" type="text/javascript" src="js/dojo/dojo.js" djConfig="isDebug:false,usePlainJson:true,bindEncoding:'UTF-8'"></script>
<script language="javascript" type="text/javascript">

dojo.addOnLoad(function (){
	var obj;
	obj = dojo.byId("computeInput");
	dojo.connect(obj, "onkeyup", obj, onKeyPress);
	//dojo.connect(obj, "onfocus", obj, obj.select);
	//dojo.connect(obj, "onclick", obj, obj.select);
	obj = dojo.byId("computeButton");
	dojo.connect(obj, "onclick", obj, onCompute);
});

function onCompute()
{
  var obj	= dojo.byId("computeInput");
  var dsp	= dojo.byId("dispLayer");
  dsp.innerHTML = Math.round(obj.value * 2.834645669291);
  return false;
}

function onKeyPress(evt)
{
  if (evt.keyCode == 13 || evt.keyCode == 0) {
    onCompute();
    this.select();
  }
}

</script>
</head>

<body>
<p>
<a href="stat/stat_summary.action">统计数据</a>
<a href="order/order_list.action">进货单列表</a>
<a href="ware_list.action">宝贝列表</a>
<a href="ware_category_list.action">分类列表</a>
<a href="sell_list.action">发货单列表</a>
</p>
<p>
<input id="computeInput" type="text"/>
<input id="computeButton" type="button" value=" 计 算 "/>
</p>
<div id="dispLayer"></div>

<table>
<tr>
<td>数量</td>
<td>警戒数</td>
<td>宝贝名称</td>
<td>操作</td>
</tr>
<#if wareList??>
<#list wareList as ware>
<tr>
<td>${ware.number}</td>
<td>#{ware.numberAlarm}</td>
<td><a href="ware.action?id=${ware.id?c}">${ware.name}</a></td>
<td><a href="ware_alarm.action?id=${ware.id?c}">暂时不提醒</a></td>
</tr>
</#list>
</#if>
</table>
<br />

</body>
</html>