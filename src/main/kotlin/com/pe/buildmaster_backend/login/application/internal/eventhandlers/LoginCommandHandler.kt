package com.pe.buildmaster_backend.login.application.internal.eventhandlers

import com.pe.buildmaster_backend.login.domain.model.commands.LoginCommand
import com.pe.buildmaster_backend.login.domain.model.valueobjects.AuthenticationResult
import com.pe.buildmaster_backend.login.domain.model.valueobjects.Role
import com.pe.buildmaster_backend.login.domain.services.AuthService
import com.pe.buildmaster_backend.login.infrastructure.persistence.jpa.repositories.UserRepository
import org.springframework.stereotype.Component

@Component
class LoginCommandHandler(
    private val authService: AuthService,
    private val userRepository: UserRepository
) {
    fun handle(cmd: LoginCommand): AuthenticationResult {
        // Perform login to get JWT token
        val token: String = authService.login(cmd.email, cmd.password)

        // Load user to retrieve role
        val userEntity = userRepository.findByEmail(cmd.email)
            ?: throw IllegalArgumentException("Usuario no encontrado: ${cmd.email}")
        val role: Role = userEntity.role

        return AuthenticationResult(
            token = token,
            role = role
        )
    }
}