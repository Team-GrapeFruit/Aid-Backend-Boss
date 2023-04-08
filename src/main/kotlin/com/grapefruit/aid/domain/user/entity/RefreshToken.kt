package com.grapefruit.aid.domain.user.entity

import com.grapefruit.aid.global.security.jwt.TokenProvider
import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.index.Indexed

@RedisHash(value = "refreshToken", timeToLive = TokenProvider.REFRESH_EXP)
class RefreshToken (
    @Id
    @Indexed
    val userId: String,
    @Indexed
    val token: String,
)