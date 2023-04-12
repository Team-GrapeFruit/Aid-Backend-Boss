package com.grapefruit.aid.domain.image.service.impl

import com.grapefruit.aid.domain.image.presentation.dto.response.UploadImageResDto
import com.grapefruit.aid.domain.image.service.UploadImageService
import com.grapefruit.aid.global.aws.S3Util
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class UploadImageServiceImpl(
    private val s3Util: S3Util,
): UploadImageService {
    override fun execute(file: MultipartFile): UploadImageResDto =
        UploadImageResDto(s3Util.upload(file))
}