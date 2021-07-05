package com.kotlin.boot.user.domain

import com.kotlin.boot.global.dto.BaseDomain
import com.kotlin.boot.global.dto.StringCryptoConverter
import com.kotlin.boot.global.dto.YesOrNoEnum
import com.kotlin.boot.user.controller.dto.ChangeUserInfo
import com.kotlin.boot.user.controller.dto.RegeditUserInfo
import javax.persistence.*

@Entity
data class PlayGameUser(
    @Id
    val userId: String,
    @Convert(converter = StringCryptoConverter::class)
    val socialNo: String,
    @Convert(converter = StringCryptoConverter::class)
    var userName: String,
    var nickName: String,
    @Convert(converter = StringCryptoConverter::class)
    val phoneNumber: String,
    @Enumerated(EnumType.STRING)
    var verify: YesOrNoEnum = YesOrNoEnum.N
) : BaseDomain() {
    companion object {
        fun of(
            userId: String,
            regeditUserInfo: RegeditUserInfo
        ) = PlayGameUser(
            userId,
            "${regeditUserInfo.socialNoPrefix}-${regeditUserInfo.socialNoSuffix}",
            regeditUserInfo.name,
            regeditUserInfo.nickName,
            regeditUserInfo.phoneNumber
        )
    }

    fun changeUserInfo(
        request: ChangeUserInfo
    ) {
        this.userName = request.name ?: this.userName
        this.nickName = request.nickName ?: this.nickName
    }
}