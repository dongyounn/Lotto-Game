package com.kotlin.boot.user.controller

import com.kotlin.boot.global.utils.CustomPageRequest
import com.kotlin.boot.user.controller.dto.ChangeUserInfo
import com.kotlin.boot.user.controller.dto.GetUserInfo
import com.kotlin.boot.user.controller.dto.RegeditUserInfo
import com.kotlin.boot.user.service.PlayGameUserService
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
class UserInfoController(
    private val playGameUserService: PlayGameUserService
) {
    @PostMapping("/join")
    fun joinNewUser(
        @Validated @RequestBody regeditUserInfo: RegeditUserInfo
    ) = playGameUserService.createNewUser(regeditUserInfo)

    @PostMapping("/change/info")
    fun updateUserInfo(
        @Validated @RequestBody updateUserInfo: ChangeUserInfo
    ) = playGameUserService.updateUserInfo(updateUserInfo)

    @GetMapping("/user/infos")
    fun getUserInfos(
        @Validated @ModelAttribute getUserInfo: GetUserInfo,
        @ModelAttribute customPageRequest: CustomPageRequest
    ) = playGameUserService.getUserInfos(getUserInfo, customPageRequest)

}