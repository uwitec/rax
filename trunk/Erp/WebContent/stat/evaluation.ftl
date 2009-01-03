<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>评价格式转换</title>
<script language="javascript" type="text/javascript" src="../js/dojo/dojo.js" djConfig="isDebug:false,usePlainJson:true,bindEncoding:'UTF-8'"></script>
<script language="javascript" type="text/javascript">

var eList = new Array();

function addEvaluation(n, d, c) {
	try {
		var e = {
			name:n,
			pubDate:d,
			content:c,
			rank:""
		};
		eList.push(e);
	} catch (err) {	console.debug(err);	}
}

function setRank(n, r) {
	console.debug("setRank", n, r);
	for (var i = 0; i < eList.length; i++) {
		if (eList[i].name == n) {
			eList[i].rank = r;
		}
	}
}

function onParse() {
	var obj;
	obj = dojo.byId("ev_list");
	obj.style.display = "none";
	
	var content = this.name == "full" ? "<table><tbody>" : "";
	for (var i = 0; i < eList.length; i++) {
		var e = eList[i];
		console.debug(e);
		
		var name = e.name.substr(0, 1) + "****" + e.name.substr(e.name.length - 1, 1);
		var img = e.rank.length ? "<img src='http://pics.taobaocdn.com/newrank/" + e.rank + "'/>" : "";
		
		content += "<tr>";
		content += "<td valign='top'><img src='http://img.taobao.com/my/rate/pj2/pj_040924_01.gif'/>好评&nbsp;&nbsp;</td>";
		content += "<td width='600'>[<font color='blue'>详情</font>]&nbsp;&nbsp;" + e.content + "&nbsp;&nbsp;" + e.pubDate +  "</td>";
		content += "<td valign='top'>买家:&nbsp;" + name + "&nbsp;" + img + "</td>";
		content += "</tr>";
	}
	if (this.name == "full") content += "</tbody></table>";

	obj = dojo.byId("ev_html");
	obj.value = content;
	obj.style.display = "block";
	
	obj = dojo.byId("div_html");
	obj.innerHTML = content;
	obj.style.display = "block";
}

dojo.addOnLoad(function (){
<#if evaluationList??>
<#list evaluationList as eva>
	addEvaluation("${eva.name}", "${eva.pubDate?string("yyyy-MM-dd HH:mm:ss")}", "${eva.content}");
</#list>
</#if>
	
	var param = { head: true };
	
	var obj;
	obj = dojo.byId("translate_full");
	dojo.connect(obj, "onclick", onParse);
	
	obj = dojo.byId("translate_lite");
	dojo.connect(obj, "onclick", onParse);
	
	obj = dojo.byId("ev_html");
	obj.value = "";
	obj.style.display = "none";

	obj = dojo.byId("div_html");
	obj.innerHTML = "";
	obj.style.display = "none";

});

</script>
</head>

<body>
<a href="stat_summary.action">返回</a><br /><br />

<div>
<@s.form action="evaluation.action">
    <@s.textarea label="评价" name="evaluation" cols="80" rows="2"/>
    <@s.submit value=" 提 交 "/>
</@s.form>
</div>
<div id="ev_list">
<#if evaluationList??>
<#list evaluationList as eva>
日期:${eva.pubDate?string("yyyy-MM-dd")}<br />
内容:${eva.content}<br />
买家:${eva.name}<br />
等级:<@s.radio name="${eva.name}" list="byerRank" onclick="javascript:setRank(this.name, this.value)"/><br />
<br />
</#list>
<input type="button" id="translate_full" name="full" value=" 带表格转换 "/><br />
<input type="button" id="translate_lite" name="lite" value=" 无表格转换 "/>
</#if>
</div>

<div id="div_html"></div>
<textarea cols="80" rows="5" id="ev_html"></textarea>

</body>
</html>
