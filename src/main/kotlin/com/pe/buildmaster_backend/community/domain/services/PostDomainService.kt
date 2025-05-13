package com.pe.buildmaster_backend.community.domain.services

import com.pe.buildmaster_backend.community.domain.interfaces.CommentRepository
import com.pe.buildmaster_backend.community.domain.interfaces.PostRepository
import com.pe.buildmaster_backend.community.domain.interfaces.ReactionRepository
import com.pe.buildmaster_backend.community.domain.model.entities.Post
import com.pe.buildmaster_backend.community.domain.model.valueobjects.PostId
import com.pe.buildmaster_backend.community.domain.model.valueobjects.UserId
import org.springframework.stereotype.Service

@Service
class PostDomainService(
    private val postRepository: PostRepository,
    private val commentRepository: CommentRepository, // Podría necesitarse para ciertas lógicas
    private val reactionRepository: ReactionRepository // Podría necesitarse para ciertas lógicas
) {


    suspend fun createNewPost(authorId: UserId, content: String, mediaUrls: List<String>): Post {
        // Aquí podría haber validaciones de dominio complejas antes de crear el post.
        // Por ejemplo, verificar si el usuario tiene permitido postear,
        // si el contenido viola alguna política, etc.
        // Estas validaciones podrían requerir acceso a otros repositorios o servicios de dominio.

        // val newPost = Post(id = PostId(), authorId = authorId, content = content, mediaAttachments = mediaUrls.map { FileContent(...) })
        // return postRepository.save(newPost)
        // La creación real del objeto Post y su guardado usualmente es orquestada por el Servicio de Aplicación
        // que a su vez llama al repositorio.
        // Un servicio de dominio se enfocaría más en la lógica de negocio que no es responsabilidad de una entidad.

        // Por ahora, este servicio de dominio no tiene mucha responsabilidad.
        // Podría ser más útil para operaciones como:
        // - Fusionar posts
        // - Manejar la propagación de un "repost" si tiene lógica compleja (e.g., notificar seguidores del reposteador)
        // - Aplicar reglas de moderación que involucren múltiples posts o usuarios.
        throw NotImplementedError("PostDomainService example method, implement actual logic or remove if not needed.")
    }

    suspend fun deletePostAndAssociatedData(postId: PostId) {
        // Esta es una buena candidata para un servicio de dominio si la eliminación de un post
        // implica eliminar comentarios y reacciones, asegurando la consistencia.
        // El servicio de aplicación llamaría a este método.

        val postToDelete = postRepository.findById(postId)
            ?: throw NoSuchElementException("Post with id $postId not found")

        // 1. Eliminar reacciones asociadas
        val reactions = reactionRepository.findByPostId(postId)
        reactions.forEach { reactionRepository.delete(it) }

        // 2. Eliminar comentarios asociados
        val comments = commentRepository.findByPostId(postId)
        comments.forEach { commentRepository.delete(it) }

        // 3. Eliminar el post
        postRepository.delete(postToDelete)

        // Aquí podrían ir eventos de dominio para notificar que un post ha sido eliminado.
    }
}