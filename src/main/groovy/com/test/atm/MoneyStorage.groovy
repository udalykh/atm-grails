package com.test.atm

class MoneyStorage {

    def notes = [:] as TreeMap
    def currencyAmount = [:]

    boolean hasNote(Currency hasCurrency, int hasValue) {
        return notes.containsKey(new BankNote(hasCurrency, hasValue))
    }

    boolean hasCurrency(Currency hasCurrency2) {
        return currencyAmount.containsKey(hasCurrency2)
    }

    void addNotes(Currency addCurrency, int addValue, int addNumber) {
        ExistingBanknotes.assertBanknote(addCurrency, addValue)
        BankNote keyToAdd = new BankNote(addCurrency, addValue)
        notes.compute(keyToAdd, { bankNote, oldNumber -> oldNumber == null ? addNumber : oldNumber + addNumber })
        currencyAmount.compute(addCurrency, { banknoteKey, integerNumber -> integerNumber == null ? addValue * addNumber : integerNumber + addNumber * addValue })
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


