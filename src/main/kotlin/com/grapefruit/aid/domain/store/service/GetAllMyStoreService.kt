package com.grapefruit.aid.domain.store.service

import com.grapefruit.aid.domain.store.presentation.dto.response.GetAllMyStoreResDto

interface GetAllMyStoreService {
    fun execute(): GetAllMyStoreResDto
}