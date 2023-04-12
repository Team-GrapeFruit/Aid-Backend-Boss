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
import org.springframework.web.bind.annotation.*
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

    @PatchMapping("/{store_id}")
    fun modifyStore(@PathVariable("store_id") storeId: Long, @RequestBody @Valid modifyStoreReqDto: ModifyStoreReqDto): ResponseEntity<Void> {
        modifyStoreService.execute(storeId ,modifyStoreReqDto)
        return ResponseEntity.noContent().build()
    }

    @DeleteMapping("/{store_id}")
    fun deleteStore(@PathVariable("store_id") storeId: Long): ResponseEntity<Void> {
        deleteStoreService.execute(storeId)
        return ResponseEntity.noContent().build()
    }
}