package com.pe.buildmaster_backend.community.infrastructure.persistence.jpa.repositories

import com.pe.buildmaster_backend.community.domain.interfaces.CommentRepository
import com.pe.buildmaster_backend.community.domain.model.entities.Comment
import com.pe.buildmaster_backend.community.domain.model.valueobjects.CommentId
import com.pe.buildmaster_backend.community.domain.model.valueobjects.PostId
import com.pe.buildmaster_backend.community.domain.model.valueobjects.UserId
import com.pe.buildmaster_backend.community.infrastructure.persistence.jpa.mappers.CommentJpaMapper
import org.springframework.stereotype.Component

@Component
class CommentRepositoryImpl(
    private val springDataRepo: SpringDataCommentJpaRepository
) : CommentRepository {

    override suspend fun findById(id: CommentId): Comment? {
        return springDataRepo.findById(id.value)
            .map { CommentJpaMapper.toDomain(it) }
            .orElse(null)
    }

    override suspend fun save(comment: Comment): Comment {
        val jpaEntity = CommentJpaMapper.toJpaEntity(comment)
        val savedEntity = springDataRepo.save(jpaEntity)
        return CommentJpaMapper.toDomain(savedEntity)
    }

    override suspend fun delete(comment: Comment) {
        springDataRepo.delete(CommentJpaMapper.toJpaEntity(comment))
    }

    override suspend fun findByPostId(postId: PostId): List<Comment> {
        return springDataRepo.findByPostId(postId.value)
            .map { CommentJpaMapper.toDomain(it) }
    }

    override suspend fun findByAuthorId(authorId: UserId): List<Comment> {
        return springDataRepo.findByAuthorId(authorId.value)
            .map { CommentJpaMapper.toDomain(it) }
    }
}