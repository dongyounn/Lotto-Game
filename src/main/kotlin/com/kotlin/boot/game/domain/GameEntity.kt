package com.kotlin.boot.game.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "PLAY_GAME_INFO")
data class GameEntity(
        @Id
        @Column(name = "PLAYER_PHONE_NUMBER")
        val phoneNumber: Long,
        @Column(name = "PLAYER_USER_NAME")
        val realName: String,
        @Column(name = "PLAYER_GAME_NUMBER")
        val gameNumber: String
){
        companion object{
                fun of(
                        phoneNumber: Long,
                        realName: String,
                        gameNumber: String
                ) = GameEntity(phoneNumber, realName, gameNumber)
        }
}
