package com.test.atm

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BeanConfig {

    @Bean
    atmCommandFactory() {
        AtmCommandFactory atmCommandFactory
    }

    @Bean
    moneyStorageService() {
        MoneyStorageService moneyStorageService
    }
}
