package com.pe.buildmaster_backend.community.infrastructure.persistence.jpa.repositories

import com.pe.buildmaster_backend.community.infrastructure.persistence.jpa.entities.ReactionJpaEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface SpringDataReactionJpaRepository : JpaRepository<ReactionJpaEntity, UUID> {
    fun findByPostId(postId: UUID): List<ReactionJpaEntity>
    fun findByPostIdAndUserId(postId: UUID, userId: UUID): List<ReactionJpaEntity>
    // Spring Data JPA es lo suficientemente inteligente para manejar la conversión del enum a String para la query
    fun findByPostIdAndUserIdAndType(postId: UUID, userId: UUID, type: String): ReactionJpaEntity? // <--- CAMBIO AQUÍ: de String a ReactionType
}