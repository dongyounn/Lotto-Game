package com.kotlin.boot.exception


data class ErrorResponse(
        val reason: String,
        val message: String? = null
) {
    companion object {
        fun of(reason: String, message: String?) = ErrorResponse(reason, message)
    }
}

private const val COMPONENT_CAMS = "01"
const val REASON_SUCCESS = "000"

enum class ErrorReason(
        private val feature: String,
        private val reason: String
) {
    INVALID_INPUT_DATA("0001", "001"),
    INVALID_CARD_APPLY_NO("0001", "002"),
    ;

    fun toReason() = "${COMPONENT_CAMS}_${feature}_${reason}"
}