package com.kotlin.boot.user.service

import com.kotlin.boot.global.sequence.CustomSequenceRepository
import com.kotlin.boot.user.controller.dto.RegeditUserInfo
import com.kotlin.boot.user.domain.PlayGameUser
import com.kotlin.boot.user.infra.repository.PlayGameUserRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class PlayGameUserServiceTest(
    @Autowired private val playGameUserRepository: PlayGameUserRepository,
    @Autowired private val playGameUserService: PlayGameUserService,
    @Autowired private val customSequenceRepository: CustomSequenceRepository
) {

    @BeforeEach
    fun saveDefaultUserInfo() {
        playGameUserRepository.save(
            PlayGameUser.of(
                "1234",
                RegeditUserInfo(
                    "990101",
                    "테스트",
                    "01011111111",
                    "똥개"
                )
            )
        )
    }

/*    @BeforeEach
    fun deleteDefaultUserInfo() {
        playGameUserRepository.deleteById("1234")
    }*/

    @Test
    fun getUserInfo() {
        val result = playGameUserService.getUserInfo("1234")
        Assertions.assertEquals(result.userId, "1234")
    }

    @Test
    fun saveUserInfo() {
        playGameUserRepository.save(
            PlayGameUser.of(
                customSequenceRepository.nextUserId(),
                RegeditUserInfo(
                    "990101",
                    "테스트",
                    "01011111111",
                    "똥개"
                )
            )
        )
    }
}