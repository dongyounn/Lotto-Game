package com.kotlin.boot.user.service

import com.kotlin.boot.global.exception.BadRequestException
import com.kotlin.boot.global.exception.ErrorReason
import com.kotlin.boot.global.sequence.CustomSequenceRepository
import com.kotlin.boot.user.controller.dto.ChangeUserInfo
import com.kotlin.boot.user.controller.dto.RegeditUserInfo
import com.kotlin.boot.user.domain.PlayGameUser
import com.kotlin.boot.user.infra.repository.PlayGameUserRepository
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class PlayGameUserService(
    private val playGameUserRepository: PlayGameUserRepository,
    private val customSequenceRepository: CustomSequenceRepository
) {
    fun getUserInfo(userId: String): PlayGameUser {
        return playGameUserRepository.findByUserId(userId)
            ?: throw BadRequestException(
                ErrorReason.INVALID_INPUT_DATA, "INVALID USER ID : $userId"
            )
    }

    @Transactional
    fun createNewUser(request: RegeditUserInfo) {
        playGameUserRepository.save(
            PlayGameUser.of(
                customSequenceRepository.nextUserId(),
                request
            )
        )
    }

    @Transactional
    fun updateUserInfo(request: ChangeUserInfo) {
        playGameUserRepository.findByPhoneNumber(request.phoneNumber)?.let {
            it.changeUserInfo(request)
        } ?: throw BadRequestException(ErrorReason.INVALID_INPUT_DATA, "USER INFO NOT FOUND")

    }
}