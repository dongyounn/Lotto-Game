package com.kotlin.boot.exception

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionResolver {
    private val log = LoggerFactory.getLogger(this::class.java)


    @ExceptionHandler(BadRequestException::class)
    protected fun handleBadRequestException(e: BadRequestException): ResponseEntity<ErrorResponse> {
        log.error("error: ", e)
        return ResponseEntity(e.errorResponse, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(ExternalServiceException::class)
    protected fun handleExternalServiceException(e: ExternalServiceException): ResponseEntity<ErrorResponse> {
        log.error("error: ", e)
        return ResponseEntity(e.errorResponse, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(InternalProcessException::class)
    protected fun handleInternalProcessException(e: InternalProcessException): ResponseEntity<ErrorResponse> {
        log.error("error: ", e)
        return ResponseEntity(e.errorResponse, HttpStatus.BAD_REQUEST)
    }

}