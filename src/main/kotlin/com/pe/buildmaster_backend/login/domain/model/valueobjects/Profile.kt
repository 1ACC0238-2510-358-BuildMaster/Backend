package com.pe.buildmaster_backend.login.domain.model.valueobjects

@JvmInline
value class Name(val value: String) {
    init {
        require(value.length in 3..50) { "Nombre inválido" }
    }
}

data class Profile(
    val name: Name,
    val biografy: String? = null,
    val fotoUrl: String? = null
)