package com.pe.buildmaster_backend.glosary.domain.model.valueobjects

data class TerminoGlosarioDTO(val id: String, val termino: String, val definicion: String, val ejemplos: List<String>)
data class GuiaTecnicaDTO(val id: String, val categoria: String, val contenido: String)
data class TipContextualDTO(val paso: String, val mensaje: String, val linkGuia: String?, val linkGlosario: String?)