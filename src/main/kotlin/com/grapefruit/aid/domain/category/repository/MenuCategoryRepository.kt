package com.grapefruit.aid.domain.category.repository

import com.grapefruit.aid.domain.category.entity.MenuAndCategory
import org.springframework.data.jpa.repository.JpaRepository

interface MenuCategoryRepository: JpaRepository<MenuAndCategory, Long> {
}