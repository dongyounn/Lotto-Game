package com.kotlin.boot.global.dto

data class BaseResponse(
    val reason: String,
    val message: Any? = null
) {
    companion object {
        fun of(message: String) = BaseResponse("SUCCESS", "응모 번호는 : $message")

        fun ofSuccess(obj: Any) = BaseResponse("SUCCESS", obj)

        fun ofSuccessMessage() = BaseResponse("SUCCESS",null)
    }
}