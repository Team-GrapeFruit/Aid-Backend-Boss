package com.grapefruit.aid.domain.category.presentation.dto.response

data class CategoryListResDto(
    val categoryList: List<SingleCategoryResDto>
) {
    data class SingleCategoryResDto(
        val categoryId: Long,
        val name: String
    )
}