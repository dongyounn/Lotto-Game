package com.kotlin.boot.game.repository.infra

import com.kotlin.boot.game.domain.GameEntity
import com.kotlin.boot.game.domain.QGameEntity.gameEntity
import com.querydsl.core.BooleanBuilder
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Repository

interface GameRepository : JpaRepository<GameEntity, Long> {
    fun findByUserIdAndPlayRound(userId: String, playRound: Long): List<GameEntity>
    fun findByPlayRound(playRound: Long): List<GameEntity>?
    fun countByPlayRound(playRound: Long): Long
}


@Repository
class GameDataCustomRepository(
    private val queryFactory: JPAQueryFactory
) : QuerydslRepositorySupport(GameEntity::class.java) {

    fun getGameDataPagingData(
        round: Long,
        pageRequest: PageRequest
    ): PageImpl<GameEntity?> {
        val builder = BooleanBuilder()
        builder.and(gameEntity.playRound.eq(round))

        val query = queryFactory
            .selectFrom(gameEntity)
            .where(builder.value)

        val result = querydsl?.applyPagination(pageRequest, query)?.fetch() ?: ArrayList()
        return PageImpl(result, pageRequest, query.fetchCount())
    }
}