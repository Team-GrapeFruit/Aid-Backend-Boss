package com.grapefruit.aid.domain.category.presentation.dto.response

import com.grapefruit.aid.domain.category.entity.Category
import com.grapefruit.aid.domain.store.entity.Store

data class CategoryListResDto(
    val categoryList: List<SingleCategoryResDto>
) {
    data class SingleCategoryResDto(
        val categoryId: Long,
        val name: String
    ) {
        constructor(category: Category): this(
            categoryId = category.id,
            name = category.name
        )
    }
}