package com.grapefruit.aid.domain.user.presentation

import com.grapefruit.aid.domain.user.presentation.dto.request.SignUpReqDto
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
    private val signUpService: SignUpService
) {
    @PostMapping("/signup")
    fun signUp(@RequestBody @Valid signUpReqDto: SignUpReqDto): ResponseEntity<Void> {
        signUpService.execute(signUpReqDto)
        return ResponseEntity(HttpStatus.CREATED)
    }
}