package com.grapefruit.aid.domain.user.service

import com.grapefruit.aid.domain.user.entity.User
import com.grapefruit.aid.domain.user.presentation.dto.request.SignUpReqDto
import com.grapefruit.aid.domain.user.repository.UserRepository
import com.grapefruit.aid.domain.user.service.impl.SignUpServiceImpl
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest
@AutoConfigureMockMvc
class SignUpServiceTest: BehaviorSpec({
    val userRepository = mockk<UserRepository>()
    val passwordEncoder = mockk<PasswordEncoder>(relaxed = true)

    val signUpService = SignUpServiceImpl(userRepository, passwordEncoder)

    given("회원정보가 들어왔을 때") {
        val id = "asdf1234"
        val name = "홍길동"
        val password = "qwer5678"

        val signUpReqDto = SignUpReqDto(
            id = id,
            name = name,
            password = password
        )

        val encodedPassword = "encoded password"
        every { passwordEncoder.encode(signUpReqDto.password) } returns encodedPassword

        `when`("비밀번호가 암호화 되었을 때") {
            val user = User(signUpReqDto.id, signUpReqDto.name, encodedPassword)

            every { userRepository.save(user) } returns user

            signUpService.execute(signUpReqDto)

            then("userRepository save 한번은 실행되어야 함") {
                verify(exactly = 1) { userRepository.save(user) }
            }
        }
    }
})