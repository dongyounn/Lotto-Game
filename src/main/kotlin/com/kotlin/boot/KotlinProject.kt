package com.kotlin.boot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration
import org.springframework.boot.runApplication

/*
* TODO ::
*  MySql 로 전환
*  flyway 적용
*  화면 만들어보기 타임리프 vs 머스테치
* */
@SpringBootApplication(exclude = [RedisAutoConfiguration::class, RabbitAutoConfiguration::class])
class KotlinProject

fun main(args: Array<String>) {
    runApplication<KotlinProject>(*args)
}