package atm.grails

import com.test.atm.*

class AtmController {

    def moneyStorage

    def index() {
        String command = ''
        String currency = ''
        String value = ''
        String number = ''
        String amount = ''
        Map<BankNote, Integer> response = [:]
        try {
            switch (CommandType.getCommandType(command)) {
                case CommandType.REMAININGS:
                    response = new RequestRemainings(moneyStorage).execute()
                    break
                case CommandType.ADD:
                    response = new DepositCommand(moneyStorage).execute(currency, value, number)
                    break
                case CommandType.WITHDRAW:
                    response = new WithdrawalCommand(moneyStorage).execute(currency, amount)
                    break
            }
            if (!response) {
                throw new AtmStateException('NULL CAPTURED')
            }
            [response: response.entrySet()]
        } catch (AtmStateException e) {
            log.error("Error while ATM operation: ${e.message}")
            [error: true]
        }
    }
}
