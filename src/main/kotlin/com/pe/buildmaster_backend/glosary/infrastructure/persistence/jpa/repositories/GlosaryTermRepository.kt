package com.pe.buildmaster_backend.glosary.infrastructure.persistence.jpa.repositories

import com.pe.buildmaster_backend.glosary.domain.model.valueobjects.GlosaryTerm

interface GlosaryTermRepository {
    fun findById(id: String): GlosaryTerm
    fun findByTexto(texto: String): GlosaryTerm?
}