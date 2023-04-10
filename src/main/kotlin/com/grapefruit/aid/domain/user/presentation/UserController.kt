package com.grapefruit.aid.domain.user.presentation

import com.grapefruit.aid.domain.user.presentation.dto.request.SignInReqDto
import com.grapefruit.aid.domain.user.presentation.dto.request.SignUpReqDto
import com.grapefruit.aid.domain.user.presentation.dto.response.SignInResDto
import com.grapefruit.aid.domain.user.presentation.dto.response.TokenRefreshResDto
import com.grapefruit.aid.domain.user.service.SignInService
import com.grapefruit.aid.domain.user.service.SignUpService
import com.grapefruit.aid.domain.user.service.TokenRefreshService
import com.grapefruit.aid.domain.user.service.UserDeleteService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/auth")
class UserController(
    private val signUpService: SignUpService,
    private val signInService: SignInService,
    private val tokenRefreshService: TokenRefreshService,
    private val userDeleteService: UserDeleteService
) {
    @PostMapping("/signup")
    fun signUp(@RequestBody @Valid signUpReqDto: SignUpReqDto): ResponseEntity<Void> {
        signUpService.execute(signUpReqDto)
        return ResponseEntity(HttpStatus.CREATED)
    }

    @PostMapping
    fun signIn(@RequestBody @Valid signInReqDto: SignInReqDto): ResponseEntity<SignInResDto> {
        val result = signInService.execute(signInReqDto)
        return ResponseEntity.ok(result)
    }

    @PatchMapping
    fun tokenRefresh(@RequestHeader("RefreshToken") refreshToken: String): ResponseEntity<TokenRefreshResDto> {
        val result = tokenRefreshService.execute(refreshToken)
        return ResponseEntity.ok(result)
    }

    @DeleteMapping
    fun userDelete(@RequestParam password: String): ResponseEntity<Void> {
        userDeleteService.execute(password)
        return ResponseEntity.noContent().build()
    }
}