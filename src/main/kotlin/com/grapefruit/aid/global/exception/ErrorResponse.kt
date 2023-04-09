package com.grapefruit.aid.global.exception

class ErrorResponse(errorCode: ErrorCode) {
    private val message: String
    private val status: Int

    init {
        message = errorCode.message
        status = errorCode.status
    }
}