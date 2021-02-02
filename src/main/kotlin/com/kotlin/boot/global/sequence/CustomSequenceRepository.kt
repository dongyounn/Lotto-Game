package com.kotlin.boot.global.sequence

import org.springframework.stereotype.Component

@Component
class CustomSequenceRepository(
    private val seqRep: SequenceRepository
) {
    fun nextUserId(): String {
        return "${SequenceEnum.USER_ID_GEN.prefix}${String.format("%06d", seqRep.getNextLong(SequenceEnum.USER_ID_GEN))}"
    }
}