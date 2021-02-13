package com.kotlin.boot.game.repository.infra

import com.kotlin.boot.game.domain.GameEntity
import org.springframework.data.jpa.repository.JpaRepository

interface GameRepository : JpaRepository<GameEntity, Long> {
    fun findByUserIdAndPlayRound(userId: String, playRound: Long): List<GameEntity>
}