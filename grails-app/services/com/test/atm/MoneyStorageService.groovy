package com.test.atm

import atm.grails.MoneyDomain
import grails.transaction.Transactional
import grails.validation.ValidationException

//@Transactional
class MoneyStorageService {
    def listMoney() {
        MoneyDomain.list()
    }

    def putMoney(Currency currencyToPut, int valueToPut, int numberToPut) {
        def moneyToCheck = MoneyDomain.findByCurrencyAndValue(currencyToPut, valueToPut)
        try {
            if (moneyToCheck) {
                moneyToCheck.number += numberToPut
                moneyToCheck.save()(failOnError: true, flush: true)
            } else {
                def moneyToDeposit = new MoneyDomain(currency: currencyToPut, value: valueToPut, number: numberToPut)
                moneyToDeposit.save()(failOnError: true, flush: true)
            }
        } catch (ValidationException e) {
            throw new AtmStateException('CANNOT PUT')
        }
    }

    def pollMoney(Currency currencyToPoll, int valueToPoll, int numberToPoll) {
        def moneyToCheck = MoneyDomain.findByCurrencyAndValue(currencyToPoll, valueToPoll)
        try {
            if (moneyToCheck.number == numberToPoll) {
                moneyToCheck.delete()(failOnError: true, flush: true)
            } else {
                moneyToCheck.number -= numberToPoll
                moneyToCheck.save()(failOnError: true, flush: true)
            }
        } catch (ValidationException e) {
            throw new AtmStateException('CANNOT POLL')
        }
    }
}
