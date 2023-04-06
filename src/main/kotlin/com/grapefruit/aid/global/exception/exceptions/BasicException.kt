package com.grapefruit.aid.global.exception.exceptions

import com.grapefruit.aid.global.exception.ErrorCode

open class BasicException(val errorCode: ErrorCode): RuntimeException()