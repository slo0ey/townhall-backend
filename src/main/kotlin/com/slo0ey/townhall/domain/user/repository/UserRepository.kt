package com.slo0ey.townhall.domain.user.repository

import com.slo0ey.townhall.domain.user.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<UserEntity, Long> {
    fun findByUsername(username: String): UserEntity?

    fun findByEmail(email: String): UserEntity?

    fun existsByUsername(username: String): Boolean

    fun existsByEmail(email: String): Boolean
}