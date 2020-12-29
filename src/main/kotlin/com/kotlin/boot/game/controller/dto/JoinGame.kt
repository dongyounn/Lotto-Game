package com.kotlin.boot.game.controller.dto

import javax.validation.constraints.Size

data class JoinGameDto(
        val numbers: List<Long>?,
        val phoneNumber: String,
        val playerName : String
)

enum class AutoYnEnum{
        Y,N
}