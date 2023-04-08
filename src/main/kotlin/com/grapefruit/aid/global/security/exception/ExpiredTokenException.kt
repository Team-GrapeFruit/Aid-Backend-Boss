package com.grapefruit.aid.global.security.exception

import com.grapefruit.aid.global.exception.ErrorCode
import com.grapefruit.aid.global.exception.exceptions.BasicException

class ExpiredTokenException: BasicException(ErrorCode.EXPIRED_TOKEN) {
}