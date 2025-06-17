package com.pe.buildmaster_backend.login.domain.model.commands

data class LoginCommand(
    val email: String,
    val password: String,
    val requiredRole: String = null.toString()
)