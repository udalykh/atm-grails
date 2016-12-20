package com.test.atm

enum Currency {
    CHF,
    EUR,
    JPY,
    RUR,
    USD

    static Currency getCurrency(String currencyForChecking) {
        for (Currency checking : values()) {
            if (checking.toString() == currencyForChecking) {
                return checking
            }
        }
        throw new AtmStateException('ILLEGAL CURRENCY TYPE')
    }
}