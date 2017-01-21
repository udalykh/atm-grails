package com.test.atm

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BeanConfig {

    @Bean
    moneyStorageService() {
        MoneyStorageService moneyStorageService
    }
}
