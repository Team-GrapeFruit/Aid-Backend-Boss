package com.grapefruit.aid.domain.category.presentation

import com.grapefruit.aid.domain.category.presentation.dto.request.CreateCategoryReqDto
import com.grapefruit.aid.domain.category.service.CreateCategoryService
import com.grapefruit.aid.domain.category.service.DeleteCategoryService
import com.grapefruit.aid.domain.category.service.EnrollCategoryService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/category")
class CategoryController(
    private val createCategoryService: CreateCategoryService,
    private val deleteCategoryService: DeleteCategoryService,
    private val enrollCategoryService: EnrollCategoryService
) {
    @PostMapping("/{store_id}")
    fun createCategory(@PathVariable("store_id") storeId: Long, @RequestBody @Valid createCategoryReqDto: CreateCategoryReqDto):ResponseEntity<Void> {
        createCategoryService.execute(storeId, createCategoryReqDto)
        return ResponseEntity(HttpStatus.CREATED)
    }

    @DeleteMapping("/{category_id}")
    fun deleteCategory(@PathVariable("category_id") categoryId: Long): ResponseEntity<Void> {
        deleteCategoryService.execute(categoryId)
        return ResponseEntity.ok().build()
    }

    @PostMapping
    fun enrollCategory(@RequestParam menuId: Long, @RequestParam categoryId: Long): ResponseEntity<Void> {
        enrollCategoryService.execute(menuId,categoryId)
        return ResponseEntity(HttpStatus.CREATED)
    }
}