package com.kotlin.boot.global.dto

data class BaseResponse(
    val reason: String,
    val message: String? = null
){
    companion object {
        fun of(message: String) = BaseResponse("SUCCESS", "응모 번호는 : $message")
    }
}