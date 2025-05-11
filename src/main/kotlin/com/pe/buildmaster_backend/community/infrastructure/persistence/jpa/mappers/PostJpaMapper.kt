package com.pe.buildmaster_backend.community.infrastructure.persistence.jpa.mappers

import com.pe.buildmaster_backend.community.domain.model.entities.Post
import com.pe.buildmaster_backend.community.domain.model.valueobjects.FileContent
import com.pe.buildmaster_backend.community.domain.model.valueobjects.PostId
import com.pe.buildmaster_backend.community.domain.model.valueobjects.UserId
import com.pe.buildmaster_backend.community.infrastructure.persistence.jpa.entities.FileContentEmbeddable
import com.pe.buildmaster_backend.community.infrastructure.persistence.jpa.entities.PostJpaEntity

object PostJpaMapper {

    fun toDomain(jpaEntity: PostJpaEntity): Post {
        return Post(
            id = PostId(jpaEntity.id),
            authorId = UserId(jpaEntity.authorId),
            content = jpaEntity.content,
            mediaAttachments = jpaEntity.mediaAttachments.map { fcEmbeddable ->
                FileContent(
                    fileName = fcEmbeddable.fileName,
                    fileType = fcEmbeddable.fileType,
                    contentUrl = fcEmbeddable.contentUrl
                )
            },
            createdAt = jpaEntity.createdAt,
            updatedAt = jpaEntity.updatedAt
        )
    }

    fun toJpaEntity(domainEntity: Post): PostJpaEntity {
        return PostJpaEntity(
            id = domainEntity.id.value,
            authorId = domainEntity.authorId.value,
            content = domainEntity.content,
            mediaAttachments = domainEntity.mediaAttachments.map { fcDomain ->
                FileContentEmbeddable(
                    fileName = fcDomain.fileName,
                    fileType = fcDomain.fileType,
                    contentUrl = fcDomain.contentUrl
                )
            },
            createdAt = domainEntity.createdAt,
            updatedAt = domainEntity.updatedAt
        )
    }
}