package com.grapefruit.aid.domain.seat.service.impl

import com.grapefruit.aid.domain.seat.entity.Seat
import com.grapefruit.aid.domain.seat.exception.SeatNotFoundException
import com.grapefruit.aid.domain.seat.presentation.dto.request.CreateSeatReqDto
import com.grapefruit.aid.domain.seat.repository.SeatRepository
import com.grapefruit.aid.domain.seat.service.CreateSeatService
import com.grapefruit.aid.domain.store.repository.StoreRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(rollbackFor = [Exception::class])
class CreateSeatServiceImpl(
    private val seatRepository: SeatRepository,
    private val storeRepository: StoreRepository
): CreateSeatService {
    override fun execute(storeId: Long, createSeatReqDto: CreateSeatReqDto) {
        val store = storeRepository.findByIdOrNull(storeId) ?: throw SeatNotFoundException()
        seatRepository.save(Seat(createSeatReqDto, store))
    }

}