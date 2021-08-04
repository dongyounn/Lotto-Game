package com.kotlin.boot.global.config

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.persistence.EntityManagerFactory

@Configuration
@EnableTransactionManagement
class DataSourceConfig(
) {
    @Bean
    @Primary
    internal fun getDataSource(hikariProperties: HikariProperties): HikariDataSource {
        val hikariConfig = HikariConfig()
        hikariConfig.username = hikariProperties.username
        hikariConfig.password = hikariProperties.password
        hikariConfig.jdbcUrl = hikariProperties.jdbcUrl
        hikariConfig.driverClassName = hikariProperties.driverClassName
        hikariConfig.validationTimeout = hikariProperties.validationTimeout
        hikariConfig.maximumPoolSize = hikariProperties.maximumPoolSize
        hikariConfig.leakDetectionThreshold = hikariProperties.leakDetectionThreshold
        return HikariDataSource(hikariConfig)
    }

    @Bean("transactionManager")
    internal fun transactionManager(entityManagerFactory: EntityManagerFactory): PlatformTransactionManager {
        val transactionManager = JpaTransactionManager(entityManagerFactory)
        transactionManager.entityManagerFactory = entityManagerFactory
        return transactionManager
    }
}