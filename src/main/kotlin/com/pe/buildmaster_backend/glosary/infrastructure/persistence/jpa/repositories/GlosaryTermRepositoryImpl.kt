package com.pe.buildmaster_backend.glosary.infrastructure.persistence.jpa.repositories

import com.pe.buildmaster_backend.glosary.domain.model.valueobjects.GlosaryTerm
import org.springframework.stereotype.Repository

@Repository
class GlosaryTermRepositoryImpl :GlosaryTermRepository {
    override fun findById(id: String): GlosaryTerm {
// simulado
        return GlosaryTerm(id, "GPU", "Unidad de procesamiento gráfico", listOf("Ejemplo 1", "Ejemplo 2"))
    }

    override fun findByTexto(texto: String): GlosaryTerm? {
        // simulado
        return if (texto.equals("gpu", ignoreCase = true)) findById("1") else null
    }
}