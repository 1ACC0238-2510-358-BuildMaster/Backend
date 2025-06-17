package com.pe.buildmaster_backend.login.application.internal.eventhandlers

import com.pe.buildmaster_backend.login.domain.events.UserRegisteredEvent
import com.pe.buildmaster_backend.login.domain.model.commands.RegisterUserCommand
import com.pe.buildmaster_backend.login.domain.services.AuthService
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component

@Component
class RegisterUserCommandHandler(
    private val authService: AuthService,
    private val publisher: ApplicationEventPublisher
) {
    fun handle(cmd: RegisterUserCommand) {
        val user = authService.registrar(cmd.email, cmd.password, cmd.name)
        publisher.publishEvent(
            UserRegisteredEvent(
                user.id,
                user.getCredential().email,
                user.profile.name.value,
                role = cmd.role
            )
        )
    }
}