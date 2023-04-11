package com.grapefruit.aid.domain.store.service.impl

import com.grapefruit.aid.domain.store.entity.Store
import com.grapefruit.aid.domain.store.exception.UserMismatchException
import com.grapefruit.aid.domain.store.presentation.dto.request.ModifyStoreReqDto
import com.grapefruit.aid.domain.store.repository.StoreRepository
import com.grapefruit.aid.domain.store.service.ModifyStoreService
import com.grapefruit.aid.domain.user.exception.UserNotFoundException
import com.grapefruit.aid.domain.user.util.UserUtil
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(rollbackFor = [Exception::class])
class ModifyStoreServiceImpl(
    private val storeRepository: StoreRepository,
    private val userUtil: UserUtil
): ModifyStoreService {
    override fun execute(modifyStoreReqDto: ModifyStoreReqDto) {
        val store = storeRepository.findByIdOrNull(modifyStoreReqDto.storeId) ?: throw UserNotFoundException()
        if(userUtil.currentUser() != store.user)
            throw UserMismatchException()

        storeRepository.save(store.update(modifyStoreReqDto))
    }
}