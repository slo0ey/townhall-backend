package com.slo0ey.townhall.domain.post.entity

import com.slo0ey.townhall.domain.common.BaseEntity
import com.slo0ey.townhall.domain.user.entity.UserEntity
import jakarta.persistence.*
import org.hibernate.annotations.ColumnDefault

@Entity
@Table(
    name = "comments",
    indexes = [
        Index(name = "idx_comments_post_id", columnList = "post_id"),
        Index(name = "idx_comments_user_id", columnList = "user_id"),
        Index(name = "idx_comments_created_at", columnList = "created_at"),
        Index(name = "idx_comments_is_deleted", columnList = "is_deleted")
    ]
)
class CommentEntity(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false, foreignKey = ForeignKey(name = "fk_comments_post"))
    var post: PostEntity,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", foreignKey = ForeignKey(name = "fk_comments_user"))
    var user: UserEntity?,

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    var content: String,

    @Column(name = "is_deleted", nullable = false)
    @ColumnDefault(value = "false")
    var isDeleted: Boolean,
): BaseEntity() {
    fun update(content: String) {
        this.content = content
    }

    fun delete() {
        this.isDeleted = true
    }

    fun isOwnedBy(userId: Long): Boolean {
        return this.user?.id == userId
    }
}