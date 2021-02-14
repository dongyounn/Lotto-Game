package com.kotlin.boot.game.controller.dto

import javax.validation.constraints.Size

data class JoinGameDto(
    @Size(max = 5)
    val numbers: List<Long>?,
    val phoneNumber: String
)

enum class AutoYnEnum {
    Y, N
}

enum class GameStatusEnum {
    ACTIVE, TERMINATED
}