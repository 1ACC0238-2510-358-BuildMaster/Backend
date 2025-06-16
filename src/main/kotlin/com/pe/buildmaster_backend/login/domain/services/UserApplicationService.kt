package com.pe.buildmaster_backend.login.domain.services

import com.pe.buildmaster_backend.login.application.internal.eventhandlers.FindUserByNameQueryHandler
import com.pe.buildmaster_backend.login.application.internal.eventhandlers.LoginCommandHandler
import com.pe.buildmaster_backend.login.application.internal.eventhandlers.RegisterUserCommandHandler
import com.pe.buildmaster_backend.login.application.internal.eventhandlers.UpdateUserCommandHandler
import com.pe.buildmaster_backend.login.domain.model.commands.LoginCommand
import com.pe.buildmaster_backend.login.domain.model.commands.RegisterUserCommand
import com.pe.buildmaster_backend.login.domain.model.commands.UpdateUserCommand
import com.pe.buildmaster_backend.login.domain.model.queries.FindUserByNameQuery
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
        loginHandler.handle(LoginCommand(email, password))

    fun register(email: String, password: String, name: String) =
        registerHandler.handle(RegisterUserCommand(email = email, password = password, name = name))

    fun updateProfile(userId: String, biografy: String?, fotoUrl: String?) {
        updateHandler.handle(UpdateUserCommand(
            userId = UUID.fromString(userId),
            biografy = biografy,
            fotoUrl = fotoUrl
        ))
    }

    fun findByName(name: String) =
        findByNameHandler.handle(FindUserByNameQuery(name))
}