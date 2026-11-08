<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <style>
        body { font-family: Arial, sans-serif; font-size: 14px; }
        pre { white-space: pre-wrap; background: #f5f5f5; padding: 8px; }
    </style>
</head>
<body>
<h4>Запрос</h4>
<pre><#if data.method??>${data.method}<#else>GET</#if>: <#if data.url??>${data.url?replace("appid=[^&]*", "appid=***", "r")}<#else>Unknown</#if></pre>

<#if data.body??>
    <h4>Тело запроса</h4>
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
