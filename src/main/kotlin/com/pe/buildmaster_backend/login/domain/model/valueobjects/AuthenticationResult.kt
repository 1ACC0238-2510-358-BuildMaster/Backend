package com.pe.buildmaster_backend.login.domain.model.valueobjects

data class AuthenticationResult(
    val token: String,
    val role: Role
)