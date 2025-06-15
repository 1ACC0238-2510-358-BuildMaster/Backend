package com.pe.buildmaster_backend.login.domain.model.valueobjects

import java.time.LocalDateTime
import java.util.*

data class Favorite(
    val buildId: UUID,
    val fechaAgregado: LocalDateTime = LocalDateTime.now(),
    val metadata: String? = null
)