package com.grapefruit.aid.domain.user.presentation.dto.request

import javax.validation.constraints.NotBlank

data class SignInReqDto(
    @field:NotBlank
    var id: String,
    @field:NotBlank
    var password: String
)