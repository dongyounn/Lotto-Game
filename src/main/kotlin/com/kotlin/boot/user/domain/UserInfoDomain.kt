package com.kotlin.boot.user.domain

import com.kotlin.boot.global.dto.BaseDomain
import com.kotlin.boot.global.dto.YesOrNoEnum
import com.kotlin.boot.user.controller.dto.JoinUserInfo
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.Id

@Entity
data class PlayGameUser(
    @Id
    val userId: String,
    val dob: String,
    val userName: String,
    val phoneNumber: String,
    @Enumerated(EnumType.STRING)
    var verify: YesOrNoEnum = YesOrNoEnum.N
) : BaseDomain() {
    companion object {
        fun of(
            userId: String,
            joinUserInfo: JoinUserInfo
        ) = PlayGameUser(
            userId, joinUserInfo.dob, joinUserInfo.name, joinUserInfo.phoneNumber
        )
    }
}