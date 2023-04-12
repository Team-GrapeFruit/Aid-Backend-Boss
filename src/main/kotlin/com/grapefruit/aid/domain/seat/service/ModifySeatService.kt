package com.grapefruit.aid.domain.seat.service

import com.grapefruit.aid.domain.seat.presentation.dto.request.ModifySeatReqDto

interface ModifySeatService {
    fun execute(seatId: Long, modifySeatReqDto: ModifySeatReqDto)
}