package com.pe.buildmaster_backend.technical_configuration.domain.services

import com.pe.buildmaster_backend.technical_configuration.application.internal.eventhandlers.BuildCreatedEvent
import com.pe.buildmaster_backend.technical_configuration.application.internal.eventhandlers.BuildDeletedEvent
import com.pe.buildmaster_backend.technical_configuration.domain.model.entities.Build
import com.pe.buildmaster_backend.technical_configuration.infrastructure.persistence.jpa.repositories.BuildRepository
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BuildService(
    private val buildRepository: BuildRepository,
    private val eventPublisher: ApplicationEventPublisher
) {
    @Transactional
    fun createBuild(componentIds: List<Long>): Build {
        val build = Build(componentIds = componentIds)
        val savedBuild = buildRepository.save(build)

        // Emitir evento
        val event = BuildCreatedEvent(
            buildId = savedBuild.id!!,
            componentIds = savedBuild.componentIds
        )
        eventPublisher.publishEvent(event)

        return savedBuild
    }
    @Transactional
    fun deleteBuild(id: Long) {
        buildRepository.deleteById(id)

        val event = BuildDeletedEvent(buildId = id)
        eventPublisher.publishEvent(event)
    }

    fun getBuildById(id: Long): Build? {
        return buildRepository.findById(id)
    }

    fun getAllBuilds(): List<Build> {
        return buildRepository.findAll()
    }
}