package com.grapefruit.aid.domain.seat.entity

import com.grapefruit.aid.domain.seat.presentation.dto.request.CreateSeatReqDto
import com.grapefruit.aid.domain.seat.presentation.dto.request.ModifySeatReqDto
import com.grapefruit.aid.domain.store.entity.Store
import com.grapefruit.aid.global.entity.BaseIdEntity
import javax.persistence.*

@Entity
class Seat (
    @Column(name = "seat_num", nullable = false)
    val seatNum: Long,
    @Column(name = "customer_num", nullable = false)
    val customerNum: Long,
    @Column(nullable = false)
    val enabled: Boolean,
    @Column(name = "location_x", nullable = false)
    val locationX: Float,
    @Column(name = "location_y", nullable = false)
    val locationY: Float,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    val store: Store
): BaseIdEntity() {
    constructor(createSeatReqDto: CreateSeatReqDto, store: Store): this(
        seatNum = createSeatReqDto.seatNum,
        customerNum = createSeatReqDto.customerNum,
        locationX = createSeatReqDto.locationX,
        locationY = createSeatReqDto.locationY,
        enabled = false,
        store = store
    )

    fun update(modifySeatReqDto: ModifySeatReqDto): Seat {
        val seat = Seat(
            seatNum = modifySeatReqDto.seatNum,
            customerNum = modifySeatReqDto.customerNum,
            enabled = this.enabled,
            locationX = modifySeatReqDto.locationX,
            locationY = modifySeatReqDto.locationY,
            store = this.store
        )
        seat.id = this.id
        return seat
    }
}