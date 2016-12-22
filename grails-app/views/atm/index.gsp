<g:form name="atm-window" controller="atm" action="index" method="post" id="atm">
    <div align="center">
        <br/>
        <br/>
        <label>Action:</label>
        <g:select name="command" from="${com.test.atm.CommandType.values()}">
        </g:select>

        <label>Currency:</label>
        <g:select name="currency" from="${com.test.atm.Currency.values()}">
        </g:select>
        <br/>
        <br/>

        <label>Value:</label>
        <g:field type="number" name="value" min="0"/>

        <label>Number:</label>
        <g:field type="number" name="number" min="0"/>

        <label>Amount:</label>
        <g:field type="number" name="amount" min="0"/>
        <br/>
        <br/>

        <g:actionSubmit value="Confirm" action="index" class="btn btn-default"/>
        <hr>
        <label>Result:</label><br>
        <g:if test="${error}">
            ERROR
        </g:if>
        <g:else>
            <g:each in="${response}">
                ${it.key} ${it.value}<br>
            </g:each>
        </g:else>
        <br/>
    </div>
</g:form>