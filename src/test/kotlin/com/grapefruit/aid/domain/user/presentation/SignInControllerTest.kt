package com.grapefruit.aid.domain.user.presentation

import com.grapefruit.aid.domain.user.presentation.dto.request.SignInReqDto
import com.grapefruit.aid.domain.user.presentation.dto.response.SignInResDto
import com.grapefruit.aid.domain.user.service.SignInService
import com.grapefruit.aid.domain.user.service.SignUpService
import com.grapefruit.aid.domain.user.service.TokenRefreshService
import com.grapefruit.aid.domain.user.service.UserDeleteService
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.context.web.WebAppConfiguration
import java.time.ZonedDateTime

@ExtendWith(SpringExtension::class)
@SpringBootTest
@AutoConfigureMockMvc
class SignInControllerTest: BehaviorSpec({
    val signUpService = mockk<SignUpService>()
    val signInService = mockk<SignInService>()
    val tokenRefreshService = mockk<TokenRefreshService>()
    val userDeleteService = mockk<UserDeleteService>()
    val userController = UserController(signUpService, signInService, tokenRefreshService, userDeleteService)

    given("로그인 시도 시") {
        val signInReqDto = SignInReqDto(
            id = "asdf1234",
            password = "qwer5678"
        )
        `when`("요청을 받았을 때") {
            every { signInService.execute(signInReqDto) } returns SignInResDto(
                accessToken = "accessToken",
                refreshToken = "refreshToken",
                expiresAt = ZonedDateTime.now()
            )
            val result = userController.signIn(signInReqDto)
            then("테스트가 한번은 실행되어야 함") {
                verify(exactly = 1) { signInService.execute(signInReqDto) }
            }
            then("response status should be ok") {
                result.statusCode shouldBe HttpStatus.OK
            }
        }
    }
})