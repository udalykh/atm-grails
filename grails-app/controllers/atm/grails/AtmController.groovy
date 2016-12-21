package atm.grails

import com.test.atm.*

class AtmController {

    def moneyStorage
    def atmCommandFactory

    Map runCommand(String command, String[] arguments) {
        try {
            AtmCommand atmCommand = atmCommandFactory.create(command)
            atmCommand.execute(arguments)
        } catch (ignore) {
            println('ERROR')
        }
        null
    }

    def index() {
        try {
            String command = ''
            String currency = ''
            String value = ''
            String number = ''
            String amount = ''
            String[] arguments = []

            switch (CommandType.getCommandType(command)) {
                case CommandType.REMAININGS:
                    arguments = []
                    break
                case CommandType.ADD:
                    arguments = [currency, value, number]
                    break
                case CommandType.WITHDRAW:
                    arguments = [currency, amount]
                    break
                default:
                    arguments = []
            }

            Map<BankNote, Integer> response = runCommand(command, arguments)
            [response: response.entrySet()]
        } catch (AtmStateException e) {
            log.error("Error while ATM operation: ${e.message}")
            [error: true]
        }
    }
}
