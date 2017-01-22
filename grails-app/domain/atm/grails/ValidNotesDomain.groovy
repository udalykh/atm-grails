package atm.grails

import com.test.atm.Currency

class ValidNotesDomain {
    Currency currency
    int value

    static constraints = {
        currency nullable: false
        value min: 0
    }
}
