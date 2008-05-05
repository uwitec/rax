<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>查看/编辑</title>
<script language="javascript" type="text/javascript" src="js/dojo/dojo.js" djConfig="isDebug:true,bindEncoding:'UTF-8'"></script> 
<script language="javascript" type="text/javascript">

var keyCount = 0;
function onKeyIn(event) {
  if (++keyCount > 2) {
    alert("begin search");
  }
}

dojo.addOnLoad(function (){
  var obj = dojo.byId("search");
  dojo.connect(obj, "onkeyup", onKeyIn);
});
</script>
</head>

<body>
<@s.url id="url" action="sell_list" includeParams="none"/>
<a href="${url}">返回</a><br /><br />

<div>
<@s.form action="sell_item_save">
    <@s.textfield label="宝贝编号" name="wareId"/>
    <@s.textfield label="价格" name="price"/>
    <@s.textfield label="数量" name="number"/>
    <@s.hidden name="id"/>
    <@s.hidden name="sellId"/>
    <@s.submit value=" 提 交 "/>
    <@s.reset value=" 重 置 "/>
</@s.form>
</div>

<div>
<@s.textfield label="搜索" name="search"/>
</div>

</body>
</html>
