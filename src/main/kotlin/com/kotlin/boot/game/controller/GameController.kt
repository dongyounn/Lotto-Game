package com.kotlin.boot.game.controller

import com.kotlin.boot.game.controller.dto.JoinGameDto
import com.kotlin.boot.game.service.GameService
import com.kotlin.boot.global.dto.BaseResponse
import io.swagger.annotations.ApiModelProperty
import io.swagger.annotations.ApiParam
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/lotto")
class GameController(
    private val gameService: GameService
) {
    @PostMapping("/participate")
    fun joinGame(
        @ModelAttribute gameDto: JoinGameDto
    ): BaseResponse {
        return gameService.playGame(gameDto)
    }

    @PostMapping("/pick/draw")
    fun playGame() {

    }

    @PostMapping("/create/game/round")
    fun createGameRound() = gameService.createGameRound()

    @GetMapping("/game/info")
    fun createGameRound(
        @RequestParam
        @ApiParam(value = "조회 하고자 하는 라운드, 미입력 시 현재 라운드로 조회 ")
        round: Long?,
        @RequestParam
        @ApiParam(value = "전화번호로 유저 조회 ")
        phoneNumber: String
    ) = gameService.getPlayerParticipantInfo(round, phoneNumber)
}