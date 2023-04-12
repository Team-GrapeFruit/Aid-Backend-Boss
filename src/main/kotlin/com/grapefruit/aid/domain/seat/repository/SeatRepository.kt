package com.grapefruit.aid.domain.seat.repository

import com.grapefruit.aid.domain.seat.entity.Seat
import org.springframework.data.jpa.repository.JpaRepository

interface SeatRepository: JpaRepository<Seat, Long> {
}