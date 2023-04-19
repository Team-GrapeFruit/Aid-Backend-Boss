package com.grapefruit.aid.domain.category.service.impl

import com.grapefruit.aid.domain.category.entity.Category
import com.grapefruit.aid.domain.category.presentation.dto.response.CategoryListResDto
import com.grapefruit.aid.domain.category.repository.CategoryRepository
import com.grapefruit.aid.domain.category.service.GetCategoryListService
import com.grapefruit.aid.domain.store.exception.StoreNotFoundException
import com.grapefruit.aid.domain.store.repository.StoreRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class GetCategoryListServiceImpl(
    private val storeRepository: StoreRepository,
    private val categoryRepository: CategoryRepository
): GetCategoryListService {
    override fun execute(storeId: Long): CategoryListResDto {
        val store = storeRepository.findByIdOrNull(storeId) ?: throw StoreNotFoundException()
        val categories = categoryRepository.findAllByStore(store)
        return CategoryListResDto(categories
            .map { CategoryListResDto.SingleCategoryResDto(it) })
    }
}