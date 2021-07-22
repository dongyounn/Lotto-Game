package com.kotlin.boot.game.repository.infra

import com.kotlin.boot.game.controller.dto.GameStatusEnum
import com.kotlin.boot.game.domain.GameResultEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Lock
import org.springframework.data.jpa.repository.QueryHints
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import javax.persistence.LockModeType
import javax.persistence.QueryHint

interface GameResultRepository : CrudRepository<GameResultEntity, Long> {
    fun findByIdAndStatus(id: Long, status: GameStatusEnum = GameStatusEnum.TERMINATED): GameResultEntity?
    fun findByStatus(status: GameStatusEnum = GameStatusEnum.ACTIVE): GameResultEntity
}

@Repository
interface GameResultLockRepository : JpaRepository<GameResultEntity, Long> {
    @Lock(LockModeType.PESSIMISTIC_READ)
    @QueryHints(QueryHint(name = "javax.persistence.lock.timeout", value = "5000"))
    fun findByStatus(status: GameStatusEnum = GameStatusEnum.ACTIVE): GameResultEntity
}