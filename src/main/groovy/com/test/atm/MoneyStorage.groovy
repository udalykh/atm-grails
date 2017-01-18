package com.test.atm

import atm.grails.MoneyDomain

class MoneyStorage {
    def notes = [:] as TreeMap
    def currencyAmount = [:]

    MoneyStorage() {
        def allMoney = MoneyDomain.list()
        for (someNotes in allMoney) {
            BankNote keyToAdd = new BankNote(someNotes.currency, someNotes.value)
            notes.compute(keyToAdd, { bankNote, oldNumber -> oldNumber == null ? someNotes.number : oldNumber + someNotes.number })
            currencyAmount.compute(someNotes.currency, { banknoteKey, integerNumber -> integerNumber == null ? someNotes.value * someNotes.number : integerNumber + someNotes.value * someNotes.number })
        }
    }

    boolean hasNote(Currency hasCurrency, int hasValue) {
        return notes.containsKey(new BankNote(hasCurrency, hasValue))
    }

    boolean hasCurrency(Currency hasCurrency2) {
        return currencyAmount.containsKey(hasCurrency2)
    }

    void pollNotes(Currency pollCurrency, int pollValue, int pollNumber) {
        BankNote keyToPoll = new BankNote(pollCurrency, pollValue)
        notes.compute(keyToPoll, { bankNote, oldNumber -> oldNumber == null ? 0 : oldNumber - pollNumber })
        if (notes.get(keyToPoll) == 0) {
            notes.remove(keyToPoll)
        }
        currencyAmount.compute(pollCurrency, { currencyKey, oldAmount -> oldAmount == null ? 0 : oldAmount - pollNumber })
    }

    int getNoteNumber(BankNote banknoteKey) {
        def numberToGet = notes.get(banknoteKey)
        if (!numberToGet) {
            throw new AtmStateException('NULL BANKNOTE NUMBER')
        }
        return (Integer) numberToGet
    }

    int getCurrencyAmount(Currency currencyKey) {
        def amountToGet = currencyAmount.get(currencyKey)
        if (!amountToGet) {
            throw new AtmStateException('NULL CURRENCY AMOUNT')
        }
        return (Integer) amountToGet
    }
}


