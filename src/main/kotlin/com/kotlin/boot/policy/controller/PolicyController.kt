package com.kotlin.boot.policy.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/policy")
class PolicyController {

    @GetMapping("/round")
    fun getRound(): String {
        return "1"
    }
}