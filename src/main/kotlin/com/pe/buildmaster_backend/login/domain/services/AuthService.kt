package com.pe.buildmaster_backend.login.domain.services

import com.pe.buildmaster_backend.login.domain.model.valueobjects.User

interface AuthService {
    fun login(email: String, password: String): String
    fun registrar(email: String, password: String, nombre: String): User
}