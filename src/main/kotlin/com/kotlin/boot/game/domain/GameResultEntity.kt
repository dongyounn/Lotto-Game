package com.kotlin.boot.game.domain

import com.kotlin.boot.game.controller.dto.GameStatusEnum
import com.kotlin.boot.global.dto.BaseDomain
import javax.persistence.*

@Entity
@Table(name = "PLAY_GAME_RESULT")
data class GameResultEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "playGameResultIdGenerator")
    @SequenceGenerator(name = "playGameResultIdGenerator", sequenceName = "PLAY_GAME_RESULT_SEQ", allocationSize = 1)
    var id: Long? = null,
    @Column(name = "NORMAL_NUMBER")
    var normalNumber: String? = null,
    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    var status: GameStatusEnum,
    @Column(name = "PLAYER_NO")
    var playerNo: Long
) : BaseDomain() {
    companion object {
        fun ofAutoStart() = GameResultEntity(
            playerNo = 0,
            status = GameStatusEnum.ACTIVE
        )

    }

    fun plusPlayerNo() {
        this.playerNo = this.playerNo.plus(1)
    }

    fun ofEnd(
        normalNumber: String
    ) {
        this.normalNumber = normalNumber
        this.status = GameStatusEnum.TERMINATED
    }
}
