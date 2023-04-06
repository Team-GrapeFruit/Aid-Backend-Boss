package com.grapefruit.aid.domain.user.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class User (
    @Id
    val id: String,
    @Column(nullable = false)
    val password: String,
    @Column(nullable = false)
    val name: String
)