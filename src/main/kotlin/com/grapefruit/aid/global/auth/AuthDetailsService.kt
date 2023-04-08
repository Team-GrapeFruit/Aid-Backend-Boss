package com.grapefruit.aid.global.auth

import com.grapefruit.aid.domain.user.entity.User
import com.grapefruit.aid.domain.user.exception.UserNotFoundException
import com.grapefruit.aid.domain.user.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class AuthDetailsService(
    private val userRepository: UserRepository
): UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        val user: User = userRepository.findByIdOrNull(username) ?: throw UserNotFoundException()
        return AuthDetails(user)
    }

}