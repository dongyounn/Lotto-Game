package com.kotlin.boot.game.controller

import com.kotlin.boot.game.controller.dto.JoinGameDto
import com.kotlin.boot.game.service.GameService
import com.kotlin.boot.global.dto.BaseResponse
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/lotto/")
class GameController(
    private val gameService: GameService
) {
    @PostMapping("/join/game")
    fun joinGame(
        @ModelAttribute gameDto: JoinGameDto
    ): BaseResponse {
        return gameService.playGame(gameDto)
    }

    @PostMapping("/play")
    fun playGame() {

    }

}