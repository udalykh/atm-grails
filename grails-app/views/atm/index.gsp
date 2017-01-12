<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Bismuth ATM</title>
    %{--<link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}" type="text/css">--}%
</head>

<body>
<g:form name="atm-window" controller="atm" class="form-inline" action="index" method="post">
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
                      value="REMAININGS">
            </g:select>
        </div>
        <span class="label-div">
            <label id="currencylabel" for="currency">Currency:</label>
            <g:select name="currency" class="form-control" from="${com.test.atm.Currency.values()}">
            </g:select><br/>
        </span>
        <br/>

        <div class="form-group">
            <label id="valuelabel" for="value">Value:</label>
            <g:field class="form-control" type="number" name="value" id="value" min="0"
                     placeholder="Value of banknote"/>
        </div>
        <g:set var="valuechange" value="false"/>

        <div class="form-group">
            <label id="numberlabel" for="number">Number:</label>
            <g:field class="form-control" type="number" name="number" id="number" min="0"
                     placeholder="Number of banknotes"/></div>
        <g:set var="numberchange" value="false"/>

        <div class="form-group">
            <label id="amountlabel" for="amount">Amount:</label>
            <g:field class="form-control" type="number" name="amount" id="amount" min="0"
                     placeholder="Amount of currency"/></div>
        <g:set var="amountchange" value="false"/>
        <br/>

        <div class="keyboard">
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
            </div></div>

        <div style="margin-top: 15px;"></div>

        <div class="btn-group">
            <button type="button" name="backspace" id="backspace" class="btn btn-default">C</button>
            <a data-toggle="tooltip" data-placement="top" title="Click to submit the action">
                <g:submitToRemote url="[controller: 'atm', action: 'result']" name="go" id="go" class="btn btn-primary"
                                  update="resultId"
                                  value="GO"/>
        </div><br/><br/>

        <br/>

        <h3>Result:</h3>

        <div class="col-md-2 col-md-offset-5">
            <div class="panel panel-default">
                <div class="panel-body">
                    <table class="table table-condensed">
                        <tbody id="resultId">
                        <div style="overflow:auto;" id="atmform">
                        </div>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</g:form>
</body>
</html>