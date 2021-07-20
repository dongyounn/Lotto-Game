package com.kotlin.boot.game.controller

import com.kotlin.boot.game.controller.dto.JoinGameDto
import com.kotlin.boot.game.service.GameService
import com.kotlin.boot.global.dto.BaseResponse
import io.swagger.annotations.ApiModelProperty
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/lotto")
class GameController(
    private val gameService: GameService
) {
    @PostMapping("/participate")
    @ApiOperation(value = "게임에 참여한다. ")
    fun joinGame(
        @ModelAttribute @Validated gameDto: JoinGameDto
    ): BaseResponse {
        return gameService.participateInGame(gameDto)
    }

    @GetMapping("/game/info")
    @ApiOperation(value = "게임 참여자 정보 조회")
    fun getGameRound(
        @RequestParam
        @ApiParam(value = "조회 하고자 하는 라운드, 미입력 시 현재 라운드로 조회 ")
        round: Long?,
        @RequestParam
        @ApiParam(value = "유저 아이디로 조회 ")
        userId: String
    ) = gameService.gerParticipateGameInfos(round, userId)

    @GetMapping("/game/round/{round}")
    fun getGameRoundInfos(
        @PathVariable round: Long
    ) = gameService.getGameRoundInfos(round)

    @GetMapping("/game/round/count")
    @ApiOperation(value = "현재 라운드 게임 정보")
    fun getCount() = gameService.getRoundCount()

    @GetMapping("/game/report/round/{round}")
    @ApiOperation(value = "해당 라운드 리포트 관리")
    fun getReport(
        @PathVariable(required = true) round: Long
    ) = gameService.getGameResultInfo(round)


}