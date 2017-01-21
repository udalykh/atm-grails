// Place your Spring DSL code here

import com.test.atm.MoneyStorage
import com.test.atm.ExistingBanknotes
import com.test.atm.MoneyStorageService

beans = {
    //moneyStorage(MoneyStorage) {}
    moneyStorageService(MoneyStorageService){}
    existingBanknotes(ExistingBanknotes)
}
