package com.pe.buildmaster_backend.glosary.infrastructure.persistence.jpa.repositories

import com.pe.buildmaster_backend.glosary.domain.model.valueobjects.ContextTip

interface ContextTipRepository {
    fun getTipByStep(paso: String): ContextTip
}