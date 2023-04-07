package com.grapefruit.aid.domain.user.exception

import com.grapefruit.aid.global.exception.ErrorCode
import com.grapefruit.aid.global.exception.exceptions.BasicException

class UserDuplicateException: BasicException(ErrorCode.DUPLICATE_USER_ID) {
}