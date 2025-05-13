package com.pe.buildmaster_backend.community.interfaces.rest.resources

import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime
import java.util.UUID

data class PostResponse(
    @Schema(description = "ID único del post.", example = "a1b2c3d4-e5f6-7890-1234-567890abcdef")
    val id: UUID,

    @Schema(description = "ID del autor del post.", example = "f0e1d2c3-b4a5-6789-0123-456789abcdef")
    val authorId: UUID,

    @Schema(description = "Contenido textual del post.", example = "Respuesta a un post interesante.")
    val content: String,

    @Schema(description = "Lista de archivos adjuntos.")
    val mediaAttachments: List<FileContentResource>,

    @Schema(description = "Fecha y hora de creación del post.")
    val createdAt: LocalDateTime,

    @Schema(description = "Fecha y hora de la última actualización del post.")
    val updatedAt: LocalDateTime,


    @Schema(description = "Número de comentarios en el post.", example = "10")
    val commentCount: Int = 0, // Ejemplo, se calcularía

    @Schema(description = "Número de 'likes' en el post.", example = "25")
    val likeCount: Int = 0, // Ejemplo, se calcularía

    @Schema(description = "Número de 'dislikes' en el post.", example = "2")
    val dislikeCount: Int = 0, // Ejemplo, se calcularía

    @Schema(description = "Número de 'reposts'.", example = "5")
    val repostCount: Int = 0 // Ejemplo, se calcularía
)