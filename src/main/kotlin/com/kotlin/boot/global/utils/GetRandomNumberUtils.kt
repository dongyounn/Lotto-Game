package com.kotlin.boot.global.utils

import org.springframework.stereotype.Service
import java.util.*

@Service
class NumberUtils {
    fun getAutoNumber(count: Long): List<Long> {
        val random = Random()
        val numberList = ArrayList<Long>()
        for (i in 0 until count) {
            var number: Long
            do {
                number = random.nextInt(45).toLong()
            } while (numberList.contains(number) || number == 0L)
            numberList.add(number)
        }
        return numberList
    }
}
