package com.test.atm

import org.springframework.beans.factory.annotation.Autowired

class DepositCommand implements AtmCommand {
    MoneyStorageService thisService

    DepositCommand(@Autowired MoneyStorageService moneyService) {
        this.thisService = moneyService
    }

    Map<BankNote, Integer> execute(String... arguments) {
        AtmUtils.assertLengthCheck(3, arguments)
        Currency currencyToPut = Currency.getCurrency(arguments[0])
        int valueToPut = AtmUtils.parseInt(arguments[1], 'ILLEGAL TYPING OF VALUE')
        int numberToPut = AtmUtils.parseInt(arguments[2], 'ILLEGAL TYPING OF NUMBER')
        thisService.putMoney(currencyToPut, valueToPut, numberToPut)
        [(new BankNote(currencyToPut, valueToPut)): numberToPut]
    }
}
