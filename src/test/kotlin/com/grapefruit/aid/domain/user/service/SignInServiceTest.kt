package com.grapefruit.aid.domain.user.service

import com.grapefruit.aid.domain.user.entity.RefreshToken
import com.grapefruit.aid.domain.user.entity.User
import com.grapefruit.aid.domain.user.exception.PasswordMismatchException
import com.grapefruit.aid.domain.user.presentation.dto.request.SignInReqDto
import com.grapefruit.aid.domain.user.presentation.dto.response.SignInResDto
import com.grapefruit.aid.domain.user.repository.RefreshTokenRepository
import com.grapefruit.aid.domain.user.repository.UserRepository
import com.grapefruit.aid.global.security.jwt.TokenProvider
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.time.ZonedDateTime

@ExtendWith(SpringExtension::class)
@SpringBootTest
@AutoConfigureMockMvc
class SignInServiceTest: BehaviorSpec({
    val userRepository = mockk<UserRepository>()
    val tokenProvider = mockk<TokenProvider>()
    val passwordEncoder = mockk<PasswordEncoder>(relaxed = true)
    val refreshTokenRepository = mockk<RefreshTokenRepository>()

    given("회원정보가 들어왔을 때") {
        val id = "asdf1234"
        val password = "qwer5678"
        val encodedPassword = "encodedPassword"
        val signInReqDto = SignInReqDto(
            id = id,
            password = password
        )
        val mockAccessToken = "access"
        val mockRefreshToken = "refresh"
        val mockExpiresAt = ZonedDateTime.now().plusSeconds(TokenProvider.ACCESS_EXP)


        val user = User(
            id = id,
            password = encodedPassword,
            name = "홍길동"
        )


        every { userRepository.findByIdOrNull(signInReqDto.id) } returns user
        every { passwordEncoder.matches(signInReqDto.password, encodedPassword) } returns true
        every { tokenProvider.generateAccessToken(signInReqDto.id) } returns mockAccessToken
        every { tokenProvider.generateRefreshToken(signInReqDto.id) } returns mockRefreshToken

        every { tokenProvider.accessExpiredTime } returns mockExpiresAt

        `when`("서비스 호출 시") {
            if(!passwordEncoder.matches(signInReqDto.password, encodedPassword))
                throw PasswordMismatchException()

            val (accessToken, refreshToken) = tokenProvider.run {
                generateAccessToken(signInReqDto.id) to generateRefreshToken(signInReqDto.id)
            }
            val expiresAt = tokenProvider.accessExpiredTime
            val refreshTokenEntity = RefreshToken(user.id, refreshToken)
            every { refreshTokenRepository.save(refreshTokenEntity) } returns refreshTokenEntity

            refreshTokenRepository.save(refreshTokenEntity)
            val result = SignInResDto(accessToken, refreshToken, expiresAt)

            then("refreshTokenRepository save 한번 실행되어야 함") {
                verify(exactly = 1) { refreshTokenRepository.save(refreshTokenEntity) }
            }

        }
    }
})
