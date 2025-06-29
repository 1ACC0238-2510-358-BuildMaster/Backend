package com.pe.buildmaster_backend.technical_configuration.application.internal.eventhandlers

import com.pe.buildmaster_backend.technical_configuration.domain.model.entities.Build

data class BuildCreatedEvent(
    val buildId: Long,
    val componentIds: List<Long>
)