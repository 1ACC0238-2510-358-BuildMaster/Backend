package com.pe.buildmaster_backend.community.infrastructure.persistence.jpa.repositories


import com.pe.buildmaster_backend.community.infrastructure.persistence.jpa.entities.CommentJpaEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface SpringDataCommentJpaRepository : JpaRepository<CommentJpaEntity, UUID> {
    fun findByPostId(postId: UUID): List<CommentJpaEntity>
    fun findByAuthorId(authorId: UUID): List<CommentJpaEntity>
}