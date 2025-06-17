package com.pe.buildmaster_backend.login.domain.model.queries

import java.util.UUID

data class GetUserRoleQuery(
    val userId: UUID
)