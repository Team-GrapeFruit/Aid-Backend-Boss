package com.grapefruit.aid.domain.user.presentation

import com.grapefruit.aid.domain.user.presentation.dto.response.TokenRefreshResDto
import com.grapefruit.aid.domain.user.service.SignInService
import com.grapefruit.aid.domain.user.service.SignUpService
import com.grapefruit.aid.domain.user.service.TokenRefreshService
import com.grapefruit.aid.domain.user.service.UserDeleteService
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.time.ZonedDateTime

@ExtendWith(SpringExtension::class)
@WebMvcTest
@AutoConfigureMockMvc
class TokenRefreshControllerTest: BehaviorSpec({
    val signUpService = mockk<SignUpService>()
    val signInService = mockk<SignInService>()
    val tokenRefreshService = mockk<TokenRefreshService>()
    val userDeleteService = mockk<UserDeleteService>()
    val userController = UserController(signUpService, signInService, tokenRefreshService, userDeleteService)

    Given("토큰 리프레시 시도 시") {
        `when`("요청이 왔을 때") {
            every { tokenRefreshService.execute("refreshToken") } returns TokenRefreshResDto(
                accessToken = "newAccessToken",
                refreshToken = "newRefreshToken",
                expiresAt = ZonedDateTime.now()
            )
            val response = userController.tokenRefresh("refreshToken")
            then("서비스가 한번은 실행되어야 함") {
                verify(exactly = 1) { tokenRefreshService.execute("refreshToken") }
            }
            then("response status should be ok") {
                response.statusCode shouldBe HttpStatus.OK
            }
        }
    }
})