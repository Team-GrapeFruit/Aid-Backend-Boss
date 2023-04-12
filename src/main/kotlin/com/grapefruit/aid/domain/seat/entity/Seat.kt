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

    fun update(modifySeatReqDto: ModifySeatReqDto): Seat {
        val seat = Seat(
            seatNum = modifySeatReqDto.seatNum,
            customerNum = modifySeatReqDto.customerNum,
            enabled = this.enabled,
            store = this.store
        )
        seat.id = this.id
        return seat
    }
}