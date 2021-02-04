package com.kotlin.boot.user.domain

import com.kotlin.boot.global.dto.BaseDomain
import com.kotlin.boot.global.dto.YesOrNoEnum
import com.kotlin.boot.user.controller.dto.ChangeUserInfo
import com.kotlin.boot.user.controller.dto.RegeditUserInfo
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.Id

@Entity
data class PlayGameUser(
    @Id
    val userId: String,
    var dob: String,
    var userName: String,
    var nickName: String,
    val phoneNumber: String,
    @Enumerated(EnumType.STRING)
    var verify: YesOrNoEnum = YesOrNoEnum.N
) : BaseDomain() {
    companion object {
        fun of(
            userId: String,
            regeditUserInfo: RegeditUserInfo
        ) = PlayGameUser(
            userId, regeditUserInfo.dob, regeditUserInfo.name, regeditUserInfo.phoneNumber, regeditUserInfo.nickName
        )
    }

    fun changeUserInfo(
        request: ChangeUserInfo
    ) {
        this.dob = request.dob ?: this.dob
        this.userName = request.name ?: this.userName
        this.nickName = request.nickName ?: this.nickName
    }
}