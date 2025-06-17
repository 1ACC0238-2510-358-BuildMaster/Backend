package com.pe.buildmaster_backend.login.domain.model.commands

import com.pe.buildmaster_backend.login.domain.model.valueobjects.Profile
import com.pe.buildmaster_backend.login.domain.model.valueobjects.Role
import java.util.*

data class UpdateUserCommand(
    val userId: UUID,
    val profile: Profile,
    val role: Role
)