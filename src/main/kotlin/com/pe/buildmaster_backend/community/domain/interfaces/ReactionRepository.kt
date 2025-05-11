package com.pe.buildmaster_backend.community.domain.interfaces

import com.pe.buildmaster_backend.community.domain.model.entities.Reaction
import com.pe.buildmaster_backend.community.domain.model.valueobjects.PostId
import com.pe.buildmaster_backend.community.domain.model.valueobjects.ReactionType
import com.pe.buildmaster_backend.community.domain.model.valueobjects.UserId
import java.util.UUID

interface ReactionRepository {
    suspend fun findById(id: UUID): Reaction? // Usando el UUID de la reacción
    suspend fun save(reaction: Reaction): Reaction
    suspend fun delete(reaction: Reaction)
    suspend fun findByPostId(postId: PostId): List<Reaction>
    suspend fun findByPostIdAndUserId(postId: PostId, userId: UserId): List<Reaction> // Para ver todas las reacciones de un usuario a un post
    suspend fun findByPostIdAndUserIdAndType(postId: PostId, userId: UserId, type: ReactionType): Reaction? // Para verificar si ya existe una reacción específica
}