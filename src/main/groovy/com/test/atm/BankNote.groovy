package com.test.atm

import groovy.transform.EqualsAndHashCode
import groovy.transform.Sortable

@EqualsAndHashCode
@Sortable(includes = 'currency, value')
class BankNote {
    final Currency currency
    final int value

    BankNote(thisCurrency, thisValue) {
        currency = thisCurrency
        value = thisValue
    }

    @Override
    String toString() {
        return "$currency $value"
    }
}
