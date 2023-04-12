package com.grapefruit.aid.domain.image.presentation

import com.grapefruit.aid.domain.image.presentation.dto.response.UploadImageResDto
import com.grapefruit.aid.domain.image.service.UploadImageService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/image")
class ImageController(
    private val uploadImageService: UploadImageService
) {
    @PostMapping
    fun uploadImage(@RequestPart("image") file: MultipartFile): ResponseEntity<UploadImageResDto> {
        val result = uploadImageService.execute(file)
        return ResponseEntity.ok(result)
    }
}