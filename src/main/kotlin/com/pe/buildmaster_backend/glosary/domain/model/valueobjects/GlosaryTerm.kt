package com.pe.buildmaster_backend.glosary.domain.model.valueobjects

import java.util.*

data class GlosaryTerm (
    val id: String,
    val termino: String,
    val definicion: String,
    val ejemplos: List<String>
    )
