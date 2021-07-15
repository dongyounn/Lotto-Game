package com.kotlin.boot.batch.service

import com.kotlin.boot.game.domain.GameResultEntity
import com.kotlin.boot.game.repository.infra.GameDataCustomRepository
import com.kotlin.boot.game.repository.infra.GameRepository
import com.kotlin.boot.game.repository.infra.GameResultRepository
import com.kotlin.boot.global.dto.GAME_BALL
import com.kotlin.boot.global.exception.BadRequestException
import com.kotlin.boot.global.exception.ErrorReason
import com.kotlin.boot.global.utils.getAutoNumber
import org.slf4j.LoggerFactory
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class BatchService(
    private val gameResultRepository: GameResultRepository,
    private val gameRepository: GameRepository,
    private val gameDataCustomRepository: GameDataCustomRepository
) {
    private val batchSize = 100
    private val log = LoggerFactory.getLogger(this::class.java)

    @Transactional(noRollbackFor = [Exception::class])
    fun playLotto(): String {
        val randomNumber = GAME_BALL.getAutoNumber().sorted()
        val normalNumber = StringBuilder()

        val regularNumber = randomNumber
            .forEach { normalNumber.append("$it,") }
            .let { number -> number.toString().removeSuffix(",") }


        val currentRoundInfo = gameResultRepository.findByStatus()
        var pagingSize: Long
        var currentPage = 0
        gameRepository.countByPlayRound(currentRoundInfo.id!!).also {
            if (it == 0L) throw BadRequestException(ErrorReason.INVALID_INPUT_DATA, "게임 참가자가 없습니다. ")
            log.info("## 전체 게임 횟수 : $it")
            log.info("## 배치 사이즈 : $batchSize")
        }.let {
            pagingSize = it.div(batchSize).plus(1)
        }

        while (pagingSize > currentPage) {
            gameDataCustomRepository.getGameDataPagingData(
                currentRoundInfo.id!!,
                PageRequest.of(currentPage, batchSize)
            ).let { gameInfos ->
                gameInfos.forEach {
                    var matchingCount = 0
                    val matchingNumbers = ArrayList<String>()
                    it!!.gameNumber.split(",").forEach { number ->
                        if (randomNumber.contains(number.toLong())) {
                            matchingNumbers.add(number)
                            matchingCount++
                        }
                    }
                    val sb = StringBuilder()
                    matchingNumbers.sorted().forEach { resultNumber -> sb.append("$resultNumber,") }
                    it.setDrawResult(
                        when (matchingCount) {
                            4 -> 1
                            3 -> 2
                            2 -> 3
                            else -> 0
                        },
                        sb.toString().removeSuffix(",")
                    )
                }
            }
            currentPage++
        }

        currentRoundInfo.ofEnd(
            regularNumber
        )
        /*라운드 초기화 */
        gameResultRepository.save(GameResultEntity.ofAutoStart())

        return randomNumber.toString()
    }
}