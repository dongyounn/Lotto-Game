package com.kotlin.boot.utils

import com.kotlin.boot.global.utils.CryptoUtils
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/dev")
class UtilsController(
    private val cryptoUtils: CryptoUtils
) {
    @GetMapping("/decrypt")
    fun getDecrypt(
        @RequestParam data: String
    ) = cryptoUtils.decrypt(data)

    @GetMapping("/encrypt")
    fun getEncrypt(
        @RequestParam data: String
    ) = cryptoUtils.encrypt(data)
}