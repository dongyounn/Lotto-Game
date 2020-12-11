package com.kotlin.boot.global.dto

import com.kotlin.boot.global.exception.ErrorResponse

data class BaseResponse(
    val reason: String,
    val message: String? = null
){
    companion object {
        fun of() = BaseResponse("SUCCESS")
    }
}