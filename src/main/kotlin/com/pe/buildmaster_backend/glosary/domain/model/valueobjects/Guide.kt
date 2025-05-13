package com.pe.buildmaster_backend.glosary.domain.model.valueobjects

data class Guide(
    val id: String,
    val categoria: CategoryComponent,
    val contenido: String
)

@JvmInline
value class CategoryComponent(val nombre: String) {
    init {
        require(nombre.isNotBlank()) { "La categoría no puede estar vacía." }
    }
}