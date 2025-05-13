package com.pe.buildmaster_backend.community.infrastructure.persistence.jpa.mappers

import com.pe.buildmaster_backend.community.domain.model.entities.Reaction
import com.pe.buildmaster_backend.community.domain.model.valueobjects.PostId
import com.pe.buildmaster_backend.community.domain.model.valueobjects.ReactionType
import com.pe.buildmaster_backend.community.domain.model.valueobjects.UserId
import com.pe.buildmaster_backend.community.infrastructure.persistence.jpa.entities.ReactionJpaEntity

object ReactionJpaMapper {

    fun toDomain(jpaEntity: ReactionJpaEntity): Reaction {
        return Reaction(
            id = jpaEntity.id,
            postId = PostId(jpaEntity.postId),
            userId = UserId(jpaEntity.userId),
            type = jpaEntity.type, // <--- CAMBIO AQUÍ: ya no es necesario ReactionType.valueOf(...)
            createdAt = jpaEntity.createdAt
        )
    }

    fun toJpaEntity(domainEntity: Reaction): ReactionJpaEntity {
        return ReactionJpaEntity(
            id = domainEntity.id,
            postId = domainEntity.postId.value,
            userId = domainEntity.userId.value,
            type = domainEntity.type, // <--- CAMBIO AQUÍ: JPA manejará la conversión a String por EnumType.STRING
            createdAt = domainEntity.createdAt
        )
    }
}