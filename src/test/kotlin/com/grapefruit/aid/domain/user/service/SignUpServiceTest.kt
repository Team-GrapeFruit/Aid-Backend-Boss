package com.grapefruit.aid.domain.user.service

import com.grapefruit.aid.domain.user.entity.User
import com.grapefruit.aid.domain.user.exception.UserDuplicateException
import com.grapefruit.aid.domain.user.presentation.dto.request.SignUpReqDto
import com.grapefruit.aid.domain.user.repository.UserRepository
import com.grapefruit.aid.domain.user.service.impl.SignUpServiceImpl
import io.kotest.core.spec.style.BehaviorSpec
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
        every { userRepository.existsById(signUpReqDto.id) } returns false

        `when`("서비스 호출 시") {
            if(userRepository.existsById(signUpReqDto.id))
                throw UserDuplicateException()
            val encodingPassword = passwordEncoder.encode(signUpReqDto.password)
            val user = User(
                id = signUpReqDto.id,
                name = signUpReqDto.name,
                password = encodingPassword)

            every { userRepository.save(user) } returns user

            userRepository.save(user)

            then("userRepository save 한번 실행되어야 함") {
                verify(exactly = 1) { userRepository.save(user) }
            }
        }
    }
})