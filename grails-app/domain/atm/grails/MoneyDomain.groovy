package atm.grails

import com.test.atm.AtmStateException
import com.test.atm.Currency

class MoneyDomain {
    Currency currency
    int value
    int number
    static constraints = {
        currency validator: { currency ->
            if (!currency) {
                return 'validation.invalidCurrency'
            }
        }
        value validator: { int val, MoneyDomain obj ->
            if (!ValidNotesDomain.findByCurrencyAndValue(obj.currency, val)) {
                return 'validation.invalidValue'
            }
        }
        number validator: { number ->
            if (number < 0) {
                return 'validation.invalidNumber'
            }
        }
    }

    static mapping = {
        currency index: 'money_currency_idx'
    }
}
