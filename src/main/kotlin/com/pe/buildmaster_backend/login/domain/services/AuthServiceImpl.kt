package com.pe.buildmaster_backend.login.domain.services

import com.pe.buildmaster_backend.login.domain.model.valueobjects.Profile
import com.pe.buildmaster_backend.login.domain.model.valueobjects.Credential
import com.pe.buildmaster_backend.login.domain.model.valueobjects.Name
import com.pe.buildmaster_backend.login.domain.model.valueobjects.Role
import com.pe.buildmaster_backend.login.domain.model.aggregates.User
import com.pe.buildmaster_backend.login.domain.model.entities.UserEntity
import com.pe.buildmaster_backend.login.domain.security.JwtProvider
import com.pe.buildmaster_backend.login.infrastructure.persistence.jpa.repositories.UserRepository
import com.pe.buildmaster_backend.login.interfaces.rest.mappers.UserMapper.toDomain
import org.springframework.stereotype.Service

@Service
class AuthServiceImpl(
    private val userRepository: UserRepository,
    private val tokenProvider: JwtProvider
) : AuthService {

    override fun login(email: String, password: String): String {
        val usuarioEntity = userRepository.findByEmail(email)
            ?: throw IllegalArgumentException("Usuario no encontrado")

        val usuario = usuarioEntity.toDomain() // Convert UserEntity to User

        if (!usuario.verifyCredentials(password)) {
            throw IllegalArgumentException("Credenciales inválidas")
        }

        return tokenProvider.generarToken(usuario.id.toString(), usuario.profile.name.value, usuario) // Pass 'usuario' as the 'user' parameter
    }

    override fun registrar(email: String, password: String, name: String): User {
        val credenciales = Credential(email, password)
        val perfil = Profile(Name(name))
        val nuevoUsuario = User(profile = perfil, credential = credenciales, role = Role.USER) // Use Role.USER
        val userEntity = UserEntity(
            id = nuevoUsuario.id,
            email = credenciales.email,
            passwordHash = credenciales.getHashPassword(), // Updated parameter
            name = perfil.name.value,
            role = nuevoUsuario.role, // Convert Role to String
            biografy = perfil.biografy, // Added biografy
            fotoUrl = perfil.fotoUrl // Added fotoUrl
        )
        userRepository.save(userEntity)
        return nuevoUsuario
    }
}