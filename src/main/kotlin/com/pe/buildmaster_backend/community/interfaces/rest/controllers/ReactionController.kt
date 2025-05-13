package com.pe.buildmaster_backend.community.interfaces.rest.controllers

import com.pe.buildmaster_backend.community.application.ReactionService
import com.pe.buildmaster_backend.community.domain.model.valueobjects.PostId
import com.pe.buildmaster_backend.community.domain.model.valueobjects.ReactionType
import com.pe.buildmaster_backend.community.interfaces.rest.mappers.ReactionResourceMapper
import com.pe.buildmaster_backend.community.interfaces.rest.resources.AddReactionRequest
import com.pe.buildmaster_backend.community.interfaces.rest.resources.ReactionResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping("/api/v1/community/posts/{postIdString}/reactions")
@Tag(name = "Community Reactions", description = "Operaciones para gestionar reacciones en posts")
class ReactionController(
    private val reactionService: ReactionService
) {

    @Operation(summary = "Añadir o actualizar una reacción a un post")
    @ApiResponses(value = [
        ApiResponse(responseCode = "201", description = "Reacción añadida/actualizada"),
        ApiResponse(responseCode = "400", description = "Solicitud inválida o tipo de reacción no permitido"),
        ApiResponse(responseCode = "401", description = "No autenticado"),
        ApiResponse(responseCode = "404", description = "Post no encontrado"),
        ApiResponse(responseCode = "409", description = "Conflicto, ej: reacción ya existe y fue removida (toggle)")
    ])
    @PostMapping
    suspend fun addOrUpdateReaction(
        @Parameter(description = "ID del post al que se reacciona") @PathVariable postIdString: String,
        @RequestBody request: AddReactionRequest
    ): ResponseEntity<ReactionResponse> {
        val postId = PostId(UUID.fromString(postIdString))
        val userId = getAuthenticatedUserId()
        val command = ReactionResourceMapper.toAddReactionCommand(request, postId, userId)
        try {
            val reaction = reactionService.addOrUpdateReaction(command)
            val response = ReactionResourceMapper.toResponse(reaction)
            return ResponseEntity.status(HttpStatus.CREATED).body(response)
        } catch (e: NoSuchElementException) {
            return ResponseEntity.notFound().build()
        } catch (e: IllegalStateException) { // Usado en ReactionService para indicar toggle off o conflicto
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null) // O un DTO de error específico
        }
    }

    @Operation(summary = "Obtener todas las reacciones de un post")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Lista de reacciones"),
        ApiResponse(responseCode = "404", description = "Post no encontrado")
    ])
    @GetMapping
    suspend fun getReactionsForPost(
        @Parameter(description = "ID del post cuyas reacciones se quieren obtener") @PathVariable postIdString: String
    ): ResponseEntity<List<ReactionResponse>> {
        val postId = PostId(UUID.fromString(postIdString))
        try {
            val reactions = reactionService.getReactionsForPost(postId)
            val response = reactions.map { ReactionResourceMapper.toResponse(it) }
            return ResponseEntity.ok(response)
        } catch (e: NoSuchElementException) {
            return ResponseEntity.notFound().build()
        }
    }

    @Operation(summary = "Eliminar una reacción específica de un post")
    @ApiResponses(value = [
        ApiResponse(responseCode = "204", description = "Reacción eliminada"),
        ApiResponse(responseCode = "401", description = "No autenticado"),
        ApiResponse(responseCode = "404", description = "Post o Reacción no encontrada")
    ])
    @DeleteMapping // Podría ser más específico, e.g., /type/{reactionType} o pasar en el body/params
    suspend fun removeReaction(
        @Parameter(description = "ID del post") @PathVariable postIdString: String,
        @Parameter(description = "Tipo de reacción a eliminar", required = true) @RequestParam reactionType: ReactionType
    ): ResponseEntity<Void> {
        val postId = PostId(UUID.fromString(postIdString))
        val userId = getAuthenticatedUserId()
        try {
            reactionService.removeReaction(postId, userId, reactionType)
            return ResponseEntity.noContent().build()
        } catch (e: NoSuchElementException) {
            return ResponseEntity.notFound().build()
        }
    }
}