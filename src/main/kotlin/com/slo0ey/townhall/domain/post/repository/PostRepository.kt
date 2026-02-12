package com.slo0ey.townhall.domain.post.repository

import com.slo0ey.townhall.domain.post.entity.PostEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface PostRepository: JpaRepository<PostEntity, Long> {
    @Query("SELECT p FROM PostEntity p WHERE p.isDeleted = false")
    fun findAllActive(pageable: Pageable): Page<PostEntity>

    @Query("SELECT p FROM PostEntity p WHERE p.id = :id AND p.isDeleted = false")
    fun fundActiveById(id: Long): PostEntity?

    @Query("SELECT p FROM PostEntity p WHERE p.user.id = :userId AND p.isDeleted = false")
    fun findAllByUserId(userId: Long, pageable: Pageable): Page<PostEntity>

    @Query("""
        SELECT p FROM PostEntity p
        LEFT JOIN PostLikeEntity pl ON p.id = pl.post.id
        WHERE p.isDeleted = false
        GROUP BY p.id
        ORDER BY COUNT(pl.id) DESC, p.createdAt DESC
    """)
    fun findPopularPosts(pageable: Pageable): Page<PostEntity>
}