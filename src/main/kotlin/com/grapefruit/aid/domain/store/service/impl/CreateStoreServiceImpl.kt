package com.grapefruit.aid.domain.store.service.impl

import com.grapefruit.aid.domain.store.entity.Store
import com.grapefruit.aid.domain.store.presentation.dto.request.CreateStoreReqDto
import com.grapefruit.aid.domain.store.repository.StoreRepository
import com.grapefruit.aid.domain.store.service.CreateStoreService
import com.grapefruit.aid.domain.user.util.UserUtil
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(rollbackFor = [Exception::class])
class CreateStoreServiceImpl(
    private val userUtil: UserUtil,
    private val storeRepository: StoreRepository
): CreateStoreService {
    override fun execute(createStoreReqDto: CreateStoreReqDto) {
        val user = userUtil.currentUser()
        val store = Store(createStoreReqDto, user)
        storeRepository.save(store)
    }
}