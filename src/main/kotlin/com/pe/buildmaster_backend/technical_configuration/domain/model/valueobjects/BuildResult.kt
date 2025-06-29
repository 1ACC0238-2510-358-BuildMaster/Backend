package com.pe.buildmaster_backend.technical_configuration.domain.model.valueobjects

data class BuildResult(
    val buildId: Long,
    val estimatedPerformance: String,
    val powerConsumptionWatts: Int,
    val estimatedPrice: Double,
    val observations: String
)