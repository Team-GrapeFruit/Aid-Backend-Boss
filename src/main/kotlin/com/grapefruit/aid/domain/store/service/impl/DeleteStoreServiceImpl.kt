package com.grapefruit.aid.domain.store.service.impl

import com.grapefruit.aid.domain.store.exception.UserMismatchException
import com.grapefruit.aid.domain.store.repository.StoreRepository
import com.grapefruit.aid.domain.store.service.DeleteStoreService
import com.grapefruit.aid.domain.user.exception.UserNotFoundException
import com.grapefruit.aid.domain.user.util.UserUtil
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(rollbackFor = [Exception::class])
class DeleteStoreServiceImpl(
    private val storeRepository: StoreRepository,
    private val userUtil: UserUtil
): DeleteStoreService{
    override fun execute(storeId: Long) {
        val store = storeRepository.findByIdOrNull(storeId) ?: throw UserNotFoundException()
        if(userUtil.currentUser() != store.user)
            throw UserMismatchException()

        storeRepository.delete(store)
    }
}