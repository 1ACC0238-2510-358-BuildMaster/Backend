package com.pe.buildmaster_backend.community.infrastructure.persistence.jpa.mappers

import com.pe.buildmaster_backend.community.domain.model.entities.Comment
import com.pe.buildmaster_backend.community.domain.model.valueobjects.CommentId
import com.pe.buildmaster_backend.community.domain.model.valueobjects.PostId
import com.pe.buildmaster_backend.community.domain.model.valueobjects.UserId
import com.pe.buildmaster_backend.community.infrastructure.persistence.jpa.entities.CommentJpaEntity

object CommentJpaMapper {

    fun toDomain(jpaEntity: CommentJpaEntity): Comment {
        return Comment(
            id = CommentId(jpaEntity.id),
            postId = PostId(jpaEntity.postId),
            authorId = UserId(jpaEntity.authorId),
            content = jpaEntity.content,
            createdAt = jpaEntity.createdAt,
            updatedAt = jpaEntity.updatedAt
        )
    }

    fun toJpaEntity(domainEntity: Comment): CommentJpaEntity {
        return CommentJpaEntity(
            id = domainEntity.id.value,
            postId = domainEntity.postId.value,
            authorId = domainEntity.authorId.value,
            content = domainEntity.content,
            createdAt = domainEntity.createdAt,
            updatedAt = domainEntity.updatedAt
        )
    }
}