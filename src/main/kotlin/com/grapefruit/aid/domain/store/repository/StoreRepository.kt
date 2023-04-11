package com.grapefruit.aid.domain.store.repository

import com.grapefruit.aid.domain.store.entity.Store
import org.springframework.data.jpa.repository.JpaRepository

interface StoreRepository: JpaRepository<Store, Long> {
}