package com.kotlin.boot.global.sequence

enum class SequenceEnum (
        val sequenceName: String,
        val prefix: String,
        val sequenceLength: Int
) {
    USER_ID_GEN("SPRINGBOOT.USER_SEQ", "GU", 6),
}