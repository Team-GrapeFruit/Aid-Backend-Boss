package com.grapefruit.aid.domain.store.service

import com.grapefruit.aid.domain.store.presentation.dto.request.CreateStoreReqDto

interface CreateStoreService {
    fun execute(createStoreReqDto: CreateStoreReqDto)
}