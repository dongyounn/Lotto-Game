package com.kotlin.boot.user.controller.dto

import io.swagger.annotations.ApiModelProperty
import org.hibernate.validator.constraints.Length

data class RegeditUserInfo(
    @ApiModelProperty(value = "생년월일 YYYYMMDD", required = true)
    @Length(min = 6, max = 6)
    val dob: String,
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
    @ApiModelProperty(value = "생년월일 YYYYMMDD", required = true)
    @Length(min = 6, max = 6)
    val dob: String?,
    @ApiModelProperty(value = "이름", required = true)
    val name: String?,
    @ApiModelProperty(value = "휴대전화번호", required = true)
    val phoneNumber: String,
    @ApiModelProperty(value = "닉네임", required = true)
    val nickName: String?
)

data class GetUserInfo(
    @ApiModelProperty(value = "생년월일 YYYYMMDD", required = false)
    @Length(min = 6, max = 6)
    val dob: String?,
    @ApiModelProperty(value = "이름", required = false)
    val name: String?,
    @ApiModelProperty(value = "휴대전화번호", required = false)
    val phoneNumber: String?,
    @ApiModelProperty(value = "닉네임", required = false)
    val nickName: String?
)