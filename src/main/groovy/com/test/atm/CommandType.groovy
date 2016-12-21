package com.test.atm

enum CommandType {
    REMAININGS('REMAININGS'),
    ADD('ADD'),
    WITHDRAW('WITHDRAW')

    private final String command

    CommandType(command) {
        this.command = command
    }

    static CommandType getCommandType(String command) {
        CommandType commandToGet = values().find { it.command == command }
        if (commandToGet) {
            return commandToGet
        } else {
            throw new AtmStateException('INVALID COMMAND TYPE')
        }
    }
}
