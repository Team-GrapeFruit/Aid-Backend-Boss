package com.grapefruit.aid.domain.store.exception

import com.grapefruit.aid.global.exception.ErrorCode
import com.grapefruit.aid.global.exception.exceptions.BasicException

class StoreNotFoundException: BasicException(ErrorCode.STORE_NOT_FOUND) {
}