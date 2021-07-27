package com.kotlin.boot.user.controller.dto

import com.kotlin.boot.global.utils.masking
import com.kotlin.boot.user.domain.PlayGameUser
import io.swagger.annotations.ApiModelProperty

data class RegeditUserInfo(
    @ApiModelProperty(value = "이름", required = true)
    val name: String,
    @ApiModelProperty(value = "휴대전화번호", required = true)
    val phoneNumber: String
)

data class ChangeUserInfo(
    @ApiModelProperty(value = "유저아이디", required = true)
    val userId: String,
    @ApiModelProperty(value = "이름", required = false)
    val name: String?,
    @ApiModelProperty(value = "휴대전화번호", required = false)
    val phoneNumber: String
)

data class GetUserInfo(
    @ApiModelProperty(value = "이름", required = false)
    val name: String?,
    @ApiModelProperty(value = "휴대전화번호", required = false)
    val phoneNumber: String?
)

data class GetUserInfoResponse(
    val userId: String,
    var userName: String,
    val phoneNumber: String
) {
    companion object {
        fun of(req: PlayGameUser) = GetUserInfoResponse(
            req.userId,
            req.userName,
            req.phoneNumber.masking()
        )
    }
}