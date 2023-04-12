package com.grapefruit.aid.domain.menu.presentation

import com.grapefruit.aid.domain.menu.presentation.dto.request.CreateMenuReqDto
import com.grapefruit.aid.domain.menu.presentation.dto.request.ModifyMenuReqDto
import com.grapefruit.aid.domain.menu.service.CreateMenuService
import com.grapefruit.aid.domain.menu.service.ModifyMenuService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/menu")
class MenuController(
    private val createMenuService: CreateMenuService,
    private val modifyMenuService: ModifyMenuService
) {
    @PostMapping("/{store_id}")
    fun createMenu(@PathVariable("store_id") storeId: Long, @RequestBody @Valid createMenuReqDto: CreateMenuReqDto): ResponseEntity<Void> {
        createMenuService.execute(storeId, createMenuReqDto)
        return ResponseEntity(HttpStatus.CREATED)
    }

    @PatchMapping("/{menu_id}")
    fun modifyMenu(@PathVariable("menu_id") menuId: Long, @RequestBody @Valid modifyMenuReqDto: ModifyMenuReqDto): ResponseEntity<Void> {
        modifyMenuService.execute(menuId, modifyMenuReqDto)
        return ResponseEntity.noContent().build()
    }

}