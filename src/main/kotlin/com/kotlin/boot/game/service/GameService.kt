package com.kotlin.boot.game.service

import com.kotlin.boot.event.CountPlus
import com.kotlin.boot.game.controller.dto.GameInfo
import com.kotlin.boot.game.controller.dto.GameReport
import com.kotlin.boot.game.controller.dto.JoinGameDto
import com.kotlin.boot.game.domain.GameEntity
import com.kotlin.boot.game.repository.infra.GameRepository
import com.kotlin.boot.game.repository.infra.GameResultLockRepository
import com.kotlin.boot.game.repository.infra.GameResultRepository
import com.kotlin.boot.global.dto.BaseResponse
import com.kotlin.boot.global.dto.GAME_BALL
import com.kotlin.boot.global.exception.BadRequestException
import com.kotlin.boot.global.exception.ErrorReason
import com.kotlin.boot.global.utils.getAutoNumber
import com.kotlin.boot.global.utils.randomUtils
import org.slf4j.LoggerFactory
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class GameService(
    private val gameRepository: GameRepository,
//    private val gameUserRepository: PlayGameUserRepository,
    private val gameResultRepository: GameResultRepository,
    private val eventPublisher: ApplicationEventPublisher,
    private val gameResultLockRepository: GameResultLockRepository
) {

    private val log = LoggerFactory.getLogger(this::class.java)

    @Transactional(readOnly = true)
    fun gerParticipateGameInfos(round: Long?, phoneNumber: String) = gameRepository.findByPhoneNumberAndPlayRound(
        phoneNumber.replace("-", ""),
        round ?: (gameResultRepository.findByStatus().id!!)
    )


    fun getRoundCount() = gameResultRepository.findByStatus()


    @Transactional
    fun participateInGame(joinGameDto: JoinGameDto): BaseResponse {
        val entryNumberList = joinGameDto.numbers
        val numberList = when {
            entryNumberList.isNullOrEmpty() -> GAME_BALL.getAutoNumber()
            entryNumberList.size < 5 -> {
                checkInputNumbers(entryNumberList.toLongArray())
                getHalfAutoRandom(
                    (4 - entryNumberList.size).toLong(),
                    entryNumberList.toLongArray()
                )
            }
            else -> throw BadRequestException(
                ErrorReason.INVALID_INPUT_DATA,
                "### 번호는 4개만 입력해주세요"
            )
        }

        numberList.sorted().forEach {
            if (it >= 46 || it < 1) {
                log.info("번호 : >>> $numberList")
                throw BadRequestException(
                    ErrorReason.INVALID_INPUT_DATA,
                    "### 번호 -> 최소값 : 1 , 최대값 : 45"
                )
            }
        }

        val submitNumbers = numberList.joinToString()

        val currentRoundInfo = gameResultLockRepository.findByStatus()
        gameRepository.save(
            GameEntity.of(
                joinGameDto.phoneNumber,
                submitNumbers,
                currentRoundInfo.id!!
            )
        )
        eventPublisher.publishEvent(CountPlus(currentRoundInfo))
        return BaseResponse.of(submitNumbers)
    }

    fun checkInputNumbers(numberList: LongArray) {
        for (target in numberList) {
            var count = 0
            numberList.forEach {
                if (target == it) count += 1
            }
            if (count > 1)
                throw BadRequestException(
                    ErrorReason.INVALID_INPUT_DATA,
                    "### 번호 중복 : $target"
                )
        }
    }

    fun getGameRoundInfos(round: Long): List<GameInfo> {
        return gameRepository.findByPlayRound(round).map {
            GameInfo.of(it)
        }
    }

    fun getGameResultInfo(round: Long): GameReport {
        return gameRepository.findByPlayRound(round)
            .groupingBy { gameEntity ->
                gameEntity.drawResult!!
            }
            .eachCount()
            .let {
                gameResultRepository.findByIdAndStatus(round)?.let { gr ->
                    GameReport.of(gr, it)
                } ?: GameReport.ofNone()
            }
    }

    private fun getHalfAutoRandom(count: Long, numberList: LongArray): List<Long> {
        return count.randomUtils(numberList.toMutableList())
    }
}
