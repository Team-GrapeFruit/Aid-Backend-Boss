package com.grapefruit.aid.domain.store.service

import com.grapefruit.aid.domain.store.presentation.dto.request.ModifyStoreReqDto

interface ModifyStoreService {
    fun execute(storeId: Long, modifyStoreReqDto: ModifyStoreReqDto)
}