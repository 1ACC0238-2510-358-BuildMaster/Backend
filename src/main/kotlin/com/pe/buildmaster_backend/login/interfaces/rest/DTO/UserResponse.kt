package com.pe.buildmaster_backend.login.interfaces.rest.DTO

import java.util.*

data class UserResponse(
    val id: UUID,
    val email: String,
    val name: String,
    val biografy: String?,
    val fotoUrl: String?,
    val role: String
)