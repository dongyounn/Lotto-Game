package com.kotlin.boot.user.controller.dto

import com.kotlin.boot.global.dto.StringCryptoConverter
import com.kotlin.boot.global.dto.YesOrNoEnum
import com.kotlin.boot.user.domain.PlayGameUser
import io.swagger.annotations.ApiModelProperty
import org.hibernate.validator.constraints.Length
import javax.persistence.Column
import javax.persistence.Convert
import javax.persistence.EnumType
import javax.persistence.Enumerated

data class RegeditUserInfo(
    @ApiModelProperty(value = "생년월일 YYYYMMDD", required = true)
    val socialNoPrefix: String,
    @ApiModelProperty(value = "주민번호 뒷자리", required = true)
    val socialNoSuffix: String,
    @ApiModelProperty(value = "이름", required = true)
    val name: String,
    /*암호화 할 수 있는 방법은?*/
//    todo 추후에 openapi 메시지를 이용해 점유인증 해보자
    @ApiModelProperty(value = "휴대전화번호", required = true)
    val phoneNumber: String,
    @ApiModelProperty(value = "닉네임", required = true)
    val nickName: String
)

data class ChangeUserInfo(
    @ApiModelProperty(value = "유저아이디", required = true)
    val userId: String,
    @ApiModelProperty(value = "이름", required = false)
    val name: String?,
    @ApiModelProperty(value = "휴대전화번호", required = false)
    val phoneNumber: String,
    @ApiModelProperty(value = "닉네임", required = false)
    val nickName: String?
)

data class GetUserInfo(
    @ApiModelProperty(value = "이름", required = false)
    val name: String?,
    @ApiModelProperty(value = "휴대전화번호", required = false)
    val phoneNumber: String?,
    @ApiModelProperty(value = "닉네임", required = false)
    val nickName: String?
)

data class GetUserInfoResponse(
    val userId: String,
    val socialNo: String,
    var userName: String,
    var nickName: String,
    val phoneNumber: String
) {
    companion object {
        fun of(req: PlayGameUser) = GetUserInfoResponse(
            req.userId,
            req.socialNo.removeRange(7, 15),
            req.userName,
            req.nickName,
            req.phoneNumber.removeRange(7, 20)
        )
    }
}