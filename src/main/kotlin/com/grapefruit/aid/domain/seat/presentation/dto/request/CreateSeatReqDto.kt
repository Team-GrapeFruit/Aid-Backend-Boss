package com.grapefruit.aid.domain.seat.presentation.dto.request

data class CreateSeatReqDto(
    val seatNum: Long,
    val customerNum: Long,
    val locationX: Float,
    val locationY: Float
)