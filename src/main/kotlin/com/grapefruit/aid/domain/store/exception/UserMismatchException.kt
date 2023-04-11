package com.grapefruit.aid.domain.store.exception

import com.grapefruit.aid.global.exception.ErrorCode
import com.grapefruit.aid.global.exception.exceptions.BasicException

class UserMismatchException: BasicException(ErrorCode.USER_MISMATCH) {
}