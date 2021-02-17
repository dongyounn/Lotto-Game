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
        val bonusNumber = numberUtils.getAutoNumber(1)[0]

        val currentRoundInfo = gameResultRepository.findByStatus()
        var bonusMatching = false

        val currentRoundPlayerInfos = gameRepository.findByPlayRound(currentRoundInfo.id!!)
        currentRoundPlayerInfos.let { gameInfos ->
            gameInfos.forEach {
                var matchingCount = 0
                it?.gameNumber?.split(",")?.forEach { number ->
                    if (randomNumber.contains(number.toLong())) {
                        matchingCount++
                    }
                    bonusMatching = number.toLong() == bonusNumber
                }
                it?.setDrawResult(
                    when (matchingCount) {
                        7 -> 1
                        6 -> (if (bonusMatching) 2 else 3).toLong()
                        5 -> 4
                        4 -> 5
                        3 -> 6
                        else -> 0
                    }
                )
            }
        }
        currentRoundInfo.ofEnd(
            regularNumber,
            bonusNumber
        )

        /*라운드 초기화 */
        gameResultRepository.save(GameResultEntity.ofAutoStart())
    }
}