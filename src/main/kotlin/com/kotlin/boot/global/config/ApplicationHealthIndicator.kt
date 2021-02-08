package com.kotlin.boot.global.config

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.actuate.health.Health
import org.springframework.boot.actuate.health.HealthIndicator
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
class ApplicationHealthIndicator : HealthIndicator {

    private val log = LoggerFactory.getLogger(this::class.java)

    /*
    * vm option
    *  -Dspring.config.location=classpath:/build-info.properties,classpath:/application.yml,claspath:/logback.xml
    * */

    @Value("\${info.version")
    private val appVersion: String? = null

    @Value("\${info.git.hash}")
    private val gitHash: String? = null

    @Value("\${info.git.buildDate}")
    private val builtDate: String? = null

    @PostConstruct
    fun init() {
        log.info("version: {}", appVersion)
        log.info("hash: {}", gitHash)
        log.info("date: {}", builtDate)
    }

    override fun health(): Health {
        return Health.up()
            .withDetail("version", appVersion)
            .withDetail("hash", gitHash)
            .withDetail("build-date", builtDate)
            .build()
    }
}