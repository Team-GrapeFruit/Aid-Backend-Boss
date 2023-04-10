package com.grapefruit.aid.domain.user.service

interface TokenRefreshService {
    fun execute(refreshToken: String)
}