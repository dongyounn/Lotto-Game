package com.kotlin.boot.game.domain

import com.kotlin.boot.global.dto.BaseDomain
import com.kotlin.boot.user.domain.PlayGameUser
import javax.persistence.*

@Entity
@Table(name = "PLAY_GAME_RESULT")
data class GameResultEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "playGameResultIdGenerator")
    @SequenceGenerator(name = "playGameResultIdGenerator", sequenceName = "PLAY_GAME_RESULT_SEQ", allocationSize = 1)
    var id: Long? = null,
    @Column(name = "GAME_NUMBER")
    val gameNumber: String,
    @Column(name = "NORMAL_NUMBER")
    val normalNumber: String,
    @Column(name = "BONUS_NUMBER")
    val bonusNumber: Long,
    @Column(name = "PLAYER_NO")
    val playerNo: Long
) : BaseDomain()
