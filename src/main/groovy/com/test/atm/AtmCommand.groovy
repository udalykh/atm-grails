package com.test.atm

interface AtmCommand {
    Map<BankNote, Integer> execute(String... arguments)
}
