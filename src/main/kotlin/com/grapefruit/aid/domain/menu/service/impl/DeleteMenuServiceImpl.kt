package com.grapefruit.aid.domain.menu.service.impl

import com.grapefruit.aid.domain.menu.exception.MenuNotFoundException
import com.grapefruit.aid.domain.menu.repository.MenuRepository
import com.grapefruit.aid.domain.menu.service.DeleteMenuService
import com.grapefruit.aid.domain.store.exception.UserMismatchException
import com.grapefruit.aid.domain.user.util.UserUtil
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(rollbackFor = [Exception::class])
class DeleteMenuServiceImpl(
    private val menuRepository: MenuRepository,
    private val userUtil: UserUtil
): DeleteMenuService {
    override fun execute(menuId: Long) {
        val menu = menuRepository.findByIdOrNull(menuId) ?: throw MenuNotFoundException()
        if(userUtil.currentUser() != menu.store.user)
            throw UserMismatchException()

        menuRepository.delete(menu)
    }

}