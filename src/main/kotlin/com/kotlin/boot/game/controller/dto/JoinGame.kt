package com.kotlin.boot.game.controller.dto

import com.kotlin.boot.game.domain.GameEntity
import javax.validation.constraints.Size

data class JoinGameDto(
    @Size(max = 4)
    val numbers: List<Long>?,
    val phoneNumber: String
)

enum class AutoYnEnum {
    Y, N
}

enum class GameStatusEnum {
    ACTIVE, TERMINATED
}

data class GameInfo(
    val userId: String,
    val round: Long,
    val gameNumber: String,
    val resultNumber: String?,
    val matchingCount: Long? = 0L
) {
    companion object {
        fun of(req: GameEntity) = GameInfo(req.userId, req.playRound, req.gameNumber, req.matchNumber, req.drawResult)
    }
}