package com.grapefruit.aid.domain.user.presentation.dto.request

import javax.validation.constraints.NotBlank

class SignUpReqDto(
    @field:NotBlank
    val id: String,
    @field:NotBlank
    val name: String,
    @field:NotBlank
    val password: String
)