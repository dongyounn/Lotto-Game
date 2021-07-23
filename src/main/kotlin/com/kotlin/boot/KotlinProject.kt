package com.kotlin.boot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration
import org.springframework.boot.runApplication

/*
* TODO ::
*  Docker 써서 MySql 로 전환
*  flyway 적용
*  코루틴 사용
*  유저 시나리오 변경
* */
@SpringBootApplication(exclude = [RedisAutoConfiguration::class, RabbitAutoConfiguration::class])
class KotlinProject

fun main(args: Array<String>) {
    runApplication<KotlinProject>(*args)
}