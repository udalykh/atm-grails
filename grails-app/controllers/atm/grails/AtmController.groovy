package atm.grails

import com.test.atm.*
import grails.converters.JSON

class AtmController {
    def moneyStorageService

    def index() {
        def allMoney = moneyStorageService.listMoney()
        [allMoney: allMoney]
    }

    def result() {
        Map<BankNote, Integer> response = [:]
        try {
            String command = params["command"]
            String currency = params["currency"]
            String value = params["value"]
            String number = params["number"]
            String amount = params["amount"]
            def moneyFind = MoneyDomain.findAllByCurrency(Currency.getCurrency(currency))
            def moneyMap = [:] as TreeMap
            for (z in moneyFind) {
                moneyMap.put(new BankNote(z.currency, z.value), z.number)
            }
            switch (CommandType.getCommandType(command)) {
                case CommandType.REMAININGS:
                    response = new RequestRemainings(new MoneyStorage(moneyMap)).execute()
                    moneyStorageService.listMoney()
                    break
                case CommandType.ADD:
                    response = new DepositCommand(new MoneyStorage(moneyMap)).execute(currency, value, number)
                    moneyStorageService.putMoney(Currency.getCurrency(currency), value as Integer, number as Integer)
                    break
                case CommandType.WITHDRAW:
                    response = new WithdrawalCommand(new MoneyStorage(moneyMap)).execute(currency, amount)
                    for (m in response) {
                        BankNote moneyNote = m.key
                        int moneyNum = m.value
                        moneyStorageService.pollMoney(moneyNote.currency, moneyNote.value, moneyNum)
                    }
                    break
                default:
                    response = new RequestRemainings(new MoneyStorage(moneyMap)).execute()
                    new MoneyStorageService().listMoney()
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
