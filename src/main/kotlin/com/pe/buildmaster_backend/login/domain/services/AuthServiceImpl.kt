package com.pe.buildmaster_backend.login.domain.services

import com.pe.buildmaster_backend.login.domain.model.valueobjects.Profile
import com.pe.buildmaster_backend.login.domain.model.valueobjects.Credential
import com.pe.buildmaster_backend.login.domain.model.valueobjects.Name
import com.pe.buildmaster_backend.login.domain.model.valueobjects.User
import com.pe.buildmaster_backend.login.infrastructure.persistence.jpa.repositories.UserRepository
import com.pe.buildmaster_backend.login.interfaces.rest.resources.JwtProvider
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
<<<<<<< Updated upstream
        val nuevoUsuario = User(profile = perfil, credential = credenciales)
        return userRepository.save(nuevoUsuario)
=======
        val nuevoUsuario = User(profile = perfil, credential = credenciales, role = Role.USER) // Use Role.USER

        val userEntity = UserEntity(
            id = null, // Ensure id is null for new entities
            email = credenciales.email,
            passwordHash = credenciales.getHashPassword(), // Correctly pass hashed password
            name = perfil.name.value,
            biografy = perfil.biografy, // Ensure biografy is nullable
            fotoUrl = perfil.fotoUrl, // Ensure fotoUrl is nullable
            role = nuevoUsuario.role // Pass the role correctly
        )

        userRepository.save(userEntity)
        return nuevoUsuario
>>>>>>> Stashed changes
    }
}