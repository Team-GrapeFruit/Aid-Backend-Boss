package com.grapefruit.aid.domain.user.service.impl

import com.grapefruit.aid.domain.user.entity.RefreshToken
import com.grapefruit.aid.domain.user.repository.RefreshTokenRepository
import com.grapefruit.aid.domain.user.service.TokenRefreshService
import com.grapefruit.aid.global.security.exception.ExpiredTokenException
import com.grapefruit.aid.global.security.exception.InvalidTokenException
import com.grapefruit.aid.global.security.jwt.TokenProvider

class TokenRefreshServiceImpl(
    private val refreshTokenRepository: RefreshTokenRepository,
    private val tokenProvider: TokenProvider
): TokenRefreshService {
    override fun execute(requestRefreshToken: String) {
        val requestRefreshToken: String = tokenProvider.parseToken(requestRefreshToken) ?: throw InvalidTokenException()
        val id = tokenProvider.exactEmailFromRefreshToken(requestRefreshToken)
        val existingRefreshToken = refreshTokenRepository.findByToken(requestRefreshToken) ?: throw ExpiredTokenException()
        if(existingRefreshToken.token != requestRefreshToken)
            throw InvalidTokenException()
        val (access, refresh) = tokenProvider.run {
            generateAccessToken(id) to generateAccessToken(id)
        }

        val refreshToken = RefreshToken(id, refresh)
        refreshTokenRepository.save(refreshToken)


    }
}