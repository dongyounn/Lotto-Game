package com.kotlin.boot.user.infra.repository

import com.kotlin.boot.user.domain.PlayGameUser
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PlayGameUserRepository : JpaRepository<PlayGameUser, String> {
    fun findByUserId(userId: String): PlayGameUser?
}