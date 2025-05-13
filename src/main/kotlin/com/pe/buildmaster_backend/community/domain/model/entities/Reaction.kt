package com.pe.buildmaster_backend.community.domain.model.entities

import com.pe.buildmaster_backend.community.domain.model.valueobjects.PostId
import com.pe.buildmaster_backend.community.domain.model.valueobjects.ReactionType
import com.pe.buildmaster_backend.community.domain.model.valueobjects.UserId
import java.time.LocalDateTime
import java.util.UUID

data class Reaction(
    val id: UUID = UUID.randomUUID(), // ID único para la reacción
    val postId: PostId, // El post al que se reacciona
    val userId: UserId, // Quién reacciona
    val type: ReactionType,
    val createdAt: LocalDateTime = LocalDateTime.now()
)