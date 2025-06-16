package com.pe.buildmaster_backend.login.domain.model.commands

import java.util.UUID

data class RegisterUserCommand(
    val id: UUID = UUID.randomUUID(),
    val email: String,
    val password: String,
    val name: String
)