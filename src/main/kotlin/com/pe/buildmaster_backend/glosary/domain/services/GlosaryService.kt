package com.pe.buildmaster_backend.glosary.domain.services

import com.pe.buildmaster_backend.glosary.application.internal.eventhandlers.GuiaConsultadaEvent
import com.pe.buildmaster_backend.glosary.application.internal.eventhandlers.TerminoConsultadoEvent
import com.pe.buildmaster_backend.glosary.application.internal.eventhandlers.TipMostradoEvent
import com.pe.buildmaster_backend.glosary.domain.model.commands.BuscarTerminoCommand
import com.pe.buildmaster_backend.glosary.domain.model.commands.ConsultarGuiaPorCategoriaCommand
import com.pe.buildmaster_backend.glosary.domain.model.commands.ConsultarTerminoCommand
import com.pe.buildmaster_backend.glosary.domain.model.commands.ObtenerTipContextualCommand

interface GlosaryService {
    fun manejar(cmd: ConsultarTerminoCommand): TerminoConsultadoEvent
    fun manejar(cmd: BuscarTerminoCommand): TerminoConsultadoEvent?
    fun manejar(cmd: ConsultarGuiaPorCategoriaCommand): GuiaConsultadaEvent
    fun manejar(cmd: ObtenerTipContextualCommand): TipMostradoEvent
}