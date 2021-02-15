package com.kotlin.boot.batch.service

import com.kotlin.boot.game.domain.GameResultEntity
import com.kotlin.boot.game.repository.infra.GameRepository
import com.kotlin.boot.game.repository.infra.GameResultRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class BatchService(
    private val gameResultRepository: GameResultRepository,
    private val gameRepository: GameRepository
) {
    //코드가 더럽다....
    @Transactional(noRollbackFor = [Exception::class])
    fun playLotto() {
        val normalNumber = StringBuilder()
        val randomNumber = getAutoRandom(5)
            .sorted()

        randomNumber.forEach {
            normalNumber.append("$it,")
        }
        val regularNumber = normalNumber.toString().removeSuffix(",")
        val bonusNumber = getAutoRandom(1)[0]

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
                    if (number.toLong() == bonusNumber) {
                        bonusMatching = true
                    }
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

    //TODO 중복코드 제거 필요
    private fun getAutoRandom(count: Long): List<Long> {
        val random = Random()
        val numberList = ArrayList<Long>()
        for (i in 0 until count) {
            var number: Long
            do {
                number = random.nextInt(45).toLong()
            } while (numberList.contains(number) || number == 0L)
            numberList.add(number)
        }
        return numberList
    }
}