package com.pe.buildmaster_backend.community.interfaces.rest.controllers

import com.pe.buildmaster_backend.community.application.CommentService
import com.pe.buildmaster_backend.community.domain.model.valueobjects.CommentId
import com.pe.buildmaster_backend.community.domain.model.valueobjects.PostId
import com.pe.buildmaster_backend.community.interfaces.rest.mappers.CommentResourceMapper
import com.pe.buildmaster_backend.community.interfaces.rest.resources.AddCommentRequest
import com.pe.buildmaster_backend.community.interfaces.rest.resources.CommentResponse
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
@RequestMapping("/api/v1/community/posts/{postIdString}/comments")
@Tag(name = "Community Comments", description = "Operaciones para gestionar comentarios en posts")
class CommentController(
    private val commentService: CommentService
) {

    @Operation(summary = "Añadir un comentario a un post")
    @ApiResponses(value = [
        ApiResponse(responseCode = "201", description = "Comentario añadido exitosamente"),
        ApiResponse(responseCode = "400", description = "Solicitud inválida"),
        ApiResponse(responseCode = "401", description = "No autenticado"),
        ApiResponse(responseCode = "404", description = "Post no encontrado")
    ])
    @PostMapping
    suspend fun addComment(
        @Parameter(description = "ID del post al que se añade el comentario") @PathVariable postIdString: String,
        @RequestBody request: AddCommentRequest
    ): ResponseEntity<CommentResponse> {
        val postId = PostId(UUID.fromString(postIdString))
        val authorId = getAuthenticatedUserId() // Obtener del contexto de seguridad
        val command = CommentResourceMapper.toAddCommentCommand(request, postId, authorId)
        try {
            val createdComment = commentService.addComment(command)
            val response = CommentResourceMapper.toResponse(createdComment)
            return ResponseEntity.status(HttpStatus.CREATED).body(response)
        } catch (e: NoSuchElementException) { // Si el post no existe
            return ResponseEntity.notFound().build()
        }
    }

    @Operation(summary = "Obtener todos los comentarios de un post")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Lista de comentarios"),
        ApiResponse(responseCode = "404", description = "Post no encontrado")
    ])
    @GetMapping
    suspend fun getCommentsForPost(
        @Parameter(description = "ID del post cuyos comentarios se quieren obtener") @PathVariable postIdString: String
    ): ResponseEntity<List<CommentResponse>> {
        val postId = PostId(UUID.fromString(postIdString))
        try {
            val comments = commentService.getCommentsForPost(postId)
            val response = comments.map { CommentResourceMapper.toResponse(it) }
            return ResponseEntity.ok(response)
        } catch (e: NoSuchElementException) { // Si el post no existe
            return ResponseEntity.notFound().build()
        }
    }

    @Operation(summary = "Actualizar un comentario existente")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Comentario actualizado"),
        ApiResponse(responseCode = "400", description = "Solicitud inválida"),
        ApiResponse(responseCode = "401", description = "No autorizado"),
        ApiResponse(responseCode = "404", description = "Comentario o Post no encontrado")
    ])
    @PutMapping("/{commentIdString}")
    suspend fun updateComment(
        @Parameter(description = "ID del post (contexto)") @PathVariable postIdString: String, // No usado directamente si commentId es globalmente único
        @Parameter(description = "ID del comentario a actualizar") @PathVariable commentIdString: String,
        @RequestBody request: AddCommentRequest // Reutilizamos para actualizar contenido
    ): ResponseEntity<CommentResponse> {
        val commentId = CommentId(UUID.fromString(commentIdString))
        // val postIdContext = PostId(UUID.fromString(postIdString)) // Para validación extra si es necesario
        val editorId = getAuthenticatedUserId()
        try {
            val updatedComment = commentService.updateCommentContent(commentId, request.content, editorId)
            return ResponseEntity.ok(CommentResourceMapper.toResponse(updatedComment))
        } catch (e: NoSuchElementException) {
            return ResponseEntity.notFound().build()
        } catch (e: SecurityException) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build()
        }
    }


    @Operation(summary = "Eliminar un comentario")
    @ApiResponses(value = [
        ApiResponse(responseCode = "204", description = "Comentario eliminado"),
        ApiResponse(responseCode = "401", description = "No autorizado"),
        ApiResponse(responseCode = "404", description = "Comentario o Post no encontrado")
    ])
    @DeleteMapping("/{commentIdString}")
    suspend fun deleteComment(
        @Parameter(description = "ID del post (contexto)") @PathVariable postIdString: String, // No usado directamente si commentId es globalmente único
        @Parameter(description = "ID del comentario a eliminar") @PathVariable commentIdString: String
    ): ResponseEntity<Void> {
        val commentId = CommentId(UUID.fromString(commentIdString))
        val userId = getAuthenticatedUserId()
        try {
            commentService.deleteComment(commentId, userId)
            return ResponseEntity.noContent().build()
        } catch (e: NoSuchElementException) {
            return ResponseEntity.notFound().build()
        } catch (e: SecurityException) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build()
        }
    }
}