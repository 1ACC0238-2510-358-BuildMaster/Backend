package com.pe.buildmaster_backend.login.infrastructure.persistence.jpa.repositories

import com.pe.buildmaster_backend.login.domain.model.aggregates.User
import com.pe.buildmaster_backend.login.domain.model.entities.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository: JpaRepository<UserEntity, UUID> {
    fun findByEmail(email: String): User?
    fun save(usuario: User): User
    fun findByName(name: String): User?
}