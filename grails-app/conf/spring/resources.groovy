// Place your Spring DSL code here

import com.test.atm.AtmCommandFactory
import com.test.atm.MoneyStorage

beans = {
    atmCommandFactory(AtmCommandFactory) {}
    moneyStorage(MoneyStorage) {}
}
