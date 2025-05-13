package com.pe.buildmaster_backend.glosary.domain.model.valueobjects

data class ContextTip(
    val paso: String,
    val mensaje: String,
    val linkGuia: String?,
    val linkGlosario: String?
)