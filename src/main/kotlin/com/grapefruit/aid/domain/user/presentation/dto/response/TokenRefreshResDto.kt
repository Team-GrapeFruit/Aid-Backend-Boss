package com.grapefruit.aid.domain.user.presentation.dto.response

import java.time.ZonedDateTime

data class TokenRefreshResDto(
    val accessToken: String,
    val refreshToken: String,
    val expiresAt: ZonedDateTime
)