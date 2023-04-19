package com.grapefruit.aid.domain.category.exception

import com.grapefruit.aid.global.exception.ErrorCode
import com.grapefruit.aid.global.exception.exceptions.BasicException

class CategoryNotFoundException: BasicException(ErrorCode.CATEGORY_NOT_FOUND) {
}