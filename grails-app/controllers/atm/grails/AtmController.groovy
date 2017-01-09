package atm.grails

import com.test.atm.*
import grails.converters.JSON

class AtmController {

    def moneyStorage

    def index() {
        Map<BankNote, Integer> response = [:]
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
                    [response: "EMPTY"]
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
    /*
    def result() {

    }

    def ajax() {
        def dummy = [ id: 3]
        render dummy as JSON
    }
    */
}
