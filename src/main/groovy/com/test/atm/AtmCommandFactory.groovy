package com.test.atm

import org.springframework.beans.factory.annotation.Autowired

class AtmCommandFactory {

    private final MoneyStorage moneyStorage

    def moneyService = new MoneyStorageService()

    AtmCommandFactory() {
        this.moneyStorage = null
    }

    AtmCommand create(String action) {
        switch (CommandType.getCommandType(action)) {
            case CommandType.REMAININGS:
                return new RequestRemainings()
            case CommandType.ADD:
                return new DepositCommand(moneyService)
            case CommandType.WITHDRAW:
                return new WithdrawalCommand(moneyService)
            default:
                throw new AtmStateException("Cannot find a command for this action $action")
        }
    }
}
