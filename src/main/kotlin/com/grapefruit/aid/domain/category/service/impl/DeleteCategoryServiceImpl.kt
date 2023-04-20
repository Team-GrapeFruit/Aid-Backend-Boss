package com.grapefruit.aid.domain.category.service.impl

import com.grapefruit.aid.domain.category.exception.CategoryNotFoundException
import com.grapefruit.aid.domain.category.repository.CategoryRepository
import com.grapefruit.aid.domain.category.service.DeleteCategoryService
import com.grapefruit.aid.domain.store.exception.UserMismatchException
import com.grapefruit.aid.domain.user.util.UserUtil
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(rollbackFor = [Exception::class])
class DeleteCategoryServiceImpl(
    private val categoryRepository: CategoryRepository,
    private val userUtil: UserUtil
): DeleteCategoryService {
    override fun execute(categoryId: Long) {
        val category = categoryRepository.findByIdOrNull(categoryId) ?: throw CategoryNotFoundException()
        if(category.store.user != userUtil.currentUser())
            throw UserMismatchException()
        categoryRepository.delete(category)
    }

}