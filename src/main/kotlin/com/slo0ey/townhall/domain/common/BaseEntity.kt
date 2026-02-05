package com.slo0ey.townhall.domain.common

import jakarta.persistence.EntityListeners
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import org.hibernate.proxy.HibernateProxy
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.util.Objects

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseEntity: AuditableEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: Long? = null

    override fun getId(): Long? = id

    override fun equals(other: Any?): Boolean {
        if (other == null) return false
        // 같은 클래스인지 비교하기 전에, 먼저 Hibernate 프록시인지 확인
        if (other !is HibernateProxy && other::class != this::class) return false
        return id == getIdentifier(other)
    }

    // 오직 식별자 기반 비교를 위해 id 값으로 hashCode 생성
    override fun hashCode() = Objects.hashCode(id)

    private fun getIdentifier(obj: Any): Any? {
        return if (obj is HibernateProxy) obj.hibernateLazyInitializer.identifier
        else (obj as BaseEntity).id
    }
}