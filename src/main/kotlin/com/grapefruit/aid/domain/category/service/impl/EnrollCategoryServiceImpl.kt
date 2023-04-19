package com.grapefruit.aid.domain.category.service.impl

import com.grapefruit.aid.domain.category.entity.MenuAndCategory
import com.grapefruit.aid.domain.category.exception.CategoryNotFoundException
import com.grapefruit.aid.domain.category.repository.CategoryRepository
import com.grapefruit.aid.domain.category.repository.MenuCategoryRepository
import com.grapefruit.aid.domain.category.service.EnrollCategoryService
import com.grapefruit.aid.domain.menu.exception.MenuNotFoundException
import com.grapefruit.aid.domain.menu.repository.MenuRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(rollbackFor = [Exception::class])
class EnrollCategoryServiceImpl(
    private val menuCategoryRepository: MenuCategoryRepository,
    private val menuRepository: MenuRepository,
    private val categoryRepository: CategoryRepository
): EnrollCategoryService {
    override fun execute(menuId: Long, categoryId: Long) {
        val menu = menuRepository.findByIdOrNull(menuId) ?: throw MenuNotFoundException()
        val category = categoryRepository.findByIdOrNull(categoryId) ?: throw CategoryNotFoundException()
        menuCategoryRepository.save(MenuAndCategory(menu, category))
    }
}