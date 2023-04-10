package com.grapefruit.aid.domain.user.service

import com.grapefruit.aid.domain.user.presentation.dto.response.TokenRefreshResDto

interface TokenRefreshService {
    fun execute(refreshToken: String): TokenRefreshResDto
}