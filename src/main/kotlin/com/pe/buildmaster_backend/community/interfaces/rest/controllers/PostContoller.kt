package com.pe.buildmaster_backend.community.interfaces.rest.controllers

import com.pe.buildmaster_backend.community.application.CommentService
import com.pe.buildmaster_backend.community.application.PostService
import com.pe.buildmaster_backend.community.application.ReactionService
import com.pe.buildmaster_backend.community.domain.model.valueobjects.PostId
import com.pe.buildmaster_backend.community.domain.model.valueobjects.ReactionType
import com.pe.buildmaster_backend.community.domain.model.valueobjects.UserId
import com.pe.buildmaster_backend.community.interfaces.rest.mappers.PostResourceMapper
import com.pe.buildmaster_backend.community.interfaces.rest.resources.CreatePostRequest
import com.pe.buildmaster_backend.community.interfaces.rest.resources.PostResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.UUID // For path variables

// Placeholder para obtener el UserId del usuario autenticado
// Deberías reemplazar esto con tu lógica de autenticación real (e.g., Spring Security)
fun getAuthenticatedUserId(): UserId {
    // Simulación: En una aplicación real, esto se obtendría del contexto de seguridad.
    // Ejemplo: val authentication = SecurityContextHolder.getContext().authentication;
    //          val userIdString = authentication.name; // o un custom UserDetails
    //          return UserId(UUID.fromString(userIdString))
    // Por ahora, un ID fijo para demostración:
    println("ADVERTENCIA: Usando UserId autenticado de placeholder.")
    return UserId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000")) // ¡Reemplazar!
}


