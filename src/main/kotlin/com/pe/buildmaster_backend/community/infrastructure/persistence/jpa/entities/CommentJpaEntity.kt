package com.pe.buildmaster_backend.community.infrastructure.persistence.jpa.entities

import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "comments")
data class CommentJpaEntity(
    @Id
    var id: UUID,

    @Column(nullable = false)
    var postId: UUID, // FK a PostJpaEntity.id

    @Column(nullable = false)
    var authorId: UUID,

    @Lob
    @Column(nullable = false, columnDefinition = "TEXT")
    var content: String,

    @Column(nullable = false, updatable = false)
    var createdAt: LocalDateTime,

    @Column(nullable = false)
    var updatedAt: LocalDateTime


)