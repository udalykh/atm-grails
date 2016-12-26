<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Bismuth ATM</title>
    %{--<link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}" type="text/css">--}%
</head>

<body>
<g:form name="atm-window" controller="atm" class="form-inline" action="index" method="post" id="atm">
    <div align="center">
        <style>
        body {
            background-color: #ECFCE4;
        }</style>
        <br/>
        <br/>
        <strong>Action:</strong>
        <g:select name="command" class="form-control" from="${com.test.atm.CommandType.values()}">
        </g:select>
        Currency:
        <g:select name="currency" class="form-control" from="${com.test.atm.Currency.values()}">
        </g:select><br/>
        <br/>

        Value:   <g:field class="form-control" type="number" name="value" min="0" placeholder="Value of banknote"/>
        Number:   <g:field class="form-control" type="number" name="number" min="0" placeholder="Number of banknotes"/>
        Amount:   <g:field class="form-control" type="number" name="amount" min="0" placeholder="Amount of currency"/>
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