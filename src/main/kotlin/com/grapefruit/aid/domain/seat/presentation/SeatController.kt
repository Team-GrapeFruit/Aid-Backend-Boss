package com.grapefruit.aid.domain.seat.presentation

import com.grapefruit.aid.domain.seat.presentation.dto.request.CreateSeatReqDto
import com.grapefruit.aid.domain.seat.presentation.dto.request.ModifySeatReqDto
import com.grapefruit.aid.domain.seat.service.CreateSeatService
import com.grapefruit.aid.domain.seat.service.ModifySeatService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/seat")
class SeatController(
    private val createSeatService: CreateSeatService,
    private val modifySeatService: ModifySeatService
) {
    @PostMapping("/{store_id}")
    fun createSeat(@PathVariable("store_id") storeId: Long, @RequestBody @Valid createSeatReqDto: CreateSeatReqDto): ResponseEntity<Void> {
        createSeatService.execute(storeId, createSeatReqDto)
        return ResponseEntity(HttpStatus.CREATED)
    }

    @PatchMapping("/{seat_id}")
    fun modifySeat(@PathVariable("seat_id") seatId: Long, @RequestBody @Valid modifySeatReqDto: ModifySeatReqDto): ResponseEntity<Void> {
        modifySeatService.execute(seatId, modifySeatReqDto)
        return ResponseEntity.noContent().build()
    }
}