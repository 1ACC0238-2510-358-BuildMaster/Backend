package com.pe.buildmaster_backend.technical_configuration.domain.model.commands

data class CreateBuildCommand(
    val componentIds: List<Long>
)