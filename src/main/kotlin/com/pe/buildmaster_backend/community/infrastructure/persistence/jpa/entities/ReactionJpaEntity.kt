package com.pe.buildmaster_backend.community.infrastructure.persistence.jpa.entities

import com.pe.buildmaster_backend.community.domain.model.valueobjects.ReactionType
import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "reactions", uniqueConstraints = [
    UniqueConstraint(columnNames = ["postId", "userId", "type"])
])
data class ReactionJpaEntity(
    @Id
    var id: UUID,

    @Column(nullable = false)
    var postId: UUID,

    @Column(nullable = false)
    var userId: UUID,

    @Enumerated(EnumType.STRING) // Esto le dice a JPA que guarde el nombre del enum como String
    @Column(nullable = false, length = 50)
    var type: ReactionType, // <--- CAMBIO AQUÍ: de String a ReactionType

    @Column(nullable = false, updatable = false)
    var createdAt: LocalDateTime
)