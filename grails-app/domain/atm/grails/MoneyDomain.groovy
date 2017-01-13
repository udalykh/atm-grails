package atm.grails

class MoneyDomain {
    Currency currency
    int value
    int number
    static constraints = {
        value min: 0
        number min: 0
    }
}
