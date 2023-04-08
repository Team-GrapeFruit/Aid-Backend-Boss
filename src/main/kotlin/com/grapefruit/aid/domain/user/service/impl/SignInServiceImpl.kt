package com.grapefruit.aid.domain.user.service.impl

import com.grapefruit.aid.domain.user.entity.RefreshToken
import com.grapefruit.aid.domain.user.entity.User
import com.grapefruit.aid.domain.user.exception.PasswordMismatchException
import com.grapefruit.aid.domain.user.exception.UserNotFoundException
import com.grapefruit.aid.domain.user.presentation.dto.request.SignInReqDto
import com.grapefruit.aid.domain.user.presentation.dto.response.SignInResDto
import com.grapefruit.aid.domain.user.repository.RefreshTokenRepository
import com.grapefruit.aid.domain.user.repository.UserRepository
import com.grapefruit.aid.domain.user.service.SignInService
import com.grapefruit.aid.global.security.jwt.TokenProvider
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class SignInServiceImpl(
    private val userRepository: UserRepository,
    private val tokenProvider: TokenProvider,
    private val passwordEncoder: PasswordEncoder,
    private val refreshTokenRepository: RefreshTokenRepository
): SignInService {
    override fun execute(signInReqDto: SignInReqDto): SignInResDto {
        val user: User = userRepository.findByIdOrNull(signInReqDto.id) ?: throw UserNotFoundException()

        if(!passwordEncoder.matches(signInReqDto.password, user.password))
            throw PasswordMismatchException()

        val (access, refresh) = tokenProvider.run {
            generateAccessToken(signInReqDto.id) to generateRefreshToken(signInReqDto.id)}

        val expiresAt = tokenProvider.accessExpiredTime

        refreshTokenRepository.save(RefreshToken(user.id, refresh))
        return SignInResDto(access, refresh, expiresAt)
    }
}