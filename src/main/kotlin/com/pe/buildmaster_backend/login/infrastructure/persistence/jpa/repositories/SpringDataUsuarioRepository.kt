package com.pe.buildmaster_backend.login.infrastructure.persistence.jpa.repositories

import com.pe.buildmaster_backend.login.domain.model.entities.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface SpringDataUsuarioRepository : JpaRepository<UserEntity, UUID> {
    fun findByEmail(email: String): UserEntity?
}