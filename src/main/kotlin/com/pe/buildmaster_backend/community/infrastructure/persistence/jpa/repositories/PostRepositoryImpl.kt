package com.pe.buildmaster_backend.community.infrastructure.persistence.jpa.repositories

import com.pe.buildmaster_backend.community.domain.interfaces.PostRepository
import com.pe.buildmaster_backend.community.domain.model.entities.Post
import com.pe.buildmaster_backend.community.domain.model.valueobjects.PostId
import com.pe.buildmaster_backend.community.domain.model.valueobjects.UserId
import com.pe.buildmaster_backend.community.infrastructure.persistence.jpa.mappers.PostJpaMapper
import org.springframework.stereotype.Component // O @Repository, pero @Component es más genérico para esta capa de adaptación

@Component // Spring lo gestionará como un bean
class PostRepositoryImpl(
    private val springDataRepo: SpringDataPostJpaRepository // Inyección de la interfaz de Spring Data
) : PostRepository {

    override suspend fun findById(id: PostId): Post? {
        return springDataRepo.findById(id.value)
            .map { PostJpaMapper.toDomain(it) }
            .orElse(null)
    }

    override suspend fun save(post: Post): Post {
        val jpaEntity = PostJpaMapper.toJpaEntity(post)
        val savedEntity = springDataRepo.save(jpaEntity)
        return PostJpaMapper.toDomain(savedEntity)
    }

    override suspend fun delete(post: Post) {
        // Spring Data JPA espera la entidad JPA o su ID para eliminar.
        // Si solo tenemos la entidad de dominio, la mapeamos.
        // O, si PostId es suficiente: springDataRepo.deleteById(post.id.value)
        springDataRepo.delete(PostJpaMapper.toJpaEntity(post))
    }

    override suspend fun findByAuthorId(authorId: UserId): List<Post> {
        return springDataRepo.findByAuthorId(authorId.value)
            .map { PostJpaMapper.toDomain(it) }
    }
}