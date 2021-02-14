package com.kotlin.boot.game.service

import com.kotlin.boot.game.controller.dto.JoinGameDto
import com.kotlin.boot.user.controller.dto.RegeditUserInfo
import com.kotlin.boot.user.domain.PlayGameUser
import com.kotlin.boot.user.infra.repository.PlayGameUserRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@Transactional
class GameServiceTest(
    @Autowired private val gameService: GameService,
    @Autowired private val playGameUserRepository: PlayGameUserRepository
) {
    @BeforeEach
    fun saveDefaultUserInfo() {
        playGameUserRepository.save(
            PlayGameUser.of(
                "99999999",
                RegeditUserInfo(
                    dob = "990101",
                    name = "테스트",
                    phoneNumber = "01099992222",
                    nickName = "똥개개똥개"
                )
            )
        )
    }

    @AfterEach
    fun deleteDefaultUserInfo() {
        playGameUserRepository.deleteById("99999999")
    }

    @Test
    fun getPlayGameNumberException() {
        val req = JoinGameDto(listOf(7, 1, 2, 4), "0109991122")
        assertThrows(RuntimeException::class.java) {
            gameService.participateInGame(req)
        }
    }

    @Test
    fun getPlayGameNumber() {
        val req = JoinGameDto(listOf(7, 1, 2, 4, 12, 44), "01099992222")
        gameService.participateInGame(req)
        val result = playGameUserRepository.findByPhoneNumber("01099992222")
        assertEquals(result?.nickName, "똥개개똥개")
    }
}
