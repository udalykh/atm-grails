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
            background: #ECFCE4 url(assets/bismuth.bmp) repeat-y;
        }
        </style>
        <br/>
        <br/>
        <g:img file="bismuthlogo.png"/>
        <br/><br/>
        <h5>
            Welcome to Bismuth ATM.
        </h5>
        <br/>
        <strong>Action:</strong>

        <div class="input-group">
            <div class="input-group-addon"><span class="glyphicon glyphicon-arrow-right"></span></div>
            <g:select name="command" id="command" class="form-control" from="${com.test.atm.CommandType.values()}"
                      value="${com.test.atm.CommandType.REMAININGS}">
            </g:select>
        </div>
        <label id="currencylabel" for="currency">Currency:</label>
        <g:select name="currency" class="form-control" from="${com.test.atm.Currency.values()}">
        </g:select><br/>
        <br/>

        <div class="form-group">
            <label id="valuelabel" for="value">Value:</label>
            <g:field class="form-control" type="number" name="value" id="value" min="0"
                     placeholder="Value of banknote"/>
        </div>

        <div class="form-group">
            <label id="numberlabel" for="number">Number:</label>
            <g:field class="form-control" type="number" name="number" id="number" min="0"
                     placeholder="Number of banknotes"/></div>

        <div class="form-group">
            <label id="amountlabel" for="amount">Amount:</label>
            <g:field class="form-control" type="number" name="amount" id="amount" min="0"
                     placeholder="Amount of currency"/></div>
        <br/>
        <br/>

        <div class="btn-group">
            <button type="button" name="seven" id="seven" class="btn btn-default">7</button>
            <button type="button" name="eight" id="eight" class="btn btn-default">8</button>
            <button type="button" name="nine" id="nine" class="btn btn-default">9</button>
        </div>
        <br/>

        <div class="btn-group">
            <button type="button" name="four" id="four" class="btn btn-default">4</button>
            <button type="button" name="five" id="five" class="btn btn-default">5</button>
            <button type="button" name="six" id="six" class="btn btn-default">6</button>
        </div>
        <br/>

        <div class="btn-group">
            <button type="button" name="one" id="one" class="btn btn-default">1</button>
            <button type="button" name="two" id="two" class="btn btn-default">2</button>
            <button type="button" name="three" id="three" class="btn btn-default">3</button>
        </div>
        <br/>

        <div class="btn-group">
            <button type="button" name="zero" id="zero" class="btn btn-default">0</button>
            <button type="button" name="triplezero" id="triplezero" class="btn btn-default">000</button>
            <button type="button" name="backspace" id="backspace" class="btn btn-default">C</button>
        </div>
        <br/>
        <a data-toggle="tooltip" data-placement="top" title="Click to submit the action">
            <g:actionSubmit value="GO" action="index" class="btn btn-primary">
                <span class="glyphicon glyphicon-align-left" aria-hidden="true"></span><br/>
            </g:actionSubmit></a>
        <br/>

        <h3>Result:</h3>

        <div class="col-md-2 col-md-offset-5">
            <table class="table table-condensed">
                <tbody>
                <div style="overflow:auto;">
                    <g:if test="${error}">
                        <tr>ERROR</tr>
                    </g:if>
                    <g:else>
                        <g:each in="${response}">
                            <tr><td>${it.key} ${it.value}</td></tr>
                        </g:each>
                    </g:else>
                </div>
                </tbody>
            </table>
        </div><br/>
    </div>
</g:form>
</body>
</html>