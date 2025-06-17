package com.pe.buildmaster_backend.login.domain.model.entities

import com.pe.buildmaster_backend.login.domain.model.valueobjects.Role
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Id
import java.util.*

@Entity
data class UserEntity(
    @Id val id: UUID,
    val email: String,
    val passwordHash: String,
    var name: String,
    var biografy: String?,
    var fotoUrl: String?,
    @Enumerated(EnumType.STRING)
    var role: Role
)