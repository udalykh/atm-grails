package com.test.atm

class RequestRemainings implements AtmCommand {
    private final MoneyStorage moneyStorage

    RequestRemainings(MoneyStorage moneyStorage) {
        this.moneyStorage = moneyStorage
    }

    Map<BankNote, Integer> execute(String... arguments) {
        return moneyStorage.getNotes()
    }
}
