package com.grapefruit.aid.global.exception.handler

import com.grapefruit.aid.global.exception.ErrorCode
import com.grapefruit.aid.global.exception.ErrorResponse
import com.grapefruit.aid.global.exception.exceptions.BasicException
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.servlet.http.HttpServletRequest

@RestControllerAdvice
class GlobalExceptionHandler {
    private val log = LoggerFactory.getLogger(this.javaClass.simpleName)

    @ExceptionHandler(BasicException::class)
    fun globalExceptionHandler(request: HttpServletRequest, e: BasicException): ResponseEntity<ErrorResponse> {
        log.error(e.errorCode.message)
        log.error(request.requestURI)
        val errorResponse = ErrorResponse(e.errorCode)
        return ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.valueOf(e.errorCode.status))
    }
}