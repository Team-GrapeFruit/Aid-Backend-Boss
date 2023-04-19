package com.grapefruit.aid.domain.category.repository

import com.grapefruit.aid.domain.category.entity.Category
import com.grapefruit.aid.domain.store.entity.Store
import org.springframework.data.jpa.repository.JpaRepository

interface CategoryRepository: JpaRepository<Category, Long> {
    fun findAllByStore(store: Store): List<Category>
}