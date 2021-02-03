package com.kotlin.boot.user.controller

import com.kotlin.boot.user.controller.dto.JoinUserInfo
import com.kotlin.boot.user.service.PlayGameUserService
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserInfoController(
    private val playGameUserService: PlayGameUserService
) {
    @PostMapping("join")
    fun joinNewUser(
        @Validated joinUserInfo: JoinUserInfo
    ) = playGameUserService.createNewUser(joinUserInfo)
}