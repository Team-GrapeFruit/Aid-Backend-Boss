package com.grapefruit.aid.domain.menu.entity

import com.grapefruit.aid.domain.menu.presentation.dto.request.CreateMenuReqDto
import com.grapefruit.aid.domain.menu.presentation.dto.request.ModifyMenuReqDto
import com.grapefruit.aid.domain.menu.service.ModifyMenuService
import com.grapefruit.aid.domain.store.entity.Store
import com.grapefruit.aid.global.entity.BaseIdEntity
import javax.persistence.*
import javax.validation.constraints.Size

@Entity
class Menu(
    @Column(name = "menu_name", nullable = false)
    @field:Size(max = 20)
    val menuName: String,
    @Column(name = "cost", nullable = false)
    val cost: Long,
    @Column(name = "description", nullable = false)
    @field:Size(max = 80)
    val description: String,
    @Column(name = "menu_img_url", nullable = true)
    val menuImgURL: String? = null,
    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "store_id", nullable = false)
    val store: Store
):BaseIdEntity() {
    constructor(createMenuReqDto: CreateMenuReqDto, store: Store): this(
        menuName = createMenuReqDto.menuName,
        cost = createMenuReqDto.cost,
        description = createMenuReqDto.description,
        menuImgURL = createMenuReqDto.menuImgUrl,
        store = store
    )

    fun update(modifyMenuReqDto: ModifyMenuReqDto): Menu {
        val menu = Menu(
            menuName = modifyMenuReqDto.menuName,
            cost = modifyMenuReqDto.cost,
            description = modifyMenuReqDto.description,
            menuImgURL = modifyMenuReqDto.menuImgUrl,
            store = this.store
        )
        menu.id = this.id
        return menu
    }
}