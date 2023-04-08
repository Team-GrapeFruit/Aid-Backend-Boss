package com.grapefruit.aid.domain.user.presentation

import com.grapefruit.aid.domain.user.presentation.dto.request.SignInReqDto
import com.grapefruit.aid.domain.user.presentation.dto.request.SignUpReqDto
import com.grapefruit.aid.domain.user.presentation.dto.response.SignInResDto
import com.grapefruit.aid.domain.user.service.SignInService
import com.grapefruit.aid.domain.user.service.SignUpService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/auth")
class UserController(
    private val signUpService: SignUpService,
    private val signInService: SignInService
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
}