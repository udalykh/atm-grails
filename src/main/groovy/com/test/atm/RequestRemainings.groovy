package com.test.atm

import atm.grails.MoneyDomain

class RequestRemainings implements AtmCommand {

    Map<BankNote, Integer> execute(String... arguments) {
        def outMap = [:] as TreeMap
        for (someNotes in MoneyDomain.list()) {
            outMap.put(new BankNote(someNotes.currency, someNotes.value), someNotes.number)
        }
        outMap
    }
}
