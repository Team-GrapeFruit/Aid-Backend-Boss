package com.grapefruit.aid.domain.user.service

interface UserDeleteService {
    fun execute(password: String)
}