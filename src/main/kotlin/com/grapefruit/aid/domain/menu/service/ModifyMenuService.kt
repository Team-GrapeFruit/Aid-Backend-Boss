package com.grapefruit.aid.domain.menu.service

import com.grapefruit.aid.domain.menu.presentation.dto.request.ModifyMenuReqDto

interface ModifyMenuService {
    fun execute(menuId: Long, modifyMenuReqDto: ModifyMenuReqDto)
}