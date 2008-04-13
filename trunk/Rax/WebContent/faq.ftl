<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>GetFaq</title>
</head>

<body>
<@s.url id="url" action="faqs"/>
<a href="${url}">返回</a><br /><br />

<div>
${faq.id}<br />
${faq.question}<br />
${faq.answer}<br />
${faq.pubDate?string('yyyy-MM-dd')}<br />
${faq.pub?string("public", "not public")}
</div>

</body>
</html>
