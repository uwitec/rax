<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>AddFaq</title>
</head>

<body>
<@s.url id="url" action="faqs"/>
<a href="${url}">返回</a><br /><br />

<div>
<@s.form action="faq_add">
    <@s.textfield label="Question" name="question" />
    <@s.textfield label="Answer" name="answer" />
    <@s.radio label="IsPub" name="pub" list="pubSel" listKey="value" listValue="key" />
    <@s.submit value="Submit"/>
</@s.form>
</div>

</body>
</html>
