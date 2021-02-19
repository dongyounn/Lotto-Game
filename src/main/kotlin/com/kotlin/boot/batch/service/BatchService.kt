package com.kotlin.boot.batch.service

import com.kotlin.boot.game.domain.GameResultEntity
import com.kotlin.boot.game.repository.infra.GameRepository
import com.kotlin.boot.game.repository.infra.GameResultRepository
import com.kotlin.boot.global.utils.NumberUtils
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class BatchService(
    private val gameResultRepository: GameResultRepository,
    private val gameRepository: GameRepository,
    private val numberUtils: NumberUtils
) {

    @Transactional(noRollbackFor = [Exception::class])
    fun playLotto() {
        val randomNumber = numberUtils.getAutoNumber(5).sorted()
        val normalNumber = StringBuilder()

        randomNumber.forEach {
            normalNumber.append("$it,")
        }

        val regularNumber = normalNumber.toString().removeSuffix(",")

        val currentRoundInfo = gameResultRepository.findByStatus()

        val currentRoundPlayerInfos = gameRepository.findByPlayRound(currentRoundInfo.id!!)
        currentRoundPlayerInfos.let { gameInfos ->
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
                matchingNumbers.forEach { resultNumber -> sb.append(resultNumber) }
                it?.setDrawResult(
                    when (matchingCount) {
                        4 -> 1
                        3 -> 2
                        2 -> 3
                        else -> 0
                    },
                    matchingNumbers.toString()
                )
            }
        }
        currentRoundInfo.ofEnd(
            regularNumber
        )
        /*라운드 초기화 */
        gameResultRepository.save(GameResultEntity.ofAutoStart())
    }
}