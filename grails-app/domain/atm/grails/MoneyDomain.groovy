package atm.grails

import com.test.atm.AtmStateException
import com.test.atm.Currency

class MoneyDomain {
    Currency currency
    int value
    int number
    static constraints = {
        currency validator: { currency -> if (!currency) throw new AtmStateException("INVALID CURRENCY") }
        value validator: { int val, MoneyDomain obj ->
            if (!ValidNotesDomain.findByCurrencyAndValue(obj.currency, val))
                throw new AtmStateException("INVALID VALUE")
        }
        number validator: { number -> if (number < 0) throw new AtmStateException("INVALID NUMBER") }
    }

    static mapping = {
        currency index: 'money_currency_idx'
    }
}
