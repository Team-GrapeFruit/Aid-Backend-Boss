package com.grapefruit.aid.domain.user.repository

import com.grapefruit.aid.domain.user.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, String> {
}