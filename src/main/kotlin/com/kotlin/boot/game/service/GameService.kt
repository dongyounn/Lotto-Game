package com.kotlin.boot.game.service

import com.kotlin.boot.game.controller.dto.AutoYnEnum
import com.kotlin.boot.game.controller.dto.JoinGameDto
import com.kotlin.boot.game.domain.GameEntity
import com.kotlin.boot.game.repository.infra.GameRepository
import com.kotlin.boot.global.dto.BaseResponse
import com.kotlin.boot.global.exception.BadRequestException
import com.kotlin.boot.global.exception.ErrorReason
import org.springframework.stereotype.Service
import java.util.*
import javax.transaction.Transactional

@Service
class GameService(
    private val gameRepository: GameRepository
) {

    @Transactional
    fun playGame(joinGameDto: JoinGameDto): BaseResponse {


        val numberList = when (joinGameDto.autoYnEnum) {
            AutoYnEnum.N -> {
                joinGameDto.numbers ?: throw BadRequestException(
                    ErrorReason.INVALID_INPUT_DATA,
                    "번호를 입력해주세요"
                )
            }
            AutoYnEnum.Y -> getAutoRandom(5)
        }

        if (numberList.size > 5) throw BadRequestException(
            ErrorReason.INVALID_INPUT_DATA,
            "번호는 5개만 입력해주세요"
        )

        val sb = StringBuilder()

        for (number in numberList) {
            if (number > 45 || number < 1) throw BadRequestException(
                ErrorReason.INVALID_INPUT_DATA,
                "번호 ::: 최소값 : 1 , 최대값 : 45"
            )

            sb.append("$number,")
        }

        gameRepository.save(
            GameEntity.of(
                joinGameDto.phoneNumber.replace("-", "").toLong(),
                joinGameDto.playerName,
                sb.substring(0, sb.length - 1).toString()
            )
        )
        return BaseResponse.of()

    }

    fun getRandomNumber(length: Long): String {
        val random = Random()
        val sb = StringBuilder()
        for (i in 0 until length) {
            sb.append(random.nextInt(10))
        }
        return sb.toString()
    }

    private fun getRandom(): Long {
        val random = Random()
        return random.nextInt(45).toLong()
    }

    private fun getAutoRandom(count: Long): List<Long> {
        val random = Random()
        val numberList = ArrayList<Long>()
        for (i in 0 until count) {
            numberList.add(random.nextInt(45).toLong())
        }
        return numberList
    }
}
