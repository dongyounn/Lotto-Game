package com.kotlin.boot.global.utils

fun String.masking() = StringBuilder("*").let { sb ->
    this.let {
        it.removeRange(it.length.div(2), it.length) + repeat((0..it.length.div(2)).count()) {
            sb.append("*")
        }.let {
            sb.toString()
        }
    }
}