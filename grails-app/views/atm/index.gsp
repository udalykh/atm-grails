<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Bismuth ATM</title>
    %{--<link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}" type="text/css">--}%
</head>

<body>
<g:form name="atm-window" controller="atm" action="index" method="post" id="atm">
    <div align="center">
        <style>
        body {
            background-color: #E9FCE4;
        }</style>
        <br/>
        <br/>
        <strong>Action:</strong>
        <g:select name="command" from="${com.test.atm.CommandType.values()}">
        </g:select>
        Currency:
        <g:select name="currency" from="${com.test.atm.Currency.values()}">
        </g:select><br/>
        <br/>

        Value:   <g:field type="number" name="value" min="0"/>
        Number:   <g:field type="number" name="number" min="0"/>
        Amount:   <g:field type="number" name="amount" min="0"/>
        <br/>

        <g:actionSubmit value="GO" action="index" class="btn btn-primary">
            <span class="glyphicon glyphicon-align-left" aria-hidden="true"></span><br/>
        </g:actionSubmit>
        <br/>
        <hr>

        <h3>Result:</h3><br>
        <em>
            <g:if test="${error}">
                ERROR
            </g:if>
            <g:else>
                <g:each in="${response}">
                    ${it.key} ${it.value}<br>
                </g:each>
            </g:else>
        </em>
        <br/>
    </div>
</g:form>
</body>
</html>