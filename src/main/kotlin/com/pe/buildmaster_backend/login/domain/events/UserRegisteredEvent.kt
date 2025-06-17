package com.pe.buildmaster_backend.login.domain.events

import com.pe.buildmaster_backend.login.domain.model.valueobjects.Role
import java.util.UUID

data class UserRegisteredEvent(
    val userId: UUID,
    val email: String,
    val name: String,
    val role: Role
)