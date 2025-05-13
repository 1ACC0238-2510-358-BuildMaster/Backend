package com.pe.buildmaster_backend.community.application

import com.pe.buildmaster_backend.community.domain.interfaces.PostRepository
import com.pe.buildmaster_backend.community.domain.model.commands.CreatePostCommand
import com.pe.buildmaster_backend.community.domain.model.entities.Post
import com.pe.buildmaster_backend.community.domain.model.queries.GetPostQuery
import com.pe.buildmaster_backend.community.domain.model.queries.GetPostsByUserQuery
import com.pe.buildmaster_backend.community.domain.model.valueobjects.PostId
import com.pe.buildmaster_backend.community.domain.model.valueobjects.UserId
import com.pe.buildmaster_backend.community.domain.services.PostDomainService
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class PostService(
    private val postRepository: PostRepository,
    private val postDomainService: PostDomainService // Para operaciones más complejas como la eliminación
) {

    suspend fun createPost(command: CreatePostCommand): Post {
        // Aquí podrían ir validaciones de la capa de aplicación,
        // por ejemplo, si el usuario tiene permiso para crear un post (aunque esto
        // a menudo se maneja con seguridad a nivel de infraestructura/framework).

        val post = Post(
            id = PostId(), // Nuevo ID generado
            authorId = command.authorId,
            content = command.content,
            mediaAttachments = command.mediaAttachments,
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now()
        )
        return postRepository.save(post)
    }

    suspend fun getPost(query: GetPostQuery): Post? {
        return postRepository.findById(query.postId)
    }

    suspend fun getPostsByUser(query: GetPostsByUserQuery): List<Post> {
        return postRepository.findByAuthorId(query.userId)
    }

    // Ejemplo de cómo se usaría el PostDomainService para una operación más compleja
    suspend fun deletePost(postId: PostId) {
        // La lógica de eliminar el post y sus datos asociados (comentarios, reacciones)
        // está encapsulada en el PostDomainService para asegurar la consistencia.
        postDomainService.deletePostAndAssociatedData(postId)
    }

    suspend fun updatePostContent(postId: PostId, newContent: String, editorId: UserId): Post {
        val post = postRepository.findById(postId)
            ?: throw NoSuchElementException("Post with id $postId not found")

        // Validación: Solo el autor puede editar su post (ejemplo simple)
        if (post.authorId != editorId) {
            throw SecurityException("User $editorId is not authorized to edit post $postId")
        }


        val updatedPost = post.copy(
            content = newContent,
            updatedAt = LocalDateTime.now()
        )

        return postRepository.save(updatedPost)
    }
}