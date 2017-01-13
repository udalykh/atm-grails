package atm.grails

import com.test.atm.*
import grails.converters.JSON

class AtmController {

    def moneyStorage

    def index() {
    }

    def result() {
        def response = [:]
        try {
            String command = params["command"]
            String currency = params["currency"]
            String value = params["value"]
            String number = params["number"]
            String amount = params["amount"]
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
                default:
                    response = new RequestRemainings(moneyStorage).execute()
                    break
            }
            if (!response) {
                if (command == "REMAININGS") {
                    [response: [:]]
                } else {
                    throw new AtmStateException('NULL CAPTURED')
                }
            }
            [response: response.entrySet()]
        } catch (AtmStateException e) {
            log.error("Error while ATM operation: ${e.message}")
            [error: true]
        }
    }
}
