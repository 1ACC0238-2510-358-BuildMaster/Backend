package com.pe.buildmaster_backend.community.interfaces.rest.resources

import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime
import java.util.UUID

data class CommentResponse(
    @Schema(description = "ID único del comentario.")
    val id: UUID,

    @Schema(description = "ID del post al que pertenece el comentario.")
    val postId: UUID,

    @Schema(description = "ID del autor del comentario.")
    val authorId: UUID,

    @Schema(description = "Contenido textual del comentario.")
    val content: String,

    @Schema(description = "Fecha y hora de creación del comentario.")
    val createdAt: LocalDateTime,

    @Schema(description = "Fecha y hora de la última actualización del comentario.")
    val updatedAt: LocalDateTime
)