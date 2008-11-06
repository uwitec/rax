<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>宝贝属性</title>
<script language="javascript" type="text/javascript" src="js/dojo/dojo.js" djConfig="isDebug:false,usePlainJson:true,bindEncoding:'UTF-8'"></script>
<script language="javascript" type="text/javascript">

function parseToken() {
	var params = {
		name: dojo.byId("ware_save_name").value
	}
	dojo.xhrPost({
		url: "/erp/json/tokenize.action",
		content: params,
		preventCache: true,
		handleAs: "json",
		load: function(json) { try {
			console.debug(json);
			dojo.byId("ware_save_tokenize").value = json.tokenize;
		} catch(e) { console.debug(e.toString()); } },
		error: function(response) { console.debug(response.status);	}
	});
}

function onSubmit(event) {
	var id			= dojo.byId("ware_save_id").value;
	var tokenize	= dojo.byId("ware_save_tokenize").value;
	if (id == 0 && tokenize.length == 0) {
		parseToken();
		return false;
	}
	return true;
}

dojo.addOnLoad(function (){
	var obj;
	
	obj = dojo.byId("ware_save_name");
	var name = dojo.trim(obj.value);
	if (name.length > 0) parseToken();
	
	obj = dojo.byId("ware_save");
	dojo.connect(obj, "onsubmit", obj, onSubmit);
});

</script>
</head>

<body>
<@s.url id="urlReturn" action="ware_list">
	<@s.param name="page" value="page" />
</@s.url>
<a href="${urlReturn}">返回</a><br /><br />

<div>
<@s.form action="ware_save">
    <@s.textfield label="名称" name="name"/>
    <@s.textfield label="条码" name="barcode"/>
    <@s.textfield label="成本" name="cost"/>
    <@s.textfield label="价格" name="price"/>
    <@s.textfield label="数量" name="number"/>
    <@s.select label="分类" name="categoryId" list="categoryMap"/>
    <@s.textarea rows="3" cols="40" label="关键词" name="tokenize"/>
    <@s.hidden name="id"/>
    <@s.submit value=" 提 交 "/>
</@s.form>
</div>

<@s.url id="urlDelete" action="ware_delete">
	<@s.param name="id" value="id"/>
	<@s.param name="page" value="page"/>
</@s.url>
<#if id != 0>
<a href="${urlDelete}" onclick="return confirm('确实要删除这个宝贝么？')">删除</a>
</#if>

</body>
</html>
