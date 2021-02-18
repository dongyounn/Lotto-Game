package com.kotlin.boot.game.controller

import com.kotlin.boot.game.controller.dto.JoinGameDto
import com.kotlin.boot.game.repository.infra.GameResultRepository
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.util.*
import javax.transaction.Transactional

@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureMockMvc
class GameControllerTest(
    @Autowired private val mvc: MockMvc,
    @Autowired private val gameResultRepository: GameResultRepository
) {

    @Test
    @Transactional
    fun createRound() {
        mvc.perform(
            MockMvcRequestBuilders.post("/lotto/create/game/round")
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk)
    }

    @Test
//    @RepeatedTest(value = 1000000)
    fun playGame() {
        mvc.perform(
            MockMvcRequestBuilders.post("/lotto/participate")
                .param("phoneNumber", "01012344121")
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk)
    }
}