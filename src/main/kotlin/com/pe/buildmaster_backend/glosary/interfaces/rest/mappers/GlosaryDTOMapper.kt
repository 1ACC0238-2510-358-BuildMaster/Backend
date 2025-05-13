package com.pe.buildmaster_backend.glosary.interfaces.rest.mappers

import com.pe.buildmaster_backend.glosary.domain.model.valueobjects.*
import org.springframework.stereotype.Component

@Component
class GlosaryDTOMapper {
    fun toDTO(entity: GlosaryTerm): TerminoGlosarioDTO = TerminoGlosarioDTO(
        id = entity.id, termino = entity.termino,
        definicion = entity.definicion, ejemplos = entity.ejemplos
    )

    fun toDTO(entity: Guide): GuiaTecnicaDTO = GuiaTecnicaDTO(
        id = entity.id, categoria = entity.categoria.nombre, contenido = entity.contenido
    )

    fun toDTO(entity: ContextTip): TipContextualDTO = TipContextualDTO(
        paso = entity.paso, mensaje = entity.mensaje,
        linkGuia = entity.linkGuia, linkGlosario = entity.linkGlosario
    )
}