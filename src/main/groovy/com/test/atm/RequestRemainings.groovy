package com.test.atm

import org.springframework.beans.factory.annotation.Autowired

class RequestRemainings{
    MoneyStorageService thisService

    RequestRemainings(@Autowired MoneyStorageService moneyService) {
        this.thisService = moneyService
    }

    Map<BankNote, Integer> execute(String... arguments) {
        thisService.listMoney()
    }
}
