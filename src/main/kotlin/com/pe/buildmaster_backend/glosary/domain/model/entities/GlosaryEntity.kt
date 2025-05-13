package com.pe.buildmaster_backend.glosary.domain.model.entities

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.*

@Entity
@Table(name = "glosary")
data class GlosaryEntity(
    @Id val id: UUID,
    val termino: String,
    val definicion: String,
    val categoria: String
)