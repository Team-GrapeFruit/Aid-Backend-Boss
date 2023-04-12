package com.grapefruit.aid.domain.seat.exception

import com.grapefruit.aid.global.exception.ErrorCode
import com.grapefruit.aid.global.exception.exceptions.BasicException

class SeatNotFoundException: BasicException(ErrorCode.SEAT_NOT_FOUND) {
}