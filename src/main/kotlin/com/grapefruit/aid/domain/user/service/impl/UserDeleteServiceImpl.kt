package com.grapefruit.aid.domain.user.service.impl

import com.grapefruit.aid.domain.user.entity.RefreshToken
import com.grapefruit.aid.domain.user.exception.PasswordMismatchException
import com.grapefruit.aid.domain.user.exception.RefreshTokenNotFoundException
import com.grapefruit.aid.domain.user.repository.RefreshTokenRepository
import com.grapefruit.aid.domain.user.repository.UserRepository
import com.grapefruit.aid.domain.user.service.UserDeleteService
import com.grapefruit.aid.domain.user.util.UserUtil
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(rollbackFor = [Exception::class])
class UserDeleteServiceImpl(
    private val userRepository: UserRepository,
    private val refreshTokenRepository: RefreshTokenRepository,
    private val passwordEncoder: PasswordEncoder,
    private val userUtil: UserUtil
): UserDeleteService {
    override fun execute(password: String) {
        val user = userUtil.currentUser()

        if(passwordEncoder.matches(passwordEncoder.encode(password), user.password))
            throw PasswordMismatchException()

        val refreshToken: RefreshToken = refreshTokenRepository.findByIdOrNull(user.id) ?: throw RefreshTokenNotFoundException()

        refreshTokenRepository.delete(refreshToken)
        userRepository.delete(user)
    }
}