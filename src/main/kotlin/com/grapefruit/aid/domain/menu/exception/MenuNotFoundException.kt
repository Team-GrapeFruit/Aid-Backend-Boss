package com.grapefruit.aid.domain.menu.exception

import com.grapefruit.aid.global.exception.ErrorCode
import com.grapefruit.aid.global.exception.exceptions.BasicException

class MenuNotFoundException: BasicException(ErrorCode.MENU_NOT_FOUND) {
}