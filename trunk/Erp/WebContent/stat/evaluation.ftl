<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>评价格式转换</title>
<script language="javascript" type="text/javascript" src="../js/dojo/dojo.js" djConfig="isDebug:false,usePlainJson:true,bindEncoding:'UTF-8'"></script>
<script language="javascript" type="text/javascript">

var evaList = new Array();

function setRank(obj, name)
{
	console.debug("setRank: " + name);
	for (var i = 0; i < evaList.length; i++) {
		if (evaList[i].name == name) {
			evaList[i].rank = obj.value;
			evaList[i].haveRank = true;
		}
	}
}

function addRow(objTable, cells)
{
	var row;
	var cell;
	try {
		row = objTable.insertRow(objTable.rows.length);
		for (var i = 0; i < cells.length; i++) {
			cell = row.insertCell(row.cells.length);
			cell.innerHTML = cells[i];
		}
	} catch (ex) { console.debug("addRow failed" + ex.description); }
}

function buildSelect(id)
{
	try {
		sel = dojo.byId(id);
		op = document.createElement("OPTION");
		op.value = "";
		op.innerHTML = "";
		sel.appendChild(op);
		
		var lv = ["red", "blue", "cap"];
		for (var i = 0; i < lv.length; i++) {
			for (var j = 1; j <= 5; j++) {
				op = document.createElement("OPTION");
				op.value = "b_" + lv[i] + "_" + j + ".gif";
				op.innerHTML = j + lv[i];
				sel.appendChild(op);
			}
		}
	} catch (ex) { console.debug("build Select failed" + ex.desription); }
}

function onParse(text)
{
	var params = { evaluation: text	}
	dojo.xhrPost({
		url: "/erp/json/parse_evaluation.action",
		content: params,
		preventCache: true,
		handleAs: "json",
		load: function(json) {
			evaList = json.evaList;
			var objTbl = dojo.byId("evaTable");
			for (var i = 0; i < evaList.length; i++) {
				var e = evaList[i];
				var name = e.name.substr(0, 1) + "****" + e.name.substr(e.name.length - 1, 1);
				var rank = "";
				if (e.haveRank) rank = e.rank.length ? "<img src='http://pics.taobaocdn.com/newrank/" + e.rank + "'/>" : "";
				else rank = "<select id='sel_" + name + "' onchange='setRank(this, \"" + e.name + "\")'></select>";
				addRow(objTbl, [e.pubDate, e.content, name, rank]);
				if (e.haveRank == false) buildSelect("sel_" + name);
			}
			this.onResponse(true);
		},
		error: function(response) { 
			console.debug("onError:" + response.status);
			this.onResponse(false);
		},
		onResponse: function(flag) {
			var text		= flag ? "提交成功" : "提交失败";
			var obj			= dojo.byId("parseStatus");
			obj.innerHTML	= text;
			objTimer		= setTimeout(dojo.hitch(this, "onFinish"), 3000);
		},
		onFinish: function() {
			var obj			= dojo.byId("parseStatus");
			obj.innerHTML	= "";
		}
	});
}

function onSubmit()
{
	var obj = dojo.byId("evaluation");
	if (obj != null) {
		var text = dojo.trim(obj.value);
		if (text.length > 0) {
			onParse(text);
			dojo.byId("ev_input").style.display = "none";
			dojo.byId("ev_list").style.display = "block";
		}
	}
}

function onGenerate()
{
	dojo.byId("ev_list").style.display = "none";
	var content = this.name == "full" ? "<table><tbody>" : "";
	for (var i = 0; i < evaList.length; i++) {
		var e = evaList[i];
		console.debug("name: " + e.name + "  rank: " + e.rank);
		
		var name = e.name.substr(0, 1) + "****" + e.name.substr(e.name.length - 1, 1);
		var img = e.rank.length ? "<img src='http://pics.taobaocdn.com/newrank/" + e.rank + "'/>" : "";
		
		content += "<tr>";
		content += "<td valign='top'><img src='http://img.taobao.com/my/rate/pj2/pj_040924_01.gif'/>好评&nbsp;&nbsp;</td>";
		content += "<td width='600'>[<font color='blue'>详情</font>]&nbsp;&nbsp;" + e.content + "&nbsp;&nbsp;" + e.pubDate +  "</td>";
		content += "<td valign='top'>买家:&nbsp;" + name + "&nbsp;" + img + "</td>";
		content += "</tr>";
	}
	if (this.name == "full") content += "</tbody></table>";
	
	var obj = dojo.byId("ev_html");
	obj.value = content;
	obj.style.display = "block";
	
	dojo.byId("div_html").innerHTML = content;
}

dojo.addOnLoad(function (){
	var obj;	
	obj = dojo.byId("parseBtn");
	dojo.connect(obj, "onclick", onSubmit);
	obj = dojo.byId("translate_full");
	dojo.connect(obj, "onclick", onGenerate);
	obj = dojo.byId("translate_lite");
	dojo.connect(obj, "onclick", onGenerate);

});

</script>
<style type="text/css">
#ev_list, #ev_html { display: none; }
</style>
</head>

<body>
<a href="stat_summary.action">返回</a><br /><br />

<div id="ev_input">
<label for="evaluation">评价:</label>
<textarea id="evaluation" cols="80" rows="2" ></textarea>
<input type="button" id="parseBtn" value=" 提 交 "/>
<div id="parseStatus"></div>
</div>

<div id="ev_list">
<table id="evaTable">
<tr>
<td>日期</td>
<td width="600">内容</td>
<td>买家</td>
<td>等级</td>
</tr>
</table>
<input type="button" id="translate_full" name="full" value=" 带表格转换 "/><br />
<input type="button" id="translate_lite" name="lite" value=" 无表格转换 "/>
</div>

<div id="div_html"></div>
<textarea cols="80" rows="5" id="ev_html"></textarea>

</body>
</html>
