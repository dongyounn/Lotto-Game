package com.kotlin.boot.game.repository.infra

import com.kotlin.boot.game.controller.dto.GameStatusEnum
import com.kotlin.boot.game.domain.GameResultEntity
import org.springframework.data.repository.CrudRepository

interface GameResultRepository : CrudRepository<GameResultEntity, Long> {
    fun findByStatus(status: GameStatusEnum = GameStatusEnum.ACTIVE): GameResultEntity
}