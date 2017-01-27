package com.test.atm

import atm.grails.ValidNotesDomain

class ExistingBanknotes {

    static BankNote[] getBank() {
        def exBank = []
        def z = ValidNotesDomain.list()
        z.each { it ->
            exBank.add(new BankNote(it.currency, it.value))
        }
        exBank = exBank.sort().reverse()
    }

    static boolean assertBankNotes(Currency currency, int value) {
        ValidNotesDomain.findByCurrencyAndValue(currency, value)
    }
}
