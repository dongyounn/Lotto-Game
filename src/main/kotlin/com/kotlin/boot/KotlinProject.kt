package com.kotlin.boot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = [RedisAutoConfiguration::class, RabbitAutoConfiguration::class])
class KotlinProject

fun main(args: Array<String>) {
    runApplication<KotlinProject>(*args)
}