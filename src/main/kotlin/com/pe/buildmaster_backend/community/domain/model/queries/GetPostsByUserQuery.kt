package com.pe.buildmaster_backend.community.domain.model.queries

import com.pe.buildmaster_backend.community.domain.model.valueobjects.UserId

data class GetPostsByUserQuery(
    val userId: UserId
    // Podríamos añadir paginación aquí, e.g., page: Int, size: Int
)