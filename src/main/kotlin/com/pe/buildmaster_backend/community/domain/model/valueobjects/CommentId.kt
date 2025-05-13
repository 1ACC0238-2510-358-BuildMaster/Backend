package com.pe.buildmaster_backend.community.domain.model.valueobjects

import java.util.UUID

@JvmInline
value class CommentId(val value: UUID = UUID.randomUUID())