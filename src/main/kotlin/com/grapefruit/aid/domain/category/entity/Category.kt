package com.grapefruit.aid.domain.category.entity

import com.grapefruit.aid.domain.category.presentation.dto.request.CreateCategoryReqDto
import com.grapefruit.aid.domain.store.entity.Store
import com.grapefruit.aid.global.entity.BaseIdEntity
import javax.persistence.*

@Entity
class Category(
    @Column(name = "category_name")
    val name: String,
    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "store_id", nullable = false)
    val store: Store
): BaseIdEntity() {
    constructor(createCategoryReqDto: CreateCategoryReqDto, store: Store): this(
        name = createCategoryReqDto.name,
        store = store
    )
}