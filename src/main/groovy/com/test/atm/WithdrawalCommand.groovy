package com.test.atm

import org.springframework.beans.factory.annotation.Autowired

class WithdrawalCommand implements AtmCommand {
    MoneyStorageService thisService

    WithdrawalCommand(@Autowired MoneyStorageService moneyService) {
        this.thisService = moneyService
    }

    Map<BankNote, Integer> execute(String... arguments) {
        Map<BankNote, Integer> outMap = [:] as TreeMap
        def numbersMap = [:]
        def exBankForWithdrawal = ExistingBanknotes.getExBank()
        MoneyStorage moneyStorage = new MoneyStorage()

        AtmUtils.assertLengthCheck(2, arguments)
        Currency currencyToPoll = Currency.getCurrency(arguments[0])
        int amountToGet = AtmUtils.parseInt(arguments[1], 'ILLEGAL TYPING OF AMOUNT')

        if (!thisService.hasCurrency(currencyToPoll)) {
            throw new AtmStateException('NO SUCH CURRENCY')
        }

        int currencyAmount = moneyStorage.getCurrencyAmount(currencyToPoll)
        if (currencyAmount >= amountToGet) {
            int checkAmount = amountToGet
            for (BankNote banknoteToCheck : exBankForWithdrawal) {
                if (banknoteToCheck.getCurrency() == currencyToPoll && checkAmount >= banknoteToCheck.getValue()) {
                    int valueToCheck = banknoteToCheck.getValue()
                    if (thisService.hasNote(currencyToPoll, valueToCheck)) {
                        int remainingNumber = moneyStorage.getNoteNumber(new BankNote(currencyToPoll, valueToCheck))
                        int divisionCheck = checkAmount / valueToCheck
                        if (remainingNumber > divisionCheck) {
                            checkAmount = checkAmount - valueToCheck * divisionCheck
                            numbersMap.put(banknoteToCheck, divisionCheck)
                        } else {
                            checkAmount = checkAmount - valueToCheck * remainingNumber
                            numbersMap.put(banknoteToCheck, remainingNumber)
                        }
                    }
                }
            }

            if (!checkAmount) {
                for (BankNote banknoteToGet : exBankForWithdrawal) {
                    int valueToPoll = banknoteToGet.getValue()
                    if (numbersMap.containsKey(banknoteToGet) && numbersMap.get(banknoteToGet) != 0) {
                        int numberToPoll = (Integer) numbersMap.get(banknoteToGet)
                        if (thisService.hasNote(currencyToPoll, valueToPoll)) {
                            moneyStorage.pollNotes(currencyToPoll, valueToPoll, numberToPoll)
                            thisService.pollMoney(currencyToPoll, valueToPoll, numberToPoll)
                            outMap.put(new BankNote(currencyToPoll, valueToPoll), numberToPoll)
                        }
                    }
                }
                return outMap
            }
        }
        throw new AtmStateException('NOT POSSIBLE WITH THE AVAILABLE BANKNOTES')
    }
}
