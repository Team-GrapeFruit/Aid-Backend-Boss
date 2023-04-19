package com.grapefruit.aid.domain.category.presentation

import com.grapefruit.aid.domain.category.presentation.dto.request.CreateCategoryReqDto
import com.grapefruit.aid.domain.category.service.CreateCategoryService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/category")
class CategoryController(
    private val createCategoryService: CreateCategoryService
) {
    @PostMapping("/{store_id}")
    fun createCategory(@PathVariable("store_id") storeId: Long, @RequestBody @Valid createCategoryReqDto: CreateCategoryReqDto):ResponseEntity<Void> {
        createCategoryService.execute(storeId, createCategoryReqDto)
        return ResponseEntity(HttpStatus.CREATED)
    }
}