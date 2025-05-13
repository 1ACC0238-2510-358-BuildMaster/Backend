package com.pe.buildmaster_backend.login.infrastructure.persistence.jpa.repositories

import com.pe.buildmaster_backend.login.domain.model.valueobjects.User

interface UserRepository {
    fun findByEmail(email: String): User?
    fun save(usuario: User): User
}