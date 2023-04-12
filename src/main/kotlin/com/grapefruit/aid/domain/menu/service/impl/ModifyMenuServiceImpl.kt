package com.grapefruit.aid.domain.menu.service.impl

import com.grapefruit.aid.domain.menu.exception.MenuNotFoundException
import com.grapefruit.aid.domain.menu.presentation.dto.request.ModifyMenuReqDto
import com.grapefruit.aid.domain.menu.repository.MenuRepository
import com.grapefruit.aid.domain.menu.service.ModifyMenuService
import com.grapefruit.aid.domain.store.exception.UserMismatchException
import com.grapefruit.aid.domain.user.util.UserUtil
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(rollbackFor = [Exception::class])
class ModifyMenuServiceImpl(
    private val menuRepository: MenuRepository,
    private val userUtil: UserUtil
): ModifyMenuService {
    override fun execute(menuId: Long, modifyMenuReqDto: ModifyMenuReqDto) {
        val menu = menuRepository.findByIdOrNull(menuId) ?: throw MenuNotFoundException()
        if(userUtil.currentUser() != menu.store.user)
            throw UserMismatchException()

        menuRepository.save(menu.update(modifyMenuReqDto))
    }
}