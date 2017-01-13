<!DOCTYPE html>
<html>
<head>
    <title></title>
</head>

<body>
<g:form name="atm-response" controller="atm" class="form-inline" action="result" method="get">
    <g:if test="${error}">
        <tr>ERROR</tr>
    </g:if>
    <g:else>
        <g:each in="${response}">
            <tr><td>
                ${it.key} ${it.value}
            </td></tr>
        </g:each>
    </g:else>
</g:form>
</body>
</html>