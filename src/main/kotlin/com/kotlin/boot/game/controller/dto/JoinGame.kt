package com.kotlin.boot.game.controller.dto

import com.kotlin.boot.game.domain.GameEntity
import com.kotlin.boot.game.domain.GameResultEntity
import com.kotlin.boot.global.dto.NONE
import com.kotlin.boot.global.dto.UNKNOWN
import java.util.*
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
    val round: Long,
    val gameNumber: String,
    val resultNumber: String?,
    val matchingCount: Long? = 0L
) {
    companion object {
        fun of(req: GameEntity) = GameInfo(req.playRound, req.gameNumber, req.matchNumber, req.drawResult)
    }
}

data class GameReport(
    val drawNumber: String,
    val playerCount: Long,
    val round: Long,
    val drawResult: Map<Long, Int>?
) {
    companion object {
        fun of(gameResult: GameResultEntity, drawResult: Map<Long, Int>?) = GameReport(
            gameResult.normalNumber ?: NONE,
            gameResult.playerNo,
            gameResult.id!!,
            drawResult
        )

        fun ofNone() = GameReport(UNKNOWN, 0L, 0L, Collections.emptyMap())
    }
}