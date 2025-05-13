package com.pe.buildmaster_backend.login.infrastructure.persistence.jpa.repositories;

import com.pe.buildmaster_backend.login.domain.model.valueobjects.User;
import com.pe.buildmaster_backend.login.interfaces.rest.mappers.UserMapper.toDomain
import com.pe.buildmaster_backend.login.interfaces.rest.mappers.UserMapper.toEntity
import org.springframework.stereotype.Repository;

@Repository
class JpaUserRepository(private val jpaRepo: SpringDataUsuarioRepository): UserRepository {
    override fun findByEmail(email: String): User? {
        return jpaRepo.findByEmail(email)?.toDomain()
    }

    override fun save(usuario: User): User {
        val entity = usuario.toEntity()
        return jpaRepo.save(entity).toDomain()
    }
}