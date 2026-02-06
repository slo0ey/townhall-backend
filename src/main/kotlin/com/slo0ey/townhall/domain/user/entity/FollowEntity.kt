package com.slo0ey.townhall.domain.user.entity

import com.slo0ey.townhall.domain.common.BaseEntity
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
    name = "follows",
    uniqueConstraints = [
        UniqueConstraint(name = "uk_follows_follower_following", columnNames = ["follower_id", "following_id"])
    ],
    indexes = [
        Index(name = "idx_follows_follower_id", columnList = "follower_id"),
        Index(name = "idx_follows_following_id", columnList = "following_id")
    ]
)
class Follow(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "follower_id", nullable = false, foreignKey = ForeignKey(name = "fk_follows_follower"))
    val follower: UserEntity,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "following_id", nullable = false, foreignKey = ForeignKey(name = "fk_follows_following"))
    val following: UserEntity,
): BaseEntity() {
    init {
        require(follower.id != following.id) { "자기 자신을 팔로우할 수 없습니다." }
    }
}