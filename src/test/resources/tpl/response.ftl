<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <style>
        body { font-family: Arial, sans-serif; font-size: 14px; }
        pre { white-space: pre-wrap; background: #f5f5f5; padding: 8px; }
    </style>
</head>
<body>
<h4>Код ответа</h4>
<pre><b><#if data.responseCode??>${data.responseCode}<#else>Unknown</#if></b></pre>

<#if data.url??>
    <pre>${data.url?replace("appid=[^&]*", "appid=***", "r")}</pre>
</#if>

<#if data.body??>
    <h4>Тело ответа</h4>
    <pre>${data.body}</pre>
</#if>

<#if (data.headers)?has_content>
    <h4>Заголовки</h4>
    <#list data.headers as name, value>
        <pre><b>${name}</b>: ${value}</pre>
    </#list>
</#if>
</body>
</html>
