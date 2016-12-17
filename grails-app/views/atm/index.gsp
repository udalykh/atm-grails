<g:form name="atm-window" controller="atm">
    <div align="center">
        <br/>
        <br/>
        <label>Action:</label>
        <g:select name="command" from="${['Remains', 'Deposit', 'Withdrawal']}">
        </g:select>

        <label>Currency:</label>
        <g:textField name="currency"/><br/>
        <br/>

        <label>Value:</label>
        <g:textField type="number" name="value"/>

        <label>Number:</label>
        <g:textField name="number"/>

        <label>Amount:</label>
        <g:textField name="amount"/><br/>
        <br/>

        <g:actionSubmit value="Confirm"/>
        <label>Result:</label>

        <g:textField name="amount"/><br/>
    </div>
</g:form>