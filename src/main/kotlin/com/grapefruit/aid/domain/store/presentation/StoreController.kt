package com.grapefruit.aid.domain.store.presentation

import com.grapefruit.aid.domain.store.presentation.dto.request.CreateStoreReqDto
import com.grapefruit.aid.domain.store.presentation.dto.request.ModifyStoreReqDto
import com.grapefruit.aid.domain.store.presentation.dto.response.GetAllMyStoreResDto
import com.grapefruit.aid.domain.store.service.CreateStoreService
import com.grapefruit.aid.domain.store.service.DeleteStoreService
import com.grapefruit.aid.domain.store.service.GetAllMyStoreService
import com.grapefruit.aid.domain.store.service.ModifyStoreService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/store")
class StoreController(
    private val createStoreService: CreateStoreService,
    private val getAllMyStoreService: GetAllMyStoreService,
    private val modifyStoreService: ModifyStoreService,
    private val deleteStoreService: DeleteStoreService
) {
    @PostMapping
    fun createStore(@RequestBody @Valid createStoreReqDto: CreateStoreReqDto): ResponseEntity<Void> {
        createStoreService.execute(createStoreReqDto)
        return ResponseEntity(HttpStatus.CREATED)
    }

    @GetMapping
    fun getAllMyStore(): ResponseEntity<GetAllMyStoreResDto> {
        val result = getAllMyStoreService.execute()
        return ResponseEntity.ok(result)
    }

    @PatchMapping
    fun modifyStore(modifyStoreReqDto: ModifyStoreReqDto): ResponseEntity<Void> {
        modifyStoreService.execute(modifyStoreReqDto)
        return ResponseEntity.noContent().build()
    }

    @DeleteMapping("/{store_id}")
    fun deleteStore(@PathVariable("store_id") storeId: Long): ResponseEntity<Void> {
        deleteStoreService.execute(storeId)
        return ResponseEntity.noContent().build()
    }
}