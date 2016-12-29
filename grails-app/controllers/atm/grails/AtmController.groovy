package atm.grails

import com.test.atm.*

class AtmController {

    def moneyStorage

    def index() {
        String command = params["command"]
        String currency = params["currency"]
        String value = params["value"]
        String number = params["number"]
        String amount = params["amount"]
        boolean boolA
        boolean boolB
        boolean boolC
        Map<BankNote, Integer> response = [:]
        try {
            switch (CommandType.getCommandType(command)) {
                case CommandType.REMAININGS:
                    response = new RequestRemainings(moneyStorage).execute()
                    boolA = true
                    boolB = true
                    boolC = true
                    break
                case CommandType.ADD:
                    response = new DepositCommand(moneyStorage).execute(currency, value, number)
                    boolA = false
                    boolB = false
                    boolC = true
                    break
                case CommandType.WITHDRAW:
                    response = new WithdrawalCommand(moneyStorage).execute(currency, amount)
                    boolA = true
                    boolB = true
                    boolC = false
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
