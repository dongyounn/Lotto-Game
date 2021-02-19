package com.kotlin.boot.batch.service

import com.kotlin.boot.game.domain.GameResultEntity
import com.kotlin.boot.game.repository.infra.GameDataCustomRepository
import com.kotlin.boot.game.repository.infra.GameRepository
import com.kotlin.boot.game.repository.infra.GameResultRepository
import com.kotlin.boot.global.utils.CustomPageRequest
import com.kotlin.boot.global.utils.NumberUtils
import org.slf4j.LoggerFactory
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class BatchService(
    private val gameResultRepository: GameResultRepository,
    private val gameRepository: GameRepository,
    private val gameDataCustomRepository: GameDataCustomRepository,
    private val numberUtils: NumberUtils
) {
    private val batchSize = 100
    private val log = LoggerFactory.getLogger(this::class.java)

    @Transactional(noRollbackFor = [Exception::class])
    fun playLotto() {
        val randomNumber = numberUtils.getAutoNumber(5).sorted()
        val normalNumber = StringBuilder()

        randomNumber.forEach {
            normalNumber.append("$it,")
        }

        val regularNumber = normalNumber.toString().removeSuffix(",")

        val currentRoundInfo = gameResultRepository.findByStatus()
        val round = currentRoundInfo.id!!
        val gameCount = gameRepository.countByPlayRound(round)
        log.info("## 전체 게임 횟수 : $gameCount")
        log.info("## 배치 사이즈 : $batchSize")
        val pagingSize = gameCount.div(batchSize).plus(1)
        var currentPage = 0

        while (pagingSize > currentPage) {
            gameDataCustomRepository.getGameDataPagingData(
                currentRoundInfo.id!!,
                PageRequest.of(currentPage, batchSize)
            ).let { gameInfos ->
                gameInfos.forEach {
                    var matchingCount = 0
                    val matchingNumbers = ArrayList<String>()
                    it?.gameNumber?.split(",")?.forEach { number ->
                        if (randomNumber.contains(number.toLong())) {
                            matchingNumbers.add(number)
                            matchingCount++
                        }
                    }
                    val sb = StringBuilder()
                    matchingNumbers.sorted().forEach { resultNumber -> sb.append("$resultNumber,") }
                    it?.setDrawResult(
                        when (matchingCount) {
                            4 -> 1
                            3 -> 2
                            2 -> 3
                            else -> 0
                        },
                        matchingNumbers.toString().removeSuffix(",")
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
    }
}