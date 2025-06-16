package com.pe.buildmaster_backend.login.application.internal.eventhandlers

import com.pe.buildmaster_backend.login.domain.model.commands.LoginCommand
import com.pe.buildmaster_backend.login.domain.services.AuthService
import org.springframework.stereotype.Component

@Component
class LoginCommandHandler(
    private val authService: AuthService
) {
    fun handle(cmd: LoginCommand): String =
        authService.login(cmd.email, cmd.password)
}