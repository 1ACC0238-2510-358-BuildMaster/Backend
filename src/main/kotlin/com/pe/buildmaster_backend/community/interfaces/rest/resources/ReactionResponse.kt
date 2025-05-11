package com.pe.buildmaster_backend.community.interfaces.rest.resources

import com.pe.buildmaster_backend.community.domain.model.valueobjects.ReactionType
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime
import java.util.UUID

data class ReactionResponse(
    @Schema(description = "ID único de la reacción (puede ser el mismo que el postId + userId + type si no tiene ID propio).")
    val id: UUID, // El ID de la entidad Reaction

    @Schema(description = "ID del post al que se reaccionó.")
    val postId: UUID,

    @Schema(description = "ID del usuario que reaccionó.")
    val userId: UUID,

    @Schema(description = "Tipo de reacción.")
    val type: ReactionType,

    @Schema(description = "Fecha y hora de la reacción.")
    val createdAt: LocalDateTime
)