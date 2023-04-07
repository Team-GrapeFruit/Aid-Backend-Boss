package com.grapefruit.aid.domain.user.service

import com.grapefruit.aid.domain.user.presentation.dto.request.SignUpReqDto

interface SignUpService {
    fun execute(signUpReqDto: SignUpReqDto)
}