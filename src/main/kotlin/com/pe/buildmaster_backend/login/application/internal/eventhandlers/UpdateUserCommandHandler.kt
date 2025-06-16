package com.pe.buildmaster_backend.login.application.internal.eventhandlers

import com.pe.buildmaster_backend.login.domain.events.UserUpdatedEvent
import com.pe.buildmaster_backend.login.domain.model.commands.UpdateUserCommand
import com.pe.buildmaster_backend.login.domain.model.valueobjects.Profile
import com.pe.buildmaster_backend.login.infrastructure.persistence.jpa.repositories.UserRepository
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component

@Component
class UpdateUserCommandHandler(
    private val userRepository: UserRepository,
    private val publisher: ApplicationEventPublisher
) {
    fun handle(cmd: UpdateUserCommand) {
        val existing = userRepository.findByEmail(cmd.userId.toString())
            ?: throw IllegalArgumentException("Usuario no encontrado: \${cmd.userId}")
        val updatedProfile = Profile(
            name = existing.profile.name,
            biografy = cmd.biografy,
            fotoUrl = cmd.fotoUrl
        )
        existing.updateProfile(updatedProfile)
        userRepository.save(existing)
        publisher.publishEvent(
            UserUpdatedEvent(
                existing.id,
                cmd.biografy,
                cmd.fotoUrl
            )
        )
    }
}