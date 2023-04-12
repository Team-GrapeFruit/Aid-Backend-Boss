package com.grapefruit.aid.domain.seat.service.impl

import com.grapefruit.aid.domain.seat.entity.Seat
import com.grapefruit.aid.domain.seat.exception.SeatNotFoundException
import com.grapefruit.aid.domain.seat.presentation.dto.request.ModifySeatReqDto
import com.grapefruit.aid.domain.seat.repository.SeatRepository
import com.grapefruit.aid.domain.seat.service.ModifySeatService
import com.grapefruit.aid.domain.store.exception.UserMismatchException
import com.grapefruit.aid.domain.user.util.UserUtil
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(rollbackFor = [Exception::class])
class ModifySeatServiceImpl(
    private val seatRepository: SeatRepository,
    private val userUtil: UserUtil
): ModifySeatService {
    override fun execute(seatId: Long, modifySeatReqDto: ModifySeatReqDto) {
        val seat: Seat = seatRepository.findByIdOrNull(seatId) ?: throw SeatNotFoundException()
        if(userUtil.currentUser() != seat.store.user)
            throw UserMismatchException()
        seatRepository.save(seat.update(modifySeatReqDto))
    }
}