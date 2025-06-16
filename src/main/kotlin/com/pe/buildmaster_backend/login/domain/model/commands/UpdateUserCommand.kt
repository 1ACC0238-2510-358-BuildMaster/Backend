package com.pe.buildmaster_backend.login.domain.model.commands

import java.util.UUID

data class UpdateUserCommand(
    val userId: UUID,
    val biografy: String?,
    val fotoUrl: String?
)