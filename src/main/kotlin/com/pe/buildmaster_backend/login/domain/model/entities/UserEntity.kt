package com.pe.buildmaster_backend.login.domain.model.entities

import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.util.*

@Entity
data class UserEntity(
    @Id val id: UUID,
    val email: String,
    val passwordHash: String,
    val name: String,
    val biografy: String?,
    val fotoUrl: String?
)