package com.test.atm

import atm.grails.ValidNotesDomain

class ExistingBanknotes {
    static def exBank = []

    static {
        [
                USD: [100, 50, 20, 10, 5, 2, 1],
                RUR: [5000, 1000, 500, 100, 50, 10],
                CHF: [1000, 200, 100, 50, 20, 10],
                JPY: [10000, 5000, 1000],
                EUR: [500, 200, 100, 50, 20, 10, 5]
        ].each {
            key, value ->
                value.each {
                    exBank << new BankNote(key, it)
                }
        }
        exBank = exBank.sort().reverse()
    }

    static boolean assertBanknote(Currency assertCurrency, int assertValue) {
        ValidNotesDomain.findByCurrencyAndValue(assertCurrency, assertValue)
    }
}
