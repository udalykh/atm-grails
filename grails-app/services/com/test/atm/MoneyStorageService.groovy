package com.test.atm

import atm.grails.MoneyDomain
import grails.transaction.Transactional
import grails.validation.ValidationException
import groovy.transform.ToString
import org.hibernate.StaleStateException

class MoneyStorageService {

    def listMoney() {
        MoneyDomain.list().collectEntries { note ->
            [(new BankNote(note.currency, note.value)): note.number]
        } as TreeMap
    }

    boolean hasCurrency(Currency currency) {
        MoneyDomain.findByCurrency(currency)
    }

    boolean hasNote(Currency currency, int value) {
        MoneyDomain.findByCurrencyAndValue(currency, value)
    }

    def putMoney(Currency currencyToPut, int valueToPut, int numberToPut) {
        def moneyToCheck = MoneyDomain.findByCurrencyAndValue(currencyToPut, valueToPut)
        try {
            if (moneyToCheck) {
                moneyToCheck.number += numberToPut
                moneyToCheck.save(flush: true)
            } else {
                def moneyToDeposit = new MoneyDomain(currency: currencyToPut, value: valueToPut, number: numberToPut)
                moneyToDeposit.save(flush: true)
            }
        } catch (ValidationException ignore) {
            throw new AtmStateException('CANNOT PUT')
        }
    }

    def pollMoney(Currency currencyToPoll, int valueToPoll, int numberToPoll) {
        def moneyToCheck = MoneyDomain.findByCurrencyAndValue(currencyToPoll, valueToPoll)
        try {
            if (!moneyToCheck) {
                throw new AtmStateException('NO SUCH BANKNOTE')
            }
            if (moneyToCheck.number == numberToPoll) {
                moneyToCheck.delete(flush: true)

            } else {
                moneyToCheck.number -= numberToPoll
                moneyToCheck.save(flush: true)
            }
        } catch (ValidationException ignore) {
            throw new AtmStateException('CANNOT POLL')
        }
    }
}
