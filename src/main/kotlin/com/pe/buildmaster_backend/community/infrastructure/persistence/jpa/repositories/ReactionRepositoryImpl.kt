package com.pe.buildmaster_backend.community.infrastructure.persistence.jpa.repositories

import com.pe.buildmaster_backend.community.domain.interfaces.ReactionRepository
import com.pe.buildmaster_backend.community.domain.model.entities.Reaction
import com.pe.buildmaster_backend.community.domain.model.valueobjects.PostId
import com.pe.buildmaster_backend.community.domain.model.valueobjects.ReactionType
import com.pe.buildmaster_backend.community.domain.model.valueobjects.UserId
import com.pe.buildmaster_backend.community.infrastructure.persistence.jpa.mappers.ReactionJpaMapper
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class ReactionRepositoryImpl(
    private val springDataRepo: SpringDataReactionJpaRepository
) : ReactionRepository {

    override suspend fun findById(id: UUID): Reaction? {
        return springDataRepo.findById(id)
            .map { ReactionJpaMapper.toDomain(it) }
            .orElse(null)
    }

    override suspend fun save(reaction: Reaction): Reaction {
        val jpaEntity = ReactionJpaMapper.toJpaEntity(reaction)
        val savedEntity = springDataRepo.save(jpaEntity)
        return ReactionJpaMapper.toDomain(savedEntity)
    }

    override suspend fun delete(reaction: Reaction) {
        springDataRepo.delete(ReactionJpaMapper.toJpaEntity(reaction))
    }

    override suspend fun findByPostId(postId: PostId): List<Reaction> {
        return springDataRepo.findByPostId(postId.value)
            .map { ReactionJpaMapper.toDomain(it) }
    }

    override suspend fun findByPostIdAndUserId(postId: PostId, userId: UserId): List<Reaction> {
        return springDataRepo.findByPostIdAndUserId(postId.value, userId.value)
            .map { ReactionJpaMapper.toDomain(it) }
    }

    override suspend fun findByPostIdAndUserIdAndType(postId: PostId, userId: UserId, type: ReactionType): Reaction? {
        return springDataRepo.findByPostIdAndUserIdAndType(postId.value, userId.value, type.name)
            ?.let { ReactionJpaMapper.toDomain(it) }
    }
}