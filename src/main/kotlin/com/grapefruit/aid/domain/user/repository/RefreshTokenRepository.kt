package com.grapefruit.aid.domain.user.repository

import com.grapefruit.aid.domain.user.entity.RefreshToken
import org.springframework.data.repository.CrudRepository

interface RefreshTokenRepository: CrudRepository<RefreshToken, Long> {
}