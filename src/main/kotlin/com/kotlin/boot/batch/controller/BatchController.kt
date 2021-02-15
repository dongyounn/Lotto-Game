package com.kotlin.boot.batch.controller

import com.kotlin.boot.batch.service.BatchService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/batch")
class BatchController(
    private val batchService: BatchService
) {
    @PostMapping("/pick/draw")
    fun playGame() =
        batchService.playLotto()

}