package atm.grails

import com.test.atm.Currency
import com.test.atm.ExistingBanknotes

class MoneyDomain {
    Currency currency
    int value
    int number
    static constraints = {
        currency nullable: false
        value min: 0, validator: { int val, MoneyDomain obj ->
            ExistingBanknotes.assertBanknote(obj.currency, val)
        }
        number min: 0
    }

    static mapping = {
        currency index: 'money_currency_idx'
    }
}
