package com.pe.buildmaster_backend.login.infrastructure.persistence.jpa.repositories

import com.pe.buildmaster_backend.login.domain.model.entities.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface UserRepository : JpaRepository<UserEntity, UUID> {
    // Return JPA entities instead of domain aggregates
    fun findByEmail(email: String): UserEntity?
    fun findByName(name: String): UserEntity?
}