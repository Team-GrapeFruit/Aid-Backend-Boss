package com.grapefruit.aid.domain.order.entity

import com.grapefruit.aid.domain.menu.entity.Menu
import com.grapefruit.aid.domain.seat.entity.Seat
import com.grapefruit.aid.global.entity.BaseIdEntity
import javax.persistence.*

@Entity
class Order(
    @Column(name = "quantity", nullable = false)
    val quantity: Long,
    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "seat_id", nullable = false)
    val seat: Seat,
    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "menu_id", nullable = false)
    val menu: Menu,
): BaseIdEntity()