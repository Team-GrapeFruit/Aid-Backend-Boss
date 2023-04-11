package com.grapefruit.aid.domain.store.presentation

import com.grapefruit.aid.domain.store.presentation.dto.request.CreateStoreReqDto
import com.grapefruit.aid.domain.store.service.CreateStoreService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/store")
class StoreController(
    private val createStoreService: CreateStoreService
) {
    @PostMapping
    fun createStore(@RequestBody @Valid createStoreReqDto: CreateStoreReqDto): ResponseEntity<Void> {
        createStoreService.execute(createStoreReqDto)
        return ResponseEntity(HttpStatus.CREATED)
    }
}