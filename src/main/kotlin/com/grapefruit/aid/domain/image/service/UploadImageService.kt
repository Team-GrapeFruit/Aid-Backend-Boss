package com.grapefruit.aid.domain.image.service

import com.grapefruit.aid.domain.image.presentation.dto.response.UploadImageResDto
import org.springframework.web.multipart.MultipartFile

interface UploadImageService {
    fun execute(file: MultipartFile): UploadImageResDto
}