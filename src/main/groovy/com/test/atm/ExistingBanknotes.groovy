package com.test.atm

import atm.grails.ValidNotesDomain

class ExistingBanknotes {
    static def exBank = []

    static BankNote[] getBank() {
        def z = ValidNotesDomain.list()
        z.each { it ->
            exBank.add(new BankNote(it.currency, it.value))
        }
        exBank = exBank.sort().reverse()
    }

    static boolean assertBanknote(Currency assertCurrency, int assertValue) {
        ValidNotesDomain.findByCurrencyAndValue(assertCurrency, assertValue)
    }
}
