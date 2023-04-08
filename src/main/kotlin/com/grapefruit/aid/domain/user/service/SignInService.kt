package com.grapefruit.aid.domain.user.service

import com.grapefruit.aid.domain.user.presentation.dto.request.SignInReqDto
import com.grapefruit.aid.domain.user.presentation.dto.response.SignInResDto

interface SignInService {
    fun execute(signInReqDto: SignInReqDto): SignInResDto
}