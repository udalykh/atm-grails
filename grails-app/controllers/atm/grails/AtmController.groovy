package atm.grails

import com.test.atm.*
import grails.converters.JSON

class AtmController {
    //def moneyStorage
    def moneyStorageService

    def index() {
        /*
        def allMoney = MoneyDomain.list()
        [allMoney: allMoney]
        */
    }

    def result() {
        Map<BankNote, Integer> response = [:]
        try {
            String command = params["command"]
            String currency = params["currency"]
            String value = params["value"]
            String number = params["number"]
            String amount = params["amount"]
            switch (CommandType.getCommandType(command)) {
                case CommandType.REMAININGS:
                    response = new RequestRemainings().execute()
                    break
                case CommandType.ADD:
                    response = new DepositCommand(moneyStorageService).execute(currency, value, number)
                    break
                case CommandType.WITHDRAW:
                    response = new WithdrawalCommand(moneyStorageService).execute(currency, amount)
                    break
                default:
                    response = new RequestRemainings().execute()
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
