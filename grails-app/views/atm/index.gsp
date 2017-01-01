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
        Currency:
        <g:select name="currency" class="form-control" from="${com.test.atm.Currency.values()}">
        </g:select><br/>
        <br/>

        <div class="form-group">
            Value:   <g:field class="form-control" type="number" name="value" min="0"
                              placeholder="Value of banknote"
                              disabled="${valuedisable == com.test.atm.CommandType.REMAININGS)as boolean}"/>
        </div>

        <div class="form-group">
            Number:
            <g:field class="form-control" type="number" name="number" min="0"
                     placeholder="Number of banknotes"/></div>

        <div class="form-group">
            Amount:   <g:field class="form-control" type="number" name="amount" min="0"
                               placeholder="Amount of currency"/></div>
        <br/>
        <br/>

        <div class="btn-group">
            <button type="button" class="btn btn-default">7</button>
            <button type="button" class="btn btn-default">8</button>
            <button type="button" class="btn btn-default">9</button>
        </div>
        <br/>

        <div class="btn-group">
            <button type="button" class="btn btn-default">4</button>
            <button type="button" class="btn btn-default">5</button>
            <button type="button" class="btn btn-default">6</button>
        </div>
        <br/>

        <div class="btn-group">
            <button type="button" class="btn btn-default">1</button>
            <button type="button" class="btn btn-default">2</button>
            <button type="button" class="btn btn-default">3</button>
        </div>
        <br/>
        <button type="button" class="btn btn-default">0</button>
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