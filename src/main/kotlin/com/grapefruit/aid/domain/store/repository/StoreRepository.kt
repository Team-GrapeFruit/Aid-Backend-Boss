package com.grapefruit.aid.domain.store.repository

import com.grapefruit.aid.domain.store.entity.Store
import com.grapefruit.aid.domain.user.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface StoreRepository: JpaRepository<Store, Long> {
    fun findAllByUser(user: User): List<Store>
}