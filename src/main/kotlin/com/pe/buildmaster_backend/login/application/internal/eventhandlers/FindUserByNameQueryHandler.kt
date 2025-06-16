package com.pe.buildmaster_backend.login.application.internal.eventhandlers

import com.pe.buildmaster_backend.login.domain.model.queries.FindUserByNameQuery
import com.pe.buildmaster_backend.login.infrastructure.persistence.jpa.repositories.UserRepository
import org.springframework.stereotype.Component

@Component
class FindUserByNameQueryHandler(
    private val userRepository: UserRepository
) {
    fun handle(query: FindUserByNameQuery) =
        userRepository.findByName(query.name)
}