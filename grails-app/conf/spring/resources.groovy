// Place your Spring DSL code here
package spring

import com.test.atm.AtmCommandFactory
import com.test.atm.MoneyStorage

beans = {
    commandBean(AtmCommandFactory) {}
    moneyStorageBean(MoneyStorage) {}
}
