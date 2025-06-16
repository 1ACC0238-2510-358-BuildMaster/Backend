package com.pe.buildmaster_backend.login.domain.model.aggregates

import org.springframework.security.crypto.bcrypt.BCrypt

data class Credential(
    val email: String,
    private var hashPassword: String
) {
    fun verify(password: String): Boolean {
        return BCrypt.checkpw(password, hashPassword)
    }

    fun getHashPassword(): String = hashPassword

    companion object {
        fun constructor(email: String, passwordPlano: String): Credential {
            val hash = BCrypt.hashpw(passwordPlano, BCrypt.gensalt())
            return Credential(email, hash)
        }
    }
}