package com.grapefruit.aid.domain.store.service.impl

import com.grapefruit.aid.domain.store.presentation.dto.request.CreateStoreReqDto
import com.grapefruit.aid.domain.store.service.CreateStoreService
import com.grapefruit.aid.domain.user.repository.UserRepository
import com.grapefruit.aid.domain.user.util.UserUtil

class CreateStoreServiceImpl(
    private val userUtil: UserUtil,
    private val userRepository: UserRepository
): CreateStoreService {
    override fun execute(createStoreReqDto: CreateStoreReqDto) {
        val user = userUtil.currentUser()

    }
}