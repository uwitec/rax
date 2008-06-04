<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>导入卖出宝贝列表</title>
<script language="javascript" type="text/javascript" src="js/dojo/dojo.js" djConfig="isDebug:false,usePlainJson:true,bindEncoding:'UTF-8'"></script>
<script language="javascript" type="text/javascript">

var sellId;
var wareList;
var importList;
var importIdx = 0;

function addOption(objSelect, text, value, expend) {
	var objOption	= document.createElement("OPTION");
	var obj			= objSelect.appendChild(objOption);
	objOption.text	= text;
	objOption.value	= value;
	//console.debug("AddOption:" + objOption.text + "," + objOption.value + "," + expend);
	if (expend == true && objSelect.options.length > objSelect.size) {
  		objSelect.size = objSelect.options.length;
  	}
}

function delOption(objSelect, idx, expend) {
	try {
		//console.debug("DelOption:" + objSelect.options[idx].text + "," + objSelect.options[idx].value);
		objSelect.remove(idx);
		if (expend == true && objSelect.options.length >= 2) objSelect.size = objSelect.options.length;
	} catch (ex) { alert(ex.description); }
}

function doParse(content) {
	var params = {
		sellContent:content
	}
	dojo.xhrGet({
		url: "/erp/json/sell_item_import.action",
		content: params,
		preventCache: true,
		handleAs: "json",
		load: function(json) { try {
			var obj = dojo.byId("importResult");
			while (obj.options.length > 0) {
				obj.remove(0);
			}
			var item = null;
			for (var i = 0; i < json.itemList.length; i++) {
				item = json.itemList[i];
				addOption(obj, item.name, i, true);
				//console.debug(item);
			}
			importList = json.itemList;
			//console.debug(importList);
		} catch(e) { console.debug(e.toString()); } },
		error: function(response) { console.debug(response.status); }
	});
}

function onImport(event) {
	var obj = dojo.byId("sellContent");
	if (dojo.trim(obj.value).length > 1) {
		doParse(obj.value);
		//console.debug(obj.value);
	}
	obj = dojo.byId("importLayer");
	obj.style.display = "none";
	obj = dojo.byId("searchLayer");
	obj.style.display = "block";	
}

function onConfirm(event) {
	if (this.selectedIndex < 0) return;
	var obj = this.options[this.selectedIndex];
	console.debug(obj.text + obj.value);
	importIdx = obj.value;
	delOption(this, this.selectedIndex, true);
	doSearch(obj.text);
}

function doSearch(keyword, id) {
	var params = {
		keyword:keyword
	}
	dojo.xhrGet({
		url: "/erp/json/ware_search.action",
		content: params,
		preventCache: true,
		handleAs: "json",
		load: function(json) { try {
			var idx = 0;
			var opt = null;
			var obj = dojo.byId("search_result");
			while (obj.options.length > 0) {
				obj.remove(0);
			}
			
			var ware = null;
			for (var i = 0; i < json.wareList.length; i++) {
				ware = json.wareList[i];
				// console.debug(ware);
				if (false == ("flag" in ware)) {
					addOption(obj, ware.name, ware.id);
				}
			}
			wareList = json.wareList;
		} catch(e) { console.debug(e.toString()); } },
		error: function(response) { console.debug(response.status); }
	});
}

function onSelect(event) {
	if (this.selectedIndex < 0) return;
	var obj = this.options[this.selectedIndex];
	//console.debug(obj.text + ":" + obj.value);

	var itemId;
	var itemName;
	var itemPrice;
	var itemNum;
	
	try {
		var item = importList[importIdx];
		itemPrice = item.price;
		itemNum = item.number;
	} catch(ex) {
		console.debug(ex.toString());
	}
	
	var ware;
	for (var i = 0; i < wareList.length; i++) {
		ware = wareList[i];
		if (obj.value == ware.id) {
			itemName = ware.name;
			itemId = ware.id;
			break;
		}
	}
	
	if (confirm("名称:" + itemName + "\n数量:" + itemNum + "\n单价:" + itemPrice)) {
		console.debug("id:" + itemId + " num:" + itemNum + " price:" + itemPrice);
		while (this.options.length > 0) {
			this.remove(0);
		}
		onSubmit(itemId, itemPrice, itemNum);
	}
}

function onSubmit(itemId, itemPrice, itemNum) {
	var params = {
		wareId:itemId,
		price:itemPrice,
		number:itemNum,
		sellId:sellId,
	}
	
	//console.debug(params);
	//return;
	
	dojo.xhrPost({
		url: "/erp/json/sell_item_add.action",
		content: params,
		handleAs: "json",
		load: function(json) {
			var ret = false;
			if (json.id > 0) {
				var objResult = dojo.byId("addList");
				var ware;
				if (wareList != null) {
					for (var i = 0; i < wareList.length; i++) {
						ware = wareList[i];
						if (itemId == ware.id) {
							addOption(objResult, ware.name + " * " + itemNum, null, true);
							break;
						}
					}
				}
				ret	= true;							
			}
			this.onResponse(ret);
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
	obj = dojo.byId("sellId");
	sellId = obj.value;
	
	obj = dojo.byId("importSubmitBtn");
	dojo.connect(obj, "onclick", obj, onImport);
	
	obj = dojo.byId("importResult");
	dojo.connect(obj, "ondblclick", obj, onConfirm);
	
	obj = dojo.byId("search_result");
	dojo.connect(obj, "ondblclick", obj, onSelect);
	
	
	obj = dojo.byId("importLayer");
	obj.style.display = "block";
	obj = dojo.byId("searchLayer");
	obj.style.display = "none";
});

</script>
<style type="text/css">
select { width:380px; }
label { cursor:pointer; }
</style>
</head>

<body>
<@s.url id="url" action="sell">
	<@s.param name="id" value="sellId"/>
</@s.url>
<a href="sell_list.action">返回发货单列表</a>
<a href="${url}">返回发货信息</a>
<br /><br />
<@s.hidden name="sellId"/>

<div id="importLayer">
<@s.textarea name="sellContent" cols="80" rows="8"/><br />
<input type="button" id="importSubmitBtn" value=" 提 交 "/>
</div>

<div id="searchLayer">
<@s.select name="importResult"/><br />
<@s.select name="search_result" size="12"/><br />
<@s.select name="addList"/>
<span id="submitStatus"></span>
</div>

</body>
</html>
