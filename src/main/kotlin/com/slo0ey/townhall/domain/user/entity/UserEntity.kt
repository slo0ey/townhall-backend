package com.slo0ey.townhall.domain.user.entity

import com.slo0ey.townhall.domain.common.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Index
import jakarta.persistence.Table

@Entity
@Table(
    name = "users",
    indexes = [
        Index(name = "idx_users_nickname", columnList = "nickname", unique = true),
        Index(name = "idx_users_email", columnList = "email"),
        Index(name = "idx_users_created_at", columnList = "created_at DESC"),
    ]
)
class UserEntity(
    @Column(name = "nickname", nullable = false, length = 30)
    var username: String,

    @Column(name = "email", nullable = false, length = 30)
    var email: String,

    @Column(name = "password", nullable = false)
    var password: String
): BaseEntity()