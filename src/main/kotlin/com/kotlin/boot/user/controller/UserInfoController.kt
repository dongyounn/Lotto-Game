package com.kotlin.boot.user.controller

import com.kotlin.boot.global.utils.CustomPageRequest
import com.kotlin.boot.user.controller.dto.ChangeUserInfo
import com.kotlin.boot.user.controller.dto.GetUserInfo
import com.kotlin.boot.user.controller.dto.RegeditUserInfo
import com.kotlin.boot.user.service.PlayGameUserService
import io.swagger.annotations.ApiModelProperty
import io.swagger.annotations.ApiOperation
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
class UserInfoController(
    private val playGameUserService: PlayGameUserService
) {
    @PostMapping("/join")
    @ApiOperation(value = "회원 가입 API")
    fun joinNewUser(
        @Validated @RequestBody regeditUserInfo: RegeditUserInfo
    ) = playGameUserService.createNewUser(regeditUserInfo)

    @PostMapping("/change/info")
    @ApiOperation(value = "회원정보 수정 API")
    fun updateUserInfo(
        @Validated @RequestBody updateUserInfo: ChangeUserInfo
    ) = playGameUserService.updateUserInfo(updateUserInfo)

    @GetMapping("/user/infos")
    @ApiOperation(value = "유저정보 조회 API")
    fun getUserInfos(
        @Validated @ModelAttribute getUserInfo: GetUserInfo,
        @ModelAttribute customPageRequest: CustomPageRequest
    ) = playGameUserService.getUserInfos(getUserInfo, customPageRequest)

}