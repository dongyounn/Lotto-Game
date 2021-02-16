package com.kotlin.boot.batch.controller

import com.kotlin.boot.batch.service.BatchService
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/batch")
class BatchController(
    private val batchService: BatchService
) {
    @PostMapping("/pick/draw")
    @ApiOperation(value = "게임 추천 배치")
    fun playGame() =
        batchService.playLotto()

}