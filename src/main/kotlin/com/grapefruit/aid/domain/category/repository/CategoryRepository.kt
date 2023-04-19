package com.grapefruit.aid.domain.category.repository

import com.grapefruit.aid.domain.category.entity.Category
import org.springframework.data.jpa.repository.JpaRepository

interface CategoryRepository: JpaRepository<Category, Long> {
}