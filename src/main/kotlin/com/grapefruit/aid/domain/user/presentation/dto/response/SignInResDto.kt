package com.grapefruit.aid.domain.user.presentation.dto.response

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.ZonedDateTime

data class SignInResDto(
    val accessToken: String,
    val refreshToken: String,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    val expiresAt: ZonedDateTime
)