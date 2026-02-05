package com.slo0ey.townhall.domain.post.entity

import com.slo0ey.townhall.domain.common.BaseEntity
import com.slo0ey.townhall.domain.user.entity.UserEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.ForeignKey
import jakarta.persistence.Index
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import org.hibernate.annotations.ColumnDefault

@Entity
@Table(
    name = "posts",
    indexes = [
        Index(name = "idx_posts_user_id", columnList = "user_id"),
        Index(name = "idx_posts_created_at", columnList = "created_at DESC"),
        Index(name = "idx_posts_is_deleted", columnList = "is_deleted")
    ]
)
class PostEntity(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", foreignKey = ForeignKey(name = "fk_posts_user"))
    var user: UserEntity?,

    @Column(name = "title", nullable = false, length = 200)
    var title: String,

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    var content: String,

    @Column(name = "view_count", nullable = false)
    @ColumnDefault(value = "0")
    var viewCount: Int,

    @Column(name = "is_deleted", nullable = false)
    @ColumnDefault(value = "false")
    var isDeleted: Boolean,
): BaseEntity() {
    fun update(title: String, content: String) {
        this.title = title
        this.content = content
    }

    fun increaseViewCount() {
        this.viewCount++
    }

    fun delete() {
        this.isDeleted = true
    }

    fun isOwnedBy(userId: Long): Boolean {
        return this.user?.id == userId
    }
}