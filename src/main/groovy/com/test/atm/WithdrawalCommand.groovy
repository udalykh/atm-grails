package com.test.atm

class WithdrawalCommand implements AtmCommand {
    private final MoneyStorage moneyStorage

    WithdrawalCommand(MoneyStorage moneyStorage) {
        this.moneyStorage = moneyStorage
    }

    Map<BankNote, Integer> execute(String... arguments) {
        def outMap = [:] as TreeMap
        def numbersMap = [:]
        def exBankForWithdrawal = ExistingBanknotes.getExBank()

        AtmUtils.assertLengthCheck(2, arguments)
        Currency currencyToPoll = Currency.getCurrency(arguments[0])
        int amountToGet = AtmUtils.parseInt(arguments[1], 'ILLEGAL TYPING OF AMOUNT')

        //Checking whether the money storage contains this currency
        if (!moneyStorage.hasCurrency(currencyToPoll)) {
            throw new AtmStateException('NO SUCH CURRENCY')
        }

        //Checking whether it is possible to take a certain amount
        int currencyAmount = moneyStorage.getCurrencyAmount(currencyToPoll)
        if (currencyAmount >= amountToGet) {
            int checkAmount = amountToGet
            for (BankNote banknoteToCheck : exBankForWithdrawal) {
                if (banknoteToCheck.getCurrency() == currencyToPoll && checkAmount >= banknoteToCheck.getValue()) {
                    int valueToCheck = banknoteToCheck.getValue()
                    if (moneyStorage.hasNote(currencyToPoll, valueToCheck)) {
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

            //Withdrawal operation
            if (!checkAmount) {
                for (BankNote banknoteToGet : exBankForWithdrawal) {
                    int valueToPoll = banknoteToGet.getValue()
                    if (numbersMap.containsKey(banknoteToGet) && numbersMap.get(banknoteToGet) != 0) {
                        int numberToPoll = (Integer) numbersMap.get(banknoteToGet)
                        if (moneyStorage.hasNote(currencyToPoll, valueToPoll)) {
                            moneyStorage.pollNotes(currencyToPoll, valueToPoll, numberToPoll)
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
