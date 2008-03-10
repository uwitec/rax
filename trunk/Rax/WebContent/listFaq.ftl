<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>ListLinks</title>
</head>

<body>
<@s.url id="url" value="index.htm"/>
<a href="${url}">返回</a><br /><br />
<div>总数:${count}</div><br />

<#if faqList??>
<#list faqList as faq>
<div>
id:${faq.id} date:${faq.pubDate}<br />
问：${faq.question}<br />
答：${faq.answer}
</div>
<br />
</#list>
</#if>

</body>
</html>
