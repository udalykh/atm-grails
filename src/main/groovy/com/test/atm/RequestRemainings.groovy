package com.test.atm

import atm.grails.MoneyDomain
import org.springframework.beans.factory.annotation.Autowired

class RequestRemainings implements AtmCommand {
    MoneyStorageService thisService

    RequestRemainings(@Autowired MoneyStorageService moneyService) {
        this.thisService = moneyService
    }

    Map<BankNote, Integer> execute(String... arguments) {
        thisService.listMoney()
    }
}
