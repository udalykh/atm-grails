package com.test.atm

import org.springframework.beans.factory.annotation.Autowired

class AtmCommandFactory {

    private final MoneyStorage moneyStorage

    AtmCommandFactory(@Autowired MoneyStorage moneyStorage) {
        this.moneyStorage = moneyStorage
    }

    AtmCommand create(String action) {
        switch (CommandType.getCommandType(action)) {
            case CommandType.REMAININGS:
                return new RequestRemainings(moneyStorage)
            case CommandType.ADD:
                return new DepositCommand(moneyStorage)
            case CommandType.WITHDRAW:
                return new WithdrawalCommand(moneyStorage)
            default:
                throw new AtmStateException("Cannot find a command for this action $action")
        }
    }
}
