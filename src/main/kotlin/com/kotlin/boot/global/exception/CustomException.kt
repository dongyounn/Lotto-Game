package com.kotlin.boot.global.exception

open class ApplicationException (
        val errorResponse: ErrorResponse
): RuntimeException() {
    constructor(errorReason: ErrorReason, errorMessage: String?)
            : this(ErrorResponse(errorReason.toReason(), errorMessage))
}

class BadRequestException(errorReason: ErrorReason, errorMessage: String?)
    : ApplicationException(errorReason, errorMessage) {
    companion object {
        fun of(errorReason: ErrorReason, errorMessage: String?) : BadRequestException {
            return BadRequestException(errorReason, errorMessage)
        }
    }
}

class ExternalServiceException(errorReason: ErrorReason, errorMessage: String?)
    : ApplicationException(errorReason, errorMessage) {
    companion object {
        fun of(errorReason: ErrorReason, errorMessage: String?) : ExternalServiceException {
            return ExternalServiceException(errorReason, errorMessage)
        }
    }
}

class InternalProcessException(errorReason: ErrorReason, errorMessage: String?)
    : ApplicationException(errorReason, errorMessage) {
    companion object {
        fun of(errorReason: ErrorReason, errorMessage: String?) : BadRequestException {
            return BadRequestException(errorReason, errorMessage)
        }
    }
}