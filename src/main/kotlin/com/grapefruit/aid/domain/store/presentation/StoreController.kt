package com.grapefruit.aid.domain.store.presentation

import com.grapefruit.aid.domain.store.presentation.dto.request.CreateStoreReqDto
import com.grapefruit.aid.domain.store.presentation.dto.response.GetAllMyStoreResDto
import com.grapefruit.aid.domain.store.service.CreateStoreService
import com.grapefruit.aid.domain.store.service.GetAllMyStoreService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/store")
class StoreController(
    private val createStoreService: CreateStoreService,
    private val getAllMyStoreService: GetAllMyStoreService
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
}