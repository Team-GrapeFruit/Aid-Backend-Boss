package com.grapefruit.aid.domain.user.presentation

import com.grapefruit.aid.domain.user.service.SignInService
import com.grapefruit.aid.domain.user.service.SignUpService
import com.grapefruit.aid.domain.user.service.TokenRefreshService
import com.grapefruit.aid.domain.user.service.UserDeleteService
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@WebMvcTest
@AutoConfigureMockMvc
class UserDeleteControllerTest: BehaviorSpec({
    val signUpService = mockk<SignUpService>()
    val signInService = mockk<SignInService>()
    val tokenRefreshService = mockk<TokenRefreshService>()
    val userDeleteService = mockk<UserDeleteService>()
    val userController = UserController(signUpService, signInService, tokenRefreshService, userDeleteService)

    Given("유저 삭제 시도 시") {
        `when`("요청이 왔을 때") {
            every { userDeleteService.execute("qwer5678") } returns Unit
            val response = userController.userDelete("qwer5678")

            then("서비스가 한 번은 실행되어야 함") {
                verify(exactly = 1) { userDeleteService.execute(("qwer5678")) }
            }
            then("response status should be ok") {
                response.statusCode shouldBe HttpStatus.NO_CONTENT
            }
        }
    }
})