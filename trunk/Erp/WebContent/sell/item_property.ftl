<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>查看/编辑</title>
<script language="javascript" type="text/javascript" src="js/dojo/dojo.js" djConfig="isDebug:false,usePlainJson:true,bindEncoding:'UTF-8'"></script>
<script language="javascript" type="text/javascript">

var wareList;

function addOption(objSelect, text, value) {
  var objOption		= document.createElement("OPTION");
  var obj			= objSelect.appendChild(objOption);
  objOption.text	= text;
  objOption.value	= value;
  console.debug("AddOption:" + objOption.text + "," + objOption.value);
}

function delOption(objSelect, idx) {
  try {
    console.debug("DelOption:" + objSelect.options[idx].text + "," + objSelect.options[idx].value);
	objSelect.remove(idx);
  } catch (ex) { alert(ex.description); }
}

function doSearch(keyword) {
  var params = {
    keyword:keyword
  }
  dojo.xhrGet({
    url: "/erp/json/ware_find.action",
    content: params,
    preventCache: true,
    handleAs: "json",
    load: function(json) { try {
      var idx = 0;
      var opt = null;
      var obj = dojo.byId("search_result");
      for (var i = obj.options.length; i > 0; i--) {
        idx = i - 1;
        opt = obj.options[idx];
        if (false == dojo.some(json.wareList, function(arg) {
              if (this.value == arg.id) {
                arg.flag = true;
                return this.value;
              }
            },
            opt)) {
          delOption(obj, idx);
        }
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

function onKeyIn(event) {
  if (dojo.trim(this.value).length > 1) {
    doSearch(this.value);
    // console.debug(this.value);
  }
}

function onSelect(event) {
  if (this.selectedIndex < 0) return;
  var obj = this.options[this.selectedIndex];
  console.debug(obj.text + ":" + obj.value);

  var objId		= dojo.byId("sell_item_save_wareId");
  var objPrice	= dojo.byId("sell_item_save_price");
  var objNum	= dojo.byId("sell_item_save_number");
  var ware;
  for (var i = 0; i < wareList.length; i++) {
    ware = wareList[i];
  	if (obj.value == ware.id) {
  	  objId.value		= ware.id;
  	  objPrice.value	= ware.price;
  	  objNum.value		= 1;
  	  break;
  	}
  }
  objNum.focus();
  objNum.select();
}

dojo.addOnLoad(function (){
  var obj;
  obj = dojo.byId("search_result");
  dojo.connect(obj, "ondblclick", obj, onSelect);
  obj = dojo.byId("search");
  dojo.connect(obj, "onkeyup", obj, onKeyIn);
  obj.focus();
});
</script>
<style type="text/css">
#search_result { width:380px; }
</style>
</head>

<body>
<@s.url id="url" action="sell">
	<@s.param name="id" value="sellId"/>
</@s.url>
<a href="sell_list.action">返回发货单列表</a>
<a href="${url}">返回发货信息</a>
<#if id != 0>
<a href="sell_item_delete.action?id=${id}&sellId=${sellId}" onclick="return confirm('确实要删除这项么？')">删除</a>
</#if>
<br /><br />

<div>
<@s.form action="sell_item_save">
    <@s.textfield label="宝贝编号" name="wareId"/>
    <@s.textfield label="价格" name="price"/>
    <@s.textfield label="数量" name="number"/>
    <@s.hidden name="id"/>
    <@s.hidden name="sellId"/>
    <@s.submit value=" 提 交 "/>
</@s.form>
</div>

<div>
<@s.textfield label="搜索" name="search"/> <a href="ware.action" target="_blank">添加新的宝贝</a><br />
<@s.select name="search_result" size="16"/>
</div>

</body>
</html>
