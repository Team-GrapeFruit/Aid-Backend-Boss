package com.grapefruit.aid.domain.category.service

import com.grapefruit.aid.domain.category.presentation.dto.request.CreateCategoryReqDto

interface CreateCategoryService {
    fun execute(storeId: Long, createCategoryReqDto: CreateCategoryReqDto)
}