@RestController
@RequestMapping("/api/v1/community/posts")
@Tag(name = "Community Posts", description = "Operaciones para gestionar posts en la comunidad")
class PostController(
    private val postService: PostService,
    private val commentService: CommentService, // Para obtener conteos
    private val reactionService: ReactionService // Para obtener conteos
) {

    @Operation(summary = "Crear un nuevo post", description = "Permite a un usuario autenticado crear un nuevo post.")
    @ApiResponses(value = [
        ApiResponse(responseCode = "201", description = "Post creado exitosamente"),
        ApiResponse(responseCode = "400", description = "Solicitud inválida"),
        ApiResponse(responseCode = "401", description = "No autenticado")
    ])
    @PostMapping
    suspend fun createPost(@RequestBody request: CreatePostRequest): ResponseEntity<PostResponse> {
        val authorId = getAuthenticatedUserId() // Obtener del contexto de seguridad
        val command = PostResourceMapper.toCreatePostCommand(request, authorId)
        val createdPost = postService.createPost(command)
        // Para los conteos, inicialmente serán 0. Se podrían cargar si es necesario.
        val response = PostResourceMapper.toResponse(createdPost, 0, 0, 0, 0)
        return ResponseEntity.status(HttpStatus.CREATED).body(response)
    }

    @Operation(summary = "Obtener un post por su ID")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Post encontrado"),
        ApiResponse(responseCode = "404", description = "Post no encontrado")
    ])
    @GetMapping("/{postId}")
    suspend fun getPostById(@Parameter(description = "ID del post a obtener") @PathVariable postIdString: String): ResponseEntity<PostResponse> {
        val postId = PostId(UUID.fromString(postIdString))
        val post = postService.getPost(com.pe.buildmaster_backend.community.domain.model.queries.GetPostQuery(postId))
        return post?.let {
            // Cargar conteos de comentarios y reacciones
            val comments = commentService.getCommentsForPost(it.id)
            val reactions = reactionService.getReactionsForPost(it.id)
            val likeCount = reactions.count { r -> r.type == ReactionType.LIKE }
            val dislikeCount = reactions.count { r -> r.type == ReactionType.DISLIKE }
            val repostCount = reactions.count { r -> r.type == ReactionType.REPOST }
            val response = PostResourceMapper.toResponse(it, comments.size, likeCount, dislikeCount, repostCount)
            ResponseEntity.ok(response)
        } ?: ResponseEntity.notFound().build()
    }

    @Operation(summary = "Obtener posts de un usuario específico")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Lista de posts del usuario"),
        ApiResponse(responseCode = "404", description = "Usuario no encontrado o sin posts")
    ])
    @GetMapping("/user/{userIdString}")
    suspend fun getPostsByUser(@Parameter(description = "ID del usuario cuyos posts se quieren obtener") @PathVariable userIdString: String): ResponseEntity<List<PostResponse>> {
        val userId = UserId(UUID.fromString(userIdString))
        val posts = postService.getPostsByUser(com.pe.buildmaster_backend.community.domain.model.queries.GetPostsByUserQuery(userId))
        if (posts.isEmpty()) {
            return ResponseEntity.ok(emptyList()) // O Not Found si el usuario no existe y eso es un caso a manejar
        }
            val response = posts.map { post ->
            val comments = commentService.getCommentsForPost(post.id)
            val reactions = reactionService.getReactionsForPost(post.id)
            val likeCount = reactions.count { r -> r.type == ReactionType.LIKE }
            val dislikeCount = reactions.count { r -> r.type == ReactionType.DISLIKE }
            val repostCount = reactions.count { r -> r.type == ReactionType.REPOST }
            PostResourceMapper.toResponse(post, comments.size, likeCount, dislikeCount, repostCount)
        }
        return ResponseEntity.ok(response)
    }

    @Operation(summary = "Actualizar un post existente")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Post actualizado exitosamente"),
        ApiResponse(responseCode = "400", description = "Solicitud inválida"),
        ApiResponse(responseCode = "401", description = "No autorizado para editar"),
        ApiResponse(responseCode = "404", description = "Post no encontrado")
    ])
    @PutMapping("/{postIdString}")
    suspend fun updatePost(
        @Parameter(description = "ID del post a actualizar") @PathVariable postIdString: String,
        @RequestBody request: CreatePostRequest // Reutilizamos CreatePostRequest para la actualización de contenido y media
    ): ResponseEntity<PostResponse> {
        val postId = PostId(UUID.fromString(postIdString))
        val editorId = getAuthenticatedUserId() // Solo el autor puede editar (o admin)
        try {
            // Asumimos que PostService tiene un método updatePost o similar
            // Aquí usaremos el método existente que actualiza el contenido.
            // Si se quieren actualizar más campos, el PostService debería tener un método más completo
            // o usaríamos un comando específico de actualización.
            // Por ahora, solo actualizaremos el contenido.
            val updatedPost = postService.updatePostContent(postId, request.content, editorId)
            // Recalcular/recargar conteos si es necesario para la respuesta
            val comments = commentService.getCommentsForPost(updatedPost.id)
            val reactions = reactionService.getReactionsForPost(updatedPost.id)
            val likeCount = reactions.count { r -> r.type == ReactionType.LIKE }
            val dislikeCount = reactions.count { r -> r.type == ReactionType.DISLIKE }
            val repostCount = reactions.count { r -> r.type == ReactionType.REPOST }
            val response = PostResourceMapper.toResponse(updatedPost, comments.size, likeCount, dislikeCount, repostCount)
            return ResponseEntity.ok(response)
        } catch (e: NoSuchElementException) {
            return ResponseEntity.notFound().build()
        } catch (e: SecurityException) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build()
        }
    }

    @Operation(summary = "Eliminar un post")
    @ApiResponses(value = [
        ApiResponse(responseCode = "204", description = "Post eliminado exitosamente"),
        ApiResponse(responseCode = "401", description = "No autorizado para eliminar"),
        ApiResponse(responseCode = "404", description = "Post no encontrado")
    ])
    @DeleteMapping("/{postIdString}")
    suspend fun deletePost(@Parameter(description = "ID del post a eliminar") @PathVariable postIdString: String): ResponseEntity<Void> {
        val postId = PostId(UUID.fromString(postIdString))
        val userId = getAuthenticatedUserId() // Para verificar permisos en el servicio si es necesario

        // El PostDomainService (usado por PostService) maneja la eliminación de datos asociados.
        // Se podría añadir una verificación de permisos aquí o delegarla completamente al servicio.
        // Por ejemplo, el servicio podría requerir el UserId para verificar la autoría.
        // postService.deletePost(postId, userId) // Si el servicio lo requiere
        try {
            val post = postService.getPost(com.pe.buildmaster_backend.community.domain.model.queries.GetPostQuery(postId))
                ?: return ResponseEntity.notFound().build()

            if (post.authorId != userId) { // Simple verificación de autoría en el controlador
                // En una app real, esto podría ser más robusto o manejado por Spring Security con @PreAuthorize
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build()
            }
            postService.deletePost(postId)
            return ResponseEntity.noContent().build()
        } catch (e: NoSuchElementException) {
            return ResponseEntity.notFound().build()
        }
    }
}