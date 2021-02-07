package com.kotlin.boot.game.service

import com.kotlin.boot.game.controller.dto.JoinGameDto
import com.kotlin.boot.game.domain.GameEntity
import com.kotlin.boot.game.repository.infra.GameRepository
import com.kotlin.boot.global.dto.BaseResponse
import com.kotlin.boot.global.exception.BadRequestException
import com.kotlin.boot.global.exception.ErrorReason
import com.kotlin.boot.global.utils.convertYYYYmmDD
import com.kotlin.boot.user.infra.repository.PlayGameUserRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*
import javax.transaction.Transactional

@Service
class GameService(
    private val gameRepository: GameRepository,
    private val gameUserRepository: PlayGameUserRepository
) {

/*    fun playLotto() {
        val today = LocalDateTime.now().convertYYYYmmDD()

        val randomNumbers = getAutoRandom(5)
        val bonusNumber = getAutoRandom(1)
    }*/

    @Transactional
    fun playGame(joinGameDto: JoinGameDto): BaseResponse {
        val entryNumberList = joinGameDto.numbers
        val numberList = when {
            entryNumberList.isNullOrEmpty() -> getAutoRandom(5)
            entryNumberList.size < 6 -> {
                checkInputNumbers(entryNumberList as ArrayList<Long>)
                getHalfAutoRandom(
                    (6 - entryNumberList.size).toLong(),
                    entryNumberList
                )
            }
            else -> throw BadRequestException(
                ErrorReason.INVALID_INPUT_DATA,
                "### 번호는 5개만 입력해주세요"
            )
        }
        val sb = StringBuilder()
        for (number in numberList) {
            if (number > 45 || number < 1) throw BadRequestException(
                ErrorReason.INVALID_INPUT_DATA,
                "### 번호 -> 최소값 : 1 , 최대값 : 45"
            )
            sb.append("$number,")
        }

        val submitNumbers = sb.substring(0, sb.length - 1).toString()
        //회원 정보 조회
        gameUserRepository.findByPhoneNumber(joinGameDto.phoneNumber)?.let {
            gameRepository.save(
                GameEntity.of(
                    it,
                    submitNumbers,
                    //TODO Round 관리하는 정책 필요하다.
                    1
                )
            )
        } ?: throw BadRequestException(
            ErrorReason.USER_INFO_NOT_FOUND,
            "### 유저 정보를 찾을 수 없습니다. 해당 번호로 가입 먼저 진행하세요."
        )

        return BaseResponse.of(submitNumbers)
    }

    fun checkInputNumbers(numberList: ArrayList<Long>) {
        for (target in numberList) {
            var count = 0
            for (i in 0 until numberList.size) {
                if (target == numberList[i]) count += 1
            }
            if (count > 1)
                throw BadRequestException(
                    ErrorReason.INVALID_INPUT_DATA,
                    "### 번호 중복 : $target"
                )
        }
    }

/*    fun getRandomNumber(length: Long): String {
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
    }*/

    private fun getAutoRandom(count: Long): List<Long> {
        val random = Random()
        val numberList = ArrayList<Long>()
        for (i in 0 until count) {
            var number: Long
            do {
                number = random.nextInt(45).toLong()
            } while (numberList.contains(number))
            numberList.add(number)
        }
        return numberList
    }

    private fun getHalfAutoRandom(count: Long, numberList: ArrayList<Long>): List<Long> {
        val random = Random()
        for (i in 0 until count) {
            var number: Long
            do {
                number = random.nextInt(45).toLong()
            } while (numberList.contains(number))
            numberList.add(number)
        }
        return numberList
    }
}
