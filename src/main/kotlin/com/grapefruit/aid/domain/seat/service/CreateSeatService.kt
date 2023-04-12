package com.grapefruit.aid.domain.seat.service

import com.grapefruit.aid.domain.seat.presentation.dto.request.CreateSeatReqDto

interface CreateSeatService {
    fun execute(storeId: Long, createSeatReqDto: CreateSeatReqDto)
}