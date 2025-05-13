package com.pe.buildmaster_backend.community.domain.model.valueobjects

// Simple por ahora, podría incluir nombre, tipo MIME, URL, etc.
data class FileContent(
    val fileName: String,
    val fileType: String, // e.g., "image/jpeg", "video/mp4"
    val contentUrl: String // o podría ser ByteArray si se almacena directamente
)