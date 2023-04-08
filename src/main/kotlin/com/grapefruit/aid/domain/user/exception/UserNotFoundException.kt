package com.grapefruit.aid.domain.user.exception

import com.grapefruit.aid.global.exception.ErrorCode
import com.grapefruit.aid.global.exception.exceptions.BasicException

class UserNotFoundException: BasicException(ErrorCode.USER_NOT_FOUND) {
}