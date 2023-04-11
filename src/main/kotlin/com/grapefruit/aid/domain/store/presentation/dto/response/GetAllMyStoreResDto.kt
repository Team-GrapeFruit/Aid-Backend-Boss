package com.grapefruit.aid.domain.store.presentation.dto.response

import com.grapefruit.aid.domain.store.entity.Store

data class GetAllMyStoreResDto(
    val singleStore: List<SingleMyStoreResDto>
) {
    data class SingleMyStoreResDto(
        val storeId: Long,
        val storeName: String,
        val information: String,
        val storeImgUrl: String?
    ) {
        constructor(store: Store): this(
            storeId = store.id,
            storeName = store.storeName,
            information = store.information,
            storeImgUrl = store.storeImgURL
        )
    }
}