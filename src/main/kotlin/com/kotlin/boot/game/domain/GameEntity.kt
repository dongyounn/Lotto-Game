package com.kotlin.boot.game.domain

import com.kotlin.boot.global.dto.BaseDomain
import com.kotlin.boot.global.dto.StringCryptoConverter
import com.kotlin.boot.user.domain.PlayGameUser
import javax.persistence.*

@Entity
@Table(name = "PLAY_GAME_INFO")
data class GameEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "playGameIdGenerator")
    @SequenceGenerator(name = "playGameIdGenerator", sequenceName = "PLAY_GAME_HISTORY_SEQ", allocationSize = 1)
    var id: Long? = null,
    @Convert(converter = StringCryptoConverter::class)
    @Column(name = "PLAYER_PHONE_NUMBER")
    val phoneNumber: String,
    @Column(name = "PLAYER_NICK_NAME")
    val nickName: String,
    @Column(name = "GAME_NUMBER")
    val gameNumber: String,
    @Column(name = "MATCH_NUMBER")
    var matchNumber: String?,
    @Column(name = "USER_ID")
    val userId: String,
    @Column(name = "GAME_ROUND")
    val playRound: Long,
    @Column(name = "DRAW_RESULT")
    var drawResult: Long? = 0
) : BaseDomain() {
    companion object {
        fun of(
            playGameUserInfo: PlayGameUser,
            gameNumber: String,
            playRound: Long
        ) = GameEntity(
            null,
            playGameUserInfo.phoneNumber,
            playGameUserInfo.nickName,
            gameNumber,
            null,
            playGameUserInfo.userId,
            playRound
        )
    }

    fun setDrawResult(
        drawResult: Long,
        matchingNumber: String
    ) {
        this.drawResult = drawResult
        this.matchNumber = matchingNumber
    }
}
