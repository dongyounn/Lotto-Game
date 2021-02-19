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
    //코드가 더럽다....
    @Transactional(noRollbackFor = [Exception::class])
    fun playLotto() {
        val normalNumber = StringBuilder()
        val randomNumber = numberUtils.getAutoNumber(5).sorted()

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
                it?.setDrawResult(
                    when (matchingCount) {
                        4 -> 1
                        3 -> 2
                        2 -> 3
                        else -> 0
                    }
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