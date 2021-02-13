package com.kotlin.boot.user.infra.repository

import com.kotlin.boot.user.controller.dto.GetUserInfo
import com.kotlin.boot.user.domain.PlayGameUser
import com.kotlin.boot.user.domain.QPlayGameUser.playGameUser
import com.querydsl.core.BooleanBuilder
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Repository

@Repository
interface PlayGameUserRepository : JpaRepository<PlayGameUser, String> {
    fun findByUserId(userId: String): PlayGameUser?
    fun findByPhoneNumber(string: String): PlayGameUser?
}

@Repository
class UserInfoQueryFactory(
    private val queryFactory: JPAQueryFactory
) : QuerydslRepositorySupport(PlayGameUser::class.java) {

    fun getUserInfos(request: GetUserInfo, pageRequest: PageRequest): PageImpl<PlayGameUser>? {

        val userName = request.name
        val nickName = request.nickName
        val dob = request.dob
        val phoneNumber = request.phoneNumber


        val builder = BooleanBuilder()
        if (!userName.isNullOrEmpty()) builder.and(playGameUser.userName.eq(userName))
        if (!nickName.isNullOrEmpty()) builder.and(playGameUser.nickName.like("%$nickName%"))
        if (!dob.isNullOrEmpty()) builder.and(playGameUser.dob.eq(dob))
        if (!phoneNumber.isNullOrEmpty()) builder.and(playGameUser.phoneNumber.eq(phoneNumber))


        val query = queryFactory
            .selectFrom(playGameUser)
            .where(builder.value)

        val result = querydsl?.applyPagination(pageRequest, query)?.fetch() ?: ArrayList()
        return PageImpl(result, pageRequest, query.fetchCount())
    }
}