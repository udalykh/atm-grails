package com.test.atm

class AtmUtils {
    static int parseInt(String stringToParse, String exceptionMessage) {
        try {
            stringToParse as Integer
        } catch (NumberFormatException e) {
            throw new AtmStateException(exceptionMessage)
        }
    }

    static void assertLengthCheck(int lengthCheck, String... stringArray) {
        if (stringArray.length != lengthCheck) {
            throw new AtmStateException('WRONG NUMBER OF PARAMETERS')
        }
    }
}
