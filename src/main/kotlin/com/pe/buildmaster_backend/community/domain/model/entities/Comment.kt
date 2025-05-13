package com.pe.buildmaster_backend.community.domain.model.entities

import com.pe.buildmaster_backend.community.domain.model.valueobjects.CommentId
import com.pe.buildmaster_backend.community.domain.model.valueobjects.PostId
import com.pe.buildmaster_backend.community.domain.model.valueobjects.UserId
import java.time.LocalDateTime

data class Comment(
    val id: CommentId,
    val postId: PostId, // A qué post pertenece este comentario
    val authorId: UserId,
    var content: String,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    var updatedAt: LocalDateTime = LocalDateTime.now()
) {
    // Métodos de comportamiento del Comment podrían ir aquí
}