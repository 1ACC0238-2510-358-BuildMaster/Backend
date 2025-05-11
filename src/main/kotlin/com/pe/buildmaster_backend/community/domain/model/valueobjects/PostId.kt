package com.pe.buildmaster_backend.community.domain.model.valueobjects

import java.util.UUID

@JvmInline
value class PostId(val value: UUID = UUID.randomUUID())