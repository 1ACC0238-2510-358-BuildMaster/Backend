package com.pe.buildmaster_backend.community.interfaces.rest.resources

import io.swagger.v3.oas.annotations.media.Schema
import java.util.UUID

data class AddCommentRequest(


    @Schema(description = "Contenido textual del comentario.", example = "¡Gran post!", required = true)
    val content: String
)