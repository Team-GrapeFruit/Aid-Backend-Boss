package com.grapefruit.aid.domain.user.exception

import com.grapefruit.aid.global.exception.ErrorCode
import com.grapefruit.aid.global.exception.exceptions.BasicException

class PasswordMismatchException: BasicException(ErrorCode.PASSWORD_MISMATCH) {
}