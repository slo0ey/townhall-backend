package com.slo0ey.townhall.domain.common

import org.hibernate.proxy.HibernateProxy
import org.springframework.data.domain.Persistable
import java.io.Serializable
import java.util.Objects

abstract class BaseEntity<T: Serializable>: Persistable<T> {
    abstract var id: T

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
        else (obj as BaseEntity<*>).id
    }
}