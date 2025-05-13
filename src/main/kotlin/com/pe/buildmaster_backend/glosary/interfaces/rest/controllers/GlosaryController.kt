package com.pe.buildmaster_backend.glosary.interfaces.rest.controllers

import com.pe.buildmaster_backend.glosary.domain.model.commands.BuscarTerminoCommand
import com.pe.buildmaster_backend.glosary.domain.model.commands.ConsultarGuiaPorCategoriaCommand
import com.pe.buildmaster_backend.glosary.domain.model.commands.ConsultarTerminoCommand
import com.pe.buildmaster_backend.glosary.domain.model.commands.ObtenerTipContextualCommand
import com.pe.buildmaster_backend.glosary.domain.model.valueobjects.GuiaTecnicaDTO
import com.pe.buildmaster_backend.glosary.domain.model.valueobjects.TerminoGlosarioDTO
import com.pe.buildmaster_backend.glosary.domain.model.valueobjects.TipContextualDTO
import com.pe.buildmaster_backend.glosary.domain.services.GlosaryServiceImpl
import com.pe.buildmaster_backend.glosary.interfaces.rest.mappers.GlosaryDTOMapper
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/glosary")
class GlosaryController(
    val service: GlosaryServiceImpl,
    val mapper: GlosaryDTOMapper
) {

    @GetMapping("/termino/{id}")
    fun obtenerTermino(@PathVariable id: String): ResponseEntity<TerminoGlosarioDTO> {
        val evento = service.manejar(ConsultarTerminoCommand(id))
        val termino = mapper.toDTO(service.terminoRepo.findById(id))
        return ResponseEntity.ok(termino)
    }

    @GetMapping("/buscar")
    fun buscarTermino(@RequestParam texto: String): ResponseEntity<TerminoGlosarioDTO?> {
        val evento = service.manejar(BuscarTerminoCommand(texto)) ?: return ResponseEntity.notFound().build()
        val termino = mapper.toDTO(service.terminoRepo.findByTexto(texto)!!)
        return ResponseEntity.ok(termino)
    }

    @GetMapping("/guia/{categoria}")
    fun obtenerGuiaPorCategoria(@PathVariable categoria: String): ResponseEntity<GuiaTecnicaDTO> {
        service.manejar(ConsultarGuiaPorCategoriaCommand(categoria))
        val guia = service.guiaRepo.findByCategory(categoria)
        return ResponseEntity.ok(mapper.toDTO(guia))
    }

    @GetMapping("/tip/{paso}")
    fun obtenerTipContextual(@PathVariable paso: String): ResponseEntity<TipContextualDTO> {
        service.manejar(ObtenerTipContextualCommand(paso))
        val tip = service.tipProvider.getTipByStep(paso)
        return ResponseEntity.ok(mapper.toDTO(tip))
    }
}