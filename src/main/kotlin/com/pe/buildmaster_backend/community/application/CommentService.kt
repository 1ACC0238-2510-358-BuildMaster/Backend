package com.pe.buildmaster_backend.community.application

import com.pe.buildmaster_backend.community.domain.interfaces.CommentRepository
import com.pe.buildmaster_backend.community.domain.interfaces.PostRepository
import com.pe.buildmaster_backend.community.domain.model.commands.AddCommentCommand
import com.pe.buildmaster_backend.community.domain.model.entities.Comment
import com.pe.buildmaster_backend.community.domain.model.valueobjects.CommentId
import com.pe.buildmaster_backend.community.domain.model.valueobjects.PostId
import com.pe.buildmaster_backend.community.domain.model.valueobjects.UserId
import org.springframework.stereotype.Service
import java.time.LocalDateTime


@Service
class CommentService(
    private val commentRepository: CommentRepository,
    private val postRepository: PostRepository // Necesario para validar que el post existe
) {

    suspend fun addComment(command: AddCommentCommand): Comment {
        // Validar que el post al que se comenta existe
        postRepository.findById(command.postId)
            ?: throw NoSuchElementException("Post with id ${command.postId.value} not found. Cannot add comment.")

        val comment = Comment(
            id = CommentId(),
            postId = command.postId,
            authorId = command.authorId,
            content = command.content,
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now()
        )
        return commentRepository.save(comment)
    }

    suspend fun getCommentsForPost(postId: PostId): List<Comment> {
        // Validar que el post existe antes de buscar sus comentarios
        postRepository.findById(postId)
            ?: throw NoSuchElementException("Post with id ${postId.value} not found.")
        return commentRepository.findByPostId(postId)
    }

    suspend fun deleteComment(commentId: CommentId, userId: UserId) {
        val comment = commentRepository.findById(commentId)
            ?: throw NoSuchElementException("Comment with id ${commentId.value} not found.")

        // Validación: Solo el autor del comentario o quizás el autor del post pueden eliminar (simplificado a autor del comentario)
        if (comment.authorId != userId) {
            throw SecurityException("User ${userId.value} is not authorized to delete comment ${commentId.value}")
        }
        commentRepository.delete(comment)
    }

    suspend fun updateCommentContent(commentId: CommentId, newContent: String, editorId: UserId): Comment {
        val comment = commentRepository.findById(commentId)
            ?: throw NoSuchElementException("Comment with id $commentId not found")

        if (comment.authorId != editorId) {
            throw SecurityException("User $editorId is not authorized to edit comment $commentId")
        }

        val updatedComment = comment.copy(
            content = newContent,
            updatedAt = LocalDateTime.now()
        )
        return commentRepository.save(updatedComment)
    }
}