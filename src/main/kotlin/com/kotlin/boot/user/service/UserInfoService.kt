package com.kotlin.boot.user.service

import com.kotlin.boot.global.dto.BaseResponse
import com.kotlin.boot.global.exception.BadRequestException
import com.kotlin.boot.global.exception.ErrorReason
import com.kotlin.boot.global.sequence.CustomSequenceRepository
import com.kotlin.boot.global.utils.CustomPageRequest
import com.kotlin.boot.user.controller.dto.ChangeUserInfo
import com.kotlin.boot.user.controller.dto.GetUserInfo
import com.kotlin.boot.user.controller.dto.GetUserInfoResponse
import com.kotlin.boot.user.controller.dto.RegeditUserInfo
import com.kotlin.boot.user.domain.PlayGameUser
import com.kotlin.boot.user.infra.repository.PlayGameUserRepository
import com.kotlin.boot.user.infra.repository.UserInfoQueryFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class PlayGameUserService(
    private val playGameUserRepository: PlayGameUserRepository,
    private val customSequenceRepository: CustomSequenceRepository,
    private val userInfoQueryFactory: UserInfoQueryFactory
) {
    fun getUserInfo(prefix: String, suffix: String): GetUserInfoResponse =
        GetUserInfoResponse.of(userInfoQueryFactory.getUserInfo(prefix, suffix))


    @Transactional
    fun createNewUser(request: RegeditUserInfo): BaseResponse {
        /*유저 존재 여부 체크*/
        userInfoQueryFactory.checkExistUser(request.socialNoPrefix, request.socialNoSuffix)
            .let {
                /*유저가 없으면 가입*/
                if (!it) {
                    customSequenceRepository.nextUserId().let { id ->
                        playGameUserRepository.save(
                            PlayGameUser.of(
                                id,
                                request
                            )
                        )
                        return BaseResponse.ofSuccess(id)
                    }
                } else
                    /*있으면 가입 불가*/
                    throw BadRequestException(ErrorReason.USER_ALREADY_EXIST, "USER_ALREADY_EXIST")
            }
    }

    @Transactional
    fun updateUserInfo(request: ChangeUserInfo): BaseResponse {
        playGameUserRepository.findByUserId(request.userId)
            ?.let {
                it.changeUserInfo(request)
                return BaseResponse.ofSuccessMessage()
            } ?: throw BadRequestException(ErrorReason.INVALID_INPUT_DATA, "유저정보 없음")
    }

    fun getUserInfos(
        request: GetUserInfo,
        pageRequest: CustomPageRequest
    ) = userInfoQueryFactory.getUserInfos(request, pageRequest.of())?.map {
        GetUserInfoResponse.of(it)
    }?.toList()
}