package com.test.atm

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BeanConfig {

    @Bean
    commandBean() {
        AtmCommandFactory commandBean
    }

    @Bean
    moneyStorageBean() {
        MoneyStorage moneyStorageBean
    }
}
