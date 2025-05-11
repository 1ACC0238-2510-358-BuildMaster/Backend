package com.pe.buildmaster_backend.community.infrastructure.persistence.jpa.repositories


import com.pe.buildmaster_backend.community.infrastructure.persistence.jpa.entities.PostJpaEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository // Anotación de Spring para que sea un bean gestionado
interface SpringDataPostJpaRepository : JpaRepository<PostJpaEntity, UUID> {
    fun findByAuthorId(authorId: UUID): List<PostJpaEntity>
    // Spring Data JPA genera automáticamente la implementación de este método
    // basado en el nombre.
}