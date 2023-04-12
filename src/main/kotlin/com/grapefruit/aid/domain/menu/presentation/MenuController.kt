package com.grapefruit.aid.domain.menu.presentation

import com.grapefruit.aid.domain.menu.presentation.dto.request.CreateMenuReqDto
import com.grapefruit.aid.domain.menu.service.CreateMenuService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/menu")
class MenuController(
    private val createMenuService: CreateMenuService
) {
    @PostMapping("/{store_id}")
    fun createMenu(@PathVariable("store_id") storeId: Long, @RequestBody @Valid createMenuReqDto: CreateMenuReqDto): ResponseEntity<Void> {
        createMenuService.execute(storeId, createMenuReqDto)
        return ResponseEntity(HttpStatus.CREATED)
    }
}