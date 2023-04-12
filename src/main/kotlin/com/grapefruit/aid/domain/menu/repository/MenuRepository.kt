package com.grapefruit.aid.domain.menu.repository

import com.grapefruit.aid.domain.menu.entity.Menu
import org.springframework.data.jpa.repository.JpaRepository

interface MenuRepository: JpaRepository<Menu, Long> {
}