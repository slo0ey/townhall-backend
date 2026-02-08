package com.slo0ey.townhall.domain.post.entity

import com.slo0ey.townhall.domain.common.BaseEntity
import com.slo0ey.townhall.domain.user.entity.UserEntity
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.ForeignKey
import jakarta.persistence.Index
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint

@Entity
@Table(
    name = "post_likes",
    uniqueConstraints = [
        UniqueConstraint(name = "uk_post_likes_post_user", columnNames = ["post_id", "user_id"])
    ],
    indexes = [
        Index(name = "idx_post_likes_post_id", columnList = "post_id"),
        Index(name = "idx_post_likes_user_id", columnList = "user_id")
    ]
)
class PostLikeEntity(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false, foreignKey = ForeignKey(name = "fk_post_likes_post"))
    val post: PostEntity,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, foreignKey = ForeignKey(name = "fk_post_likes_user"))
    val user: UserEntity
): BaseEntity()