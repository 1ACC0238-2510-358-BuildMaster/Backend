package com.pe.buildmaster_backend.login.domain.services

import com.pe.buildmaster_backend.login.domain.model.valueobjects.Profile
import com.pe.buildmaster_backend.login.domain.model.valueobjects.Credential
import com.pe.buildmaster_backend.login.domain.model.valueobjects.Name
import com.pe.buildmaster_backend.login.domain.model.valueobjects.User
import com.pe.buildmaster_backend.login.domain.security.JwtProvider
import com.pe.buildmaster_backend.login.infrastructure.persistence.jpa.repositories.UserRepository
import org.springframework.stereotype.Service

@Service
class AuthServiceImpl(
    private val userRepository: UserRepository,
    private val tokenProvider: JwtProvider
) : AuthService {

    override fun login(email: String, password: String): String {
        val usuario = userRepository.findByEmail(email)
            ?: throw IllegalArgumentException("Usuario no encontrado")

        if (!usuario.verifyCredentials(password)) {
            throw IllegalArgumentException("Credenciales inválidas")
        }

        return tokenProvider.generarToken(usuario.id.toString(), usuario.profile.name.value)
    }

    override fun registrar(email: String, password: String, name: String): User {
        val credenciales = Credential.constructor(email, password)
        val perfil = Profile(Name(name))
        val nuevoUsuario = User(profile = perfil, credential = credenciales)
        return userRepository.save(nuevoUsuario)
    }
}