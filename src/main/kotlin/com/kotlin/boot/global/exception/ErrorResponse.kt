package com.kotlin.boot.global.exception


data class ErrorResponse(
    val reason: String,
    val message: String? = null
) {
    companion object {
        fun of(reason: String, message: String?) = ErrorResponse(reason, message)
    }
}

private const val COMPONENT_DYK = "01"
const val REASON_SUCCESS = "000"

enum class ErrorReason(
    private val feature: String,
    private val reason: String
) {
    INVALID_INPUT_DATA("0001", "001"),
    USER_INFO_NOT_FOUND("0001", "002"),
    CURRENT_ROUND_GAME_NOT_FOUND("0001", "003"),
    ACTIVE_GAME_IS_EXIST("0001", "004")
    ;

    fun toReason() = "${COMPONENT_DYK}_${feature}_${reason}"
}