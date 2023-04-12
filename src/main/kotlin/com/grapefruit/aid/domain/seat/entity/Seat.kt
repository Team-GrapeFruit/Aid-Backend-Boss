package com.grapefruit.aid.domain.seat.entity

import com.grapefruit.aid.domain.seat.presentation.dto.request.CreateSeatReqDto
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
    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "store_id", nullable = false)
    val store: Store
): BaseIdEntity() {
    constructor(createSeatReqDto: CreateSeatReqDto, store: Store): this(
        seatNum = createSeatReqDto.seatNum,
        customerNum = createSeatReqDto.customerNum,
        enabled = false,
        store = store
    )
    fun updateEnableState(): Seat {
        val updatedSeat = Seat(
            seatNum = this.seatNum,
            customerNum = this.customerNum,
            enabled = true,
            store = this.store
        )
        updatedSeat.id = this.id
        return updatedSeat
    }

    fun updateDisableState(): Seat {
        val updatedSeat = Seat(
            seatNum = this.seatNum,
            customerNum = this.customerNum,
            enabled = false,
            store = this.store
        )
        updatedSeat.id = this.id
        return updatedSeat
    }
}