package com.pe.buildmaster_backend.glosary.infrastructure.persistence.jpa.repositories

import com.pe.buildmaster_backend.glosary.domain.model.valueobjects.ContextTip
import org.springframework.stereotype.Component

@Component
class ContextTipRepositoryImpl : ContextTipRepository {
    override fun getTipByStep(paso: String): ContextTip {
        return ContextTip(paso, "Recuerda revisar compatibilidad con la placa madre", "guia/cpu", "glosario/socket")
    }
}