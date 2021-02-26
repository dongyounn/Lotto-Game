package com.kotlin.boot.global.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun LocalDateTime.convertYYYYmmDD(): String = this.format(DateTimeFormatter.ofPattern("yyyyMMdd"))


fun LocalDateTime.convertYYYYmmDDhhmmiss(): String = this.format(DateTimeFormatter.ofPattern("yyyyMMddHHMIss"))