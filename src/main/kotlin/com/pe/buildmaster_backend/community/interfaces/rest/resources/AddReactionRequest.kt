package com.pe.buildmaster_backend.community.interfaces.rest.resources

import com.pe.buildmaster_backend.community.domain.model.valueobjects.ReactionType
import io.swagger.v3.oas.annotations.media.Schema

data class AddReactionRequest(
    // postId se tomará de la URL
    // userId se tomará del contexto de seguridad
    @Schema(description = "Tipo de reacción (LIKE, DISLIKE, REPOST).", example = "LIKE", required = true)
    val reactionType: ReactionType
)