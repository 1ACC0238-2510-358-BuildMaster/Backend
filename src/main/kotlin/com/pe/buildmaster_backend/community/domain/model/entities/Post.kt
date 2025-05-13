package com.pe.buildmaster_backend.community.domain.model.entities

import com.pe.buildmaster_backend.community.domain.model.valueobjects.FileContent
import com.pe.buildmaster_backend.community.domain.model.valueobjects.PostId
import com.pe.buildmaster_backend.community.domain.model.valueobjects.UserId
import java.time.LocalDateTime

data class Post(
    val id: PostId,
    val authorId: UserId,
    var content: String, // El contenido del post (texto)
    val mediaAttachments: List<FileContent> = emptyList(), // Para imágenes, videos, etc.
    val createdAt: LocalDateTime = LocalDateTime.now(),
    var updatedAt: LocalDateTime = LocalDateTime.now(),
    // Podríamos añadir más campos como 'tags', 'visibility', etc.
) {

}