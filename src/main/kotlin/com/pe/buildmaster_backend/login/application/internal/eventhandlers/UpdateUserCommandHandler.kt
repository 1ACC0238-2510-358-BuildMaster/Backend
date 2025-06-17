package com.pe.buildmaster_backend.login.application.internal.eventhandlers

import com.pe.buildmaster_backend.login.domain.events.UserUpdatedEvent
import com.pe.buildmaster_backend.login.domain.model.commands.UpdateUserCommand
import com.pe.buildmaster_backend.login.domain.model.entities.UserEntity
import com.pe.buildmaster_backend.login.infrastructure.persistence.jpa.repositories.UserRepository
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class UpdateUserCommandHandler(
    private val userRepository: UserRepository,
    private val publisher: ApplicationEventPublisher
) {
    @Transactional
    fun handle(cmd: UpdateUserCommand) {
        // Fetch existing JPA entity by ID
        val entity: UserEntity = userRepository.findById(cmd.userId)
            .orElseThrow { IllegalArgumentException("Usuario no encontrado: ${cmd.userId}") }

        // Update entity fields directly
        entity.name = cmd.profile.name.toString()
        entity.biografy = cmd.profile.biografy
        entity.fotoUrl = cmd.profile.fotoUrl
        entity.role = cmd.role // Convert Role to String

        // Persist changes
        userRepository.save(entity)

        // Publish domain event
        publisher.publishEvent(
            UserUpdatedEvent(
                userId = cmd.userId, // Pass cmd.userId to userId parameter
                biografy = cmd.profile.biografy,
                fotoUrl = cmd.profile.fotoUrl,
                role = cmd.role.toString() // Convert Role to String
            )
        )
    }
}