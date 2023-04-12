package com.grapefruit.aid.domain.menu.service

import com.grapefruit.aid.domain.menu.presentation.dto.request.CreateMenuReqDto

interface CreateMenuService {
    fun execute(storeId: Long, createMenuReqDto: CreateMenuReqDto)
}