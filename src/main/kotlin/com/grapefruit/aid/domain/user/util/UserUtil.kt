package com.grapefruit.aid.domain.user.util

import com.grapefruit.aid.domain.user.entity.User
import com.grapefruit.aid.domain.user.exception.UserNotFoundException
import com.grapefruit.aid.domain.user.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Component
class UserUtil(
    private val userRepository: UserRepository
) {
    fun currentUser(): User {
        val id = SecurityContextHolder.getContext().authentication.name
        return userRepository.findByIdOrNull(id) ?: throw UserNotFoundException()
    }
}