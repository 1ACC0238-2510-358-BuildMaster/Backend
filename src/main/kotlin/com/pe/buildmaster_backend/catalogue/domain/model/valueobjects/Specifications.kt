package com.pe.buildmaster_backend.catalogue.domain.model.valueobjects

import jakarta.persistence.Embeddable

@Embeddable
class Specifications(
    val socket: String,
    val memoryType: String,
    val powerConsumptionWatts: Int,
    val formFactor: String
){
    // Constructor vacío requerido por JPA
    constructor() : this("", "", 0, "")
}