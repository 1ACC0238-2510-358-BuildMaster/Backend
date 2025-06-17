package com.pe.buildmaster_backend.login.interfaces.rest.DTO

import com.pe.buildmaster_backend.login.domain.model.valueobjects.Role

data class LoginResponse(
    val token: String,
    val role: Role
)