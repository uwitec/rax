<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>宝贝列表</title>
<script language="javascript" type="text/javascript" src="../js/dojo/dojo.js" djConfig="isDebug:false,usePlainJson:true,bindEncoding:'UTF-8'"></script>
<script language="javascript" type="text/javascript">

function onSubmit() {
	var obj;
	obj = dojo.byId("startDate");
	var start = obj.value;
	obj = dojo.byId("endDate");
	var end = obj.value;
	var params = {
		startDate:start,
		endDate:end
	}
	console.log(params);
	dojo.xhrPost({
		url: "/erp/json/fee_stat.action",
		content: params,
		handleAs: "json",
		load: function(json) {
			var obj = dojo.byId("feeLayer");
			var objDiv;
			var statFee;
			
			for (var i = 0; i < json.feeList.length; i++) {
				statFee = json.feeList[i];
				objDiv = document.createElement("DIV");
				objDiv.className = "float";
				objDiv.innerHTML = statFee.expressName;
				obj.appendChild(objDiv);

				objDiv = document.createElement("DIV");
				objDiv.className = "float";
				objDiv.innerHTML = statFee.number;
				obj.appendChild(objDiv);

				objDiv = document.createElement("DIV");
				objDiv.innerHTML = statFee.amount;
				obj.appendChild(objDiv);
			}
			this.onResponse(true);
		},
		error: function(response) { this.onResponse(false); },
		onResponse: function(flag) {
			var text		= flag ? "提交成功" : "提交失败";
			var obj			= dojo.byId("submitStatus");
			obj.innerHTML	= text;
			setTimeout(dojo.hitch(this, "onFinish"), 3000);
		},
		onFinish: function() {
			var obj			= dojo.byId("submitStatus");
			obj.innerHTML	= "";
		}
	});
}

dojo.addOnLoad(function (){
	var obj;
	obj = dojo.byId("feeButton");
	dojo.connect(obj, "onclick", obj, onSubmit);

});
</script>
<style type="text/css">
.float { float:left; margin-right:12px; width:80px; }
</style>

</head>

<body>
<a href="../index.htm">返回首页</a>
<br /><br />

<div>库存数:${storeAmount}</div><br />

<div class="float">日期</div>
<div class="float">利润</div>
<div class="float">营业额</div> 
<div>包裹数</div>
<div>
<#if weekProfitList??>
<#list weekProfitList as profit>
<div class="float">${profit.statDate?string("yy-MM-dd")}</div>
<div class="float">${profit.profit}</div>
<div class="float">${profit.amount}</div> 
<div>${profit.number}</div>
</#list>
</#if>
</div>
<br />

<div class="float">月份</div>
<div class="float">利润</div>
<div class="float">营业额</div> 
<div>包裹数</div>
<div>
<#if monthProfitList??>
<#list monthProfitList as profit>
<div class="float">${profit.statDate?string("yy-MM")}</div>
<div class="float">${profit.profit}</div>
<div class="float">${profit.amount}</div> 
<div>${profit.number}</div>
</#list>
</#if>
</div>
<br /><br />

<div>
<div>
<@s.textfield label="起始日期" name="startDate"/>
<@s.textfield label="结算日期" name="endDate"/>
<input type="button" id="feeButton" value=" 计 算 " />
<span id="submitStatus"></span>
</div><br />

<div>
<div class="float">快递</div>
<div class="float">数量</div> 
<div>费用</div>
<div id="feeLayer">
</div>
</div>

</body>
</html>
