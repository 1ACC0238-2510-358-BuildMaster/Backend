package com.pe.buildmaster_backend.login.domain.services

import com.pe.buildmaster_backend.login.application.internal.eventhandlers.FindUserByNameQueryHandler
import com.pe.buildmaster_backend.login.application.internal.eventhandlers.LoginCommandHandler
import com.pe.buildmaster_backend.login.application.internal.eventhandlers.RegisterUserCommandHandler
import com.pe.buildmaster_backend.login.application.internal.eventhandlers.UpdateUserCommandHandler
import com.pe.buildmaster_backend.login.domain.model.commands.LoginCommand
import com.pe.buildmaster_backend.login.domain.model.commands.RegisterUserCommand
import com.pe.buildmaster_backend.login.domain.model.commands.UpdateUserCommand
import com.pe.buildmaster_backend.login.domain.model.valueobjects.Role
import com.pe.buildmaster_backend.login.domain.model.queries.FindUserByNameQuery
import com.pe.buildmaster_backend.login.domain.model.valueobjects.Name
import com.pe.buildmaster_backend.login.domain.model.valueobjects.Profile
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserApplicationService(
    private val loginHandler: LoginCommandHandler,
    private val registerHandler: RegisterUserCommandHandler,
    private val updateHandler: UpdateUserCommandHandler,
    private val findByNameHandler: FindUserByNameQueryHandler,
) {
    fun login(email: String, password: String): String =
        loginHandler.handle(LoginCommand(email, password)).token // Extract token from AuthenticationResult

    fun register(email: String, password: String, name: String, role: Role) =
        registerHandler.handle(RegisterUserCommand(email = email, password = password, name = name, role = role))

    fun updateProfile(userId: String, biografy: String?, fotoUrl: String?, profile: String?, role: String?) {
        val resolvedProfile = profile?.let { Profile(Name(it), biografy, fotoUrl) }
            ?: throw IllegalArgumentException("Profile cannot be null") // Ensure non-null Profile
        val resolvedRole = role?.let { Role.valueOf(it) }
            ?: throw IllegalArgumentException("Role cannot be null") // Ensure non-null Role

        updateHandler.handle(UpdateUserCommand(
            userId = UUID.fromString(userId),
            profile = resolvedProfile,
            role = resolvedRole
        ))
    }

    fun findByName(name: String) =
        findByNameHandler.handle(FindUserByNameQuery(name))
}