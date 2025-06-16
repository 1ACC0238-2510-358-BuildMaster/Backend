package com.pe.buildmaster_backend.login.domain.events

import java.util.UUID

data class UserUpdatedEvent(
    val userId: UUID,
    val biografy: String?,
    val fotoUrl: String?
)