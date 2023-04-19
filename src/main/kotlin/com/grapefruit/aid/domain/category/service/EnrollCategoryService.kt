package com.grapefruit.aid.domain.category.service

interface EnrollCategoryService {
    fun execute(menuId: Long, categoryId: Long)
}