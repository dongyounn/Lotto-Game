package com.kotlin.boot.user.domain

import com.kotlin.boot.global.dto.BaseDomain
import com.kotlin.boot.global.dto.YesOrNoEnum
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
            dob: String,
            userName: String,
            phoneNumber: String
        ) = PlayGameUser(
            userId, dob, userName, phoneNumber
        )
    }
}