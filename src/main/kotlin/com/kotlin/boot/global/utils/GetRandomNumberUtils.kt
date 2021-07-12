package com.kotlin.boot.global.utils

import java.util.*


fun Long.getAutoNumber(): List<Long> = this.randomUtils(ArrayList<Long>())

fun Long.randomUtils(list: MutableList<Long>?): MutableList<Long> {
    val resultList = list ?: ArrayList<Long>()
    val random = Random()
    for (i in 0 until this) {
        var number: Long
        do {
            number = random.nextInt(45).toLong()
        } while (resultList.contains(number) || number == 0L)
        resultList.add(number)
    }
    return resultList
}