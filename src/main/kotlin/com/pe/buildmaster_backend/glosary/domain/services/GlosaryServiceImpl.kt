package com.pe.buildmaster_backend.glosary.domain.services

import com.pe.buildmaster_backend.glosary.application.internal.eventhandlers.GuiaConsultadaEvent
import com.pe.buildmaster_backend.glosary.application.internal.eventhandlers.TerminoConsultadoEvent
import com.pe.buildmaster_backend.glosary.application.internal.eventhandlers.TipMostradoEvent
import com.pe.buildmaster_backend.glosary.domain.model.commands.BuscarTerminoCommand
import com.pe.buildmaster_backend.glosary.domain.model.commands.ConsultarGuiaPorCategoriaCommand
import com.pe.buildmaster_backend.glosary.domain.model.commands.ConsultarTerminoCommand
import com.pe.buildmaster_backend.glosary.domain.model.commands.ObtenerTipContextualCommand
import com.pe.buildmaster_backend.glosary.infrastructure.persistence.jpa.repositories.ContextTipRepository
import com.pe.buildmaster_backend.glosary.infrastructure.persistence.jpa.repositories.GlosaryEventPublisher
import com.pe.buildmaster_backend.glosary.infrastructure.persistence.jpa.repositories.GlosaryTermRepository
import com.pe.buildmaster_backend.glosary.infrastructure.persistence.jpa.repositories.GuideRepository
import org.springframework.stereotype.Service
import java.util.*
import kotlin.NoSuchElementException

@Service
class GlosaryServiceImpl(
    val terminoRepo: GlosaryTermRepository,
    val guiaRepo: GuideRepository,
    val tipProvider: ContextTipRepository,
    val publisher: GlosaryEventPublisher
) : GlosaryService {
    override fun manejar(cmd: ConsultarTerminoCommand): TerminoConsultadoEvent {
        val termino = terminoRepo.findById(cmd.terminoId)
        publisher.publicar(TerminoConsultadoEvent(cmd.terminoId))
        return TerminoConsultadoEvent(cmd.terminoId)
    }

    override fun manejar(cmd: BuscarTerminoCommand): TerminoConsultadoEvent? {
        val termino = terminoRepo.findByTexto(cmd.texto) ?: return null
        publisher.publicar(TerminoConsultadoEvent(termino.id))
        return TerminoConsultadoEvent(termino.id)
    }

    override fun manejar(cmd: ConsultarGuiaPorCategoriaCommand): GuiaConsultadaEvent {
        val guia = guiaRepo.findByCategory(cmd.categoria)
        publisher.publicar(GuiaConsultadaEvent(cmd.categoria))
        return GuiaConsultadaEvent(cmd.categoria)
    }

    override fun manejar(cmd: ObtenerTipContextualCommand): TipMostradoEvent {
        val tip = tipProvider.getTipByStep(cmd.paso)
        publisher.publicar(TipMostradoEvent(cmd.paso))
        return TipMostradoEvent(cmd.paso)
    }
}