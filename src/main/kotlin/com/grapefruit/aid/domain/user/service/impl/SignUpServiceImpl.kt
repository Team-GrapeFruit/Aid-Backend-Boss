package com.grapefruit.aid.domain.user.service.impl

import com.grapefruit.aid.domain.user.entity.User
import com.grapefruit.aid.domain.user.repository.UserRepository
import com.grapefruit.aid.domain.user.exception.UserDuplicateException
import com.grapefruit.aid.domain.user.presentation.dto.request.SignUpReqDto
import com.grapefruit.aid.domain.user.service.SignUpService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(rollbackFor = [Exception::class])
class SignUpServiceImpl(
    private val userRepository: UserRepository
): SignUpService {
    override fun execute(signUpReqDto: SignUpReqDto) {
        if(userRepository.existsById(signUpReqDto.id))
            throw UserDuplicateException()
        val user: User = User(signUpReqDto)
        userRepository.save(user)
    }
}