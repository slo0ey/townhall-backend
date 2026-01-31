package com.slo0ey.townhall.domain.common

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.io.Serializable
import java.time.LocalDateTime

abstract class AuditableEntity<T: Serializable>: BaseEntity<T>() {
    @CreatedDate
    var createdAt: LocalDateTime? = null

    @LastModifiedDate
    var updatedAt: LocalDateTime? = null

    // Persistable의 isNew를 createdAt 필드를 통해 판단
    override fun isNew() = createdAt == null
}