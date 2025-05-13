package com.pe.buildmaster_backend.community.interfaces.rest.resources

import io.swagger.v3.oas.annotations.media.Schema // Para documentación Swagger
import java.util.UUID



data class FileContentResource(
    @Schema(description = "Nombre del archivo adjunto.", example = "image.png")
    val fileName: String,
    @Schema(description = "Tipo MIME del archivo.", example = "image/png")
    val fileType: String,
    @Schema(description = "URL del contenido del archivo.", example = "https://example.com/uploads/image.png")
    val contentUrl: String
)

data class CreatePostRequest(

    @Schema(description = "Contenido textual del post.", example = "Este es mi primer post!", required = true)
    val content: String,

    @Schema(description = "Lista de archivos adjuntos (imágenes, videos).")
    val mediaAttachments: List<FileContentResource> = emptyList()
)