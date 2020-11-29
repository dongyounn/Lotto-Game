package com.kotlin.boot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinProject

fun main(args: Array<String>) {
    runApplication<KotlinProject>(*args)
}