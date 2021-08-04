package com.kotlin.boot.global.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "spring.datasource.hikari")
data class HikariProperties(
    var username: String = "",
    var password: String = "",
    var jdbcUrl: String = "",
    var driverClassName: String = "",
    var validationTimeout: Long = 5000,
    var maximumPoolSize: Int = 50,
    var leakDetectionThreshold: Long = 0
)