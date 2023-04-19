package com.grapefruit.aid.domain.category.service

import com.grapefruit.aid.domain.category.presentation.dto.response.CategoryListResDto

interface GetCategoryListService {
    fun execute(storeId: Long): CategoryListResDto
}