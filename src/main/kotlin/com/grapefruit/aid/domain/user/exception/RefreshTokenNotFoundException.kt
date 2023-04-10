package com.grapefruit.aid.domain.user.exception

import com.grapefruit.aid.global.exception.ErrorCode
import com.grapefruit.aid.global.exception.exceptions.BasicException

class RefreshTokenNotFoundException: BasicException(ErrorCode.REFRESH_TOKEN_NOT_FOUND) {
}