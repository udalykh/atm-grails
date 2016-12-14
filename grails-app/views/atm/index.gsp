TODO: add a form here

<g:form controller="atm">

    <label>Action:</label>
    <g:select name="command" from="${['Remain', 'Deposit', 'Withdrawal']}">
    </g:select>

    <label>Currency:</label>
    <g:textField name="currency"/><br/>

    <label>Value:</label>
    <g:textField name="value"/>

    <label>Number:</label>
    <g:textField name="number"/>

    <label>Amount:</label>
    <g:textField name="amount"/><br/>

    <g:actionSubmit value="Confirm"/>
</g:form>