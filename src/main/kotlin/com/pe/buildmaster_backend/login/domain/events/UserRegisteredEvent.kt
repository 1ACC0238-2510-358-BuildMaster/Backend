package com.pe.buildmaster_backend.login.domain.events

import java.util.UUID

data class UserRegisteredEvent(
    val userId: UUID,
    val email: String,
    val name: String
)