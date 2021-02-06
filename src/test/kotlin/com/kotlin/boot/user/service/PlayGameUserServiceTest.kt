package com.kotlin.boot.user.service

import com.kotlin.boot.global.dto.NONE
import com.kotlin.boot.global.sequence.CustomSequenceRepository
import com.kotlin.boot.global.utils.CustomPageRequest
import com.kotlin.boot.user.controller.dto.GetUserInfo
import com.kotlin.boot.user.controller.dto.RegeditUserInfo
import com.kotlin.boot.user.domain.PlayGameUser
import com.kotlin.boot.user.infra.repository.PlayGameUserRepository
import org.junit.jupiter.api.AfterEach
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
                    dob = "990101",
                    name = "테스트",
                    phoneNumber = "01011111111",
                    nickName = "똥개개똥개"
                )
            )
        )
    }

    @AfterEach
    fun deleteDefaultUserInfo() {
        playGameUserRepository.deleteById("1234")
    }

    @Test
    fun getUserInfo() {
        val result = playGameUserService.getUserInfo("1234")
        Assertions.assertEquals(result.userId, "1234")
    }

    @Test
    fun getUserInfoByQueryDslTest() {
        val result = playGameUserService.getUserInfos(
            GetUserInfo(null, null, null, "개개"),
            CustomPageRequest()
        )
        Assertions.assertEquals(result?.content?.get(0)?.userId ?: NONE, "1234")
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