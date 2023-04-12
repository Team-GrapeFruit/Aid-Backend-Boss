package com.grapefruit.aid.domain.menu.service.impl

import com.grapefruit.aid.domain.menu.entity.Menu
import com.grapefruit.aid.domain.menu.presentation.dto.request.CreateMenuReqDto
import com.grapefruit.aid.domain.menu.repository.MenuRepository
import com.grapefruit.aid.domain.menu.service.CreateMenuService
import com.grapefruit.aid.domain.store.exception.StoreNotFoundException
import com.grapefruit.aid.domain.store.repository.StoreRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(rollbackFor = [Exception::class])
class CreateMenuServiceImpl(
    private val storeRepository: StoreRepository,
    private val menuRepository: MenuRepository
): CreateMenuService {
    override fun execute(storeId: Long, createMenuReqDto: CreateMenuReqDto) {
        val store = storeRepository.findByIdOrNull(storeId) ?: throw StoreNotFoundException()
        menuRepository.save(Menu(createMenuReqDto, store))
    }
}