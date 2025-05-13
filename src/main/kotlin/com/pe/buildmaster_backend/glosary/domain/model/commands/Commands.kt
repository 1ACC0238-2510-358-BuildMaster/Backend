package com.pe.buildmaster_backend.glosary.domain.model.commands

data class ConsultarTerminoCommand(val terminoId: String)
data class BuscarTerminoCommand(val texto: String)
data class ConsultarGuiaPorCategoriaCommand(val categoria: String)
data class ObtenerTipContextualCommand(val paso: String)