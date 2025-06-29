package com.pe.buildmaster_backend.technical_configuration.domain.services

import com.pe.buildmaster_backend.technical_configuration.application.internal.eventhandlers.BuildCreatedEvent
import com.pe.buildmaster_backend.technical_configuration.application.internal.eventhandlers.BuildDeletedEvent
import com.pe.buildmaster_backend.technical_configuration.domain.model.entities.Build
import com.pe.buildmaster_backend.technical_configuration.domain.model.valueobjects.BuildResult
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
    fun generateBuildResult(build: Build): BuildResult {
        // Combina los IDs para tener siempre la misma "semilla"
        val seed = build.componentIds.sum()

        // Simula datos "deterministas"
        val estimatedPerformance = when {
            seed % 3 == 0L -> "High"
            seed % 3 == 1L -> "Medium"
            else -> "Low"
        }
        val powerConsumption = (seed * 10 % 500 + 200).toInt()
        val estimatedPrice = build.componentIds.size * 450.0
        val observations = when {
            seed % 5 == 0L -> "No issues detected"
            seed % 5 == 1L -> "Possible bottleneck between CPU and GPU"
            seed % 5 == 2L -> "Check RAM compatibility"
            seed % 5 == 3L -> "Verify PSU capacity"
            else -> "Minor airflow restrictions"
        }

        return BuildResult(
            buildId = build.id!!,
            estimatedPerformance = estimatedPerformance,
            powerConsumptionWatts = powerConsumption,
            estimatedPrice = estimatedPrice,
            observations = observations
        )
    }
}