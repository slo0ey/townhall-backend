package com.slo0ey.townhall.domain.auth.repository

import com.slo0ey.townhall.domain.auth.entity.RefreshTokenEntity
import org.springframework.data.jpa.repository.JpaRepository

interface RefreshTokenRepository: JpaRepository<RefreshTokenEntity, Long> {
    fun findByToken(token: String): RefreshTokenEntity?

    fun findByUserId(userId: Long): RefreshTokenEntity?

    fun deleteByUserId(userId: Long)
}