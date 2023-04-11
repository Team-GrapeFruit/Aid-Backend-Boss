package com.grapefruit.aid.domain.store.service.impl

import com.grapefruit.aid.domain.store.presentation.dto.response.GetAllMyStoreResDto
import com.grapefruit.aid.domain.store.repository.StoreRepository
import com.grapefruit.aid.domain.store.service.GetAllMyStoreService
import com.grapefruit.aid.domain.user.util.UserUtil
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class GetAllMyStoreServiceImpl(
    private val storeRepository: StoreRepository,
    private val userUtil: UserUtil
): GetAllMyStoreService {
    override fun execute(): GetAllMyStoreResDto =
        GetAllMyStoreResDto(storeRepository.findAllByUser(userUtil.currentUser())
            .map { GetAllMyStoreResDto.SingleMyStoreResDto(it) })
}