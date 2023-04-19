package com.grapefruit.aid.domain.category.service.impl

import com.grapefruit.aid.domain.category.entity.Category
import com.grapefruit.aid.domain.category.presentation.dto.request.CreateCategoryReqDto
import com.grapefruit.aid.domain.category.repository.CategoryRepository
import com.grapefruit.aid.domain.category.service.CreateCategoryService
import com.grapefruit.aid.domain.store.exception.StoreNotFoundException
import com.grapefruit.aid.domain.store.repository.StoreRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(rollbackFor = [Exception::class])
class CreateCategoryServiceImpl(
    private val storeRepository: StoreRepository,
    private val categoryRepository: CategoryRepository
): CreateCategoryService {
    override fun execute(storeId: Long, createCategoryReqDto: CreateCategoryReqDto) {
        val store = storeRepository.findByIdOrNull(storeId) ?: throw StoreNotFoundException()
        categoryRepository.save(Category(createCategoryReqDto, store))
    }
}