package com.kotlin.boot.user.domain

import com.kotlin.boot.global.dto.BaseDomain
import com.kotlin.boot.global.dto.StringCryptoConverter
import com.kotlin.boot.global.dto.YesOrNoEnum
import com.kotlin.boot.user.controller.dto.ChangeUserInfo
import com.kotlin.boot.user.controller.dto.RegeditUserInfo
import javax.persistence.*

@Entity
@Table(name = "play_game_user")
data class PlayGameUser(
    @Id
    @Column(name = "user_id")
    val userId: String,
    @Column(name = "user_name")
    @Convert(converter = StringCryptoConverter::class)
    var userName: String,
    @Column(name = "phone_number")
    @Convert(converter = StringCryptoConverter::class)
    val phoneNumber: String
) : BaseDomain() {
    companion object {
        fun of(
            userId: String,
            regeditUserInfo: RegeditUserInfo
        ) = PlayGameUser(
            userId,
            regeditUserInfo.name,
            regeditUserInfo.phoneNumber
        )
    }

    fun changeUserInfo(
        request: ChangeUserInfo
    ) {
        this.userName = request.name ?: this.userName
    }
}